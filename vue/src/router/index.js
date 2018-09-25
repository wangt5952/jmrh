import Vue from 'vue'
import Router from 'vue-router'
const _import = require('./_import_' + process.env.NODE_ENV)
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

import MenuUtils from '@/views/MenuUtils'

Vue.use(Router)

let load = {
forload: function(treeData) {
  let userType = window.sessionStorage.getItem('userType')
  for (let i = 0; i < treeData.length; i++) {
      if (treeData[i].children && treeData[i].children.length > 0 && treeData[i].leaf == false) { //有下级
        treeData[i].meta = {
          title: treeData[i].label,
          icon: 'tree'
        }
        treeData[i].name = treeData[i].label
        treeData[i].path = '/' + treeData[i].menuUrl
        treeData[i].leaf = false
        treeData[i].component = treeData[i].menuUrl
        if (treeData[i].isMenu == "1") { //有下级 第一个菜单
          treeData[i].component = 'layout'
          treeData[i].redirect = '/' + treeData[i].menuUrl + '/' + treeData[i].children[0].menuUrl + ''
        }
        if(userType != '0' && userType != '101' && treeData[i].menuUrl != 'dashboard') treeData[i].hidden = true
      } else if (treeData[i].leaf == true && treeData[i].isMenu == "1"&& treeData[i].sortOrder != 9999 && treeData[i].menuLevel != 1) {
        treeData[i].meta = {
          title: treeData[i].label,
          icon: 'table'
        }
        treeData[i].name = treeData[i].label
        treeData[i].path = treeData[i].menuUrl
        treeData[i].leaf = true
        if(userType != '0' && userType != '101') treeData[i].hidden = true
        treeData[i].component = treeData[i].menuUrl
        treeData[i].children = []
      }  else if (treeData[i].leaf == true && treeData[i].isMenu == "1"&& treeData[i].sortOrder == 9999 && treeData[i].menuLevel != 1) {
        treeData[i].meta = {
          title: treeData[i].label,
          icon: 'table'
        }
        treeData[i].path = treeData[i].menuUrl
        treeData[i].name = treeData[i].menuUrl
        treeData[i].leaf = true
        treeData[i].hidden = true
        treeData[i].component = treeData[i].menuUrl
        treeData[i].children = []
      } else if (treeData[i].menuLevel == 1 && treeData[i].isMenu == "1" && treeData[i].leaf == true) { //第一层菜单无下级生成一个
        let chil = {}
        chil.meta = {
          title: treeData[i].label,
          icon: 'table'
        }
        treeData[i].name = treeData[i].label
        chil.path = treeData[i].menuUrl
        chil.leaf = true
        chil.component = treeData[i].menuUrl
        treeData[i].children = []
        treeData[i].children.push(chil)

        treeData[i].path = '/index'
        treeData[i].leaf = false
        treeData[i].component = 'layout'
      }
      if(treeData[i].children){
        treeData[i].children = this.forload(treeData[i].children)
      }else{
        continue
      }
  }
  return treeData
}
}

var routers = []
var treeData = window.sessionStorage.getItem('treeData')
if(treeData != null){
  treeData = JSON.parse(treeData)
 let data = load.forload(treeData)
 MenuUtils(routers, treeData)
}

/* Layout */
import Layout from '@/views/layout/index.vue'
 // const login = r => require.ensure([], () => r(_import('login/index')), 'login')
const login = r => require.ensure([], () => r(require('@/views/login/index')), 'login')
const register = r => require.ensure([], () => r(require("@/views/register/index")), 'register');
const registerDetail = r => require.ensure([], () => r(require("@/views/registerDetail/index")), 'registerDetail');
const resetPaw = r => require.ensure([], () => r(require("@/views/resetPaw/index")), 'resetPaw');
const resetPawManage = r => require.ensure([], () => r(require("@/views/resetPawManage/index")), 'resetPawManage');
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
const contentEdit = r => require.ensure([], () => r(require("@/views/contentEdit/index")), 'contentEdit');
const activeManage = r => require.ensure([], () => r(require("@/views/activeManage/index")), 'activeManage');
const activeEdit = r => require.ensure([], () => r(require("@/views/activeEdit/index")), 'activeEdit');
const majorPlatform = r => require.ensure([], () => r(require("@/views/majorPlatform/index")), 'majorPlatform');
const majorPlatformEdit = r => require.ensure([], () => r(require("@/views/majorPlatformEdit/index")), 'majorPlatformEdit');


