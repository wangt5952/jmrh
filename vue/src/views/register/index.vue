<template>
<div class="register-container" style="background: #1896d2">
  <div class="content" style="top: 50%;">

    <!-- <div class="left-content" :style="{'background-image': `url('${leftImage}')`}"></div> -->

    <div class="right-content" style="width: 550px;">
      <el-card class="box-card">

        <div slot="header" class="clearfix">
          <span>注册</span>
        </div>

        <div class="item">
          <el-form :label-position="labelPosition" label-width="100px">
            <el-form-item label="">
              <div class="paddingb textl paddingr">
                <el-radio v-model="userType" label="1">专家</el-radio>
                <el-radio v-model="userType" label="2">企业</el-radio>
                <el-radio v-model="userType" label="3">服务机构</el-radio>
                <el-radio v-model="userType" label="4">高校院所</el-radio>
              </div>
            </el-form-item>
            <el-form-item label="">
              <el-input type="text" v-model="registerForm.email" placeholder="请输入邮箱">
                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
              </el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input type="password" v-model="registerForm.password" placeholder="请输入密码">
                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
              </el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input type="password" v-model="registerForm.repassword" placeholder="请再次输入密码">
                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
              </el-input>
            </el-form-item>

            <el-form-item label="">
              <el-input type="text" v-model="registerForm.name" :placeholder="idNumberName">
                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
              </el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input type="text" v-model="registerForm.code" :placeholder="idNumberCode">
                <i slot="prefix" class="el-input__icon el-icon-date"></i></el-input>
              </el-input>
            </el-form-item>
            <el-form-item label="">
              <el-checkbox  v-model="registerForm.agreement" label=""></el-checkbox> <span @click="dialogShowSH = true" style="cursor: pointer;color: #538fe8;">平台服务协议</span>
            </el-form-item>

            <el-form-item>
              <el-button :disabled = !registerForm.agreement v-if="!restEmail" @click="toRegisterDetail" type="primary">提交</el-button>
              <el-button v-if="restEmail" @click="tosendEmailActivate" :disabled="isDisabled" type="primary">{{buttonName}}</el-button>
              <span @click="toLogin" class="toLogin">已有账号，返回登录</span>
            </el-form-item>
          </el-form>
          <!-- <div class="loginQuick">
                  <p><span>快速登录</span></p>
                  <div class="quick-content">
                    <ul class="quickList">
                      <li><img :src="QQImage"/></li>
                      <li><img :src="weiXinImage"/></li>
                    </ul>
                  </div>
                </div> -->
          </el-tab-pane>

          <!-- <el-dialog title="平台服务协议" :visible.sync="dialogShowSH" width="100%" top='5%'>

          <div class="about-main">
            <h3>一、服务条款的确认和接纳</h3>

            <p>界面（上海）网络科技有限公司（以下简称"界面网络"）涉及到的界面网络产品的所有权以及相关软件的知识产权归界面网络所有。界面网络所提供的服务必须按照其发布的章程，服务条款和操作规则严格执行。本服务条款的效力范围及于界面网络的一切产品和服务，用户在享受界面网络的任何单项服务时，应当受本服务条款的约束。用户通过进入注册程序并勾选"我已阅读并同意《界面网络用户注册协议》"，即表示用户与界面网络已达成协议，自愿接受本服务条款的所有内容。</p>

            <p>当用户使用界面网络各单项服务时，用户的使用行为视为其对该单项服务的服务条款以及界面网络在该单项服务中发出的各类公告的同意。</p>

            <h3>二、服务说明</h3>

            <p>界面网络运用自己的操作系统通过国际互联网络为用户提供各项服务。</p>

            <p>（一）用户必须：</p>

            <p>1、提供设备，如个人电脑、手机或其他上网设备。</p>

            <p>2、个人上网和支付与此服务有关的费用。</p>

            <p>（二）根据相关法律、法规规定以及考虑到界面网络服务的重要性，用户同意：</p>
          </div>

          </el-dialog> -->

        </div>

      </el-card>
    </div>

    <div v-if="dialogShowSH" class="about-main" style="position: absolute;top: -150px;background:#fff;padding:60px;height: 140%;">
      <div class="" style="overflow-y: auto;height:100%;font-size: 16px;">

      <div class="" style="text-align: center;">
        <h2>苏南自创区国家军民科技协同创新平台服务使用协议</h2>
      </div>
      <h4>为明确各方权利和义务，就苏南自创区国家军民科技协同创新平台（以下简称“平台”）使用等事宜达成以下协议，共同遵守执行。</h4>
      <h4>一、服务条款的确认和接纳</h4>

      <p>在您决定成为平台注册用户前，请仔细阅读本服务条款。您必须在完全同意如下条款的前提下，才能进行注册，您只有在成为平台注册用户后，才能使用专家对接服务、需求成果对接服务等功能。用户在享受平台服务时必须完全、严格遵守本服务条款。</p>


      <h4>二、服务条款的完善和修改</h4>

      <p>平台根据互联网的发展和中华人民共和国有关法律、法规的变化，不断地完善服务质量并依此修改平台用户服务条款。用户的权利以及义务的表述，均以最新的服务条款为准。平台保留随时修改或中断服务而不需通知您的权利，平台所行使修改或中断服务的权利，不需对您或第三方负责。</p>

      <h4>三、注册用户资格的有关规定</h4>
      <p>（一）在注册平台用户资格时，所提交的资料必须真实有效，否则平台有权拒绝其申请或者撤销其用户资格，并不予任何赔偿。用户的资料发生变化时，应及时修改注册资料并由相关单位重新审核，否则由此造成的用户权利不能全面有效地行使的责任由用户自己承担，平台有权因此取消其用户资格，并不予任何赔偿。</p>
      <p>（二）个人账号只限本人使用，机构用户只限单位使用，不得转让。</p>
      <p>（三）若用户违反本服务条款的规定，平台将有权取消该个人或者该单位的用户资格而无须给与任何补偿。</p>
      <p>（四）用户对本服务条款的修改有任何异议，可自动放弃用户资格。</p>
      <p>（五）注册用户对所发布的信息负责，信息必须真实准确，不涉及国家秘密和内部信息，不涉及版权纠纷等问题。</p>
      <p>（六）注册用户所发布的信息，平台有权无偿使用。</p>


      <h4>四、用户隐私</h4>
      <p>网站保证不公开或披露用户的信息，也不会私自更改用户注册信息。</p>
      <h4>五、拒绝提供担保</h4>
      <p>（一）您须明确同意平台的使用由您个人承担风险。平台明确表示不提供任何类型的担保，不论是明确的或隐含的。</p>
      <p>（二）平台不担保所提供的服务一定能满足您的要求，也不担保服务不会中断，对服务的及时性、安全性、错误发生都不作担保。</p>
      <h4>六、保密条款</h4>
      <p>您应自觉遵守保密纪律，并作以下保密承诺：</p>
      <p>（一）认真遵守国家相关保密法律、法规和规章制度，履行保密义务；</p>
      <p>（二）不发表煽动抗拒、破坏国家的法律、行政法规实施的信息；</p>
      <p>（三）不发表煽动颠覆国家政权，推翻社会主义制度的的信息；</p>
      <p>（四）不发表煽动分裂国家、破坏国家统一的信息；</p>
      <p>（五）不发表煽动民族仇恨、民族歧视，破坏民族团结的信息；</p>
      <p>（六）不发表捏造或者歪曲事实，散布谣言，扰乱社会秩序的信息；</p>
      <p>（七）不发表宣扬封建迷信、淫秽、色情、赌博、暴力、凶杀、恐怖，教唆犯罪的信息；</p>
      <p>（八）不发表公然侮辱他人或者捏造事实诽谤他人的信息；</p>
      <p>（九）不发表涉及国家和军队秘密的信息；</p>
      <p>（十）不发表其他违反国家相关行政法规的信息。</p>
      <p>违反上述承诺，将按照国家有关规定追究责任，将视情进行批评或取消用户资格。</p>
      <h4>七、免责条款</h4>
      <p>（一）平台对任何直接、间接、偶然、特殊及继起的损害不负责任，不予赔偿。</p>
      <p>（二）用户在使用平台所提供的服务时，如遭受任何人身或财务的损失、损害或伤害，不论原因如何，平台均不负责任。由于用户自身原因导致的任何单位资料泄露，本网站不负任何责任。</p>
      <h4>八、关于中断或终止服务</h4>
      <p>一）因发生不可抗拒的事由，导致用户服务无法继续提供，平台将尽快通知您，但不承担由此对您造成的任何损失。</p>
      <p>（二）如用户违反被服务条款中的内容，平台有权取消用户资格，并中止向其继续提供服务。</p>
      <h4>九、服务内容的版权</h4>
      <p>平台提供的服务内容（包括但不限于：文字、照片、图形、图像、图表、声音、动画、视频、音频等），均受版权保护，由平台版权所有，或由第三方授权使用。用户不能擅自复制、再造这些内容、或创造与内容有关的派生产品。</p>
      <p>凡以任何方式登陆本网站或直接、间接使用本网站资料者，视为自愿接受本网站声明的约束。本声明未涉及的问题参见国家有关法律法规，当本声明与国家法律法规冲突时，以国家法律法规为准。</p>
      <h4>用户确认已经认真阅读并完全理解本协议中的各项规定，请按“同意”按钮。</h4>
      <div class="" style="text-align: center;">
        <el-button  @click="agree" type="primary">同意</el-button>
        <el-button  @click="noagree" type="primary">不同意</el-button>
      </div>
    </div>

    </div>
    <!-- <div class="center-content"></div> -->
    <!-- <div class="right-top"></div> -->
    <!-- <div class="left-bottom"></div> -->
  </div>

