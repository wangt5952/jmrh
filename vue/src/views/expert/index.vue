<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <el-button v-if="userType =='0'" style="margin-left: 10px;" @click="handleEdit" type="primary" icon="el-icon-edit">添加专家</el-button>

    </div>
  </div>


  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" align="center" label="ID">

    </el-table-column>
    <el-table-column align="center" label="姓名">
      <template slot-scope="scope">
                    <span>{{ scope.row.name }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="身份证号">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.dpartmentId}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="手机号">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.dpartmentId}}</span>
                </template>
    </el-table-column>

    <el-table-column align="center" label="领域">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.research_area}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="项目描述">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.project_desc}}</span>
                    </template>
    </el-table-column>
    <el-table-column align="center" label="">
      <template slot-scope="scope">

                      <div style="margin:2% 2% 2% 2%">
                          <el-button size="small" @click="showDetail(scope.row,'edit')" type=""  class="el-icon-edit colorblue borderblue">查看详情</el-button>
                      </div>
                    </template>
    </el-table-column>
    <el-table-column v-if="userType =='0'" align="center" label="操作">
      <template slot-scope="scope">
                    <div style="margin:2% 2% 2% 2%">
                        <el-button size="small" @click="handleEdit(scope.row,'edit')" type=""  class="el-icon-edit colorblue borderblue"></el-button>
                        <el-button size="small" @click="handleEdit(scope.row,'del')"  type=""  class="el-icon-delete colorred borderred"></el-button>
                    </div>
                </template>
    </el-table-column>

  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>

  <el-dialog title="专家详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
    <div class="textr paddinga">
      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handlePrint">打印</el-button>

      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
      <table id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>

        <tr height=27 style='mso-height-source:userset;height:20.25pt' id='r0'>
          <td colspan=13 id='tc0' height=27>军民科技协同创新专家信息表</td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r1'>
          <td height=19 style='height:14.25pt;'>姓名</td>
          <td colspan=2 id='tc1'></td>
          <td colspan=2 id='tc2'>性别</td>
          <td></td>
          <td>出生年月</td>
          <td></td>
          <td colspan=2 id='tc3'>身份证号</td>
          <td colspan=3 id='tc4'></td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r2'>
          <td height=19 style='height:14.25pt;'>毕业院校</td>
          <td colspan=2 id='tc5'></td>
          <td colspan=2 id='tc6'>学历</td>
          <td colspan=3 id='tc7'></td>
          <td colspan=2 id='tc8'>学位</td>
          <td colspan=3 id='tc9'></td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r3'>
          <td height=19 style='height:14.25pt;'>研究领域</td>
          <td colspan=12 id='tc10'>□智能装备<span style='mso-spacerun:yes'>&nbsp; </span>□电子信息<span style='mso-spacerun:yes'>&nbsp; </span>□新材料<span style='mso-spacerun:yes'>&nbsp; </span>□航空航天<span style='mso-spacerun:yes'>&nbsp; </span>□生物技术与新医药<span style='mso-spacerun:yes'>&nbsp; </span>□能源与环保
            <span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span>□管理<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□其他</td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r4'>
          <td height=19 style='height:14.25pt;'>研究方向</td>
          <td colspan=12 id='tc11'></td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r5'>
          <td height=19 style='height:14.25pt;'>工作单位</td>
          <td colspan=5 id='tc12'></td>
          <td>工作部门</td>
          <td colspan=6 id='tc13'></td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r6'>
          <td height=19 style='height:14.25pt;'>现任职务</td>
          <td colspan=5 id='tc14'></td>
          <td>职称</td>
          <td colspan=2 id='tc15'></td>
          <td>职称级别</td>
          <td colspan=3 id='tc16'> □正高<br> □副高</td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r7'>
          <td height=19 style='height:14.25pt;'>手机号码</td>
          <td colspan=5 id='tc17'></td>
          <td>办公电话</td>
          <td colspan=2 id='tc18'></td>
          <td>邮箱</td>
          <td colspan=3 id='tc19'></td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r8'>
          <td height=19 style='height:14.25pt;'>所在地区</td>
          <td colspan=5 id='tc20' style='border-bottom:1px solid windowtext;'>省<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>市<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span>区（县）（下拉选择）</td>
          <td>通讯地址</td>
          <td colspan=6 id='tc21'></td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r9'>
          <td colspan=13 id='tc22' height=19>主要学术成就/研究成果/管理成就</td>
        </tr>
        <tr height=19 style='mso-height-source:userset;height:14.25pt' id='r10'>
          <td colspan=13 id='tc23' height=19></td>
        </tr>
        <tr>
          <td colspan=13 id='tc24' height=19>主要产学研合作项目情况（国防军工类项目）</td>
        </tr>
        <tr>
          <td colspan=13 id='tc25' height=19></td>
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
        <tr>
          <td height=19 style='height:14.25pt;'></td>
          <td></td>
          <td colspan=2 id='tc32'></td>
          <td colspan=2 id='tc33'></td>
          <td colspan=3 id='tc34'></td>
          <td colspan=3 id='tc35'>备注：可动态添加</td>
          <td></td>
        </tr>
        <tr>
          <td height=19 style='height:14.25pt;'></td>
          <td></td>
          <td colspan=2 id='tc36'></td>
          <td colspan=2 id='tc37'></td>
          <td colspan=3 id='tc38'></td>
          <td colspan=3 id='tc39'></td>
          <td></td>
        </tr>
        <tr>
          <td height=19 style='height:14.25pt;'></td>
          <td></td>
          <td colspan=2 id='tc40'></td>
          <td colspan=2 id='tc41'></td>
          <td colspan=3 id='tc42'></td>
          <td colspan=3 id='tc43'></td>
          <td></td>
        </tr>
        <tr>
          <td height=19 style='height:14.25pt;'></td>
          <td></td>
          <td colspan=2 id='tc44'></td>
          <td colspan=2 id='tc45'></td>
          <td colspan=3 id='tc46'></td>
          <td colspan=3 id='tc47'></td>
          <td></td>
        </tr>
        <tr>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
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



  <el-dialog title="专家信息填写" :visible.sync="dialogFormVisible" width="100%" top='0%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">
        <el-tabs type="border-card">
          <el-tab-pane>
            <span slot="label"><i class="el-icon-date"></i> 专家信息</span>
            <el-form class="" label-width="30%" style="text-align:left">
              <el-row :gutter="20">
                <el-col :span="20">

                  <el-form-item label="姓名">
                    <el-input placeholder="请输入姓名" v-model="expert.name" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="性别">
                    <el-select v-model="expert.sex" style="width:100px" placeholder="请选择">
                      <el-option label="男" key="1" value='1'>
                      </el-option>
                      <el-option label="女" key="0" value='0'>
                      </el-option>
                    </el-select>
                  </el-form-item>

                  <el-form-item label="出生日期">
                    <el-date-picker v-model="expert.bornDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
                    </el-date-picker>
                  </el-form-item>

                  <el-form-item label="身份证号">
                    <el-input placeholder="请输入身份证号" v-model="expert.id" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="上传身份证正面">
                    <div>
                      <!--这是正面照-->
                      <div class="photo">
                        <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="expert.cardPositive" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                        </el-upload>
                        <!-- <input type="file" @change="uploadImg($event)" id="IdCard"> -->
                        <!-- <input type="file"  @change="per.cardPositive"  id="IdCard"> -->

                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="上传身份证反面">
                    <div>
                      <!--这是背面照-->
                      <div class="photo photo1">
                        <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="expert.cardSide" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                        </el-upload>

                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="手持身份证">
                    <div>
                      <!--这是背面照-->
                      <div class="photo photo1">
                        <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="expert.cardHands" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                        </el-upload>

                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="免冠照片">
                    <div>
                      <!--这是背面照-->
                      <div class="photo photo1">
                        <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="expert.onepicture" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                        </el-upload>

                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="毕业院校">
                    <el-input placeholder="请输入毕业院校" v-model="expert.shcool" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="学历">
                    <el-input placeholder="请输入学历" v-model="expert.edu" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="学位">
                    <el-input placeholder="请输入学位" v-model="expert.academic" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="研究领域">
                    <el-radio v-model="expert.research_field" label="1">智能装备</el-radio>
                    <el-radio v-model="expert.research_field" label="2">电子信息</el-radio>
                    <el-radio v-model="expert.research_field" label="3">新材料 </el-radio>
                    <el-radio v-model="expert.research_field" label="4">航空航天</el-radio>
                    <el-radio v-model="expert.research_field" label="5">生物技术与新医药</el-radio>
                    <el-radio v-model="expert.research_field" label="6">能源与环保</el-radio>
                    <el-radio v-model="expert.research_field" label="7">管理</el-radio>
                    <el-radio v-model="expert.research_field" label="8">其他</el-radio>
                  </el-form-item>

                  <el-form-item label="研究方向">
                    <el-input placeholder="请输入研究方向" v-model="expert.research_area" style="width:80%"></el-input>
                  </el-form-item>

                </el-col>

                <el-col :span="10">


                </el-col>

              </el-row>
            </el-form>

          </el-tab-pane>
          <el-tab-pane label="联系方式">
            <el-form class="" label-width="30%" style="text-align:left">
              <el-row :gutter="20">
                <el-col :span="20">

                  <el-form-item label="现任职务">
                    <el-input placeholder="请输入现任职务" v-model="expert.zwname" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="职称">
                    <el-input placeholder="请输入职称" v-model="expert.zcname" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="职称级别">
                    <el-radio v-model="expert.zclevel" label="1">正高</el-radio>
                    <el-radio v-model="expert.zclevel" label="2">副高</el-radio>
                  </el-form-item>
                  <el-form-item label="工作单位">
                    <el-input placeholder="请输入职称" v-model="expert.work_unit" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="手机号">
                    <el-input placeholder="请输入手机号" v-model="expert.mobilephone" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="办公电话">
                    <el-input placeholder="请输入办公电话" v-model="expert.telphone" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="邮箱">
                    <el-input placeholder="请输入邮箱" v-model="expert.fdemail" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="所在地区">
                    <area-cascader :level="1" v-model="expert.country" :data="pcaa"></area-cascader>
                  </el-form-item>
                  <el-form-item label="通讯地址">
                    <el-input placeholder="请输入通讯地址" v-model="expert.address" style="width:80%"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="10">


                </el-col>

              </el-row>
            </el-form>
          </el-tab-pane>


          <el-tab-pane label="主要学术成就/研究成果/管理成就">
            <el-form class="" label-width="30%" style="text-align:left">
              <el-row :gutter="24">
                <el-col :span="24">
                  <textarea v-model="expert.success_record" rows="3" cols="20" style="width:100%;height: 350px;">
                  </textarea>
                </el-col>

                <el-col :span="10">


                </el-col>

              </el-row>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="主要产学研合作项目情况（国防军工类项目）">
            <el-form class="" label-width="30%" style="text-align:left">
              <el-row :gutter="24">
                <el-col :span="24">
                  <textarea v-model="expert.project_desc" rows="3" cols="20" style="width:100%;height: 350px;">
                  </textarea>
                </el-col>

                <el-col :span="10">


                </el-col>

              </el-row>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="近5年专业研究及获奖情况">
            <el-form class="" label-width="30%" style="text-align:left">
              <el-row :gutter="22">
                <!-- <el-form-item label=""> -->

                <div style="margin:0 auto">

                  <el-button size="small" @click="addProjectexpert()">
                    添加课题
                  </el-button>
                  <el-table class="tableH" :data="expert.research_record" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                    <el-table-column align="center" label="项目或课题名称">
                      <template slot-scope="scope">
                      <input  type="text" v-model="scope.row.projectname">
                                      </template>
                    </el-table-column>
                    <el-table-column align="center" label="项目或课题来源">
                      <template slot-scope="scope">
                                            <span>
                                              <input  type="text" v-model="scope.row.projectSrc">
                                            </span>
                                        </template>
                    </el-table-column>
                    <el-table-column align="center" label="完成情况">
                      <template slot-scope="scope">
                      <input  type="text" v-model="scope.row.finishcon">
                                      </template>
                    </el-table-column>
                    <el-table-column align="center" label="完成时间">
                      <template slot-scope="scope">
                      <input  type="text" v-model="scope.row.finishtime">
                                      </template>
                    </el-table-column>
                    <el-table-column align="center" label="奖项名称">
                      <template slot-scope="scope">
                      <input  type="text" v-model="scope.row.rewname">
                                      </template>
                    </el-table-column>
                    <el-table-column align="center" label="获奖等级">
                      <template slot-scope="scope">
                      <input  type="text" v-model="scope.row.rewlevel">
                                      </template>
                    </el-table-column>
                    <el-table-column align="center" label="获奖时间">
                      <template slot-scope="scope">
                      <input  type="text" v-model="scope.row.rewtime">
                                      </template>
                    </el-table-column>
                  </el-table>
                </div>
                <!-- </el-form-item> -->

              </el-row>
            </el-form>
          </el-tab-pane>
        </el-tabs>

      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary"  @click="saveCreate(expert)">保存</el-button>
      <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
    </span>
  </el-dialog>


</div>
</template>

<script>
import {
  getexpert,
  addLib,
  delLib
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
      input: '',
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

      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      expert: {
        cardPositive: [],
        cardSide: [],
        cardHands: [],
        onepicture: [],
        name: '',
        sex: '',
        bornDate: '',
        id: '',
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
    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()
    this.userType = window.sessionStorage.getItem('userType')

  },
  computed: {},
  methods: {

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
    async loadPageList() {
      if (this.input) {
        this.listQuery.objName = this.input
      } else {
        this.listQuery.objName = ''
      }
      let {
        data,
        success
      } = await getexpert(this.listQuery)
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
    async loadgetdep() {
      let depData = await depgetAll()
      this.treeData = depData.data
    },
    async ondep1Change(val) {
      if (val) {
        val = val
      }
    },
    async saveCreate(obj) {
      // if (!this.validata.validausr(obj)) return
      let arr = {}
      arr.formType = '1'
      arr.detail = JSON.stringify(obj)
      let {
        data,
        success
      } = await addLib(arr)

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
        debugger
        this.$router.push({
          name: 'expertEdit',
          params: {
            objId :data.form.id,
            objData: data.form.detail
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
</style>
