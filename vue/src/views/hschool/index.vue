<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <el-button v-if="userType =='0'" style="margin-left: 10px;" @click="handleEdit" type="primary" icon="el-icon-edit">添加高校</el-button>
    </div>
  </div>


  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" align="center" label="ID">

    </el-table-column>
    <el-table-column align="center" label="高校院所名">
      <template slot-scope="scope">
                    <span>{{ scope.row.name }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="高校代码">
      <template slot-scope="scope">
                    <span>{{ scope.row.code }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="单位网址">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.unit_url}}</span>
                </template>
    </el-table-column>

    <el-table-column align="center" label="">
      <template slot-scope="scope">

                      <div style="margin:2% 2% 2% 2%">
                          <el-button size="small" @click="showDetail(scope.row,'edit')" type=""  class="el-icon-edit colorblue borderblue">查看详情</el-button>
                      </div>
                    </template>
    </el-table-column>
    <el-table-column v-if="userType =='0'" align="center" label="操作">
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


  <el-dialog title="高校院所详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
    <div class="textr paddinga">
      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handlePrint">打印</el-button>

      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
      <table id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>
        <tr height=27>
          <td colspan=4 id='tc0' height=27 class=x21>军民科技协同创新高校院所信息表</td>
        </tr>
        <tr height=19 id='r1'>
          <td height=19 class=x22 style='height:14.25pt;'>名称</td>
          <td colspan=3 id='tc1' class=x23 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'></td>
        </tr>
        <tr height=19 id='r2'>
          <td height=19 class=x24 style='height:14.25pt;overflow:hidden;'>统一社会信用代码</td>
          <td colspan=3 id='tc2' class=x23 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'></td>
        </tr>
        <tr height=19 id='r3'>
          <td height=19 class=x22 style='height:14.25pt;'>所在地区</td>
          <td colspan=3 id='tc3' class=x28 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;overflow:hidden;'><span style='mso-spacerun:yes'>&nbsp; </span>省<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>市<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>区（县）（下拉选择）</td>
        </tr>
        <tr height=19 id='r4'>
          <td height=19 class=x22 style='height:14.25pt;'>通信地址</td>
          <td class=x28></td>
          <td class=x29>邮编</td>
          <td class=x29></td>
        </tr>
        <tr height=19 id='r5'>
          <td height=19 class=x22 style='height:14.25pt;'>单位网址</td>
          <td colspan=3 id='tc4' class=x23 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'></td>
        </tr>
        <tr height=19 id='r6'>
          <td height=19 class=x22 style='height:14.25pt;'>单位简介</td>
          <td colspan=3 id='tc5' class=x23 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'></td>
        </tr>
        <tr height=19 id='r7'>
          <td height=19 class=x22 style='height:14.25pt;'>重大平台</td>
          <td colspan=3 id='tc6' class=x23 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;'></td>
        </tr>
        <tr height=0 style='display:none'>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
        </tr>
      </table>

    </div>

    <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogShowDep = false">关闭</el-button>
      </span>
  </el-dialog>


</div>
</template>

<script>
import {
  getUserDetail,
  setUserDetail
} from '@/api/login'
import {
  getcollege,
  addLib,
  delLib
} from '@/api/library'

import {
  getAllrole
} from '@/api/role'
import {
  depgetAll
} from '@/api/department'
import {
  pca,
  pcaa
} from "area-data";

import table2excel from 'table2excel'
import printArea from 'printArea'
export default {
  data() {
    return {
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

      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      school: {
        zhengben: [],
        fuben: [],
        logo: [],
        introduction: '',
        major_platform: '',
        unit_url: '',
        zip_code: '',
        address: '',
        country: '',
        code: '',
        name: '',
      },
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

    showDetail() {
      this.dialogShowDep = true
    },
    handlePrint() {
      $("#tablePrint").printArea();
    },
    handleDownload() { //导出
      $("#tableExcel").table2excel({
        exclude: ".noExl", //过滤位置的 css 类名
        filename: new Date().getTime() + ".xls", //文件名称
        name: "Excel Document Name.xlsx",
        exclude_img: true,
        exclude_links: true,
        exclude_inputs: true
      })
    },
    async loadPageList() {
      if (this.input) {
        this.listQuery.objName = this.input
      } else {
        this.listQuery.objName = ''
      }
      let {
        data,
        success
      } = await getcollege(this.listQuery)
      if (success) {
       this.list = data.list

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
      // if (!this.validata.validausr(obj)) return
      let arr ={}
      arr.formType = '4'
      arr.detail = JSON.stringify(obj)
      let {
        data,
        success
      } = await addLib(arr)
      if (success) {
        this.$message({
          message: '保存成功',
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

    async handleEdit(data, type) {
      if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          debugger
          let del = await delLib(data)
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
      } else if (type === 'edit') {
        this.$router.push({
          name: 'hschoolEdit',
          params: {
            objId :data.form.id,
            objData: data.form.detail
          }
        })

      } else {
        this.$router.push({
          name: 'hschoolEdit'
        })
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
