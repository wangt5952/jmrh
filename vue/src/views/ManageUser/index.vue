<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input.objName" placeholder="请输入ID/管理员/邮箱" style="width: 11%;"></el-input>
      <el-select v-model="input.userType" style="width:120px" placeholder="用户类型">
        <el-option label="专家" key="1" value="1">
        </el-option>
        <el-option label="企业" key="2" value="2">
        </el-option>
        <el-option label="服务机构" key="3" value="3">
        </el-option>
        <el-option label="高校院所" key="4" value="4">
        </el-option>
      </el-select>
      <el-select v-model="input.status" style="width:100px" placeholder="状态">
        <el-option label="开启" key="1" value="1">
        </el-option>
        <el-option label="禁用" key="0" value="0">
        </el-option>
      </el-select>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <el-button v-if="userType =='0'" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加管理员</el-button>

    </div>
  </div>


  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;">
    <!-- <el-table-column type="index" align="center" label="ID">

    </el-table-column> -->
    <el-table-column align="center" label="管理员名称">
      <template slot-scope="scope">
                    <span>{{ scope.row.userName }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="邮箱">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.email}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="手机号">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.cellphone}}</span>
                </template>
    </el-table-column>

    <!-- <el-table-column align="center" label="所属组织机构">
      <template slot-scope="scope">
                    <span> -->
    <!-- {{ scope.row.org.label }} -->
    <!-- {{ scope.row.org.label }} - {{ scope.row.org.remark }} - {{ scope.row.org.children[0].label }}  - {{ scope.row.org.children[0].remark }}
                        </span>
                </template>
    </el-table-column> -->
    <el-table-column align="center" label="所属角色">
      <template slot-scope="scope">
                        <span v-for="item in  scope.row.roles">
                            {{item.roleName}},</span>
                    </template>
    </el-table-column>
    <el-table-column align="center" label="用户状态">
      <template slot-scope="scope">
            <span v-if="scope.row.status =='1'">开启</span>
            <span v-if="scope.row.status =='0'">禁用</span>
                        </template>
    </el-table-column>
    <el-table-column v-if="userType =='0'" align="center" label="">
      <template slot-scope="scope">
                        <div style="margin:2% 2% 2% 2%">
                            <el-button size="small" v-if="scope.row.status =='0'" @click="handleEditjy(scope.row,'1')" type="" style="border-radius: 5px;">开启</el-button>
                            <el-button size="small" v-if="scope.row.status =='1'" @click="handleEditjy(scope.row,'0')" type="" style="background: #f44;color: #fff;border-radius: 5px;">禁用</el-button>
                        </div>
                    </template>
    </el-table-column>

    <el-table-column v-if="userType =='0'" align="center" label="操作">
      <template slot-scope="scope">
                    <div style="margin:2% 2% 2% 2%">
                        <el-button size="small" @click="handleEdit(scope.row,'edit')" type=""  class="el-icon-edit colorblue borderblue"></el-button>
                        <!-- <el-button size="small" @click="handleEdit(scope.row,'del')"  type=""  class="el-icon-delete colorred borderred"></el-button> -->
                    </div>
                </template>
    </el-table-column>

  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>

  <!-- <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogShowDep" width="30%" top='5%'>
    <el-tree :data="treeData" show-checkbox default-expand-all node-key="id" ref="tree" highlight-current :props="defaultProps">
    </el-tree>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dialogShowDep = false">关闭</el-button>
    </span>
  </el-dialog> -->

  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogShowRole" width="30%" top='5%'>

    <el-table class="tableH" :data="selected" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
      <el-table-column type="index" align="center" label="序号">

      </el-table-column>
      <el-table-column align="center" label="角色名">
        <template slot-scope="scope">
                  <span>{{ scope.row.roleName }}</span>
              </template>
      </el-table-column>
      <el-table-column align="center" label="角色描述">
        <template slot-scope="scope">
                  <span>{{ scope.row.roleDesc }}</span>
              </template>
      </el-table-column>


    </el-table>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dialogShowRole = false">关闭</el-button>
    </span>
  </el-dialog>

  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">

        <el-form-item label="用户名">
          <el-input v-model="obj.userName" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <!-- <el-form-item label="全名">
          <el-input v-model="obj.fullName" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item> -->
        <el-form-item label="手机号">
          <el-input v-model="obj.cellphone" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="obj.email" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="obj.password" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <v-select multiple v-model="selected" :options="options" style="width:80%"></v-select>
        </el-form-item>
        <el-form-item label="组织架构">
          <el-select v-model="orgId" placeholder="请选择" style="width:80%">
            <el-option v-for="item in treeData" :key="item.id" :label="item.label" :value="item.id">
            </el-option>
          </el-select>
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
  addUser,
  saveUser,
  delUser,
  setUserStatus
} from '@/api/manageUser'

