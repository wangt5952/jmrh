<template>
<div class="tab-container">

  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span>关键字</span>
      <el-input v-model="input.objName" placeholder="" style="width:100px;"></el-input>
      <span style="margin-left: 15px;">是否可见</span>
      <el-select v-model="input.status" style="width:100px;height:30px" placeholder="请选择">
        <el-option label="请选择" key="" value="">
        </el-option>
        <el-option label="可见" key=1 value=1>
        </el-option>
        <el-option label="不可见" key=0 value=0>
        </el-option>
      </el-select>
      <span style="margin-left: 15px;">状态</span>
      <el-select v-model="input.checkStatus" style="width:100px;height:30px" placeholder="请选择">
        <el-option label="草稿" :key=-1 :value=-1>
        </el-option>
        <el-option label="待审核" :key=0 :value=0>
        </el-option>
        <el-option label="已发布" :key=1 :value=1>
        </el-option>
        <el-option label="驳回" :key=2 :value=2>
        </el-option>
      </el-select>

      <el-button style="margin-left:20px" @click="loadPageList" type="primary">查询</el-button>
      <div class="" style="padding:10px 0px;">
        <el-button v-if="userType =='0' " style="" @click="handleEdit" type="primary">发布成果</el-button>
        <el-button v-if="userType =='0' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-if="userType =='0' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-if="userType =='0' && tfcheckStatus == 1" style="" @click="pldc" type="primary">批量导出</el-button>
      </div>

    </div>
  </div>

  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="selection" width="55">
    </el-table-column>
    <el-table-column align="center" label="编号">
      <template slot-scope="scope">
                    <!-- <span>{{ scope.row.id }}</span> -->
                </template>
    </el-table-column>
    <el-table-column align="center" label="名称">
      <template slot-scope="scope">
                    <div @click="showDetail(scope.row,'edit')" class="clickText" >{{ scope.row.name }}</div>
                </template>
    </el-table-column>
    <el-table-column align="center" label="需求概述">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.reqDesc}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 0" align="center" label="创建时间">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.createTime}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 0" align="center" label="修改时间 ">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.updateTime}}</span>
                </template>
    </el-table-column>



    <el-table-column v-if="tfcheckStatus == 1" align="center" label="所属领域">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.domain}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="是否可见">
      <template slot-scope="scope">
                        <span v-if="scope.row.status == 1">可见</span>
                        <span v-if="scope.row.status == 0">不可见</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="发布人">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.creater}}</span>
                    </template>
    </el-table-column>

    <el-table-column v-if="tfcheckStatus == 1" align="center" label="审核用户">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.creater}}</span>
                    </template>
    </el-table-column>

    <el-table-column v-if="userType =='0'" align="center" label="操作">
      <template slot-scope="scope">
                    <div style="">

                        <div v-if="tfcheckStatus == 0" @click="handlesh(scope.row)" class="clickText" style="float:left">
                          审核
                        </div>
                      <div @click="handleEdit(scope.row,'edit')" class="clickText" style="float:left">
                        编辑
                      </div>
                  <span v-if="scope.row.status == 1 && tfcheckStatus == 1">  <div @click="handlexj(scope.row)" class="clickText" style="float:left">
                                            下架
                            </div></span>
                  <span v-if="scope.row.status == 0 && tfcheckStatus == 1">  <div @click="handlesj(scope.row)" class="clickText" style="float:left">
                                            上架
                  </div></span>

                    </div>
                </template>
    </el-table-column>

  </el-table>


  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>

  <!-- <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogShowDep" width="30%" top='5%'>
    <el-tree :data="treeData" show-checkbox default-expand-all node-key="id" ref="tree" highlight-current :props="defaultProps">
    </el-tree>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dialogShowDep = false">关闭</el-button>
    </span>
  </el-dialog> -->



  <el-dialog title="审核" :visible.sync="dialogShowSH" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="24">
        <el-col :span="24">

          <el-form-item label="审核">
            <el-select v-model="rej.way" style="height:30px" placeholder="请选择">

              <el-option label="通过" key="1" value="1">
              </el-option>
              <el-option label="驳回" key="2" value="2">
              </el-option>
            </el-select>
          </el-form-item>


          <el-form-item v-if="rej.way == '2'" label="驳回原因">
            <el-input v-model="rej.info" placeholder=""></el-input>
          </el-form-item>
        </el-col>

      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary" style="" @click="saveReject(rej)">保存</el-button>
      <el-button type="primary" @click="dialogShowSH = false">关闭</el-button>
    </span>
  </el-dialog>


  <el-dialog title="成果详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
    <div class="textr paddinga">
      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handlePrint">打印</el-button>

      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
      <table id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>
        <col width=72 span=8 style='width:54pt'>
        <tr height=23 style='mso-height-source:userset;height:17.4pt' id='r0'>
          <td colspan=8 id='tc0' height=23 class=x21 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:17.4pt;'>军民科技协同创新技术成果信息登记表</td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r2'>
          <td height=38 class=x23 style='height:28.8pt;'>技术成果名称</td>
          <td colspan=7 id='tc2' class=x24 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'></td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r3'>
          <td height=38 class=x25 style='height:28.8pt;'>企业名称</td>
          <td colspan=2 id='tc3' class=x54 style='border-bottom:1px solid windowtext;'></td>
          <td class=x25>通讯地址</td>
          <td class=x28></td>
          <td class=x25>所在地区</td>
          <td colspan=2 id='tc4' class=x54 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'><span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>省<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>市<span style='mso-spacerun:yes'>&nbsp; </span>区（县）<br>下拉选择</td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r4'>
          <td height=38 class=x25 style='height:28.8pt;'>项目负责人</td>
          <td colspan=3 id='tc5' class=x54 style='border-bottom:1px solid windowtext;'></td>
          <td class=x25>电话</td>
          <td class=x30></td>
          <td class=x31>邮箱</td>
          <td class=x32></td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r5'>
          <td height=38 class=x25 style='height:28.8pt;'>项目联系人</td>
          <td colspan=3 id='tc6' class=x54 style='border-bottom:1px solid windowtext;'></td>
          <td class=x33>电话</td>
          <td class=x30></td>
          <td class=x34>邮箱</td>
          <td class=x35></td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r6'>
          <td height=38 class=x36 style='height:28.8pt;'>所属领域</td>
          <td colspan=7 id='tc7' class=x37 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>□智能装备<span style='mso-spacerun:yes'>&nbsp; </span>□电子信息<span style='mso-spacerun:yes'>&nbsp; </span>□新材料<span style='mso-spacerun:yes'>&nbsp; </span>□航空航天<span style='mso-spacerun:yes'>&nbsp; </span>□生物技术与新医药 <br> □能源与环保<span style='mso-spacerun:yes'>&nbsp; </span>□其他</td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r7'>
          <td height=38 class=x23 style='height:28.8pt;'>技术成果简介</td>
          <td colspan=7 id='tc8' class=x55 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'></td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r8'>
          <td height=38 class=x41 style='height:28.8pt;'>技术创新点</td>
          <td colspan=7 id='tc9' class=x42 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'></td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.4pt' id='r9'>
          <td rowspan=4 height=76 class=x44 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:57.6pt;'>知识产权情况</td>
          <td colspan=7 id='tc10' class=x43 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>
            <font class="font3">□已申请专利</font>
            <font class="font11"> </font>
            <font class="font12"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font11"> </font>
            <font class="font3">件</font>
            <font class="font11"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><br></font>
            <font class="font3">□获授权专利</font>
            <font class="font11"> </font>
            <font class="font12"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font3">件</font>
            <font class="font11"><span style='mso-spacerun:yes'>&nbsp; </span><br></font>
            <font class="font3">□其他知识产权</font>
            <font class="font11"> </font>
            <font class="font12"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font3">件</font>
          </td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.4pt' id='r10'>
          <td class=x44>序号</td>
          <td class=x30>专利名称</td>
          <td class=x32>专利类型</td>
          <td class=x32>专利号</td>
          <td class=x44>专利权人</td>
          <td colspan=2 id='tc11' class=x44 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>有效期</td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.4pt' id='r11'>
          <td class=x45></td>
          <td class=x46></td>
          <td class=x47></td>
          <td class=x48></td>
          <td class=x45></td>
          <td colspan=2 id='tc12' class=x49 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>备注：可动态添加</td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.4pt' id='r12'>
          <td class=x45></td>
          <td class=x46></td>
          <td class=x47></td>
          <td class=x48></td>
          <td class=x45></td>
          <td colspan=2 id='tc13' class=x24 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'></td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r13'>
          <td height=38 class=x50 style='height:28.8pt;'>所处阶段</td>
          <td colspan=7 id='tc14' class=x56 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>□实验室阶段<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□试制阶段<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□产业化阶段<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r14'>
          <td height=38 class=x41 style='height:28.8pt;'>合作方式</td>
          <td colspan=7 id='tc15' class=x56 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>□技术转让<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□技术许可<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□技术开发<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>□技术服务<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□技术入股</td>
        </tr>
        <tr height=0 style='display:none'>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
        </tr>
      </table>
    </div>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dialogShowDep = false">关闭</el-button>
    </span>
  </el-dialog>
