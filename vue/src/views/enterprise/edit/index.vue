<template>
<div class="tab-container">
  <div class="" style="height: 80% !important;overflow-y: auto;">

    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 联系人信息</span>

        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="姓名">
                <el-input placeholder="请输入姓名" v-model="com.lxname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职务">
                <el-input placeholder="请输入职务" v-model="com.lxzw" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <el-input placeholder="请输入手机号" v-model="com.lxphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input placeholder="请输入邮箱" v-model="com.lxemail" style="width:80%"></el-input>
              </el-form-item>
            </el-col>

          </el-row>
        </el-form>


      </el-tab-pane>
      <el-tab-pane label="企业法人信息">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="姓名">
                <el-input placeholder="请输入姓名" v-model="com.lpname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职务">
                <el-input placeholder="请输入职务" v-model="com.lpzw" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <el-input placeholder="请输入手机号" v-model="com.lpphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input placeholder="请输入邮箱" v-model="com.lpemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="营业执照正本">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="com.zhengben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="营业执照副本">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="com.fuben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="企业logo">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="com.logo" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="企业基础信息">
        <el-form class="" label-width="40%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="企业名称">
                <el-input placeholder="请输入企业名称" v-model="com.name" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="企业编码">
                <el-input placeholder="请输入企业编码" v-model="com.code" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="企业规模（注册资金）">
                <el-radio v-model="com.registered_capital" label="1">小于2000万（含）</el-radio>
                <el-radio v-model="com.registered_capital" label="2">2000-5000万</el-radio>
                <el-radio v-model="com.registered_capital" label="3">5000-1亿（含）</el-radio>
                <el-radio v-model="com.registered_capital" label="4">1亿-2亿（含）</el-radio>
                <el-radio v-model="com.registered_capital" label="5">2亿-4亿（含）</el-radio>
                <el-radio v-model="com.registered_capital" label="6">4亿及以上（含）</el-radio>
              </el-form-item>
              <el-form-item label="注册时间">
                <el-date-picker v-model="com.registerDate" type="date" placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="注册类型">
                <el-radio v-model="com.registered_type" label="1">内资企业</el-radio>
                <el-radio v-model="com.registered_type" label="2">合资企业</el-radio>
                <el-radio v-model="com.registered_type" label="3">外资企业</el-radio>
              </el-form-item>
              <el-form-item label="所在地区">
                <area-cascader :level="1" v-model="com.country" :data="pcaa"></area-cascader>
                <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
              </el-form-item>
              <el-form-item label="通讯地址">
                <el-input placeholder="请输入通讯地址" v-model="com.registeraddress" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮编">
                <el-input placeholder="请输入邮编" v-model="com.registerecode" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="是否高新技术企业">
                <el-radio v-model="com.is_high_new_tech" label="1">是</el-radio>
                <el-radio v-model="com.is_high_new_tech" label="2">否</el-radio>
              </el-form-item>
              <el-form-item label="企业所在地性质">
                <el-radio v-model="com.registerSite" label="1">国家级高新区 </el-radio>
                <el-radio v-model="com.registerSite" label="2">省级高新区</el-radio>
                <el-radio v-model="com.registerSite" label="3">国家级经开区 </el-radio>
                <el-radio v-model="com.registerSite" label="4">省级经开区</el-radio>
                <el-radio v-model="com.registerSite" label="5">其他</el-radio>
              </el-form-item>
              <el-form-item label="所在国家高新区">
                <el-radio v-model="com.registerHSite" label="1">南京高新区</el-radio>
                <el-radio v-model="com.registerHSite" label="2">无锡高新区</el-radio>
                <el-radio v-model="com.registerHSite" label="3">昆山高新区 </el-radio>
                <el-radio v-model="com.registerHSite" label="4">徐州高新区</el-radio>
                <el-radio v-model="com.registerHSite" label="5">其他</el-radio>
              </el-form-item>
              <el-form-item label="是否上市">
                <el-radio v-model="com.registerMarket" label="1">是</el-radio>
                <el-radio v-model="com.registerMarket" label="2">否</el-radio>
              </el-form-item>
              <el-form-item label="上市地点">
                <el-radio v-model="com.registerMarkeSite" label="1">上交所</el-radio>
                <el-radio v-model="com.registerMarkeSite" label="2">深交所</el-radio>
                <el-radio v-model="com.registerMarkeSite" label="3">新三板 </el-radio>
                <el-radio v-model="com.registerMarkeSite" label="4">港交所</el-radio>
                <el-radio v-model="com.registerMarkeSite" label="5">主版</el-radio>
                <el-radio v-model="com.registerMarkeSite" label="6">中小板</el-radio>
                <el-radio v-model="com.registerMarkeSite" label="7">创业板</el-radio>
              </el-form-item>
              <el-form-item label="所属领域">
                <el-radio v-model="com.domain" label="1">智能装备</el-radio>
                <el-radio v-model="com.domain" label="2">电子信息</el-radio>
                <el-radio v-model="com.domain" label="3">新材料 </el-radio>
                <el-radio v-model="com.domain" label="4">航空航天</el-radio>
                <el-radio v-model="com.domain" label="5">生物技术与新医药</el-radio>
                <el-radio v-model="com.domain" label="6">能源与环保</el-radio>
                <el-radio v-model="com.domain" label="7">其他</el-radio>
              </el-form-item>
              <el-form-item label="主营产品">
                <el-input placeholder="请输入主营产品" v-model="com.product" style="width:80%"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="获得知识产权情况">
        <el-form class="" label-width="40%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="发明专利数量(件)">
                <el-input placeholder="请输入发明专利数量" v-model="com.inventionNum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="国家新药数量(件)">
                <el-input placeholder="请输入国家新药数量" v-model="com.newDrugnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="实用新型数量(件)">
                <el-input placeholder="请输入实用新型数量" v-model="com.utilityModel" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="集成电路布图设计专有权数量(件)">
                <el-input placeholder="请输入集成电路布图设计专有权数量" v-model="com.integratedCircuitnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="外观设计数量(件)">
                <el-input placeholder="请输入外观设计数量" v-model="com.designnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="国防专利数量(件)">
                <el-input placeholder="请输入国防专利数量" v-model="com.nationalNum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="软件著作权数量(件)">
                <el-input placeholder="请输入软件著作权数量" v-model="com.softwareNum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="其他数量(件)">
                <el-input placeholder="请输入其他数量" v-model="com.othernum" style="width:80%"></el-input>
              </el-form-item>

            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="企业研发情况">
        <el-form class="" label-width="40%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="研发团队人员总数(人)">
                <el-input placeholder="请输入人员总数" v-model="com.researchTeamnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="本科及以上人数(人)">
                <el-input placeholder="请输入国家新药数量" v-model="com.bachelorAbovenum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="中高级职称以上人员人数(人)">
                <el-input placeholder="请输入中高级职称人数" v-model="com.middleLevelnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="研发团队高层次人才(人)">
                <el-input placeholder="请输入研发团队人才人数" v-model="com.highleveltalentsnum" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="高层次人才类型">
                <el-radio v-model="com.highleveltalentType" label="1">院士</el-radio>
                <el-radio v-model="com.highleveltalentType" label="2">国家千人</el-radio>
                <el-radio v-model="com.highleveltalentType" label="3">青年千人 </el-radio>
                <el-radio v-model="com.highleveltalentType" label="4">中青年科技创新领军人才</el-radio>
                <el-radio v-model="com.highleveltalentType" label="5">长江学者</el-radio>
                <el-radio v-model="com.highleveltalentType" label="6">国家杰青</el-radio>
                <el-radio v-model="com.highleveltalentType" label="7">其他</el-radio>
              </el-form-item>

              <el-form-item :label="service_research_lastt">
                <el-input placeholder="请输入研发投入" v-model="com.service_research_last" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_research_beforet">
                <el-input placeholder="请输入研发投入" v-model="com.service_research_before" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_research_previoust">
                <el-input placeholder="请输入研发投入" v-model="com.service_research_previous" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="平台建设情况">
                <el-radio v-model="com.platform" label="1">企业重点实验室（国家级）</el-radio>
                <el-radio v-model="com.platform" label="2">企业重点实验室（省级）</el-radio>
                <el-radio v-model="com.platform" label="3">企业院士工作站 </el-radio>
                <el-radio v-model="com.platform" label="4">程技术研究中心（国家级）</el-radio>
                <el-radio v-model="com.platform" label="5">程技术研究中心（省级）</el-radio>
              </el-form-item>
            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="创新情况">
        <el-form class="" label-width="40%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="是否承担过武器装备科研生产任务">
                <el-radio v-model="com.sfkyrw" label="1">是</el-radio>
                <el-radio v-model="com.sfkyrw" label="2">否</el-radio>
              </el-form-item>

              <el-form-item label="“三证”已获得">
                <el-radio v-model="com.szget" label="1">武器装备科研生产单位保密资格认证（一级、二级、三级） </el-radio>
                <el-radio v-model="com.szget" label="2">武器装备科研生产许可证 </el-radio>
                <el-radio v-model="com.szget" label="3">装备承制单位资格认证 </el-radio>
              </el-form-item>
              <el-form-item label="取得时间">
                <el-date-picker v-model="com.qdtime" type="date" placeholder="选择日期">
                </el-date-picker>
              </el-form-item>

              <el-form-item label="“三证”未获得是否计划申请">
                <el-radio v-model="com.szwhd" label="1">是 </el-radio>
                <el-radio v-model="com.szwhd" label="2">否 </el-radio>
              </el-form-item>

              <el-form-item label="军民融合建设管理情况">
                <el-radio v-model="com.jmrhgn" label="1">内设专门机构</el-radio>
                <el-radio v-model="com.jmrhgn" label="2">设有专项经费</el-radio>
                <el-radio v-model="com.jmrhgn" label="3">有专人负责 </el-radio>
              </el-form-item>
              <el-form-item label="“民参军”过程中，遇到的障碍及困难">
                <el-radio v-model="com.ydkn" label="1">需求信息</el-radio>
                <el-radio v-model="com.ydkn" label="2">产品准入</el-radio>
                <el-radio v-model="com.ydkn" label="3">人才保障 </el-radio>
                <el-radio v-model="com.ydkn" label="4">审批程序 </el-radio>
                <el-radio v-model="com.ydkn" label="5">资质认证 </el-radio>
                <el-radio v-model="com.ydkn" label="6">无人管理 </el-radio>
                <el-radio v-model="com.ydkn" label="7">其他 </el-radio>
              </el-form-item>
              <el-form-item label="“民参军”过程中企业自身存在的问题">
                <el-radio v-model="com.problem" label="1">技术人才不足</el-radio>
                <el-radio v-model="com.problem" label="2">资金保障困难</el-radio>
                <el-radio v-model="com.problem" label="3">保密设施设备不足 </el-radio>
                <el-radio v-model="com.problem" label="4">需求信息不了解 </el-radio>
              </el-form-item>

              <el-form-item label="有参军潜力的技术成果">
                <el-input placeholder="请输入有参军潜力的技术成果" v-model="com.jscg" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="服务部门">
                <el-radio v-model="com.fwbm" label="1">海军</el-radio>
                <el-radio v-model="com.fwbm" label="2">陆军</el-radio>
                <el-radio v-model="com.fwbm" label="3">空军</el-radio>
                <el-radio v-model="com.fwbm" label="4">火箭军</el-radio>
                <el-radio v-model="com.fwbm" label="5">战略支援部队</el-radio>
                <el-radio v-model="com.fwbm" label="6">军工企业</el-radio>
              </el-form-item>

              <el-form-item label="执行情况">
                <el-radio v-model="com.zxqk" label="1">申请</el-radio>
                <el-radio v-model="com.zxqk" label="2">在研</el-radio>
                <el-radio v-model="com.zxqk" label="3">验收/结题 </el-radio>
              </el-form-item>
              <el-form-item label="可用于军民融合共享的资源情况（不超过200字）">

                <textarea v-model="com.zyqk" rows="3" cols="20" style="width:100%;height: 350px;">
                  </textarea>
              </el-form-item>

              <el-form-item label="对军民科技协同创新的相关建议（不超过200字）">
                <textarea v-model="com.xgjy" rows="3" cols="20" style="width:100%;height: 350px;">
                    </textarea>
              </el-form-item>

            </el-col>


          </el-row>
        </el-form>
      </el-tab-pane>


      <el-tab-pane label="创新项目">
        <el-form class="" label-width="40%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <div style="margin:0 auto">

                <el-button size="small" @click="addProjectcom()">
                  添加项目
                </el-button>
                <el-table class="tableH" :data="com.comPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                  <el-table-column align="center" label="项目名称">
                    <template slot-scope="scope">
                      <input  type="text" v-model="scope.row.name">
                                      </template>
                  </el-table-column>
                  <el-table-column align="center" label="起止时间">
                    <template slot-scope="scope">
                                            <span>
                                              <input  type="text" v-model="scope.row.time">
                                            </span>
                                        </template>
                  </el-table-column>
                  <el-table-column align="center" label="项目来源">
                    <template slot-scope="scope">
                      <input  type="text" v-model="scope.row.source">
                                      </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
  <div style="padding-left: 35%;margin: 40px 0  0  0;">
    <el-button type="primary" style="width: 120px;" @click="saveFile(com)">保存</el-button>
    <el-button type="primary" style="width: 120px;" @click="back">返回</el-button>
  </div>

