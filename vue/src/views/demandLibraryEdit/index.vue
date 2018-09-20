<template>
<div class="tab-container">
  <div class="" style="height: 80% !important;overflow-y: auto;">

    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 基础信息</span>
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="技术需求名称">
                <span style='position: absolute;left: -110px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入技术需求名称" v-model="demandLibrary.re_name" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="需求单位名称">
                <span style='position: absolute;left: -110px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入需求单位名称" v-model="demandLibrary.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="统一社会信用代码">
                <span style='position: absolute;left: -130px;color: #f60d0d;'>*</span>
                <el-input disabled="codeD" placeholder="请输入统一社会信用代码" v-model="demandLibrary.code" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="营业执照">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile0" :file-list="demandLibrary.picOrgLicense" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="事业单位法人证书">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile1" :file-list="demandLibrary.picLpLicense" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="联系人身份证正面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile2" :file-list="demandLibrary.picLmIdCardFront" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="联系人身份证反面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile22" :file-list="demandLibrary.picLmIdCardBack" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>

              <el-form-item label="手持身份证">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFilepicLmIdCardInHand" :file-list="demandLibrary.picLmIdCardInHand" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>

              <el-form-item label="承诺书上传(图片)">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload :http-request="uploadSectionFile1c" class="upload-demo" :file-list="demandLibrary.picCommitmentLetter" list-type="picture" style="float:left">
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
                <area-cascader :level="1" v-model="demandLibrary.country" :data="pcaa"></area-cascader>
              </el-form-item>
              <el-form-item label="通讯地址">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入通讯地址" v-model="demandLibrary.address" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="负责人">
                <span style='position: absolute;left: -70px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入负责人" v-model="demandLibrary.fzname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职务">
                <span style='position: absolute;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入职务" v-model="demandLibrary.fzzw" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入电话" v-model="demandLibrary.fzphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="demandLibrary.fzemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="联系人">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入联系人" v-model="demandLibrary.fzname2" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职务">
                <span style='position: absolute;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入职务" v-model="demandLibrary.fzzw2" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入电话" v-model="demandLibrary.fzphone2" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="demandLibrary.fzemail2" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="所属领域">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="demandLibrary.domain">
                  <el-checkbox label="1">智能装备</el-checkbox>
                  <el-checkbox label="2">电子信息</el-checkbox>
                  <el-checkbox label="3">新材料</el-checkbox>
                  <el-checkbox label="4">航空航天</el-checkbox>
                  <el-checkbox label="5">生物技术与新医药</el-checkbox>
                  <el-checkbox label="6">能源与环保</el-checkbox>
                  <el-checkbox label="99">其他</el-checkbox>
                  <el-input v-if="demandLibrary.domain.includes('99')" placeholder="请输入其他" v-model="demandLibrary.domainOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="需求背景">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="demandLibrary.reqBack">
                  <el-checkbox label="1">新产品开发</el-checkbox>
                  <el-checkbox label="2">制造工艺改进</el-checkbox>
                  <el-checkbox label="3">产品升级换代</el-checkbox>
                  <el-checkbox label="4">制造设备改进</el-checkbox>
                  <el-checkbox label="5">生产线技术改造</el-checkbox>
                  <el-checkbox label="6">其他</el-checkbox>
                  <el-input v-if="demandLibrary.reqBack.includes('6')" placeholder="请输入其他" v-model="demandLibrary.reqBackOther" style="width:80%"></el-input>

                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="意向获得方式">
                <span style='position: absolute;left: -110px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="demandLibrary.reqGet">
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

      <el-tab-pane label="希望解决的技术需求概述">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="24">
            <el-col :span="24">

              <el-form-item label="希望解决的技术需求概述">
                <span style='position: absolute;left: -180px;color: #f60d0d;'></span>
                <textarea v-model="demandLibrary.reqDesc" rows="3" cols="20" style="width:60%;height: 120px;">
                </textarea>
              </el-form-item>

            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="要求达到的技术性能、参数指标等">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="24">
            <el-col :span="24">
              <el-form-item label="要求达到的技术性能、参数指标等">
                <textarea v-model="demandLibrary.reqNew" rows="3" cols="20" style="width:60%;height: 120px;">
                </textarea>
              </el-form-item>

            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="其他相关要求">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="24">
            <el-col :span="24">
              <el-form-item label="其他相关要求">
                <textarea v-model="demandLibrary.reqOther" rows="3" cols="20" style="width:60%;height: 150px;">
                </textarea>
              </el-form-item>

            </el-col>

            <el-col :span="10">


            </el-col>

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
          <el-radio v-show="userType == '0' ||  userType =='101'" :label="1">直接审核</el-radio>
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
  getrequirement,
  addpublish,
  downloadfileName,
  libupload
} from '@/api/library'
export default {
  data() {
    return {
      codeD: false,
      userType: '3',
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
      demandLibrary: {
        re_name: '',
        code: '',
        name: '',
        picCommitmentLetter: [{
          name: '默认',
          url: this.imgBaseUrl + `def/commitment.png`
        }],
        picOrgLicense: [{
          name: '默认',
          url: this.imgBaseUrl + `def/companyZZ.png`
        }],
        picLpLicense: [{
          name: '默认',
          url: this.imgBaseUrl + `def/companyZS.png`
        }],
        picLmIdCardFront: [{
          name: '默认',
          url: this.imgBaseUrl + `def/idfront.png`
        }],
        picLmIdCardBack: [{
          name: '默认',
          url: this.imgBaseUrl + `def/idback.png`
        }],
        picLmIdCardInHand: [{
          name: '默认',
          url: this.imgBaseUrl + `def/handPhoto.jpg`
        }],
        fzname: '',
        fzzw: '',
        fzphone: '',
        fzemail: '',
        fzname2: '',
        fzzw2: '',
        fzphone2: '',
        fzemail2: '',
        domain: [],
        domainOther: '',
        address: '',
        country: '',
        reqDesc: '',
        reqNew: '',
        reqOther: '',
        reqGet: [],
        reqBack: [],
        reqBackOther: '',
        search_param: []
      },
      checkStatus: 1
    }
  },
  async mounted() {
    this.listLoading = false
    if (this.$route.params.objData) {
      this.demandLibrary = JSON.parse(this.$route.params.objData)
      this.demandLibrary.picOrgLicense[0].url = this.imgBaseUrl +  this.demandLibrary.picOrgLicense[0].url
      this.demandLibrary.picLpLicense[0].url = this.imgBaseUrl +  this.demandLibrary.picLpLicense[0].url
      this.demandLibrary.picLmIdCardFront[0].url = this.imgBaseUrl +  this.demandLibrary.picLmIdCardFront[0].url
      this.demandLibrary.picLmIdCardBack[0].url = this.imgBaseUrl +  this.demandLibrary.picLmIdCardBack[0].url
      this.demandLibrary.picLmIdCardInHand[0].url = this.imgBaseUrl +  this.demandLibrary.picLmIdCardInHand[0].url
      this.demandLibrary.picCommitmentLetter[0].url = this.imgBaseUrl +  this.demandLibrary.picCommitmentLetter[0].url
      if (!this.demandLibrary.picCommitmentLetter) {
        this.demandLibrary.picCommitmentLetter = []
      }
    }

    this.userType = window.sessionStorage.getItem('userType')
    if (this.userType == '0' || this.userType == '101') {
      this.checkStatus = 1
    } else {
      this.checkStatus = 0
      let fromCheckStatus = window.sessionStorage.getItem('checkStatus')
      debugger
      if (fromCheckStatus == '2') {
        let {
          data,
          success
        } = await getUserDetail()
          let detail = JSON.parse(data.detail)
          this.demandLibrary.code = detail.code
          this.demandLibrary.name = detail.name
          this.demandLibrary.country = detail.country

          if (this.demandLibrary.code != "") this.codeD = true
          this.demandLibrary.picOrgLicense[0].url = this.imgBaseUrl +  detail.picOrgLicense[0].url
          this.demandLibrary.picLpLicense[0].url = this.imgBaseUrl +  detail.picLpLicense[0].url
          this.demandLibrary.picLmIdCardFront[0].url = this.imgBaseUrl +  detail.picLmIdCardFront[0].url
          this.demandLibrary.picLmIdCardBack[0].url = this.imgBaseUrl +  detail.picLmIdCardBack[0].url
          this.demandLibrary.picLmIdCardInHand[0].url = this.imgBaseUrl +  detail.picLmIdCardInHand[0].url


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
            this.demandLibrary.code = detail.code
            this.demandLibrary.name = detail.name
            this.demandLibrary.country = detail.country

            if (this.demandLibrary.code != "") this.codeD = true
            this.demandLibrary.picOrgLicense[0].url = this.imgBaseUrl +  detail.picOrgLicense[0].url
            this.demandLibrary.picLpLicense[0].url = this.imgBaseUrl +  detail.picLpLicense[0].url
            this.demandLibrary.picLmIdCardFront[0].url = this.imgBaseUrl +  detail.picLmIdCardFront[0].url
            this.demandLibrary.picLmIdCardBack[0].url = this.imgBaseUrl +  detail.picLmIdCardBack[0].url
            this.demandLibrary.picLmIdCardInHand[0].url = this.imgBaseUrl +  detail.picLmIdCardInHand[0].url

          }
        }

      }

    }
  },
  computed: {},
  methods: {
    async uploadSectionFile1c(param) {
      this.demandLibrary.picCommitmentLetter = []
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
        this.demandLibrary.picCommitmentLetter.push(arro)
      }
    },
    async uploadSectionFile0(param) {
      this.demandLibrary.picOrgLicense = []
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
        this.demandLibrary.picOrgLicense.push(arro)
      }
    },
    async uploadSectionFile1(param) {
      this.demandLibrary.picLpLicense = []
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
        this.demandLibrary.picLpLicense.push(arro)
      }
    },

    async uploadSectionFile2(param) {
      this.demandLibrary.picLmIdCardFront = []
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
        this.demandLibrary.picLmIdCardFront.push(arro)
      }
    },
    async uploadSectionFile22(param) {
      this.demandLibrary.picLmIdCardBack = []
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
        this.demandLibrary.picLmIdCardBack.push(arro)
      }
    },

    async uploadSectionFilepicLmIdCardInHand(param) {
      this.demandLibrary.picLmIdCardInHand = []
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
        this.demandLibrary.picLmIdCardInHand.push(arro)
      }
    },
    back() {
      window.history.go(-1);
    },
    async getdownloadfile() {

      let {
        data,
        success
      } = await downloadfileName()


    },

    addCN(data) {

      if (JSON.stringify(data.domain).includes(1)) data.search_param.push('智能装备')
      if (JSON.stringify(data.domain).includes(2)) data.search_param.push('电子信息')
      if (JSON.stringify(data.domain).includes(3)) data.search_param.push('新材料')
      if (JSON.stringify(data.domain).includes(4)) data.search_param.push('航空航天')
      if (JSON.stringify(data.domain).includes(5)) data.search_param.push('生物技术与新医药')
      if (JSON.stringify(data.domain).includes(6)) data.search_param.push('能源与环保')

      if (JSON.stringify(data.reqBack).includes(1)) data.search_param.push('新产品开发')
      if (JSON.stringify(data.reqBack).includes(2)) data.search_param.push('制造工艺改进')
      if (JSON.stringify(data.reqBack).includes(3)) data.search_param.push('产品升级换代')
      if (JSON.stringify(data.reqBack).includes(4)) data.search_param.push('制造设备改进')
      if (JSON.stringify(data.reqBack).includes(5)) data.search_param.push('生产线技术改造')

      if (JSON.stringify(data.reqGet).includes(1)) data.search_param.push('技术转让')
      if (JSON.stringify(data.reqGet).includes(2)) data.search_param.push('技术许可')
      if (JSON.stringify(data.reqGet).includes(3)) data.search_param.push('技术开发')
      if (JSON.stringify(data.reqGet).includes(4)) data.search_param.push('技术服务')
      if (JSON.stringify(data.reqGet).includes(5)) data.search_param.push('技术入股')


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
      if (!this.validata.validaDemandLibrary(this.demandLibrary)) return

      let picOrgLicense = this.demandLibrary.picOrgLicense[0].url
      if (picOrgLicense.indexOf('/user/') > -1) {
        this.demandLibrary.picOrgLicense[0].url = picOrgLicense.substring(picOrgLicense.indexOf('/user/') + 1, picOrgLicense.length)
      } else {
        this.demandLibrary.picOrgLicense[0].url = picOrgLicense.substring(picOrgLicense.indexOf('/def/') + 1, picOrgLicense.length)
      }
      let picLpLicense = this.demandLibrary.picLpLicense[0].url
      if (picLpLicense.indexOf('/user/') > -1) {
        this.demandLibrary.picLpLicense[0].url = picLpLicense.substring(picLpLicense.indexOf('/user/') + 1, picLpLicense.length)
      } else {
        this.demandLibrary.picLpLicense[0].url = picLpLicense.substring(picLpLicense.indexOf('/def/') + 1, picLpLicense.length)
      }
      let picLmIdCardFront = this.demandLibrary.picLmIdCardFront[0].url
      if (picLmIdCardFront.indexOf('/user/') > -1) {
        this.demandLibrary.picLmIdCardFront[0].url = picLmIdCardFront.substring(picLmIdCardFront.indexOf('/user/') + 1, picLmIdCardFront.length)
      } else {
        this.demandLibrary.picLmIdCardFront[0].url = picLmIdCardFront.substring(picLmIdCardFront.indexOf('/def/') + 1, picLmIdCardFront.length)
      }

      let picLmIdCardBack = this.demandLibrary.picLmIdCardBack[0].url
      if (picLmIdCardBack.indexOf('/user/') > -1) {
        this.demandLibrary.picLmIdCardBack[0].url = picLmIdCardBack.substring(picLmIdCardBack.indexOf('/user/') + 1, picLmIdCardBack.length)
      } else {
        this.demandLibrary.picLmIdCardBack[0].url = picLmIdCardBack.substring(picLmIdCardBack.indexOf('/def/') + 1, picLmIdCardBack.length)
      }

      let picLmIdCardInHand = this.demandLibrary.picLmIdCardInHand[0].url
      if (picLmIdCardInHand.indexOf('/user/') > -1) {
        this.demandLibrary.picLmIdCardInHand[0].url = picLmIdCardInHand.substring(picLmIdCardInHand.indexOf('/user/') + 1, picLmIdCardInHand.length)
      } else {
        this.demandLibrary.picLmIdCardInHand[0].url = picLmIdCardInHand.substring(picLmIdCardInHand.indexOf('/def/') + 1, picLmIdCardInHand.length)
      }

      let picCommitmentLetter = this.demandLibrary.picCommitmentLetter[0].url
      if (picCommitmentLetter.indexOf('/user/') > -1) {
        this.demandLibrary.picCommitmentLetter[0].url = picCommitmentLetter.substring(picCommitmentLetter.indexOf('/user/') + 1, picCommitmentLetter.length)
      } else {
        this.demandLibrary.picCommitmentLetter[0].url = picCommitmentLetter.substring(picCommitmentLetter.indexOf('/def/') + 1, picCommitmentLetter.length)
      }


      let arr = {}
      arr.formType = '7'
      arr.checkStatus = checkStatus
      arr.id = this.$route.params.objId
      this.demandLibrary.search_param = []
      this.demandLibrary = this.addCN(this.demandLibrary)
      arr.detail = JSON.stringify(this.demandLibrary)

      let {
        data,
        success
      } = await addpublish(arr)

      if (success) {
        this.$message({
          message: '保存成功',
          type: 'success'
        });
        this.demandLibrary.picOrgLicense[0].url = this.imgBaseUrl +  this.demandLibrary.picOrgLicense[0].url
        this.demandLibrary.picLpLicense[0].url = this.imgBaseUrl +  this.demandLibrary.picLpLicense[0].url
        this.demandLibrary.picLmIdCardFront[0].url = this.imgBaseUrl +  this.demandLibrary.picLmIdCardFront[0].url
        this.demandLibrary.picLmIdCardBack[0].url = this.imgBaseUrl +  this.demandLibrary.picLmIdCardBack[0].url
        this.demandLibrary.picLmIdCardInHand[0].url = this.imgBaseUrl +  this.demandLibrary.picLmIdCardInHand[0].url
        this.demandLibrary.picCommitmentLetter[0].url = this.imgBaseUrl +  this.demandLibrary.picCommitmentLetter[0].url
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
@import '/../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>
.tools {
  height: 5%
}

.area-select .area-selected-trigger {
  position: absolute;
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
