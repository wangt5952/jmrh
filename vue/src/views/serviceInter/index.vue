<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <el-button style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加服务</el-button>

    </div>
  </div>


  <el-table class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" align="center" label="序号">

    </el-table-column>
    <el-table-column align="center" label="类型">
      <template slot-scope="scope">
                      <el-select disabled  v-model="scope.row.type" placeholder="请选择" >
                        <el-option  label="webservicee" value="0">
                        </el-option>
                          <el-option  label="存储过程" value="1">
                        </el-option>
                      </el-select>
                </template>
    </el-table-column>
    <el-table-column align="center" label="WSDL地址">
      <template slot-scope="scope">
                    <span>{{ scope.row.wsdlLocation }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="数据库地址">
      <template slot-scope="scope">
                    <span>{{ scope.row.dburl }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="描述">
      <template slot-scope="scope">
                    <span>{{ scope.row.remark }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="系统模块">
      <template slot-scope="scope">
                    <span>
                      <el-select disabled  v-model="scope.row.bsId" placeholder="请选择" >
                        <el-option v-for="item in serData" :key="item.id" :label="item.bsName" :value="item.id">
                        </el-option>
                      </el-select>
                    </span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="业务模块">
      <template slot-scope="scope">
                    <span>
                      <el-select disabled  v-model="scope.row.modelId" placeholder="请选择" >
                        <el-option v-for="item in sysData" :key="item.id" :label="item.mdmModel" :value="item.id">
                        </el-option>
                      </el-select>
                    </span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="操作员">
      <template slot-scope="scope">
                    <span>{{ scope.row.operation }}</span>
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


  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="50%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">
        <el-col :span="12">
          <el-form-item label="类型">
            <el-select v-model="obj.type" placeholder="请选择">
              <el-option label="webservicee" value="0">
              </el-option>
              <el-option label="存储过程" value="1">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="WSDL地址">
            <el-input v-model="obj.wsdlLocation" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>

          <el-form-item label="数据库地址">
            <el-input v-model="obj.dburl" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>

          <el-form-item label="描述">
            <el-input v-model="obj.remark" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="用户">
            <el-input v-model="obj.username" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="obj.password" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="系统模块">
            <el-select v-model="obj.bsId" placeholder="请选择">
              <el-option v-for="item in serData" :key="item.id" :label="item.bsName" :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>

        </el-col>


        <el-col :span="12">
          <el-form-item label="业务模块">
            <el-select v-model="obj.modelId" placeholder="请选择">
              <el-option v-for="item in sysData" :key="item.id" :label="item.mdmModel" :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="操作类型">
            <el-select v-model="obj.operationType" placeholder="请选择">
              <el-option label="拉取" value="0">
              </el-option>
              <el-option label="推送" value="1">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="操作员">
            <el-input v-model="obj.operation" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>

          <el-form-item label="wsbinding">
            <el-input v-model="obj.wsbinding" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>

          <el-form-item label="bingNamespace">
            <el-input v-model="obj.bingNamespace" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>

          <el-form-item label="operationNamespace">
            <el-input v-model="obj.operationNamespace" placeholder="请输入内容" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="是否数据源">
            <el-select v-model="obj.type" placeholder="请选择">
              <el-option label="否" value="0">
              </el-option>
              <el-option label="是" value="1">
              </el-option>
            </el-select>
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
  getServiceInterface,
  addserviceInterface,
  editserviceInterface
} from '@/api/serviceInter'
import {
  modelAll
} from '@/api/modelDiy'
import {
  getAllBs
} from '@/api/service'
export default {
  data() {
    return {
      input: '',
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      dialogFormVisible: false,
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
        create: '添加'
      },
      obj: {},
      sysData: [],
      serData: [],
    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()
    this.loadSys()
  },
  computed: {},
  methods: {

    async loadSys() {
      let sysData = await modelAll()
      this.sysData = sysData.data
      let serData = await getAllBs()
      this.serData = serData.data

    },
    async loadPageList() {
      if (this.input) {
        this.listQuery.objName = this.input
      } else {
        this.listQuery.objName = ''
      }
      let data = await getServiceInterface(this.listQuery)
      debugger
      this.list = data.data.rows
    },
    handleSizeChange(val) {
      if (!isNaN(val)) {
        debugger
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
      this.obj = {}

    },
    async ondep1Change(val) {
      if (val) {
        val = val
      }
    },
    async addCreate(obj) {
      if (!this.validata.validaserviceInter(obj)) return
      obj.method = 'post'
      let data = await addserviceInterface(obj)
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

      if (!this.validata.validaserviceInter(obj)) return
      obj.method = 'put'
      let data = await editserviceInterface(obj)
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
