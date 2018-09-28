<!-- <template>
<div class="login-container">
  <el-form autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left" label-width="0px" class="card-box login-form">
    <h3 class="title">协同创新平台</h3>
    <el-form-item prop="username">
      <span class="svg-container svg-container_login">
          <svg-icon icon-class="user"/>
        </span>
      <el-input name="username" v-on:change="change" type="text" v-model="loginForm.username" autoComplete="on" placeholder="username" />
    </el-form-item>
    <el-form-item prop="password">
      <span class="svg-container">
          <svg-icon icon-class="password"></svg-icon>
        </span>
      <el-input name="password" v-on:change="change" :type="pwdType" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on" placeholder="password"></el-input>
      <span class="show-pwd" @click="showPwd"><svg-icon icon-class="eye" /></span>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" style="width:100%;" :loading="loading" @click.native.prevent="handleLogin">
        登陆
      </el-button>
      <span @click="toRegister" class="toLogin">立即注册</span>
    </el-form-item>
  </el-form>
</div>
</template> -->


<template>
<div class="login-container" style="background: #1896d2">
  <div class="content" style="top: 50%;">

    <div class="left-content" :style="{'background-image': `url('${leftImage}')`}"></div>

    <div class="right-content">
      <el-card class="box-card">

        <div slot="header" class="clearfix">
          <span>登录</span>
        </div>

        <div class="item">
          <!-- <el-tabs v-model="login_method" @tab-click="handleClick">

              <el-tab-pane label="普通登录" name="first"> -->
          <el-form :model="loginForm" :rules="loginRules" ref="loginForm" :label-position="labelPosition" label-width="100px">
            <el-form-item label="">
              <el-input v-model="loginForm.userName" placeholder="请输入用户名">
                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
            </el-form-item>


            <el-form-item label="" style="margin: 0;">
              <el-input type="password" v-model="loginForm.password" placeholder="请输入密码">

                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
              </el-input>
            </el-form-item>

            <el-form-item label="" style="margin: 0;">
              <el-checkbox v-model="loginForm.isAdmin" style="float: left;">管理员</el-checkbox>
            </el-form-item>
            <el-form-item>
              <el-button v-if="!restEmail" @keyup.enter="handleLogin" type="primary" @click.native.prevent="handleLogin">登录</el-button>
              <el-button v-if="restEmail" @click="tosendEmailActivate" type="primary">重新发送</el-button>
              <span @click="toRegister" class="toLogin">立即注册</span>
              <span @click="toresetPawManage" v-show="loginForm.isAdmin" class="resetPaw">忘记密码?</span>
              <span @click="toresetPaw" v-show="!loginForm.isAdmin" class="resetPaw">忘记密码?</span>
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
          <!-- </el-tab-pane> -->

          <!-- 验证登录 -->
          <!--  <el-tab-pane label="验证登录" name="second">
                <el-form :label-position="labelPosition" label-width="80px" :model="loginVerify">
                  <el-form-item label="手机号码">
                    <el-input v-model="loginVerify.phone" placeholder="请输入手机号"></el-input>
                  </el-form-item>
                  <el-form-item label="验证码">
                    <el-input v-model="loginVerify.verity" placeholder="请输入验证码" style="width:230px">13</el-input>
                    <el-button type="text">获取验证码</el-button>
                  </el-form-item>
                  <el-form-item>
                  <el-button type="primary">登录</el-button>
                  <span @click="toRegister" class="toLogin">立即注册</span>
                </el-form-item>
                </el-form>
               <div class="loginQuick">
                  <p><span>快速登录</span></p>
                  <div class="quick-content">
                    <ul class="quickList">
                      <li><img :src="QQImage"/></li>
                      <li><img :src="weiXinImage"/></li>
                    </ul>
                  </div>
                </div>
             </el-tab-pane> -->
          </el-tabs>
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
  registers,
  sendEmailActivate
} from '@/api/login'
import {
  isvalidUsername
} from '@/utils/validate'
import {
  getUserMenusone
} from '@/api/menu'
import MenuUtils from '@/views/MenuUtils'

import leftImage from '@/assets/images/bg-1.jpg'

