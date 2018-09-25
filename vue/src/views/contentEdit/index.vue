<template>
<div class="tab-container" style="overflow-y: auto;">

  <table v-show="!show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;padding: 50px;">
    <tr  style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 标题</td>
      <td>
        <el-input v-model="content.title" placeholder="请输入标题" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 所属栏目</td>
      <td>
        <el-select v-model="content.categroyId" style="height:30px;width:80%" placeholder="请选择">

          <el-option v-for="item in leafNodeslist" :label="item.name" :key="item.name" :value="item.id">
          </el-option>
        </el-select>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 封面</td>
      <td>
        <el-upload class="avatar-uploader"  accept=".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF" :http-request="uploadSectionFile" :before-upload="beforeUploadImg" list-type="picture-card" :file-list="content.covers" :on-success="handleAvatarSuccess" :on-remove="handleRemove">
          <i class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span>&nbsp;内容类型</td>
      <td>
        <el-select v-model="content.typeId" style="height:30px;width:80%" placeholder="请选择">

          <el-option label="文章" :key=1 :value=1>
          </el-option>
          <el-option label="轮播" :key=2 :value=2>
          </el-option>
        </el-select>
      </td>
    </tr>

    <tr>
      <td style="width:100px;padding:10px">&nbsp;&nbsp;是否外链</td>
      <td>
        <el-select v-model="content.onlyUrl" style="height:30px;width:80%" placeholder="请选择">
          <el-option label="是" :key=1 :value=1>
          </el-option>
          <el-option label="否" :key=0 :value=0>
          </el-option>
        </el-select>
      </td>
      <td  v-show="content.onlyUrl == 1" style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 外链地址</td>
      <td  v-show="content.onlyUrl == 1">
        <el-input v-model="content.contentUrl" placeholder="请输入外链地址" style="width:80%"></el-input>
      </td>
    </tr>
    <tr v-show="content.typeId == 1">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;是否转载</td>
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
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 来源</td>
      <td>
        <el-input v-model="content.copyFrom" placeholder="请输入来源" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 来源网址</td>
      <td>
        <el-input v-model="content.copyFromUrl" placeholder="请输入来源网址" style="width:80%"></el-input>
      </td>
    </tr>
    <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;作者</td>
      <td>
        <el-input v-model="content.author" placeholder="请输入作者" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px">&nbsp;&nbsp;编辑</td>
      <td>
        <el-input v-model="content.editor" placeholder="请输入编辑" style="width:80%"></el-input>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td v-show="content.typeId == 1" style="width:100px;padding:10px">&nbsp;&nbsp;标签</td>
      <td v-show="content.typeId == 1">
        <el-input v-model="content.tags" placeholder="请输入标签" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 描述</td>
      <td>
        <el-input v-model="content.description" placeholder="请输入描述" style="width:80%"></el-input>
      </td>
    </tr>
    <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;定时发布</td>
      <td>
        <el-select v-model="content.publishNow" style="height:30px;width:80%" placeholder="请选择">

          <el-option label="是" :key=1 :value=1>
          </el-option>
          <el-option label="否" :key=0 :value=0>
          </el-option>
        </el-select>
      </td>
      <td v-show="content.publishNow == 1" style="width:100px;padding:10px">&nbsp;&nbsp;发布日期</td>
      <td v-show="content.publishNow == 1">
        <el-date-picker v-model="content.publishDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
        </el-date-picker>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;置顶</td>
      <td>
            <el-select v-model="content.stickSort" style="height:30px;width:80%" placeholder="请选择">
              <el-option label="是" :key=0 :value=0>
              </el-option>
              <el-option label="否" :key=9999 :value=9999>
              </el-option>
            </el-select>
      </td>
      <td v-show="content.typeId == 1"  style="width:100px;padding:10px">&nbsp;&nbsp;推荐</td>
      <td v-show="content.typeId == 1" >
        <el-select v-model="content.recommend" style="height:30px;width:80%" placeholder="请选择">

          <el-option label="是" :key=1 :value=1>
          </el-option>
          <el-option label="否" :key=0 :value=0>
          </el-option>
        </el-select>
      </td>
    </tr>

    <tr v-show="content.typeId == 1" style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;活动内容</td>
      <td colspan=3>
        <!-- <tinymce :height="300" v-model="content.contentTxt"></tinymce> -->
        <editor v-model="content.contentTxt" :setting="editorSetting" :init="editorInit"></editor>
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
// import Tinymce from '@/components/Tinymce'
import Editor from '@tinymce/tinymce-vue';
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
  getLeafNodes,
  uploadFile,
  uploadCategory
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
    // Tinymce
    'editor': Editor
  },
  data() {
    return {
      editorInit: {
        language: 'zh_CN',
        plugins: [
          'image  code codesample'
        ],
        toolbar: ['bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript ',
          'hr bullist numlist link image charmap preview anchor pagebreak fullscreen media table emoticons forecolor backcolor'
        ],
        height: 300,
        images_upload_handler(blobInfo, success, failure) {
          const formData = new FormData()
          formData.append('file', blobInfo.blob())
          uploadCategory(formData, '1').then(res => {
            success(Vue.prototype.imgBaseUrl+ res.data.savePath)
          }).catch(() => {
            failure('上传失败，请重新上传')
          })
        },
      },
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
        onlyUrl: 0,
        contentUrl: '',
        copyFrom: '',
        copyFromUrl: '',
        author: '',
        editor: '',
        cover: '',
        covers: [{
          url: this.imgBaseUrl + `def/topiccover.png`
        }],
        tags: '',
        description: '',
        onlyUrl: 0,
        contentUrl: '',
        publishNow: 1,
        publishDate: '',
        contentTxt: '',
        stickSort: 0,
        recommend: 0,
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


    beforeUploadImg(file) {
      const isLt10M = file.size / 1024 / 1024 < 10;
      if (['image/png', 'image/jpeg', ].indexOf(file.type) == -1) {
          this.$message.error('请上传正确的图片');
          return false;
      }
      if (!isLt10M) {
        this.$message.error('上传文件大小不能超过10MB哦!');
        return false;
      }
    },
    //封面上传
    async uploadSectionFile(param) { //自定义文件上传
      this.content.covers = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await uploadFile(form)
      debugger
      let obj = {
        name: data.fileName,
        url: this.imgBaseUrl+ data.savePath
      }
      this.content.covers.push(obj)
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
      this.content.covers = [{
        name: 'name.jpg',
        url: this.imgBaseUrl + data.cover
      }] //封面赋值显示仅仅
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

    async saveObj(checkStatus) {
      if (!this.content.publishDate && this.content.publishNow == 0) {
        this.content.publishDate = this.getformatTime()
      }
      if (this.content.typeId == 1) {
        if (!this.validata.validacontent(this.content)) return
      } else {
        if (!this.validata.validacontent2(this.content)) return
      }


      let coversarr = this.content.covers[0].url
      if (coversarr.indexOf('/cover/') > -1) {
        this.content.covers[0].url = coversarr.substring(coversarr.indexOf('/cover/') + 1, coversarr.length)
      } else {
        this.content.covers[0].url = coversarr.substring(coversarr.indexOf('/def/') + 1, coversarr.length)
      }

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

        this.content.covers = [{
          name: 'name.jpg',
          url: this.imgBaseUrl + this.content.covers[0].url
        }] //封面赋值显示仅仅
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
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




  }
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>
