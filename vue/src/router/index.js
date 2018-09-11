import Vue from 'vue'
import Router from 'vue-router'
const _import = require('./_import_' + process.env.NODE_ENV)
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '@/views/layout/index.vue'
 // const login = r => require.ensure([], () => r(_import('login/index')), 'login')
const login = r => require.ensure([], () => r(require('@/views/login/index')), 'login')
const register = r => require.ensure([], () => r(require("@/views/register/index")), 'register');
const registerDetail = r => require.ensure([], () => r(require("@/views/registerDetail/index")), 'registerDetail');
const resetPaw = r => require.ensure([], () => r(require("@/views/resetPaw/index")), 'resetPaw');
const activate = r => require.ensure([], () => r(require("@/views/activate/index")), 'activate');
const userBaseDetail = r => require.ensure([], () => r(require("@/views/userBaseDetail/index")), 'userBaseDetail');
const userDetail = r => require.ensure([], () => r(require("@/views/userDetail/index")), 'userDetail');
const page404 = r => require.ensure([], () => r(require("@/views/404.vue")), 'page404');

const dashboard = r => require.ensure([], () => r(require("@/views/dashboard/index")), 'dashboard');
const user = r => require.ensure([], () => r(require("@/views/user/index")), 'user');
const ManageUser = r => require.ensure([], () => r(require("@/views/ManageUser/index")), 'ManageUser');
const org = r => require.ensure([], () => r(require("@/views/org/index")), 'org');
const role = r => require.ensure([], () => r(require("@/views/role/index")), 'role');
const tree = r => require.ensure([], () => r(require("@/views/tree/index")), 'tree');
const columnManage = r => require.ensure([], () => r(require("@/views/columnManage/index")), 'columnManage');
const contentManage = r => require.ensure([], () => r(require("@/views/contentManage/index")), 'contentManage');
const contentEdit = r => require.ensure([], () => r(require("@/views/contentManage/edit/index")), 'contentEdit');
const activeManage = r => require.ensure([], () => r(require("@/views/activeManage/index")), 'activeManage');
const activeEdit = r => require.ensure([], () => r(require("@/views/activeManage/edit/index")), 'activeEdit');
const expert = r => require.ensure([], () => r(require("@/views/expert/index")), 'expert');
const expertEdit = r => require.ensure([], () => r(require("@/views/expert/edit/index")), 'expert');
const enterprise = r => require.ensure([], () => r(require("@/views/enterprise/index")), 'enterprise');
const enterpriseEdit = r => require.ensure([], () => r(require("@/views/enterprise/edit/index")), 'enterprise');
const hschool = r => require.ensure([], () => r(require("@/views/hschool/index")), 'hschool');
const hschoolEdit = r => require.ensure([], () => r(require("@/views/hschool/edit/index")), 'hschool');
const mechanism = r => require.ensure([], () => r(require("@/views/mechanism/index")), 'mechanism');
const mechanismEdit = r => require.ensure([], () => r(require("@/views/mechanism/edit/index")), 'mechanism');

const demandLibrary = r => require.ensure([], () => r(require("@/views/demandLibrary/index")), 'demandLibrary');
const achieveLibraryEdit = r => require.ensure([], () => r(require("@/views/achieveLibrary/edit/index")), 'achieveLibrary');
const achieveLibrary = r => require.ensure([], () => r(require("@/views/achieveLibrary/index")), 'achieveLibrary');
const demandLibraryEdit = r => require.ensure([], () => r(require("@/views/demandLibrary/edit/index")), 'demandLibrary');

