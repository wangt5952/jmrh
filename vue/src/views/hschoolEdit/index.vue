<template>
<div class="tab-container">

  <div class="" style="height: 80% !important;overflow-y: auto;">
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 高校基本信息</span>
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="名称">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入名称，不能超过20个字符！"  maxlength="20" v-model="school.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="统一社会信用代码">
                <span style='position: absolute;left: -135px;color: #f60d0d;'>*</span>
                <el-input  :disabled=codeD placeholder="请输入统一社会信用代码，不能超过20个字符！"  maxlength="20" v-model="school.code" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="营业执照">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile0" :file-list="school.picOrgLicense" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="事业单位法人证书">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile1" :file-list="school.picLpLicense" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="联系人身份证正面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile2" :file-list="school.picLmIdCardFront" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="联系人身份证反面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile22" :file-list="school.picLmIdCardBack" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>

              <el-form-item label="手持身份证">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFilepicLmIdCardInHand" :file-list="school.picLmIdCardInHand" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="高校logo">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile3" :file-list="school.picLogo" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="联系人姓名">
                <span style='position: relative;left: -60px;color: #f60d0d;'></span>
                <el-input placeholder="请输入手机号" v-model="school.lxname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="联系人手机号">
                <span style='position: relative;left: -60px;color: #f60d0d;'></span>
                <el-input placeholder="请输入手机号" v-model="school.lxmobile" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="联系人邮箱">
                <span style='position: relative;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入邮箱" v-model="school.lxemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="所在地区">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <area-cascader :level="1" v-model="school.country" :data="pcaa"></area-cascader>
                <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
              </el-form-item>
              <el-form-item label="联系地址">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入联系地址，不能超过20个字符！"  maxlength="20" v-model="school.address" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮编">
                <el-input placeholder="请输入邮编" v-model="school.zip_code" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="单位网址">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入单位网址" v-model="school.unit_url" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="单位简介">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入单位简介，不能超过30个字符！"  maxlength="30" v-model="school.major_platform" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="重大平台">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入重大平台，不能超过30个字符！"  maxlength="30" v-model="school.introduction" style="width:80%"></el-input>
              </el-form-item>
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
          <el-radio v-show="userType == '0'" :label="1">直接审核</el-radio>
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
  setUserDetail
} from '@/api/login'
import {
  getcollege,
  addLib,
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
      titleName: '',
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
      school: {
        picOrgLicense: [{
          url: this.imgBaseUrl + `def/companyZZ.png`
        }],
        picLpLicense: [{
          url: this.imgBaseUrl + `def/companyZS.png`
        }],
        picLmIdCardFront: [{
          url: this.imgBaseUrl + `def/idfront.png`
        }],
        picLmIdCardBack: [{
          url: this.imgBaseUrl + `def/idback.png`
        }],
        picLmIdCardInHand: [{
          url: this.imgBaseUrl + `def/handPhoto.jpg`
        }],
        picLogo: [{
          url: this.imgBaseUrl + `def/qylogo.png`
        }],
        lxname: '',
        lxmobile: '',
        lxemail: '',
        introduction: '',
        major_platform: '',
        unit_url: '',
        zip_code: '',
        address: '',
        country: '',
        code: '',
        name: '',
        search_param: []
      },
      checkStatus: 0
    }
  },
  async mounted() {
    if (this.$route.params.objData) {
      this.school = JSON.parse(this.$route.params.objData)
      if (this.school.code != "") this.codeD = true
      this.school.picOrgLicense = [{
        url: this.imgBaseUrl + this.school.picOrgLicense
      }]
      this.school.picLpLicense = [{
        url: this.imgBaseUrl + this.school.picLpLicense
      }]
      this.school.picLmIdCardFront = [{
        url: this.imgBaseUrl + this.school.picLmIdCardFront
      }]
      this.school.picLmIdCardBack = [{
        url: this.imgBaseUrl + this.school.picLmIdCardBack
      }]
      this.school.picLmIdCardInHand = [{
        url: this.imgBaseUrl + this.school.picLmIdCardInHand
      }]
      this.school.picLogo = [{
        url: this.imgBaseUrl + this.school.picLogo
      }]

    }

    this.userType = window.sessionStorage.getItem('userType')
    if (this.userType == '0' || this.userType == '101') {
      this.checkStatus = 1
    } else {
      this.checkStatus = 0
    }
  },
  computed: {},
  methods: {
    async uploadSectionFile0(param) {
      this.school.picOrgLicense = []
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
        this.school.picOrgLicense.push(arro)
      }
    },
    async uploadSectionFile1(param) {
      this.school.picLpLicense = []
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
        this.school.picLpLicense.push(arro)
      }
    },

    async uploadSectionFile2(param) {
      this.school.picLmIdCardFront = []
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
        this.school.picLmIdCardFront.push(arro)
      }
    },
    async uploadSectionFile22(param) {
      this.school.picLmIdCardBack = []
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
        this.school.picLmIdCardBack.push(arro)
      }
    },

    async uploadSectionFilepicLmIdCardInHand(param) {
      this.school.picLmIdCardInHand = []
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
        this.school.picLmIdCardInHand.push(arro)
      }
    },
    async uploadSectionFile3(param) {
      this.school.picLogo = []
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
        this.school.picLogo.push(arro)
      }
    },
    back() {
      window.history.go(-1);
    },
    addCN(data) {
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
      if (!this.validata.validaHschool(this.school)) return

      let picOrgLicense = this.school.picOrgLicense[0].url
      if (picOrgLicense.indexOf('/user/') > -1) {
        this.school.picOrgLicense[0].url = picOrgLicense.substring(picOrgLicense.indexOf('/user/') + 1, picOrgLicense.length)
      } else {
        this.school.picOrgLicense[0].url = picOrgLicense.substring(picOrgLicense.indexOf('/def/') + 1, picOrgLicense.length)
      }
      let picLpLicense = this.school.picLpLicense[0].url
      if (picLpLicense.indexOf('/user/') > -1) {
        this.school.picLpLicense[0].url = picLpLicense.substring(picLpLicense.indexOf('/user/') + 1, picLpLicense.length)
      } else {
        this.school.picLpLicense[0].url = picLpLicense.substring(picLpLicense.indexOf('/def/') + 1, picLpLicense.length)
      }
      let picLmIdCardFront = this.school.picLmIdCardFront[0].url
      if (picLmIdCardFront.indexOf('/user/') > -1) {
        this.school.picLmIdCardFront[0].url = picLmIdCardFront.substring(picLmIdCardFront.indexOf('/user/') + 1, picLmIdCardFront.length)
      } else {
        this.school.picLmIdCardFront[0].url = picLmIdCardFront.substring(picLmIdCardFront.indexOf('/def/') + 1, picLmIdCardFront.length)
      }

      let picLmIdCardBack = this.school.picLmIdCardBack[0].url
      if (picLmIdCardBack.indexOf('/user/') > -1) {
        this.school.picLmIdCardBack[0].url = picLmIdCardBack.substring(picLmIdCardBack.indexOf('/user/') + 1, picLmIdCardBack.length)
      } else {
        this.school.picLmIdCardBack[0].url = picLmIdCardBack.substring(picLmIdCardBack.indexOf('/def/') + 1, picLmIdCardBack.length)
      }

      let picLmIdCardInHand = this.school.picLmIdCardInHand[0].url
      if (picLmIdCardInHand.indexOf('/user/') > -1) {
        this.school.picLmIdCardInHand[0].url = picLmIdCardInHand.substring(picLmIdCardInHand.indexOf('/user/') + 1, picLmIdCardInHand.length)
      } else {
        this.school.picLmIdCardInHand[0].url = picLmIdCardInHand.substring(picLmIdCardInHand.indexOf('/def/') + 1, picLmIdCardInHand.length)
      }

      let picLogo = this.school.picLogo[0].url
      if (picLogo.indexOf('/user/') > -1) {
        this.school.picLogo[0].url = picLogo.substring(picLogo.indexOf('/user/') + 1, picLogo.length)
      } else {
        this.school.picLogo[0].url = picLogo.substring(picLogo.indexOf('/def/') + 1, picLogo.length)
      }



      let arr = {}
      arr.formType = '4'
      arr.checkStatus = checkStatus
      arr.id = this.$route.params.objId
      this.school.search_param = []
      this.school = this.addCN(this.school)
      arr.detail = JSON.stringify(this.school)
      arr.number = this.$route.params.number
      arr.userId =  window.sessionStorage.getItem('userId')
      let {
        data,
        success,
        message
      } = await addLib(arr)
      if (success) {
        this.$message({
          message: '保存成功',
          type: 'success'
        });
        this.school.picOrgLicense[0].url = this.imgBaseUrl + this.school.picOrgLicense[0].url
        this.school.picLpLicense[0].url = this.imgBaseUrl + this.school.picLpLicense[0].url
        this.school.picLmIdCardFront[0].url = this.imgBaseUrl + this.school.picLmIdCardFront[0].url
        this.school.picLmIdCardBack[0].url = this.imgBaseUrl + this.school.picLmIdCardBack[0].url
        this.school.picLmIdCardInHand[0].url = this.imgBaseUrl + this.school.picLmIdCardInHand[0].url
        this.school.picLogo[0].url = this.imgBaseUrl + this.school.picLogo[0].url
        this.dialogFormVisible = false
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
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>
.tools {
  height: 5%
}


</style>
