{"source":"webpackJsonp([18],{\"T+/8\":function(e,t,n){\"use strict\";Object.defineProperty(t,\"__esModule\",{value:!0});var o=n(\"m1D0\"),i=n(\"yqma\");var r=function(e){n(\"s6Ki\")},a=n(\"VU/8\")(o.a,i.a,!1,r,null,null);t.default=a.exports},bUp0:function(e,t,n){\"use strict\";t.d=function(e){return Object(o.a)({url:\"/sysManager/menu/tree\",method:\"get\",data:e})},t.e=function(e){return Object(o.a)({url:\"/user/getMenus\",method:\"get\",data:e})},t.c=function(e){return Object(o.a)({url:\"/sysManager/role?id=\"+e,method:\"get\"})},t.a=function(e){return Object(o.a)({url:\"/sysManager/menu\",method:\"post\",data:e})},t.f=function(e){return Object(o.a)({url:\"/sysManager/menu\",method:\"post\",data:e})},t.b=function(e){return Object(o.a)({url:\"/sysManager/menu?id=\"+e,method:\"delete\"})};var o=n(\"vLgD\")},ipRY:function(e,t,n){e.exports=n.p+\"static/img/bg-1.21328f7.jpg\"},lE51:function(e,t,n){(e.exports=n(\"FZ+f\")(!1)).push([e.i,'.login-container{width:100%;height:100vh;background-repeat:no-repeat;background-size:cover;background-position:50%;background-attachment:fixed;position:relative;z-index:1;overflow:hidden;font-size:20px}.login-container .content{width:986px;height:518px;position:absolute;left:50%;z-index:2;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%)}.login-container .content .left-content,.login-container .content .right-content{width:500px;height:500px;-webkit-filter:drop-shadow(4px 4px 4px #cbbd63);filter:drop-shadow(4px 4px 4px #CBBD63)}.login-container .content .left-content{position:absolute;left:0;top:0;z-index:2;background-repeat:no-repeat;background-position:50%;background-size:cover}.login-container .content .right-content{position:absolute;right:0;bottom:-6%;z-index:3}.login-container .content .right-content .box-card .item{position:relative}.login-container .content .right-content .box-card .item .el-tabs__nav-wrap{width:180px;padding-left:12px;overflow:hidden}.login-container .content .right-content .box-card .item .el-form-item__label{display:block;margin-left:60px;height:36px;color:#9ba3af}.login-container .content .right-content .box-card .item .resetPaw{font-size:14px;color:#9ba3af;float:right;margin-right:60px;cursor:pointer}.login-container .content .right-content .box-card .item .el-form-item__content{width:340px}.login-container .content .right-content .box-card .item .el-form-item__content .el-button{width:100px}.login-container .content .right-content .box-card .item .toLogin{display:inline-block;margin-left:20px;color:#483d8b;cursor:pointer;font-size:14px}.login-container .content .right-content .box-card .item .loginQuick{margin-top:30px}.login-container .content .right-content .box-card .item .loginQuick p{height:24px;line-height:24px;color:#9ba3af;font-size:14px}.login-container .content .right-content .box-card .item .loginQuick p:after,.login-container .content .right-content .box-card .item .loginQuick p:before{content:\"\";position:absolute;height:1px;width:20%;background-color:#eee;margin-top:10px}.login-container .content .right-content .box-card .item .loginQuick p:before{left:20%}.login-container .content .right-content .box-card .item .loginQuick p:after{right:20%}.login-container .content .right-content .box-card .item .loginQuick p span{display:inline-block;margin-top:-10px}.login-container .content .right-content .box-card .item .loginQuick .quick-content ul{list-style:none}.login-container .content .right-content .box-card .item .loginQuick .quick-content ul li{width:50px;height:50px;display:inline-block;cursor:pointer}.login-container .content .right-content .box-card .item .loginQuick .quick-content ul li img{width:40px;height:40px}',\"\"])},m1D0:function(e,t,n){\"use strict\";var o=n(\"mvHQ\"),a=n.n(o),i=n(\"Xxa5\"),s=n.n(i),r=n(\"exGp\"),l=n.n(r),c=n(\"M9A7\"),u=(n(\"E4LH\"),n(\"bUp0\")),g=n(\"UUZ+\"),d=n(\"ipRY\"),m=n.n(d),p=[];t.a={name:\"login\",data:function(){return{leftImage:m.a,restEmail:!1,labelPosition:\"top\",login_method:\"first\",loginVerify:{phone:\"\",number:\"\"},loginForm:{userName:\"admin\",password:\"admin\",isAdmin:!1},loginRules:{username:[{required:!0,trigger:\"blur\",validator:function(e,t,n){n()}}],password:[{required:!0,trigger:\"blur\",validator:function(e,t,n){t.length<5?n(new Error(\"密码不能小于5位\")):n()}}]},loading:!1,pwdType:\"password\",callbackUrl:\"\"}},created:function(){this.keyupSubmit()},mounted:function(){this.$route.query.callback&&(this.callbackUrl=this.$route.query.callback)},methods:{keyupSubmit:function(){var t=this;document.onkeydown=function(e){13===window.event.keyCode&&t.handleLogin()}},tosendEmailActivate:function(){var a=this;return l()(s.a.mark(function e(){var t,n,o,i,r;return s.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return(t={}).email=a.loginForm.userName,e.next=4,Object(c.i)(t);case 4:n=e.sent,n.data,o=n.success,i=n.message,o?(r=a.$createElement,a.$notify({title:\"用户激活提醒\",message:r(\"i\",{style:\"color: teal\"},\"激活帐户邮件已发送到你的邮箱中\"+a.loginForm.userName+\"，点击里面的激活链接\")}),a.restEmail=!1):a.$message({message:i,type:\"success\"});case 9:case\"end\":return e.stop()}},e,a)}))()},handleClick:function(e,t){console.log(e,t)},toresetPaw:function(){this.$router.push({path:\"/resetPaw\"})},toresetPawManage:function(){this.$router.push({path:\"/resetPawManage\"})},toRegister:function(){this.$router.push({path:\"/register\"})},change:function(e){this.loading=!1},showPwd:function(){\"password\"===this.pwdType?this.pwdType=\"\":this.pwdType=\"password\"},login:function(e){window.sessionStorage.setItem(\"user\",a()(e)),Object(g.a)(p,e)},forload:function(e){for(var t=window.sessionStorage.getItem(\"userType\"),n=0;n<e.length;n++){if(e[n].children&&0<e[n].children.length&&0==e[n].leaf)e[n].meta={title:e[n].label,icon:\"tree\"},e[n].path=\"/\"+e[n].menuUrl,e[n].leaf=!1,e[n].component=e[n].menuUrl,\"1\"==e[n].isMenu&&(e[n].component=\"layout\",e[n].redirect=\"/\"+e[n].menuUrl+\"/\"+e[n].children[0].menuUrl),\"0\"!=t&&\"101\"!=t&&\"dashboard\"!=e[n].menuUrl&&(e[n].hidden=!0);else if(1==e[n].leaf&&\"1\"==e[n].isMenu&&9999!=e[n].sortOrder&&1!=e[n].menuLevel)e[n].meta={title:e[n].label,icon:\"table\"},e[n].path=e[n].menuUrl,e[n].leaf=!0,\"0\"!=t&&\"101\"!=t&&(e[n].hidden=!0),e[n].component=e[n].menuUrl,e[n].children=[];else if(1==e[n].leaf&&\"1\"==e[n].isMenu&&9999==e[n].sortOrder&&1!=e[n].menuLevel)e[n].meta={title:e[n].label,icon:\"table\"},e[n].path=e[n].menuUrl,e[n].name=e[n].menuUrl,e[n].leaf=!0,e[n].hidden=!0,e[n].component=e[n].menuUrl,e[n].children=[];else if(1==e[n].menuLevel&&\"1\"==e[n].isMenu&&1==e[n].leaf){var o={};o.meta={title:e[n].label,icon:\"table\"},o.path=e[n].menuUrl,o.leaf=!0,o.component=e[n].menuUrl,e[n].children=[],e[n].children.push(o),e[n].path=\"/index\",e[n].leaf=!1,e[n].component=\"layout\"}e[n].children&&(e[n].children=this.forload(e[n].children))}return e},handleLogin:function(){var r=this;this.loading=!0,this.$store.dispatch(\"Login\",this.loginForm).then(l()(s.a.mark(function e(){var t,n,o,i;return s.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(!r.$store.getters.token||null==r.$store.getters.token||\"\"==r.$store.getters.token){e.next=14;break}return r.loading=!1,window.sessionStorage.setItem(\"user\",a()(\"true\")),e.next=5,Object(u.e)();case 5:t=e.sent,n=t.data,window.sessionStorage.setItem(\"treeData\",a()(n)),o=r.forload(n),r.login(o),r.$router.addRoutes(p),\"\"!=r.callbackUrl?(i=-1<r.callbackUrl.indexOf(\"?\")?\"http://\"+r.callbackUrl+\"&token=\"+window.sessionStorage.getItem(\"token\")+\"&checkStatus=\"+window.sessionStorage.getItem(\"checkStatus\")+\"&userName=\"+window.sessionStorage.getItem(\"userName\")+\"&userId=\"+window.sessionStorage.getItem(\"userId\")+\"&userType=\"+window.sessionStorage.getItem(\"userType\")+\"&cellphone=\"+window.sessionStorage.getItem(\"cellphone\")+\"&email=\"+window.sessionStorage.getItem(\"email\")+\"&orgType=\"+window.sessionStorage.getItem(\"orgType\"):\"http://\"+r.callbackUrl+\"?token=\"+window.sessionStorage.getItem(\"token\")+\"&checkStatus=\"+window.sessionStorage.getItem(\"checkStatus\")+\"&userName=\"+window.sessionStorage.getItem(\"userName\")+\"&userId=\"+window.sessionStorage.getItem(\"userId\")+\"&userType=\"+window.sessionStorage.getItem(\"userType\")+\"&cellphone=\"+window.sessionStorage.getItem(\"cellphone\")+\"&email=\"+window.sessionStorage.getItem(\"email\")+\"&orgType=\"+window.sessionStorage.getItem(\"orgType\"),window.location.href=i):r.$router.push({path:\"/\"}),e.next=16;break;case 14:r.$message({message:r.$store.getters.message,type:\"warning\"}),\"用户未激活,请重新发送邮箱进行激活！\"==r.$store.getters.message&&(r.restEmail=!0);case 16:case\"end\":return e.stop()}},e,r)}))).catch(function(){r.loading=!1})}}}},mvHQ:function(e,t,n){e.exports={default:n(\"qkKv\"),__esModule:!0}},qkKv:function(e,t,n){var o=n(\"FeBl\"),i=o.JSON||(o.JSON={stringify:JSON.stringify});e.exports=function(e){return i.stringify.apply(i,arguments)}},s6Ki:function(e,t,n){var o=n(\"lE51\");\"string\"==typeof o&&(o=[[e.i,o,\"\"]]),o.locals&&(e.exports=o.locals);n(\"rjj0\")(\"26b32e28\",o,!0)},yqma:function(e,t,n){\"use strict\";var o={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n(\"div\",{staticClass:\"login-container\",staticStyle:{background:\"#1896d2\"}},[n(\"div\",{staticClass:\"content\",staticStyle:{top:\"50%\"}},[n(\"div\",{staticClass:\"left-content\",style:{\"background-image\":\"url('\"+t.leftImage+\"')\"}}),t._v(\" \"),n(\"div\",{staticClass:\"right-content\"},[n(\"el-card\",{staticClass:\"box-card\"},[n(\"div\",{staticClass:\"clearfix\",attrs:{slot:\"header\"},slot:\"header\"},[n(\"span\",[t._v(\"登录\")])]),t._v(\" \"),n(\"div\",{staticClass:\"item\"},[n(\"el-form\",{ref:\"loginForm\",attrs:{model:t.loginForm,rules:t.loginRules,\"label-position\":t.labelPosition,\"label-width\":\"100px\"}},[n(\"el-form-item\",{attrs:{label:\"\"}},[n(\"el-input\",{attrs:{placeholder:\"请输入用户名\"},model:{value:t.loginForm.userName,callback:function(e){t.$set(t.loginForm,\"userName\",e)},expression:\"loginForm.userName\"}},[n(\"i\",{staticClass:\"el-input__icon el-icon-date\",attrs:{slot:\"prefix\"},slot:\"prefix\"})])],1),t._v(\" \"),n(\"el-form-item\",{staticStyle:{margin:\"0\"},attrs:{label:\"\"}},[n(\"el-input\",{attrs:{type:\"password\",placeholder:\"请输入密码\"},model:{value:t.loginForm.password,callback:function(e){t.$set(t.loginForm,\"password\",e)},expression:\"loginForm.password\"}},[n(\"i\",{staticClass:\"el-input__icon el-icon-date\",attrs:{slot:\"prefix\"},slot:\"prefix\"})])],1),t._v(\" \"),n(\"el-form-item\",{staticStyle:{margin:\"0\"},attrs:{label:\"\"}},[n(\"el-checkbox\",{staticStyle:{float:\"left\"},model:{value:t.loginForm.isAdmin,callback:function(e){t.$set(t.loginForm,\"isAdmin\",e)},expression:\"loginForm.isAdmin\"}},[t._v(\"管理员\")])],1),t._v(\" \"),n(\"el-form-item\",[t.restEmail?t._e():n(\"el-button\",{attrs:{type:\"primary\"},on:{keyup:function(e){if(!(\"button\"in e)&&t._k(e.keyCode,\"enter\",13,e.key))return null;t.handleLogin(e)}},nativeOn:{click:function(e){e.preventDefault(),t.handleLogin(e)}}},[t._v(\"登录\")]),t._v(\" \"),t.restEmail?n(\"el-button\",{attrs:{type:\"primary\"},on:{click:t.tosendEmailActivate}},[t._v(\"重新发送\")]):t._e(),t._v(\" \"),n(\"span\",{staticClass:\"toLogin\",on:{click:t.toRegister}},[t._v(\"立即注册\")]),t._v(\" \"),n(\"span\",{directives:[{name:\"show\",rawName:\"v-show\",value:t.loginForm.isAdmin,expression:\"loginForm.isAdmin\"}],staticClass:\"resetPaw\",on:{click:t.toresetPawManage}},[t._v(\"忘记密码?\")]),t._v(\" \"),n(\"span\",{directives:[{name:\"show\",rawName:\"v-show\",value:!t.loginForm.isAdmin,expression:\"!loginForm.isAdmin\"}],staticClass:\"resetPaw\",on:{click:t.toresetPaw}},[t._v(\"忘记密码?\")])],1)],1)],1)])],1)])])},staticRenderFns:[]};t.a=o}});"}