var routers = []
export default {
  name: 'login',
  data() {
    const validateUsername = (rule, value, callback) => {
      // if (!isvalidUsername(value)) {
      //   callback(new Error('请输入正确的用户名'))
      // } else {
      callback()
      // }
    }
    const validatePass = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能小于5位'))
      } else {
        callback()
      }
    }
    return {
      leftImage,
      restEmail: false,
      labelPosition: 'top',
      login_method: 'first', // 登录方式
      loginVerify: { // 普通登录
        phone: '',
        number: ''
      },
      loginForm: {
        userName: '',
        password: '',
        isAdmin: false,
      },
      loginRules: {
        username: [{
          required: true,
          trigger: 'blur',
          validator: validateUsername
        }],
        password: [{
          required: true,
          trigger: 'blur',
          validator: validatePass
        }]
      },
      loading: false,
      pwdType: 'password',
      callbackUrl: ''
    }
  },
  created() {
    this.keyupSubmit()

  },
  mounted() {
    if (this.$route.query.callback) {
      this.callbackUrl = this.$route.query.callback
    }
  },
  methods: {
    keyupSubmit() {
      document.onkeydown = e => {
        let _key = window.event.keyCode;
        if (_key === 13) {
          this.handleLogin()
        }
      }
    },
    async tosendEmailActivate() {
      let obj = {}
      obj.email = this.loginForm.userName
      let {
        data,
        success,
        message
      } = await sendEmailActivate(obj)
      if (success) {
        const h = this.$createElement;
        this.$notify({
          title: '用户激活提醒',
          message: h('i', {
            style: 'color: teal'
          }, '激活帐户邮件已发送到你的邮箱中' + this.loginForm.userName + '，点击里面的激活链接')
        });
        this.restEmail = false
      } else {
        this.$message({
          message: message,
          type: 'success'
        });
      }
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    toresetPaw() {
      this.$router.push({
        path: '/resetPaw'
      })
    },
    toresetPawManage() {
      this.$router.push({
        path: '/resetPawManage'
      })
    },
    toRegister() {
      this.$router.push({
        path: '/register'
      })
    },
    change(value) {
      // 目的是去掉输入框中的小数点
      // if (this.value) {
      //   this.value = parseInt(value);
      // }
      this.loading = false
    },
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    login(data) {
      window.localStorage.setItem('user', JSON.stringify(data))
      MenuUtils(routers, data)
    },
    forload(treeData) {
      let userType = window.sessionStorage.getItem('userType')
      for (let i = 0; i < treeData.length; i++) {
        if (treeData[i].children && treeData[i].children.length > 0 && treeData[i].leaf == false) { //有下级
          treeData[i].meta = {
            title: treeData[i].label,
            icon: 'tree'
          }
          treeData[i].name = treeData[i].label
          treeData[i].path = '/' + treeData[i].menuUrl
          treeData[i].leaf = false
          treeData[i].component = treeData[i].menuUrl
          if (treeData[i].isMenu == "1") { //有下级 第一个菜单
            treeData[i].component = 'layout'
            treeData[i].redirect = '/' + treeData[i].menuUrl + '/' + treeData[i].children[0].menuUrl + ''
          }

          if (userType != '0' && userType != '101' && treeData[i].menuUrl != 'dashboard') treeData[i].hidden = true

        } else if (treeData[i].leaf == true && treeData[i].isMenu == "1" && treeData[i].sortOrder != 9999 && treeData[i].menuLevel != 1) {
          treeData[i].meta = {
            title: treeData[i].label,
            icon: 'table'
          }
          treeData[i].name = treeData[i].label
          treeData[i].path = treeData[i].menuUrl
          treeData[i].leaf = true
          if (userType != '0' && userType != '101') treeData[i].hidden = true

          treeData[i].component = treeData[i].menuUrl
          treeData[i].children = []
        } else if (treeData[i].leaf == true && treeData[i].isMenu == "1" && treeData[i].sortOrder == 9999 && treeData[i].menuLevel != 1) {
          treeData[i].meta = {
            title: treeData[i].label,
            icon: 'table'
          }
          treeData[i].path = treeData[i].menuUrl
          treeData[i].name = treeData[i].menuUrl
          treeData[i].leaf = true
          treeData[i].hidden = true
          treeData[i].component = treeData[i].menuUrl
          treeData[i].children = []
        } else if (treeData[i].menuLevel == 1 && treeData[i].isMenu == "1" && treeData[i].leaf == true) { //第一层菜单无下级生成一个
          let chil = {}
          chil.meta = {
            title: treeData[i].label,
            icon: 'table'
          }
          treeData[i].name = treeData[i].label
          chil.path = treeData[i].menuUrl
          chil.leaf = true
          chil.component = treeData[i].menuUrl
          treeData[i].children = []
          treeData[i].children.push(chil)

          treeData[i].path = '/index'
          treeData[i].leaf = false
          treeData[i].component = 'layout'
        }
        if (treeData[i].children) {
          treeData[i].children = this.forload(treeData[i].children)
        } else {
          continue
        }
      }
      return treeData
    },
    handleLogin() {
      this.loading = true
      let token = window.sessionStorage.getItem('token')//过滤 仅限第一次获取菜单
      this.$store.dispatch('Login', this.loginForm).then(async () => {

        if (this.$store.getters.token && this.$store.getters.token != undefined && this.$store.getters.token != '') {


          window.localStorage.setItem('user', JSON.stringify('true'))//没有进行菜单获取时的进入权限
          if (!token && this.$store.getters.token != undefined) {
            let data = await getUserMenusone()
            let treeData = data.data
            window.sessionStorage.setItem('treeData', JSON.stringify(treeData)) //必须传入 路由进行渲染
            if (treeData) {
              treeData = this.forload(treeData)
              this.login(treeData)
              this.$router.addRoutes(routers)
            }
          }

          if (this.callbackUrl != '') {
            if (this.callbackUrl.indexOf('?') > -1) {
              var url = "http://" + this.callbackUrl + "&token=" + window.sessionStorage.getItem('token') + "&checkStatus=" + window.sessionStorage.getItem('checkStatus') + "&userName=" + window.sessionStorage.getItem('userName') + "&userId=" +
                window.sessionStorage.getItem('userId') + "&userType=" + window.sessionStorage.getItem('userType') + "&cellphone=" + window.sessionStorage.getItem('cellphone') + "&email=" + window.sessionStorage.getItem('email') + "&orgType=" +
                window.sessionStorage.getItem('orgType')
            } else {
              var url = "http://" + this.callbackUrl + "?token=" + window.sessionStorage.getItem('token') + "&checkStatus=" + window.sessionStorage.getItem('checkStatus') + "&userName=" + window.sessionStorage.getItem('userName') + "&userId=" +
                window.sessionStorage.getItem('userId') + "&userType=" + window.sessionStorage.getItem('userType') + "&cellphone=" + window.sessionStorage.getItem('cellphone') + "&email=" + window.sessionStorage.getItem('email') + "&orgType=" +
                window.sessionStorage.getItem('orgType')
            }
            window.location.href = url
          } else {
debugger
            this.$router.push({
              path: '/'
            })
          }


        } else {
          // this.$message({
          //   message: this.$store.getters.message,
          //   type: 'warning'
          // });
          if (this.$store.getters.message == '用户未激活,请重新发送邮箱进行激活！') {
            this.restEmail = true
          }
        }

      }).catch(() => {
        this.loading = false
      })

    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import "src/styles/mixin.scss";
.login-container {
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
            bottom: -6%;
            z-index: 3;

            .box-card {
                .clearfix {}
                .item {
                    position: relative;
                    // tabs
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
                        // float: left;
                        color: #9ba3af;
                    }
                    .resetPaw {
                        font-size: 14px;
                        color: #9ba3af;
                        float: right;
                        margin-right: 60px;
                        cursor: pointer;
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
                    .loginQuick {
                        margin-top: 30px;
                        p {
                            // display: block;
                            height: 24px;
                            line-height: 24px;
                            color: #9ba3af;
                            font-size: 14px;

                            &::after,
                            &::before {
                                content: "";
                                position: absolute;
                                height: 1px;
                                width: 20%;
                                background-color: #eee;
                                margin-top: 10px;
                                // box-shadow: 0px 0px 1px 0.2px #ccc;
                                // display: block;
                                // top: 50%;left: 50%;
                                // transform: translate(-50%,-50%)

                            }
                            &::before {
                                left: 20%;
                            }
                            &::after {
                                right: 20%;
                            }
                            span {
                                display: inline-block;
                                margin-top: -10px;
                            }
                        }
                        .quick-content {
                            ul {
                                list-style: none;
                                li {
                                    width: 50px;
                                    height: 50px;
                                    display: inline-block;
                                    cursor: pointer;
                                    img {
                                        width: 40px;
                                        height: 40px;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
</style>
