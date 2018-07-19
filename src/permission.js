import router from './router'
import store from './store'
import NProgress from 'nprogress' // Progress 进度条
import $ from 'jquery'
import 'nprogress/nprogress.css' // Progress 进度条样式
import {
  Message
} from 'element-ui'
import {
  getToken
} from '@/utils/auth' // 验权
import MenuUtils from '@/views/MenuUtils'

// let data = JSON.parse(window.sessionStorage.getItem('user'))
// if (data) {
//   //这里是防止用户手动刷新页面，整个app要重新加载,动态新增的路由，会消失，所以我们重新add一次
//   let routes = []
//   MenuUtils(routes, data)
//   router.addRoutes(routes)
//   window.sessionStorage.removeItem('isLoadNodes')
// }

const whiteList = ['/login','/register','/resetPaw'] // 不重定向白名单
router.beforeEach((route, redirect, next) => {
  let data = JSON.parse(window.sessionStorage.getItem('user'))

  if (data && route.path === '/login') {
    //这里不使用router进行跳转，是因为，跳转到登录页面的时候，是需要重新登录，获取数据的，这个时候，会再次向router实例里面add路由规则，
    //而next()跳转过去之后，没有刷新页面，之前的规则还是存在的，但是使用location的话，可以刷新页面，当刷新页面的时候，整个app会重新加载
    //而我们在刷新之前已经把sessionStorage里的user移除了，所以上面的添加路由也不行执行
    window.sessionStorage.removeItem('user')
    window.sessionStorage.removeItem('isLoadNodes')
    window.location.href = '/'
    return false
  }
  if (route.path !== '/main/moudle1') {
    $('.jtk-endpoint').css("display", 'none');
    $('.jtk-connector').css("display", 'none');
    $('.jtk-overlay').css("display", 'none');
  }
  if (!data && whiteList.indexOf(route.path) == -1) {
    next({
      path: '/login'
    })
  } else {
    if (route.path) {
      next()
    } else {
      next({
        path: '/404'
      })
    }
  }
})

router.afterEach(() => {
  NProgress.done() // 结束Progress
})
