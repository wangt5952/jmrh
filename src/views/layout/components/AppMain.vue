<template>
  <section class="app-main">
    <breadcrumb></breadcrumb>
    <transition name="fade" mode="out-in">
      <!-- <router-view :key="key"></router-view> -->
      <router-view></router-view>
    </transition>
  </section>
</template>

<script>
import Breadcrumb from '@/components/Breadcrumb'
export default {
  name: 'AppMain',
  computed: {
    // key() {
    //   return this.$route.name !== undefined ? this.$route.name + +new Date() : this.$route + +new Date()
    // }
  },
  components: {
    Breadcrumb
  },
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
.app-main {
    height: 90%;
    }

</style>
