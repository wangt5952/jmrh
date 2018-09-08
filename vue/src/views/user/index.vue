<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input.objName" placeholder="请输入名称/昵称/手机号" style="width: 12%;"></el-input>
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
      <!-- <el-button style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加用户</el-button> -->

    </div>
  </div>

  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;">
    <el-table-column type="index" width="50" align="center" label="ID">
    </el-table-column>
    <el-table-column align="center" label="名称(单位)">
      <template slot-scope="scope">
                    <span  @click="handleShow(scope.row,'show')" class="clickText">{{ scope.row.name }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="昵称">
      <template slot-scope="scope">
                    <span>{{ scope.row.userName }}</span>
                </template>
    </el-table-column>
    <!-- <el-table-column align="center" label="手机号">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.cellphone}}</span>
                </template>
    </el-table-column> -->

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
    <el-table-column align="center" label="身份证号/社会统一信用代码">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.code}}</span>
                </template>
    </el-table-column>


    <el-table-column align="center" label="用户状态">
      <template slot-scope="scope">
        <span v-if="scope.row.status =='1'">开启</span>
        <span v-if="scope.row.status =='0'">禁用</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="userType =='0'" align="center" label="操作">
      <template slot-scope="scope">
                    <div style="margin:2% 2% 2% 2%">
                        <el-button size="small" v-if="scope.row.status =='0'" @click="handleEdit(scope.row,'1')" type="" style="border-radius: 5px;">开启</el-button>
                        <el-button size="small" v-if="scope.row.status =='1'" @click="handleEdit(scope.row,'0')" type="" style="background: #f44;color: #fff;border-radius: 5px;">禁用</el-button>
                    </div>
                </template>
    </el-table-column>

  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>




  <el-dialog title="注册用户详情" :visible.sync="dialogFormVisible" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="24">
        <el-col :span="24">

          <!-- <table class="showDetailTable" v-show="show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;">
            <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">name</td>
              <td>
                {{content.title}}
              </td>
              <td style="width:100px;padding:10px">用户类别</td>
              <td>
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
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">封面</td>
              <td>
                <img :src="content.cover" alt="" style="width:100px">
              </td>
              <td style="width:100px;padding:10px">内容类型</td>
              <td>

                <span v-show="content.typeId == 1" style="">文章</span>
                <span v-show="content.typeId == 2" style="">轮播</span>
              </td>
            </tr>
            <tr v-show="content.typeId == 1">
              <td style="width:100px;padding:10px">是否转载</td>
              <td>
                <span v-show="content.copied == 1">转载</span>
                <span v-show="content.copied == 0">非转载</span>
              </td>
            </tr>
            <tr v-show="content.copied == 1 && content.typeId == 1" style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">来源</td>
              <td>
                {{content.copyFrom}}
              </td>
              <td style="width:100px;padding:10px">来源网址</td>
              <td>
                {{content.copyFromUrl}}
              </td>
            </tr>
            <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">作者</td>
              <td>
                {{content.author}}
              </td>
              <td style="width:100px;padding:10px">编辑</td>
              <td>
                {{content.editor}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td v-show="content.typeId == 1" style="width:100px;padding:10px">标签</td>
              <td v-show="content.typeId == 1">
                {{content.tags}}
              </td>
              <td style="width:100px;padding:10px">描述</td>
              <td>
                {{content.description}}
              </td>
            </tr>
            <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">定时发布</td>
              <td>
                <span v-show="content.publishNow == 1">是</span>
                <span v-show="content.publishNow == 0">否</span>
              </td>
              <td v-show="content.publishNow == 1" style="width:100px;padding:10px">发布日期</td>
              <td v-show="content.publishNow == 1">
                {{content.publishDate | formatTime}}
              </td>
            </tr>
            <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">置顶</td>
              <td>
                <span v-show="content.stickSort == 0">是</span>
                <span v-show="content.stickSort == 9999">否</span>
              </td>
              <td style="width:100px;padding:10px">推荐</td>
              <td>
                <span v-show="content.recommend == 1">是</span>
                <span v-show="content.recommend == 0">否</span>
              </td>
            </tr>
          </table> -->

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
  setStatus,
  getUserDetailByUserId,
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
      userType: '',
    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()
    this.userType = window.sessionStorage.getItem('userType')

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
    async handleEdit(obj, num) {
      let arr = {}
      arr.status = num
      arr.id = obj.id
      let {
        data,
        success,
        message
      } = await setStatus(arr)
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

    async handleShow(item, type) {
        let {
          data,
          success
        } = await getUserDetailByUserId(item.id)
        this.content = data
        this.dialogFormVisible = true
        this.show = true
        this.title = '查看内容详情'
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
