<template>
<div class="tab-container">
  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span>关键字</span>
      <el-input v-model="input.queryStr" placeholder="可根据名称/单位/领域/级别/链接查询" style="width:20%;"></el-input>

      <el-button style="margin-left:20px" @click="loadPageList" type="primary">查询</el-button>
      <el-button style="" @click="handleEdit('','add')" type="primary">添加</el-button>
      <div class="" style="padding:10px 0px;">
        <!-- <el-button v-show="userType =='0' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1" style="" @click="plsj" type="primary">批量上架</el-button> -->
      </div>

    </div>
  </div>

  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;">
    <el-table-column type="selection" width="30px;">
    </el-table-column>
    <el-table-column align="center" label="名称">
      <template slot-scope="scope">
                    <div @click="handleEdit(scope.row,'show')" class="clickText" >{{ scope.row.spName }}</div>
                </template>
    </el-table-column>
    <el-table-column align="center" label="承担单位">
      <template slot-scope="scope">
                    <span >
                        {{ scope.row.spCompany}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="领域" width="90px;">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.spDomain}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="级别" width="80px;">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.spLevel}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="链接">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.spLink}}</span>
                </template>
    </el-table-column>





    <el-table-column align="center" label="操作" width="150px;">
      <template slot-scope="scope">
                    <div style="">
                        <div v-show="scope.row.checkStatus == 0" @click="handlesh(scope.row)" class="clickText" style="float:left;padding-left:5px">
                          审核
                        </div>
                      <div  v-show="scope.row.checkStatus != 0 && scope.row.checkStatus != 1" @click="handleEdit(scope.row,'edit')" class="clickText" style="float:left;padding-left:5px">
                        编辑
                      </div>

                  <span v-show="scope.row.checkStatus == 1 && scope.row.stickSort > 0">  <div @click="handlexy(scope.row,1)" class="clickText" style="float:left;padding-left:5px">
                                            置顶
                            </div></span>
                    <span v-show="scope.row.checkStatus == 1 && scope.row.stickSort == 0">  <div @click="handlexy(scope.row,0)" class="clickText" style="float:left;padding-left:5px">
                                                      取消置顶
                    </div></span>
                  <span v-show="scope.row.readable == 1 && scope.row.checkStatus == 1">  <div @click="handlexj(scope.row)" class="clickText" style="float:left;padding-left:5px">
                                            下架
                            </div></span>
                  <span v-show="scope.row.readable == 0 && scope.row.checkStatus == 1">  <div @click="handlesj(scope.row)" class="clickText" style="float:left;padding-left:5px">
                                            上架
                  </div></span>
                  <span >  <div @click="delObj(scope.row)" class="clickText" style="float:left;padding-left:5px;color:red">
                                            删除
                  </div></span>

                    </div>
                </template>
    </el-table-column>

  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>



  <el-dialog :title="title" :visible.sync="dialogFormVisible" width="60%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="24">
        <el-col :span="24">
          <table class="showDetailTable" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;text-align: center;">
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">名称</td>
              <td>
                {{majorPlatform.spName}}
              </td>
              <td style="width:100px;padding:10px">承担单位</td>
              <td>
                {{majorPlatform.spCompany}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">领域</td>
              <td>
                {{majorPlatform.spDomain}}
              </td>
              <td style="width:100px;padding:10px">级别</td>
              <td>
                {{majorPlatform.spLevel}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">链接</td>
              <td>
                {{majorPlatform.spLink}}
              </td>
            </tr>


          </table>

          <div v-show="activeTabPosition == '报名信息'">
            <span>报名人姓名</span>
            <el-input v-model="inputE.objName" placeholder="" style="width:100px;"></el-input>
            <span style="margin-left: 15px;">活动日期</span>
            <el-date-picker v-model="inputE.enrollStart" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" style="width:140px">
            </el-date-picker>
            <el-date-picker v-model="inputE.enrollEnd" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" style="width:140px">
            </el-date-picker>
            <el-button style="margin-left:20px" @click="loadEnrolls" type="primary">查询</el-button>
            <el-table class="tableH" :data="listE" border style="margin-top:20px;width:100%;font-size:12px;">

              <el-table-column align="center" label="姓名">
                <template slot-scope="scope">
                    <span>
                        {{ scope.row.name}}</span>
                </template>
              </el-table-column>

              <el-table-column align="center" label="工作单位">
                <template slot-scope="scope">
                    <span>
                        {{ scope.row.workplace}}</span>
                </template>
              </el-table-column>

              <el-table-column align="center" label="职务">
                <template slot-scope="scope">
                    <span>
                        {{ scope.row.job}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" label="联系电话">
                <template slot-scope="scope">
                    <span>
                        {{ scope.row.phone}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" label="联系邮箱">
                <template slot-scope="scope">
                    <span>
                        {{ scope.row.email}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" label="联系地址">
                <template slot-scope="scope">
                    <span>
                        {{ scope.row.addr}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" label="备注">
                <template slot-scope="scope">
                    <span>
                        {{ scope.row.publishUserId}}</span>
                </template>
              </el-table-column>



              <el-table-column align="center" label="报名时间">
                <template slot-scope="scope">
                    <span>
                        {{ scope.row.enrollDate | formatTime}}</span>
                </template>
              </el-table-column>

            </el-table>
          </div>


          <div v-show="activeTabPosition == '报名信息'" class="pagination-container pageH" style="padding-top:20px">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery2.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
          </div>



        </el-col>

      </el-row>
    </el-form>
    <div v-show="!show" style="padding-left: 35%;margin: 40px 0  0  0;height: 100px;">
      <div class="" style="padding:15px" v-show="!$route.params.objData">
        <el-radio-group v-model="checkStatus">
          <el-radio :label="-1">草稿</el-radio>
          <el-radio :label="0">提交待审</el-radio>
          <el-radio :label="1">直接审核</el-radio>
        </el-radio-group>
      </div>
      <div class="">
        <el-button type="primary" style="width: 120px;" @click="saveObj(checkStatus)">保存</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </div>
    <div v-show="show" style="height: 100px;">

    </div>
  </el-dialog>



  <el-dialog title="置顶" :visible.sync="dialogShowLevel" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="12">
        <el-col :span="20">

          <el-form-item label="置顶排序">
            <el-input v-model="xyset.order" placeholder=""></el-input>
          </el-form-item>

        </el-col>

      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
        <el-button type="primary" style="" @click="saveFile(xyset)">保存</el-button>
        <el-button type="primary" @click="dialogShowLevel = false">关闭</el-button>
      </span>
  </el-dialog>


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


          <el-form-item v-show="rej.way == '2'" label="驳回原因">
            <el-input v-model="rej.info" placeholder=""></el-input>
          </el-form-item>
        </el-col>

      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer" style="">
        <el-button type="primary" style="" @click="saveReject(rej)">保存</el-button>
        <el-button type="primary" @click="dialogShowSH = false">关闭</el-button>
      </span>
  </el-dialog>

</div>
</template>

<script>
import {
  getShareplatform,
  addShareplatform,
  updShareplatform,
  getShareplatformC,
  delShareplatformC,
} from '@/api/majorPlatform'

import table2excel from 'table2excel'
import printArea from 'printArea'
export default {
  components: {},
  data() {
    return {
      defaultMsg: '<span style="orphans: 2; widows: 2; font-size: 22px; font-family: KaiTi_GB2312; background-color: rgb(229, 51, 51);"><strong>测试内容</strong></span>',
      config: {
        initialFrameWidth: null,
        initialFrameHeight: 350
      },
      activeTabPosition: '活动信息',
      ue1: "ue1", // 不同编辑器必须不同的id
      rej: {
        way: '1',
        info: '',
        id: ''
      },
      xyset: {
        order: '',
        id: ''
      },
      title: "",
      input: {
        queryStr: '',
      },
      inputE: {
        objName: '',
        enrollStart: '',
        enrollEnd: '',
      },
      listE: [],
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      dialogFormVisible: false,
      dialogShowRole: false,
      dialogShowDep: false,
      dialogShowSH: false,
      dialogShowLevel: false,
      dialogadd: false,
      dialogsave: false,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
      },
      listQuery2: {
        page: 1,
        limit: 10,
      },
      treeData: [],
      loading: true,
      userType: '',
      multipleSelection: [],
      detailData: '',
      tfcheckStatus: '',
      checkStatus: -1,
      majorPlatform: {
        spName: '',
        spCompany: '',
        spDomain: '',
        spLevel: '',
        spLink: '',
      }
    }
  },
  async mounted() {

    this.loadPageList()
    this.userType = window.sessionStorage.getItem('userType')

  },
  computed: {},
  methods: {
    async loadPageList() {
      if (this.input) {
        this.input.pageNum = this.listQuery.page
        this.input.pageSize = this.listQuery.limit
      }
      let {
        data,
        success
      } = await getShareplatform(this.input)
      if (success) {
        this.loading = false
        this.list = data.list
        this.total = data.total
      }
    },
    async saveFile(objdata) {
      if (objdata.order == '') {
        this.$message({
          message: '请输入置顶排序！',
          type: 'success'
        });
        return
      }
      let {
        data,
        success
      } = await topExchanges(objdata)
      this.$message({
        message: '设置成功',
        type: 'success'
      });
      this.loadPageList()
    },

    async saveReject(rej) {
      if (rej.way == '1') {
        let arr = []
        arr.push(rej.id)
        let {
          data,
          success
        } = await checkExchanges(arr)
        if (success) {
          this.$message({
            message: '审核成功',
            type: 'success'
          });
          this.loadPageList()
        }
      } else {
        if (rej.info == "") {
          this.$message({
            message: '请输入驳回原因！',
            type: 'success'
          });
          return
        }
        let {
          data,
          success
        } = await rejectExchanges(rej)
        if (success) {
          this.$message({
            message: '提交成功',
            type: 'success'
          });
          this.loadPageList()
        }
      }
      this.dialogShowSH = false

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
      } = await checkExchanges(this.multipleSelection)
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
      } = await plonExchanges(this.multipleSelection, '1')
      if (success) {
        this.$message({
          type: 'success',
          message: '上架成功!'
        });
        this.loadPageList()
      }
    },
    async plxj() {
      if (this.multipleSelection.length == 0) {
        this.$message({
          type: 'success',
          message: '请勾选下架内容!'
        });
      }
      let {
        data,
        success
      } = await offExchanges(this.multipleSelection)
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
    async handlexy(item, num) {
      if (num == 0) {
        this.xyset.id = item.id
        this.xyset.order = '9999'
        let {
          data,
          success
        } = await topExchanges(this.xyset)
        this.$message({
          message: '取消置顶',
          type: 'success'
        });
        this.loadPageList()
      } else {
        this.xyset.id = item.id
        this.xyset.order = '0'
        let {
          data,
          success
        } = await topExchanges(this.xyset)
        this.$message({
          message: '置顶成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
    handlesh(data) {
      this.dialogShowSH = true
      this.rej.id = data.id
    },
    async handlexj(params) {
      let obj = []
      obj.push(params.id)
      let {
        data,
        success
      } = await offExchanges(obj)
      if (success) {
        this.$message({
          message: '下架成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
    async handlesj(params) {
      let arr = []
      arr.push(params.id)
      let {
        data,
        success
      } = await plonExchanges(arr)
      if (success) {
        this.$message({
          message: '上架成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
    showDetail() {
      this.dialogShowDep = true
    },
    handlePrint() {
      $("#tablePrint").printArea();
    },
    handleDownload() { //导出
      $("#tableExcel").table2excel({
        exclude: ".noExl", //过滤位置的 css 类名
        filename: new Date().getTime() + ".xls", //文件名称
        name: "Excel Document Name.xlsx",
        exclude_img: true,
        exclude_links: true,
        exclude_inputs: true
      })
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


    async handleEdit(item, type) {
      if (type === 'edit') {
        this.title = '编辑活动'
        this.$router.push({
          name: 'majorPlatformEdit',
          params: {
            itemId: item.id,
            checkStatus: this.tfcheckStatus
          }
        })
      } else if (type === 'add') {
        this.title = '添加活动'
        this.$router.push({
          name: 'majorPlatformEdit',
          // params: {
          //   objData: item
          // }
        })
      } else if (type === 'show') {
        this.dialogFormVisible = true
        let {
          data,
          success
        } = await getShareplatformC(item.id)
        this.majorPlatform = data
        this.show = true
        this.title = '查看详情'
        this.loadEnrolls()

      }
    },
    async loadEnrolls() {
      if (this.inputE) {
        this.inputE.page = this.listQuery2.page
        this.inputE.limit = this.listQuery2.limit
      }
      let {
        data,
        success
      } = await getEnrolls(this.inputE)
      if (success) {
        this.loading = false
        this.listE = data.list
        this.total = data.total
      }
    },
    delObj(item) {
      this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        let del = await delShareplatformC(item.id)
        this.list.splice(this.list.indexOf(item), 1)
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
    },

    async saveObj(checkStatus) {
      // if (!this.validata.validausr(obj)) return
      let arr = {}
      arr = this.active
      arr.checkStatus = checkStatus
      debugger
      let {
        data,
        success
      } = await postExchanges(arr)

      if (success) {
        this.$message({
          message: '保存成功',
          type: 'success'
        });
        this.dialogFormVisible = false
        this.loadPageList()
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
      }
    },

  }
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>
