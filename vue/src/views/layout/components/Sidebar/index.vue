<template>
<scroll-bar>
  <el-menu mode="vertical" unique-opened :default-active="$route.path" :collapse="isCollapse" background-color="#fff" text-color="#616265" active-text-color="#fff">

    <div class="navbar-header">
      <el-row>
        <img :src="logo_light_icon" alt="homepage" class="" />
        <!-- <el-col :span="10"> <img :src="logo_light" class="" alt="homepage" /></el-col> -->
      </el-row>
    </div>

    <sidebar-item :nodes="$router.options.routes"></sidebar-item>
  </el-menu>
</scroll-bar>
</template>

<script>
import logo_icon from '@/assets/logo/logo_icon.png'
import logo_light_icon from '@/assets/logo/logo_light_icon.png'
import logo_text from '@/assets/logo/logo_text.png'
import logo_light from '@/assets/logo/logo_light.png'
import {
  mapGetters
} from 'vuex'
import SidebarItem from './SidebarItem'
import ScrollBar from '@/components/ScrollBar'

export default {
  data() {
    return {
      nodes: this.$router.options.routes,
      logo_icon,
      logo_light_icon,
      logo_text,
      logo_light
    }
  },
  components: {
    SidebarItem,
    ScrollBar
  },
  mounted() {},
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    isCollapse() {
      return !this.sidebar.opened
    }
  },
  created() {
    //这里没有直接使用this.$router.options.routes，是因为addRoute的路由规则，在这里this.$router.options.routes获取不到
    //有兴趣的可以看一下源码，是为什么获取不到，但是却又有规则了
    //另外在开发的时候，可能由于是热部署，也会不断重复的给nodes添加元素，造成导航条有重复的，简单解决，可以设置一个开关
    let isLoadNodes = sessionStorage.getItem('isLoadNodes')
    if (!isLoadNodes) {
      let data = JSON.parse(window.sessionStorage.getItem('user'))
      this.nodes.push(...data)
      console.log(this.nodes)
      sessionStorage.setItem('isLoadNodes', 'true')
    }
  },
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.el-menu {
    height: 100%;
}
.navbar-header {
    height: 57px;
    background: #167fdc;
    text-align: center;
    padding: 10px;
}
.logo_light_icon {
    width: 10%;
    display: inline;
}
.logo_light {
    width: 50%;
    display: inline;
}
</style>
