<template>
<div class="tab-container" style="overflow-y: auto;">

  <table v-show="!show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;padding: 50px;">
    <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">标题</td>
      <td>
        <el-input v-model="content.title" placeholder="请输入标题" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px">所属栏目</td>
      <td>
        <el-select v-model="content.categroyId" style="height:30px;width:80%" placeholder="请选择">

          <el-option v-for="item in leafNodeslist" :label="item.name" :key="item.name" :value="item.id">
          </el-option>
        </el-select>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">封面</td>
      <td>
        <el-upload  class="avatar-uploader" :action="coverUrl" list-type="picture-card" :file-list="content.cover" :on-success="handleAvatarSuccess" :on-remove="handleRemove">
          <i class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </td>
      <td style="width:100px;padding:10px">内容类型</td>
      <td>
        <el-select v-model="content.typeId" style="height:30px;width:80%" placeholder="请选择">

          <el-option label="文章" :key=1 :value=1>
          </el-option>
          <el-option label="轮播" :key=2 :value=2>
          </el-option>
        </el-select>
      </td>
    </tr>
    <tr v-show="content.typeId == 1">
      <td style="width:100px;padding:10px">是否转载</td>
      <td>
        <el-select v-model="content.copied" style="height:30px;width:80%" placeholder="请选择">

          <el-option label="转载" :key=1 :value=1>
          </el-option>
          <el-option label="非转载" :key=0 :value=0>
          </el-option>
        </el-select>
      </td>
    </tr>
    <tr v-show="content.copied == 1 && content.typeId == 1" style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">来源</td>
      <td>
        <el-input v-model="content.copyFrom" placeholder="请输入来源" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px">来源网址</td>
      <td>
        <el-input v-model="content.copyFromUrl" placeholder="请输入来源网址" style="width:80%"></el-input>
      </td>
    </tr>
    <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">作者</td>
      <td>
        <el-input v-model="content.author" placeholder="请输入作者" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px">编辑</td>
      <td>
        <el-input v-model="content.editor" placeholder="请输入编辑" style="width:80%"></el-input>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td v-show="content.typeId == 1" style="width:100px;padding:10px">标签</td>
      <td v-show="content.typeId == 1">
        <el-input v-model="content.tags" placeholder="请输入标签" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px">描述</td>
      <td>
        <el-input v-model="content.description" placeholder="请输入描述" style="width:80%"></el-input>
      </td>
    </tr>
    <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">定时发布</td>
      <td>
        <el-select v-model="content.publishNow" style="height:30px;width:80%" placeholder="请选择">

          <el-option label="是" :key=1 :value=1>
          </el-option>
          <el-option label="否" :key=0 :value=0>
          </el-option>
        </el-select>
      </td>
      <td v-show="content.publishNow == 1" style="width:100px;padding:10px">发布日期</td>
      <td v-show="content.publishNow == 1">
        <el-date-picker v-model="content.publishDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
        </el-date-picker>
      </td>
    </tr>
    <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">置顶</td>
      <td>
        <el-input v-model="content.stickSort" placeholder="" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px">推荐</td>
      <td>
        <el-select v-model="content.recommend" style="height:30px;width:80%" placeholder="请选择">

          <el-option label="是" :key=1 :value=1>
          </el-option>
          <el-option label="否" :key=0 :value=0>
          </el-option>
        </el-select>
      </td>
    </tr>

    <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">活动内容</td>
      <td colspan=3>
        <tinymce :height="300" v-model="content.contentTxt"></tinymce>
      </td>
    </tr>
  </table>
  <div style="padding-left: 35%;margin: 40px 0  0  0;height: 100px;">
    <div class="" style="padding:15px" v-if="!$route.params.objData">
      <el-radio-group v-model="checkStatus">
        <el-radio :label="-1">草稿</el-radio>
        <el-radio :label="0">提交待审</el-radio>
        <el-radio :label="1">直接审核</el-radio>
      </el-radio-group>
    </div>
    <div class="">
      <el-button type="primary" style="width: 120px;" @click="saveObj(checkStatus)">保存</el-button>
      <el-button type="primary" style="width: 120px;" @click="back">返回</el-button>
    </div>
  </div>
