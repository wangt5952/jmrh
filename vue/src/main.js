import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'
import App from './App'
import router from './router'
import store from './store'
import '@/icons' // icon
import '@/permission' // 权限
import validate from './utils/validate' // 验证
import jsPlumb from 'jsplumb'
import VueAreaLinkage from 'vue-area-linkage';

Vue.prototype.$jsPlumb = jsPlumb.jsPlumb
Vue.prototype.validata =  validate
import vSelect from 'vue-select'
Vue.component('v-select', vSelect)

Vue.use(VueAreaLinkage)
Vue.use(ElementUI, { locale })

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
