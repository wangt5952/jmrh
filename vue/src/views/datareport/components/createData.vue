<template>
<div class="tab-container">
  <el-dialog title="新建数据源" :visible.sync="outerVisible">

    <div class="overflowa" style="height:10%">
    <el-card class="box-card">
      <el-dialog custom-class="dialogInner"  title="添加数据源" :visible.sync="innerVisible" :modal='false' append-to-body='true'>
        <el-form :label-position="labelPosition" label-width="100px" :model="formLabelAlign">
          <el-form-item label="显示名称：">
            <el-input v-model="formLabelAlign.name"></el-input>
          </el-form-item>
          <el-form-item label="数据库地址：">
            <el-input v-model="formLabelAlign.region"></el-input>
          </el-form-item>
          <el-form-item label="端口：">
            <el-input v-model="formLabelAlign.type"></el-input>
          </el-form-item>
          <el-form-item label="数据库：">
            <el-input v-model="formLabelAlign.type"></el-input>
          </el-form-item>
          <el-form-item label="用户名：">
            <el-input v-model="formLabelAlign.type"></el-input>
          </el-form-item>
          <el-form-item label="密码：">
            <el-input v-model="formLabelAlign.type"></el-input>
          </el-form-item>

        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="outerVisible = false">添加</el-button>
          <el-button @click="outerVisible = false">关闭</el-button>
          <el-button @click="outerVisible = false">连接测试</el-button>
        </div>
      </el-dialog>
      <div slot="header" class="clearfix">
        <span>来自云数据库</span>
      </div>
      <el-row :gutter="20" style="margin-top:10px;">
        <el-col :span="6">
          <div @click="innerVisible = true" class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>MySQL</span></div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>SQL Server</span></div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>Analytic DB</span></div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>PPAS</span></div>
          </div>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>来自自建数据源</span>
      </div>
      <el-row :gutter="20" style="margin-top:10px;">
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>MySQL</span></div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>SQL Server</span></div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>Analytic DB</span></div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>PPAS</span></div>
          </div>
        </el-col>
      </el-row>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>来自其他</span>
      </div>
      <el-row :gutter="20" style="margin-top:10px;">
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>MySQL</span></div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>SQL Server</span></div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>Analytic DB</span></div>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="component-item textc">
            <div style="font-size: 30px;"><i class="el-icon-document"></i></div>
            <div class="textc"><span>PPAS</span></div>
          </div>
        </el-col>
      </el-row>
    </el-card>
    </div>
    <div slot="footer" class="dialog-footer">
      <el-button @click="outerVisible = false">关闭</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>

export default {
  data() {
    return {
      list: null,
      listLoading: true,
      outerVisible: false,
      innerVisible: false,
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '120px',
      options4: [],
      value9: [],
      list: [],
      loading: false,
      labelPosition: 'right',
      formLabelAlign: {
        name: '',
        region: '',
        type: ''
      }
    }
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      this.listLoading = true;
      getList(this.listQuery).then(response => {
        this.list = response.data.items;
        //this.list = '';
        this.listLoading = false;
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
          this.options4 = this.list.filter(item => {
            return item.label.toLowerCase()
              .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.options4 = [];
      }
    }

  }
};
</script>
<style lang="scss">
@import '../../../styles/index.scss'; // 全局自定义的css样式
</style>
<style scoped>
.tab-container {
  margin: 30px;
}

.el-table {
  border: 0px;
}

.el-table-column {
  border: 0px;
}

.box-card {
  margin-top: 10px;
}
.el-dialog{
  width: 60%;
}
.component-item {
  min-height: 100px;
}
.el-form-item {
    margin-bottom: 12px;
}
</style>
