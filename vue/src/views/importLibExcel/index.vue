<template>
<div class="tab-container">
  <div class="">
    <div class="paddingb textc paddingr" style="font-size:14px">
      <el-radio-group v-model="userType">
        <el-radio :label="1">专家</el-radio>
        <el-radio :label="2">企业</el-radio>
        <el-radio :label="3">服务机构</el-radio>
        <el-radio :label="4">高校院所</el-radio>
        <el-radio :label="6">成果库</el-radio>
        <el-radio :label="7">需求库</el-radio>
      </el-radio-group>
    </div>
    <div class="textc" style="padding:3%">
      <el-upload class="upload-demo" accept=".xlsx,.xls" drag :http-request="uploadSectionFile2" :before-upload="beforeUploadImg">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传xlsx/xls文件，且不超过500kb</div>
      </el-upload>
    </div>
    <div class="textc" style="height:100px;overflow-y:auto">
      <li v-for="item in list" style="list-style:none">{{item}}</li>
    </div>
  </div>



</div>
</template>


<script>
import {
  importLibExcel
} from '@/api/library'
import {
  Loading
} from 'element-ui';
export default {
  data() {
    return {
      userType: 1,
      list: [],
    }
  },
  mounted() {

  },
  computed: {},
  methods: {
    beforeUploadImg(file) {
      // const isLt10M = file.size / 1024 / 1024 < 10;
      if (file.name.indexOf('xlsx') <= -1 && file.name.indexOf('xls') <= -1) {
        // if (['application/vnd.ms-excel', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', ].indexOf(file.type) == -1) {
        this.$message.error('请上传正确的格式');
        return false;
      }
      // if (!isLt10M) {
      //   this.$message.error('上传文件大小不能超过10MB哦!');
      //   return false;
      // }
    },
    async uploadSectionFile2(param) {
      Loading.service({
        fullscreen: true,
        background: 'rgba(0, 0, 0, 0.2)'
      });
        var div = document.getElementsByClassName("el-loading-mask is-fullscreen")
        div[0].style.display = 'block'
      this.cardSide = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      form.append("userType", this.userType);
      let {
        data,
        success,
        message
      } = await importLibExcel(form)
      // this.$nextTick(() => { // 以服务的方式调用的 Loading 需要异步关闭
      //   // loadingInstance.close();
      // });
      var div = document.getElementsByClassName("el-loading-mask is-fullscreen")
      div[0].style.display = 'none'

      if (success) {
        this.$message({
          type: 'success',
          message: message
        });
      } else {
        let arr = []
        arr = message.split(';')
        this.list = arr
        loadingInstance.close();
        // this.$message({
        //   type: 'warning',
        //   message: message
        // });
      }
    },


  }
}
</script>

<style lang="scss">
</style>