</div>
</template>

<script>
import {
  getAllrole
} from '@/api/role'
import {
  depgetAll
} from '@/api/department'
import {
  getresults,
  addLib,
  delLib,
  rejectUserDetail,
  offUserDetail,
  onUserDetail,
  PLrejectUserDetail,
  PLoffUserDetails,
  updateLevel
} from '@/api/library'
export default {
  data() {
    return {
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      dialogFormVisible: false,
      dialogShowRole: false,
      dialogShowDep: false,
      dialogadd: false,
      dialogsave: false,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
      },
      total: null,
      textMap: {
        update: '编辑',
        dep: '部门详情',
        role: '角色详情',
        create: '添加'
      },
      obj: {
        userName: '',
        userPassword: '',
        fullName: '',
        department: '',
      },
      selected: [],
      options: [{
        label: 'foo',
        value: 'Foo'
      }, {
        label: 'boo',
        value: 'boo'
      }, {
        label: 'coo',
        value: 'coo'
      }],
      treeData: [],
      loading: true,
      multipleSelection: [],
      detailData: '',
      tfcheckStatus: '',
      dialogShowDep: false,
      dialogShowSH: false,
        rej: {
          way: '1',
          info: '',
          formId: ''
        },
        xyset: {
          creditLevel: '',
          id: ''
        },
      userType: '',
      input: {
        objName: '',
        status: '',
        checkStatus: 1,
        creditLevel: '',
      },
    }
  },
  async mounted() {
    if (typeof this.$route.query.checkStatus == 'number' ) {
      this.input.checkStatus = this.$route.query.checkStatus
    }
    this.loadPageList()
    this.tfcheckStatus = this.input.checkStatus
    this.userType = window.sessionStorage.getItem('userType')

  },
  computed: {},
  methods: {
    async saveFile(objdata) {
      let {
        data,
        success
      } = await updateLevel(objdata, '3')
      this.$message({
        message: '保存成功',
        type: 'success'
      });
      this.loadPageList()
    },

    async saveReject(rej) {
      if (rej.way == '1') {
        let arr = []
        arr.push(rej.formId)
        let {
          data,
          success
        } = await PLrejectUserDetail(arr)
        if (success) {
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.loadPageList()
        }
      } else {
        let {
          data,
          success
        } = await rejectUserDetail(rej)
        if (success) {
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.loadPageList()
        }
      }
    },
    handleSelectionChange(val) {
      let arr = []
      for (let i in val) {
        arr.push(val[i].id)
      }
      this.multipleSelection = arr;
    },
    async plsh() {
      let {
        data,
        success
      } = await PLrejectUserDetail(this.multipleSelection)
      if (success) {
        this.$message({
          type: 'success',
          message: '审核成功!'
        });
        this.loadPageList()
      }


    },
    async plxj() {
      let {
        data,
        success
      } = await PLoffUserDetails(this.multipleSelection, '6')
      if (success) {
        this.$message({
          type: 'success',
          message: '下架成功!'
        });
        this.loadPageList()
      }
    },
    pldc() {
      this.$message({
        type: 'success',
        message: '导出成功!'
      });
    },
    handlexy(data) {
      this.dialogShowLevel = true
      this.xyset.id = data.id
    },
    handlesh(data) {
      this.dialogShowSH = true
      this.rej.formId = data.id
    },
    async handlexj(params) {
      debugger
      let obj = {}
      obj.id = params.id
      obj.type = '6'
      let {
        data,
        success
      } = await offUserDetail(obj)
      if (success) {
        this.$message({
          message: '下架成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
    async handlesj(params) {
      debugger
      let obj = {}
      obj.id = params.id
      obj.type = '6'
      let {
        data,
        success
      } = await onUserDetail(obj)
      if (success) {
        this.$message({
          message: '上架成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
    showDetail(data) {
      this.detailData = JSON.parse(data.form.detail)
      this.dialogShowDep = true
    },
    async loadPageList() {
      if (this.input) {
        this.listQuery.objName = this.input.objName
        this.listQuery.status = this.input.status
        this.listQuery.checkStatus = this.input.checkStatus

        this.tfcheckStatus = this.input.checkStatus

        this.listQuery.creditLevel = this.input.creditLevel
      } else {
        this.listQuery.objName = ''
      }
      let {
        data,
        success
      } = await getresults(this.listQuery)
      if (success) {
        this.list = data.list
        this.loading = false
      }
    },
    handleSizeChange(val) {
      if (!isNaN(val)) {
        this.listQuery.limit = val
      }
      this.loadPageList()
    },
    handleCurrentChange(val) {
      if (!isNaN(val)) {
        this.listQuery.page = val
      }
      this.loadPageList()
    },
    async handleCreate() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.dialogadd = true
      this.dialogsave = false
      this.obj = {
        userName: '',
        userPassword: '',
        fullName: '',
        department: '',
      }
      this.loadoptions()
    },
    async loadoptions() {
      let getAlldata = await getAllrole()
      let arr = []
      getAlldata = getAlldata.data
      for (let i = 0; i < getAlldata.length; i++) {
        let obja = {}
        obja.label = getAlldata[i].roleName
        obja.value = getAlldata[i].id
        arr.push(obja)
      }
      this.options = arr
      this.loadgetUserId()
      this.loadgetdep()
    },
    async loadgetUserId() {
      let aa = this.obj
      let getUserIddata = await getUserId(this.obj.id)
      getUserIddata = getUserIddata.data.roles
      for (let i = 0; i < getUserIddata.length; i++) {
        getUserIddata[i].label = getUserIddata[i].roleName
        getUserIddata[i].value = getUserIddata[i].id
      }
      debugger
      this.selected = getUserIddata

    },
    async loadgetdep() {
      let depData = await depgetAll()
      this.treeData = depData.data
    },
    async ondep1Change(val) {
      if (val) {
        val = val
      }
    },
    async addCreate(obj) {

      if (!this.validata.validausr(obj)) return
      obj.method = 'post'
      let arr = []
      let getAlldata = this.selected
      for (let i = 0; i < getAlldata.length; i++) {
        let obja = {}
        obja.id = getAlldata[i].value
        arr.push(obja)
      }
      obj.roles = arr
      let data = await addUser(obj)
      if (data.code === 10000) {
        this.$message({
          message: '添加成功',
          type: 'success'
        });
        this.loadPageList()
        this.dialogFormVisible = false
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
      }
    },
    async saveCreate(obj) {
      if (!this.validata.validausr(obj)) return
      obj.method = 'put'
      let arr = []
      let getAlldata = this.selected
      for (let i = 0; i < getAlldata.length; i++) {
        let obja = {}
        obja.id = getAlldata[i].value
        arr.push(obja)
      }
      obj.roles = arr
      let data = await saveUser(obj)
      if (data.code === 10000) {
        this.$message({
          message: '修改成功',
          type: 'success'
        });
        this.dialogFormVisible = false
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
      }
    },
    //
    // async subSaveCreate() {
    //   let obj = this.obj
    //   obj.method = 'put'
    //   obj.dpartmentId = this.$refs.tree.getCheckedNodes()
    //
    //
    //   let data = await saveUser(obj)
    //   this.dialogEditVisible = false
    //   this.$message({
    //     type: 'success',
    //     message: '修改成功!'
    //   });
    // }
    async handleShow(data, type) {
      if (type === 'role') {
        this.obj = data
        this.dialogStatus = 'role'
        this.dialogShowRole = true
        this.loadgetUserId()

      }
    },
    async handleEdit(data, type) {

     if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await delUser(data.id)
          this.list.splice(this.list.indexOf(data), 1)
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          // this.splice(data.id, 1);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      } else if (type === 'edit') {
        this.$router.push({
          name: 'achieveLibraryEdit',
          params: {
            objId: data.form.id,
            objData: data.form.detail
          }
        })
      }else {
        this.$router.push({
          name: 'achieveLibraryEdit'
        })
      }
    },
    onDate1Change(val) {
      this.obj.loanDate = val
    },
    onDate2Change(val) {
      this.obj.appointmentRepaymentDate = val
    },
    onDate3Change(val) {
      this.obj.interestPayTime = val
    },
  }
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>
</style>
