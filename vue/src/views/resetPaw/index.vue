<template>
<div class="reset-container" style="background: #1896d2">
  <div class="content">
    <h3 class="title">忘记密码</h3>
    <el-form :label-position="labelPosition" label-width="80px" :model="loginVerify">
      <el-form-item label="">
        <el-input v-model="obj.email" placeholder="请输入邮箱地址"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input v-model="obj.code" placeholder="请输入验证码" style="width:230px">13</el-input>
        <el-button type="primary" @click="sendcode">获取验证码</el-button>
      </el-form-item>
      <el-form-item label="">
        <el-input v-model="obj.newPassword" placeholder="请输入新密码" style="width:230px">13</el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="tolookPwd" type="primary">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
import {
  sendEmailCode,
  lookPwd
} from '@/api/user'

export default {
  data() {
    return {
      labelPosition: 'top',
      login_method: 'first',
      obj: {
        email: '',
        code: '',
        newPassword:''
      },

    }
  },
  methods: {
    async sendcode() {
      let {
        message,
        success
      } = await sendEmailCode(this.obj)
      if (success) {
        this.$message({
          message: '发送成功',
          type: 'success'
        });
      } else {
        this.$message({
          message: '发送失败',
          type: 'success'
        });
      }

    },
    async tolookPwd() {
      let {
        message,
        success
      } = await lookPwd(this.obj)
      if (success) {
        this.$message({
          message: '修改成功',
          type: 'success'
        });
        this.$router.push({
          path: '/'
        })
      } else {
        this.$message({
          message: '修改失败',
          type: 'success'
        });
      }

    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import "src/styles/mixin.scss";
.reset-container {
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
