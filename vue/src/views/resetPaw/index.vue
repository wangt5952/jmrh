<template>
<div class="register-container" style="background: #1896d2">
  <div class="content">
    <h3 class="title">忘记密码</h3>
    <el-form :label-position="labelPosition" label-width="80px" :model="loginVerify">
      <el-form-item label="">
        <el-input v-model="loginVerify.phone" placeholder="请输入邮箱地址"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input v-model="loginVerify.verity" placeholder="请输入验证码" style="width:230px">13</el-input>
        <el-button type="primary">获取验证码</el-button>
      </el-form-item>
      <el-form-item>
        <el-button @click="toRegister" type="primary">下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
import bgImage from '@/assets/images/login-bg.jpg'
import leftImage from '@/assets/images/bg-1.jpg'
import weiXinImage from '@/assets/images/weixin.png'
import weiBoImage from '@/assets/images/weibo.jpg'
import QQImage from '@/assets/images/QQ.png'
export default {
  data() {
    return {
      bgImage,
      leftImage,
      weiXinImage,
      weiBoImage,
      QQImage,
      labelPosition: 'top',
      login_method: 'first', // 登录方式
      loginVerify: { // 普通登录
        phone: '',
        verity: ''
      },
      registerForm: { // 普通登录
        email: '',
        ecode :''
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
    toRegister() {
      if(this.login_method == 'first'){
        if (!this.validata.validatoRegistere(this.registerForm)) return
        this.$message({
          message: '1',
          type: 'warning'
        });
      }else if(this.login_method == 'second'){
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
            position: absolute;
            right: 0;
            bottom: 0;
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
