<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <!-- <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button> -->
      <el-button style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加</el-button>
      <el-button style="margin-left: 10px;" @click="handlePush" type="primary" icon="el-icon-upload2">推送</el-button>
      <el-button style="margin-left: 10px;" @click="handlePull" type="primary" icon="el-icon-refresh">同步</el-button>

    </div>
  </div>


  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" align="center" label="序号">

    </el-table-column>
    <el-table-column v-for='(key, value) in listtop' align="center" :label="key.ch">

      <template slot-scope="scope">
                                <span>{{ scope.row[key.en] }}</span>
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
          <span v-for="(key, value) in listtop">
        <el-form-item :label="key.ch">
          <el-input v-model="obj[key.en]" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item></span>

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
  getdefinedId,
  adddefinedId,
  editdefinedId,
  deldefinedId,
  loadPush,
  loadPull
} from '@/api/mdm'


export default {
  data() {
    return {
      input: '',
      bank: '1',
      list: [],
      listtop: [],
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
  watch: {
    '$route' (to, from) {
      this.loadPageList()
    }
  },
  methods: {

    async loadPageList() {
      let data = await getdefinedId(this.$route.query.id)
      this.listtop = data.data.definedColumn
      this.list = data.data.definedData
      this.loading = false
    },

    async handleCreate() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.dialogadd = true
      this.dialogsave = false
      this.obj = {
      }

    },

    async handlePush() {
        let data = await loadPush(this.$route.query.id)

        if (data.success) {
          this.$message({
            message: '推送成功',
            type: 'success'
          });
        } else {
          this.$message({
            message: '推送失败',
            type: 'error'
          });
        }
    },

    async handlePull() {
        let data = await loadPull(this.$route.query.id)
        if (data.success) {
          this.$message({
            message: '同步成功',
            type: 'success'
          });
        } else {
          this.$message({
            message: '同步失败',
            type: 'error'
          });
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

      // if (!this.validata.validaRole(obj)) return

      if (!this.validata.validamdm(obj)) return
      let data = await adddefinedId(obj,this.$route.query.id)
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

      let data = await editdefinedId(obj,this.$route.query.id)
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
          debugger
          let del = await deldefinedId(data.ID,this.$route.query.id)
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

.aaa {
  width: 95%
}

.el-dialog__wrapper {
  overflow: none;
}
</style>