import {
  getAllrole
} from '@/api/role'
import {
  getOrgMenus
} from '@/api/org'
export default {
  data() {
    return {
      userType: '',
      input: {
        objName: '',
        userType: '',
        status: '',
      },
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      dialogFormVisible: false,
      dialogShowRole: false,
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
        password: '',
        email: '',
        cellphone: '',
      },
      orgId: '',
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
      loading: true
    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()
    this.userType = window.sessionStorage.getItem('userType') //权限控制
  },
  computed: {},
  methods: {
    async loadPageList() {
      if (this.input) {
        this.listQuery.objName = this.input.objName
        this.listQuery.userType = this.input.userType
        this.listQuery.status = this.input.status
      } else {
        this.listQuery.objName = ''
      }
      let {
        data,
        success
      } = await getUser(this.listQuery)
      if (success) {
        this.list = data.list
        this.total = data.total
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
        password: '',
        email: '',
        cellphone: '',
      }
      this.selected = []
      this.orgId = ''
      this.loadoptions()
      this.loadgetdep()
    },
    async loadoptions() {
      let obj = {}
      obj.page = 1
      obj.limit = 100
      let {
        data
      } = await getAllrole(obj)
      let arr = []
      let getAlldata = data.list

      for (let i = 0; i < getAlldata.length; i++) {
        let obja = {}
        if (getAlldata[i].roleType != 2) {
          obja.label = getAlldata[i].roleDesc
          obja.value = getAlldata[i].id
          arr.push(obja)
        }
      }
      this.options = arr
      // this.loadgetUserId()
    },
    // async loadgetUserId() {
    //   let aa = this.obj
    //   let getUserIddata = await getUserId(this.obj.id)
    //   getUserIddata = getUserIddata.data.roles
    //   for (let i = 0; i < getUserIddata.length; i++) {
    //     getUserIddata[i].label = getUserIddata[i].roleName
    //     getUserIddata[i].value = getUserIddata[i].id
    //   }
    //   debugger
    //   this.selected = getUserIddata
    //
    // },
    async loadgetdep() {
      let {
        data,
        success
      } = await getOrgMenus()
      if (success) {
        let objD = data
        var arr = []
        let obj = {}
        for (var i in objD) {
          obj.id = objD[i].id
          obj.label = '省级-' + objD[i].label
          let onechild = objD[i].children
          for (var j in onechild) {
            let obj2 = {}
            obj2.id = onechild[j].id
            obj2.label = '市级--' + onechild[j].label
            arr.push(obj2)
            let twochild = onechild[j].children
            for (var w in twochild) {
              let obj3 = {}
              obj3.id = twochild[w].id
              obj3.label = '区级---' + twochild[w].label
              arr.push(obj3)
            }
          }
          arr.push(obj)
        }
        this.treeData = arr
      }
    },
    async ondep1Change(val) {
      if (val) {
        val = val
      }
    },
    async addCreate(obj) {
      obj.selected = this.selected
      obj.orgId = this.orgId
      if (!this.validata.validaManageUser(obj)) return
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
      obj.selected = this.selected
      obj.orgId = this.orgId
      if (!this.validata.validaManageUser(obj)) return
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
        this.loadPageList()
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
    async handleShow(data, type) {
      if (type === 'role') {
        this.obj = data
        this.dialogStatus = 'role'
        this.dialogShowRole = true
        this.loadgetUserId()

      }
    },
    async handleEditjy(obj, num) {
      let arr = {}
      arr.status = num
      arr.id = obj.id
      let {
        data,
        success,
        message
      } = await setUserStatus(arr)
      if (success) {
        this.$message({
          message: '操作成功',
          type: 'success'
        });
        this.loadPageList()
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
        let arr = []
        let getAlldata = data.roles

        for (let i = 0; i < getAlldata.length; i++) {
          let obja = {}
          obja.label = getAlldata[i].roleDesc
          obja.value = getAlldata[i].id
          arr.push(obja)
        }
        this.selected = arr
        if (data.org && data.org.id) {
          this.orgId = data.org.id
        }
        this.dialogStatus = 'update'
        this.dialogsave = true
        this.dialogadd = false
        this.dialogFormVisible = true
        this.loadoptions()
        this.loadgetdep()
      } else if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await delUser(data.id)
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
