<template>
<div style="background:#f0f2f5;margin-top: -20px;">
  <div class="wscn-http404">
    <div class="pic-404" style="text-align: center;width: 100%;">
      <div v-if="pic == '1'" class="el-icon-success" style="font-size: 170px;color: #229a37;">

      </div>
      <div v-if="pic == '2'" class="el-icon-error" style="font-size: 170px;color: #229a37;">

      </div>
      <div class="bullshit__oops">{{text}}</div>
      <div class="">
        <!-- <div v-if="pic == '2'" class="" style="padding:10px">
          <a class="bullshit__return-home" @click="tosendEmailActivate">重新发送邮件</a>
        </div> -->
        <div class="">
          <a class="bullshit__return-home" @click="toIndex">不加入平台直接登录</a>
        </div>

        <div v-if="pic == '1'" class="" style="padding:10px">
          <a class="bullshit__return-home" @click="toNext">下一步，加入平台</a>
        </div>
      </div>

    </div>

  </div>
</div>
</template>

<script>
import img_404 from '@/assets/404_images/404.png'
import img_404_cloud from '@/assets/404_images/404_cloud.png'
import {
  activate,
} from '@/api/login'

import {
  getToken,
  setToken,
  removeToken
} from '@/utils/auth'

export default {
  data() {
    return {
      img_404,
      img_404_cloud,
      text: '',
      pic: '',
      userType: '',
      code:''
    }
  },
  computed: {

  },
  mounted() {
    this.loadPageList()
      debugger
  },
  methods: {
    toIndex() {
      this.$router.push({
        path: '/'
      })
    },
    toNext() {
      this.$router.push({
        name: 'registerDetail',
        params: {
          userType: this.userType,
          code: this.code
        }
      })

    },
    async loadPageList() {
      if (this.$route.query.activateId) {
        let {
          data,
          success,
          message
        } = await activate(this.$route.query.activateId)
        if (success) {
          this.text = message
          this.pic = '1'
          this.userType = data.userType
          this.code = data.code
          setToken(data.token)
          this.$store.commit('SET_TOKEN', {
            token: data.token
          })
          this.$store.commit('SET_NAME', {
            userName: data.userName
          })
          this.$store.commit('SET_ROLES', {
            userType: data.userType
          })
          window.sessionStorage.setItem('token',data.token)
          window.sessionStorage.setItem('userName', data.userName)
          window.sessionStorage.setItem('userId', data.userId)
          window.sessionStorage.setItem('userType', data.userType)
          window.sessionStorage.setItem('name', data.name)
          window.sessionStorage.setItem('code', data.code)
        } else {
          this.text = message
          this.pic = '2'
        }
      }
    },
    // async tosendEmailActivate(){
    //   let {
    //     data,
    //     success,
    //     message
    //   } = await sendEmailActivate(this.registerForm)
    //   if (success) {
    //     this.$message({
    //       message: message,
    //       type: 'success'
    //     });
    //   }else {
    //     this.$message({
    //       message: message,
    //       type: 'success'
    //     });
    //   }
    // },
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.wscn-http404 .bullshit__return-home {
    display: initial!important;
    float: none!important;
    padding: 10px;
    width: 110px;
    height: 36px;
    background: #1482f0;
    border-radius: 100px;
    text-align: center;
    color: #ffffff;
    opacity: 0;
    font-size: 14px;
    line-height: 36px;
    cursor: pointer;
    -webkit-animation-name: slideUp-data-v-1ccd3646;
    animation-name: slideUp-data-v-1ccd3646;
    -webkit-animation-duration: 0.5s;
    animation-duration: 0.5s;
    -webkit-animation-delay: 0.3s;
    animation-delay: 0.3s;
    -webkit-animation-fill-mode: forwards;
    animation-fill-mode: forwards;
}
.wscn-http404 {
    position: relative;
    width: 1200px;
    margin: 20px auto 60px;
    padding: 0 100px;
    overflow: hidden;
    .pic-404 {
        position: relative;
        float: left;
        width: 600px;
        padding: 150px 0;
        overflow: hidden;
        &__parent {
            width: 100%;
        }
        &__child {
            position: absolute;
            &.left {
                width: 80px;
                top: 17px;
                left: 220px;
                opacity: 0;
                animation-name: cloudLeft;
                animation-duration: 2s;
                animation-timing-function: linear;
                animation-fill-mode: forwards;
                animation-delay: 1s;
            }
            &.mid {
                width: 46px;
                top: 10px;
                left: 420px;
                opacity: 0;
                animation-name: cloudMid;
                animation-duration: 2s;
                animation-timing-function: linear;
                animation-fill-mode: forwards;
                animation-delay: 1.2s;
            }
            &.right {
                width: 62px;
                top: 100px;
                left: 500px;
                opacity: 0;
                animation-name: cloudRight;
                animation-duration: 2s;
                animation-timing-function: linear;
                animation-fill-mode: forwards;
                animation-delay: 1s;
            }
            @keyframes cloudLeft {
                0% {
                    top: 17px;
                    left: 220px;
                    opacity: 0;
                }
                20% {
                    top: 33px;
                    left: 188px;
                    opacity: 1;
                }
                80% {
                    top: 81px;
                    left: 92px;
                    opacity: 1;
                }
                100% {
                    top: 97px;
                    left: 60px;
                    opacity: 0;
                }
            }
            @keyframes cloudMid {
                0% {
                    top: 10px;
                    left: 420px;
                    opacity: 0;
                }
                20% {
                    top: 40px;
                    left: 360px;
                    opacity: 1;
                }
                70% {
                    top: 130px;
                    left: 180px;
                    opacity: 1;
                }
                100% {
                    top: 160px;
                    left: 120px;
                    opacity: 0;
                }
            }
            @keyframes cloudRight {
                0% {
                    top: 100px;
                    left: 500px;
                    opacity: 0;
                }
                20% {
                    top: 120px;
                    left: 460px;
                    opacity: 1;
                }
                80% {
                    top: 180px;
                    left: 340px;
                    opacity: 1;
                }
                100% {
                    top: 200px;
                    left: 300px;
                    opacity: 0;
                }
            }
        }
    }
    .bullshit {
        position: relative;
        float: left;
        width: 300px;
        padding: 150px 0;
        overflow: hidden;
        &__oops {
            font-size: 32px;
            font-weight: bold;
            line-height: 40px;
            color: #1482f0;
            opacity: 0;
            margin-bottom: 20px;
            animation-name: slideUp;
            animation-duration: 0.5s;
            animation-fill-mode: forwards;
        }
        &__headline {
            font-size: 20px;
            line-height: 24px;
            color: #1482f0;
            opacity: 0;
            margin-bottom: 10px;
            animation-name: slideUp;
            animation-duration: 0.5s;
            animation-delay: 0.1s;
            animation-fill-mode: forwards;
        }
        &__info {
            font-size: 13px;
            line-height: 21px;
            color: grey;
            opacity: 0;
            margin-bottom: 30px;
            animation-name: slideUp;
            animation-duration: 0.5s;
            animation-delay: 0.2s;
            animation-fill-mode: forwards;
        }
        &__return-home {
            display: block;
            float: left;
            width: 110px;
            height: 36px;
            background: #1482f0;
            border-radius: 100px;
            text-align: center;
            color: #ffffff;
            opacity: 0;
            font-size: 14px;
            line-height: 36px;
            cursor: pointer;
            animation-name: slideUp;
            animation-duration: 0.5s;
            animation-delay: 0.3s;
            animation-fill-mode: forwards;
        }
        @keyframes slideUp {
            0% {
                transform: translateY(60px);
                opacity: 0;
            }
            100% {
                transform: translateY(0);
                opacity: 1;
            }
        }
    }
}
</style>