</div>
</template>

<script>
import Tinymce from '@/components/Tinymce'
import {
  getCategory,
  postCategoryC,
  getCategoryC,
  delCategoryC,
  rejectCategory,
  checkCategory,
  onCategory,
  offCategory,
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
import {
  getToken,
} from '@/utils/auth'
export default {
  components: {
    Tinymce
  },
  data() {
    return {
      coverUrl: '/xtcx/file/upload?token=' + getToken(),
      rej: {
        way: '1',
        info: '',
        formId: ''
      },
      xyset: {
        order: '',
        id: ''
      },
      title: "",
      input: {
        objName: '',
        enrollStart: '',
        enrollEnd: '',
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
        cover: [],
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
    if (this.$route.params.itemId) {
      this.loadData(this.$route.params.itemId)
      if (this.$route.params.checkStatus > 1) {
        this.checkStatus = 1
      } else {
        this.checkStatus = this.$route.params.checkStatus
      }
    } else {
      this.loadgetLeafNodes()
    }


  },
  computed: {},
  methods: {
    //封面返回
    handleAvatarSuccess(res, file) {
      let obj = file.response.data
      this.content.cover = obj
    },
    back() {
      window.history.go(-1);
    },
    async loadData(id) {
      let {
        data,
        success
      } = await getCategoryC(id)
      var myFilter = Vue.filter('formatTime')
      this.content = data
      this.content.publishDate = myFilter(data.publishDate)
      this.content.cover = [{name: 'food.jpeg',url:"http://106.14.172.38:8990/jmrhupload/cover/"+data.cover}]
      this.loadgetLeafNodes()
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
        arr.push(rej.formId)
        let {
          data,
          success
        } = await PLrejectUserDetail(arr)
        if (success) {
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.loadPageList()
        }
      } else {
        let {
          data,
          success
        } = await rejectUserDetail(rej)
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
      } = await PLrejectUserDetail(this.multipleSelection)
      if (success) {
        this.$message({
          type: 'success',
          message: '审核成功!'
        });
        this.loadPageList()
      }


    },
    async plxj() {
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
        this.dialogShowLevel = true
        this.xyset.id = item.id
      } else if (num == 1) {
        this.xyset.id = item.id
        let {
          data,
          success
        } = await topCategory(this.xyset)
        this.$message({
          message: '置顶成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
    handlesh(data) {
      this.dialogShowSH = true
      this.rej.formId = data.id
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
      let obj = {}
      obj.id = params.id
      let {
        data,
        success
      } = await onCategory(obj)
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
        this.loadgetLeafNodes()
        this.title = '编辑内容'
        this.dialogFormVisible = true
        let {
          data,
          success
        } = await getCategoryC(item.id)
        var myFilter = Vue.filter('formatTime')
        this.content = data
        this.content.publishDate = myFilter(data.publishDate)
      } else if (type === 'add') {
        this.loadgetLeafNodes()
        this.title = '添加内容'
        this.show = false
        this.dialogFormVisible = true
      } else if (type === 'show') {
        let {
          data,
          success
        } = await getCategoryC(item.id)
        this.content = data
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
      if (!this.content.publishDate && this.content.publishNow == 0) {
        this.content.publishDate = this.getformatTime()
      }
      if (this.content.typeId == 1) {
        if (!this.validata.validacontent(this.content)) return
      } else {
        if (!this.validata.validacontent2(this.content)) return
      }
      let arr = {}
      arr = this.content
      if(this.content.cover.length == 0){
      arr.cover = ''
      }
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
@import '../../../styles/index.scss'; // 全局自定义的css样式
</style>
