<template>
<div class="tab-container">
  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span>关键字</span>
      <el-input v-model="input.objName"  style="width:20%;"></el-input>
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
        <el-button v-show="userType =='0' || userType =='101'" @click="handleEdit" type="primary">添加服务机构</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 0 || userType =='101' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1 || userType =='101' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1 || userType =='101' && tfcheckStatus == 1" style="" @click="plsj" type="primary">批量上架</el-button>
        <a :href=pldcUrl target="_blank"><el-button v-show="userType =='0' && tfcheckStatus == 1 " style=""  type="primary">批量导出</el-button></a>
      </div>

    </div>
  </div>

  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:5px;width:100%;font-size:12px;">
    <el-table-column type="selection"  width="30">
    </el-table-column>
    <el-table-column align="center" label="编号" width="150">
      <template slot-scope="scope">
                    <span>{{ scope.row.number ||scope.row.form.number }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="服务机构名">
      <template slot-scope="scope">
                    <div @click="showDetail(scope.row,'edit')" class="clickText" >{{ scope.row.name }}</div>
                </template>
    </el-table-column>
    <el-table-column align="center" label="机构代码">
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
                                        <span v-if='scope.row.orgNature.includes(1)'>研究开发</span>
                                        <span v-if='scope.row.orgNature.includes(2)'>科技投融资</span>
                                        <span v-if='scope.row.orgNature.includes(3)'>技术转移</span>
                                        <span v-if='scope.row.orgNature.includes(4)'>检验检测</span>
                                        <span v-if='scope.row.orgNature.includes(5)'>创业孵化</span>
                                        <span v-if='scope.row.orgNature.includes(6)'>知识产权</span>
                                        <span v-if='scope.row.orgNature.includes(7)'>科技评估</span>
                                        <span v-if='scope.row.orgNature.includes(8)'>标准认证</span>
                                        <span v-if='scope.row.orgNature.includes(9)'>管理咨询</span>
                                        <span v-if='scope.row.orgNature.includes(10)'>综合科技服务</span>
                                        <span v-if='scope.row.orgNature.includes(99)'>{{scope.row.orgTypeOther}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="信用级别" width="100">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.creditLevel}}</span>
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
                            <span v-show="tfcheckStatus == 0">待审核</span>
                            <span v-show="tfcheckStatus == 1">已发布</span>
                            <span v-show="tfcheckStatus == 2">驳回</span>
                        </template>
    </el-table-column>

    <el-table-column v-if="tfcheckStatus == 2" align="center" label="驳回原因">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.info}}</span>
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
          <span v-show="userType =='0'&& tfcheckStatus != 0 || userType =='101'&& tfcheckStatus != 0" @click="handleEdit(scope.row,'edit')" class="clickText" >
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

  <el-dialog title="服务机构详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
    <div class="textr paddinga">
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handlePrint">打印</el-button>

      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
      <table class="tableExcelText" id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>
        <tr height=25 style='mso-height-source:userset;height:18.75pt' id='r0'>
          <td colspan=9 id='tc0' height=25 class=x40>军民科技协同创新服务机构信息表</td>
        </tr>
        <tr height=64 style='mso-height-source:userset;height:48pt' id='r1'>
          <td height=64 class=x35 style='height:48pt;'>单位名称</td>
          <td class=x22>{{detailData.name}}</td>
          <td class=x21>所在地区</td>
          <td class=x25>
            {{detailData.search_paramobj}}
          </td>
          <td class=x25>通信地址{{detailData.address}}</td>
          <td colspan=4 class=x28>邮编{{detailData.ecode}}</td>
        </tr>
        <tr height=19 id='r2'>
          <td height=19 class=x42>机构性质</td>
          <td colspan=8 id='tc1' class=x90>
              <span v-if="detailData.registerNature.includes('1')">企业;</span>
              <span v-if="detailData.registerNature.includes('2')">科研院所;</span>
              <span v-if="detailData.registerNature.includes('3')">高等院校;</span>
              <span v-if="detailData.registerNature.includes('99')">{{detailData.mechregisterNatureOther}};</span>

          </td>
        </tr>
        <tr height=19 id='r3'>
          <td height=19 class=x35>机构类别</td>
          <td colspan=8 id='tc2' class=x90>
            <span v-if="detailData.orgType.includes('1')">研究开发;</span>
            <span v-if="detailData.orgType.includes('2')">科技投融资;</span>
            <span v-if="detailData.orgType.includes('3')">技术转移;</span>
            <span v-if="detailData.orgType.includes('4')">检验检测;</span>
            <span v-if="detailData.orgType.includes('5')">创业孵化;</span>
            <span v-if="detailData.orgType.includes('6')">知识产权;</span>
            <span v-if="detailData.orgType.includes('7')">科技评估;</span>
            <span v-if="detailData.orgType.includes('8')">标准认证;</span>
            <span v-if="detailData.orgType.includes('9')">管理咨询;</span>
            <span v-if="detailData.orgType.includes('10')">综合科技服务;</span>
            <span v-if="detailData.orgType.includes('99')">{{detailData.orgTypeOther}};</span>
          </td>
        </tr>
        <tr height=32 id='r4'>
          <td height=32 class=x46>统一社会信用代码</td>
          <td colspan=9 class=x47>{{detailData.ecode}}</td>
        </tr>
        <tr height=32 id='r5'>
          <td height=32>机构法定代表人{{detailData.ecode}}</td>
          <td>姓名{{detailData.ecode}}</td>
          <td>电话{{detailData.ecode}}</td>
          <td colspan=3>手机{{detailData.ecode}}</td>
          <td colspan=3>邮箱{{detailData.ecode}}</td>
        </tr>
        <tr height=19 id='r6'>
          <td height=19 class=x42>机构联系人{{detailData.ecode}}</td>
          <td class=x49>姓名<span>{{detailData.ecode}} </span></td>
          <td class=x52>职务{{detailData.ecode}}</td>
          <td id='tc7' class=x49>电话{{detailData.ecode}}</td>
          <td colspan=2 id='tc8' class=x49>手机{{detailData.ecode}}</td>
          <td colspan=3 id='tc9' class=x49>邮箱{{detailData.ecode}}</td>
        </tr>
        <tr height=19 id='r7'>
          <td rowspan=3 height=83 class=x89>近三年服务情况</td>
          <td class=x21>{{lastt}}服务情况</td>
          <td colspan=2 class=x21>{{beforet}}服务情况</td>
          <td colspan=3 class=x21>{{previoust}}服务情况</td>
        </tr>
        <tr height=32 id='r8'>
          <td class=x21>{{detailData.service_amount_last}}</td>
          <td colspan=2 class=x21>{{detailData.service_amount_before}}</td>
          <td colspan=3 class=x25>{{detailData.service_amount_previous}}</td>
        </tr>
        <tr height=32 id='r9'>
          <td class=x21>服务数量（次）</td>
          <td class=x21>{{detailData.service_quantity_last}}</td>
          <td class=x25>{{detailData.service_quantity_before}}</td>
          <td colspan=5 id='tc12' class=x25>{{detailData.service_quantity_previous}}</td>
        </tr>
        <tr height=32 id='r10'>
          <td height=32 class=x36>人才队伍情况</td>
          <td class=x21>人员总数</td>
          <td colspan=2 id='tc13' class=x89>本科及以上学历人员占比</td>
          <td colspan=6 id='tc14' class=x60>中高级职称以上人员占比</td>
        </tr>
        <tr height=19 id='r11'>
          <td height=19 class=x34></td>
          <td class=x36>
            <font class="font3"><span></span></font>
            <font class="font5"><span>{{detailData.perNum}}</span></font>
            <font class="font3">人</font>
          </td>
          <td colspan=2 id='tc15' class=x91 style='border-right:1px solid windowtext;'>
            <font class="font6"><span>{{detailData.underPer}}</span></font>
            <font class="font4">人（</font>
            <font class="font6"><span> </span></font>
            <font class="font4">%）</font>
          </td>
          <td colspan=5 id='tc16' class=x27><span>{{detailData.MHPer}}</span>人（<span> </span>%）</td>
        </tr>
        <tr height=120 style='mso-height-source:userset;height:90pt' id='r12'>
          <td  :rowspan=(detailData.PorcolumnDefinitions.length+1) height=190 class=x91>承担的政府项目情况</td>
          <td colspan=1 class=x65>项目名称</td>
          <td colspan=1 class=x65>起止时间<br><span> </span>年<span> </span>月--<span> </span>年<span> </span>月</td>
          <td colspan=1 class=x66>项目来源<br></td>
          <td colspan=1 class=x66>资助方式<br></td>
          <td colspan=2 class=x66>执行情况<br></td>
        </tr>
        <tr v-for="item in detailData.PorcolumnDefinitions" height=32 id='r13'>
          <td colspan=1 class=x72>{{item.name}}</td>
          <td colspan=1 class=x37>{{item.time}}</td>
          <td colspan=1 class=x37>{{item.source}}</td>
          <td colspan=1 class=x37>
            <span v-if="item.registerSupport.includes('1')">拨款;</span>
            <span v-if="item.registerSupport.includes('2')">贴息;</span>
            <span v-if="item.registerSupport.includes('3')">减免税;</span>
            <span v-if="item.registerSupport.includes('4')">以上全无;</span>
            <span v-if="item.registerSupport.includes('99')">{{item.mechregisterSupportOther}};</span>
          </td>
          <td colspan=2 class=x37>
              <span v-if="item.registerImplement.includes('1')">申请;</span>
              <span v-if="item.registerImplement.includes('2')">在研;</span>
              <span v-if="item.registerImplement.includes('3')">验收/结题;</span>
          </td>
        </tr>

        <tr height=19 id='r16'>
          <td :rowspan=(detailData.honor.length+1) height=57 class=x89>所获资质及荣誉</td>
          <td colspan=2 class=x32>名称</td>
          <td colspan=2 class=x32>获得时间</td>
          <td colspan=2  class=x26>颁发机构</td>
        </tr>
        <tr  v-for="item in detailData.honor" height=19 id='r17'>
          <td colspan=2 class=x25>{{item.name}}</td>
          <td colspan=2 class=x32>{{item.time}}</td>
          <td colspan=6 id='tc19' class=x93>{{item.issuingAgency}}</td>
        </tr>

        <tr height=32 id='r19'>
          <td :rowspan=(detailData.workrPorcolumnDefinitions.length+1) height=70 class=x89>主要工作成效</td>
          <td class=x41>服务项目名称</td>
          <td class=x25>服务对象</td>
          <td colspan=6 id='tc21' class=x37>服务时间</td>
        </tr>
        <tr  v-for="item in detailData.workrPorcolumnDefinitions" height=19 id='r20'>
          <td class=x88>{{item.name}}</td>
          <td class=x25>{{item.object}}</td>
          <td colspan=6 id='tc22' class=x93>{{item.time}}</td>
        </tr>
        <tr height=64 style='mso-height-source:userset;height:48pt' id='r22'>
          <td height=64 class=x89 style='height:48pt;overflow:hidden;'>可提供的服务简介（200字以内）</td>
          <td colspan=8 id='tc24' class=x91>{{detailData.serviceAbout}}</td>
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
              <el-option label="5" key="5" value="5">
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
import {
  getUserDetail,
  setUserDetail
} from '@/api/login'

