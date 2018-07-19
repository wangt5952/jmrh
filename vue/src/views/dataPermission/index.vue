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
    <el-table-column align="center" label="数据库表">
      <template slot-scope="scope">

                    <span>{{ scope.row.tableDefinition.tableName }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="字段">
      <template slot-scope="scope">
                    <span>{{ scope.row.permissionField }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="角色">
      <template slot-scope="scope">
                    <span v-for ="x in scope.row.dataPermission">
                      {{ x.role.roleName }}
                    </span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="值">
      <template slot-scope="scope">
                    <span v-for ="x in scope.row.dataPermission">
                      {{ x.permissionValue }}
                    </span>
                </template>
    </el-table-column>


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



  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">

        <el-form-item label="数据库表">

          <el-select v-model="tableName" placeholder="请选择" style="width:80%" @change='onDate1Change'>
            <el-option v-for="group in listdata" :key="group.id" :label="group.tableName" :value="group.id">
            </el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="字段">

          <el-select v-model="permissionField" placeholder="请选择" style="width:80%" :disabled='dispermissionValue'>
            <el-option v-for="group in options" :key="group.id" :label="group.label" :value="group.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="值">
          <el-input v-model="dataPermission" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>

        <el-form-item label="角色">

          <el-select v-model="permissionrole" placeholder="请选择" style="width:80%" >
            <el-option v-for="group in roleoptions" :key="group.id" :label="group.label" :value="group.value">
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
  pergetAll,
  addPermissione,
  savePermissione,
  deldataPermission,
} from '@/api/dataPermission'

import {
  getAll,
  getAllone
} from '@/api/modelDiy'

import {
  getAllrole
} from '@/api/role'
export default {
  data() {
    return {
      input: '',
      bank: '1',
      list: [],
      listdata: [],
      timeType: '1',
      tableName: '',
      permissionField: '',
      dataPermission : '',
      permissionrole : '',
      dispermissionValue: true,
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
      roleoptions :[],
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

    async loadPageList() {
      let data = await pergetAll()
      this.list = data.data
      this.loading = false
    },
    async handleCreate() {
      this.tableName = ''
      this.permissionField = ''
      this.permissionrole = ''
      this.dataPermission = ''
      this.dispermissionValue = false
      this.loadEditData()
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
    async addCreate() {
      // if (!this.validata.validaPermissione(obj)) return

      let obj = {}
      obj.permissionField = this.permissionField
      obj.tableId = this.tableName
      let dataPermissionobj ={}
      let arr = []
      dataPermissionobj.permissionValue = this.dataPermission
      dataPermissionobj.roleId = this.permissionrole
      arr.push(dataPermissionobj)
      obj.dataPermission = arr
      let data = await addPermissione(obj)
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
      // if (!this.validata.validaRole(obj)) return
      obj.permissionField = this.permissionField
      obj.tableId = this.tableName
      let dataPermissionobj ={}
      let arr = []
      dataPermissionobj.permissionValue = this.dataPermission
      dataPermissionobj.roleId = this.permissionrole
      arr.push(dataPermissionobj)
      obj.dataPermission = arr

      let data = await savePermissione(obj)

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
    async loadEditData() {
      let getAlldata = await getAll()
      this.listdata = getAlldata.data
        let getAllroleData = await getAllrole()
        let arr = []
        getAllroleData = getAllroleData.data
        for (let i = 0; i < getAllroleData.length; i++) {
          let obja = {}
          obja.label = getAllroleData[i].roleName
          obja.value = getAllroleData[i].id
          arr.push(obja)
        }
        this.roleoptions = arr
    },
    async handleEdit(data, type) {
      if (type === 'edit') {
        this.loadEditData()
        debugger
         this.onDate1Change(data.tableId)
        this.obj = data
        let dataPermissiontemp = data.dataPermission
        for (let i = 0; i < dataPermissiontemp.length; i++) {
          dataPermissiontemp[i].label = dataPermissiontemp[i].permissionValue
          dataPermissiontemp[i].value = dataPermissiontemp[i].id
        }
        this.permissionValue = dataPermissiontemp
        this.permissionField = data.permissionField
        this.tableName   = data.tableDefinition.id
        this.permissionrole   = data. dataPermission[0].roleId
        this.dataPermission   = data. dataPermission[0].permissionValue
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
          let del = await deldataPermission(data.id)
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
    async onDate1Change(tableId) {
      this.dispermissionValue = false
      if(tableId){
        this.tableName = tableId
      }
      let getone = await getAllone(this.tableName)
      let getoneIddata = getone.data.table[0].columnDefinitions
      for (let i = 0; i < getoneIddata.length; i++) {
        getoneIddata[i].label = getoneIddata[i].name
        getoneIddata[i].value = getoneIddata[i].name
      }
      this.options = getoneIddata
    },
  }
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>

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
