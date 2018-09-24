<template>
<div class="tab-container">

  <div class="" style="height: 80% !important;overflow-y: auto;">
    <el-tabs type="border-card">
      <el-tab-pane label="机构信息">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="单位名称">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入单位名称" v-model="mech.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="统一社会信用代码">
                <span style='position: absolute;left: -135px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入统一社会信用代码" v-model="mech.code" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="营业执照">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile0" :file-list="mech.picOrgLicense" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>
                  </div>
                </div>
              </el-form-item>

              <el-form-item label="机构logo">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile3" :file-list="mech.picLogo" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="事业单位法人证书">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile1" :file-list="mech.picLpLicense" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="所在地区">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <area-cascader :level="1" v-model="mech.country" :data="pcaa"></area-cascader>
                <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
              </el-form-item>
              <el-form-item label="联系地址">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入联系地址" v-model="mech.address" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮编">
                <el-input placeholder="请输入邮编" v-model="mech.ecode" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="机构性质">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="mech.registerNature">
                  <el-checkbox label="1">企业</el-checkbox>
                  <el-checkbox label="2">科研院所</el-checkbox>
                  <el-checkbox label="3">高等院校 </el-checkbox>
                  <el-checkbox label="99">其他</el-checkbox>
                  <el-input v-if="mech.registerNature.includes('99')" placeholder="请输入其他" v-model="mech.mechregisterNatureOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="机构类别">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="mech.orgType">
                  <el-checkbox label="1">研究开发</el-checkbox>
                  <el-checkbox label="2">科技投融资</el-checkbox>
                  <el-checkbox label="3">技术转移 </el-checkbox>
                  <el-checkbox label="4">检验检测</el-checkbox>
                  <el-checkbox label="5">创业孵化</el-checkbox>
                  <el-checkbox label="6">知识产权</el-checkbox>
                  <el-checkbox label="7">科技评估</el-checkbox>
                  <el-checkbox label="8">标准认证</el-checkbox>
                  <el-checkbox label="9">管理咨询</el-checkbox>
                  <el-checkbox label="10">综合科技服务</el-checkbox>
                  <el-checkbox label="99">其他</el-checkbox>
                  <el-input v-if="mech.orgType.includes('99')" placeholder="请输入其他" v-model="mech.orgTypeOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="可提供的服务简介（200字以内）">
                <span style='position: absolute;left: -225px;color: #f60d0d;'></span>

                <textarea rows="3" v-model="mech.serviceAbout" cols="20" style="width:100%;height: 120px;">
                    </textarea>
              </el-form-item>

              <el-form-item :label="service_amount_lastt">
                <span style='position: absolute;left: -180px;color: #f60d0d;'></span>
                <el-input placeholder="请输入服务收入" v-model="mech.service_amount_last" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_amount_beforet">
                <span style='position: absolute;left: -180px;color: #f60d0d;'></span>
                <el-input placeholder="请输入服务收入" v-model="mech.service_amount_before" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_amount_previoust">
                <span style='position: absolute;left: -180px;color: #f60d0d;'></span>
                <el-input placeholder="请输入服务收入" v-model="mech.service_amount_previous" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item :label="service_quantity_lastt">
                <span style='position: absolute;left: -160px;color: #f60d0d;'></span>
                <el-input placeholder="请输入服务数量" v-model="mech.service_quantity_last" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_quantity_beforet">
                <span style='position: absolute;left: -160px;color: #f60d0d;'></span>
                <el-input placeholder="请输入服务数量" v-model="mech.service_quantity_before" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_quantity_previoust">
                <span style='position: absolute;left: -160px;color: #f60d0d;'></span>
                <el-input placeholder="请输入服务数量" v-model="mech.service_quantity_previous" style="width:80%"></el-input>
              </el-form-item>
            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="机构法定代表人">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="姓名">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入姓名" v-model="mech.fdname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <span style='position: absolute;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入电话" v-model="mech.fdtel" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <span style='position: absolute;left: -60px;color: #f60d0d;'></span>
                <el-input placeholder="请输入手机号" v-model="mech.fdphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: absolute;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入邮箱" v-model="mech.fdemail" style="width:80%"></el-input>
              </el-form-item>

              <!-- <el-form-item label="法人身份证正面">
                <div>
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile2" :file-list="mech.fuben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="法人身份证反面">
                <div>
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile22" :file-list="mech.fuben2" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item> -->

            </el-col>
            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="机构联系人">
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="姓名">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入姓名" v-model="mech.linkman" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="联系人身份证正面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFilepicLmIdCardFront" :file-list="mech.picLmIdCardFront" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="联系人身份证反面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFilepicLmIdCardBack" :file-list="mech.picLmIdCardBack" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>

              <el-form-item label="手持身份证">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFilepicLmIdCardInHand" :file-list="mech.picLmIdCardInHand" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="职务">
                <span style='position: absolute;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入职务" v-model="mech.lxzw" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <span style='position: absolute;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入电话" v-model="mech.lxtel" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <span style='position: absolute;left: -60px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入手机号" v-model="mech.lxphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: absolute;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="mech.lxemail" style="width:80%"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="10">


            </el-col>

          </el-row>
        </el-form>

      </el-tab-pane>


      <el-tab-pane label="人才队伍情况">

        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="人员总数">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input v-model="mech.perNum" placeholder="请输入人员总数" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="本科及以上学历人员数">
                <span style='position: absolute;left: -160px;color: #f60d0d;'></span>
                <el-input v-model="mech.underPer" placeholder="请输入人员本科及以上学历人员数" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="中高级职称以上人员数">
                <span style='position: absolute;left: -160px;color: #f60d0d;'></span>
                <el-input v-model="mech.MHPer" placeholder="请输入中高级职称以上人员数" style="width:80%"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

      </el-tab-pane>

      <el-tab-pane label="项目情况">
        <el-form class="" label-width="30%" style="text-align:left">

          <!-- <el-form-item label=""> -->

          <div style="margin:0 auto">

            <el-button size="small" @click="addProjectmech()">
              添加项目
            </el-button>


            <el-form v-for="item in mech.PorcolumnDefinitions" class="" label-width="40%" style="text-align:left;border: 1px solid#ccc;border-radius: 4px;margin: 15px 0;padding: 15px;">
              <el-row :gutter="24">
                <el-col :span="8">


                  <el-form-item label="项目名称">
                    <el-input placeholder="请输入项目名称" v-model="item.name" style="width:130%"></el-input>
                  </el-form-item>

                  <el-form-item label="起止时间">

                    <el-date-picker v-model="item.time" type="datetimerange" value-format="yyyy-MM-dd" format="yyyy-MM-dd" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['12:00:00']" style="width:130%">
                    </el-date-picker>
                  </el-form-item>


                  <el-form-item label="项目来源">
                    <el-input placeholder="请输入项目来源" v-model="item.source" style="width:130%"></el-input>
                  </el-form-item>

                </el-col>
                <el-col :span="16">

                  <el-form-item label="资助方式">
                    <el-checkbox-group v-model="item.registerSupport">
                      <el-checkbox label="1">拨款</el-checkbox>
                      <el-checkbox label="2">贴息</el-checkbox>
                      <el-checkbox label="3">减免税 </el-checkbox>
                      <el-checkbox label="4">以上全无</el-checkbox>
                      <el-checkbox label="99">其他</el-checkbox>
                      <el-input v-if="item.registerSupport.includes('99')" placeholder="请输入其他" v-model="item.mechregisterSupportOther" style="width:80%"></el-input>
                    </el-checkbox-group>
                  </el-form-item>

                  <el-form-item label="执行情况">
                    <el-checkbox-group v-model="item.registerImplement">
                      <el-checkbox label="1">申请</el-checkbox>
                      <el-checkbox label="2">在研</el-checkbox>
                      <el-checkbox label="3">验收/结题 </el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>

                </el-col>

              </el-row>
              <div style="text-align: right;">
                <el-button size="small" @click="delProjectmech(item)" style="color:red">
                  删除项目
                </el-button>
              </div>
            </el-form>

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
                      <el-input  type="text" v-model="scope.row.name" style="width:80%"></el-input>
                                      </template>
                </el-table-column>
                <el-table-column align="center" label="获得时间">
                  <template slot-scope="scope">
                                            <span>
                                              <el-date-picker style="width:80%" v-model="scope.row.time" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="">
                                              </el-date-picker>
                                            </span>
                                        </template>
                </el-table-column>
                <el-table-column align="center" label="颁发机构">
                  <template slot-scope="scope">
                      <el-input  type="text" v-model="scope.row.issuingAgency" style="width:80%"></el-input>
                                      </template>
                </el-table-column>

                <el-table-column align="center" label="" style="width: 50%;">
                  <template slot-scope="scope">
                    <el-button size="small" @click="delhonrmech(scope.row)" style="color:red">
                      删除
                    </el-button>
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
                      <el-input  type="text" v-model="scope.row.name" style="width:80%"></el-input>
                                      </template>
                </el-table-column>
                <el-table-column align="center" label="服务对象">
                  <template slot-scope="scope">
                                            <span>
                                              <el-input  type="text" v-model="scope.row.object" style="width:80%"></el-input>
                                            </span>
                                        </template>
                </el-table-column>
                <el-table-column align="center" label="服务时间起止">
                  <template slot-scope="scope">
                                      <el-date-picker v-model="scope.row.time" type="datetimerange" value-format="yyyy-MM-dd" format="yyyy-MM-dd" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['12:00:00']" style="width:130%">
                                      </el-date-picker>
                                      </template>
                </el-table-column>
                <el-table-column align="center" label="" style="width: 50%;">
                  <template slot-scope="scope">
                    <el-button size="small" @click="delcjmech(scope.row)" style="color:red">
                      删除
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
      mech: {
        picOrgLicense: [{
          name: '默认',
          url: this.imgBaseUrl + `def/companyZZ.png`
        }],
        picLpLicense: [{
          name: '默认',
          url: this.imgBaseUrl + `def/companyZS.png`
        }],
        // fuben: [{
        //   name: '默认',
        //   url: this.imgBaseUrl + `def/idfront.png`
        // }],
        // fuben2: [{
        //   name: '默认',
        //   url: this.imgBaseUrl + `def/idback.png`
        // }],
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

        picLogo: [{
          name: '默认',
          url: this.imgBaseUrl + `def/qylogo.png`
        }],
        code: '',
        registerNature: [],
        mechregisterNatureOther: '',
        orgType: [],
        mechorg_typeOther: '',
        serviceAbout: '',
        service_amount_last: '',
        service_amount_before: '',
        service_amount_previous: '',
        service_quantity_last: '',
        service_quantity_before: '',
        service_quantity_previous: '',
        mechregisterSupportOther: '',
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
        fdtel: '',
        fdphone: '',
        fdname: '',
        lxemail: '',
        lxphone: '',
        lxtel: '',
        lxzw: '',
        linkman: '',
        search_param: []
      },
      checkStatus: 1
    }
  },
  async mounted() {
    this.listLoading = false
    if (this.$route.params.objData) {
      this.mech = JSON.parse(this.$route.params.objData)
      this.mech.picOrgLicense[0].url = this.imgBaseUrl +  this.mech.picOrgLicense[0].url
      this.mech.picLpLicense[0].url = this.imgBaseUrl +  this.mech.picLpLicense[0].url
      this.mech.picLmIdCardFront[0].url = this.imgBaseUrl +  this.mech.picLmIdCardFront[0].url
      this.mech.picLmIdCardBack[0].url = this.imgBaseUrl +  this.mech.picLmIdCardBack[0].url
      this.mech.picLmIdCardInHand[0].url = this.imgBaseUrl +  this.mech.picLmIdCardInHand[0].url
      this.mech.picLogo[0].url = this.imgBaseUrl +  this.mech.picLogo[0].url
    }
    var myDate = new Date();
    this.service_amount_lastt = parseInt(myDate.getFullYear() - 1) + '年服务收入（万元）';
    this.service_amount_beforet = parseInt(myDate.getFullYear() - 2) + '年服务收入（万元）';
    this.service_amount_previoust = parseInt(myDate.getFullYear() - 3) + '年服务收入（万元）';

    this.service_quantity_lastt = parseInt(myDate.getFullYear() - 1) + '年服务数量（次）';
    this.service_quantity_beforet = parseInt(myDate.getFullYear() - 2) + '年服务数量（次）';
    this.service_quantity_previoust = parseInt(myDate.getFullYear() - 3) + '年服务数量（次）';


    this.service_research_lastt = parseInt(myDate.getFullYear() - 1) + '年研发投入（万元）';
    this.service_research_beforet = parseInt(myDate.getFullYear() - 2) + '年研发投入（万元）';
    this.service_research_previoust = parseInt(myDate.getFullYear() - 3) + '年研发投入（万元）';


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
      this.mech.picOrgLicense = []
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
        this.mech.picOrgLicense.push(arro)
      }
    },
    async uploadSectionFile1(param) {
      this.mech.picLpLicense = []
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
        this.mech.picLpLicense.push(arro)
      }
    },

    async uploadSectionFile2(param) {
      this.mech.fuben = []
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
        this.mech.fuben.push(arro)
      }
    },

    async uploadSectionFile22(param) {
      this.mech.fuben2 = []
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
        this.mech.fuben2.push(arro)
      }
    },

    async uploadSectionFilepicLmIdCardFront(param) {
      this.mech.picLmIdCardFront = []
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
        this.mech.picLmIdCardFront.push(arro)
      }
    },

    async uploadSectionFilepicLmIdCardBack(param) {
      this.mech.picLmIdCardBack = []
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
        this.mech.picLmIdCardBack.push(arro)
      }
    },

    async uploadSectionFilepicLmIdCardInHand(param) {
      this.mech.picLmIdCardInHand = []
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
        this.mech.picLmIdCardInHand.push(arro)
      }
    },
    async uploadSectionFile3(param) {
      this.mech.picLogo = []
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
        this.mech.picLogo.push(arro)
      }
    },
    back() {
      window.history.go(-1);
    },
    addCN(data) {
      if (JSON.stringify(data.registerNature).includes(1)) data.search_param.push('企业')
      if (JSON.stringify(data.registerNature).includes(2)) data.search_param.push('科研院所')
      if (JSON.stringify(data.registerNature).includes(3)) data.search_param.push('高等院校')

      if (JSON.stringify(data.orgType).includes(1)) data.search_param.push('研究开发')
      if (JSON.stringify(data.orgType).includes(2)) data.search_param.push('科技投融资')
      if (JSON.stringify(data.orgType).includes(3)) data.search_param.push('技术转移')
      if (JSON.stringify(data.orgType).includes(4)) data.search_param.push('检验检测')
      if (JSON.stringify(data.orgType).includes(5)) data.search_param.push('创业孵化')
      if (JSON.stringify(data.orgType).includes(6)) data.search_param.push('知识产权')
      if (JSON.stringify(data.orgType).includes(7)) data.search_param.push('科技评估')
      if (JSON.stringify(data.orgType).includes(8)) data.search_param.push('标准认证')
      if (JSON.stringify(data.orgType).includes(9)) data.search_param.push('管理咨询')
      if (JSON.stringify(data.orgType).includes(10)) data.search_param.push('综合科技服务')


      let arr = data.PorcolumnDefinitions
      let arr1 = []
      let arr2 = []
      for (var i in arr) {
        arr1 = arr[i].registerSupport
        arr2 = arr[i].registerImplement
      }
      if (arr1.includes("1")) data.search_param.push('拨款')
      if (arr1.includes("2")) data.search_param.push('贴息')
      if (arr1.includes("3")) data.search_param.push('减免税')
      if (arr1.includes("4")) data.search_param.push('以上全无')
      if (arr2.includes("1")) data.search_param.push('申请')
      if (arr2.includes("2")) data.search_param.push('在研')
      if (arr2.includes("3")) data.search_param.push('验收/结题')
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
      if (!this.validata.validaMechanism(this.mech)) return
      let picOrgLicense = this.mech.picOrgLicense[0].url
      if (picOrgLicense.indexOf('/user/') > -1) {
        this.mech.picOrgLicense[0].url = picOrgLicense.substring(picOrgLicense.indexOf('/user/') + 1, picOrgLicense.length)
      } else {
        this.mech.picOrgLicense[0].url = picOrgLicense.substring(picOrgLicense.indexOf('/def/') + 1, picOrgLicense.length)
      }
      let picLpLicense = this.mech.picLpLicense[0].url
      if (picLpLicense.indexOf('/user/') > -1) {
        this.mech.picLpLicense[0].url = picLpLicense.substring(picLpLicense.indexOf('/user/') + 1, picLpLicense.length)
      } else {
        this.mech.picLpLicense[0].url = picLpLicense.substring(picLpLicense.indexOf('/def/') + 1, picLpLicense.length)
      }
      let picLmIdCardFront = this.mech.picLmIdCardFront[0].url
      if (picLmIdCardFront.indexOf('/user/') > -1) {
        this.mech.picLmIdCardFront[0].url = picLmIdCardFront.substring(picLmIdCardFront.indexOf('/user/') + 1, picLmIdCardFront.length)
      } else {
        this.mech.picLmIdCardFront[0].url = picLmIdCardFront.substring(picLmIdCardFront.indexOf('/def/') + 1, picLmIdCardFront.length)
      }

      let picLmIdCardBack = this.mech.picLmIdCardBack[0].url
      if (picLmIdCardBack.indexOf('/user/') > -1) {
        this.mech.picLmIdCardBack[0].url = picLmIdCardBack.substring(picLmIdCardBack.indexOf('/user/') + 1, picLmIdCardBack.length)
      } else {
        this.mech.picLmIdCardBack[0].url = picLmIdCardBack.substring(picLmIdCardBack.indexOf('/def/') + 1, picLmIdCardBack.length)
      }

      let picLmIdCardInHand = this.mech.picLmIdCardInHand[0].url
      if (picLmIdCardInHand.indexOf('/user/') > -1) {
        this.mech.picLmIdCardInHand[0].url = picLmIdCardInHand.substring(picLmIdCardInHand.indexOf('/user/') + 1, picLmIdCardInHand.length)
      } else {
        this.mech.picLmIdCardInHand[0].url = picLmIdCardInHand.substring(picLmIdCardInHand.indexOf('/def/') + 1, picLmIdCardInHand.length)
      }

      let picLogo = this.mech.picLogo[0].url
      if (picLogo.indexOf('/user/') > -1) {
        this.mech.picLogo[0].url = picLogo.substring(picLogo.indexOf('/user/') + 1, picLogo.length)
      } else {
        this.mech.picLogo[0].url = picLogo.substring(picLogo.indexOf('/def/') + 1, picLogo.length)
      }

      let arr = {}
      arr.formType = '3'
      arr.checkStatus = checkStatus
      arr.id = this.$route.params.objId
      this.mech.search_param = []
      this.mech = this.addCN(this.mech)
      arr.detail = JSON.stringify(this.mech)
      let {
        data,
        success
      } = await addLib(arr)

      if (success) {
        this.$message({
          message: '保存成功',
          type: 'success'
        });
        this.mech.picOrgLicense[0].url = this.imgBaseUrl +  this.mech.picOrgLicense[0].url
        this.mech.picLpLicense[0].url = this.imgBaseUrl +  this.mech.picLpLicense[0].url
        this.mech.picLmIdCardFront[0].url = this.imgBaseUrl +  this.mech.picLmIdCardFront[0].url
        this.mech.picLmIdCardBack[0].url = this.imgBaseUrl +  this.mech.picLmIdCardBack[0].url
        this.mech.picLmIdCardInHand[0].url = this.imgBaseUrl +  this.mech.picLmIdCardInHand[0].url
        this.mech.picLogo[0].url = this.imgBaseUrl +  this.mech.picLogo[0].url
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
        time: [],
        source: '',
        registerImplement: [],
        registerSupport: [],
      })
    },
    delProjectmech(item) {
      this.mech.PorcolumnDefinitions.splice(this.mech.PorcolumnDefinitions.indexOf(item), 1)
      this.$message({
        type: 'info',
        message: '删除成功'
      });
    },
    addhonrmech() {
      this.mech.honor.push({
        name: '',
        time: '',
        issuingAgency: ''
      })
    },
    delhonrmech(item) {
      this.mech.honor.splice(this.mech.honor.indexOf(item), 1)
      this.$message({
        type: 'info',
        message: '删除成功'
      });
    },
    addcjmech() {
      this.mech.workrPorcolumnDefinitions.push({
        name: '',
        object: '',
        time: []
      })
    },

    delcjmech(item) {
      this.mech.workrPorcolumnDefinitions.splice(this.mech.workrPorcolumnDefinitions.indexOf(item), 1)
      this.$message({
        type: 'info',
        message: '删除成功'
      });
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
