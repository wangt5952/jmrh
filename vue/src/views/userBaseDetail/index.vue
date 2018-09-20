<template>
<div class="tab-container">
  <div class="" style="padding-left:35%;margin: 0 0 40px 0;">
    <h2>{{titleName}}</h2>
  </div>
  <el-form class="" label-width="30%" style="text-align:left">
    <el-row :gutter="20">
      <el-col :span="10">
        <el-form-item label="姓名(单位名)">
          <el-input v-model="per.name" disabled placeholder="请输入姓名" style="width:80%"></el-input>
        </el-form-item>

        <el-form-item label="身份证号">
          <el-input placeholder="请输入身份证号" disabled v-model="per.code" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="per.userName" disabled placeholder="请输入姓名" style="width:80%"></el-input>
        </el-form-item>

      </el-col>

      <el-col :span="10">

        <el-form-item label="是否修改邮箱">
          <el-switch
            v-model="per.needModifyEmail" >
          </el-switch>
        </el-form-item>

        <el-form-item label="邮箱">
          <el-input :disabled = !per.needModifyEmail placeholder="请输入邮箱" v-model="per.email" style="width:80%"></el-input>
        </el-form-item>

        <el-form-item v-show="per.needModifyEmail" label="">
          <el-input v-model="per.code1" placeholder="请输入邮箱验证码" style="width:230px">13</el-input>
          <el-button type="primary" @click="sendcode(0)" :disabled="isDisabled1">{{buttonName1}}</el-button>
        </el-form-item>

        <el-form-item label="是否修改手机号">
          <el-switch
            v-model="per.needModifyMobile" >
          </el-switch>
        </el-form-item>

        <el-form-item label="手机号">
          <el-input :disabled = !per.needModifyMobile placeholder="请输入手机号" v-model="per.cellphone" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item v-show="per.needModifyMobile" label="">
          <el-input v-model="per.code2" placeholder="请输入邮箱验证码" style="width:230px">13</el-input>
          <el-button type="primary" @click="sendcode(1)" :disabled="isDisabled2">{{buttonName2}}</el-button>
        </el-form-item>
      </el-col>

    </el-row>
    <el-row>
      <div style="padding-left: 35%;margin: 40px 0  0  0;">
        <el-button type="primary" @click="saveCreate(per)" style="width: 120px;">保存</el-button>
      </div>
    </el-row>
  </el-form>

</div>
</template>

<script>
import 'vue-area-linkage/dist/index.css'; // v2 or higher
import contrary from '@/assets/images/QQ.png'
import contrary2 from '@/assets/images/QQ.png'
import {
  pca,
  pcaa
} from "area-data";
import {
  getBaseUserDetail,
  setBaseUserDetail
} from '@/api/login'
import {
  sendEmailCode,
  sendCellphoneCode
} from '@/api/user'

export default {
  data() {
    return {
      needModifyEmail: false,
      needModifyMobile: false,
      listLoading: true,
      titleName: '',
      buttonName1: "获取邮箱验证码",
      buttonName2: "获取手机验证码",
      isDisabled1: false,
      isDisabled2: false,
      time1: 60,
      time2: 60,
      per: {
        needModifyEmail: false,
        needModifyMobile: false,
        name: '',
        userName: '',
        email: '',
        idNumber: '',
        cellphone: '',
        code1: '',
        code2: '',
      },
    }
  },
  async mounted() {
    this.listLoading = false
    this.titleName = '基础资料完善'
    this.loadPageList()
    // if (this.userType == '1' || this.userType == '2' || this.userType == '3' || this.userType == '4') {
    //   // this.needModifyEmail = true
    // }
  },
  computed: {},
  methods: {

    async loadPageList() {
      let {
        data,
        success
      } = await getBaseUserDetail()
      if (success) {
        this.per = data
        this.loading = false
      }
    },
    async sendcode(checkStatus) {


      if (checkStatus == 0) {
        let me = this;
        me.isDisabled1 = true;
        let interval = window.setInterval(function() {
          me.buttonName1 = '（' + me.time1 + '秒）后重新发送';
          --me.time1;
          if (me.time1 < 0) {
            me.buttonName1 = "重新发送";
            me.time1 = 60;
            me.isDisabled1 = false;
            window.clearInterval(interval);
          }
        }, 1000);

        let obj = {}
        obj.email = this.per.email
        const {
          message,
          success
        } = await sendEmailCode(obj)
        if (success) {
          this.$message({
            message: '发送成功',
            type: 'success'
          });
        } else {
          this.$message({
            message: '发送失败',
            type: 'success'
          });
        }
      } else if (checkStatus == 1) {
        let me = this;
        me.isDisabled2 = true;
        let interval = window.setInterval(function() {
          me.buttonName2 = '（' + me.time2 + '秒）后重新发送';
          --me.time2;
          if (me.time2 < 0) {
            me.buttonName2 = "重新发送";
            me.time2 = 60;
            me.isDisabled2 = false;
            window.clearInterval(interval);
          }
        }, 1000);

        let obj = {}
        obj.cellphone = this.per.cellphone
        const {
          message,
          success
        } = await sendCellphoneCode(obj)
        if (success) {
          this.$message({
            message: '发送成功',
            type: 'success'
          });
        } else {
          this.$message({
            message: '发送失败',
            type: 'success'
          });
        }
      }


    },
    async saveCreate(obj) {
      let aobj = {}
      aobj = obj
      aobj.id = obj.id
      aobj.userName = obj.userName
      aobj.cellphone = obj.cellphone
      if (!this.validata.validaUserBaseDetail(obj)) return
      let {
        data,
        success,
        message
      } = await setBaseUserDetail(aobj)
      debugger
      if (success) {
        this.$message({
          message: '保存成功',
          type: 'success'
        });
        this.dialogFormVisible = false
      } else {
        this.$message({
          message: message,
          type: 'success'
        });
      }

    },


    async handleEdit(data, type) {
      if (type === 'edit') {
        this.obj = data
        this.dialogStatus = 'update'
        this.dialogsave = true
        this.dialogadd = false
        this.dialogFormVisible = true
      } else if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await delRole(data.id)
          this.list.splice(this.list.indexOf(data), 1)
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          // this.splice(data.id, 1);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    },

  }
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>

.tools {
  height: 5%
}

.area-select .area-selected-trigger {
  position: relative;
  display: block;
  font-size: 14px;
  cursor: pointer;
  margin: 0;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  height: 100%;
  padding: 0px 0px 0px 12px;
}
</style>
