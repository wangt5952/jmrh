<template>
<div class="register-container" style="background: #1896d2">
  <div class="content">

    <!-- <div class="left-content" :style="{'background-image': `url('${leftImage}')`}"></div> -->

    <div class="detail-content">
      <el-card class="box-card">

        <div slot="header" class="clearfix">
          <span> <span v-if="radioData == '1'">个人</span> <span v-if="radioData == '2'">企业</span><span v-if="radioData == '3'">服务</span>注册</span>
        </div>

        <div class="item">
          <div v-if="radioData == '1'" class="">
            <el-form class="" label-width="30%" style="text-align:left">
              <el-row :gutter="20">
                <el-col :span="10">
                  <el-form-item label="姓名">
                    <el-input placeholder="请输入姓名" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="性别">
                    <el-select style="width:100px" placeholder="请选择">
                      <el-option label="男" key="1" value='1'>
                      </el-option>
                      <el-option label="女" key="0" value='0'>
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="出生日期">
                    <el-date-picker v-model="dateValue" type="date" placeholder="选择日期">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="所在地区">
                    <area-cascader :level="1" v-model="selected" :data="pcaa"></area-cascader>
                    <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                  </el-form-item>
                  <el-form-item label="联系地址">
                    <el-input placeholder="请输入联系地址" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="邮箱">
                    <el-input placeholder="请输入邮箱" style="width:80%"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="10">
                  <el-form-item label="身份证号">
                    <el-input placeholder="请输入身份证号" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="上传身份证正面">
                    <div>
                      <!--这是正面照-->
                      <div class="photo">
                        <a href="javascript">
                      <input type="file" id="IdCard">
                      </a>
                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="上传身份证反面">
                    <div>
                      <!--这是背面照-->
                      <div class="photo photo1">
                        <a href="javascript">
                        <input type="file" id="IdCardbg">
                            </a>
                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="手持身份证">
                    <div>
                      <!--这是背面照-->
                      <div class="photo photo1">
                        <a href="javascript">
                        <input type="file" id="IdCardbg">
                            </a>
                      </div>
                    </div>
                  </el-form-item>

                  <el-form-item label="手机号">
                    <el-input placeholder="请输入手机号" style="width:80%"></el-input>
                  </el-form-item>

                </el-col>

              </el-row>
              <el-row>
                <div style="text-align:center">
                  <el-button type="primary" style="width: 120px;">完善并保存</el-button>
                  <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
                </div>
              </el-row>
            </el-form>
          </div>


          <div v-if="radioData == '2'" class="">


            <el-tabs type="border-card">
              <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i> 联系人信息</span>

                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="10">
                      <el-form-item label="姓名">
                        <el-input placeholder="请输入姓名" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职务">
                        <el-input placeholder="请输入职务" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">

                      <el-form-item label="所在地区">
                        <area-cascader :level="1" v-model="selected" :data="pcaa"></area-cascader>
                        <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                      </el-form-item>
                      <el-form-item label="联系地址">
                        <el-input placeholder="请输入联系地址" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮编">
                        <el-input placeholder="请输入邮编" style="width:80%"></el-input>
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
                        <el-input placeholder="请输入姓名" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职务">
                        <el-input placeholder="请输入职务" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="企业信息">
                <el-form class="" label-width="40%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="企业规模（注册资金）">
                        <el-radio v-model="registerFinance" label="1">小于2000万（含）</el-radio>
                        <el-radio v-model="registerFinance" label="2">2000-5000万</el-radio>
                        <el-radio v-model="registerFinance" label="3">5000-1亿（含）</el-radio>
                        <el-radio v-model="registerFinance" label="4">1亿-2亿（含）</el-radio>
                        <el-radio v-model="registerFinance" label="5">2亿-4亿（含）</el-radio>
                        <el-radio v-model="registerFinance" label="6">4亿及以上（含）</el-radio>
                      </el-form-item>
                      <el-form-item label="注册时间">
                        <el-date-picker v-model="registerValue" type="date" placeholder="选择日期">
                        </el-date-picker>
                      </el-form-item>
                      <el-form-item label="注册类型">
                        <el-radio v-model="registerType" label="1">内资企业</el-radio>
                        <el-radio v-model="registerType" label="2">合资企业</el-radio>
                        <el-radio v-model="registerType" label="3">外资企业</el-radio>
                      </el-form-item>
                      <el-form-item label="是否高新技术企业">
                        <el-radio v-model="registerH" label="1">是</el-radio>
                        <el-radio v-model="registerH" label="2">否</el-radio>
                      </el-form-item>
                      <el-form-item label="企业所在地性质">
                        <el-radio v-model="registerSite" label="1">国家级高新区  </el-radio>
                        <el-radio v-model="registerSite" label="2">省级高新区</el-radio>
                        <el-radio v-model="registerSite" label="3">国家级经开区 </el-radio>
                        <el-radio v-model="registerSite" label="4">省级经开区</el-radio>
                        <el-radio v-model="registerSite" label="5">其他</el-radio>
                      </el-form-item>
                      <el-form-item label="所在国家高新区">
                        <el-radio v-model="registerHSite" label="1">南京高新区</el-radio>
                        <el-radio v-model="registerHSite" label="2">无锡高新区</el-radio>
                        <el-radio v-model="registerHSite" label="3">昆山高新区 </el-radio>
                        <el-radio v-model="registerHSite" label="4">徐州高新区</el-radio>
                        <el-radio v-model="registerHSite" label="5">其他</el-radio>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>


              </el-tab-pane>
              <el-tab-pane label="其他信息">其他信息</el-tab-pane>
            </el-tabs>


            <el-row>
              <div style="text-align:center;margin-top: 20px;">
                <el-button type="primary" style="width: 120px;">完善并保存</el-button>
                <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
              </div>
            </el-row>

          </div>

        </div>

      </el-card>
    </div>
    <!-- <div class="center-content"></div> -->
    <!-- <div class="right-top"></div> -->
    <!-- <div class="left-bottom"></div> -->
  </div>