const expert = r => require.ensure([], () => r(require("@/views/expert/index")), 'expert');
const expertEdit = r => require.ensure([], () => r(require("@/views/expertEdit/index")), 'expert');
const enterprise = r => require.ensure([], () => r(require("@/views/enterprise/index")), 'enterprise');
const enterpriseEdit = r => require.ensure([], () => r(require("@/views/enterpriseEdit/index")), 'enterprise');
const hschool = r => require.ensure([], () => r(require("@/views/hschool/index")), 'hschool');
const hschoolEdit = r => require.ensure([], () => r(require("@/views/hschoolEdit/index")), 'hschool');
const mechanism = r => require.ensure([], () => r(require("@/views/mechanism/index")), 'mechanism');
const mechanismEdit = r => require.ensure([], () => r(require("@/views/mechanismEdit/index")), 'mechanism');

const demandLibrary = r => require.ensure([], () => r(require("@/views/demandLibrary/index")), 'demandLibrary');
const achieveLibraryEdit = r => require.ensure([], () => r(require("@/views/achieveLibraryEdit/index")), 'achieveLibrary');
const achieveLibrary = r => require.ensure([], () => r(require("@/views/achieveLibrary/index")), 'achieveLibrary');
const demandLibraryEdit = r => require.ensure([], () => r(require("@/views/demandLibraryEdit/index")), 'demandLibrary');

const buttExpert = r => require.ensure([], () => r(require("@/views/buttExpert/index")), 'buttExpert');
const registerSee = r => require.ensure([], () => r(require("@/views/registerSee/index")), 'registerSee');
const registerUser = r => require.ensure([], () => r(require("@/views/registerUser/index")), 'registerUser');
const registerExpert = r => require.ensure([], () => r(require("@/views/registerExpert/index")), 'registerExpert');
const registerQy = r => require.ensure([], () => r(require("@/views/registerQy/index")), 'registerQy');
const registerHshcool = r => require.ensure([], () => r(require("@/views/registerHshcool/index")), 'registerHshcool');
const registerfwjg = r => require.ensure([], () => r(require("@/views/registerfwjg/index")), 'registerfwjg');
const registerXq = r => require.ensure([], () => r(require("@/views/registerXq/index")), 'registerXq');
const registerCg = r => require.ensure([], () => r(require("@/views/registerCg/index")), 'registerCg');
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
let arrRouter = [{
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
  },{
    path: '/resetPawManage',
    component: resetPawManage,
    name: 'resetPawManage',
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
  }
]

let arr = [...arrRouter,...routers];
export const constantRouterMap = arr


