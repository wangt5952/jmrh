<template>
<div class="tab-container">
  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span>关键字</span>
      <el-input v-model="input.objName" placeholder="" style="width:200px;"></el-input>
      <span style="margin-left: 15px;">类别</span>
      <el-select v-model="input.typeId" style="height:30px;width:110px;" placeholder="请选择">
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
      <el-select v-model="input.isFormOrToMe" style="width:100px;height:30px" placeholder="请选择">
        <el-option label="请选择" key="" value="">
        </el-option>
        <el-option label="我发起的" key=1 value=1>
        </el-option>
        <el-option label="向我发起的" key=2 value=2>
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
        <el-button v-show="userType =='0' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1" style="" @click="plsj" type="primary">批量上架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1" style="" @click="pldc" type="primary">批量导出</el-button>
      </div>

    </div>
  </div>


  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:5px;width:100%;font-size:12px;">
    <el-table-column type="selection" width="30">
    </el-table-column>
    <el-table-column align="center" label="编号" width="120">
      <template slot-scope="scope">
                      <span>{{ scope.row.code }}</span>
                  </template>
    </el-table-column>

    <el-table-column align="center" label="类别" width="70">
      <template slot-scope="scope">
                              <span v-if="scope.row.typeId == 0">专家对接</span>
                              <span v-if="scope.row.typeId == 1">需求对接</span>
                              <span v-if="scope.row.typeId == 2">成果对接</span>
                              <span v-if="scope.row.typeId == 3">服务对接</span>
                              <span v-else>暂无</span>
                          </template>
    </el-table-column>
    <el-table-column align="center" label="发起联系人姓名" >
      <template slot-scope="scope">
                      <div class="clickText" >{{ scope.row.fcn }}</div>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="发起联系人电话"  >
      <template slot-scope="scope">
                      <span>
                          {{ scope.row.fcp}}</span>
                  </template>
    </el-table-column>
    <el-table-column  align="center" label="发起联系人邮箱">
      <template slot-scope="scope">
                      <span>
                          {{ scope.row.fcm}}</span>
                  </template>
    </el-table-column>
    <el-table-column  align="center" label="发起联系人地址 ">
      <template slot-scope="scope">
                      <span>
                          {{ scope.row.fca}}</span>
                  </template>
    </el-table-column>

    <el-table-column  align="center" label="内容ID" width="100">
      <template slot-scope="scope">
                          <span>
                              {{ scope.row.appId}}</span>
                      </template>
    </el-table-column>

    <el-table-column align="center" label="状态" width="70px;">
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
                                    <span @click="handleEdit(scope.row,'edit')" class="clickText" >
                                      编辑
                                    </span>
                                <span>  <span @click="handlexj(scope.row)" class="clickText" >
                                                          同意
                                          </span></span>
                                <span>  <span @click="handlesj(scope.row)" class="clickText" >
                                                          拒绝
                                </span></span>

                                  </div>
                              </template>
    </el-table-column>
  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>

  <el-dialog title="修改对接" :visible.sync="dialogFormVisible" width="50%" top='9%'>

    <el-form class="" label-width="30%" style="text-align:left">


      <el-row :gutter="24">
        <el-col :span="24">
          <table v-show="!show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;height: 350px;">

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px">&nbsp;&nbsp;分类类型</td>
              <td>
                <el-select v-model="meeting.typeId" style="height:30px;width:80%" placeholder="请选择">
                  <el-option label="专家对接" :key=0 :value=0>
                  </el-option>
                  <el-option label="需求对接" :key=1 :value=1>
                  </el-option>
                  <el-option label="成果对接" :key=2 :value=2>
                  </el-option>
                  <el-option label="服务对接" :key=3 :value=3>
                  </el-option>
                </el-select>
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;发起联系人姓名</td>
              <td>
                <el-input v-model="meeting.fcn" placeholder="请输入发起联系人姓名" style="width:80%"></el-input>
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;发起联系人电话</td>
              <td>
                <el-input v-model="meeting.fcp" placeholder="请输入发起联系人电话" style="width:80%"></el-input>
              </td>
            </tr>

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;发起联系人邮箱</td>
              <td>
                <el-input v-model="meeting.fcm" placeholder="请输入发起联系人邮箱" style="width:80%"></el-input>
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;发起联系人地址</td>
              <td>
                <el-input v-model="meeting.fca" placeholder="请输入发起联系人地址" style="width:80%"></el-input>
              </td>
            </tr>

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;内容ID</td>
              <td>
                <el-input v-model="meeting.appId" placeholder="请输入内容ID" style="width:80%"></el-input>
              </td>
              <td style="width:150px;padding:20px;">&nbsp;&nbsp;申请说明</td>
              <td>
                <el-input v-model="meeting.appNote" placeholder="请输入申请说明" style="width:80%"></el-input>
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

  <!-- <el-dialog title="专家详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
      <div class="textr paddinga">
        <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handlePrint">打印</el-button>

        <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
      </div>

      <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
        <table class="tableExcelText" id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>

          <tr height=27 style='mso-height-source:userset;height:20.25pt' id='r0'>
            <td colspan=13 id='tc0' height=27>军民科技协同创新专家信息表</td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r1'>
            <td height=19 style='height:14.25pt;'>姓名</td>
            <td colspan=2 id='tc1'>{{detailData.name}}</td>
            <td colspan=2 id='tc2'>性别</td>
            <td v-show="detailData.sex == '1'">男</td>
            <td v-show="detailData.sex == '0'">女</td>
            <td>出生年月</td>
            <td>{{detailData.bornDate}}</td>
            <td colspan=2 id='tc3'>身份证号</td>
            <td colspan=3 id='tc4'>{{detailData.code}}</td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r2'>
            <td height=19 style='height:14.25pt;'>毕业院校</td>
            <td colspan=2 id='tc5'>{{detailData.shcool}}</td>
            <td colspan=2 id='tc6'>学历</td>
            <td colspan=3 v-show="detailData.edu == '1'">小学</td>
            <td colspan=3 v-show="detailData.edu == '2'">初中</td>
            <td colspan=3 v-show="detailData.edu == '3'">高中</td>
            <td colspan=3 v-show="detailData.edu == '4'">大专</td>
            <td colspan=3 v-show="detailData.edu == '5'">本科</td>
            <td colspan=3 v-show="detailData.edu == '6'">研究生</td>
            <td colspan=3 v-show="detailData.edu == '7'">博士</td>
            <td colspan=3 v-show="detailData.edu == '8'">其他</td>
            <td colspan=2 id='tc8'>学位</td>
            <td colspan=3 v-show="detailData.academic == '1'">学士</td>
            <td colspan=3 v-show="detailData.academic == '2'">硕士</td>
            <td colspan=3 v-show="detailData.academic == '3'">博士</td>
            <td colspan=3 v-show="detailData.academic == '4'">其他</td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r3'>
            <td height=19 style='height:14.25pt;'>研究领域</td>
            <td colspan=12 id='tc10'>
              <el-checkbox-group v-model="detailData.research_field">
                <el-checkbox label="1">智能装备</el-checkbox>
                <el-checkbox label="2">电子信息</el-checkbox>
                <el-checkbox label="3">新材料 </el-checkbox>
                <el-checkbox label="4">航空航天</el-checkbox>
                <el-checkbox label="5">生物技术与新医药</el-checkbox>
                <el-checkbox label="6">能源与环保</el-checkbox>
                <el-checkbox label="7">管理</el-checkbox>
                <el-checkbox label="8">其他</el-checkbox>
              </el-checkbox-group>
            </td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r4'>
            <td height=19 style='height:14.25pt;'>研究方向</td>
            <td colspan=12 id='tc11'>{{detailData.research_area}}</td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r5'>
            <td height=19 style='height:14.25pt;'>工作单位</td>
            <td colspan=5 id='tc12'>{{detailData.work_unit}}</td>
            <td>工作部门</td>
            <td colspan=6 id='tc13'>{{detailData.work_BM}}</td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r6'>
            <td height=19 style='height:14.25pt;'>现任职务</td>
            <td colspan=5 id='tc14'>{{detailData.zwname}}</td>
            <td>职称</td>
            <td colspan=2 id='tc15'>{{detailData.zcname}}</td>
            <td>职称级别</td>
            <td colspan=3 id='tc16'>
              <el-checkbox-group v-model="detailData.zclevel">
                <el-checkbox label="1">正高</el-checkbox>
                <el-checkbox label="2">副高</el-checkbox>
              </el-checkbox-group>
            </td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r7'>
            <td height=19 style='height:14.25pt;'>手机号码</td>
            <td colspan=5 id='tc17'>{{detailData.mobilephone}}</td>
            <td>办公电话</td>
            <td colspan=2 id='tc18'>{{detailData.telphone}}</td>
            <td>邮箱</td>
            <td colspan=3 id='tc19'>{{detailData.fdemail}}</td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r8'>
            <td height=19 style='height:14.25pt;'>所在地区</td>
            <td colspan=5 id='tc20' style='border-bottom:1px solid windowtext;'>
              <area-cascader :level="1" v-model="detailData.country" :data="pcaas"></area-cascader>
            </td>
            <td>通讯地址</td>
            <td colspan=6 id='tc21'>{{detailData.address}}</td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r9'>
            <td colspan=13 id='tc22' height=19>主要学术成就/研究成果/管理成就</td>
          </tr>
          <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r10'>
            <td colspan=13 id='tc23' height=19>{{detailData.success_record}}</td>
          </tr>
          <tr>
            <td colspan=13 id='tc24' height=19>主要产学研合作项目情况（国防军工类项目）</td>
          </tr>
          <tr>
            <td colspan=13 id='tc25' height=19>{{detailData.project_desc}}</td>
          </tr>
          <tr>
            <td colspan=13 id='tc26' height=19>近5年专业研究及获奖情况</td>
          </tr>
          <tr>
            <td colspan=13 id='tc27' height=19>（注：“项目或课题来源”指下达或委托任务单位，国际合作、国家、部门、地方、企业、单位自有等。奖励情况以获国家、省（部）级为主。）</td>
          </tr>
          <tr>
            <td height=34 style='height:25.5pt;overflow:hidden;'>项目或课题名称</td>
            <td style='overflow:hidden;'>项目或课题来源</td>
            <td colspan=2 id='tc28'>完成情况</td>
            <td colspan=2 id='tc29'>完成时间</td>
            <td colspan=3 id='tc30'>奖项名称</td>
            <td colspan=3 id='tc31'>获奖等级</td>
            <td>获奖时间</td>
          </tr>
          <tr v-for='item in detailData.research_record'>
            <td height=19 style='height:14.25pt;'>{{item.projectname}}</td>
            <td>{{item.projectSrc}}</td>
            <td colspan=2 id=''>{{item.finishcon}}</td>
            <td colspan=2 id=''>{{item.finishtime}}</td>
            <td colspan=3 id=''>{{item.rewname}}</td>
            <td colspan=3 id=''>{{item.rewlevel}}</td>
            <td colspan=3 id=''>{{item.rewtime}}</td>
          </tr>

        </table>

      </div>

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogShowDep = false">关闭</el-button>
      </span>
    </el-dialog> -->


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
  getMeeting
} from '@/api/buttManage'
export default {
  data() {
    return {
      list: [],
      input: {
        objName: '',
        typeId: 0,
        checkStatus: 1,
        isFormOrToMe: '',
      },
      listQuery: {
        page: 1,
        limit: 10,
      },
      meeting: {
        typeId: '',
        fcn: '',
        fcp: '',
        fcm: '',
        fca: '',
        appId: '',
      },
    }
  },
  mounted() {
    this.loadMeeting()
  },
  computed: {},
  methods: {
    async loadMeeting() {
      if (this.input) {
        this.listQuery.objName = this.input.objName
        this.listQuery.typeId = this.input.typeId
        this.listQuery.checkStatus = this.input.checkStatus
        this.tfcheckStatus = this.input.checkStatus

        this.listQuery.isFormOrToMe = this.input.isFormOrToMe
      } else {
        this.listQuery.objName = ''
      }

      let {
        data,
        success
      } = await getMeeting(this.listQuery)

      // this.list = data.list
    },


    async handleEdit(item, type) {
      if (type === 'edit') {
        this.title = '编辑活动'
        this.dialogFormVisible = true

      } else if (type === 'add') {
        this.title = '添加活动'
      } else if (type === 'show') {
        this.dialogFormVisible = true
        let {
          data,
          success
        } = await getExchangesC(item.id)
        this.active = data
        this.show = true
        this.title = '查看活动详情'
        this.loadEnrolls()
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

  }
}
</script>

<style lang="scss">
</style>
