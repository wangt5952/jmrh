<template>
<div class="tab-container">
  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span>关键字</span>
      <el-input v-model="input.objName" placeholder="" style="width:200px;"></el-input>
      <span style="margin-left: 15px;">发布日期</span>
      <el-date-picker v-model="input.publishStart" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" style="width:140px">
      </el-date-picker>
      <el-date-picker v-model="input.publishEnd" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" style="width:140px">
      </el-date-picker>
      <span style="margin-left: 15px;">状态</span>
      <el-select v-model="input.checkStatus" style="width:100px;height:30px" placeholder="请选择">
        <el-option label="全部" key='' value=''>
        </el-option>
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
        <el-button style="" @click="handleEdit('','add')" type="primary">添加活动</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 0  || userType =='101' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plsj" type="primary">批量上架</el-button>
      </div>

    </div>
  </div>

  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;">
    <el-table-column type="selection" width="30px;">
    </el-table-column>
    <el-table-column align="center" label="活动名">
      <template slot-scope="scope">
                    <div @click="handleEdit(scope.row,'show')" class="clickText" >{{ scope.row.title }}</div>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1  || tfcheckStatus == ''" align="center" label="报名数" width="60px;">
      <template slot-scope="scope">
                    <span >
                        {{ scope.row.enrollNumber}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="发布人">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.publishUserId}}</span>
                </template>
    </el-table-column>



    <el-table-column v-if="tfcheckStatus == 1  || tfcheckStatus == ''" align="center" label="发布日期">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.publishDate | formatTime}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1  || tfcheckStatus == ''" align="center" label="创建日期">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.createTime  | formatTime}}</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1  || tfcheckStatus == ''" align="center" label="置顶" width="50px;">
      <template slot-scope="scope">
                                <span v-show="scope.row.stickSort == 0">是</span>
                                <span v-show="scope.row.stickSort == 9999">否</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1  || tfcheckStatus == ''" align="center" label="是否可见" width="70px;">
      <template slot-scope="scope">
                        <span v-show="scope.row.readable == 1">可见</span>
                        <span v-show="scope.row.readable == 0">不可见</span>
                    </template>
    </el-table-column>


    <el-table-column v-if="tfcheckStatus == 1|| tfcheckStatus == ''" align="center" label="状态" width="70px;">
      <template slot-scope="scope">
                        <span v-show="scope.row.checkStatus == -1">草稿</span>
                        <span v-show="scope.row.checkStatus == 0">待审核</span>
                        <span v-show="scope.row.checkStatus == 1">已发布</span>
                        <span v-show="scope.row.checkStatus == 2">驳回</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1  || tfcheckStatus == ''" align="center" label="审核用户">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.checkUserId}}</span>
                    </template>
    </el-table-column>
    <el-table-column align="center" label="状态"  width="70px;">
      <template slot-scope="scope">
                        <span v-show="scope.row.checkStatus == -1">草稿</span>
                        <span v-show="scope.row.checkStatus == 0">待审核</span>
                        <span v-show="scope.row.checkStatus == 1">已发布</span>
                        <span v-show="scope.row.checkStatus == 2">驳回</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 2" align="center" label="驳回原因">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.info}}</span>
                    </template>
    </el-table-column>
    <el-table-column align="center" label="操作" width="150px;">
      <template slot-scope="scope">

          <div style="text-align:center" >
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
          <el-radio-group v-model="activeTabPosition" style="margin-bottom: 30px;">
            <el-radio-button label="活动信息">活动信息</el-radio-button>
            <el-radio-button label="报名信息">报名信息</el-radio-button>
          </el-radio-group>

          <table class="showDetailTable" v-show="activeTabPosition == '活动信息'" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;text-align: center;">
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">标题</td>
              <td>
                {{active.title}}
              </td>
              <td style="width:100px;padding:10px">主题</td>
              <td>
                {{active.subject}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">封面</td>
              <td>
                <img :src="active.cover" alt="" style="width:100px">
              </td>
              <td style="width:100px;padding:10px">活动类型</td>
              <td>
                <span v-show="active.typeId == 0">展会</span>
                <span v-show="active.typeId == 1">讲座</span>
                <span v-show="active.typeId == 2">座谈</span>
                <span v-show="active.typeId == 3">洽谈</span>
                <span v-show="active.typeId == 4">高峰论坛</span>
                <span v-show="active.typeId == 5">其他</span>
              </td>
            </tr>
            <tr>
              <td style="width:100px;padding:10px">活动地址</td>
              <td colspan=3>
                {{active.exAddr}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">报名时间起</td>
              <td>
                {{active.enrollStart | formatTime}}
              </td>
              <td style="width:100px;padding:10px">报名时间止</td>
              <td>
                {{active.enrollEnd | formatTime}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">活动时间起</td>
              <td>
                {{active.exStart | formatTime}}
              </td>
              <td style="width:100px;padding:10px">活动时间止</td>
              <td>
                {{active.exEnd | formatTime}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">主办方</td>
              <td>
                {{active.organizerName}}
              </td>
              <td style="width:120px;padding:10px">主办方联系人</td>
              <td>
                {{active.orgContacts}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:120px;padding:10px">主办方联系电话</td>
              <td>
                {{active.orgPhone}}
              </td>
              <td style="width:120px;padding:10px">主办方联系邮件</td>
              <td>
                {{active.orgEmail}}
              </td>
            </tr>

            <tr>
              <td style="width:120px;padding:10px">主办方联系地址</td>
              <td colspan=3>
                {{active.orgAddr}}
              </td>
            </tr>

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">定时发布</td>
              <td>
                <span v-show="active.publishNow == 1">是</span>
                <span v-show="active.publishNow == 0">否</span>
              </td>
              <td v-show="active.publishNow == 1" style="width:100px;padding:10px">发布日期</td>
              <td v-show="active.publishNow == 1">
                {{active.publishDate | formatTime}}
              </td>
            </tr>

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">置顶</td>
              <td>
                <span v-show="active.stickSort == 0">是</span>
                <span v-show="active.stickSort == 9999">否</span>
              </td>

            </tr>

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">活动内容</td>
              <td colspan=3>
                <div class="editor-content" v-html="active.exContent" style="width:100%"></div>

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
  getExchanges,
  postExchanges,
  submitExchanges,
  delexchanges,
  rejectExchanges,
  checkExchanges,
  plonExchanges,
  offExchanges,
  onExchanges,
  topExchanges,
  getExchangesC,
  getEnrolls
} from '@/api/columnManage'
import {
  addLib,
  delLib,
  rejectUserDetail,
  offUserDetail,
  onUserDetail,
  PLrejectUserDetail,
  PLoffUserDetails,
} from '@/api/library'
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
        objName: '',
        publishStart: '',
        publishEnd: '',
        checkStatus: 1,
      },
      inputE: {
        objName: '',
        enrollStart: '',
        enrollEnd: '',
      },
      listE:[],
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
      active: {
        title: '',
        subject: '',
        typeId: 1,
        cover: '',
        exAddr: '',
        enrollStart: '',
        enrollEnd: '',
        exStart: '',
        exEnd: '',
        organizerName: '',
        orgContacts: '',
        orgPhone: '',
        orgEmail: '',
        orgAddr: '',
        publishNow: 1,
        publishDate: '',
        publishUserId: '',
        checkDate: '',
        checkUserId: '',
        stickSort: 1,
        exContent: '',
      }
    }
  },
  async mounted() {
    if (typeof this.$route.query.checkStatus == 'number') {
      this.input.checkStatus = this.$route.query.checkStatus
    }
    this.loadPageList()
    this.userType = window.sessionStorage.getItem('userType')

  },
  computed: {},
  methods: {
    async loadPageList() {
      this.tfcheckStatus = this.input.checkStatus
      if (this.input) {
        this.input.page = this.listQuery.page
        this.input.limit = this.listQuery.limit
      }
      let {
        data,
        success
      } = await getExchanges(this.input)
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
          name: 'activeEdit',
          params: {
            itemId: item.id,
            checkStatus: this.tfcheckStatus
          }
        })
      } else if (type === 'add') {
        this.title = '添加活动'
        this.$router.push({
          name: 'activeEdit',
          // params: {
          //   objData: item
          // }
        })
      } else if (type === 'show') {
        this.dialogFormVisible = true
        let {
          data,
          success
        } = await getExchangesC(item.id)
        this.active = data
       this.active.cover =  this.imgBaseUrl  + data.cover
        this.show = true
        this.title = '查看活动详情'
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
        let del = await delexchanges(item)
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
