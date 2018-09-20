<template>
<div class="tab-container">
  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span>内容名</span>
      <el-input v-model="input.objName" placeholder="" style="width:200px;"></el-input>
      <span style="margin-left: 15px;">发布日期</span>
      <el-date-picker v-model="input.publishStart" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" style="width:140px">
      </el-date-picker>
      <el-date-picker v-model="input.publishEnd" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期" style="width:140px">
      </el-date-picker>
      <span style="margin-left: 15px;">状态</span>
      <el-select v-model="input.checkStatus" style="width:100px;height:30px" placeholder="请选择">
        <el-option label="全部" key='' value=''>
        </el-option>
        <el-option label="草稿" :key=-1 :value=-1>
        </el-option>
        <el-option label="待审核" :key=0 :value=0>
        </el-option>
        <el-option label="已发布" :key=1 :value=1>
        </el-option>
        <el-option label="驳回" :key=2 :value=2>
        </el-option>
      </el-select>

      <el-button style="margin-left:20px" @click="loadPageList" type="primary">查询</el-button>
      <div class="" style="padding:10px 0px;">
        <el-button  style="" @click="handleEdit('','add')" type="primary">添加内容</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 0  || userType =='101' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plsj" type="primary">批量上架</el-button>
      </div>

    </div>
  </div>

  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;">
    <el-table-column type="selection"  width="30px;">
    </el-table-column>
    <el-table-column align="center" label="标题">
      <template slot-scope="scope">
                    <div @click="handleEdit(scope.row,'show')" class="clickText" >{{ scope.row.title }}</div>
                </template>
    </el-table-column>
    <el-table-column align="center" label="发布人"  width="60px;">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.publishUserId}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="所属栏目">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.categroyId}}</span>
                </template>
    </el-table-column>


    <el-table-column v-show="tfcheckStatus == 1  || tfcheckStatus == ''" align="center" label="点击数"  width="60px;">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.clicks}}</span>
                </template>
    </el-table-column>

    <el-table-column v-show="tfcheckStatus == 1 || tfcheckStatus == ''" align="center" label="发布日期">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.publishDate  | formatTime}}</span>
                </template>
    </el-table-column>
    <el-table-column v-show="tfcheckStatus == 1 || tfcheckStatus == ''" align="center" label="创建日期">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.createTime  | formatTime}}</span>
                    </template>
    </el-table-column>
    <el-table-column v-show="tfcheckStatus == 1|| tfcheckStatus == ''" align="center" label="置顶"  width="60px;">
      <template slot-scope="scope">
                                <span v-show="scope.row.stickSort == 0">是</span>
                                <span v-show="scope.row.stickSort == 9999">否</span>
                    </template>
    </el-table-column>
    <el-table-column v-show="tfcheckStatus == 1|| tfcheckStatus == ''" align="center" label="是否可见"  width="70px;">
      <template slot-scope="scope">
                        <span v-show="scope.row.readable == 1">可见</span>
                        <span v-show="scope.row.readable == 0">不可见</span>
                    </template>
    </el-table-column>

    <el-table-column v-show="tfcheckStatus == 1|| tfcheckStatus == ''" align="center" label="审核用户">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.checkUserId}}</span>
                    </template>
    </el-table-column>

    <el-table-column v-show="tfcheckStatus == 1|| tfcheckStatus == ''" align="center" label="状态"  width="70px;">
      <template slot-scope="scope">
                        <span v-show="scope.row.checkStatus == -1">草稿</span>
                        <span v-show="scope.row.checkStatus == 0">待审核</span>
                        <span v-show="scope.row.checkStatus == 1">已发布</span>
                        <span v-show="scope.row.checkStatus == 2">驳回</span>
                    </template>
    </el-table-column>
    <el-table-column align="center" label="操作"  width="180px;">
      <template slot-scope="scope">
                    <div style="">
                        <div v-show="scope.row.checkStatus == 0" @click="handlesh(scope.row)" class="clickText" style="float:left;padding-left:5px">
                          审核
                        </div>
                      <div  v-show="scope.row.checkStatus != 0" @click="handleEdit(scope.row,'edit')" class="clickText" style="float:left;padding-left:5px">
                        编辑
                      </div>

                  <span v-show="scope.row.checkStatus == 1 && scope.row.stickSort > 0">  <div @click="handlexy(scope.row,1)" class="clickText" style="float:left;padding-left:5px">
                                            置顶
                            </div></span>
                    <span v-show="scope.row.checkStatus == 1 && scope.row.stickSort == 0">  <div @click="handlexy(scope.row,0)" class="clickText" style="float:left;padding-left:5px">
                                                      取消置顶
                    </div></span>

                  <span v-show="scope.row.readable == 1 && scope.row.checkStatus == 1">  <div @click="handlexj(scope.row)" class="clickText" style="float:left;padding-left:5px">
                                            下架
                            </div></span>
                  <span v-show="scope.row.readable == 0 && scope.row.checkStatus == 1">  <div @click="handlesj(scope.row)" class="clickText" style="float:left;padding-left:5px">
                                            上架
                  </div></span>
                  <span >  <div @click="delObj(scope.row)" class="clickText" style="float:left;padding-left:5px;color:red">
                                            删除
                  </div></span>

                    </div>
                </template>
    </el-table-column>

  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>


  <el-dialog :title="title" :visible.sync="dialogFormVisible" width="40%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="24">
        <el-col :span="24">

          <table class="showDetailTable" v-show="show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;">
            <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">标题</td>
              <td>
                {{content.title}}
              </td>
              <td style="width:100px;padding:10px">所属栏目</td>
              <td>
                <el-select disabled v-model="content.categroyId" style="height:30px;width:80%" placeholder="请选择">

                  <el-option v-for="item in leafNodeslist" :label="item.name" :key="item.name" :value="item.id">
                  </el-option>
                </el-select>
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

            <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">活动内容</td>
              <td colspan=3>
                <div class="editor-content" v-html="content.contentTxt" style="width:100%"></div>
              </td>
            </tr>
          </table>

        </el-col>

      </el-row>
    </el-form>
    <div v-show="!show" style="padding-left: 35%;margin: 40px 0  0  0;height: 100px;">
      <div class="" style="padding:15px" v-show="!$route.params.objData">
        <el-radio-group v-model="checkStatus">
          <el-radio :label="-1">草稿</el-radio>
          <el-radio :label="0">提交待审</el-radio>
          <el-radio :label="1">直接审核</el-radio>
        </el-radio-group>
      </div>
      <div class="">
        <el-button type="primary" style="width: 120px;" @click="saveObj(checkStatus)">保存</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </div>
    <div v-show="show" style="height: 100px;">

    </div>
  </el-dialog>



  <el-dialog title="置顶" :visible.sync="dialogShowLevel" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="12">
        <el-col :span="20">

          <el-form-item label="置顶排序">
            <el-input v-model="xyset.order" placeholder=""></el-input>
          </el-form-item>

        </el-col>

      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
        <el-button type="primary" style="" @click="saveFile(xyset)">保存</el-button>
        <el-button type="primary" @click="dialogShowLevel = false">关闭</el-button>
      </span>
  </el-dialog>



  <el-dialog title="审核" :visible.sync="dialogShowSH" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="24">
        <el-col :span="24">

          <el-form-item label="审核">
            <el-select v-model="rej.way" style="height:30px" placeholder="请选择">

              <el-option label="通过" key="1" value="1">
              </el-option>
              <el-option label="驳回" key="2" value="2">
              </el-option>
            </el-select>
          </el-form-item>


          <el-form-item v-show="rej.way == '2'" label="驳回原因">
            <el-input v-model="rej.info" placeholder=""></el-input>
          </el-form-item>
        </el-col>

      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer" style="">
        <el-button type="primary" style="" @click="saveReject(rej)">保存</el-button>
        <el-button type="primary" @click="dialogShowSH = false">关闭</el-button>
      </span>
  </el-dialog>