//  export const constantRouterMap = [
// {
//     path: '/login',
//     component: login,
//     name: 'login',
//     leaf: true,
//     hidden: true
//   }, {
//     path: '/register',
//     component: register,
//     name: 'register',
//     leaf: true,
//     hidden: true
//   }, {
//     path: '/registerDetail',
//     component: registerDetail,
//     name: 'registerDetail',
//     leaf: true,
//     hidden: true
//   },{
//     path: '/resetPaw',
//     component: resetPaw,
//     name: 'resetPaw',
//     leaf: true,
//     hidden: true
//   },
//   {
//     path: '/xtcx/user/activate',
//     component: activate,
//     name: 'resetPaw',
//     leaf: true,
//     hidden: true
//   },
//   {
//     path: '/index',
//     component: Layout,
//     name: '完善信息',
//     leaf: false,
//     hidden: true,
//     meta: {
//       title: '完善信息',
//       icon: 'dashboard'
//     },
//     children: [{
//       path: 'userDetail',
//       component: userDetail,
//       leaf: true,
//     },{
//       path: 'userBaseDetail',
//       component: userBaseDetail,
//       leaf: true,
//     }]
//   },
//   {
//     path: '/404',
//     component: page404,
//     name: '404',
//     leaf: true,
//     hidden: true
//   },
//   {
//     path: '/',
//     component: Layout,
//     redirect: '/dashboard',
//     name: 'Dashboard',
//     hidden: true,
//     children: [{
//       path: 'dashboard',
//       component: dashboard
//     }]
//   },
//   {
//     path: '/index',
//     leaf: false,
//     component: Layout,
//     name: '我的待办',
//     meta: {
//       title: '我的待办',
//       icon: 'dashboard'
//     },
//     children: [{
//       path: 'dashboard',
//       component: dashboard,
//       leaf: true,
//       meta: {
//         title: '我的待办',
//         icon: 'dashboard'
//       },
//     }]
//   },
//   {
//     path: '/manage',
//     component: Layout,
//     redirect: '/manage/user',
//     name: '系统管理',
//     meta: {
//       title: '系统管理',
//       icon: 'tree'
//     },
//     leaf: false,
//     children: [{
//       path: 'user',
//       name: '注册用户列表',
//       component: user,
//       leaf: true,
//       meta: {
//         title: '注册用户列表',
//         icon: 'table'
//       }
//     }, {
//       path: 'ManageUser',
//       name: '管理用户列表',
//       component: ManageUser,
//       leaf: true,
//       meta: {
//         title: '管理用户列表',
//         icon: 'table'
//       }
//     }, {
//       path: 'org',
//       name: '组织架构列表',
//       component: org,
//       leaf: true,
//       meta: {
//         title: '组织架构列表',
//         icon: 'table'
//       }
//     },{
//       path: 'role',
//       name: '角色列表',
//       component: role,
//       leaf: true,
//       meta: {
//         title: '角色列表',
//         icon: 'table'
//       }
//     }, {
//       path: 'tree',
//       name: '功能列表',
//       component:tree,
//       leaf: true,
//       meta: {
//         title: '功能列表',
//         icon: 'table'
//       }
//     }]
//   },{
//     path: '/manage2',
//     component: Layout,
//     name: '门户管理',
//     meta: {
//       title: '门户管理',
//       icon: 'tree'
//     },
//     leaf: false,
//     children: [{
//       path: 'columnManage',
//       name: '栏目管理',
//       component: columnManage,
//       leaf: true,
//       meta: {
//         title: '栏目管理',
//         icon: 'table'
//       }
//     },{
//       path: 'contentManage',
//       name: '内容管理',
//       component: contentManage,
//       leaf: true,
//       meta: {
//         title: '内容管理',
//         icon: 'table'
//       }
//     },{
//       path: 'contentEdit',
//       name: 'contentEdit',
//       component: contentEdit,
//       hidden: true,
//       leaf: true,
//       meta: {
//         title: '内容编辑',
//         icon: 'table'
//       }
//     },{
//       path: 'activeManage',
//       name: '活动管理',
//       component: activeManage,
//       leaf: true,
//       meta: {
//         title: '活动管理',
//         icon: 'table'
//       }
//     },{
//       path: 'activeEdit',
//       name: 'activeEdit',
//       component: activeEdit,
//       leaf: true,
//       hidden: true,
//       meta: {
//         title: '活动编辑',
//         icon: 'table'
//       }
//     },{
//       path: 'majorPlatform',
//       name: 'majorPlatform',
//       component: majorPlatform,
//       leaf: true,
//       meta: {
//         title: '重大平台',
//         icon: 'table'
//       }
//     },{
//       path: 'majorPlatformEdit',
//       name: 'majorPlatformEdit',
//       component: majorPlatformEdit,
//       leaf: true,
//       hidden: true,
//       meta: {
//         title: '重大平台编辑',
//         icon: 'table'
//       }
//     }]
//   },{
//     path: '/manage3',
//     component: Layout,
//     redirect: '/manage3/expert',
//     name: '资源库管理',
//     meta: {
//       title: '资源库管理',
//       icon: 'tree'
//     },
//     leaf: false,
//     children: [{
//       path: 'expert',
//       name: '专家库管理',
//       component: expert,
//       leaf: true,
//       meta: {
//         title: '专家库管理',
//         icon: 'table'
//       }
//     },{
//       path: 'enterprise',
//       name: '企业库管理',
//       component: enterprise,
//       leaf: true,
//       meta: {
//         title: '企业库管理',
//         icon: 'table'
//       }
//     },{
//       path: 'hschool',
//       name: '高校院所库管理',
//       component: hschool,
//       leaf: true,
//       meta: {
//         title: '高校院所库管理',
//         icon: 'table'
//       }
//     },{
//       path: 'mechanism',
//       name: 'mechanism',
//       component: mechanism,
//       leaf: true,
//       meta: {
//         title: '服务机构库管理',
//         icon: 'table'
//       }
//     },{
//       path: 'expertEdit',
//       name: 'expertEdit',
//       component: expertEdit,
//       leaf: true,
//       hidden: true,
//       meta: {
//         title: '专家库资料填写',
//         icon: 'table'
//       }
//     },{
//       path: 'enterpriseEdit',
//       name: 'enterpriseEdit',
//       component: enterpriseEdit,
//       leaf: true,
//       hidden: true,
//       meta: {
//         title: '企业库资料填写',
//         icon: 'table'
//       }
//     },{
//       path: 'hschoolEdit',
//       name: 'hschoolEdit',
//       component: hschoolEdit,
//       leaf: true,
//       hidden: true,
//       meta: {
//         title: '高校院所库资料填写',
//         icon: 'table'
//       }
//     },{
//       path: 'mechanismEdit',
//       name: 'mechanismEdit',
//       component: mechanismEdit,
//       leaf: true,
//       hidden: true,
//       meta: {
//         title: '服务机构库资料填写',
//         icon: 'table'
//       }
//     },{
//       path: 'demandLibrary',
//       name: '需求库管理',
//       component: demandLibrary,
//       leaf: true,
//       meta: {
//         title: '需求库管理',
//         icon: 'table'
//       }
//     },{
//       path: 'demandLibraryEdit',
//       name: 'demandLibraryEdit',
//       component: demandLibraryEdit,
//       leaf: true,
//       hidden: true,
//       meta: {
//         title: '需求库资料填写',
//         icon: 'table'
//       }
//     },
//     {
//       path: 'achieveLibrary',
//       name: '成果库管理',
//       component: achieveLibrary,
//       leaf: true,
//       meta: {
//         title: '成果库管理',
//         icon: 'table'
//       }
//     },{
//       path: 'achieveLibraryEdit',
//       name: 'achieveLibraryEdit',
//       component: achieveLibraryEdit,
//       leaf: true,
//       hidden: true,
//       meta: {
//         title: '成果库资料填写',
//         icon: 'table'
//       }
//     }]
//   },
//   {
//     path: '/buttManage',
//     component: Layout,
//     name: '对接管理',
//     meta: {
//       title: '对接管理',
//       icon: 'tree'
//     },
//     leaf: false,
//     children: [{
//       path: 'buttExpert',
//       name: '对接维护',
//       component: buttExpert,
//       leaf: true,
//       meta: {
//         title: '对接维护',
//         icon: 'table'
//       }
//     }]
//   },  {
//       path: '/register',
//       component: Layout,
//       name: '统计',
//       meta: {
//         title: '统计',
//         icon: 'tree'
//       },
//       leaf: false,
//       children: [{
//         path: 'registerSee',
//         name: '访问统计',
//         component: registerSee,
//         leaf: true,
//         meta: {
//           title: '访问统计',
//           icon: 'table'
//         }
//       },{
//         path: 'registerUser',
//         name: '注册用户统计',
//         component: registerUser,
//         leaf: true,
//         meta: {
//           title: '注册用户统计',
//           icon: 'table'
//         }
//       },{
//         path: 'registerExpert',
//         name: '专家统计',
//         component: registerExpert,
//         leaf: true,
//         meta: {
//           title: '专家统计',
//           icon: 'table'
//         }
//       },{
//         path: 'registerQy',
//         name: '企业统计',
//         component: registerQy,
//         leaf: true,
//         meta: {
//           title: '企业统计',
//           icon: 'table'
//         }
//       },{
//         path: 'registerHshcool',
//         name: '高校统计',
//         component: registerHshcool,
//         leaf: true,
//         meta: {
//           title: '高校统计',
//           icon: 'table'
//         }
//       },{
//         path: 'registerfwjg',
//         name: '服务机构统计',
//         component: registerfwjg,
//         leaf: true,
//         meta: {
//           title: '服务机构统计',
//           icon: 'table'
//         }
//       },{
//         path: 'registerXq',
//         name: '需求统计',
//         component: registerXq,
//         leaf: true,
//         meta: {
//           title: '需求统计',
//           icon: 'table'
//         }
//       },{
//         path: 'registerCg',
//         name: '成果统计',
//         component: registerCg,
//         leaf: true,
//         meta: {
//           title: '成果统计',
//           icon: 'table'
//         }
//       },{
//         path: 'registerDj',
//         name: '对接统计',
//         component: registerDj,
//         leaf: true,
//         meta: {
//           title: '对接统计',
//           icon: 'table'
//         }
//       }]
//     },
//
//
//
//
//
//     {
//       path: '/robot',
//       component: Layout,
//       name: '机器人',
//       meta: {
//         title: '机器人',
//         icon: 'tree'
//       },
//       leaf: false,
//       children: [{
//         path: 'robotResponse',
//         name: '应答设置',
//         component: robotResponse,
//         leaf: true,
//         meta: {
//           title: '应答设置',
//           icon: 'table'
//         }
//       }]
//     },
//
//
//
//
// ]




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
