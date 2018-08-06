<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <el-button v-if="userType =='0'" style="margin-left: 10px;" @click="handleEdit" type="primary" icon="el-icon-edit">添加服务机构</el-button>

    </div>
  </div>


  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" align="center" label="ID">

    </el-table-column>
    <el-table-column align="center" label="服务机构名">
      <template slot-scope="scope">
                    <span>{{ scope.row.name }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="机构代码">
      <template slot-scope="scope">
                    <span>{{ scope.row.code }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="联系人姓名">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.linkman}}</span>
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

  <el-dialog title="服务机构详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
    <div class="textr paddinga">
      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handlePrint">打印</el-button>

      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
      <table id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>
        <tr height=25 style='mso-height-source:userset;height:18.75pt' id='r0'>
          <td colspan=9 id='tc0' height=25 class=x40>军民科技协同创新服务机构信息表</td>
        </tr>
        <tr height=64 style='mso-height-source:userset;height:48pt' id='r1'>
          <td height=64 class=x35 style='height:48pt;'>单位名称</td>
          <td class=x22></td>
          <td class=x21>所在地区</td>
          <td class=x25 ><span > </span>省<span > </span>市<span > </span>区（县）<br>（下拉选择）</td>
          <td class=x25>通信地址</td>
          <td colspan=4 class=x28>邮编</td>
        </tr>
        <tr height=19  id='r2'>
          <td height=19 class=x42 >机构性质</td>
          <td colspan=8 id='tc1' class=x90>□企业<span > </span>□科研院所<span > </span>□高等院校<span > </span>□其他：<span > </span>（注明）</td>
        </tr>
        <tr height=19  id='r3'>
          <td height=19 class=x35 >机构类别</td>
          <td colspan=8 id='tc2' class=x90 >□研究开发<span > </span>□技术转移<span > </span>□创业孵化<span > </span>□科技评估<span > </span>□管理咨询<br>□科技投融资
            <span
              > </span>□检验检测<span > </span>□知识产权<span > </span>□标准认证<span > </span>□综合科技服务
              <span
                > </span>□其他<br></td>
        </tr>
        <tr height=32  id='r4'>
          <td height=32 class=x46 >统一社会信用代码</td>
          <td colspan=9 class=x47></td>
        </tr>
        <tr height=32  id='r5'>
          <td height=32 >机构法定代表人</td>
          <td   >姓名</td>
          <td  >电话</td>
          <td  colspan=3 >手机</td>
          <td  colspan=3 >邮箱</td>
        </tr>
        <tr height=19  id='r6'>
          <td height=19 class=x42 >机构联系人</td>
          <td class=x49>姓名<span > </span></td>
          <td class=x52>职务</td>
          <td  id='tc7' class=x49>电话</td>
          <td colspan=2 id='tc8' class=x49>手机</td>
          <td colspan=3 id='tc9' class=x49>邮箱</td>
        </tr>
        <tr height=19  id='r7'>
          <td rowspan=3 height=83 class=x89 >近三年服务情况</td>
          <td class=x21>年度</td>
          <td class=x21>2017年</td>
          <td class=x21>2016年</td>
          <td colspan=5 id='tc10' class=x21>2015年</td>
        </tr>
        <tr height=32  id='r8'>
          <td class=x21 >服务收入（万元）</td>
          <td class=x21></td>
          <td class=x25></td>
          <td colspan=5 id='tc11' class=x25>备注：具体年份根据实际情况可变化</td>
        </tr>
        <tr height=32  id='r9'>
          <td class=x21 >服务数量（次）</td>
          <td class=x21></td>
          <td class=x25></td>
          <td colspan=5 id='tc12' class=x25></td>
        </tr>
        <tr height=32  id='r10'>
          <td height=32 class=x36 >人才队伍情况</td>
          <td class=x21>人员总数</td>
          <td colspan=2 id='tc13' class=x89>本科及以上学历人员占比</td>
          <td colspan=6 id='tc14' class=x60 >中高级职称以上人员占比</td>
        </tr>
        <tr height=19  id='r11'>
          <td height=19 class=x34 ></td>
          <td class=x36>
            <font class="font3"><span > </span></font>
            <font class="font5"><span > </span></font>
            <font class="font3">人</font>
          </td>
          <td colspan=2 id='tc15' class=x91 style='border-right:1px solid windowtext;'>
            <font class="font6"><span > </span></font>
            <font class="font4">人（</font>
            <font class="font6"><span > </span></font>
            <font class="font4">%）</font>
          </td>
          <td colspan=5 id='tc16' class=x27 ><span > </span>人（<span > </span>%）</td>
        </tr>
        <tr height=120 style='mso-height-source:userset;height:90pt' id='r12'>
          <td rowspan=4 height=190 class=x91 >承担的政府项目情况</td>
          <td class=x65>项目名称</td>
          <td class=x65 >起止时间<br><span > </span>年<span > </span>月--<span > </span>年<span > </span>月</td>
          <td class=x66 >项目来源<br>(按照“国家/省级XX项目”的形式填写，如江苏省军民融合发展引导资金项目)</td>
          <td class=x67>资助方式<br></td>
          <td colspan=4 id='tc17' class=x92>执行情况</td>
        </tr>
        <tr height=32  id='r13'>
          <td class=x72 >备注：可动态添加</td>
          <td class=x37></td>
          <td class=x37></td>
          <td rowspan=3 height=70 class=x91>□拨款<span > </span>□贴息<span > </span><br>□减免税<span > </span>□其他<span > </span><br>□以上全无</td>
          <td colspan=4 id='tc18' rowspan=3 height=70 class=x92>□申请<br>□在研<br>□验收/结题</td>
        </tr>
        <tr height=19  id='r14'>
          <td class=x37></td>
          <td class=x37></td>
          <td class=x37></td>
        </tr>
        <tr height=19  id='r15'>
          <td class=x37></td>
          <td class=x37></td>
          <td class=x37></td>
        </tr>
        <tr height=19  id='r16'>
          <td rowspan=3 height=57 class=x89>所获资质及荣誉</td>
          <td class=x32>名称</td>
          <td class=x32>获得时间</td>
          <td class=x26>颁发机构</td>
          <td class=x26></td>
          <td colspan=4 class=x26></td>
        </tr>
        <tr height=19  id='r17'>
          <td class=x25></td>
          <td class=x32></td>
          <td colspan=6 id='tc19' class=x93>备注：可动态添加</td>
        </tr>
        <tr height=19  id='r18'>
          <td class=x83></td>
          <td class=x32></td>
          <td colspan=6 id='tc20' class=x25></td>
        </tr>
        <tr height=32  id='r19'>
          <td rowspan=3 height=70 class=x89>主要工作成效（围绕国家军民融合公共服务开展的服务情况）</td>
          <td class=x41>服务项目名称</td>
          <td class=x25>服务对象</td>
          <td colspan=6 id='tc21' class=x37>服务时间</td>
        </tr>
        <tr height=19  id='r20'>
          <td class=x88></td>
          <td class=x25></td>
          <td colspan=6 id='tc22' class=x93>备注：可动态添加</td>
        </tr>
        <tr height=19  id='r21'>
          <td class=x25></td>
          <td class=x25></td>
          <td colspan=6 id='tc23' class=x91></td>
        </tr>
        <tr height=64 style='mso-height-source:userset;height:48pt' id='r22'>
          <td height=64 class=x89 style='height:48pt;overflow:hidden;'>可提供的服务简介（200字以内）</td>
          <td colspan=8 id='tc24' class=x91></td>
        </tr>
        <tr height=0 style='display:none'>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
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
  getservices,
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

      mech: {
        zhengben: [],
        fuben: [],
        logo: [],
        registerNature: '',
        org_type: '',
        serviceAbout: '',
        service_amount_last: '',
        service_amount_before: '',
        service_amount_previous: '',
        service_quantity_last: '',
        service_quantity_before: '',
        service_quantity_previous: '',
        registerImplement: '',
        registerSupport: '',
        honor: [{
          name: '',
          time: '',
          issuingAgency: ''
        }],
        PorcolumnDefinitions: [{
          name: '',
          time: '',
          source: ''
        }],
        workrPorcolumnDefinitions: [{
          name: '',
          object: '',
          time: ''
        }],
        MHPer: '',
        underPer: '',
        perNum: '',
        ecode: '',
        address: '',
        selected: '',
        name: '',
        fdemail: '',
        fdphone: '',
        fdname: '',
        lxemail: '',
        lxphone: '',
        lxzw: '',
        linkman: ''
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
      } = await getservices(this.listQuery)
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
      // if (!this.validata.validausr(obj)) return
      let arr ={}
      arr.formType = '3'
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
          name: 'mechanismEdit',
          params: {
            objId :data.form.id,
            objData: data.form.detail
          }
        })
      } else {
        this.$router.push({
          name: 'mechanismEdit'
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
