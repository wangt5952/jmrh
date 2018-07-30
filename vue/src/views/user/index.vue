<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <!-- <el-button style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加用户</el-button> -->

    </div>
  </div>

  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" width="50" align="center" label="ID">
    </el-table-column>
    <el-table-column align="center" label="名称(单位)">
      <template slot-scope="scope">
                    <span>{{ scope.row.name }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="昵称">
      <template slot-scope="scope">
                    <span>{{ scope.row.userName }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="手机号">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.cellphone}}</span>
                </template>
    </el-table-column>

    <el-table-column align="center" label="用户类别">
      <template slot-scope="scope">
                    <span v-if="scope.row.userType =='1'">
                        个人/专家</span>
                  <span v-if="scope.row.userType =='2'">
                          企业</span>
                    <span v-if="scope.row.userType =='3'">
                      服务机构  </span>
                      <span v-if="scope.row.userType =='4'">
                    高校院校</span>
                    <span v-if="scope.row.userType =='5'">
                    军方</span>
                </template>
    </el-table-column>

    <el-table-column align="center" label="邮箱">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.email}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="身份证号">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.idNumber}}</span>
                </template>
    </el-table-column>


    <el-table-column align="center" label="用户状态">
      <template slot-scope="scope">
        <span v-if="scope.row.status =='1'">开启</span>
        <span v-if="scope.row.status =='2'">关闭</span>
                    </template>
    </el-table-column>
   <el-table-column v-if="userType =='0'" align="center" label="操作">
      <template slot-scope="scope">
                    <div style="margin:2% 2% 2% 2%">
                        <el-button size="small" v-if="scope.row.status =='2'" @click="handleEdit('1')" type="" style="border-radius: 5px;">开启</el-button>
                        <el-button size="small" v-if="scope.row.status =='1'" @click="handleEdit('2')" type="" style="background: #f44;color: #fff;border-radius: 5px;">禁用</el-button>
                    </div>
                </template>
    </el-table-column>

  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>




  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">

        <el-form-item label="名称(单位)">
          <el-input v-model="obj.name" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="obj.userName" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="全名">
          <el-input v-model="obj.fullName" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="obj.cellphone" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="用户类别">
          <el-input v-model="obj.userType" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="obj.email" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="obj.idNumber" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary" v-if="dialogadd == true" @click="addCreate(obj)">添加</el-button>
      <el-button type="primary" v-if="dialogsave == true"  @click="saveCreate(obj)">修改</el-button>
      <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
    </span>
  </el-dialog>


</div>
</template>

<script>
import {
  getUser,
} from '@/api/user'

import {
  getAllrole
} from '@/api/role'
import {
  depgetAll
} from '@/api/department'
export default {
  data() {
    return {
      input: '',
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      dialogFormVisible: false,
      dialogShowDep: false,
      dialogadd: false,
      dialogsave: false,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id',
        objName: ''
      },
      total: null,
      textMap: {
        update: '编辑',
        dep: '部门详情',
        role: '角色详情',
        create: '添加'
      },
      obj: {
        userName: '',
        userPassword: '',
        fullName: '',
        department: '',
      },
      selected: [],
      options: [{
        label: 'foo',
        value: 'Foo'
      }, {
        label: 'boo',
        value: 'boo'
      }, {
        label: 'coo',
        value: 'coo'
      }],
      treeData: [],
      loading: true,
        userType:'',
    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()
    this.userType =  window.sessionStorage.getItem('userType')

  },
  computed: {},
  methods: {
    async loadPageList() {
      if (this.input) {
        this.listQuery.email = this.input
      } else {
        this.listQuery.email = ''
      }
      let {
        data,
        success
      } = await getUser(this.listQuery)
      if (success) {
        this.list = data.list
        this.loading = false
      }
    },
    handleSizeChange(val) {
      if (!isNaN(val)) {
        this.listQuery.limit = val
      }
      this.loadPageList()
    },
    handleCurrentChange(val) {
      if (!isNaN(val)) {
        this.listQuery.page = val
      }
      this.loadPageList()
    },
    async handleCreate() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.dialogadd = true
      this.dialogsave = false
      this.obj = {
        userName: '',
        userPassword: '',
        fullName: '',
        department: '',
      }
      this.loadoptions()
    },
    async loadoptions() {
      let getAlldata = await getAllrole()
      let arr = []
      getAlldata = getAlldata.data
      for (let i = 0; i < getAlldata.length; i++) {
        let obja = {}
        obja.label = getAlldata[i].roleName
        obja.value = getAlldata[i].id
        arr.push(obja)
      }
      this.options = arr
      this.loadgetUserId()
      this.loadgetdep()
    },
    async loadgetUserId() {
      let aa = this.obj
      let getUserIddata = await getUserId(this.obj.id)
      getUserIddata = getUserIddata.data.roles
      for (let i = 0; i < getUserIddata.length; i++) {
        getUserIddata[i].label = getUserIddata[i].roleName
        getUserIddata[i].value = getUserIddata[i].id
      }
      debugger
      this.selected = getUserIddata

    },
    async loadgetdep() {
      let depData = await depgetAll()
      this.treeData = depData.data
    },
    async ondep1Change(val) {
      if (val) {
        val = val
      }
    },
    async addCreate(obj) {

      if (!this.validata.validausr(obj)) return
      obj.method = 'post'
      let arr = []
      let getAlldata = this.selected
      for (let i = 0; i < getAlldata.length; i++) {
        let obja = {}
        obja.id = getAlldata[i].value
        arr.push(obja)
      }
      obj.roles = arr
      let data = await addUser(obj)
      if (data.code === 10000) {
        this.$message({
          message: '添加成功',
          type: 'success'
        });
        this.loadPageList()
        this.dialogFormVisible = false
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
      }
    },
    async saveCreate(obj) {
      if (!this.validata.validausr(obj)) return
      obj.method = 'put'
      let arr = []
      let getAlldata = this.selected
      for (let i = 0; i < getAlldata.length; i++) {
        let obja = {}
        obja.id = getAlldata[i].value
        arr.push(obja)
      }
      obj.roles = arr
      let data = await saveUser(obj)
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
    //
    // async subSaveCreate() {
    //   let obj = this.obj
    //   obj.method = 'put'
    //   obj.dpartmentId = this.$refs.tree.getCheckedNodes()
    //
    //
    //   let data = await saveUser(obj)
    //   this.dialogEditVisible = false
    //   this.$message({
    //     type: 'success',
    //     message: '修改成功!'
    //   });
    // }
    async handleEdit(data, type) {

      // let del = await delUser(data.id)
      // this.list.splice(this.list.indexOf(data), 1)
      // this.$message({
      //   type: 'success',
      //   message: '删除成功!'
      // });
    },
    onDate1Change(val) {
      this.obj.loanDate = val
    },
    onDate2Change(val) {
      this.obj.appointmentRepaymentDate = val
    },
    onDate3Change(val) {
      this.obj.interestPayTime = val
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

.el-table__body-wrapper {
  overflow: auto;
  position: relative;
  height: 89%;
}

.el-dialog__header {
  border-bottom: 1px solid#d8d6d6;
  padding-bottom: 10px;
}

/*
.el-dialog__title {
  line-height: 1;
  font-size: 25px;
  font-weight: 700;
  text-align: center;
  color: #1f2d3d;
} */

.el-dialog__body {
  margin-top: 30px;
  padding: 0px 20px;
  color: #48576a;
  font-size: 14px;
}

.el-dialog__footer {
  text-align: center;
}

.aaa {
  width: 95%
}

.el-dialog__wrapper {
  overflow: none;
}
</style>
