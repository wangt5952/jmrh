<template>
<div class="tab-container">
  <div class="tools">
  </div>
  <div class="" style="padding-left:35%;margin: 0 0 80px 0;">
    <h2>{{titleName}}</h2>
  </div>
  <div v-if="userType == '0'" class="">
    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="20">
        <el-col :span="10">
          <el-form-item label="姓名">
            <el-input v-model="per.name" placeholder="请输入姓名" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-select v-model="per.sex" style="width:100px" placeholder="请选择">
              <el-option label="男" key="1" value='1'>
              </el-option>
              <el-option label="女" key="0" value='0'>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="出生日期">
            <el-date-picker v-model="per.dateValue" type="date" placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="所在地区">
            <area-cascader :level="1" v-model="per.address" :data="pcaa"></area-cascader>
            <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
          </el-form-item>
          <el-form-item label="联系地址">
            <el-input placeholder="请输入联系地址" v-model="per.selected" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input placeholder="请输入邮箱" v-model="per.ecode" style="width:80%"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="10">
          <el-form-item label="身份证号">
            <el-input placeholder="请输入身份证号" v-model="per.id" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="上传身份证正面">
            <div>
              <!--这是正面照-->
              <div class="photo">
                <el-upload class="upload-demo" action="" :on-change="handleChange1" :on-preview="handlePreview" :on-remove="handleRemove1" :file-list="per.cardPositive" list-type="picture">
                  <el-button size="small" type="primary">点击上传</el-button>
                </el-upload>
                <!-- <input type="file" @change="uploadImg($event)" id="IdCard"> -->
                <!-- <input type="file"  @change="per.cardPositive"  id="IdCard"> -->

              </div>
            </div>
          </el-form-item>
          <el-form-item label="上传身份证反面">
            <div>
              <!--这是背面照-->
              <div class="photo photo1">
                <el-upload class="upload-demo" action="" :on-change="handleChange2" :on-preview="handlePreview" :on-remove="handleRemove2" :file-list="per.cardSide" list-type="picture">
                  <el-button size="small" type="primary">点击上传</el-button>
                </el-upload>

              </div>
            </div>
          </el-form-item>
          <el-form-item label="手持身份证">
            <div>
              <!--这是背面照-->
              <div class="photo photo1">
                <el-upload class="upload-demo" action="" :on-change="handleChange3" :on-preview="handlePreview" :on-remove="handleRemove2" :file-list="per.cardHands" list-type="picture">
                  <el-button size="small" type="primary">点击上传</el-button>
                </el-upload>

              </div>
            </div>
          </el-form-item>

          <el-form-item label="手机号">
            <el-input placeholder="请输入手机号" v-model="per.phone" style="width:80%"></el-input>
          </el-form-item>

        </el-col>

      </el-row>
      <el-row>
        <div style="padding-left: 35%;margin:  80px 0  0  0;">
          <el-button type="primary" @click="saveFile" style="width: 120px;">完善并保存</el-button>
          <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
        </div>
      </el-row>
    </el-form>


  </div>

  <div v-if="userType == '1'" class="">
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 联系人信息</span> 联系人信息
      </el-tab-pane>
      <el-tab-pane label="企业法人信息">企业法人信息</el-tab-pane>
      <el-tab-pane label="其他信息">其他信息</el-tab-pane>
      <el-tab-pane label="其他信息">其他信息</el-tab-pane>
    </el-tabs>
  </div>

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
  getAllrole,
} from '@/api/role'

export default {
  data() {
    return {
      userType: '1',
      dateValue: '',
      contrary: '',
      contrary2: '',
      selected: [],
      titleName: '',
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区

      school: {
        desc: '',
        network: '',
        email: '',
        address: '',
        selected: '',
        name: '',
      },
      mech: {
        registerNature: '',
        registerNatureType: '',
        registerImplement: '',
        registerSupport: '',
        registerPorcolumnDefinitions: [{
          name: '',
          time: '',
          source: ''
        }],
        MHPer: '',
        underPer: '',
        perNum: '',
        ecode: '',
        address: '',
        selected: '',
        comname: '',
        fdemail: '',
        fdphone: '',
        fdname: '',
        lxemail: '',
        lxphone: '',
        lxzw: '',
        lxname: ''
      },
      com: {
        lxname: '',
        lxzw: '',
        lxphone: '',
        lxemail: '',
        lxselected: '',
        lxaddress: '',
        lxecode: '',
        lpname: '',
        lpzw: '',
        lpphone: '',
        lpemail: '',
        registerFinance: '1',
        registerValue: '',
        registerType: '1',
        registerH: '1',
        registerSite: '1',
        registerHSite: '1',
        registerMarket: '1',
        registerMarkeSite: '1',
        registerField: '1',
      },
      per: {
        name: '',
        dateValue: '',
        sex: '',
        selected: '',
        address: '',
        ecode: '',
        id: '',
        cardPositive: [],
        cardSide: [],
        cardHands: [],
        phone: '',
      },

    }
  },
  async mounted() {
    this.listLoading = false
    if (window.sessionStorage.getItem('userType')) {
      this.userType = window.sessionStorage.getItem('userType')
    }
    if (this.userType == '0') {
      this.titleName = '个人资料完善'
    } else if (this.userType == '1') {
      this.titleName = '企业资料完善'
    } else if (this.userType == '2') {
      this.titleName = '服务机构资料完善'
    } else if (this.userType == '3') {
      this.titleName = '高效院所资料完善'
    }
    this.loadPageList()
  },
  computed: {},
  methods: {
    async loadPageList() {
      let data = await getAllrole()
      this.list = data.data
      this.loading = false
    },

    async saveCreate(obj) {
      if (!this.validata.validaRole(obj)) return

      let arr = []
      obj.method = 'put'
      let data = await saveRole(obj)

      if (data.code === 10000) {
        this.$message({
          message: '修改成功',
          type: 'success'
        });
        this.dialogFormVisible = false
      } else {
        this.$message({
          message: data.message,
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
.tab-container {
  padding: 30px;
}

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
