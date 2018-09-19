<template>
<div class="reset-container" style="background: #1896d2">
  <div class="content">
    <h3 class="title">管理员忘记密码</h3>
    <el-form :label-position="labelPosition" label-width="80px" :model="loginVerify">
      <el-form-item label="">
        <el-radio-group v-model="checkStatus">
          <el-radio :label="0">手机号验证</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item v-if="checkStatus == 0" label="">
        <el-input v-model="obj.email" placeholder="请输入邮箱地址"></el-input>
      </el-form-item>
      <el-form-item v-if="checkStatus == 1" label="">
        <el-input v-model="cellphone" placeholder="请输入手机号"></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-input v-model="obj.code" placeholder="请输入验证码" style="width:230px">13</el-input>
        <el-button type="primary" @click="sendcode" :disabled="isDisabled">{{buttonName}}</el-button>
      </el-form-item>
      <el-form-item label="">
        <el-input type="password" v-model="obj.newPassword" placeholder="请输入新密码" style="width:230px">13</el-input>
      </el-form-item>

      <el-form-item label="">
        <el-input type="password" v-model="obj.rePassword" placeholder="请重复输入新密码" style="width:230px"></el-input>
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
  lookPwd,
  sendCellphoneCode
} from '@/api/user'

export default {
  data() {
    return {
      labelPosition: 'top',
      login_method: 'first',
      buttonName: "获取验证码",
      isDisabled: false,
      time: 60,
      obj: {
        email: '',
        code: '',
        newPassword: '',
        rePassword: '',
      },
      obj2: {
        code: '',
        newPassword: ''
      },
      cellphone: '',
      checkStatus: 0
    }
  },
  methods: {
    async sendcode() {
      let me = this;
      me.isDisabled = true;
      let interval = window.setInterval(function() {
        me.buttonName = '（' + me.time + '秒）后重新发送';
        --me.time;
        if (me.time < 0) {
          me.buttonName = "重新发送";
          me.time = 60;
          me.isDisabled = false;
          window.clearInterval(interval);
        }
      }, 1000);

      if (this.checkStatus == 0) {
        const {
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
      } else if (this.checkStatus == 1) {
        this.obj2.code = this.obj.code
        this.obj2.cellphone = this.cellphone
        const {
          message,
          success
        } = await sendCellphoneCode(this.obj2)
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
      }


    },
    async tolookPwd() {
       if (!this.validata.validresetPW(this.obj,this.checkStatus,this.cellphone)) return

        if(this.obj.newPassword != this.obj.rePassword){
          this.$message({
            message: '两次密码输入不一致！',
            type: 'error'
          })
          return
        }
      let objA ={}
      if (this.checkStatus == 0) {
        objA = this.obj
      } else if (this.checkStatus == 1) {
          objA.code = this.obj.code
          objA.newPassword = this.obj.newPassword
          objA.cellphone = this.cellphone
      }

      let {
        message,
        success,
        data
      } = await lookPwd(objA)
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
          message: data,
          type: 'error'
        });
      }

    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import "src/styles/mixin.scss";
.el-radio__input.is-checked+.el-radio__label {
    color: #000000;
}
.el-radio {
    color: #000000;
}
.el-radio__input.is-checked .el-radio__inner {
    border-color: #000;
    background: #000;
}
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
