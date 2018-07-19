import Vue from 'vue'
import Router from 'vue-router'
const _import = require('./_import_' + process.env.NODE_ENV)
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '@/views/layout/index.vue'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [{
    path: '/login',
    component: _import('login/index'),
    name: 'login',
    leaf: true,
    hidden: true
  }, {
    path: '/register',
    component: _import('register/index'),
    name: 'register',
    leaf: true,
    hidden: true
  },{
    path: '/resetPaw',
    component: _import('resetPaw/index'),
    name: 'resetPaw',
    leaf: true,
    hidden: true
  },
  {
    path: '/index',
    component: Layout,
    name: '完善信息',
    leaf: false,
    hidden: true,
    meta: {
      title: '完善信息',
      icon: 'dashboard'
    },
    children: [{
      path: 'userDetail',
      component: _import('userDetail/index'),
      leaf: true,
    }]
  },
  {
    path: '/404',
    component: _import('404'),
    name: '404',
    leaf: true,
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: _import('dashboard/index')
    }]
  },
  {
    path: '/index',
    leaf: false,
    component: Layout,
    name: '我的工作台',
    meta: {
      title: '我的工作台',
      icon: 'dashboard'
    },
    children: [{
      path: 'dashboard',
      component: _import('dashboard/index'),
      leaf: true,
      meta: {
        title: '我的工作台',
        icon: 'dashboard'
      },
    }]
  }, {
    path: '/manage',
    component: Layout,
    redirect: '/manage/role',
    name: '系统管理',
    meta: {
      title: '系统管理',
      icon: 'tree'
    },
    leaf: false,
    children: [{
      path: 'role',
      name: '注册用户列表',
      component: _import('user/index'),
      leaf: true,
      meta: {
        title: '注册用户列表',
        icon: 'table'
      }
    }, {
      path: 'role2',
      name: '管理用户列表',
      component: _import('user/index'),
      leaf: true,
      meta: {
        title: '管理用户列表',
        icon: 'table'
      }
    }, {
      path: 'role3',
      name: '组织架构列表',
      component: _import('tree/index'),
      leaf: true,
      meta: {
        title: '组织架构列表',
        icon: 'table'
      }
    },{
      path: 'role4',
      name: '角色列表',
      component: _import('role/index'),
      leaf: true,
      meta: {
        title: '角色列表',
        icon: 'table'
      }
    }, {
      path: 'role5',
      name: '功能列表',
      component: _import('tree/index'),
      leaf: true,
      meta: {
        title: '功能列表',
        icon: 'table'
      }
    }]
  },{
    path: '/manage2',
    component: Layout,
    redirect: '/manage/role',
    name: '门户管理',
    meta: {
      title: '门户管理',
      icon: 'tree'
    },
    leaf: false,
    children: [{
      path: 'role',
      name: '栏目管理',
      component: _import('columnManage/index'),
      leaf: true,
      meta: {
        title: '栏目管理',
        icon: 'table'
      }
    },{
      path: 'role2',
      name: '内容管理',
      component: _import('contentManage/index'),
      leaf: true,
      meta: {
        title: '内容管理',
        icon: 'table'
      }
    },{
      path: 'role3',
      name: '添加文章',
      component: _import('contentManage/index'),
      leaf: true,
      meta: {
        title: '添加文章',
        icon: 'table'
      }
    }]
  },
]
// { path: '/login', component: _import('login/index'), hidden: true },
// { path: '/404', component: _import('404'), hidden: true },
//
// {
//   path: '/',
//   component: Layout,
//   redirect: '/dashboard',
//   name: 'Dashboard',
//   hidden: true,
//   children: [{
//     path: 'dashboard',
//     component: _import('dashboard/index')
//   }]
// },
//
// {
//   path: '/example',
//   component: Layout,
//   redirect: '/example/table',
//   name: 'Example',
//   meta: { title: 'Example', icon: 'example' },
//   children: [
//     {
//       path: 'table',
//       name: 'Table',
//       component: _import('table/index'),
//       meta: { title: 'Table', icon: 'table' }
//     },
//     {
//       path: 'tree',
//       name: 'Tree',
//       component: _import('tree/index'),
//       meta: { title: 'Tree', icon: 'tree' }
//     }
//   ]
// },
//
// {
//   path: '/form',
//   component: Layout,
//   children: [
//     {
//       path: 'index',
//       name: 'Form',
//       component: _import('form/index'),
//       meta: { title: 'Form', icon: 'form' }
//     }
//   ]
// },
//
// { path: '*', redirect: '/404', hidden: true }


export default new Router({
  // mode: 'history', //后端支持可开
  // scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
