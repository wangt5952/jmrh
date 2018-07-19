<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <!-- <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button> -->
      <el-button style="margin-left: 10px;" @click="addCreatefsub" type="primary" icon="el-icon-edit">添加部门</el-button>

    </div>
  </div>


  <el-tree style=" padding: 50px;" class="filter-tree" :data="treeData" :props="defaultProps" @node-click="handleNodeClick" :expand-on-click-node="false" default-expand-all ref="tree2"></el-tree>



  <el-dialog title="添加" :visible.sync="dialogEditVisibleF" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">
        <el-form-item label="名称">
          <el-input v-model="obj.label" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>

        <el-form-item label="编号">
          <el-input v-model="obj.departCode" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary"  @click="subAddCreate(obj)">添加</el-button>
      <el-button type="primary" @click="dialogEditVisibleF = false">关闭</el-button>
    </span>
  </el-dialog>

  <el-dialog :visible.sync="dialogFormVisible" width="30%" top='15%' :show-close="false">
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogEditVisible" append-to-body width="30%" top='5%'>

      <el-form class="" label-width="30%" style="text-align:left">

        <el-row :gutter="24">

          <el-form-item label="名称">
            <el-input v-model="obj.label" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>

          <el-form-item label="编号">
            <el-input v-model="obj.departCode" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>
        </el-row>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" v-if="dialogStatus == 'create'" @click="subAddCreate(obj)">添加</el-button>
        <el-button type="primary" v-if="dialogStatus == 'update'"  @click="subSaveCreate(obj)">修改</el-button>
        <el-button type="primary" @click="dialogEditVisible = false">关闭</el-button>
      </span>
    </el-dialog>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="addCreate(obj)">添加</el-button>
      <el-button type="primary"   @click="saveCreate(obj)">编辑</el-button>
      <el-button type="danger" @click="delCreat(obj)">删除</el-button>
    </span>
  </el-dialog>


</div>
</template>

<script>
import {
  depgetAll,
  addDepartment,
  saveDepartment,
  delDepartment
} from '@/api/department'
export default {
  data() {
    return {
      textMap: {
        update: '编辑部门',
        create: '添加部门'
      },
      dialogStatus: '',
      filterText: '',
      dialogFormVisible: false,
      dialogEditVisible: false,
      dialogEditVisibleF: false,
      obj: {
        label: '',
        departCode: ''
      },
      treeTemp: '',
      treeData: [{
        id: 1,
        label: 'Level one 1',
        children: [{
          id: 4,
          label: 'Level two 1-1',
          children: [{
            id: 9,
            label: 'Level three 1-1-1'
          }, {
            id: 10,
            label: 'Level three 1-1-2'
          }]
        }]
      }, {
        id: 2,
        label: 'Level one 2',
        children: [{
          id: 5,
          label: 'Level two 2-1'
        }, {
          id: 6,
          label: 'Level two 2-2'
        }]
      }, {
        id: 3,
        label: 'Level one 3',
        children: [{
          id: 7,
          label: 'Level two 3-1'
        }, {
          id: 8,
          label: 'Level two 3-2'
        }]
      }],
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()

  },
  computed: {},
  methods: {
    async loadPageList() {
      let data = await depgetAll()
      this.treeData = data.data
      this.dialogFormVisible = false
    },
    handleNodeClick(val) {
      this.dialogFormVisible = true
      this.treeTemp = val

    },
    addCreate() {
      this.dialogStatus = 'create'
      this.dialogEditVisible = true
      this.obj = {
        label: '',
        departCode: ''
      }
    },

    addCreatefsub() { //添加一级菜单
      this.dialogEditVisibleF = true
      this.obj = {
        label: '',
        departCode: ''
      }
    },
    saveCreate() {
      this.obj = this.treeTemp
      this.dialogStatus = 'update'
      this.dialogEditVisible = true
    },
    delCreat() {
      this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        let del = await delDepartment(this.treeTemp.id)
        if (del.success) {
          this.loadPageList()
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    async subAddCreate() {
      let obj = this.obj
      if (!this.validata.validadep(obj)) return
      if (this.treeTemp.id) {
        obj.parentId = this.treeTemp.id
      }
      obj.method = 'post'
      let data = await addDepartment(obj)

      this.dialogEditVisible = false
      this.dialogEditVisibleF = false

      this.loadPageList()
      this.$message({
        type: 'success',
        message: '添加成功!'
      });

    },

    async subSaveCreate() {
      let obj = this.obj
      if (!this.validata.validadep(obj)) return
      obj.method = 'put'
      let data = await saveDepartment(obj)
      this.dialogEditVisible = false
      this.$message({
        type: 'success',
        message: '修改成功!'
      });
    }

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
