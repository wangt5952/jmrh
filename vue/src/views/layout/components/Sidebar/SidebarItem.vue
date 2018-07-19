<template>
  <div class="menu-wrapper">
    <template v-for="item in nodes">
      <router-link v-if="!item.hidden&&item.children&&item.children.length===1&&item.path == '/index'" :to="item.path+'/'+item.children[0].path" :key="item.children[0].name">
        <el-menu-item :index="item.path+'/'+item.children[0].path" class='submenu-title-noDropdown' >
          <svg-icon v-if="item.children[0].meta&&item.children[0].meta.icon" :icon-class="item.children[0].meta.icon"></svg-icon>
          <span v-if="item.path === '/index'&&item.children[0].meta&&item.children[0].meta.title">{{item.children[0].meta.title}}</span>
        </el-menu-item>
      </router-link>

      <el-submenu v-if="!item.hidden&&item.children&&item.children.length>=1&&item.path != '/index'" :index="item.name||item.path" :key="item.name">
        <template slot="title">
          <i v-if="item.meta&&item.meta.icon" :class="item.meta.icon"></i>
          <svg-icon v-if="item.meta&&item.meta.icon" :icon-class="item.meta.icon"></svg-icon>
          <!-- 有子集时图标 -->
          <span v-if="item.meta&&item.meta.title">{{item.meta.title}}</span>
        </template>

        <template v-if="!child.hidden" v-for="child in item.children">
          <sidebar-item class="nest-menu" v-if="child.children&&child.children.length>0" :nodes="[child]" :key="child.path"></sidebar-item>

          <router-link v-else :to="{path:item.path+'/'+child.path,query:{id:child.id}}" :key="child.name">
            <el-menu-item  :index="item.path+'/'+child.path">
              <svg-icon v-if="child.meta&&child.meta.icon" :icon-class="child.meta.icon"></svg-icon>
              <span v-if="child.meta&&child.meta.title">{{child.meta.title}}</span>
            </el-menu-item>
          </router-link>
        </template>
      </el-submenu>



    </template>
  </div>
</template>

<script>
export default {
  name: 'SidebarItem',
  props: {
    nodes: {
      type: Array
    }
  },
	mounted() {
		console.log( this.$router.options.routes)

	},
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.el-menu-item.is-active {
   background-color: #26c6da !important;

}


</style>