import 'vue-area-linkage/dist/index.css'; // v2 or higher
import {
  getAllrole
} from '@/api/role'
import {
  depgetAll
} from '@/api/department'

import {
  pca,
  pcaa
} from "area-data";
import {
  getservices,
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
      total: null,
      textMap: {
        update: '编辑',
        dep: '部门详情',
        role: '角色详情',
        create: '添加'
      },

      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区

      mech: {
        zhengben: [],
        fuben: [],
        logo: [],
        registerNature: '',
        org_type: '',
        serviceAbout: '',
        service_amount_last: '',
        service_amount_before: '',
        service_amount_previous: '',
        service_quantity_last: '',
        service_quantity_before: '',
        service_quantity_previous: '',
        registerImplement: '',
        registerSupport: '',
        honor: [{
          name: '',
          time: '',
          issuingAgency: ''
        }],
        PorcolumnDefinitions: [{
          name: '',
          time: '',
          source: ''
        }],
        workrPorcolumnDefinitions: [{
          name: '',
          object: '',
          time: ''
        }],
        MHPer: '',
        underPer: '',
        perNum: '',
        ecode: '',
        address: '',
        country: '',
        name: '',
        fdemail: '',
        fdphone: '',
        fdname: '',
        lxemail: '',
        lxphone: '',
        lxzw: '',
        linkman: '',
        search_paramobj: ''
      },
      treeData: [],
      loading: true,
      userType: '',
      multipleSelection: [],
      tfcheckStatus: '',
      multipleSelection: [],
      detailData: {
        zhengben: [],
        fuben: [],
        logo: [],
        registerNature: '',
        orgType: '',
        serviceAbout: '',
        service_amount_last: '',
        service_amount_before: '',
        service_amount_previous: '',
        service_quantity_last: '',
        service_quantity_before: '',
        service_quantity_previous: '',
        registerImplement: '',
        registerSupport: '',
        honor: [{
          name: '',
          time: '',
          issuingAgency: ''
        }],
        PorcolumnDefinitions: [{
          name: '',
          time: [],
          source: '',
          registerImplement: [],
          registerSupport: [],
        }],
        workrPorcolumnDefinitions: [{
          name: '',
          object: '',
          time: []
        }],
        MHPer: '',
        underPer: '',
        perNum: '',
        ecode: '',
        address: '',
        country: '',
        name: '',
        fdemail: '',
        fdphone: '',
        fdname: '',
        lxemail: '',
        lxphone: '',
        lxzw: '',
        linkman: '',
        search_paramobj: ''
      },
      tfcheckStatus: '',
      dialogShowDep: false,
      dialogShowSH: false,
      rej: {
        way: '1',
        info: '',
        formId: ''
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
    this.pldcUrl = this.docUrl + '/lib/exportLib?objName='+this.input.objName+'&checkStatus=1&userType=3&creditLevel='+this.input.creditLevel+'&status='+this.input.status+'&token='+window.sessionStorage.getItem('token')
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
      } = await updateLevel(objdata, '3')
      this.$message({
        message: '保存成功',
        type: 'success'
      });
     this.dialogShowLevel = false
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
          this.dialogShowSH =false
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
            this.dialogShowSH = false
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
        return
      }
      let {
        data,
        success
      } = await PLonUserDetails(this.multipleSelection, '3')
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
      return
    }
      let {
        data,
        success
      } = await PLoffUserDetails(this.multipleSelection, '3')
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
      obj.type = '3'
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
      obj.type = '3'
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
      this.dialogShowDep = true
            let objData
            if (this.input.checkStatus == 1) {
              objData = data.form.detail
            } else if (this.input.checkStatus == 0 || this.input.checkStatus == -1|| this.input.checkStatus == 2) {
              objData = data.detail
            }
      this.detailData = JSON.parse(objData)
      this.loadOneTree(this.detailData.country[0])
      this.loadtwoTree(this.detailData.country[0], this.detailData.country[1])
      this.loadThreeTree(this.detailData.country[1], this.detailData.country[2])
      this.detailData.search_paramobj = this.arrValue1 + '-' + this.arrValue2 + '-' + this.arrValue3
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
      } = await getservices(this.listQuery)
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

      this.country = getUserIddata

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
      let getAlldata = this.country
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
      // if (!this.validata.validausr(obj)) return
      let arr = {}
      arr.formType = '3'
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
        let objId, objData
        if (this.input.checkStatus == 1 ) {
          objId = data.form.id
          objData = data.form.detail
          objnumber = data.form.number
        } else if (this.input.checkStatus == 0 || this.input.checkStatus == -1 || this.input.checkStatus == 2) {
          objId = data.id
          objData = data.detail
        }
        this.$router.push({
          name: 'mechanismEdit',
          params: {
            objId: objId,
            objnumber: objnumber,
            objData: objData
          }
        })
      } else {
        this.$router.push({
          name: 'mechanismEdit'
        })
      }
    },

        loadOneTree(code) {
          let pcadata = this.pcaa
          let arr = []
          for (var i in pcadata) {
            if (i == '86') {
              let obj = {}
              obj.date = i
              obj.value = pcadata[i]
              arr.push(obj)
            }
          }
          arr = arr[0].value
          for (var j in arr) {
            if (j == code) {
              let obj = {}
              obj.name = j
              obj.value = arr[j]
              this.arrValue1 = obj.value
            }
          }
        },
        loadtwoTree(code1, code2) {
          this.arrValue2 = []
          let pcadata = this.pcaa
          let arr = []
          for (var i in pcadata) {
            if (i == code1) {
              let obj = {}
              obj.date = i
              obj.value = pcadata[i]
              arr.push(obj)
            }
          }
          arr = arr[0].value
          for (var j in arr) {
            if (j == code2) {
              let obj = {}
              obj.name = j
              obj.value = arr[j]
              this.arrValue2 = obj.value
            }
          }
        },
        loadThreeTree(code1, code2) {
          this.arrValue3 = []
          let pcadata = this.pcaa
          let arr = []
          for (var i in pcadata) {
            if (i == code1) {
              let obj = {}
              obj.date = i
              obj.value = pcadata[i]
              arr.push(obj)
            }
          }
          if (arr.length > 0) {
            arr = arr[0].value
            for (var j in arr) {
              if (j == code2) {
                let obj = {}
                obj.name = j
                obj.value = arr[j]
                this.arrValue3 = obj.value
              }
            }
          }
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
