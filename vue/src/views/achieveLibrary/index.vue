<template>
<div class="tab-container">

  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span>关键字</span>
      <el-input v-model="input.objName" placeholder="" style="width:200px;"></el-input>
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
        <el-button @click="handleEdit" type="primary">发布成果</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 0  || userType =='101' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plsj" type="primary">批量上架</el-button>
        <a :href=pldcUrl target="_blank"><el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style=""  type="primary">批量导出</el-button></a>
      </div>

    </div>
  </div>

  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:5px;width:100%;font-size:12px;">
    <el-table-column type="selection" width="30">
    </el-table-column>
    <el-table-column align="center" label="编号"  width="150">
      <template slot-scope="scope">
                    <span>{{ scope.row.number }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="名称">
      <template slot-scope="scope">
                    <div @click="showDetail(scope.row,'edit')" class="clickText" >{{ scope.row.name }}</div>
                </template>
    </el-table-column>
    <el-table-column align="center" label="技术成果概述">
      <template slot-scope="scope">
                    <span style="width:200px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis;">
                        {{ scope.row.resDesc}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 0" align="center" label="创建时间">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.createTime | formatTime}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 0" align="center" label="修改时间 ">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.updateTime | formatTime}}</span>
                </template>
    </el-table-column>



    <el-table-column v-if="tfcheckStatus == 1" align="center" label="所属领域">
      <template slot-scope="scope">

                                      <span v-if='scope.row.domain.includes(1)'>智能装备</span>
                                      <span v-if='scope.row.domain.includes(2)'>电子信息</span>
                                      <span v-if='scope.row.domain.includes(3)'>新材料</span>
                                      <span v-if='scope.row.domain.includes(4)'>航空航天</span>
                                      <span v-if='scope.row.domain.includes(5)'>生物技术与新医药</span>
                                      <span v-if='scope.row.domain.includes(6)'>能源与环保</span>
                                      <span v-if='scope.row.domain.includes(99)'>{{scope.row.domainOther}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="是否可见" width="100">
      <template slot-scope="scope">
                        <span v-if="scope.row.status == 1">可见</span>
                        <span v-if="scope.row.status == 0">不可见</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="发布人" width="100">
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

    <el-table-column  align="center" label="状态" width="70px;">
      <template slot-scope="scope">
                            <span v-show="tfcheckStatus == -1">草稿</span>
                            <span v-show="tfcheckStatus == 0">审核</span>
                            <span v-show="tfcheckStatus == 1">已发布</span>
                            <span v-show="tfcheckStatus == 2">驳回</span>
                        </template>
    </el-table-column>
    <el-table-column v-show="userType =='0'" align="center" label="操作" width="120">
      <template slot-scope="scope">
        <div style="text-align:center" >
          <span v-show="userType =='0' && tfcheckStatus == 1 || userType =='101' && tfcheckStatus == 1" @click="handlexy(scope.row)" class="clickText" >
            信用
          </span>
            <span v-show="userType =='0' &&tfcheckStatus == 0 || userType =='101' && tfcheckStatus == 0" @click="handlesh(scope.row)" class="clickText" >
            审核
            </span>
          <span v-show="userType =='0' || userType =='101'" @click="handleEdit(scope.row,'edit')" class="clickText" >
            编辑
          </span>
      <span v-show="userType =='0' &&scope.row.status == 1 && tfcheckStatus == 1 || userType =='101'  &&scope.row.status == 1 && tfcheckStatus == 1">  <span @click="handlexj(scope.row)" class="clickText" >
            下架
                </span></span>
      <span v-show="userType =='0' &&scope.row.status == 0 && tfcheckStatus == 1 || userType =='101'  &&scope.row.status == 0 && tfcheckStatus == 1">  <span @click="handlesj(scope.row)" class="clickText" >
            上架
      </span></span>
      <span v-show="userType !='0' && userType !='101'" @click="showDetail(scope.row,'edit')" class="clickText" >
        查看
      </span>
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
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handlePrint">打印</el-button>

      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
      <table class="tableExcelText" id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>
        <col width=72 span=8 style='width:54pt'>
        <tr height=23 style='mso-height-source:userset;height:17.4pt' id='r0'>
          <td colspan=8 id='tc0' height=23 class=x21 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:17.4pt;'>军民科技协同创新技术成果信息登记表</td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r2'>
          <td height=38 class=x23 style='height:28.8pt;'>技术成果名称</td>
          <td colspan=7 id='tc2' class=x24 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>{{detailData.re_name}}</td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r3'>
          <td height=38 class=x25 style='height:28.8pt;'>企业名称</td>
          <td colspan=2 id='tc3' class=x54 style='border-bottom:1px solid windowtext;'>{{detailData.name}}</td>
          <td class=x25>通讯地址</td>
          <td class=x28>{{detailData.address}}</td>
          <td class=x25>所在地区</td>
          <td colspan=2 id='tc4' class=x54 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>
            <area-cascader :level="1" v-model="detailData.country" :data="pcaa"></area-cascader>
          </td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r4'>
          <td height=38 class=x25 style='height:28.8pt;'>项目负责人</td>
          <td colspan=3 id='tc5' class=x54 style='border-bottom:1px solid windowtext;'>{{detailData.fzname}}</td>
          <td class=x25>电话</td>
          <td class=x30>{{detailData.fzphone}}</td>
          <td class=x31>邮箱</td>
          <td class=x32>{{detailData.fzemail}}</td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r5'>
          <td height=38 class=x25 style='height:28.8pt;'>项目联系人</td>
          <td colspan=3 id='tc6' class=x54 style='border-bottom:1px solid windowtext;'>{{detailData.fzname2}}</td>
          <td class=x33>电话</td>
          <td class=x30>{{detailData.fzphone2}}</td>
          <td class=x34>邮箱</td>
          <td class=x35>{{detailData.fzemail2}}</td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r6'>
          <td height=38 class=x36 style='height:28.8pt;'>所属领域</td>
          <td colspan=7 id='tc7' class=x37 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>
            <el-checkbox-group v-model="detailData.domain">
              <el-checkbox label="1">智能装备</el-checkbox>
              <el-checkbox label="2">电子信息</el-checkbox>
              <el-checkbox label="3">新材料</el-checkbox>
              <el-checkbox label="4">航空航天</el-checkbox>
              <el-checkbox label="5">生物技术与新医药</el-checkbox>
              <el-checkbox label="6">能源与环保</el-checkbox>
              <el-checkbox label="7">其他</el-checkbox>
              <!-- <el-input v-show="achieveLibrary.domain.includes('7')" placeholder="请输入其他" v-model="achieveLibrary.domainOther" style="width:80%"></el-input> -->
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r7'>
          <td height=38 class=x23 style='height:28.8pt;'>技术成果简介</td>
          <td colspan=7 id='tc8' class=x55 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>{{detailData.resDesc}}</td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r8'>
          <td height=38 class=x41 style='height:28.8pt;'>技术创新点</td>
          <td colspan=7 id='tc9' class=x42 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>{{detailData.newIdea}}</td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.4pt' id='r9'>
          <td rowspan=4 height=76 class=x44 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:57.6pt;'>知识产权情况</td>
          <td colspan=7 id='tc10' class=x43 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>
            <font class="font3">已申请专利</font>
            <font class="font11"> </font>
            <font class="font12"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font11">{{detailData.sqPatent}}</font>
            <font class="font3">件</font>
            <font class="font11"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><br></font>
            <font class="font3">□获授权专利</font>
            <font class="font11">{{detailData.havePatent}}</font>
            <font class="font12"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font3">件</font>
            <font class="font11"><span style='mso-spacerun:yes'>&nbsp; </span><br></font>
            <font class="font3">其他知识产权</font>
            <font class="font11">{{detailData.otherPatentotherPatent}}</font>
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
        <tr v-for="item in detailData.patents" height=19 style='mso-height-source:userset;height:14.4pt' id='r11'>
          <td class=x46>{{item.projectname}}</td>
          <td class=x47>{{item.projecType}}</td>
          <td class=x48>{{item.projectNum}}</td>
          <td class=x45>{{item.projectPer}}</td>
          <td class=x45>{{item.projectTime}}</td>
        </tr>

        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r13'>
          <td height=38 class=x50 style='height:28.8pt;'>所处阶段</td>
          <td colspan=7 id='tc14' class=x56 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>
            <el-checkbox-group v-model="detailData.stage">
              <el-checkbox label="1">实验室阶段</el-checkbox>
              <el-checkbox label="2">试制阶段</el-checkbox>
              <el-checkbox label="3">产业化阶段</el-checkbox>
            </el-checkbox-group>
      </td>
        </tr>
        <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r14'>
          <td height=38 class=x41 style='height:28.8pt;'>合作方式</td>
          <td colspan=7 id='tc15' class=x56 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'>
            <el-checkbox-group v-model="detailData.cooperation">
              <el-checkbox label="1">技术转让</el-checkbox>
              <el-checkbox label="2">技术许可</el-checkbox>
              <el-checkbox label="3">技术开发</el-checkbox>
              <el-checkbox label="4">技术服务</el-checkbox>
              <el-checkbox label="5">技术入股</el-checkbox>
            </el-checkbox-group>
          </td>
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
import 'vue-area-linkage/dist/index.css'; // v2 or higher
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
  PLonUserDetails,
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
      pldcUrl : "",
    }
  },
  created(){
    this.pldcUrl = this.docUrl + '/xtcx/lib/exportLib?objName='+this.input.objName+'&checkStatus=1&userType=6&creditLevel=&status='+this.input.status+'&token='+window.sessionStorage.getItem('token')
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
        if(rej.info==""){
          this.$message({
            message: '请输入驳回原因！',
            type: 'success'
          });
          return
        }
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
    async plsj() {
      if (this.multipleSelection.length == 0) {
        this.$message({
          type: 'success',
          message: '请勾选上架内容!'
        });
      }
      let {
        data,
        success
      } = await PLonUserDetails(this.multipleSelection, '6')
      if (success) {
        this.$message({
          type: 'success',
          message: '上架成功!'
        });
        this.loadPageList()
      }
    },
    async plxj() {
    if(this.multipleSelection.length == 0){
      this.$message({
        type: 'success',
        message: '请勾选下架内容!'
      });
    }
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
    let objData
    if (this.input.checkStatus == 1) {
      objData = data.form.detail
    } else if (this.input.checkStatus == 0 || this.input.checkStatus == -1) {
      objData = data.detail
    }
      this.detailData = JSON.parse(objData)
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
        this.total = data.total
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
        let objId, objData
        if (this.input.checkStatus == 1) {
          objId = data.form.id
          objData = data.form.detail
        } else if (this.input.checkStatus == 0 || this.input.checkStatus == -1) {
          objId = data.id
          objData = data.detail
        }
        this.$router.push({
          name: 'achieveLibraryEdit',
          params: {
            objId:objId,
            objData: objData
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
.el-checkbox__input.is-indeterminate .el-checkbox__inner,
.tableExcelText .el-checkbox__input.is-checked .el-checkbox__inner {
    background-color: #94989c;
    border-color: #94989c;
}
.tableExcelText .el-checkbox__input.is-checked+.el-checkbox__label {
    color: #000;
}
</style>
