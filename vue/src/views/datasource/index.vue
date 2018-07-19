<template>
<div class="tab-container">
  <el-row>
    <el-col :span="8">
      <div class="changeGriddata">
        <li>我的数据源</li>
        <li icon="el-icon-edit" class="el-icon-edit" @click="changeData(item.id)" :id = 'item.id' v-for='item in changeList'>{{item.name}}</li>
      </div>
    </el-col>
    <el-col :span="16">
      <div class="changeGriddata">
        <li style="margin-bottom: 0;">本地探索
          <div class="paddingb  paddingr" style="float: right;">
            <el-button type="primary" >新建 <i class="el-icon-sync"></i></el-button>
            <el-button type="primary">导入 <i class="el-icon-sync"></i></el-button>
            <el-button type="primary">上传文件 <i class="el-icon-upload1"></i></el-button>
          </div></li>

      </div>
      <div class="grid-content bg-purple-light" style="margin: 0 5px  5px 5px;">
        <div class="grid-content bg-purple">
          <!-- <el-table :data="list" v-loading.body="listLoading" element-loading-text="拼命加载中" border fit> -->
          <el-table :data="list"  border fit>
            <el-table-column align="center" label='名称'>
              <template scope="scope">
                  {{scope.$index}}
                </template>
            </el-table-column>
            <el-table-column label="最近同步时间" align="center">
              <template scope="scope">
                  {{scope.row.author}}
                </template>
            </el-table-column>
            <el-table-column label="状态" align="center">
              <template scope="scope">
                  {{scope.row.author}}
                </template>
            </el-table-column>
            <el-table-column label="大小" align="center">
              <template scope="scope">
                  {{scope.row.author}}
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
              <template scope="scope">
                  <span>{{scope.row.author}}</span>
                </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </el-col>
  </el-row>

  <el-dialog title="新建数据源" :visible.sync="outerVisible">

    <div class="overflowa" style="height:10%">
      <el-card class="box-card">
        <el-dialog custom-class="dialogInner" title="添加数据源" :visible.sync="innerVisible" :modal='false' append-to-body='true'>
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
import {
  getList
} from '@/api/table';
// <create-data></create-data>
// import createData from './components/createData'
export default {
  data() {
    return {
      list: null,
      listLoading: true,
      outerVisible: false,
      innerVisible: false,
      changeList:[{id:'file',name:'探索空间'},{id:'12345',name:'会员'}],
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
      states: ["Alabama", "Alaska", "Arizona",
        "Arkansas", "California", "Colorado",
        "Connecticut", "Delaware", "Florida",
        "Georgia", "Hawaii", "Idaho", "Illinois",
        "Indiana", "Iowa", "Kansas", "Kentucky",
        "Louisiana", "Maine", "Maryland",
        "Massachusetts", "Michigan", "Minnesota",
        "Mississippi", "Missouri", "Montana",
        "Nebraska", "Nevada", "New Hampshire",
        "New Jersey", "New Mexico", "New York",
        "North Carolina", "North Dakota", "Ohio",
        "Oklahoma", "Oregon", "Pennsylvania",
        "Rhode Island", "South Carolina",
        "South Dakota", "Tennessee", "Texas",
        "Utah", "Vermont", "Virginia",
        "Washington", "West Virginia", "Wisconsin",
        "Wyoming"
      ],
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

      // this.listLoading = true;
      // getList(this.listQuery).then(response => {
      //   this.list = response.data.items;
      //   //this.list = '';
      //   this.listLoading = false;
      // })
    },
    changeData(id){
      this.$router.push({ path:'datasource',query: { dsType: id}});
      this.changeList.forEach((item,index) => {
        if(id == item.id){
          document.getElementById(id).style.background = "#d0caca"
        }else{
          document.getElementById(item.id).style.background = "#fff"
        }
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
@import '../../styles/index.scss'; // 全局自定义的css样式
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

.el-dialog {
  width: 60%;
}

.component-item {
  min-height: 100px;
}

.el-form-item {
  margin-bottom: 12px;
}
.changeGriddata li{
  font-size: 14px;
  list-style-type: none;
  background: #fff;
  padding: 10px;
  margin: 5px
}
.el-button {
    padding: 3px 7px;
}
.el-table, .el-table__expanded-cell {
    background-color: #fff;
    height: 700px;
}
.tab-container[data-v-481e6aaf] {
    padding: 30px;
    margin: 0
}
.el-table thead {
  color: rgba(0,0,0,.4);
    font-weight:normal;
    font-size: 12px;
}
</style>