</div>
</template>

<script>
import 'vue-area-linkage/dist/index.css'; // v2 or higher
import contrary from '@/assets/images/QQ.png'
import contrary2 from '@/assets/images/QQ.png'
import {
  pca,
  pcaa
} from "area-data";
import {
  getAllrole,
} from '@/api/role'
import {
  getUserDetail,
  setUserDetail
} from '@/api/login'
import {
  getexpert,
  addLib
} from '@/api/library'
export default {
  data() {
    return {
      userType: '3',
      dateValue: '',
      contrary: '',
      contrary2: '',
      selected: [],
      titleName: '',
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      service_amount_lastt: '',
      service_amount_beforet: '',
      service_amount_previoust: '',
      service_quantity_lastt: '',
      service_quantity_beforet: '',
      service_quantity_previoust: '',
      service_research_lastt: '',
      service_research_beforet: '',
      service_research_previoust: '',
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
      school: {
        zhengben: [],
        fuben: [],
        logo: [],
        introduction: '',
        major_platform: '',
        unit_url: '',
        zip_code: '',
        address: '',
        country: '',
        code: '',
        name: '',
      },
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
        selected: '',
        name: '',
        fdemail: '',
        fdphone: '',
        fdname: '',
        lxemail: '',
        lxphone: '',
        lxzw: '',
        linkman: ''
      },
      com: {
        zhengben: [],
        fuben: [],
        logo: [],
        lxname: '',
        lxzw: '',
        lxphone: '',
        lxemail: '',
        lpname: '',
        lpzw: '',
        lpphone: '',
        lpemail: '',
        name: '',
        code:'',
        registered_capital: '1',
        registerDate: '',
        registered_type: '',
        country: '',
        registeraddress: '',
        registerecode: '',
        is_high_new_tech: '',
        registerSite: '',
        registerHSite: '',
        registerMarket: '',
        registerMarkeSite: '',
        domain: '',
        product: '',
        inventionNum: '',
        newDrugnum: '',
        utilityModel: '',
        integratedCircuitnum: '',
        designnum: '',
        nationalNum: '',
        softwareNum: '',
        othernum: '',
        researchTeamnum: '',
        bachelorAbovenum: '',
        middleLevelnum: '',
        highleveltalentsnum: '',
        highleveltalentType: '',
        service_research_last: '',
        service_research_before: '',
        service_research_previous: '',
        platform: '',
        sfkyrw: '',
        qdtime: '',
        szwhd: '',
        jmrhgn: '',
        ydkn: '',
        problem: '',
        jscg: '',
        zyqk: '',
        xgjy: '',
        fwbm: '',
        zxqk: '',
        comPorcolumnDefinitions: [{
          name: '',
          time: '',
          source: ''
        }],
      },
    }
  },
  async mounted() {
    this.listLoading = false
    if (this.$route.params.objData) {
      this.com = JSON.parse(this.$route.params.objData)
    }
  },
  computed: {},
  methods: {
    back() {
      window.history.go(-1);
    },

    async saveFile() {
      // if (!this.validata.validaRole(obj)) return
      let arr = {}
      arr.formType = '2'
      arr.id = this.$route.params.objId
      arr.detail = JSON.stringify(this.com)
      debugger
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
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
      }
    },


    async handleEdit(data, type) {
      if (type === 'edit') {
        this.obj = data
        this.dialogStatus = 'update'
        this.dialogsave = true
        this.dialogadd = false
        this.dialogFormVisible = true
      } else if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await delRole(data.id)
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
      }
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
    addProjectcom() {
      this.com.comPorcolumnDefinitions.push({
        name: '',
        time: '',
        source: ''
      })
    },
    addProjectmech() {
      this.mech.PorcolumnDefinitions.push({
        name: '',
        time: '',
        source: ''
      })
    },
    addhonrmech() {
      this.mech.honor.push({
        name: '',
        time: '',
        issuingAgency: ''
      })
    },
    addcjmech() {
      this.mech.workrPorcolumnDefinitions.push({
        name: '',
        object: '',
        time: ''
      })
    },
  }
}
</script>

<style lang="scss">
@import '../../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>
.tab-container {
  padding: 30px;
  overflow: auto;
}

.tools {
  height: 5%
}

.area-select .area-selected-trigger {
  position: relative;
  display: block;
  font-size: 14px;
  cursor: pointer;
  margin: 0;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  height: 100%;
  padding: 0px 0px 0px 12px;
}
</style>
