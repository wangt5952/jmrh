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
      <span style="margin-left: 15px;">信用级别</span>
      <el-select v-model="input.creditLevel" style="width:100px;height:30px" placeholder="请选择">
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
        <el-option label="5" key="5" value="5">
        </el-option>
      </el-select>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary">查询</el-button>
      <div class="" style="padding:10px 0px;">
        <el-button style="" @click="handleEdit" type="primary">添加专家</el-button>
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
                    <span>{{ scope.row.number }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="名称" width="80">
      <template slot-scope="scope">
                    <div @click="showDetail(scope.row,'edit')" class="clickText" >{{ scope.row.name }}</div>
                </template>
    </el-table-column>
    <el-table-column align="center" label="证件号">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.code}}</span>
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
                        <span v-if='scope.row.researchField.includes(1)'>智能装备</span>
                        <span v-if='scope.row.researchField.includes(2)'>电子信息</span>
                        <span v-if='scope.row.researchField.includes(3)'>新材料</span>
                        <span v-if='scope.row.researchField.includes(4)'>航空航天</span>
                        <span v-if='scope.row.researchField.includes(5)'>生物技术与新医药</span>
                        <span v-if='scope.row.researchField.includes(6)'>能源与环保</span>
                        <span v-if='scope.row.researchField.includes(7)'>管理</span>
                        <span v-if='scope.row.researchField.includes(99)'>{{scope.row.researchFieldOther}}</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="信用级别" width="70">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.creditLevel}}</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="是否可见" width="70">
      <template slot-scope="scope">
                        <span v-if="scope.row.status == 1">可见</span>
                        <span v-if="scope.row.status == 0">不可见</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="发布人" width="80">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.form.creater}}</span>
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
                            <span v-show="tfcheckStatus == 0">待审核</span>
                            <span v-show="tfcheckStatus == 1">已发布</span>
                            <span v-show="tfcheckStatus == 2">驳回</span>
                        </template>
    </el-table-column>


    <el-table-column v-show="userType =='0'" align="center" label="操作" width="120">
      <template slot-scope="scope">
                                <div style="text-align:center" >
                                  <span v-show="tfcheckStatus == 1" @click="handlexy(scope.row)" class="clickText" >
                                    信用
                                  </span>
                                    <span v-show="tfcheckStatus == 0" @click="handlesh(scope.row)" class="clickText" >
                                      审核
                                    </span>
                                  <span @click="handleEdit(scope.row,'edit')" class="clickText" >
                                    编辑
                                  </span>
                              <span v-show="scope.row.status == 1 && tfcheckStatus == 1">  <span @click="handlexj(scope.row)" class="clickText" >
                                                        下架
                                        </span></span>
                              <span v-show="scope.row.status == 0 && tfcheckStatus == 1">  <span @click="handlesj(scope.row)" class="clickText" >
                                                        上架
                              </span></span>

                                </div>
                            </template>
    </el-table-column>
  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>



  <el-dialog title="专家详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
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
  </el-dialog>


  <el-dialog title="信用等级" :visible.sync="dialogShowLevel" width="30%" top='5%'>

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
  </el-dialog>

</div>
</template>

