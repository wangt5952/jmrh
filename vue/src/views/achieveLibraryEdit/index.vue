<template>
<div class="tab-container">
  <div class="" style="height: 80% !important;overflow-y: auto;">

    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 基础信息</span>
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="技术成果名称">
                <span style='position: absolute;left: -110px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入技术成果名称，不能超过20个字符！"  maxlength="20" v-model="achieveLibrary.re_name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="企业名称">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入企业名称，不能超过20个字符！"  maxlength="20" v-model="achieveLibrary.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="统一社会信用代码">
                <span style='position: absolute;left: -135px;color: #f60d0d;'>*</span>
                <el-input :disabled=codeD placeholder="请输入统一社会信用代码，不能超过20个字符！"  maxlength="20" v-model="achieveLibrary.code" style="width:80%"></el-input>
              </el-form-item>


              <el-form-item label="营业执照">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile0" :file-list="achieveLibrary.picOrgLicense" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="事业单位法人证书">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile1" :file-list="achieveLibrary.picLpLicense" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="联系人身份证正面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile2" :file-list="achieveLibrary.picLpIdCardFront" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="联系人身份证反面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile22" :file-list="achieveLibrary.picLpIdCardBack" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>

              <el-form-item label="手持身份证">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFilepicLpIdCardInHand" :file-list="achieveLibrary.picLpIdCardInHand" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>

              <el-form-item label="承诺书上传(图片)">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload :http-request="uploadSectionFile1c" class="upload-demo" :file-list="achieveLibrary.picCommitmentLetter" list-type="picture" style="float:left">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>
                    <div style="color:#63a9f1;float:left;margin-left:20px">
                      <a :href="docUrl+'/xtcx/lib/download?fileName=3-信息报送承诺书.doc'" target="_blank">承诺书下载</a>
                    </div>
                  </div>
                </div>
              </el-form-item>
              <el-form-item label="所在地区">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <area-cascader :level="1" v-model="achieveLibrary.country" :data="pcaa"></area-cascader>
              </el-form-item>
              <el-form-item label="通讯地址">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入通讯地址，不能超过20个字符！"  maxlength="20" v-model="achieveLibrary.address" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="负责人">
                <span style='position: absolute;left: -65px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入项目负责人，不能超过20个字符！"  maxlength="20" v-model="achieveLibrary.fzname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入电话" v-model="achieveLibrary.fzphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="achieveLibrary.fzemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="联系人">
                <span style='position: absolute;left: -65px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入项目联系人，不能超过20个字符！"  maxlength="20" v-model="achieveLibrary.fzname2" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入电话" v-model="achieveLibrary.fzphone2" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="achieveLibrary.fzemail2" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="所属领域">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="achieveLibrary.domain">
                  <el-checkbox label="1">智能装备</el-checkbox>
                  <el-checkbox label="2">电子信息</el-checkbox>
                  <el-checkbox label="3">新材料</el-checkbox>
                  <el-checkbox label="4">航空航天</el-checkbox>
                  <el-checkbox label="5">生物技术与新医药</el-checkbox>
                  <el-checkbox label="6">能源与环保</el-checkbox>
                  <el-checkbox label="7">其他</el-checkbox>
                  <el-input v-if="achieveLibrary.domain.includes('7')" placeholder="请输入其他，不能超过20个字符！"  maxlength="20" v-model="achieveLibrary.domainOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="所处阶段">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="achieveLibrary.stage">
                  <el-checkbox label="1">实验室阶段</el-checkbox>
                  <el-checkbox label="2">试制阶段</el-checkbox>
                  <el-checkbox label="3">产业化阶段</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="合作方式">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="achieveLibrary.cooperation">
                  <el-checkbox label="1">技术转让</el-checkbox>
                  <el-checkbox label="2">技术许可</el-checkbox>
                  <el-checkbox label="3">技术开发</el-checkbox>
                  <el-checkbox label="4">技术服务</el-checkbox>
                  <el-checkbox label="5">技术入股</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>

      </el-tab-pane>

      <el-tab-pane label="技术成果简介">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="24">
            <el-col :span="24">

              <el-form-item label="技术成果简介">
                <span style='position: absolute;left: -110px;color: #f60d0d;'></span>
                <textarea placeholder="不能超过200个字符！" maxlength="200" v-model="achieveLibrary.resDesc" rows="3" cols="20" style="width:60%;height: 150px;">
                </textarea>
              </el-form-item>

              <el-form-item label="可应用领域">
                <span style='position: absolute;left: -90px;color: #f60d0d;'></span>
                <el-input placeholder="请输入可应用领域，不能超过20个字符！"  maxlength="20" v-model="achieveLibrary.applyLY" style="width:80%"></el-input>
              </el-form-item>

            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="技术创新点">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="24">
            <el-col :span="24">
              <el-form-item label="技术创新点">
                <span style='position: absolute;left: -90px;color: #f60d0d;'></span>
                <textarea placeholder="不能超过200个字符！" maxlength="200" v-model="achieveLibrary.newIdea" rows="3" cols="20" style="width:60%;height: 150px;">
                </textarea>
              </el-form-item>

            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="知识产权数量">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="24">
            <el-form-item label="申请专利数量">
              <el-input placeholder="请输入申请专利数量" v-model="achieveLibrary.sqPatent" style="width:80%"  type="number"></el-input>
            </el-form-item>
            <el-form-item label="获授权专利数量">
              <el-input placeholder="请输入获授权专利数量" v-model="achieveLibrary.havePatent" style="width:80%"  type="number"></el-input>
            </el-form-item>
            <el-form-item label="其他知识产权数量">
              <el-input placeholder="请输入其他知识产权数量" v-model="achieveLibrary.otherPatent" style="width:80%"  type="number"></el-input>
            </el-form-item>
          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="知识产权情况">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="22">
            <!-- <el-form-item label=""> -->

            <div style="margin:0 auto">

              <el-button size="small" @click="addProjectexpert()">
                添加专利
              </el-button>
              <el-table class="tableH" :data="achieveLibrary.patents" border style="margin-top:20px;width:100%;font-size:14px;overflow-y:auto">

                <el-table-column align="center" label="专利名称">
                  <template slot-scope="scope">
                  <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="scope.row.projectname" style="width: 50%;"></el-input>
                                  </template>
                </el-table-column>
                <el-table-column align="center" label="专利类型">
                  <template slot-scope="scope">
                                        <span>
                                          <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="scope.row.projecType" style="width: 50%;"></el-input>
                                        </span>
                                    </template>
                </el-table-column>
                <el-table-column align="center" label="专利号">
                  <template slot-scope="scope">
                  <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="scope.row.projectNum" style="width: 50%;"></el-input>
                                  </template>
                </el-table-column>
                <el-table-column align="center" label="专利权人">
                  <template slot-scope="scope">

                    <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="scope.row.projectPer" style="width: 50%;"></el-input>
                                  </template>
                </el-table-column>

                <el-table-column align="center" label="有效期" style="width: 50%;">
                  <template slot-scope="scope">
                                      <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="scope.row.projectTime" style="width: 50%;"></el-input>
                                  </template>
                </el-table-column>
                <el-table-column align="center" label="" style="width: 50%;">
                  <template slot-scope="scope">
                    <el-button size="small" @click="delProjectcom(scope.row)" style="color:red">
                      删除项目
                    </el-button>
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
      <div class="" style="padding:15px">
        <el-radio-group v-model="checkStatus">
          <el-radio :label="-1">草稿</el-radio>
          <el-radio :label="0">提交待审</el-radio>
          <el-radio v-show="userType == '0' " :label="1">直接审核</el-radio>
        </el-radio-group>
      </div>
      <div class="">
        <el-button type="primary" style="width: 120px;" @click="saveFile(checkStatus)">保存</el-button>
        <el-button type="primary" style="width: 120px;" @click="back">返回</el-button>
      </div>

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
  setUserDetail,
  isInLibs
} from '@/api/login'
import {
  getresults,
  addpublish,
  downloadfileName,
  libupload,
} from '@/api/library'
export default {
  data() {
    return {
      codeD: false,
      userType: '',
      dateValue: '',
      contrary: '',
      contrary2: '',
      selected: [],
      titleName: '专家信息填写',
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      arrValue1: '',
      arrValue2: '',
      arrValue3: '',
      service_amount_lastt: '',
      service_amount_beforet: '',
      service_amount_previoust: '',
      service_quantity_lastt: '',
      service_quantity_beforet: '',
      service_quantity_previoust: '',
      service_research_lastt: '',
      service_research_beforet: '',
      service_research_previoust: '',
      achieveLibrary: {
        fzname: '',
        fzphone: '',
        picCommitmentLetter: [{
          url: this.imgBaseUrl + `def/commitment.png`
        }],
        picOrgLicense: [{
          url: this.imgBaseUrl + `def/companyZZ.png`
        }],
        picLpLicense: [{
          url: this.imgBaseUrl + `def/companyZS.png`
        }],
        picLpIdCardFront: [{
          url: this.imgBaseUrl + `def/idfront.png`
        }],
        picLpIdCardBack: [{
          url: this.imgBaseUrl + `def/idback.png`
        }],
        picLpIdCardInHand: [{
          url: this.imgBaseUrl + `def/handPhoto.jpg`
        }],
        fzemail: '',
        fzname2: '',
        fzphone2: '',
        fzemail2: '',
        re_name: '',
        code: '',
        name: '',
        address: '',
        country: '',
        resDesc: '',
        applyLY: '',
        newIdea: '',
        sqPatent: '',
        havePatent: '',
        otherPatent: '',
        cooperation: [],
        cooperationOther: '',
        domain: [],
        domainOther: '',
        stage: [],
        patents: [{
          projectname: '',
          projecType: '',
          projectNum: '',
          projectPer: '',
          projectTime: '',
        }],
        search_param: []

      },
      checkStatus: 0
    }
  },
  async mounted() {
    this.listLoading = false
    if (this.$route.params.objData) {
      this.achieveLibrary = JSON.parse(this.$route.params.objData)
      this.achieveLibrary.picOrgLicense = [{
        url: this.imgBaseUrl + this.achieveLibrary.picOrgLicense
      }]
      this.achieveLibrary.picLpLicense = [{
        url: this.imgBaseUrl + this.achieveLibrary.picLpLicense
      }]
      this.achieveLibrary.picLpIdCardFront = [{
        url: this.imgBaseUrl + this.achieveLibrary.picLpIdCardFront
      }]
      this.achieveLibrary.picLpIdCardBack = [{
        url: this.imgBaseUrl + this.achieveLibrary.picLpIdCardBack
      }]
      this.achieveLibrary.picLpIdCardInHand = [{
        url: this.imgBaseUrl + this.achieveLibrary.picLpIdCardInHand
      }]
      this.achieveLibrary.picCommitmentLetter = [{
        url: this.imgBaseUrl + this.achieveLibrary.picCommitmentLetter
      }]
      if (!this.achieveLibrary.picCommitmentLetter) {
        this.achieveLibrary.picCommitmentLetter = []
      }


    }

    this.userType = window.sessionStorage.getItem('userType')
    if (this.userType == '0' || this.userType == '101') {
      this.checkStatus = 1
      if (this.achieveLibrary.code != "") this.codeD = true
    } else {
      this.checkStatus = 0

      let fromCheckStatus = window.sessionStorage.getItem('checkStatus')
      if (fromCheckStatus == '2') {
        let {
          data,
          success
        } = await getUserDetail()
        let detail = JSON.parse(data.detail)
        this.achieveLibrary.code = detail.code
        this.achieveLibrary.name = detail.name
        this.achieveLibrary.country = detail.country
        if (this.achieveLibrary.code != "") this.codeD = true
        this.achieveLibrary.picOrgLicense[0].url = this.imgBaseUrl + detail.picOrgLicense[0].url
        this.achieveLibrary.picLpLicense[0].url = this.imgBaseUrl + detail.picLpLicense[0].url
        this.achieveLibrary.picLpIdCardFront[0].url = this.imgBaseUrl + detail.picLpIdCardFront[0].url
        this.achieveLibrary.picLpIdCardBack[0].url = this.imgBaseUrl + detail.picLpIdCardBack[0].url
        this.achieveLibrary.picLpIdCardInHand[0].url = this.imgBaseUrl + detail.picLpIdCardInHand[0].url


      } else {

        let {
          data,
          success
        } = await isInLibs()
        if (success) {
          if (data == null) {

            return
          } else {

            let detail = JSON.parse(data.detail)
            this.achieveLibrary.code = detail.code
            this.achieveLibrary.name = detail.name
            this.achieveLibrary.country = detail.country

            if (this.achieveLibrary.code != "") this.codeD = true
            this.achieveLibrary.picOrgLicense[0].url = this.imgBaseUrl + detail.picOrgLicense[0].url
            this.achieveLibrary.picLpLicense[0].url = this.imgBaseUrl + detail.picLpLicense[0].url
            this.achieveLibrary.picLpIdCardFront[0].url = this.imgBaseUrl + detail.picLpIdCardFront[0].url
            this.achieveLibrary.picLpIdCardBack[0].url = this.imgBaseUrl + detail.picLpIdCardBack[0].url
            this.achieveLibrary.picLpIdCardInHand[0].url = this.imgBaseUrl + detail.picLpIdCardInHand[0].url

          }
        }

      }


    }
  },
  computed: {},
  methods: {
    async uploadSectionFile1c(param) {
      this.achieveLibrary.picCommitmentLetter = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `user/` + data
        this.achieveLibrary.picCommitmentLetter.push(arro)
      }
    },
    async uploadSectionFile0(param) {
      this.achieveLibrary.picOrgLicense = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `user/` + data
        this.achieveLibrary.picOrgLicense.push(arro)
      }
    },
    async uploadSectionFile1(param) {
      this.achieveLibrary.picLpLicense = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `user/` + data
        this.achieveLibrary.picLpLicense.push(arro)
      }
    },

    async uploadSectionFile2(param) {
      this.achieveLibrary.picLpIdCardFront = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `user/` + data
        this.achieveLibrary.picLpIdCardFront.push(arro)
      }
    },
    async uploadSectionFile22(param) {
      this.achieveLibrary.picLpIdCardBack = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `user/` + data
        this.achieveLibrary.picLpIdCardBack.push(arro)
      }
    },

    async uploadSectionFilepicLpIdCardInHand(param) {
      this.achieveLibrary.picLpIdCardInHand = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `user/` + data
        this.achieveLibrary.picLpIdCardInHand.push(arro)
      }
    },
    async getdownloadfile() {

      let {
        data,
        success
      } = await downloadfileName()


    },
    back() {
      window.history.go(-1);
    },

    addCN(data) {

      if (JSON.stringify(data.domain).includes(1)) data.search_param.push('智能装备')
      if (JSON.stringify(data.domain).includes(2)) data.search_param.push('电子信息')
      if (JSON.stringify(data.domain).includes(3)) data.search_param.push('新材料')
      if (JSON.stringify(data.domain).includes(4)) data.search_param.push('航空航天')
      if (JSON.stringify(data.domain).includes(5)) data.search_param.push('生物技术与新医药')
      if (JSON.stringify(data.domain).includes(6)) data.search_param.push('能源与环保')

      if (JSON.stringify(data.stage).includes(1)) data.search_param.push('实验室阶段')
      if (JSON.stringify(data.stage).includes(2)) data.search_param.push('试制阶段')
      if (JSON.stringify(data.stage).includes(3)) data.search_param.push('产业化阶段')

      if (JSON.stringify(data.cooperation).includes(1)) data.search_param.push('技术转让')
      if (JSON.stringify(data.cooperation).includes(2)) data.search_param.push('技术许可')
      if (JSON.stringify(data.cooperation).includes(3)) data.search_param.push('技术开发')
      if (JSON.stringify(data.cooperation).includes(4)) data.search_param.push('技术服务')
      if (JSON.stringify(data.cooperation).includes(5)) data.search_param.push('技术入股')

      if (data.country.length > 0) {
        this.loadOneTree(data.country[0])
        this.loadtwoTree(data.country[0], data.country[1])
        this.loadThreeTree(data.country[1], data.country[2])
        data.search_param.push(this.arrValue1)
        data.search_param.push(this.arrValue2)
        data.search_param.push(this.arrValue3)
      }
      data.search_param.push(JSON.stringify(data))
      return data
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
    async saveFile(checkStatus) {
      if (!this.validata.validaAchieveLibrary(this.achieveLibrary)) return

      let picOrgLicense = this.achieveLibrary.picOrgLicense[0].url
      if (picOrgLicense.indexOf('/user/') > -1) {
        this.achieveLibrary.picOrgLicense[0].url = picOrgLicense.substring(picOrgLicense.indexOf('/user/') + 1, picOrgLicense.length)
      } else {
        this.achieveLibrary.picOrgLicense[0].url = picOrgLicense.substring(picOrgLicense.indexOf('/def/') + 1, picOrgLicense.length)
      }
      let picLpLicense = this.achieveLibrary.picLpLicense[0].url
      if (picLpLicense.indexOf('/user/') > -1) {
        this.achieveLibrary.picLpLicense[0].url = picLpLicense.substring(picLpLicense.indexOf('/user/') + 1, picLpLicense.length)
      } else {
        this.achieveLibrary.picLpLicense[0].url = picLpLicense.substring(picLpLicense.indexOf('/def/') + 1, picLpLicense.length)
      }
      let picLpIdCardFront = this.achieveLibrary.picLpIdCardFront[0].url
      if (picLpIdCardFront.indexOf('/user/') > -1) {
        this.achieveLibrary.picLpIdCardFront[0].url = picLpIdCardFront.substring(picLpIdCardFront.indexOf('/user/') + 1, picLpIdCardFront.length)
      } else {
        this.achieveLibrary.picLpIdCardFront[0].url = picLpIdCardFront.substring(picLpIdCardFront.indexOf('/def/') + 1, picLpIdCardFront.length)
      }

      let picLpIdCardBack = this.achieveLibrary.picLpIdCardBack[0].url
      if (picLpIdCardBack.indexOf('/user/') > -1) {
        this.achieveLibrary.picLpIdCardBack[0].url = picLpIdCardBack.substring(picLpIdCardBack.indexOf('/user/') + 1, picLpIdCardBack.length)
      } else {
        this.achieveLibrary.picLpIdCardBack[0].url = picLpIdCardBack.substring(picLpIdCardBack.indexOf('/def/') + 1, picLpIdCardBack.length)
      }

      let picLpIdCardInHand = this.achieveLibrary.picLpIdCardInHand[0].url
      if (picLpIdCardInHand.indexOf('/user/') > -1) {
        this.achieveLibrary.picLpIdCardInHand[0].url = picLpIdCardInHand.substring(picLpIdCardInHand.indexOf('/user/') + 1, picLpIdCardInHand.length)
      } else {
        this.achieveLibrary.picLpIdCardInHand[0].url = picLpIdCardInHand.substring(picLpIdCardInHand.indexOf('/def/') + 1, picLpIdCardInHand.length)
      }

      let picCommitmentLetter = this.achieveLibrary.picCommitmentLetter[0].url
      if (picCommitmentLetter.indexOf('/user/') > -1) {
        this.achieveLibrary.picCommitmentLetter[0].url = picCommitmentLetter.substring(picCommitmentLetter.indexOf('/user/') + 1, picCommitmentLetter.length)
      } else {
        this.achieveLibrary.picCommitmentLetter[0].url = picCommitmentLetter.substring(picCommitmentLetter.indexOf('/def/') + 1, picCommitmentLetter.length)
      }

      let arr = {}
      arr.formType = '6'
      arr.checkStatus = checkStatus
      arr.id = this.$route.params.objId
      this.achieveLibrary.search_param = []
      this.achieveLibrary = this.addCN(this.achieveLibrary)
      arr.detail = JSON.stringify(this.achieveLibrary)
      arr.number = this.$route.params.number
      arr.userId =  window.sessionStorage.getItem('userId')

      let {
        data,
        success
      } = await addpublish(arr)

      if (success) {
        this.$message({
          message: '保存成功',
          type: 'success'
        });
        this.achieveLibrary.picOrgLicense[0].url = this.imgBaseUrl + this.achieveLibrary.picOrgLicense[0].url
        this.achieveLibrary.picLpLicense[0].url = this.imgBaseUrl + this.achieveLibrary.picLpLicense[0].url
        this.achieveLibrary.picLpIdCardFront[0].url = this.imgBaseUrl + this.achieveLibrary.picLpIdCardFront[0].url
        this.achieveLibrary.picLpIdCardBack[0].url = this.imgBaseUrl + this.achieveLibrary.picLpIdCardBack[0].url
        this.achieveLibrary.picLpIdCardInHand[0].url = this.imgBaseUrl + this.achieveLibrary.picLpIdCardInHand[0].url
        this.achieveLibrary.picCommitmentLetter[0].url = this.imgBaseUrl + this.achieveLibrary.picCommitmentLetter[0].url
          window.history.go(-1);
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
          let del = await addpublish(data)
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
      this.achieveLibrary.patents.push({
        projectname: '',
        projecType: '',
        projectNum: '',
        projectPer: '',
        projectTime: '',
      })
    },

    delProjectcom(item) {
      this.achieveLibrary.patents.splice(this.achieveLibrary.patents.indexOf(item), 1)
      this.$message({
        type: 'info',
        message: '删除成功'
      });
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
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>
.tools {
  height: 5%
}

</style>
