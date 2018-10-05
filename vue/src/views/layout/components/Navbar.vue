<template>
<el-menu class="navbar" mode="horizontal">
  <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>
  <el-dropdown class="avatar-container" trigger="click">
    <div class="avatar-wrapper">
      <img class="user-avatar" :src=livePhoto>
    </div>
    <el-dropdown-menu class="user-dropdown" slot="dropdown">
      <!-- <router-link class="inlineBlock" to="/">
        <el-dropdown-item>
        <span>首页</span>
        </el-dropdown-item>
      </router-link> -->
      <router-link class="inlineBlock" to="">
        <el-dropdown-item>
          <span @click="dialogFormVisible = true">密码修改</span>
        </el-dropdown-item>
      </router-link>
      <router-link class="inlineBlock" to="/index/userBaseDetail">
        <el-dropdown-item>
          <span>修改用户信息</span>
        </el-dropdown-item>
      </router-link>
      <router-link  v-if="userType !='0'" class="inlineBlock" to="/index/userDetail">
        <el-dropdown-item>
          <span>加入社区</span>
        </el-dropdown-item>
      </router-link>
      <el-dropdown-item divided>
        <span @click="logout" style="display:block;">登出</span>
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>

  <div class="avatarname">
      <el-button style="margin-left:20px" @click="loadIndex" type="primary">返回门户</el-button> {{name}} 
  </div>
  <div class="avatarBUT">

  </div>
  <el-dialog title="密码修改" :visible.sync="dialogFormVisible" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">

        <el-form-item label="旧密码">
          <el-input type="password" v-model="obj.password" placeholder="请输入旧密码" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input type="password" v-model="obj.newPassword" placeholder="请输入新密码" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="重复输入新密码">
          <el-input type="password" v-model="obj.rePassword" placeholder="请重复输入新密码" style="width:80%"></el-input>
        </el-form-item>
      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary"  @click="savePaw(obj)">保存</el-button>
      <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
    </span>
  </el-dialog>

</el-menu>
</template>

<script>
import {
  mapGetters
} from 'vuex'
import Hamburger from '@/components/Hamburger'
import livePhoto from '@/assets/logo/livePhoto.jpg'
import {
  resetPwd,
  sendCellphoneCode
} from '@/api/user'

import {
  Message
} from 'element-ui'

export default {

  data() {
    return {
      livePhoto,
      name: '',
      dialogFormVisible: false,
      obj: {
        password: '',
        newPassword: '',
        rePassword: '',
      },
      userType: '',
      checkStatus: '',
    };
  },
  components: {
    Hamburger
  },
  computed: {
    ...mapGetters([
      'sidebar'
    ])
  },
  mounted() {
    this.name = window.sessionStorage.getItem('userName')
    this.userType =  window.sessionStorage.getItem('userType')
    this.checkStatus =  window.sessionStorage.getItem('checkStatus')
    if (this.checkStatus == -1) {
      this.$message({
        message: '请修改默认密码！',
        type: 'success'
      });
    }

  },
  methods: {
    loadIndex(){
       window.location.href = '/front-net/index.html?token=' + window.sessionStorage.getItem('token')
    },
    async savePaw(obj) {

      if (!this.validata.validachangePWD(obj)) return
      if(obj.newPassword != obj.rePassword){
        Message({
          message: '两次密码输入不一致！',
          type: 'error'
        })
        return
      }

      let {
        message,
        success
      } = await resetPwd(obj)
      if (success) {
        Message({
          message: '修改成功',
          type: 'success',
          duration: 5 * 1000
        })
        this.dialogFormVisible = false

        this.obj = {
          password: '',
          newPassword: '',
          rePassword: '',
        }
      } else {
        Message({
          message: message,
          type: 'error',
          duration: 5 * 1000
        })
      }
    },
    toggleSideBar() {
      this.$store.dispatch('ToggleSideBar')
    },
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        location.reload() // 为了重新实例化vue-router对象 避免bug
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
    height: 57px;
    line-height: 50px;
    border-radius: 0 !important;
    background: #0056B8;
    color: #fff;
    -webkit-box-shadow: 1px 0 5px rgba(0, 0, 0, 0.5);
    box-shadow: 1px 0 5px rgba(0, 0, 0, 0.5);
    .hamburger-container {
        line-height: 58px;
        height: 50px;
        float: left;
        padding: 0 10px;
    }
    .screenfull {
        position: absolute;
        right: 90px;
        top: 16px;
        color: red;
    }
    .avatarname {
        position: absolute;
        display: inline-block;
        right: 100px;
    }
    .avatarBUT {
        position: absolute;
        display: inline-block;
        right: 260px;
    }

    .avatar-container {
        height: 50px;
        display: inline-block;
        position: absolute;
        right: 30px;
        .avatar-wrapper {
            cursor: pointer;
            margin-top: 5px;
            position: relative;
            .user-avatar {
                width: 40px;
                height: 40px;
                border-radius: 30px;
            }
            .el-icon-caret-bottom {
                position: absolute;
                right: -20px;
                top: 25px;
                font-size: 12px;
            }
        }
    }
}
</style>