const buttExpert = r => require.ensure([], () => r(require("@/views/buttExpert/index")), 'buttExpert');
const registerSee = r => require.ensure([], () => r(require("@/views/registerSee/index")), 'registerSee');
const registerUser = r => require.ensure([], () => r(require("@/views/registerUser/index")), 'registerUser');
const registerExpert = r => require.ensure([], () => r(require("@/views/registerExpert/index")), 'registerExpert');
const registerxqcg = r => require.ensure([], () => r(require("@/views/registerxqcg/index")), 'registerxqcg');
const registerDj = r => require.ensure([], () => r(require("@/views/registerDj/index")), 'registerDj');
const robotResponse = r => require.ensure([], () => r(require("@/views/robot/robotResponse/index")), 'robotResponse');



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
    component: login,
    name: 'login',
    leaf: true,
    hidden: true
  }, {
    path: '/register',
    component: register,
    name: 'register',
    leaf: true,
    hidden: true
  }, {
    path: '/registerDetail',
    component: registerDetail,
    name: 'registerDetail',
    leaf: true,
    hidden: true
  },{
    path: '/resetPaw',
    component: resetPaw,
    name: 'resetPaw',
    leaf: true,
    hidden: true
  },
  {
    path: '/xtcx/user/activate',
    component: activate,
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
      component: userDetail,
      leaf: true,
    },{
      path: 'userBaseDetail',
      component: userBaseDetail,
      leaf: true,
    }]
  },
  {
    path: '/404',
    component: page404,
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
      component: dashboard
    }]
  },
  {
    path: '/index',
    leaf: false,
    component: Layout,
    name: '我的待办',
    meta: {
      title: '我的待办',
      icon: 'dashboard'
    },
    children: [{
      path: 'dashboard',
      component: dashboard,
      leaf: true,
      meta: {
        title: '我的待办',
        icon: 'dashboard'
      },
    }]
  },
  {
    path: '/manage',
    component: Layout,
    redirect: '/manage/user',
    name: '系统管理',
    meta: {
      title: '系统管理',
      icon: 'tree'
    },
    leaf: false,
    children: [{
      path: 'user',
      name: '注册用户列表',
      component: user,
      leaf: true,
      meta: {
        title: '注册用户列表',
        icon: 'table'
      }
    }, {
      path: 'ManageUser',
      name: '管理用户列表',
      component: ManageUser,
      leaf: true,
      meta: {
        title: '管理用户列表',
        icon: 'table'
      }
    }, {
      path: 'org',
      name: '组织架构列表',
      component: org,
      leaf: true,
      meta: {
        title: '组织架构列表',
        icon: 'table'
      }
    },{
      path: 'role',
      name: '角色列表',
      component: role,
      leaf: true,
      meta: {
        title: '角色列表',
        icon: 'table'
      }
    }, {
      path: 'tree',
      name: '功能列表',
      component:tree,
      leaf: true,
      meta: {
        title: '功能列表',
        icon: 'table'
      }
    }]
  },{
    path: '/manage2',
    component: Layout,
    name: '门户管理',
    meta: {
      title: '门户管理',
      icon: 'tree'
    },
    leaf: false,
    children: [{
      path: 'columnManage',
      name: '栏目管理',
      component: columnManage,
      leaf: true,
      meta: {
        title: '栏目管理',
        icon: 'table'
      }
    },{
      path: 'contentManage',
      name: '内容管理',
      component: contentManage,
      leaf: true,
      meta: {
        title: '内容管理',
        icon: 'table'
      }
    },{
      path: 'contentEdit',
      name: 'contentEdit',
      component: contentEdit,
      hidden: true,
      leaf: true,
      meta: {
        title: '内容编辑',
        icon: 'table'
      }
    },{
      path: 'activeManage',
      name: '活动管理',
      component: activeManage,
      leaf: true,
      meta: {
        title: '活动管理',
        icon: 'table'
      }
    },{
      path: 'activeEdit',
      name: 'activeEdit',
      component: activeEdit,
      leaf: true,
      hidden: true,
      meta: {
        title: '活动编辑',
        icon: 'table'
      }
    }]
  },{
    path: '/manage3',
    component: Layout,
    redirect: '/manage3/expert',
    name: '资源库管理',
    meta: {
      title: '资源库管理',
      icon: 'tree'
    },
    leaf: false,
    children: [{
      path: 'expert',
      name: '专家库管理',
      component: expert,
      leaf: true,
      meta: {
        title: '专家库管理',
        icon: 'table'
      }
    },{
      path: 'enterprise',
      name: '企业库管理',
      component: enterprise,
      leaf: true,
      meta: {
        title: '企业库管理',
        icon: 'table'
      }
    },{
      path: 'hschool',
      name: '高校院所库管理',
      component: hschool,
      leaf: true,
      meta: {
        title: '高校院所库管理',
        icon: 'table'
      }
    },{
      path: 'mechanism',
      name: 'mechanism',
      component: mechanism,
      leaf: true,
      meta: {
        title: '服务机构库管理',
        icon: 'table'
      }
    },{
      path: 'expertEdit',
      name: 'expertEdit',
      component: expertEdit,
      leaf: true,
      hidden: true,
      meta: {
        title: '专家库资料填写',
        icon: 'table'
      }
    },{
      path: 'enterpriseEdit',
      name: 'enterpriseEdit',
      component: enterpriseEdit,
      leaf: true,
      hidden: true,
      meta: {
        title: '企业库资料填写',
        icon: 'table'
      }
    },{
      path: 'hschoolEdit',
      name: 'hschoolEdit',
      component: hschoolEdit,
      leaf: true,
      hidden: true,
      meta: {
        title: '高校院所库资料填写',
        icon: 'table'
      }
    },{
      path: 'mechanismEdit',
      name: 'mechanismEdit',
      component: mechanismEdit,
      leaf: true,
      hidden: true,
      meta: {
        title: '服务机构库资料填写',
        icon: 'table'
      }
    },{
      path: 'demandLibrary',
      name: '需求库管理',
      component: demandLibrary,
      leaf: true,
      meta: {
        title: '需求库管理',
        icon: 'table'
      }
    },{
      path: 'demandLibraryEdit',
      name: 'demandLibraryEdit',
      component: demandLibraryEdit,
      leaf: true,
      hidden: true,
      meta: {
        title: '需求库资料填写',
        icon: 'table'
      }
    },
    {
      path: 'achieveLibrary',
      name: '成果库管理',
      component: achieveLibrary,
      leaf: true,
      meta: {
        title: '成果库管理',
        icon: 'table'
      }
    },{
      path: 'achieveLibraryEdit',
      name: 'achieveLibraryEdit',
      component: achieveLibraryEdit,
      leaf: true,
      hidden: true,
      meta: {
        title: '成果库资料填写',
        icon: 'table'
      }
    }]
  },
  {
    path: '/buttManage',
    component: Layout,
    name: '对接管理',
    meta: {
      title: '对接管理',
      icon: 'tree'
    },
    leaf: false,
    children: [{
      path: 'buttExpert',
      name: '对接管理',
      component: buttExpert,
      leaf: true,
      meta: {
        title: '专家对接',
        icon: 'table'
      }
    }]
  },  {
      path: '/register',
      component: Layout,
      name: '统计',
      meta: {
        title: '统计',
        icon: 'tree'
      },
      leaf: false,
      children: [{
        path: 'registerSee',
        name: '访问统计',
        component: registerSee,
        leaf: true,
        meta: {
          title: '访问统计',
          icon: 'table'
        }
      },{
        path: 'registerUser',
        name: '注册用户统计',
        component: registerUser,
        leaf: true,
        meta: {
          title: '注册用户统计',
          icon: 'table'
        }
      },{
        path: 'registerExpert',
        name: '专家机构统计',
        component: registerExpert,
        leaf: true,
        meta: {
          title: '专家机构统计',
          icon: 'table'
        }
      },{
        path: 'registerxqcg',
        name: '需求成果统计',
        component: registerxqcg,
        leaf: true,
        meta: {
          title: '需求成果统计',
          icon: 'table'
        }
      },{
        path: 'registerDj',
        name: '对接统计',
        component: registerDj,
        leaf: true,
        meta: {
          title: '对接统计',
          icon: 'table'
        }
      }]
    },





    // {
    //   path: '/robot',
    //   component: Layout,
    //   name: '机器人',
    //   meta: {
    //     title: '机器人',
    //     icon: 'tree'
    //   },
    //   leaf: false,
    //   children: [{
    //     path: 'robotResponse',
    //     name: '应答设置',
    //     component: robotResponse,
    //     leaf: true,
    //     meta: {
    //       title: '应答设置',
    //       icon: 'table'
    //     }
    //   }]
    // },
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
//     component: dashboard
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
