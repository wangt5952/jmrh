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
Vue.prototype.imgBaseUrl = 'http://106.14.172.38:8990/jmrhupload/';
Vue.prototype.docUrl = 'http://106.14.172.38:8080';

// Vue.prototype.imgBaseUrl = 'http://127.0.0.1:9990/upload/';
// Vue.prototype.docUrl = 'http://127.0.0.1:8080';

// Vue.prototype.imgBaseUrl = 'http://127.0.0.1:9009/upload/';
// Vue.prototype.docUrl = 'http://127.0.0.1:8080';

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
Vue.filter('replaceKG', value => {
  value = value.replace(/<\/?.+?>/g, "");
  value = value.replace(/[\r\n]/g, "");
  value = value.replace(/\s+/g, "");
  return value;
})
// Vue.filter('formatDomain', value => {
//   var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]")
//   var rs = "";
//   for (var i = 0; i < value.length; i++) {
//     rs = rs + value.substr(i, 1).replace(pattern, '');
//   }
//   let arrV = []
//   var arr = Array.from(rs);
//   for(var j in arr){
//     if(parseInt(arr[j])>= 0){
//       arrV.push(arr[j])
//     }
//   }
//   debugger
//   return arrV;
// })
Vue.prototype.$jsPlumb = jsPlumb.jsPlumb
Vue.prototype.validata = validate


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