<script>
import 'vue-area-linkage/dist/index.css'; // v2 or higher
import {
  getexpert,
  exportLib,
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

import {
  getAllrole
} from '@/api/role'
import {
  depgetAll
} from '@/api/department'
import {
  getUserDetail,
  setUserDetail
} from '@/api/login'

import {
  pca,
  pcaa
} from "area-data";

import table2excel from 'table2excel'
import printArea from 'printArea'

export default {
  data() {
    return {
      rej: {
        way: '1',
        info: '',
        formId: ''
      },
      xyset: {
        creditLevel: '',
        id: ''
      },
      creditLevel: '',
      input: {
        objName: '',
        status: '',
        checkStatus: 1,
        creditLevel: '',
      },
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      dialogShowSH: false,
      dialogShowLevel: false,
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

      pcaas: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      expert: {
        cardPositive: [],
        cardSide: [],
        cardHands: [],
        onepicture: [],
        name: '',
        sex: '',
        bornDate: '',
        code: '',
        shcool: '',
        edu: '',
        academic: '',
        research_field: '',
        research_area: '',
        zwname: '',
        zcname: '',
        zclevel: '',
        work_unit: '',
        mobilephone: '',
        telphone: '',
        fdemail: '',
        country: '',
        address: '',
        success_record: '',
        project_desc: '',
        research_record: [{
          projectname: '',
          projectSrc: '',
          finishcon: '',
          finishtime: '',
          rewname: '',
          rewlevel: '',
          rewtime: ''
        }]
      },
      treeData: [],
      loading: true,
      userType: '',
      multipleSelection: [],
      detailData: {
        cardPositive: [],
        cardSide: [],
        cardHands: [],
        onepicture: [],
        name: '',
        sex: '',
        bornDate: '',
        code: '',
        shcool: '',
        edu: '',
        academic: '',
        research_field: '',
        research_area: '',
        zwname: '',
        zcname: '',
        zclevel: '',
        work_unit: '',
        mobilephone: '',
        telphone: '',
        fdemail: '',
        country: '',
        address: '',
        success_record: '',
        project_desc: '',
        research_record: [{
          projectname: '',
          projectSrc: '',
          finishcon: '',
          finishtime: '',
          rewname: '',
          rewlevel: '',
          rewtime: ''
        }]
      },
      tfcheckStatus: '',
    }
  },
  async mounted() {
    if (typeof this.$route.query.checkStatus == 'number') {
      this.input.checkStatus = this.$route.query.checkStatus
    }
    this.listLoading = false
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
      } = await updateLevel(objdata, '1')
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
    addProjectexpert() {
      this.expert.research_record.push({
        projectname: '',
        projectSrc: '',
        finishcon: '',
        finishtime: '',
        rewname: '',
        rewlevel: '',
        rewtime: ''
      })
    },
    showDetail(data) {
      this.dialogShowDep = true
      let objData
      if (this.input.checkStatus == 1) {
        objData = data.form.detail
      } else if (this.input.checkStatus == 0 || this.input.checkStatus == -1) {
        objData = data.detail
      }
      this.detailData = JSON.parse(objData)
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
      } = await getexpert(this.listQuery)
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
      if (this.multipleSelection.length == 0) {
        this.$message({
          type: 'success',
          message: '请勾选下架内容!'
        });
      }
      let {
        data,
        success
      } = await PLoffUserDetails(this.multipleSelection, '1')
      if (success) {
        this.$message({
          type: 'success',
          message: '下架成功!'
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
      } = await PLonUserDetails(this.multipleSelection, '1')
      if (success) {
        this.$message({
          type: 'success',
          message: '上架成功!'
        });
        this.loadPageList()
      }
    },
    async pldc() {
      let obj = {}
      obj.objName = this.input.objName
      obj.userType = window.sessionStorage.getItem('userType')
      obj.creditLevel = this.input.creditLevel
      obj.status = this.input.status
      obj.token = window.sessionStorage.getItem('token')
      let {
        data,
        success
      } = await exportLib(obj)
      if (success) {
        this.$message({
          type: 'success',
          message: '导出成功!'
        });
      }
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
      obj.type = '1'
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
      obj.type = '1'
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
    async handleEdit(data, type) {
      if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await delLib(data)
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
          name: 'expertEdit',
          params: {
            objId: objId,
            objData: objData
          }
        })

      } else {
        this.$router.push({
          name: 'expertEdit'
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
.el-input__inner {
    height: 30px!important;
    line-height: 30px!important;
}
.el-checkbox__input.is-indeterminate .el-checkbox__inner,
.tableExcelText .el-checkbox__input.is-checked .el-checkbox__inner {
    background-color: #94989c;
    border-color: #94989c;
}
.tableExcelText .el-checkbox__input.is-checked+.el-checkbox__label {
    color: #000;
}
</style>
