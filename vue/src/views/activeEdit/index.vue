<template>
<div class="tab-container" style="overflow-y: auto;">

  <table cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;padding: 50px;">
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 标题</td>
      <td>
        <el-input v-model="active.title" placeholder="请输入标题" style="width:80%"></el-input>
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 主题</td>
      <td>
        <el-input v-model="active.subject" placeholder="请输入主题" style="width:80%"></el-input>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;封面</td>
      <td>
        <el-upload class="avatar-uploader"  accept=".jpg,.jpeg,.png,.gif,.bmp,.pdf,.JPG,.JPEG,.PBG,.GIF,.BMP,.PDF" :http-request="uploadSectionFile" :before-upload="beforeUploadImg" list-type="picture-card" :file-list="active.covers" :on-success="handleAvatarSuccess" :on-remove="handleRemove">
          <i class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <!-- <img v-if="active.cover!=''" width="100%" :src="cover" alt=""> -->
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span>&nbsp;活动类型</td>
      <td>
        <el-select v-model="active.typeId" style="height:30px;width:80%" placeholder="请选择">

          <el-option label="展会" :key=0 :value=0>
          </el-option>
          <el-option label="讲座" :key=1 :value=1>
          </el-option>
          <el-option label="座谈" :key=2 :value=2>
          </el-option>
          <el-option label="洽谈" :key=3 :value=3>
          </el-option>
          <el-option label="高峰论坛" :key=4 :value=4>
          </el-option>
          <el-option label="其他" :key=5 :value=5>
          </el-option>
        </el-select>
      </td>
    </tr>
    <tr>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span>&nbsp;活动地址</td>
      <td colspan=3>
        <el-input v-model="active.exAddr" placeholder="请输入活动地址" style="width:90%"></el-input>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 报名时间起</td>
      <td>
        <el-date-picker v-model="active.enrollStart" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
        </el-date-picker>
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 报名时间止</td>
      <td>
        <el-date-picker v-model="active.enrollEnd" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
        </el-date-picker>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 活动时间起</td>
      <td>
        <el-date-picker v-model="active.exStart" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
        </el-date-picker>
      </td>
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 活动时间止</td>
      <td>
        <el-date-picker v-model="active.exEnd" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
        </el-date-picker>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span>&nbsp;主办方</td>
      <td>
        <el-input v-model="active.organizerName" placeholder="请输入主办方" style="width:80%"></el-input>
      </td>
      <td style="width:150px;padding:10px"><span style='color: #f60d0d;'>*</span>&nbsp;主办方联系人</td>
      <td>
        <el-input v-model="active.orgContacts" placeholder="请输入主办方联系人" style="width:80%"></el-input>
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:150px;padding:10px"><span style='color: #f60d0d;'>*</span>&nbsp;主办方联系电话</td>
      <td>
        <el-input v-model="active.orgPhone" placeholder="请输入主办方联系电话" style="width:80%"></el-input>
      </td>
      <td style="width:150px;padding:10px">&nbsp;&nbsp;主办方联系邮件</td>
      <td>
        <el-input v-model="active.orgEmail" placeholder="请输入主办方联系邮件" style="width:80%"></el-input>
      </td>
    </tr>

    <tr>
      <td style="width:150px;padding:10px">&nbsp;&nbsp;主办方联系地址</td>
      <td colspan=3>
        <el-input v-model="active.orgAddr" placeholder="请输入主办方联系地址" style="width:90%"></el-input>
      </td>
    </tr>

    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;定时发布</td>
      <td>
        <el-select v-model="active.publishNow" style="height:30px;width:80%" placeholder="请选择">
          <el-option label="是" :key=1 :value=1>
          </el-option>
          <el-option label="否" :key=0 :value=0>
          </el-option>
        </el-select>
      </td>
      <td v-show="active.publishNow == 1" style="width:100px;padding:10px"><span style='color: #f60d0d;'>*</span> 发布日期</td>
      <td v-show="active.publishNow == 1">
        <el-date-picker v-model="active.publishDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
        </el-date-picker>
      </td>
    </tr>

    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;置顶</td>
      <td>
          <el-select v-model="active.stickSort" style="height:30px;width:80%" placeholder="请选择">
            <el-option label="是" :key=0 :value=0>
            </el-option>
            <el-option label="否" :key=9999 :value=9999>
            </el-option>
          </el-select>
      </td>

    </tr>

    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;活动内容</td>
      <td colspan=3>
        <div>
          <!-- <tinymce :height="300" v-model="active.exContent"></tinymce> -->
          <editor v-model="active.exContent" :setting="editorSetting" :init="editorInit"></editor>
        </div>
        <!-- <div class="editor-content" v-html="active.exContent"></div> -->
      </td>
    </tr>
    <tr style="border-bottom: 1px solid#ccc;">
      <td style="width:100px;padding:10px">&nbsp;&nbsp;附件</td>
      <td colspan=3>
        <div>
          <div class="iconfont" style="float:left;padding-right: 15px;" v-for="item in active.cmsFileList">
            <el-dropdown>
              <span class="el-dropdown-link">
                  <i v-show="item.fileObj == 'video'" class="iconfont  icon-shipin" slot="right"></i>
                  <i v-show="item.fileObj == 'img'" class="iconfont  icon-tupian1" slot="right"></i>
                  <i v-show="item.fileObj == 'word'" class="iconfont  icon-Word" slot="right"></i>
                  <i v-show="item.fileObj == 'txt'" class="iconfont  icon-txt" slot="right"></i>
                  <i v-show="item.fileObj == 'mp3'" class="iconfont  icon-music" slot="right"></i>
                  <i v-show="item.fileObj == 'file'" class="iconfont  icon-wenjian" slot="right"></i>
               </span>
              <el-dropdown-menu slot="dropdown">
                <div class="" @click="subDownloadExchanges(item)">
                  <el-dropdown-item>下载</el-dropdown-item>
                </div>
                <div class="" @click="subDelFile(item)">
                  <el-dropdown-item style="color:red">删除</el-dropdown-item>
                </div>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <div class="iconfont" style="float:left;padding-left: 5px;">
            <el-upload :action="uploadUrl" :show-file-list="false" :on-preview="handlePictureCardPreview" :on-success="handleVideoSuccess" :before-upload="beforeUploadVideo" :on-progress="uploadVideoProcess">
              <i class="iconfont  icon-htmal5icon18"></i>
            </el-upload>
          </div>
          <!-- <el-progress v-if="videoFlag == true" type="circle" :percentage="videoUploadPercent" style="position: relative;left: 20%;"></el-progress> -->

        </div>
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
  getExchanges,
  postExchanges,
  submitExchanges,
  delexchanges,
  rejectExchanges,
  checkExchanges,
  onExchanges,
  offExchanges,
  topExchanges,
  getExchangesC,
  downloadExchanges,
  delFile,
  uploadFile,
  uploadExchanges
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
      formDatas: '',
      uploadUrl: this.docUrl+'/exchanges/upload?token=' + getToken(),
      editorInit: {
        language: 'zh_CN',
        plugins: [
          'image  code codesample'
        ],
        toolbar: ['fontselect fontsizeselect  bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample',
          'hr bullist numlist link image charmap	 preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen'
        ],
        fontsize_formats: "8px 10px 12px 14px 18px 20px 24px 36px",
        font_formats: "微软雅黑='微软雅黑';宋体='宋体';黑体='黑体';仿宋='仿宋';楷体='楷体';隶书='隶书';幼圆='幼圆';Andale Mono=andale mono,times;Arial=arial,helvetica,sans-serif;Arial Black=arial black,avant garde;Book Antiqua=book antiqua,palatino;Comic Sans MS=comic sans ms,sans-serif;Courier New=courier new,courier;Georgia=georgia,palatino;Helvetica=helvetica;Impact=impact,chicago;Symbol=symbol;Tahoma=tahoma,arial,helvetica,sans-serif;Terminal=terminal,monaco;Times New Roman=times new roman,times;Trebuchet MS=trebuchet ms,geneva;Verdana=verdana,geneva;Webdings=webdings;Wingdings=wingdings",
        height: 300,
        images_upload_handler(blobInfo, success, failure) {
          const formData = new FormData()
          formData.append('file', blobInfo.blob())
          uploadExchanges(formData, '1').then(res => {
            success(Vue.prototype.imgBaseUrl+ res.data.savePath)
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
      active: {
        title: '',
        subject: '',
        typeId: 1,
        cover: '',
        covers: [{
          name: '默认',
          url: this.imgBaseUrl + `def/activitycover.png`
        }],
        exAddr: '',
        enrollStart: '',
        enrollEnd: '',
        exStart: '',
        exEnd: '',
        organizerName: '',
        orgContacts: '',
        orgPhone: '',
        orgEmail: '',
        orgAddr: '',
        publishNow: 1,
        publishDate: '',
        publishUserId: '',
        checkDate: '',
        checkUserId: '',
        stickSort: 9999,
        exContent: '',
        cmsFileList: []
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
      window.open(this.imgBaseUrl+ item.savePath);
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
      } = await getExchangesC(id)
      var myFilter = Vue.filter('formatTime')
      this.active = data
      debugger
      this.active.covers = [{
        name: 'name.jpg',
        url: this.imgBaseUrl +  data.cover
      }] //封面赋值显示仅仅
      let arr = data.cmsFileList
      if (arr.length > 0) {
        for (var i in arr) {
          let name = arr[i].fileName
          if (name.indexOf('mp4') > -1 || name.indexOf('avi') > -1 || name.indexOf('rmvb') > -1) {
            name = 'video'
          } else if (name.indexOf('jpg') > -1 || name.indexOf('jpeg') > -1 || name.indexOf('png') > -1) {
            name = 'img'
          } else if (name.indexOf('txt') > -1) {
            name = 'txt'
          } else if (name.indexOf('xlsx') > -1 || name.indexOf('docx') > -1 || name.indexOf('doc') > -1) {
            name = 'word'
          } else if (name.indexOf('mp3') > -1) {
            name = 'mp3'
          } else {
            name = 'file'
          }
          arr[i].fileObj = name
        }
      }
      this.active.cmsFileList = data.cmsFileList


      this.active.enrollStart = myFilter(data.enrollStart)
      this.active.enrollEnd = myFilter(data.enrollEnd)
      this.active.exStart = myFilter(data.exStart)
      this.active.exEnd = myFilter(data.exEnd)
      this.active.publishDate = myFilter(data.publishDate)
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
        url: this.imgBaseUrl+ data.savePath
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

    async saveObj(checkStatus) {
      if (!this.active.publishDate && this.active.publishNow == 0) {
        this.active.publishDate = this.getformatTime()
      }
      if (!this.validata.validactive(this.active)) return

      let coversarr = this.active.covers[0].url
      if (coversarr.indexOf('/cover/') > -1) {
        this.active.covers[0].url = coversarr.substring(coversarr.indexOf('/cover/') + 1, coversarr.length)
      } else {
        this.active.covers[0].url = coversarr.substring(coversarr.indexOf('/def/') + 1, coversarr.length)
      }


      let arr = {}
      arr = this.active
      arr.checkStatus = checkStatus

      let {
        data,
        success
      } = await postExchanges(arr)

      if (success) {
        this.$message({
          message: '发布成功',
          type: 'success'
        });
        this.active.covers = [{
          name: 'name.jpg',
          url: this.imgBaseUrl +  this.active.covers[0].url
        }] //封面赋值显示仅仅
          window.history.go(-1);
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
