{"source":"webpackJsonp([16],{\"9bBU\":function(e,t,r){r(\"mClu\");var i=r(\"FeBl\").Object;e.exports=function(e,t,r){return i.defineProperty(e,t,r)}},C4MV:function(e,t,r){e.exports={default:r(\"9bBU\"),__esModule:!0}},aoCE:function(e,t,r){\"use strict\";var i={render:function(){var t=this,e=t.$createElement,r=t._self._c||e;return r(\"div\",{staticClass:\"register-container\",staticStyle:{background:\"#1896d2\"}},[r(\"div\",{staticClass:\"content\",staticStyle:{top:\"50%\"}},[r(\"div\",{staticClass:\"right-content\",staticStyle:{width:\"550px\"}},[r(\"el-card\",{staticClass:\"box-card\"},[r(\"div\",{staticClass:\"clearfix\",attrs:{slot:\"header\"},slot:\"header\"},[r(\"span\",[t._v(\"注册\")])]),t._v(\" \"),r(\"div\",{staticClass:\"item\"},[r(\"el-form\",{attrs:{\"label-position\":t.labelPosition,\"label-width\":\"100px\"}},[r(\"el-form-item\",{attrs:{label:\"\"}},[r(\"div\",{staticClass:\"paddingb textl paddingr\"},[r(\"el-radio\",{attrs:{label:\"1\"},model:{value:t.userType,callback:function(e){t.userType=e},expression:\"userType\"}},[t._v(\"专家\")]),t._v(\" \"),r(\"el-radio\",{attrs:{label:\"2\"},model:{value:t.userType,callback:function(e){t.userType=e},expression:\"userType\"}},[t._v(\"企业\")]),t._v(\" \"),r(\"el-radio\",{attrs:{label:\"3\"},model:{value:t.userType,callback:function(e){t.userType=e},expression:\"userType\"}},[t._v(\"服务机构\")]),t._v(\" \"),r(\"el-radio\",{attrs:{label:\"4\"},model:{value:t.userType,callback:function(e){t.userType=e},expression:\"userType\"}},[t._v(\"高校院所\")])],1)]),t._v(\" \"),r(\"el-form-item\",{attrs:{label:\"\"}},[r(\"el-input\",{attrs:{type:\"text\",placeholder:\"请输入邮箱\"},model:{value:t.registerForm.email,callback:function(e){t.$set(t.registerForm,\"email\",e)},expression:\"registerForm.email\"}},[r(\"i\",{staticClass:\"el-input__icon el-icon-date\",attrs:{slot:\"prefix\"},slot:\"prefix\"})])],1),t._v(\" \"),r(\"el-form-item\",{attrs:{label:\"\"}},[r(\"el-input\",{attrs:{type:\"password\",placeholder:\"请输入密码\"},model:{value:t.registerForm.password,callback:function(e){t.$set(t.registerForm,\"password\",e)},expression:\"registerForm.password\"}},[r(\"i\",{staticClass:\"el-input__icon el-icon-date\",attrs:{slot:\"prefix\"},slot:\"prefix\"})])],1),t._v(\" \"),r(\"el-form-item\",{attrs:{label:\"\"}},[r(\"el-input\",{attrs:{type:\"password\",placeholder:\"请再次输入密码\"},model:{value:t.registerForm.repassword,callback:function(e){t.$set(t.registerForm,\"repassword\",e)},expression:\"registerForm.repassword\"}},[r(\"i\",{staticClass:\"el-input__icon el-icon-date\",attrs:{slot:\"prefix\"},slot:\"prefix\"})])],1),t._v(\" \"),r(\"el-form-item\",{attrs:{label:\"\"}},[r(\"el-input\",{attrs:{type:\"text\",placeholder:t.idNumberName},model:{value:t.registerForm.name,callback:function(e){t.$set(t.registerForm,\"name\",e)},expression:\"registerForm.name\"}},[r(\"i\",{staticClass:\"el-input__icon el-icon-date\",attrs:{slot:\"prefix\"},slot:\"prefix\"})])],1),t._v(\" \"),r(\"el-form-item\",{attrs:{label:\"\"}},[r(\"el-input\",{attrs:{type:\"text\",placeholder:t.idNumberCode},model:{value:t.registerForm.code,callback:function(e){t.$set(t.registerForm,\"code\",e)},expression:\"registerForm.code\"}},[r(\"i\",{staticClass:\"el-input__icon el-icon-date\",attrs:{slot:\"prefix\"},slot:\"prefix\"})])],1),t._v(\" \"),r(\"el-form-item\",{attrs:{label:\"\"}},[r(\"el-checkbox\",{attrs:{label:\"\"},model:{value:t.registerForm.agreement,callback:function(e){t.$set(t.registerForm,\"agreement\",e)},expression:\"registerForm.agreement\"}}),t._v(\" \"),r(\"span\",{staticStyle:{cursor:\"pointer\",color:\"#538fe8\"},on:{click:function(e){t.dialogShowSH=!0}}},[t._v(\"平台服务协议\")])],1),t._v(\" \"),r(\"el-form-item\",[t.restEmail?t._e():r(\"el-button\",{attrs:{disabled:!t.registerForm.agreement,type:\"primary\"},on:{click:t.toRegisterDetail}},[t._v(\"提交\")]),t._v(\" \"),t.restEmail?r(\"el-button\",{attrs:{disabled:t.isDisabled,type:\"primary\"},on:{click:t.tosendEmailActivate}},[t._v(t._s(t.buttonName))]):t._e(),t._v(\" \"),r(\"span\",{staticClass:\"toLogin\",on:{click:t.toLogin}},[t._v(\"已有账号，返回登录\")])],1)],1)],1)])],1),t._v(\" \"),t.dialogShowSH?r(\"div\",{staticClass:\"about-main\",staticStyle:{position:\"absolute\",top:\"-150px\",background:\"#fff\",padding:\"60px\",height:\"140%\"}},[r(\"div\",{staticStyle:{\"overflow-y\":\"auto\",height:\"100%\",\"font-size\":\"16px\"}},[t._m(0,!1,!1),t._v(\" \"),r(\"h4\",[t._v(\"为明确各方权利和义务，就苏南自创区国家军民科技协同创新平台（以下简称“平台”）使用等事宜达成以下协议，共同遵守执行。\")]),t._v(\" \"),r(\"h4\",[t._v(\"一、服务条款的确认和接纳\")]),t._v(\" \"),r(\"p\",[t._v(\"在您决定成为平台注册用户前，请仔细阅读本服务条款。您必须在完全同意如下条款的前提下，才能进行注册，您只有在成为平台注册用户后，才能使用专家对接服务、需求成果对接服务等功能。用户在享受平台服务时必须完全、严格遵守本服务条款。\")]),t._v(\" \"),r(\"h4\",[t._v(\"二、服务条款的完善和修改\")]),t._v(\" \"),r(\"p\",[t._v(\"平台根据互联网的发展和中华人民共和国有关法律、法规的变化，不断地完善服务质量并依此修改平台用户服务条款。用户的权利以及义务的表述，均以最新的服务条款为准。平台保留随时修改或中断服务而不需通知您的权利，平台所行使修改或中断服务的权利，不需对您或第三方负责。\")]),t._v(\" \"),r(\"h4\",[t._v(\"三、注册用户资格的有关规定\")]),t._v(\" \"),r(\"p\",[t._v(\"（一）在注册平台用户资格时，所提交的资料必须真实有效，否则平台有权拒绝其申请或者撤销其用户资格，并不予任何赔偿。用户的资料发生变化时，应及时修改注册资料并由相关单位重新审核，否则由此造成的用户权利不能全面有效地行使的责任由用户自己承担，平台有权因此取消其用户资格，并不予任何赔偿。\")]),t._v(\" \"),r(\"p\",[t._v(\"（二）个人账号只限本人使用，机构用户只限单位使用，不得转让。\")]),t._v(\" \"),r(\"p\",[t._v(\"（三）若用户违反本服务条款的规定，平台将有权取消该个人或者该单位的用户资格而无须给与任何补偿。\")]),t._v(\" \"),r(\"p\",[t._v(\"（四）用户对本服务条款的修改有任何异议，可自动放弃用户资格。\")]),t._v(\" \"),r(\"p\",[t._v(\"（五）注册用户对所发布的信息负责，信息必须真实准确，不涉及国家秘密和内部信息，不涉及版权纠纷等问题。\")]),t._v(\" \"),r(\"p\",[t._v(\"（六）注册用户所发布的信息，平台有权无偿使用。\")]),t._v(\" \"),r(\"h4\",[t._v(\"四、用户隐私\")]),t._v(\" \"),r(\"p\",[t._v(\"网站保证不公开或披露用户的信息，也不会私自更改用户注册信息。\")]),t._v(\" \"),r(\"h4\",[t._v(\"五、拒绝提供担保\")]),t._v(\" \"),r(\"p\",[t._v(\"（一）您须明确同意平台的使用由您个人承担风险。平台明确表示不提供任何类型的担保，不论是明确的或隐含的。\")]),t._v(\" \"),r(\"p\",[t._v(\"（二）平台不担保所提供的服务一定能满足您的要求，也不担保服务不会中断，对服务的及时性、安全性、错误发生都不作担保。\")]),t._v(\" \"),r(\"h4\",[t._v(\"六、保密条款\")]),t._v(\" \"),r(\"p\",[t._v(\"您应自觉遵守保密纪律，并作以下保密承诺：\")]),t._v(\" \"),r(\"p\",[t._v(\"（一）认真遵守国家相关保密法律、法规和规章制度，履行保密义务；\")]),t._v(\" \"),r(\"p\",[t._v(\"（二）不发表煽动抗拒、破坏国家的法律、行政法规实施的信息；\")]),t._v(\" \"),r(\"p\",[t._v(\"（三）不发表煽动颠覆国家政权，推翻社会主义制度的的信息；\")]),t._v(\" \"),r(\"p\",[t._v(\"（四）不发表煽动分裂国家、破坏国家统一的信息；\")]),t._v(\" \"),r(\"p\",[t._v(\"（五）不发表煽动民族仇恨、民族歧视，破坏民族团结的信息；\")]),t._v(\" \"),r(\"p\",[t._v(\"（六）不发表捏造或者歪曲事实，散布谣言，扰乱社会秩序的信息；\")]),t._v(\" \"),r(\"p\",[t._v(\"（七）不发表宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖，教唆犯罪的信息；\")]),t._v(\" \"),r(\"p\",[t._v(\"（八）不发表公然侮辱他人或者捏造事实诽谤他人的信息；\")]),t._v(\" \"),r(\"p\",[t._v(\"（九）不发表涉及国家和军队秘密的信息；\")]),t._v(\" \"),r(\"p\",[t._v(\"（十）不发表其他违反国家相关行政法规的信息。\")]),t._v(\" \"),r(\"p\",[t._v(\"违反上述承诺，将按照国家有关规定追究责任，将视情进行批评或取消用户资格。\")]),t._v(\" \"),r(\"h4\",[t._v(\"七、免责条款\")]),t._v(\" \"),r(\"p\",[t._v(\"（一）平台对任何直接、间接、偶然、特殊及继起的损害不负责任，不予赔偿。\")]),t._v(\" \"),r(\"p\",[t._v(\"（二）用户在使用平台所提供的服务时，如遭受任何人身或财务的损失、损害或伤害，不论原因如何，平台均不负责任。由于用户自身原因导致的任何单位资料泄露，本网站不负任何责任。\")]),t._v(\" \"),r(\"h4\",[t._v(\"八、关于中断或终止服务\")]),t._v(\" \"),r(\"p\",[t._v(\"一）因发生不可抗拒的事由，导致用户服务无法继续提供，平台将尽快通知您，但不承担由此对您造成的任何损失。\")]),t._v(\" \"),r(\"p\",[t._v(\"（二）如用户违反被服务条款中的内容，平台有权取消用户资格，并中止向其继续提供服务。\")]),t._v(\" \"),r(\"h4\",[t._v(\"九、服务内容的版权\")]),t._v(\" \"),r(\"p\",[t._v(\"平台提供的服务内容（包括但不限于：文字、照片、图形、图像、图表、声音、动画、视频、音频等），均受版权保护，由平台版权所有，或由第三方授权使用。用户不能擅自复制、再造这些内容、或创造与内容有关的派生产品。\")]),t._v(\" \"),r(\"p\",[t._v(\"凡以任何方式登陆本网站或直接、间接使用本网站资料者，视为自愿接受本网站声明的约束。本声明未涉及的问题参见国家有关法律法规，当本声明与国家法律法规冲突时，以国家法律法规为准。\")]),t._v(\" \"),r(\"h4\",[t._v(\"用户确认已经认真阅读并完全理解本协议中的各项规定，请按“同意”按钮。\")]),t._v(\" \"),r(\"div\",{staticStyle:{\"text-align\":\"center\"}},[r(\"el-button\",{attrs:{type:\"primary\"},on:{click:t.agree}},[t._v(\"同意\")]),t._v(\" \"),r(\"el-button\",{attrs:{type:\"primary\"},on:{click:t.noagree}},[t._v(\"不同意\")])],1)])]):t._e()])])},staticRenderFns:[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r(\"div\",{staticStyle:{\"text-align\":\"center\"}},[r(\"h2\",[e._v(\"苏南自创区国家军民科技协同创新平台服务使用协议\")])])}]};t.a=i},bOdI:function(e,t,r){\"use strict\";t.__esModule=!0;var i,a=r(\"C4MV\"),s=(i=a)&&i.__esModule?i:{default:i};t.default=function(e,t,r){return t in e?(0,s.default)(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}},bqOp:function(e,t,r){\"use strict\";Object.defineProperty(t,\"__esModule\",{value:!0});var i=r(\"jW5I\"),a=r(\"aoCE\");var s=function(e){r(\"cdgW\")},o=r(\"VU/8\")(i.a,a.a,!1,s,null,null);t.default=o.exports},cdgW:function(e,t,r){var i=r(\"kjs+\");\"string\"==typeof i&&(i=[[e.i,i,\"\"]]),i.locals&&(e.exports=i.locals);r(\"rjj0\")(\"3ba88aa7\",i,!0)},jW5I:function(e,t,r){\"use strict\";var i=r(\"Xxa5\"),n=r.n(i),a=r(\"exGp\"),s=r.n(a),o=r(\"bOdI\"),l=r.n(o),c=r(\"M9A7\");t.a={data:function(){var e;return{idNumberName:\"请输入名字\",idNumberCode:\"请输入身份证号\",labelPosition:\"top\",userType:\"1\",restEmail:!1,buttonName:\"重新发送\",isDisabled:!1,time:60,registerForm:(e={email:\"\",password:\"\",repassword:\"\",name:\"\",idNumber:\"\"},l()(e,\"name\",\"\"),l()(e,\"userType\",\"\"),l()(e,\"agreement\",!1),e),dialogShowSH:!1}},mounted:function(){},methods:{agree:function(){this.registerForm.agreement=!0,this.dialogShowSH=!1},noagree:function(){this.dialogShowSH=!1},toLogin:function(){this.$router.push({path:\"/login\"})},tosendEmailActivate:function(){var o=this;return s()(n.a.mark(function e(){var t,r,i,a,s;return n.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(c.i)(o.registerForm);case 2:t=e.sent,t.data,r=t.success,i=t.message,r?(o.$message({message:i,type:\"success\"}),(a=o).isDisabled=!0,s=window.setInterval(function(){a.buttonName=\"(\"+a.time+\"秒)\",--a.time,a.time<0&&(a.buttonName=\"重新发送\",a.time=60,a.isDisabled=!1,window.clearInterval(s))},1e3)):o.$message({message:i,type:\"success\"});case 7:case\"end\":return e.stop()}},e,o)}))()},toRegisterDetail:function(){var o=this;return s()(n.a.mark(function e(){var t,r,i,a,s;return n.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:if(1==o.userType){e.next=5;break}if(o.validata.validatoRegistere(o.registerForm)){e.next=3;break}return e.abrupt(\"return\");case 3:e.next=7;break;case 5:if(o.validata.validatoRegistere2(o.registerForm)){e.next=7;break}return e.abrupt(\"return\");case 7:return o.registerForm.userType=o.userType,1==o.registerForm.agreement?o.registerForm.agreement=1:o.registerForm.agreement=0,e.next=11,Object(c.h)(o.registerForm);case 11:t=e.sent,r=t.data,i=t.success,a=t.message,i?(s=o.$createElement,o.$notify({title:\"用户激活提醒\",message:s(\"i\",{style:\"color: teal\"},\"激活帐户邮件已发送到你的邮箱中\"+r.email+\"，点击里面的激活链接\")}),o.restEmail=!0,o.$router.push({path:\"/login\"})):o.$message({message:a,type:\"success\"});case 16:case\"end\":return e.stop()}},e,o)}))()}},watch:{userType:function(){\"0\"==this.userType?(this.idNumberName=\"请输入名字\",this.idNumberCode=\"请输入身份证号\"):\"1\"==this.userType?(this.idNumberName=\"请输入名字\",this.idNumberCode=\"请输入身份证号\"):\"2\"==this.userType?(this.idNumberName=\"请输入单位名称\",this.idNumberCode=\"请输入统一社会信用代码\"):\"3\"==this.userType?(this.idNumberName=\"请输入单位名称\",this.idNumberCode=\"请输入统一社会信用代码\"):\"4\"==this.userType&&(this.idNumberName=\"请输入单位名称\",this.idNumberCode=\"请输入统一社会信用代码\")}}}},\"kjs+\":function(e,t,r){(e.exports=r(\"FZ+f\")(!1)).push([e.i,\".register-container{width:100%;height:100vh;background-repeat:no-repeat;background-size:cover;background-position:50%;background-attachment:fixed;position:relative;z-index:1;overflow:hidden;font-size:20px}.register-container .content{width:986px;height:518px;position:absolute;left:50%;z-index:2;-webkit-transform:translate(-50%,-50%);transform:translate(-50%,-50%)}.register-container .content .left-content,.register-container .content .right-content{width:500px;height:500px;-webkit-filter:drop-shadow(4px 4px 4px #cbbd63);filter:drop-shadow(4px 4px 4px #CBBD63)}.register-container .content .left-content{position:absolute;left:0;top:0;z-index:2;background-repeat:no-repeat;background-position:50%;background-size:cover}.register-container .content .right-content{margin:0 auto;z-index:3}.register-container .content .right-content .box-card .item{position:relative}.register-container .content .right-content .box-card .item .el-form-item{margin-top:-12px}.register-container .content .right-content .box-card .item .el-tabs__nav-wrap{width:180px;padding-left:12px;overflow:hidden}.register-container .content .right-content .box-card .item .el-form-item__label{display:block;margin-left:60px;height:36px;color:#9ba3af}.register-container .content .right-content .box-card .item .el-form-item__content .el-button{width:100px}.register-container .content .right-content .box-card .item .toLogin{display:inline-block;margin-left:20px;color:#483d8b;cursor:pointer;font-size:14px}\",\"\"])},mClu:function(e,t,r){var i=r(\"kM2E\");i(i.S+i.F*!r(\"+E39\"),\"Object\",{defineProperty:r(\"evD5\").f})}});"}