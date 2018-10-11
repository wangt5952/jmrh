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
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入姓名，不能超过20个字符！" v-model="expert.name" style="width:80%" maxlength="20"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-select v-model="expert.sex" style="width:100px" placeholder="请选择">
                  <el-option label="男" key="1" value='1'>
                  </el-option>
                  <el-option label="女" key="0" value='0'>
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="出生日期">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-date-picker v-model="expert.bornDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
                </el-date-picker>
              </el-form-item>

              <el-form-item label="身份证号">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-input  :disabled=codeD placeholder="请输入身份证号" v-model="expert.code" style="width:80%"  type="number"></el-input>
              </el-form-item>
              <el-form-item label="上传身份证正面">
                <div>
                  <!--这是正面照-->
                  <div class="photo">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile1" :file-list="expert.picLmIdCardFront" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>
                    <!-- <input type="file" @change="uploadImg($event)" id="IdCard"> -->
                    <!-- <input type="file"  @change="per.picLmIdCardFront"  id="IdCard"> -->

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="上传身份证反面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile2" :file-list="expert.picLmIdCardBack" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="手持身份证">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile3" :file-list="expert.picLmIdCardInHand" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="免冠照片">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile4" :file-list="expert.picLogo" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="毕业院校">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入毕业院校，不能超过20个字符！" v-model="expert.shcool" style="width:80%"  maxlength="20"></el-input>
              </el-form-item>
              <el-form-item label="学历">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-select v-model="expert.edu" style="width:120px;height:30px" placeholder="请选择">
                  <el-option label="请选择" key="" value="">
                  </el-option>
                  <el-option label="本科" key="5" value="5">
                  </el-option>
                  <el-option label="硕士研究生" key="6" value="6">
                  </el-option>
                  <el-option label="博士研究生" key="7" value="7">
                  </el-option>
                  <el-option label="其他" key="99" value="99">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="学位">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-select v-model="expert.academic" style="width:100px;height:30px" placeholder="请选择">
                  <el-option label="请选择" key="" value="">
                  </el-option>
                  <el-option label="学士" key="1" value="1">
                  </el-option>
                  <el-option label="硕士" key="2" value="2">
                  </el-option>
                  <el-option label="博士" key="3" value="3">
                  </el-option>
                  <el-option label="其他" key="99" value="99">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="研究领域">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>

                <el-checkbox-group v-model="expert.research_field">
                  <el-checkbox label="1">智能装备</el-checkbox>
                  <el-checkbox label="2">电子信息</el-checkbox>
                  <el-checkbox label="3">新材料 </el-checkbox>
                  <el-checkbox label="4">航空航天</el-checkbox>
                  <el-checkbox label="5">生物技术与新医药</el-checkbox>
                  <el-checkbox label="6">能源与环保</el-checkbox>
                  <el-checkbox label="7">管理</el-checkbox>
                  <el-checkbox label="99">其他</el-checkbox>
                  <el-input v-if="expert.research_field.includes('99')" placeholder="请输入其他，不能超过20个字符！" v-model="expert.researchFieldOther" maxlength="20" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="研究方向">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入研究方向，不能超过20个字符！" v-model="expert.research_area" style="width:80%"  maxlength="20"></el-input>
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

              <el-form-item label="工作单位">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入工作单位，不能超过20个字符！" maxlength="20" v-model="expert.work_unit" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="工作部门">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入工作部门，不能超过20个字符！" maxlength="20" v-model="expert.work_bumen" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="现任职务">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入现任职务，不能超过20个字符！"  maxlength="20" v-model="expert.zwname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职称">
                <span style='position: absolute;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入职称，不能超过20个字符！" maxlength="20" v-model="expert.zcname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职称级别">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="expert.zclevel">
                  <el-checkbox label="1">正高</el-checkbox>
                  <el-checkbox label="2">副高</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="手机号">
                <span style='position: absolute;left: -60px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入手机号" v-model="expert.mobilephone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="办公电话">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入办公电话" v-model="expert.telphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="expert.fdemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="所在地区">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <area-cascader :level="1" v-model="expert.country" :data="pcaa"></area-cascader>
              </el-form-item>
              <el-form-item label="通讯地址">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入通讯地址，不能超过30个字符！" maxlength="30" v-model="expert.address" style="width:80%"></el-input>
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

              <el-form-item label="主要学术成就/研究成果/管理成就">
                <span style='position: absolute;left: -230px;color: #f60d0d;'></span>
                <textarea placeholder="不能超过200个字符！" maxlength="200" v-model="expert.success_record" rows="3" cols="20" style="width:60%;height: 120px;">
                </textarea>
              </el-form-item>

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
              <el-form-item label="主要产学研合作项目情况（国防军工类项目）">
                <textarea placeholder="不能超过200个字符！" maxlength="200" v-model="expert.project_desc" rows="3" cols="20" style="width:60%;height: 120px;">
                </textarea>
              </el-form-item>

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


              <el-form v-for="item in expert.research_record" class="" label-width="40%" style="text-align:left;border: 1px solid#ccc;border-radius: 4px;margin: 15px 0;padding: 15px;">
                <el-row :gutter="24">
                  <el-col :span="12">


                    <el-form-item label="项目或课题名称">
                      <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="item.projectname" style="width: 50%;"></el-input>
                    </el-form-item>

                    <el-form-item label="项目或课题来源">

                      <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="item.projectSrc" style="width: 50%;"></el-input>
                    </el-form-item>


                    <el-form-item label="完成情况">
                      <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="item.finishcon" style="width: 50%;"></el-input>
                    </el-form-item>

                    <el-form-item label="完成时间">
                      <el-date-picker v-model="item.finishtime" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="" style="width: 50%;">
                      </el-date-picker>
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="奖项名称">
                      <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="item.rewname" style="width: 50%;"></el-input>
                    </el-form-item>

                    <el-form-item label="获奖等级">

                      <el-input placeholder="不能超过20个字符！" maxlength="20" type="text" v-model="item.rewlevel" style="width: 50%;"></el-input>
                    </el-form-item>


                    <el-form-item label="获奖时间">
                      <el-date-picker v-model="item.rewtime" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="" style="width: 50%;">
                      </el-date-picker>
                    </el-form-item>

                  </el-col>

                </el-row>
                <div style="text-align: right;">
                  <el-button size="small" @click="delProjectexpert(item)" style="color:red">
                    删除项目
                  </el-button>
                </div>
              </el-form>

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
  setUserDetail
} from '@/api/login'
import {
  getexpert,
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
      expert: {
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

          url: this.imgBaseUrl + `def/livePhoto.jpg`
        }],
        name: '',
        sex: '',
        bornDate: '',
        code: '',
        shcool: '',
        edu: '',
        academic: '',
        research_field: [],
        researchFieldOther: '',
        research_area: '',
        zwname: '',
        zcname: '',
        zclevel: [],
        work_unit: '',
        work_bumen: '',
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
        }],
        search_param: []
      },
      checkStatus: 0
    }
  },
  async mounted() {
    this.listLoading = false
    if (this.$route.params.objData) {
      this.expert = JSON.parse(this.$route.params.objData)
      if (this.expert.code != "") this.codeD = true
      debugger
      this.expert.picLmIdCardFront = [{
        url: this.imgBaseUrl + this.expert.picLmIdCardFront
      }]
      this.expert.picLmIdCardBack = [{
        url: this.imgBaseUrl + this.expert.picLmIdCardBack
      }]
      this.expert.picLmIdCardInHand = [{
        url: this.imgBaseUrl + this.expert.picLmIdCardInHand
      }]
      this.expert.picLogo = [{
        url: this.imgBaseUrl + this.expert.picLogo
      }]


      if (!this.expert.picLmIdCardFront) {
        this.expert.picLmIdCardFront = []
      }
      if (!this.expert.picLmIdCardBack) {
        this.expert.picLmIdCardBack = []
      }
      if (!this.expert.picLmIdCardInHand) {
        this.expert.picLmIdCardInHand = []
      }

      if (!this.expert.picLogo) {
        this.expert.picLogo = []
      }
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
    async uploadSectionFile1(param) {
      this.expert.picLmIdCardFront = []
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
        this.expert.picLmIdCardFront.push(arro)
      }
    },
    async uploadSectionFile2(param) {
      this.expert.picLmIdCardBack = []
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
        this.expert.picLmIdCardBack.push(arro)
      }
    },
    async uploadSectionFile3(param) {
      this.expert.picLmIdCardInHand = []
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
        this.expert.picLmIdCardInHand.push(arro)
      }
    },
    async uploadSectionFile4(param) {
      this.expert.picLogo = []
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
        this.expert.picLogo.push(arro)
      }
    },
    back() {
      window.history.go(-1);
    },
    addCN(data) {
      if (data.edu.includes(1)) data.search_param.push('小学')
      if (data.edu.includes(2)) data.search_param.push('初中')
      if (data.edu.includes(3)) data.search_param.push('高中')
      if (data.edu.includes(4)) data.search_param.push('大专')
      if (data.edu.includes(5)) data.search_param.push('本科')
      if (data.edu.includes(6)) data.search_param.push('研究生')
      if (data.edu.includes(7)) data.search_param.push('博士')
      if (data.edu.includes(8)) data.search_param.push('研究生')

      if (data.academic.includes(1)) data.search_param.push('学士')
      if (data.academic.includes(2)) data.search_param.push('硕士')
      if (data.academic.includes(3)) data.search_param.push('博士')

      if (JSON.stringify(data.research_field).includes(1)) data.search_param.push('智能装备')
      if (JSON.stringify(data.research_field).includes(2)) data.search_param.push('电子信息')
      if (JSON.stringify(data.research_field).includes(3)) data.search_param.push('新材料')
      if (JSON.stringify(data.research_field).includes(4)) data.search_param.push('航空航天')
      if (JSON.stringify(data.research_field).includes(5)) data.search_param.push('生物技术与新医药')
      if (JSON.stringify(data.research_field).includes(6)) data.search_param.push('能源与环保')
      if (JSON.stringify(data.research_field).includes(7)) data.search_param.push('管理')

      if (JSON.stringify(data.zclevel).includes(1)) data.search_param.push('正高')
      if (JSON.stringify(data.zclevel).includes(2)) data.search_param.push('副高')


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

    async saveFile(checkStatus) {
      if (!this.validata.validaExpert(this.expert)) return

      let picLmIdCardFront = this.expert.picLmIdCardFront[0].url
      if (picLmIdCardFront.indexOf('/user/') > -1) {
        this.expert.picLmIdCardFront[0].url = picLmIdCardFront.substring(picLmIdCardFront.indexOf('/user/') + 1, picLmIdCardFront.length)
      } else {
        this.expert.picLmIdCardFront[0].url = picLmIdCardFront.substring(picLmIdCardFront.indexOf('/def/') + 1, picLmIdCardFront.length)
      }

      let picLmIdCardBack = this.expert.picLmIdCardBack[0].url
      if (picLmIdCardBack.indexOf('/user/') > -1) {
        this.expert.picLmIdCardBack[0].url = picLmIdCardBack.substring(picLmIdCardBack.indexOf('/user/') + 1, picLmIdCardBack.length)
      } else {
        this.expert.picLmIdCardBack[0].url = picLmIdCardBack.substring(picLmIdCardBack.indexOf('/def/') + 1, picLmIdCardBack.length)
      }

      let picLmIdCardInHand = this.expert.picLmIdCardInHand[0].url
      if (picLmIdCardInHand.indexOf('/user/') > -1) {
        this.expert.picLmIdCardInHand[0].url = picLmIdCardInHand.substring(picLmIdCardInHand.indexOf('/user/') + 1, picLmIdCardInHand.length)
      } else {
        this.expert.picLmIdCardInHand[0].url = picLmIdCardInHand.substring(picLmIdCardInHand.indexOf('/def/') + 1, picLmIdCardInHand.length)
      }

      let picLogo = this.expert.picLogo[0].url
      if (picLogo.indexOf('/user/') > -1) {
        this.expert.picLogo[0].url = picLogo.substring(picLogo.indexOf('/user/') + 1, picLogo.length)
      } else {
        this.expert.picLogo[0].url = picLogo.substring(picLogo.indexOf('/def/') + 1, picLogo.length)
      }

      let arr = {}
      arr.formType = '1'
      arr.checkStatus = checkStatus
      arr.id = this.$route.params.objId
      this.expert.search_param = []
      this.expert = this.addCN(this.expert)
      arr.detail = JSON.stringify(this.expert)
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
        this.expert.picLmIdCardFront[0].url = this.imgBaseUrl +  this.expert.picLmIdCardFront[0].url
        this.expert.picLmIdCardBack[0].url = this.imgBaseUrl +  this.expert.picLmIdCardBack[0].url
        this.expert.picLmIdCardInHand[0].url = this.imgBaseUrl +  this.expert.picLmIdCardInHand[0].url
        this.expert.picLogo[0].url = this.imgBaseUrl +  this.expert.picLogo[0].url
        this.dialogFormVisible = false
          window.history.go(-1);
      } else {
        this.$message({
          message: message,
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

    delProjectexpert(item) {
      this.expert.research_record.splice(this.expert.research_record.indexOf(item), 1)
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
@import '/../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>

</style>
