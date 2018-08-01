<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <el-button style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加文章</el-button>

    </div>
  </div>


  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" align="center" label="ID">

    </el-table-column>
    <el-table-column align="center" label="标题">
      <template slot-scope="scope">
                    <span>{{ scope.row.fullName }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="发布">
      <template slot-scope="scope">
                    <span>{{ scope.row.fullName }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="分类">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.dpartmentId}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="模型">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.dpartmentId}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="分数">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.dpartmentId}}</span>
                </template>
    </el-table-column>

    <el-table-column align="center" label="点评数">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.dpartmentId}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="点击数">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.dpartmentId}}</span>
                    </template>
    </el-table-column>
    <el-table-column align="center" label="发布日期">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.dpartmentId}}</span>
                    </template>
    </el-table-column>
    <el-table-column align="center" label="创建日期">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.dpartmentId}}</span>
                    </template>
    </el-table-column>
    <el-table-column align="center" label="置顶级别">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.dpartmentId}}</span>
                    </template>
    </el-table-column>
    <el-table-column align="center" label="状态">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.dpartmentId}}</span>
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

  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="60%" top='5%'>


            <el-form class="" label-width="30%" style="text-align:left">
              <el-row :gutter="22">
                <el-col :span="22">
                  <el-form-item label="分类">
                    <el-select  style="width:100px" placeholder="请选择">
                      <el-option label="1" key="1" value='1'>
                      </el-option>
                      <el-option label="2" key="0" value='0'>
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="标题">
                    <el-input  placeholder="请输入标题" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="作者">
                    <el-input  placeholder="请输入作者" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="标签">
                    <el-input  placeholder="请输入标签" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="描述">
                    <el-input placeholder="请输入联系地址"  style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="定时发布">
                    <el-checkbox v-model="send"></el-checkbox>
                  </el-form-item>
                  <el-form-item label="封面">
                    <el-input placeholder="请输入封面"  style="width:80%"></el-input>
                  </el-form-item>

                  <el-form-item label="正文">
                  <div class="editor-container">
                    <UE :defaultMsg=defaultMsg :config=config :id=ue1 ref="ue"></UE>
                  </div>
                  <button @click="getUEContentTxt()">获取无文本内容</button>
                </el-form-item>

                </el-col>

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


import UE from '@/components/ue/ue.vue';
export default {
  components: {
    UE
  },
  data() {
    return {
      send:true,
      defaultMsg: '<span style="orphans: 2; widows: 2; font-size: 22px; font-family: KaiTi_GB2312; background-color: rgb(229, 51, 51);"><strong>测试内容</strong></span>',
      config: {
        initialFrameWidth: null,
        initialFrameHeight: 350
      },
      ue1: "ue1", // 不同编辑器必须不同的id
      input: '',
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
      loading: true
    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()

  },
  computed: {},
  methods: {

    getUEContentTxt() {
      let content = this.$refs.ue.getUEContentTxt(); // 调用子组件方法
      // this.$notify({
      //   title: '获取成功，可在控制台查看！',
      //   message: content,
      //   type: 'success'
      // });
    },
    async loadPageList() {
      if (this.input) {
        this.listQuery.objName = this.input
      } else {
        this.listQuery.objName = ''
      }
      let data = await getUser(this.listQuery)
      this.list = data.data.rows
      this.loading = false
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
    async handleShow(data, type) {
      if (type === 'role') {
        this.obj = data
        this.dialogStatus = 'role'
        this.dialogShowRole = true
        this.loadgetUserId()

      }
    },
    async handleEdit(data, type) {


      if (type === 'edit') {
        this.obj = data
        this.selected = data.roles
        this.dialogStatus = 'update'
        this.dialogsave = true
        this.dialogadd = false
        this.dialogFormVisible = true
        this.loadoptions()
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

<style>
.info {
  border-radius: 10px;
  line-height: 20px;
  padding: 10px;
  margin: 10px;
  background-color: #ffffff;
}

</style>