</div>
</template>

<script>
import {
  pca,
  pcaa
} from "area-data";
export default {
  data() {
    return {
      dateValue: '',
      radioData: '2',
      registerFinance: '1',
      registerType: '1',
      registerH : '1',
      registerSite : '1',
      registerHSite : '1',
      registerValue:'',
      loginVerify: { // 普通登录
        phone: '',
        verity: ''
      },
      selected: [],
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区

    }
  },
  mounted() {
    if (this.$route.params.radioData) {
      this.radioData = this.$route.params.radioData
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    },
    toLogin() {
      this.$router.push({
        path: '/login'
      })
    },
    toRegister() {
      if (this.login_method == 'first') {
        if (!this.validata.validatoRegistere(this.registerForm)) return
        this.$message({
          message: '1',
          type: 'warning'
        });
      } else if (this.login_method == 'second') {
        if (!this.validata.validatoRegisterp(this.loginVerify)) return
        this.$message({
          message: '2',
          type: 'warning'
        });
      }

    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import "src/styles/mixin.scss";
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
    padding: 0 0 0 12px;
}
.area-select {
    position: relative;
    display: inline-block;
    vertical-align: top;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    height: 40px;
    cursor: pointer;
    background: #fff;
    border-radius: 4px;
    border: 1px solid #e1e2e6;
}
.register-container {
    width: 100%;
    height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center center;
    background-attachment: fixed;
    position: relative;
    z-index: 1;
    overflow: hidden;
    font-size: 20px;
    .el-radio {
        margin: 0;
    }
    .el-tabs__content {
    overflow: auto;
    height: 400px;
    position: relative;
}
    .content {
        width: 986px;
        height: 518px;
        position: absolute;
        top: 50%;
        left: 45%;
        z-index: 2;
        // border:1px solid #ccc;
        transform: translate(-50%,-50%);
        .detail-content,
        .left-content {
            width: 1200px;
            height: 500px;
            // border: 2px dashed  #CBBD63;
            // background-color: rgba(225, 255, 255, 0.1);
            -webkit-filter: drop-shadow(4px 4px 4px #CBBD63);
            filter: drop-shadow(4px 4px 4px #CBBD63);

        }

        .left-content {
            position: absolute;
            left: 0;
            top: 0;
            z-index: 2;
            background-repeat: no-repeat;
            background-position: center center;
            background-size: cover;
        }

        .right-content {
            margin: 0 auto;
            // background-color: rgba(225, 255, 255, 0.6);
            z-index: 3;

            .box-card {
                .clearfix {}
                .item {
                    position: relative;
                    // tabs
                    .el-form-item {
                        margin-top: -12px;
                    }
                    .el-tabs__nav-wrap {
                        width: 180px;
                        padding-left: 12px;
                        overflow: hidden;
                    }
                    // lable
                    .el-form-item__label {
                        display: block;
                        margin-left: 60px;
                        height: 36px;
                        color: #9ba3af;
                    }

                    // input-box
                    .el-form-item__content {
                        width: 340px;

                        .el-button {
                            width: 100px;
                        }
                    }
                    .toLogin {
                        display: inline-block;
                        margin-left: 20px;
                        color: darkslateblue;
                        cursor: pointer;
                        font-size: 14px;
                    }
                }
            }
        }

    }
}
</style>
