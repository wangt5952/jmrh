<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-radio v-model="radioData" label="1">个人</el-radio>
      <el-radio v-model="radioData" label="2">企业</el-radio>
      <el-radio v-model="radioData" label="3">服务</el-radio>

    </div>
  </div>

  <div v-if="radioData == '1'" class="">
    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="12">
        <el-col :span="12">
          <el-form-item label="姓名">
            <el-input placeholder="请输入姓名" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-select style="width:100px" placeholder="请选择">
              <el-option label="男" key="1" value='1'>
              </el-option>
              <el-option label="女" key="0" value='0'>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="出生日期">
            <el-date-picker v-model="dateValue" type="date" placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="身份证号">
            <el-input placeholder="请输入身份证号" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="所在地区">
            <area-cascader :level="1" v-model="selected" :data="pcaa"></area-cascader>{{selected}}
            <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
          </el-form-item>
          <el-form-item label="联系地址">
            <el-input placeholder="请输入联系地址" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="上传身份证">
            <div>
  <!--这是正面照-->
  <div class="photo">
  <a href="javascript">
  <img :src="contrary" id="bgpic">
  <input type="file" id="IdCard">
  </a>
  </div>
  <!--这是背面照-->
  <div class="photo photo1">
  <a href="javascript">
  <img :src="contrary2" id="bgpicb">
  <input type="file" id="IdCardbg">
      </a>
      </div>
      </div>
          </el-form-item>

          <el-form-item label="手机号">
            <el-input placeholder="请输入手机号" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input placeholder="请输入邮箱" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-button type="primary">保存</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>

  <div v-if="radioData == '2'" class="">
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
      radioData: '1',
      dateValue: '',
      contrary:'',
      contrary2:'',
      selected: [],
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区

    }
  },
  async mounted() {
    this.listLoading = false
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
