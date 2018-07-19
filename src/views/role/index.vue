<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <!-- <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button> -->
      <el-button style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加</el-button>

    </div>
  </div>


  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" align="center" label="序号">

    </el-table-column>
    <el-table-column align="center" label="名称">
      <template slot-scope="scope">
                    <span>{{ scope.row.roleName }}</span>
                </template>
    </el-table-column>
    <!-- <el-table-column align="center" label="类型">
      <template slot-scope="scope">
                    <span>{{ scope.row.roleType }}</span>
                </template>
    </el-table-column> -->
    <el-table-column align="center" label="描述">
      <template slot-scope="scope">
                    <span>{{ scope.row.roleDesc }}</span>
                </template>
    </el-table-column>

    <el-table-column align="center" label="菜单分配">
      <template slot-scope="scope">
                    <span>
                        <el-button size="small" @click="handleShow(scope.row,'menu')" type=""  class="colorblue borderblue">编辑</el-button></span></span>
                </template>
    </el-table-column>

    <!-- <el-table-column align="center" label="数据权限">
      <template slot-scope="scope">
                    <span>
                        <el-button size="small" @click="handleShow(scope.row,'data')" type=""  class="colorblue borderblue">查看</el-button></span></span>
                </template>
    </el-table-column> -->
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
                    <div style="margin:2% 2% 2% 2%">
                        <el-button size="small" @click="handleEdit(scope.row,'edit')" type=""  class="el-icon-edit colorblue borderblue"></el-button>
                        <el-button size="small" @click="handleEdit(scope.row,'del')"  type=""  class="el-icon-delete colorred borderred"></el-button>
                    </div>
                </template>
    </el-table-column>
  </el-table>


  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogShowMenu" width="30%" top='5%'>
    <el-tree :data="treeData" :default-checked-keys="checkedRole" show-checkbox default-expand-all node-key="id" ref="tree" highlight-current :props="defaultProps">
    </el-tree>

    <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="subSaveCreate">修改</el-button>
        <el-button type="primary" @click="dialogShowMenu = false">关闭</el-button>
      </span>
  </el-dialog>

  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogShowData" width="30%" top='5%'>

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
        <el-button type="primary" @click="dialogShowData = false">关闭</el-button>
      </span>
  </el-dialog>


  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">

        <el-form-item label="名称">
          <el-input v-model="obj.roleName" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>

        <!-- <el-form-item label="数据权限">
          <v-select multiple v-model="selected" :options="options" style="width:80%"></v-select>
        </el-form-item> -->
        <el-form-item label="描述">
          <el-input v-model="obj.roleDesc" placeholder="请输入内容" style="width:80%"></el-input>
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
  getAllrole,
  addRole,
  saveRole,
  delRole,
  dataPermissionAll,
  dataTreeAll,
  saveRoleMenus,
  getRoleId
} from '@/api/role'

import {
  saveEdit,
  loadmenu1
} from '@/api/menu'
export default {
  data() {
    return {
      input: '',
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      checkedRole: [],
      dialogFormVisible: false,
      dialogShowMenu: false,
      dialogShowData: false,
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
        menu: '菜单编辑',
        data: '数据权限',
        create: '添加'
      },
      obj: {
        roleName: '',
        roleDesc: ''
      },
      selected: [],
      options: [],
      treeTemp: '',
      treeData: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      loading: true
    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()
  },
  computed: {},
  methods: {
    async loadTree() {
      let data = await loadmenu1()

      this.treeData = data.data
      this.dialogFormVisible = false
    },
    // handleNodeClick(val) {
    //   this.dialogFormVisible = true
    //   this.treeTemp = val
    //
    // },
    async loadPageList() {
      let data = await getAllrole()
      this.list = data.data
      this.loading = false
    },
    async handleCreate() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.dialogadd = true
      this.dialogsave = false
      this.obj = {
        roleName: '',
        roleDesc: ''
      }

    },
    async loadgetRoleId() {
      let getRoleIddata = await getRoleId(this.obj.id)
      getRoleIddata = getRoleIddata.data.menus
      let arr = [];
      for (let i = 0; i < getRoleIddata.length; i++) {
        arr.push(getRoleIddata[i].id)
      }
      this.checkedRole = arr

    },
    async ondep1Change(val) {
      if (val) {
        val = val
      }
    },
    async addCreate(obj) {

      if (!this.validata.validaRole(obj)) return

      obj.method = 'post'
      obj.menus = null
      let data = await addRole(obj)
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
      if (!this.validata.validaRole(obj)) return

      let arr =[]
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

    async subSaveCreate() {
      let obj = this.obj
      obj.method = 'put'
      obj.menus = this.$refs.tree.getCheckedNodes()

      let data = await saveRoleMenus(obj)
      this.dialogEditVisible = false
      this.$message({
        type: 'success',
        message: '修改成功!'
      });
    },

    async handleShow(data, type) {
      if (type === 'menu') {
        this.obj = data
        this.dialogStatus = 'menu'
        this.dialogShowMenu = true
        this.loadTree()
        this.loadgetRoleId()

      } else if (type === 'data') {
        this.obj = data
        this.dialogStatus = 'data'
        this.dialogShowData = true

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
.el-table td {
    padding: 0px 0;
    min-width: 0;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    text-overflow: ellipsis;
    vertical-align: middle;
    position: relative;
}
.aaa {
  width: 95%
}

.el-dialog__wrapper {
  overflow: none;
}
</style>
