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
    <div class="content">

      <div class="left-content" :style="{'background-image': `url('${leftImage}')`}"></div>

      <div class="right-content">
        <el-card class="box-card">

          <div slot="header" class="clearfix">
            <span>登录</span>
          </div>

          <div  class="item">
            <el-tabs v-model="login_method" @tab-click="handleClick">
              <!-- 普通登录 -->
              <el-tab-pane label="普通登录" name="first">
                <el-form  :model="loginForm" ref="loginForm" :label-position="labelPosition" label-width="100px">
                  <el-form-item label="用户名">
                    <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
                  </el-form-item>

                  <p class="resetPaw" @click="toresetPaw">忘记密码?</p>

                  <el-form-item label="密码">
                    <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click.native.prevent="handleLogin">登录</el-button>
                    <span @click="toRegister" class="toLogin">立即注册</span>
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

              <!-- 验证登录 -->
              <el-tab-pane label="验证登录" name="second">
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
  isvalidUsername
} from '@/utils/validate'
import {
  getUserMenus
} from '@/api/menu'
import MenuUtils from '@/views/MenuUtils'

import bgImage from '@/assets/images/login-bg.jpg'
import leftImage from '@/assets/images/bg-1.jpg'
import weiXinImage from '@/assets/images/weixin.png'
import weiBoImage from '@/assets/images/weibo.jpg'
import QQImage from '@/assets/images/QQ.png'

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
      bgImage,
      leftImage,
      weiXinImage,
      weiBoImage,
      QQImage,
      labelPosition: 'top',
      login_method: 'first', // 登录方式
      loginVerify: { // 普通登录
        phone: '',
        number: ''
      },
      loginForm: {
        username: 'admin',
        password: 'admin'
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
      pwdType: 'password'
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    },
    toresetPaw(){
      this.$router.push({
        path: '/resetPaw'
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
    // login(data) {
    //   // MenuUtils(routers, data)
    //
    // },
    forload(treeData) {
      for (let i = 0; i < treeData.length; i++) {
        if (treeData[i].children) {
          if (treeData[i].children.length > 0) { //有下级
            treeData[i].meta = {
              title: treeData[i].label,
              icon: 'tree'
            }
            treeData[i].path = '/' + treeData[i].menuUrl
            treeData[i].leaf = false
            treeData[i].component = treeData[i].menuUrl
            if (treeData[i].level == 1) { //有下级 第一个菜单
              treeData[i].component = 'layout'
              treeData[i].redirect = '/' + treeData[i].menuUrl + '/' + treeData[i].children[0].menuUrl + ''
            }
          } else if (treeData[i].children.length == 0 && treeData[i].level == 1) { //第一层菜单无下级生成一个
            let chil = {}
            chil.meta = {
              title: treeData[i].label,
              icon: 'table'
            }
            chil.path = treeData[i].menuUrl
            chil.leaf = true
            chil.component = treeData[i].menuUrl
            treeData[i].children.push(chil)

            treeData[i].path = '/index'
            treeData[i].leaf = false
            treeData[i].component = 'layout'
          } else if (treeData[i].children.length == 0 && treeData[i].level != 1) {
            treeData[i].meta = {
              title: treeData[i].label,
              icon: 'table'
            }
            treeData[i].path = treeData[i].menuUrl
            treeData[i].leaf = true
            treeData[i].component = treeData[i].menuUrl

          }
          treeData[i].children = this.forload(treeData[i].children)
        }
      }
      return treeData
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true

          this.$store.dispatch('Login', this.loginForm).then(async () => {

            if (this.$store.getters.token && this.$store.getters.token != undefined && this.$store.getters.token != '') {

              this.loading = false
              window.sessionStorage.setItem('user', JSON.stringify('true'))
              this.$router.push({
                path: '/'
              })
            } else {
              this.$message({
                message: this.$store.getters.message,
                type: 'warning'
              });
            }

          }).catch(() => {
            this.loading = false
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import "src/styles/mixin.scss";
.login-container{
  width: 100%;
  height: 100vh;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center center;
  background-attachment: fixed;
  position: relative;
  z-index:1;
  overflow: hidden;
  font-size: 20px;

  .content{
    width: 986px;
    height:518px;
    position: absolute;
    top: 50%;
    left: 50%;
    z-index: 2;
    // border:1px solid #ccc;
    transform: translate(-50%,-50%);

    .left-content,.right-content,{
      width: 500px;
      height: 500px;
      // border: 2px dashed  #CBBD63;
      // background-color: rgba(225, 255, 255, 0.1);
      -webkit-filter: drop-shadow(4px 4px 4px #CBBD63);
      filter: drop-shadow(4px 4px 4px #CBBD63);

    }

    .left-content{
      position: absolute;
      left: 0;
      top:0;
      z-index: 2;
      background-repeat: no-repeat;
      background-position: center center;
      background-size: cover;
    }

    .right-content{
      position: absolute;
      right: 0;
      bottom:0;
      z-index: 3;

      .box-card{
        .clearfix{

        }
        .item{
          position: relative;
          // tabs
          .el-tabs__nav-wrap{
            width: 180px;
            padding-left: 12px;
            overflow: hidden;
          }
          // lable
          .el-form-item__label{
            display: block;
            margin-left: 60px;
            height: 36px;
            // float: left;
            color: #9ba3af;
          }
          .resetPaw{
            font-size: 14px;
            color: #9ba3af;
            float: right;
            margin-right: 60px;
            cursor: pointer;
          }

          // input-box
          .el-form-item__content{
            width: 340px;

            .el-button{
              width: 100px;
            }
          }
          .toLogin{
            display: inline-block;
            margin-left: 20px;
            color: darkslateblue;
            cursor: pointer;
            font-size: 14px;
          }
          .loginQuick{
            margin-top: 30px;
            p{
              // display: block;
              height: 24px;
              line-height: 24px;
              color: #9ba3af;
              font-size: 14px;

              &::before,&::after{
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
              &::before{
                left: 20%;
              }
              &::after{
                right:20%;
              }
              span{
                display: inline-block;
                margin-top: -10px;
              }
            }
            .quick-content{
              ul{
                list-style: none;
                li{
                  width: 50px;
                  height: 50px;
                  display: inline-block;
                  cursor: pointer;
                  img{
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
