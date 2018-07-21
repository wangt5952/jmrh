<template>
<div class="register-container" style="background: #1896d2">
  <div class="content">

    <!-- <div class="left-content" :style="{'background-image': `url('${leftImage}')`}"></div> -->

    <div class="right-content">
      <el-card class="box-card">

        <div slot="header" class="clearfix">
          <span>注册</span>
        </div>

        <div class="item">
          <el-form :model="registerForm" :label-position="labelPosition" label-width="100px">

            <el-form-item label="">
              <div class="paddingb textl paddingr">
                <el-radio v-model="radioData" label="1">个人</el-radio>
                <el-radio v-model="radioData" label="2">企业</el-radio>
                <el-radio v-model="radioData" label="3">服务</el-radio>
              </div>
            </el-form-item>
            <el-form-item label="">
              <el-input type="text" v-model="registerForm.email" placeholder="请输入邮箱">
                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
              </el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input type="text" v-model="registerForm.ecode" placeholder="请输入密码">
                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
              </el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input type="text" v-model="registerForm.ecode" placeholder="请再次输入密码">
                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
              </el-input>
            </el-form-item>

            <div class="" v-if="radioData == '1'">
              <el-form-item label="">
                <el-input type="text" v-model="registerForm.ecode" placeholder="请输入姓名">
                  <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
                </el-input>
              </el-form-item>

              <el-form-item label="">
                <el-input type="text" v-model="registerForm.ecode" placeholder="请输入身份证号">
                  <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
                </el-input>
              </el-form-item>
            </div>

            <div class="" v-if="radioData == '2'">
              <el-form-item label="">
                <el-input type="text" v-model="registerForm.ecode" placeholder="请输入企业名称">
                  <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
                </el-input>
              </el-form-item>

              <el-form-item label="">
                <el-input type="text" v-model="registerForm.ecode" placeholder="请输入营业执照">
                  <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
                </el-input>
              </el-form-item>
            </div>



            <div class="" v-if="radioData == '3'">
              <el-form-item label="">
                <el-input type="text" v-model="registerForm.ecode" placeholder="请输入单位名称">
                  <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
                </el-input>
              </el-form-item>

              <el-form-item label="">
                <el-input type="text" v-model="registerForm.ecode" placeholder="请输入机构代码">
                  <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
                </el-input>
              </el-form-item>
            </div>

            <el-form-item>
              <el-button @click="toRegisterDetail" type="primary">下一步</el-button>
              <span @click="toLogin" class="toLogin">立即登录</span>
            </el-form-item>
          </el-form>
          <!-- <div class="loginQuick">
                  <p><span>快速登录</span></p>
                  <div class="quick-content">
                    <ul class="quickList">
                      <li><img :src="QQImage"/></li>
                      <li><img :src="weiXinImage"/></li>
                    </ul>
                  </div>
                </div> -->
          </el-tab-pane>

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
export default {
  data() {
    return {
      labelPosition: 'top',
      login_method: 'first', // 登录方式
      radioData: '1',
      loginVerify: { // 普通登录
        phone: '',
        verity: ''
      },
      registerForm: { // 普通登录
        email: '',
        ecode: ''
      }
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
    toRegisterDetail() {
        this.$router.push(
        {name: 'registerDetail', params: {radioData: this.radioData}}
      )
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import "src/styles/mixin.scss";
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

    .content {
        width: 986px;
        height: 518px;
        position: absolute;
        top: 50%;
        left: 50%;
        z-index: 2;
        // border:1px solid #ccc;
        transform: translate(-50%,-50%);
        .left-content,
        .right-content {
            width: 500px;
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
