<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <!-- <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button> -->
      <el-button style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">新建</el-button>
      <el-button style="margin-left: 10px;" @click="pathTomoudle" type="primary" icon="el-icon-edit">数据结构映射</el-button>

    </div>
  </div>


  <el-table class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

    <el-table-column type="index" align="center" label="编码">
    </el-table-column>
    <el-table-column align="center" label="主数据模型">
      <template slot-scope="scope">
                      <span>{{ scope.row.model.mdmModel }}</span>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="中文名">
      <template slot-scope="scope">
                      <span>{{ scope.row.tableLabel }}</span>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="数据库表名">
      <template slot-scope="scope">
                      <span>{{ scope.row.tableName }}</span>
                  </template>
    </el-table-column>
    <el-table-column align="center" label="创建时间">
      <template slot-scope="scope">
                    <span>{{ scope.row.createTime }}</span>
                </template>
    </el-table-column>

    <el-table-column align="center" label="创建人">
      <template slot-scope="scope">
                    <span>{{ scope.row.creater }}</span>
                </template>
    </el-table-column>

    <!-- <el-table-column align="center" label="备注">
      <template slot-scope="scope">
                    <span>{{ scope.row.columnDefinitions }}</span>
                </template>
    </el-table-column> -->
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
                    <div style="margin:2% 2% 2% 2%">
                        <el-button size="small" @click="handleEdit(scope.row,'edit')" type=""  class="el-icon-edit colorblue borderblue"></el-button>
                        <el-button size="small" @click="handleEdit(scope.row,'del')"  type=""  class="el-icon-delete colorred borderred"></el-button>
                    </div>
                </template>
    </el-table-column>

  </el-table>





  <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="75%" top='2%'>
    <el-button v-if='tabShow' size="small" @click="addTab()">
      新建从表
    </el-button>
    <el-tabs @tab-click="handleClick" v-model="editableTabsValue" type="border-card">
      <el-tab-pane v-for="tagData in listTag" :key="tagData.id" :label="tagData.tableLabel">

        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="24" v-show='titleS'>
            <el-col :span="8">
              <el-form-item :span="12" label="模型">
                <el-select :disabled='tabShow' v-model="tagData.modelId" placeholder="请选择" style="width:80%">
                  <el-option v-for="(item,key) in modellist" :key="item.id" :label="item.mdmModel" :value="item.id">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item :span="12" label="数据库表名">
                <el-input v-model="tagData.tableName" placeholder="请输入内容" style="width:80%"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item :span="12" label="中文名">
                <el-input v-model="tagData.tableLabel" placeholder="请输入内容" style="width:80%"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-button style="margin-left: 10px;" @click="lineCreate(tagData.columnDefinitions)" type="" icon="el-icon-plus">新增字段</el-button>

          <el-row :gutter="24">

            <el-table class="tableH" :data="tagData.columnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

              <el-table-column align="center" label="字段">
                <template slot-scope="scope">
                                <span>
                                  <input  :disabled="tabShow && scope.row.index <= nowlistLength" type="text" v-model="scope.row.name">
                                </span>
                            </template>
              </el-table-column>
              </el-table-column>
              <el-table-column align="center" label="字段名称">
                <template slot-scope="scope">
                  <input type="text" v-model="scope.row.columnComment">
                          </template>
              </el-table-column>

              <el-table-column align="center" label="数据类型">
                <template slot-scope="scope">
                  <select  v-model="scope.row.dataType">
                      <option v-for="(item,key) in DataTypeMapitems" :value ="key">{{item}}</option>
                    </select>
                </template>
              </el-table-column>

              <el-table-column align="center" label="数据长度(精度)">
                <template slot-scope="scope">
                  <input v-if="scope.row.dataType == 'NUMBER'" type="number" v-model="scope.row.dataTypeLength">
                  <input v-else-if="scope.row.dataType == 'DATE'" type="date" v-model="scope.row.dataTypeLength">
                  <input v-else type="text" v-model="scope.row.dataTypeLength">
                          </template>
              </el-table-column>

              <el-table-column align="center" label="约束">
                <template slot-scope="scope">
                  <select  v-model="scope.row.constraints[0]">
                      <option v-for="(item,key) in ColumnConstraintMapitems" :value ="key">{{item}}</option>
                    </select>
                          </template>
              </el-table-column>
              <el-table-column align="center" label="操作">
                <template slot-scope="scope">
                              <div  v-if="scope.row.name != 'ID'"  style="margin:2% 2% 2% 2%">
                                  <el-button size="small" @click="handleModelEdit(scope.row)"  type=""  class="el-icon-delete colorred borderred"></el-button>
                              </div>
                          </template>
              </el-table-column>
            </el-table>
          </el-row>
        </el-form>
        <div class="dialog-footer textc ">
          <el-button type="primary" v-if="dialogadd == true" @click="addCreate()">提交</el-button>
          <el-button type="primary" v-if="dialogsave == true" @click="saveCreate(obj,tagData.columnDefinitions)">修改</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
        </div>

      </el-tab-pane>

    </el-tabs>

  </el-dialog>


