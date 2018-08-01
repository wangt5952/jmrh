<template>
<div class="tab-container">
  <div class="" style="height: 80% !important;overflow-y: auto;">

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
  </div>
  <el-row>
    <div style="padding-left: 35%;margin: 40px 0  0  0;">
      <el-button type="primary" style="width: 120px;" @click="saveFile(expert)">保存</el-button>
      <el-button type="primary" style="width: 120px;" @click="back">返回</el-button>
    </div>
  </el-row>



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
      titleName: '专家信息填写',
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
    if (this.$route.params.objData) {
      debugger
      this.expert = JSON.parse(this.$route.params.objData)
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
      arr.formType = '1'
      arr.detail = JSON.stringify(this.expert)
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
          let del = await addLib(data)
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
