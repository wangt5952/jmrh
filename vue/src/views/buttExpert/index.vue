<template>
<div class="tab-container">
  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span style="margin-left: 15px;">类别</span>
      <el-select v-model="input.typeId" style="height:30px;width:110px;" placeholder="请选择">
        <el-option label="请选择" key="" value="">
        </el-option>
        <el-option label="专家对接" :key=0 :value=0>
        </el-option>
        <el-option label="需求对接" :key=1 :value=1>
        </el-option>
        <el-option label="成果对接" :key=2 :value=2>
        </el-option>
        <el-option label="服务对接" :key=3 :value=3>
        </el-option>
      </el-select>
      <span style="margin-left: 15px;">发起人</span>
      <el-select v-model="input.isFormOrToMe" style="width:120px;height:30px" placeholder="请选择">
        <el-option label="请选择" key="" value="">
        </el-option>
        <el-option label="我发起的" :key=1 :value=1>
        </el-option>
        <el-option label="向我发起的" :key=2 :value=2>
        </el-option>
      </el-select>
      <span style="margin-left: 15px;">状态</span>
      <el-select v-model="input.checkStatus" style="width:100px;height:30px" placeholder="请选择">
        <el-option label="请选择" key="" value="">
        </el-option>
        <el-option label="待通知" :key=-1 :value=-1>
        </el-option>
        <el-option label="已通知" :key=0 :value=0>
        </el-option>
        <el-option label="已接受" :key=1 :value=1>
        </el-option>
        <el-option label="已拒绝" :key=2 :value=2>
        </el-option>
        <el-option label="已超时" :key=3 :value=3>
        </el-option>
      </el-select>
      <el-button style="margin-left:20px" @click="loadMeeting" type="primary">查询</el-button>
      <div class="" style="padding:10px 0px;">
        <el-button v-show="userType =='0' && tfcheckStatus == 0  || userType =='101' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plsj" type="primary">批量上架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="pldc" type="primary">批量导出</el-button>
      </div>

    </div>
  </div>


  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:5px;width:100%;font-size:12px;">
    <el-table-column type="selection" width="30">
    </el-table-column>
    <el-table-column align="center" label="编码">
      <template slot-scope="scope">
                      <div @click="handleEdit(scope.row,'show')" class="clickText" >{{ scope.row.code }}</div>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="对接内容">
      <template slot-scope="scope">
                      <div class="" >{{ scope.row.applicationContentName }}</div>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="对接类别" width="90">
      <template slot-scope="scope">
                              <span v-if="scope.row.typeId == 0">专家对接</span>
                              <span v-if="scope.row.typeId == 1">需求对接</span>
                              <span v-if="scope.row.typeId == 2">成果对接</span>
                              <span v-if="scope.row.typeId == 3">服务对接</span>
                          </template>
    </el-table-column>
    <el-table-column align="center" label="发起人">
      <template slot-scope="scope">
                      <div class="" >{{ scope.row.fromerName }}</div>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="接收人">
      <template slot-scope="scope">
                      <span>
                          {{ scope.row.targeterName}}</span>
                  </template>
    </el-table-column>

    <el-table-column align="center" label="发起联系人姓名">
      <template slot-scope="scope">
                      <div class="" >{{ scope.row.fromerContactsName }}</div>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="发起联系人电话">
      <template slot-scope="scope">
                      <span>
                          {{ scope.row.fromerContactsPhone}}</span>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="发起联系人邮箱">
      <template slot-scope="scope">
                      <span>
                          {{ scope.row.fromerContactsMail}}</span>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="发起联系人地址 ">
      <template slot-scope="scope">
                      <span>
                          {{ scope.row.fromerContactsAddr}}</span>
                  </template>
    </el-table-column>

    <el-table-column align="center" label="提交时间" width="100">
      <template slot-scope="scope">
                          <span>
                              {{ scope.row.submitTime | formatTime}}</span>
                      </template>
    </el-table-column>
    <el-table-column align="center" label="状态" width="70">
      <template slot-scope="scope">
                              <span v-if="scope.row.checkStatus == -1">待通知</span>
                              <span v-if="scope.row.checkStatus == 0">已通知</span>
                              <span v-if="scope.row.checkStatus == 1">已接受</span>
                              <span v-if="scope.row.checkStatus == 2">已拒绝</span>
                              <span v-if="scope.row.checkStatus == 3">已超时</span>
                          </template>
    </el-table-column>

    <el-table-column v-if=" tfcheckStatus == 0" align="center" label="操作" width="120">
      <template slot-scope="scope">
                                  <div style="text-align:center" >
                                    <span v-show="scope.row.fromToMe == 1" @click="handleEdit(scope.row,'edit')" class="clickText" >
                                      重新发起
                                    </span>
                                <span v-show="scope.row.fromToMe == 2 && scope.row.checkStatus !=1 ">  <span @click="handleRejectagree(scope.row)" class="clickText" >
                                                          同意
                                          </span></span>
                                      <span v-show="scope.row.fromToMe == 2 && scope.row.checkStatus !=1 ">  <span @click="handleRejectrefuse(scope.row)" class="clickText" >
                                                                拒绝
                                                </span></span>


                                  </div>
                              </template>
    </el-table-column>
  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>

  <el-dialog title="重新发起" :visible.sync="dialogFormVisible" width="50%" top='9%'>

    <el-form class="" label-width="30%" style="text-align:left">


      <el-row :gutter="24">
        <el-col :span="24">
          <table v-show="!show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;height: 350px;">
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;编号</td>
              <td>
                {{meeting.code}}
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;类别</td>
              <td>
                <span v-show="meeting.typeId == '0'">专家对接</span>
                <span v-show="meeting.typeId == '1'">需求对接</span>
                <span v-show="meeting.typeId == '2'">成果对接</span>
                <span v-show="meeting.typeId == '3'">服务对接</span>
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;申请方名称</td>
              <td>
                {{meeting.applicationContentName}}
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;申请说明</td>
              <td>
                {{meeting.applicationNote}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;联系人姓名</td>
              <td>
                <el-input v-model="meeting.fromerContactsName" placeholder="请输入联系人姓名" style="width:80%"></el-input>
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;联系人电话</td>
              <td>
                <el-input v-model="meeting.fromerContactsPhone" placeholder="请输入联系人电话" style="width:80%"></el-input>
              </td>
            </tr>

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;联系人邮箱</td>
              <td>
                <el-input v-model="meeting.fromerContactsMail" placeholder="请输入联系人邮箱" style="width:80%"></el-input>
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;联系人地址</td>
              <td>
                <el-input v-model="meeting.fromerContactsAddr" placeholder="请输入联系人地址" style="width:80%"></el-input>
              </td>
            </tr>

          </table>

        </el-col>

      </el-row>
    </el-form>

    <span v-show="!show" slot="footer" class="dialog-footer">
        <el-button type="primary" style="" @click="saveObj">保存</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
      </span>
  </el-dialog>


  <el-dialog title="查看详情" :visible.sync="dialogFormVisibleShow" width="50%" top='9%'>

    <el-form class="" label-width="30%" style="text-align:left">


      <el-row :gutter="24">
        <el-col :span="24">
          <table v-show="!show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;height: 350px;">
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;编号</td>
              <td>
                {{meeting.code}}
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;类别</td>
              <td>
                <span v-show="meeting.typeId == '0'">专家对接</span>
                <span v-show="meeting.typeId == '1'">需求对接</span>
                <span v-show="meeting.typeId == '2'">成果对接</span>
                <span v-show="meeting.typeId == '3'">服务对接</span>
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;申请方名称</td>
              <td>
                {{meeting.applicationContentName}}
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;申请说明</td>
              <td>
                {{meeting.applicationNote}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;联系人姓名</td>
              <td>
                {{meeting.fromerContactsName}}
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;联系人电话</td>
              <td>
                {{meeting.fromerContactsPhone}}
              </td>
            </tr>

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;联系人邮箱</td>
              <td>
                {{meeting.fromerContactsMail}}
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;联系人地址</td>
              <td>
                {{meeting.fromerContactsAddr}}
              </td>
            </tr>

          </table>

        </el-col>

      </el-row>
    </el-form>

  </el-dialog>

  <!-- <el-dialog title="信用等级" :visible.sync="dialogShowLevel" width="30%" top='5%'>

      <el-form class="" label-width="30%" style="text-align:left">
        <el-row :gutter="24">
          <el-col :span="24">

            <el-form-item label="信用等级">
              <el-select v-model="xyset.creditLevel" style="height:30px" placeholder="请选择">
                <el-option label="请选择" key="" value="">
                </el-option>
                <el-option label="1" key="1" value="1">
                </el-option>
                <el-option label="2" key="2" value="2">
                </el-option>
                <el-option label="3" key="3" value="3">
                </el-option>
                <el-option label="4" key="4" value="4">
                </el-option>
                <el-option label="5" key="5" value="4">
                </el-option>
              </el-select>
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

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" style="" @click="saveReject(rej)">保存</el-button>
        <el-button type="primary" @click="dialogShowSH = false">关闭</el-button>
      </span>
    </el-dialog> -->

</div>
</template>



<script>
import {
  getMeeting,
  getMeetingC,
  saveMeeting,
  rejectrefuse,
  rejectagree
} from '@/api/buttManage'
export default {
  data() {
    return {
      dialogFormVisibleShow: false,
      dialogFormVisible: false,
      list: [],
      input: {
        typeId: null,
        checkStatus: null,
        isFormOrToMe: null,
      },
      listQuery: {
        page: 1,
        limit: 10,
      },
      meeting: {
        fromerContactsName: '',
        fromerContactsPhone: '',
        fromerContactsMail: '',
        fromerContactsAddr: '',
        application_note: '',
      },
    }
  },
  mounted() {
    if (typeof this.$route.query.checkStatus == "number" || typeof this.$route.query.checkStatus == "string") {
      this.input.checkStatus = parseInt(this.$route.query.checkStatus)
      this.input.isFormOrToMe = parseInt(this.$route.query.isFormOrToMe)
      this.input.typeId = parseInt(this.$route.query.typeId)
    }
    this.loadMeeting()
  },
  computed: {},
  methods: {
    async loadMeeting() {
      if (this.input) {
        this.listQuery.typeId = this.input.typeId
        this.listQuery.checkStatus = this.input.checkStatus
        this.listQuery.currPage = this.listQuery.page
        this.listQuery.pageSize = this.listQuery.limit
        this.tfcheckStatus = this.input.checkStatus
        this.listQuery.isFormOrToMe = this.input.isFormOrToMe
      } else {
        this.listQuery.objName = ''
      }
      let {
        data,
        success
      } = await getMeeting(this.listQuery)
      this.list = data.list
    },


    async handleEdit(item, type) {
      if (type === 'edit') {
        this.title = '编辑活动'
        this.dialogFormVisible = true
        let {
          data,
          success
        } = await getMeetingC(item.id)
        debugger
        this.meeting = data

      } else if (type === 'show') {
        debugger
        this.dialogFormVisibleShow = true
        let {
          data,
          success,
          message
        } = await getMeetingC(item.id)
        this.meeting = data
        this.title = '查看活动详情'
      }
    },
    async handleRejectagree(objdata) {
      let obj = {}
      obj.id = objdata.id
      let {
        data,
        success,
        message
      } = await rejectagree(obj)

      this.$message({
        type: 'success',
        message: '操作成功！'
      });

    },

    async handleRejectrefuse(objdata) {

        let obj = {}
        obj.id = objdata.id
      let {
        data,
        success
      } = await rejectrefuse(obj)
      this.$message({
        type: 'success',
        message: '操作成功！'
      });
      this.loadMeeting()

    },
    async saveObj() {
      let {
        data,
        success
      } = await saveMeeting(this.meeting)
      debugger
      this.$message({
        type: 'success',
        message: message
      });
      this.loadMeeting()
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

  }
}
</script>

<style lang="scss">
</style>
