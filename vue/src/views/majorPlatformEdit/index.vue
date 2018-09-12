<template>
<div class="tab-container" style="overflow-y: auto;">

  <table cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;padding: 50px;">
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 名称</td>
      <td>
        <el-input v-model="majorPlatform.spName" placeholder="请输入名称" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 承担单位</td>
      <td>
        <el-input v-model="majorPlatform.spCompany" placeholder="请输入承担单位" style="width:80%"></el-input>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 领域</td>
      <td>
        <el-input v-model="majorPlatform.spDomain" placeholder="请输入领域" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 级别</td>
      <td>
        <el-input v-model="majorPlatform.spLevel" placeholder="请输入级别" style="width:80%"></el-input>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 链接</td>
      <td>
        <el-input v-model="majorPlatform.spLink" placeholder="请输入链接" style="width:80%"></el-input>
      </td>

    </tr>

  </table>

  <div style="padding-left: 35%;margin: 40px 0  0  0;height: 100px;">

    <div class="">
      <el-button type="primary" style="width: 120px;" @click="saveObj(majorPlatform)">保存</el-button>
      <el-button type="primary" style="width: 120px;" @click="back">返回</el-button>
    </div>
  </div>
</div>
</template>

<script>
// import Tinymce from '@/components/Tinymce'
// import Editor from '@tinymce/tinymce-vue';

import {
  getShareplatformC,
  updShareplatform,
  addShareplatform
} from '@/api/majorPlatform'

import table2excel from 'table2excel'
import printArea from 'printArea'

import idback from '@/assets/logo/idback.png'
import {
  getToken,
} from '@/utils/auth'

export default {
  components: {
    // Tinymce
    // 'editor': Editor
  },
  data() {
    return {
      formDatas: '',
      uploadUrl: this.docUrl + '/xtcx/exchanges/upload?token=' + getToken(),
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
          uploadExchanges(formData, '1').then(res => {
            success(Vue.prototype.imgBaseUrl + "/jmrhupload" + res.data.savePath)
          }).catch(() => {
            failure('上传失败，请重新上传')
          })
        },
      },
      showFile: '',
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
      majorPlatform: {
        spName: '',
        spCompany: '',
        spDomain: '',
        spLevel: '',
        spLink: '',
      }
    }
  },
  async mounted() {
    // editor.init({})
    if (this.$route.params.itemId) {
      this.loadData(this.$route.params.itemId)
      if (this.$route.params.checkStatus > 1) {
        this.checkStatus = 1
      } else {
        this.checkStatus = this.$route.params.checkStatus
      }
    }
  },
  computed: {},
  methods: {
    async subDownloadExchanges(item) {
      window.open(this.imgBaseUrl + "/jmrhupload" + item.savePath);
    },
    async subDelFile(item) {
      let {
        data,
        success
      } = await delFile(item.id)
      this.active.cmsFileList.splice(this.active.cmsFileList.indexOf(item), 1)
      this.$message({
        type: 'success',
        message: '删除成功!'
      });
    },
    async loadData(id) {
      let {
        data,
        success
      } = await getShareplatformC(id)

      this.majorPlatform = data
    },

    //附件返回
    handleVideoSuccess(res, file) {
      let obj = file.response.data
      if (obj.fileName) {
        if (obj.fileName.indexOf('mp4') > -1 || obj.fileName.indexOf('avi') > -1 || obj.fileName.indexOf('rmvb') > -1) {
          obj.fileObj = 'video'
        } else if (obj.fileName.indexOf('jpg') > -1 || obj.fileName.indexOf('jpeg') > -1 || obj.fileName.indexOf('png') > -1) {
          obj.fileObj = 'img'
        } else if (obj.fileName.indexOf('txt') > -1) {
          obj.fileObj = 'txt'
        } else if (obj.fileName.indexOf('xlsx') > -1 || obj.fileName.indexOf('docx') > -1 || obj.fileName.indexOf('doc') > -1) {
          obj.fileObj = 'word'
        } else if (obj.fileName.indexOf('mp3') > -1) {
          obj.fileObj = 'mp3'
        } else {
          obj.fileObj = 'file'
        }
        this.active.cmsFileList.push(obj)
      }
    },
    beforeUploadVideo(file) {
      const isLt10M = file.size / 1024 / 1024 < 10;
      // if (['video/mp4', 'video/ogg', 'video/flv','video/avi','video/wmv','video/rmvb'].indexOf(file.type) == -1) {
      //     this.$message.error('请上传正确的视频格式');
      //     return false;
      // }
      if (!isLt10M) {
        this.$message.error('上传文件大小不能超过10MB哦!');
        return false;
      }
    },

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
      this.active.covers = []
      var fileObj = param.file;
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await uploadFile(form)
      let obj = {
        name: data.fileName,
        url: this.imgBaseUrl + "/jmrhupload" + data.savePath
      }
      this.active.covers.push(obj)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    // uploadVideoProcess(event, file, fileList) {
    //   this.videoFlag = true;
    //   this.videoUploadPercent = file.percentage.toFixed(0);
    // },
    back() {
      window.history.go(-1);
    },
    async saveFile(objdata) {

      let {
        data,
        success
      } = await updShareplatform(objdata)
      this.$message({
        message: '设置成功',
        type: 'success'
      });
    },

    async saveObj(arr) {

      if (!this.validata.validmajorPlatform(this.majorPlatform)) return

      if (this.majorPlatform.id) {
        let {
          data,
          success,
          message
        } = await updShareplatform(arr)
        if (success) {
          this.$message({
            message: message,
            type: 'success'
          });
          window.history.go(-1);
        } else {
          this.$message({
            message: message,
            type: 'success'
          });
        }
      }else{
        let {
          data,
          success,
          message
        } = await addShareplatform(arr)
        if (success) {
          this.$message({
            message: message,
            type: 'success'
          });
          window.history.go(-1);
        } else {
          this.$message({
            message: message,
            type: 'success'
          });
        }
      }


    },

  }
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>