</div>
</template>

<script>
import {
  registers,
  sendEmailActivate
} from '@/api/login'
export default {
  data() {
    return {
      idNumberName: '请输入名字',
      idNumberCode: '请输入身份证号',
      labelPosition: 'top',
      userType: '1',
      restEmail: false,
      buttonName: "重新发送",
      isDisabled: false,
      time: 60,
      registerForm: { // 普通登录
        email: '',
        password: '',
        repassword: '',
        name: '',
        idNumber: '',
        name: '',
        userType: '',
        agreement :false
      },
      dialogShowSH: false
    }
  },
  mounted() {

  },
  methods: {
    agree() {
      this.registerForm.agreement =true
      this.dialogShowSH = false
    },
    noagree() {
        this.dialogShowSH = false
    },
    toLogin() {
      this.$router.push({
        path: '/login'
      })
    },
    async tosendEmailActivate() {
      let {
        data,
        success,
        message
      } = await sendEmailActivate(this.registerForm)
      if (success) {
        this.$message({
          message: message,
          type: 'success'
        });
          let me = this;
          me.isDisabled = true;
        let interval = window.setInterval(function() {
          me.buttonName = '(' + me.time + '秒)';
          --me.time;
          if (me.time < 0) {
            me.buttonName = "重新发送";
            me.time = 60;
            me.isDisabled = false;
            window.clearInterval(interval);
          }
        }, 1000);
      } else {
        this.$message({
          message: message,
          type: 'success'
        });
      }
    },
    async toRegisterDetail() {
      if(this.userType != 1){
        if (!this.validata.validatoRegistere(this.registerForm)) return
      }else{
        if (!this.validata.validatoRegistere2(this.registerForm)) return
      }
      this.registerForm.userType = this.userType
      if (this.registerForm.agreement == true) {
        this.registerForm.agreement = 1
      } else {
        this.registerForm.agreement = 0
      }
      let {
        data,
        success,
        message
      } = await registers(this.registerForm)
      this.registerForm.agreement = true
      if (success) {
        const h = this.$createElement;
        this.$notify({
          title: '用户激活提醒',
          message: h('i', {
            style: 'color: teal'
          }, '激活帐户邮件已发送到你的邮箱中' + data.email + '，点击里面的激活链接')
        });
        this.restEmail = true
        this.$router.push({
          path: '/login'
        })
      } else {
        this.$message({
          message: message,
          type: 'success'
        });

      }
    }


    // this.$confirm('是否要加入社区?', '提示', {
    //   confirmButtonText: '加入',
    //   cancelButtonText: '不加入',
    //   type: 'warning'
    // }).then(async () => {
    //
    // this.$router.push({
    //   name: 'registerDetail',
    //   params: {
    //     userType: this.userType
    //   }
    // })
    // }).catch(() => {
    //   this.$router.push({
    //     name: '/',
    //     params: {
    //       userType: this.userType
    //     }
    //   })
    // });
  },
  watch: {
    userType() {
      if (this.userType == '0') {
        this.idNumberName = '请输入名字'
        this.idNumberCode = '请输入身份证号'
      } else if (this.userType == '1') {
        this.idNumberName = '请输入名字'
        this.idNumberCode = '请输入身份证号'
      } else if (this.userType == '2') {
        this.idNumberName = '请输入单位名称'
        this.idNumberCode = '请输入统一社会信用代码'
      } else if (this.userType == '3') {
        this.idNumberName = '请输入单位名称'
        this.idNumberCode = '请输入统一社会信用代码'
      } else if (this.userType == '4') {
        this.idNumberName = '请输入单位名称'
        this.idNumberCode = '请输入统一社会信用代码'
      }
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import "src/styles/mixin.scss";
.register-container {
    width: 100%;
    height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center center;
    background-attachment: fixed;
    position: relative;
    z-index: 1;
    overflow: hidden;
    font-size: 20px;

    .content {
        width: 986px;
        height: 518px;
        position: absolute;
        left: 50%;
        z-index: 2;
        // border:1px solid #ccc;
        transform: translate(-50%,-50%);
        .left-content,
        .right-content {
            width: 500px;
            height: 500px;
            // border: 2px dashed  #CBBD63;
            // background-color: rgba(225, 255, 255, 0.1);
            -webkit-filter: drop-shadow(4px 4px 4px #CBBD63);
            filter: drop-shadow(4px 4px 4px #CBBD63);

        }

        .left-content {
            position: absolute;
            left: 0;
            top: 0;
            z-index: 2;
            background-repeat: no-repeat;
            background-position: center center;
            background-size: cover;
        }

        .right-content {
            margin: 0 auto;
            // background-color: rgba(225, 255, 255, 0.6);
            z-index: 3;

            .box-card {
                .clearfix {}
                .item {
                    position: relative;
                    // tabs
                    .el-form-item {
                        margin-top: -12px;
                    }
                    .el-tabs__nav-wrap {
                        width: 180px;
                        padding-left: 12px;
                        overflow: hidden;
                    }
                    // lable
                    .el-form-item__label {
                        display: block;
                        margin-left: 60px;
                        height: 36px;
                        color: #9ba3af;
                    }

                    // input-box
                    .el-form-item__content {
                        .el-button {
                            width: 100px;
                        }
                    }
                    .toLogin {
                        display: inline-block;
                        margin-left: 20px;
                        color: darkslateblue;
                        cursor: pointer;
                        font-size: 14px;
                    }
                }
            }
        }

    }
}
</style>