</div>
</template>

<script>
import {
  validaNULL
} from '@/utils/validate'
import {
  getAll,
  addTable,
  editTable,
  delTable,
  getBasicData,
  getAllone,
  addTableRelation,
  getAlldel
} from '@/api/modelDiy'

import {
  modelAll
} from '@/api/modelDiy'
export default {
  data() {
    return {
      input: '',
      bank: '1',
      listone: [],
      list: [],
      modellist: [],
      indexNum: '',
      listTag: [],
      listadd: [{
        name: '',
        columnComment: '',
        dataTypeMap: '',
        dataTypeLength: '',
        constraints: []
      }],
      titleS: true,
      DataTypeMapitems: '',
      ColumnConstraintMapitems: '',
      timeType: '1',
      dialogStatus: '',
      dialogFormVisibleone: false,
      dialogFormVisible: false,
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
        create: '新建'
      },
      obj: {
        modelId: '',
        tableName: '',
        tableLabel: ''
      },
      listLength: [],
      nowlistLength: '',
      tabIndex: 0,
      tabIndexNow: false, //从表禁用id
      tabShow: false,
      editableTabsValue: ''
    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()
    //console.log(validaNULL('1'))
  },
  computed: {},
  methods: {
    handleClick(tab, event) {
      this.tabIndex = parseInt(tab.index)
      this.nowlistLength = this.listLength[this.tabIndex]
      this.tabIndex > 0 ? this.titleS = false : this.titleS = true
      if (parseInt(tab.index) >= 1 && this.tabIndexNow) {

        this.tabShow = false
      } else {
        if (this.dialogStatus == 'update') {
          this.tabShow = true
        }
      }
    },
    lineCreate(data) {
      data.push({
        index: this.nowlistLength + 1,
        name: '',
        columnComment: '',
        dataTypeMap: {},
        dataTypeLength: '',
        constraints: []
      })
      this._newVals()
    },
    async loadPageList() {
      let data = await getAll()
      this.list = data.data
      this.loadBasicData()
      this.loadmodelAll()
    },
    async loadBasicData() {
      let data = await getBasicData()
      this.DataTypeMapitems = data.data.DataTypeMap
      this.ColumnConstraintMapitems = data.data.ColumnConstraintMap
    },
    async loadmodelAll() {
      let data = await modelAll()
      this.modellist = data.data
    },
    async handleCreate() {
      this.editableTabsValue = '0'
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.dialogadd = true
      this.dialogsave = false
      this.tabShow = false
      this.listTag = [{
        modelId: '',
        tableName: '',
        tableLabel: '新建主表',
        columnDefinitions: [{
          name: '',
          columnComment: '',
          dataTypeMap: '',
          dataTypeLength: '',
          constraints: []
        }]
      }]
    },
    addTab(targetName) {
      let newTabName = this.tabIndex + 1;
      this.tabIndexNow = true
      this.listTag.push({
        tableLabel: '从表',
        id: newTabName,
        columnDefinitions: [{
          name: '',
          columnComment: '',
          dataTypeMap: [],
          dataTypeLength: '',
          constraints: []
        }]
      })
      this._newVals()
      // this.editableTabsValue = newTabName;
    },
    pathTomoudle() {
      this.$router.push({
        path: '/main_yewu/mapping'
      })

    },
    async ondep1Change(val) {
      if (val) {
        val = val
      }
    },
    async addCreate() {
      if (!this.validata.validamodelDiy(this.listTag[0])) return
      this.listTag[0].isMenu = true
      var listData = this.listTag[0].columnDefinitions
      for (let i = 0; i < listData.length; i++) {
        listData[i].dataTypeMap = JSON.parse('{"' + listData[i].dataType + '":"' + listData[i].dataTypeLength + '"}')
      }
      let data = await addTable(this.listTag[0])


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
    async saveCreate() {
      let obj = this.listTag
      let saveData = this._changeVals(obj)
      if (obj.length > 1 && this.tabIndex > 0) {
        let objadata = {}
        objadata.table1 = obj[0].id
        objadata.tableDefinition2.columnDefinitions = obj[1].columnDefinitions
        objadata.tableDefinition2.tableLabel = obj[0].tableLabel
        objadata.tableDefinition2.tableName = obj[0].tableName
        objadata.tableDefinition2.modelId = obj[0].modelId

        var data = await addTableRelation(objadata)
      } else {
        let endData = {}
        let arr = []
        for (var x in saveData) {
          if (x != 'tableName') {
            arr.push(saveData[x])
          }
        }
        endData.columnDefinitions = arr

        endData.id = this.listTag[this.tabIndex].id
        endData.modelId = this.listTag[this.tabIndex].modelId
        endData.tableName = this.listTag[this.tabIndex].tableName
        endData.tableLabel = this.listTag[this.tabIndex].tableLabel

        if (!this.validata.validamodelDiy(endData)) return

        var data = await editTable(endData)
      }
      if (data.code === 10000) {
        this.$message({
          message: '修改成功',
          type: 'success'
        });
        this.dialogFormVisible = false
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
      }

    },

    async handleModelEdit(data, type) {
      this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        let saveData = {}
        let arr = []
        arr.push(data)
        saveData.columnDefinitions = arr
        saveData.id = this.listTag[this.tabIndex].id
        saveData.modelId = this.listTag[this.tabIndex].modelId
        saveData.tableName = this.listTag[this.tabIndex].tableName
        saveData.tableLabel = this.listTag[this.tabIndex].tableLabel
        if (saveData.columnDefinitions[0].index <= this.nowlistLength) {
          let del = await delTable(saveData)
          if (del.success) {
            this.listTag[this.tabIndex].columnDefinitions.splice(this.listTag[this.tabIndex].columnDefinitions.indexOf(data), 1)
          }
        } else {
          this.listTag[this.tabIndex].columnDefinitions.splice(this.listTag[this.tabIndex].columnDefinitions.indexOf(data), 1)
        }
        this.$message({
          type: 'success',
          message: '删除成功'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    async handleEdit(data, type) {
      if (type === 'edit') {
        this.editableTabsValue = '0'
        this.dialogStatus = 'update'
        this.dialogsave = true
        this.dialogadd = false
        this.dialogFormVisible = true
        this.tabShow = true
        this.titleS = true
        this.tabIndexNow = false
        let getone = await getAllone(data.id)
        let loadNum = getone.data.table
        this.listTag = getone.data.table
        let arr = []
        for (var x in loadNum) {
          arr.push(loadNum[x].columnDefinitions.length)
        }
        this.listLength = arr
        this.nowlistLength = this.listLength[0]
        this._newVals()
        // this.obj = data

      } else if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await getAlldel(data.id)
          if (del.success) {
            this.list.splice(this.list.indexOf(data), 1)
          }
          this.$message({
            type: 'success',
            message: del.message
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
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
    _newVals() {
      var deviceContent = this.listTag[this.tabIndex];

      var obj = [];
      for (var x in deviceContent) {
        if (x == 'columnDefinitions') {
          let columnData = deviceContent[x]
          for (var m in columnData) {
            var data2 = {};
            data2.name = columnData[m].index;
            data2.value = columnData[m];
            obj.push(data2);
          }
          continue
        }
        var data = {};
        data.name = x;
        data.value = deviceContent[x];
        obj.push(data);
      }

      // debugger
      localStorage.setItem('valueDoms', JSON.stringify(obj));
    },
    _changeVals(x) {
      var deviceContent = x[this.tabIndex];

      var obj = [];
      for (var x in deviceContent) {
        if (x == 'columnDefinitions') {
          let columnData = deviceContent[x]
          for (var m in columnData) {
            var data2 = {};
            data2.name = columnData[m].index;
            data2.value = columnData[m];
            obj.push(data2);
          }
          continue
        }
        var data = {};
        data.name = x;
        data.value = deviceContent[x];
        obj.push(data);
      }

      var _vals = JSON.parse(localStorage.getItem('valueDoms')),
        changeObj = {};
        debugger
      for (var i = 0; i < obj.length; i++) {
        var _id = obj[i].name,
          _val = JSON.stringify(obj[i].value);
        for (var j = 0; j < _vals.length; j++) {
          var _id2 = _vals[j].name,
            _val2 = JSON.stringify(_vals[j].value);
          if (_id === _id2) {
            if (_val != _val2) {
              changeObj[_id] = JSON.parse(_val);
            }
          }
        }
      }

      this._newVals()
      return changeObj;
    }
  },
  watch: {
    // tabIndex() {
    //   debugger
    // }
  },
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>
.tab-container {
  padding: 30px;
}

.el-table__body-wrapper {
  overflow: auto;
  position: relative;
  height: 89%;
}

.el-dialog__header {
  border-bottom: 1px solid#d8d6d6;
  padding-bottom: 10px;
}

/*
.el-dialog__title {
  line-height: 1;
  font-size: 25px;
  font-weight: 700;
  text-align: center;
  color: #1f2d3d;
} */

.el-dialog__body {
  margin-top: 30px;
  padding: 0px 20px;
  color: #48576a;
  font-size: 14px;
}

.el-dialog__footer {
  text-align: center;
}

.aaa {
  width: 95%
}

.el-dialog__wrapper {
  overflow: none;
}
</style>
