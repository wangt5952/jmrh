<template>
<div class="tab-container">
  <!-- <el-input placeholder="Filter keyword" v-model="filterText" style="margin-bottom:30px;"></el-input> -->
  <!-- <el-button style="margin-left: 10px;" @click="addCreatefsub" type="primary" icon="el-icon-edit">添加组织</el-button> -->

  <el-tree v-loading="loading" style=" padding: 50px;" class="filter-tree" :data="treeData" :props="defaultProps" @node-click="handleNodeClick" :expand-on-click-node="false" default-expand-all ref="tree2"></el-tree>


  <el-dialog title="添加" :visible.sync="dialogEditVisibleF" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">

        <el-form-item label="名称">
          <el-input v-model="obj.label" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>

        <el-form-item label="备注">
          <el-input v-model="obj.remark" placeholder="请输入内容" style="width:80%"></el-input>
        </el-form-item>
      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary"  @click="subAddCreate(obj)">添加</el-button>
      <el-button type="primary" @click="dialogEditVisibleF = false">关闭</el-button>
    </span>
  </el-dialog>


  <el-dialog :visible.sync="dialogFormVisible" width="30%" top='15%' :show-close="false" style="border-bottom: 0px">
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogEditVisible" append-to-body width="30%" top='5%'>

      <el-form class="" label-width="30%" style="text-align:left">

        <el-row :gutter="24">

          <el-form-item label="名称">
            <el-input v-if='obj.label == "主数据管理"' :disabled="true" v-model="obj.label" placeholder="请输入内容" style="width:80%"></el-input>
            <el-input v-if='obj.label != "主数据管理"' v-model="obj.label" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>

          <el-form-item label="备注">
            <el-input v-model="obj.remark" placeholder="请输入内容" style="width:80%"></el-input>
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
      <el-button type="primary" @click="addCreatesub()">添加</el-button>
      <el-button type="primary"   @click="saveCreatesub()">编辑</el-button>
      <el-button type="danger" @click="delCreat(obj)">删除</el-button>
    </span>
  </el-dialog>

</div>
</template>

<script>
import {
  getOrgMenus,
  addCreate,
  saveEdit,
  delOrg
} from '@/api/org'

export default {
  data() {
    return {
      textMap: {
        update: '编辑',
        create: '添加'
      },
      dialogStatus: '',
      filterText: '',
      dialogFormVisible: false,
      dialogEditVisible: false,
      dialogEditVisibleF: false,
      obj: {
        label: '',
        menuUrl: '',
        level: '',
        sortOrder: '',
        remark: '',
        icon: ''
      },
      treeTemp: '',
      treeData: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      loading: true
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },
  mounted() {
    this.loadTree()
  },

  methods: {
    async loadTree() {
      let {data,success} = await getOrgMenus()
      if(success){
        this.treeData = data
        this.dialogFormVisible = false
        this.loading = false
      }
    },
    handleNodeClick(val) {
      this.dialogFormVisible = true
      this.treeTemp = val

    },
    addCreatesub() { //添加其他级菜单
      this.dialogStatus = 'create'
      this.dialogEditVisible = true
      this.obj = {
        label: '',
        menuUrl: '',
        level: '',
        sortOrder: '',
        remark: '',
        icon: ''
      }
    },
    addCreatefsub() { //添加一级菜单
      this.dialogEditVisibleF = true
      this.obj = {
        label: '',
        menuUrl: '',
        level: '',
        sortOrder: '',
        remark: '',
        icon: ''
      }
    },
    saveCreatesub() {
      if (this.treeTemp.level != 1000) {
        this.treeTemp.level = '0'
      } else {
        this.treeTemp.level = '1000'
      }
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
        let del = await delOrg(this.treeTemp.id)
        this.loadTree()
        this.$message({
          type: 'success',
          message: '删除成功!'
        });

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    async subAddCreate() {
      // if (!this.validata.validaTree(this.obj)) return
      let obj = this.obj
      if (this.treeTemp.id) {
        obj.parentId = this.treeTemp.id
      }
      obj.method = 'post'
      let data = await addCreate(obj)
      debugger
      if (this.treeTemp.id) {
        this.treeTemp.children.push(this.obj)
        this.dialogEditVisible = false
      } else {
        this.treeData.push(this.obj)
        this.dialogEditVisibleF = false
      }
      this.loadTree()
      this.$message({
        type: 'success',
        message: '添加成功!'
      });

    },
    async subSaveCreate() {
      // if (!this.validata.validaTree(this.obj)) return
      debugger
      let obj = this.obj
      obj.method = 'put'
      let data = await saveEdit(obj)
      this.dialogEditVisible = false
      this.$message({
        type: 'success',
        message: '修改成功!'
      });
    }
  },

}
</script>

<style>
.el-dialog__header {
  border-bottom: 0;
  padding-bottom: 10px;
}

.tab-container {
  padding: 30px;
  overflow-y: auto;
}


.el-table__body-wrapper {
  overflow: auto;
  position: relative;
  height: 89%;
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
  margin-top: 10px;
  padding: 0px;
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
