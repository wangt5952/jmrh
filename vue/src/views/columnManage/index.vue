<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input" placeholder="请输入栏目名" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <el-button style="margin-left: 10px;" @click="handleCreate()" type="primary" icon="el-icon-edit">新增栏目</el-button>

    </div>
  </div>



  <tree-table :data="list" :evalFunc="func" :expandAll="expandAll" border style="text-align: center;">
    <el-table-column type="index" align="center" label="ID">

    </el-table-column>
    <el-table-column label="编码">
      <template slot-scope="scope">
        <span style="">{{scope.row.code}}</span>
      </template>
    </el-table-column>
    <el-table-column label="栏目">
      <template slot-scope="scope">
        <span style="">{{scope.row.name}}</span>
      </template>
    </el-table-column>
    <el-table-column label="栏目类型">
      <template slot-scope="scope">
        <span v-show="scope.row.typeId == 0" style="">目录</span>
        <span v-show="scope.row.typeId == 1" style="">栏目</span>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <div class="" style="position: relative;left: 10%;">
      <span> <div class="clickText" type="text" @click="handleCreate(scope.row,'show')" style="float:left;padding-left:5px">详情</div></span>
          <span>  <div class="clickText" type="text" @click="handleCreate(scope.row,scope.row.id)"  style="float:left;padding-left:5px">添加子栏目</div></span>
            <span>  <div class="clickText" type="text" @click="handleEdit(scope.row)"  style="float:left;padding-left:5px">编辑</div></span>
              <!-- <span>  <div class="clickText" type="text" @click="message(scope.row)"  style="float:left;padding-left:5px">禁用</div></span> -->
                <span>  <div class="clickText" type="text" @click="delObj(scope.row)"  style="float:left;padding-left:5px;color:red">删除</div></span>
                </div>
      </template>
    </el-table-column>
  </tree-table>


  <el-dialog :title="title" :visible.sync="dialogFormVisible" width="40%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <div class="" style="padding:5px">
        <div v-show="editlm">栏目ID：{{editlmID}}</div>
        <div v-show="addchlm">父栏目：{{addchlmName}}</div>
      </div>

      <el-row :gutter="24">
        <el-col :span="24">
          <table v-show="!show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;">
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">栏目编码</td>
              <td>
                <el-input v-model="column.code" placeholder="请输入栏目编码" style="width:80%"></el-input>
              </td>
              <td style="width:100px;padding:10px">栏目名</td>
              <td>
                <el-input v-model="column.name" placeholder="请输入栏目名" style="width:80%"></el-input>
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">栏目Icon</td>
              <td>
                <el-upload class="avatar-uploader" ref="my-upload" :http-request="uploadSectionFile" list-type="picture-card" :file-list="column.icons" :on-success="handleAvatarSuccess" :on-remove="handleRemove">
                  <i class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
                <!-- <img v-if="dialogImageUrl!=''" width="100%" :src="dialogImageUrl" alt=""> -->
              </td>
              <td style="width:100px;padding:10px">栏目类型</td>
              <td>
                <el-select v-model="column.typeId" style="height:30px;width:80%" placeholder="请选择">

                  <el-option label="目录" :key=0 :value=0>
                  </el-option>
                  <el-option label="栏目" :key=1 :value=1>
                  </el-option>
                </el-select>
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">是否外链</td>
              <td>
                <el-select v-model="column.onlyUrl" style="height:30px;width:80%" placeholder="请选择">

                  <el-option label="是" :key=1 :value=1>
                  </el-option>
                  <el-option label="否" :key=0 :value=0>
                  </el-option>
                </el-select>
              </td>
              <td v-show="column.onlyUrl == 1" style="width:100px;padding:10px">外链地址</td>
              <td v-show="column.onlyUrl == 1">
                <el-input v-model="column.contentUrl" placeholder="请输入外链地址" style="width:80%"></el-input>
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">栏目标签</td>
              <td>
                <el-input v-model="column.tags" placeholder="请输入栏目标签" style="width:80%"></el-input>
              </td>
              <td style="width:100px;padding:10px">栏目状态</td>
              <td>
                <el-select v-model="column.readable" style="height:30px;width:80%" placeholder="请选择">

                  <el-option label="可见" :key=1 :value=1>
                  </el-option>
                  <el-option label="不可见" :key=0 :value=0>
                  </el-option>
                </el-select>
              </td>
            </tr>

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">栏目说明</td>
              <td colspan=3>
                <textarea v-model="column.memo" rows="3" cols="20" style="width:90%;height: 150px;margin: 10px 0;">
                          </textarea>
              </td>
            </tr>
          </table>

          <table class="showDetailTable" v-show="show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;">
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">栏目编码</td>
              <td>
                {{column.code}}
              </td>
              <td style="width:100px;padding:10px">栏目名</td>
              <td>
                {{column.name}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">栏目Icon</td>
              <td>
                <img :src="column.icon" alt="" style="width:100px">
              </td>
              <td style="width:100px;padding:10px">栏目类型</td>
              <td>
                <span v-show="column.typeId == 0" style="">目录</span>
                <span v-show="column.typeId == 1" style="">栏目</span>
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">是否外链</td>
              <td>
                <span v-show="column.onlyUrl == 0" style="">否</span>
                <span v-show="column.onlyUrl == 1" style="">是</span>
              </td>
              <td v-show="column.onlyUrl == 1" style="width:100px;padding:10px">外链地址</td>
              <td v-show="column.onlyUrl == 1">

                {{column.contentUrl}}
              </td>
            </tr>
            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">栏目标签</td>
              <td>

                {{column.tags}}
              </td>
              <td style="width:100px;padding:10px">栏目状态</td>
              <td>

                {{column.readable}}
              </td>
            </tr>

            <tr style="border-bottom: 1px solid#ccc;">
              <td style="width:100px;padding:10px">栏目说明</td>
              <td colspan=3>

                {{column.memo}}
              </td>
            </tr>
          </table>

        </el-col>

      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
        <el-button type="primary" style="" @click="saveObj">保存</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
      </span>
  </el-dialog>
  <!--
  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div> -->

</div>
</template>

<script>
import treeTable from '@/components/TreeTable'
import treeToArray from './customEval'
import {
  getCategoryTree,
  putCategory,
  postCategory,
  delcategory,
  uploadFile
} from '@/api/columnManage'
import {
  libupload2
} from '@/api/library'
import {
  getToken,
} from '@/utils/auth'
export default {
  components: {
    treeTable
  },
  data() {
    return {
      func: treeToArray,
      expandAll: false,
      list: [],
      input: '',
      listQuery: {
        page: 1,
        limit: 10,
        objName: ''
      },
      editlm: false,
      addchlm: false,
      editlmID: "",
      addchlmName: "",
      dialogFormVisible: false,
      column: {
        name: '',
        code: '',
        readable: 1,
        typeId: 0,
        icon: '',
        icons: [],
        onlyUrl: 0,
        contentUrl: '',
        tags: '',
        sort: 1,
        memo: '',
        creater: '',
        updater: '',
      },
      dialogImageUrl: '',
      dialogsave: false,
      show: false,
      title: ""
    }
  },
  mounted() {
    this.loadPageList()
  },
  computed: {},
  methods: {
    message(row) {
      this.$message.info(row.event)
    },
    async loadPageList() {
      // if (this.input) {
      //   this.listQuery.objName = this.input
      // } else {
      //   this.listQuery.objName = ''
      // }
      let obj = {}
      obj.objName = this.input
      // obj.readable = ''
      // obj.type = ''

      let {
        data
      } = await getCategoryTree(obj)
      this.list = data
      this.loading = false
    },
    clone(v) {
      var o = v.constructor === Array ? [] : {};
      for (var key in v) {
        o[key] = typeof v[key] === 'Object' ? clone(v[key]) : v[key];
      }

      return o;
    },
    handleEdit(item) {
      this.show = false
      this.dialogFormVisible = true
      this.title = '编辑栏目'
      let obj = this.clone(item) //深复制

      this.editlm = true //编辑
      this.addchlm = false
      this.editlmID = item.id
      if (obj.parent != undefined) {
        this.addchlm = true
        this.addchlmName = obj.parent.name
        obj.parent = null
      }
      if (obj.children != null && obj.children.length > 0) {
        obj.children = null
      }
      this.column = obj
      this.column.icons = [{
        name: 'name.jpg',
        url: obj.icon
      }]//封面赋值显示仅仅

      this.dialogsave = true
    },
    async handleCreate(item, parentId) {
      this.dialogFormVisible = true
      if (item) {
        if (parentId && parentId == "show") {
          this.column = item

          this.column.icons = [{
            name: 'name.jpg',
            url: item.icon
          }]//封面赋值显示仅仅

          this.show = true
          this.title = '查看栏目详情'
        } else {
          this.show = false
          this.title = '添加子栏目'
          this.column = {
            name: '',
            code: '',
            readable: 1,
            typeId: 0,
            icon: '',
            onlyUrl: 0,
            contentUrl: '',
            tags: '',
            sort: 1,
            memo: '',
            creater: '',
            updater: '',
            parentId: parentId
          }
          this.editlm = false

          if (item.parent) {
            this.addchlm = true //添加子栏目
            this.addchlmName = item.parent.name
          } else {
            this.addchlm = false //隐藏父栏目
          }
        }
        this.dialogsave = true //子目录标识
      } else {
        this.dialogsave = false //切换回 添加功能
        this.show = false
        this.title = '新增栏目'
        this.editlm = false
        this.addchlm = false
        this.column = {
          name: '',
          code: '',
          readable: 1,
          typeId: 0,
          icon: '',
          icons: [],
          onlyUrl: 0,
          contentUrl: '',
          tags: '',
          sort: 1,
          memo: '',
          creater: '',
          updater: '',
        }
      }
    },
    async saveObj() {
      if (!this.validata.validacolumn(this.column)) return
      if (this.dialogsave) {
        if (this.title == '添加子栏目') {
          var {
            data,
            success
          } = await postCategory(this.column)
        } else {

          var {
            data,
            success
          } = await putCategory(this.column)
        }
      } else {
        var {
          data,
          success
        } = await postCategory(this.column)
      }
      if (success) {
        let text
        this.dialogsave ? text = '保存成功' : text = '添加成功'
        if (this.title == '添加子栏目') text = '添加成功'
        if (this.title == '编辑栏目') text = '编辑成功'
        if (this.title == '新增栏目') text = '新增成功'
        this.$message({
          message: text,
          type: 'success'
        });
        this.dialogFormVisible = false
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
      }
      this.loadPageList()
    },
    delObj(item) {
      this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        let del = await delcategory(item)
        this.list.splice(this.list.indexOf(item), 1)
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.loadPageList()
        // this.splice(data.id, 1);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    //封面上传
    async uploadSectionFile(param) { //自定义文件上传
      this.column.icons = []
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
      let obj = {
        name: data.fileName,
        url: this.imgBaseUrl+"/jmrhupload" + data.savePath
      }
      this.column.icons.push(obj)
    },
    //封面返回
    // handleAvatarSuccess(res, file) {
    //   let obj = file.response.data
    //
    // },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
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

<style>

</style>
