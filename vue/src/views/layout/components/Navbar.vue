<template>
<el-menu class="navbar" mode="horizontal">
  <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>
  <el-dropdown class="avatar-container" trigger="click">
    <div class="avatar-wrapper">
      <img class="user-avatar" :src="avatar+'?imageView2/1/w/80/h/80'">
    </div>
    <el-dropdown-menu class="user-dropdown" slot="dropdown">
      <router-link class="inlineBlock" to="/">
        <el-dropdown-item>
          首页
        </el-dropdown-item>
      </router-link>
        <el-dropdown-item>
            <span @click="dialogFormVisible = true" style="display:block;">密码修改</span>
        </el-dropdown-item>
      <router-link class="inlineBlock" to="/index/userDetail">
        <el-dropdown-item>
          完善信息
        </el-dropdown-item>
      </router-link>
      <el-dropdown-item divided>
        <span @click="logout" style="display:block;">登出</span>
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>

  <div class="avatarname">
    {{name}}
  </div>

  <el-dialog title="密码修改" :visible.sync="dialogFormVisible" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">

        <el-form-item label="旧密码">
          <el-input v-model="obj.oldpaw" placeholder="请输入旧密码" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="obj.newpaw" placeholder="请输入新密码" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="重复输入新密码">
          <el-input v-model="obj.rewpaw" placeholder="请输入新密码" style="width:80%"></el-input>
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

export default {

  data() {
    return {
      avatar: 'http://77g1mh.com1.z0.glb.clouddn.com/5753c62386f39448cf77608db4081b3a',
      name: '',
      dialogFormVisible: false,
      obj: {
        oldpaw: '',
        newpaw: '',
        rewpaw: '',
      },
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
  },
  methods: {
    savePaw(){

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
    background: #1e88e5;
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
        right: 25px;
    }
    .avatar-container {
        height: 50px;
        display: inline-block;
        position: absolute;
        right: 75px;
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
