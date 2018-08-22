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
        <el-button v-if="userType =='0' " style="" @click="handleEdit" type="primary">发布需求</el-button>
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
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="需求单位">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.name}}</span>
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


  <el-dialog title="需求详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
    <div class="textr paddinga">
      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handlePrint">打印</el-button>

      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
        <table id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>
       <col width=72 span=7 style='width:54pt'>
       <tr height=27 style='mso-height-source:userset;height:20.4pt' id='r0'>
      <td colspan=7 id='tc0' height=27 class=x21 style='height:20.4pt;' >军民科技协同创新技术需求信息表</td>
       </tr>
       <tr height=23 style='mso-height-source:userset;height:17.4pt' id='r1'>
      <td height=23 class=x22 style='height:17.4pt;' >填报单位名称（盖章）：</td>
      <td class=x22></td>
      <td class=x22></td>
      <td class=x22></td>
      <td class=x22></td>
      <td class=x22></td>
      <td class=x22></td>
       </tr>
       <tr height=41 style='mso-height-source:userset;height:31.2pt' id='r2'>
      <td height=41 class=x23 style='height:31.2pt;' >技术需求名称</td>
      <td colspan=6 id='tc1' class=x24 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;' ></td>
       </tr>
       <tr height=104 style='mso-height-source:userset;height:78.6pt' id='r3'>
      <td height=104 class=x25 style='height:78.6pt;' >需求单位名称</td>
      <td colspan=2 id='tc2' class=x30 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;' ></td>
      <td class=x25>通信地址</td>
      <td class=x28></td>
      <td class=x29>所在地区</td>
      <td class=x30 style='overflow:hidden;' ><font class="font8">省</font><font class="font7"><span style='mso-spacerun:yes'>&nbsp; </span></font><font class="font8">市</font><font class="font7"> </font><font class="font8">区（县）下拉选择</font></td>
       </tr>
       <tr height=20 style='mso-height-source:userset;height:15.6pt' id='r4'>
      <td height=20 class=x25 style='height:15.6pt;' >负责人</td>
      <td class=x25></td>
      <td class=x25>职务</td>
      <td class=x31></td>
      <td class=x25>电话</td>
      <td class=x31></td>
      <td class=x25>邮箱</td>
       </tr>
       <tr height=20 style='mso-height-source:userset;height:15.6pt' id='r5'>
      <td height=20 class=x25 style='height:15.6pt;' >联系人</td>
      <td class=x32></td>
      <td class=x32>职务</td>
      <td class=x33></td>
      <td class=x32>电话</td>
      <td class=x33></td>
      <td class=x32>邮箱</td>
       </tr>
       <tr height=41 style='mso-height-source:userset;height:31.2pt' id='r6'>
      <td height=41 class=x34 style='height:31.2pt;' >所属领域</td>
      <td colspan=6 id='tc3' class=x49 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;' >□智能装备<span style='mso-spacerun:yes'>&nbsp; </span>□电子信息<span style='mso-spacerun:yes'>&nbsp; </span>□新材料<span style='mso-spacerun:yes'>&nbsp; </span>□航空航天<span style='mso-spacerun:yes'>&nbsp; </span>□生物技术与新医药<span style='mso-spacerun:yes'>&nbsp; </span>□能源与环保<span style='mso-spacerun:yes'>&nbsp; </span>□其他</td>
       </tr>
       <tr height=83 style='mso-height-source:userset;height:62.4pt' id='r7'>
      <td height=83 class=x38 style='height:62.4pt;' >希望解决的技术需求概述</td>
      <td colspan=6 id='tc4' class=x50 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;' ></td>
       </tr>
       <tr height=76 style='mso-height-source:userset;height:57.6pt' id='r8'>
      <td height=76 class=x42 style='height:57.6pt;' >要求达到的技术性能、参数指标等</td>
      <td colspan=6 id='tc5' class=x51 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;' ></td>
       </tr>
       <tr height=38 style='mso-height-source:userset;height:28.8pt' id='r9'>
      <td height=38 class=x42 style='height:28.8pt;' >其他相关要求</td>
      <td colspan=6 id='tc6' class=x51 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;' ></td>
       </tr>
       <tr height=41 style='mso-height-source:userset;height:31.2pt' id='r10'>
      <td height=41 class=x38 style='height:31.2pt;' >需求背景</td>
      <td class=x46>□新产品开发</td>
      <td class=x47>□制造工艺改进</td>
      <td class=x47>□产品升级换代</td>
      <td class=x47>□制造设备改进</td>
      <td class=x47>□生产线技术改造</td>
      <td class=x48>□其他</td>
       </tr>
       <tr height=41 style='mso-height-source:userset;height:31.2pt' id='r11'>
      <td height=41 class=x38 style='height:31.2pt;' >意向获得方式</td>
      <td colspan=6 id='tc7' class=x49 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;' >□技术转让<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□技术许可<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□技术开发<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>□技术服务<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>□技术入股</td>
       </tr>
       <tr height=0 style='display:none'>
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
  getUser,
} from '@/api/user'

import {
  getAllrole
} from '@/api/role'
import {
  getrequirement,
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
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id',
        objName: ''
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
      } = await updateLevel(objdata,'3')
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
          } = await PLoffUserDetails(this.multipleSelection,'7')
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
      let obj = {}
      obj.id = params.id
      obj.type = '7'
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
      let obj = {}
      obj.id = params.id
      obj.type = '7'
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
      } =  await getrequirement(this.listQuery)
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
          name: 'demandLibraryEdit',
          params: {
            objId: data.form.id,
            objData: data.form.detail
          }
        })
      }else {
        this.$router.push({
          name: 'demandLibraryEdit'
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
