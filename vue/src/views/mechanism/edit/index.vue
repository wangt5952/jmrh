<template>
<div class="tab-container">
  <div class="" style="padding-left:35%;margin: 0 0 40px 0;">
    <h2>{{titleName}}</h2>
  </div>




  <div class="">
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 机构联系人</span>
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="姓名">
                <el-input placeholder="请输入姓名" v-model="mech.linkman" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职务">
                <el-input placeholder="请输入职务" v-model="mech.lxzw" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <el-input placeholder="请输入手机号" v-model="mech.lxphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input placeholder="请输入邮箱" v-model="mech.lxemail" style="width:80%"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>

      </el-tab-pane>
      <el-tab-pane label="机构法定代表人">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="姓名">
                <el-input placeholder="请输入姓名" v-model="mech.fdname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <el-input placeholder="请输入手机号" v-model="mech.fdphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <el-input placeholder="请输入邮箱" v-model="mech.fdemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="事业单位法人证书正本">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="mech.zhengben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="事业单位法人证书副本">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="mech.fuben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="机构logo">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="mech.logo" list-type="picture">
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
      <el-tab-pane label="机构信息">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="单位名称">
                <el-input placeholder="请输入单位名称" v-model="mech.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="所在地区">
                <area-cascader :level="1" v-model="mech.selected" :data="pcaa"></area-cascader>
                <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
              </el-form-item>
              <el-form-item label="联系地址">
                <el-input placeholder="请输入联系地址" v-model="mech.address" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮编">
                <el-input placeholder="请输入邮编" v-model="mech.ecode" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="机构性质">
                <el-radio v-model="mech.registerNature" label="1">企业</el-radio>
                <el-radio v-model="mech.registerNature" label="2">科研院所</el-radio>
                <el-radio v-model="mech.registerNature" label="3">高等院校 </el-radio>
                <el-radio v-model="mech.registerNature" label="4">其他</el-radio>
              </el-form-item>

              <el-form-item label="机构类别">
                <el-radio v-model="mech.org_type" label="1">研究开发</el-radio>
                <el-radio v-model="mech.org_type" label="2">科技投融资</el-radio>
                <el-radio v-model="mech.org_type" label="3">技术转移 </el-radio>
                <el-radio v-model="mech.org_type" label="4">检验检测</el-radio>
                <el-radio v-model="mech.org_type" label="5">创业孵化</el-radio>
                <el-radio v-model="mech.org_type" label="6">知识产权</el-radio>
                <el-radio v-model="mech.org_type" label="7">科技评估</el-radio>
                <el-radio v-model="mech.org_type" label="8">标准认证</el-radio>
                <el-radio v-model="mech.org_type" label="9">管理咨询</el-radio>
                <el-radio v-model="mech.org_type" label="10">综合科技服务</el-radio>
                <el-radio v-model="mech.org_type" label="11">其他</el-radio>
              </el-form-item>

              <el-form-item label="可提供的服务简介（200字以内">

                <textarea rows="3" v-model="mech.serviceAbout" cols="20" style="width:100%;height: 250px;">
                  </textarea>
              </el-form-item>

              <el-form-item :label="service_amount_lastt">
                <el-input placeholder="请输入服务收入" v-model="mech.service_amount_last" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_amount_beforet">
                <el-input placeholder="请输入服务收入" v-model="mech.service_amount_before" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_amount_previoust">
                <el-input placeholder="请输入服务收入" v-model="mech.service_amount_previous" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item :label="service_quantity_lastt">
                <el-input placeholder="请输入服务数量" v-model="mech.service_quantity_last" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_quantity_beforet">
                <el-input placeholder="请输入服务数量" v-model="mech.service_quantity_before" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_quantity_previoust">
                <el-input placeholder="请输入服务数量" v-model="mech.service_quantity_previous" style="width:80%"></el-input>
              </el-form-item>
            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="人才队伍情况">

        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="人员总数">
                <el-input v-model="mech.perNum" placeholder="请输入人员总数" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="本科及以上学历人员占比">
                <el-input v-model="mech.underPer" placeholder="请输入人员本科及以上学历人员占比" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="中高级职称以上人员占比">
                <el-input v-model="mech.MHPer" placeholder="请输入中高级职称以上人员占比编" style="width:80%"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

      </el-tab-pane>

      <el-tab-pane label="项目情况">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="资助方式">
                <el-radio v-model="mech.registerSupport" label="1">拨款</el-radio>
                <el-radio v-model="mech.registerSupport" label="2">贴息</el-radio>
                <el-radio v-model="mech.registerSupport" label="3">减免税 </el-radio>
                <el-radio v-model="mech.registerSupport" label="4">其他</el-radio>
                <el-radio v-model="mech.registerSupport" label="5">以上全无</el-radio>
              </el-form-item>
              <el-form-item label="执行情况">
                <el-radio v-model="mech.registerImplement" label="1">申请</el-radio>
                <el-radio v-model="mech.registerImplement" label="2">在研</el-radio>
                <el-radio v-model="mech.registerImplement" label="3">验收/结题 </el-radio>
              </el-form-item>


            </el-col>
          </el-row>
          <!-- <el-form-item label=""> -->

          <div style="margin:0 auto">

            <el-button size="small" @click="addProjectmech()">
              添加项目
            </el-button>
            <el-table class="tableH" :data="mech.PorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

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
          <!-- </el-form-item> -->

        </el-form>
      </el-tab-pane>
      <el-tab-pane label="所获资质及荣誉">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="22">
            <!-- <el-form-item label=""> -->

            <div style="margin:0 auto">

              <el-button size="small" @click="addhonrmech()">
                添加荣誉
              </el-button>
              <el-table class="tableH" :data="mech.honor" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                <el-table-column align="center" label="名称">
                  <template slot-scope="scope">
                    <input  type="text" v-model="scope.row.name">
                                    </template>
                </el-table-column>
                <el-table-column align="center" label="获得时间">
                  <template slot-scope="scope">
                                          <span>
                                            <input  type="text" v-model="scope.row.time">
                                          </span>
                                      </template>
                </el-table-column>
                <el-table-column align="center" label="颁发机构">
                  <template slot-scope="scope">
                    <input  type="text" v-model="scope.row.issuingAgency">
                                    </template>
                </el-table-column>
              </el-table>
            </div>
            <!-- </el-form-item> -->

          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="主要工作成效">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="22">
            <!-- <el-form-item label=""> -->

            <div style="margin:0 auto">

              <el-button size="small" @click="addcjmech()">
                添加成效
              </el-button>
              <el-table class="tableH" :data="mech.workrPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                <el-table-column align="center" label="服务项目名称">
                  <template slot-scope="scope">
                    <input  type="text" v-model="scope.row.name">
                                    </template>
                </el-table-column>
                <el-table-column align="center" label="服务对象">
                  <template slot-scope="scope">
                                          <span>
                                            <input  type="text" v-model="scope.row.object">
                                          </span>
                                      </template>
                </el-table-column>
                <el-table-column align="center" label="服务时间">
                  <template slot-scope="scope">
                    <input  type="text" v-model="scope.row.time">
                                    </template>
                </el-table-column>
              </el-table>
            </div>
            <!-- </el-form-item> -->
          </el-row>
        </el-form>
      </el-tab-pane>
    </el-tabs>
    <el-row>
      <div style="padding-left: 35%;margin: 40px 0  0  0;">
        <el-button type="primary" style="width: 120px;" @click="saveFile(mech)">保存</el-button>
      </div>
    </el-row>
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
        org_code: '',
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
        enterprise_name: '',
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
    if (window.sessionStorage.getItem('userType')) {
      this.userType = window.sessionStorage.getItem('userType')
    }
    if (this.userType == '1') {
      this.titleName = '专家资料完善'
    } else if (this.userType == '2') {

      this.titleName = '企业资料完善'
    } else if (this.userType == '3') {
      this.titleName = '服务机构资料完善'
    } else if (this.userType == '4') {
      this.titleName = '高效院所资料完善'
    }
    this.loadPageList()
  },
  computed: {},
  methods: {
    async loadPageList() {
      let {
        data,
        success
      } = await getUserDetail()
      if (this.userType == '1') {
        this.expert = JSON.parse(data.detail)
      } else if (this.userType == '2') {
        this.com = JSON.parse(data.detail)
      } else if (this.userType == '3') {
        this.mech = JSON.parse(data.detail)
      } else if (this.userType == '4') {
        this.school = JSON.parse(data.detail)
      }
      this.loading = false
    },

    async saveFile(obj) {
      // if (!this.validata.validaRole(obj)) return

      let arr = []
      obj.method = 'post'
      let {
        data,
        success
      } = await setUserDetail(obj)

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
