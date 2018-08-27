// import Vue from 'vue'
// import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'
import App from './App'
import router from './router'
import store from './store'
import '@/icons' // icon
import '@/permission' // 权限
import validate from './utils/validate' // 验证
import jsPlumb from 'jsplumb'
import VueAreaLinkage from 'vue-area-linkage';
//全局变量
Vue.prototype.imgBaseUrl  = 'http://106.14.172.38:8990';
// Vue.prototype.imgBaseUrl  = 'http://localhost:8080';

var myDate = new Date();
Vue.prototype.lastt = myDate.getFullYear() + '年';
Vue.prototype.beforet = parseInt(myDate.getFullYear() - 1) + '年';
Vue.prototype.previoust = parseInt(myDate.getFullYear() - 2) + '年';

Vue.prototype.urlencode = function(str) {
  str = (str + '').toString();
  return encodeURIComponent(str).replace(/!/g, '%21').replace(/'/g, '%27').replace(/\(/g, '%28').
  replace(/\)/g, '%29').replace(/\*/g, '%2A').replace(/%20/g, '+');
}
Vue.prototype.getformatTime = function() {
  var d = new Date(); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
  let year = d.getFullYear()
  let month = parseInt(d.getMonth() + 1)
  let date = d.getDate()
  if (month < 10) {
    month = '0' + month
  }
  if (date < 10) {
    date = '0' + date
  }
  return year + '-' + month + '-' + date
}
Vue.filter('formatTime', value => {
  var d = new Date(value); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
  let year = d.getFullYear()
  let month = parseInt(d.getMonth() + 1)
  let date = d.getDate()
  if (month < 10) {
    month = '0' + month
  }
  if (date < 10) {
    date = '0' + date
  }
  return year + '-' + month + '-' + date
})
Vue.prototype.$jsPlumb = jsPlumb.jsPlumb
Vue.prototype.validata = validate
import img2base64 from './img2base64'; //引入转图片为base64
Vue.prototype.img2base64 = img2base64

import vSelect from 'vue-select'
Vue.component('v-select', vSelect)

Vue.use(VueAreaLinkage)
Vue.use(ELEMENT);

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {
    App
  }
})