</div>
</template>

<script>
import {
  getCategory,
  postCategoryC,
  getCategoryC,
  delCategoryC,
  rejectCategory,
  checkCategory,
  plonCategory,
  offCategory,
  onCategory,
  topCategory,
  getLeafNodes
} from '@/api/columnManage'


import {
  addLib,
  delLib,
  rejectUserDetail,
  offUserDetail,
  onUserDetail,
  PLrejectUserDetail,
  PLoffUserDetails,
} from '@/api/library'
import table2excel from 'table2excel'
import printArea from 'printArea'
export default {
  components: {},
  data() {
    return {
      defaultMsg: '<span style="orphans: 2; widows: 2; font-size: 22px; font-family: KaiTi_GB2312; background-color: rgb(229, 51, 51);"><strong>测试内容</strong></span>',
      config: {
        initialFrameWidth: null,
        initialFrameHeight: 350
      },
      ue1: "ue1", // 不同编辑器必须不同的id
      rej: {
        way: '1',
        info: '',
        id: ''
      },
      xyset: {
        order: '',
        id: ''
      },
      title: "",
      input: {
        objName: '',
        publishStart: '',
        publishEnd: '',
        checkStatus: 1,
      },
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      dialogFormVisible: false,
      dialogShowRole: false,
      dialogShowDep: false,
      dialogShowSH: false,
      dialogShowLevel: false,
      dialogadd: false,
      dialogsave: false,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
      },
      treeData: [],
      loading: true,
      userType: '',
      multipleSelection: [],
      detailData: '',
      tfcheckStatus: '',
      checkStatus: -1,
      leafNodeslist: [],
      content: {
        title: '',
        categroyId: '',
        typeId: 1,
        copied: 0,
        copyFrom: '',
        copyFromUrl: '',
        author: '',
        editor: '',
        cover: '',
        tags: '',
        description: '',
        onlyUrl: 0,
        contentUrl: '',
        publishNow: 1,
        publishDate: '',
        contentTxt: '',
        stickSort: 1,
        recommend: 1,
      }
    }
  },
  async mounted() {
    if (typeof this.$route.query.checkStatus == 'number') {
      this.input.checkStatus = this.$route.query.checkStatus
    }
    this.loadPageList()
    this.userType = window.sessionStorage.getItem('userType')

  },
  computed: {},
  methods: {
    async loadPageList() {
      this.tfcheckStatus = this.input.checkStatus
      if (this.input) {
        this.input.page = this.listQuery.page
        this.input.limit = this.listQuery.limit
      }
      let {
        data,
        success
      } = await getCategory(this.input)
      if (success) {
        this.loading = false
        this.list = data.list
        this.total = data.total
      }
    },
    async loadgetLeafNodes() {
      let {
        data,
        success
      } = await getLeafNodes()
      if (success) {
        this.leafNodeslist = data
      }
    },
    async saveFile(objdata) {
      if (objdata.order == '') {
        this.$message({
          message: '请输入置顶排序！',
          type: 'success'
        });
        return
      }
      let {
        data,
        success
      } = await topCategory(objdata)
      this.$message({
        message: '设置成功',
        type: 'success'
      });
      this.loadPageList()
    },

    async saveReject(rej) {
      if (rej.way == '1') {
        let arr = []
        arr.push(rej.id)
        let {
          data,
          success
        } = await checkCategory(arr)
        if (success) {
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.loadPageList()
        }
      } else {
        if(rej.info==""){
          this.$message({
            message: '请输入驳回原因！',
            type: 'success'
          });
          return
        }
        let {
          data,
          success
        } = await rejectCategory(rej)
        if (success) {
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.loadPageList()
        }
      }


    },
    handleSelectionChange(val) {
      let arr = []
      for (let i in val) {
        arr.push(val[i].id)
      }
      this.multipleSelection = arr;
    },
    async plsh() {
      let {
        data,
        success
      } = await checkCategory(this.multipleSelection)
      if (success) {
        this.$message({
          type: 'success',
          message: '审核成功!'
        });
        this.loadPageList()
      }


    },
    async plsj() {
      if (this.multipleSelection.length == 0) {
        this.$message({
          type: 'success',
          message: '请勾选上架内容!'
        });
      }
      let {
        data,
        success
      } = await plonCategory(this.multipleSelection)
      if (success) {
        this.$message({
          type: 'success',
          message: '上架成功!'
        });
        this.loadPageList()
      }
    },
    async plxj() {
    if(this.multipleSelection.length == 0){
      this.$message({
        type: 'success',
        message: '请勾选下架内容!'
      });
    }
      let {
        data,
        success
      } = await offCategory(this.multipleSelection)
      if (success) {
        this.$message({
          type: 'success',
          message: '下架成功!'
        });
        this.loadPageList()
      }
    },
    pldc() {
      this.$message({
        type: 'success',
        message: '导出成功!'
      });
    },
    async handlexy(item, num) {
      if (num == 0) {
        this.xyset.id = item.id
        this.xyset.order = '9999'
        let {
          data,
          success
        } = await topCategory(this.xyset)
        this.$message({
          message: '取消置顶',
          type: 'success'
        });
      } else {
        this.xyset.id = item.id
        this.xyset.order = '0'
        let {
          data,
          success
        } = await topCategory(this.xyset)
        this.$message({
          message: '置顶成功',
          type: 'success'
        });
      }
      this.loadPageList()
    },
    handlesh(data) {
      this.dialogShowSH = true
      this.rej.id = data.id
    },
    async handlexj(params) {
      let obj = []
      obj.push(params.id)
      let {
        data,
        success
      } = await offCategory(obj)
      if (success) {
        this.$message({
          message: '下架成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
    async handlesj(params) {
      let arr = []
      arr.push(params.id)
      let {
        data,
        success
      } = await plonCategory(arr)
      if (success) {
        this.$message({
          message: '上架成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
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


    async handleEdit(item, type) {
      if (type === 'edit') {
        this.title = '编辑内容'
        this.$router.push({
          name: 'contentEdit',
          params: {
            itemId: item.id,
            checkStatus: this.tfcheckStatus
          }
        })
      } else if (type === 'add') {
        this.title = '添加内容'
        this.$router.push({
          name: 'contentEdit',
        })
      } else if (type === 'show') {
        this.loadgetLeafNodes()
        let {
          data,
          success
        } = await getCategoryC(item.id)
        this.content = data
       this.content.cover =  this.imgBaseUrl  + data.cover
        this.dialogFormVisible = true
        this.show = true
        this.title = '查看内容详情'
      }
    },
    delObj(item) {
      this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        let del = await delCategoryC(item)
        this.list.splice(this.list.indexOf(item), 1)
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
    },

    async saveObj(checkStatus) {
      // if (!this.validata.validausr(obj)) return
      let arr = {}
      arr = this.content
      arr.checkStatus = checkStatus
      let {
        data,
        success
      } = await postCategoryC(arr)

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

  }
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>
