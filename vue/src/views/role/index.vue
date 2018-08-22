<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input.objName" placeholder="请输入内容" style="width: 15%;"></el-input>
      <!-- <el-select v-model="input.userType" style="width:100px" placeholder="用户类型选择">
        <el-option label="专家" key="1" value="1">
        </el-option>
        <el-option label="企业" key="2" value="2">
        </el-option>
        <el-option label="服务机构" key="3" value="3">
        </el-option>
        <el-option label="高校院所" key="4" value="4">
        </el-option>
      </el-select>
      <el-select v-model="input.status" style="width:100px" placeholder="状态选择">
        <el-option label="开启" key="1" value="1">
        </el-option>
        <el-option label="禁用" key="0" value="0">
        </el-option>
      </el-select> -->
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <el-button style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加角色</el-button>

    </div>
  </div>


  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" align="center" label="ID">

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
        <el-button type="primary" @click="subSaveCreate">保存</el-button>
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
        <el-form-item label="描述">
          <el-input v-model="obj.roleDesc" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item label="类型">

          <el-select v-model="obj.roleType" style="width:100px" placeholder="请选择">
            <el-option label="管理员" :key=1 :value=1>
            </el-option>
            <el-option label="用户" :key=2 :value=2>
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

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>


</div>
</template>

<script>
import {
  getAllrole,
  addRole,
  delRole,
  saveRoleMenus,
  dataTreeAll,
  getRoleId
} from '@/api/role'

import {
  getUserMenus,
  saveEdit,
} from '@/api/menu'

export default {
  data() {
    return {
      input: {
        objName: '',
        userType: '',
        status: '',
      },
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
        roleDesc: '',
        roleType: ''
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
      let data = await getUserMenus()

      this.treeData = data.data
      this.dialogFormVisible = false
    },
    // handleNodeClick(val) {
    //   this.dialogFormVisible = true
    //   this.treeTemp = val
    //
    // },
    async loadPageList() {
      if (this.input) {
        this.listQuery.objName = this.input.objName
        this.listQuery.userType = this.input.userType
        this.listQuery.status = this.input.status
      } else {
        this.listQuery.objName = ''
      }
      let {data} = await getAllrole(this.listQuery)
      this.list = data.list
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
    async loadgetRoleId(data) {
      let getRoleIddata = data
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

      // if (!this.validata.validaRole(obj)) return

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
      // if (!this.validata.validaRole(obj)) return
      let arr =[]
      obj.method = 'put'
      let data = await addRole(obj)

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

    async subSaveCreate() {//菜单保存
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
        this.loadgetRoleId(data.menus)

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
