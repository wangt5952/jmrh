<template>
<div class="tab-container" style="background: #fff;">
  <div class="textl" style="font-size:14px;height:100%;padding: 40px;">

    <el-row :gutter="24" style="font-size: 12px;height:100%">

      <el-col :span="10" style="background: #fff; cursor: pointer;height:100%">
        <div class="" style="width: 300px;height: 100px;margin: 0 auto;background: #ccc;display:flex;justify-content:center; align-items:Center;text-align:center;">
          <span style="flex:1;text-align:right;color: #fff;font-size: 24px;">总数</span>
          <span @click="Toexpert2('activeManage')" style="flex:1;font-size: 24px;color: #409EFF;text-align:center;">{{total || 0}}</span>
        </div>
        <div class="" style="width: 450px;height:300px;margin: 0 auto;display:flex;justify-content:center; align-items:Center;text-align:center;padding-top:8%">
          <line-chart :chartData="info" v-on:childByValue="childByValue"></line-chart>
        </div>
      </el-col>
      <el-col :span="12" style="background: #fff; margin-right: 20px;cursor: pointer;height:100%">
        <div class="txselect" style="height:100px;display:flex">
          <div class="" style="flex:10">
            <div class="">

              <el-select v-model="input.types" style="width:80px;height:30px" placeholder="类别">
                <el-option label="专家" :key=1 :value=1>
                </el-option>
                <el-option label="企业" :key=2 :value=2>
                </el-option>
                <el-option label="服务机构" :key=3 :value=3>
                </el-option>
                <el-option label="高校院所" :key=4 :value=4>
                </el-option>
              </el-select>
                <el-button style="" @click="loadData" type="primary">统计</el-button>

            </div>
              <div class="" style="display: flex;">
              <el-checkbox-group v-model="input.type" style="margin:10px 0px;float:right">
                <el-checkbox label="1">类别</el-checkbox>
              </el-checkbox-group>

            </div>
          </div>
        </div>
        <div class="" style="height:340px;padding-top: 8%;">
          <pie-chart :chartData="input"  ref='piec'></pie-chart>
        </div>
      </el-col>


    </el-row>


  </div>
</div>

</div>
</template>

<script>
import lineChart from './components/lineChart'
import pieChart from './components/pieChart'
import 'vue-area-linkage/dist/index.css'; // v2 or higher
import {
  pca,
  pcaa
} from "area-data";
import {
  gettjlib,
} from '@/api/statistics'
export default {
  components: {
    pieChart,
    lineChart,
  },
  data() {
    return {
      input: {
        type: true,
        types: '',
      },
      pca: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      arrValue1: [],
      arrValue2: [],
      arrValue3: [],
      total: '',
    }
  },

  mounted() {
    this.loadOneTree()
  },
  methods: {
    loadData(){
      if(!this.input.type){
        this.$message({
          message: '统计项（复选框）必选一个！',
          type: 'warning'
        });
        return
      }
       this.$refs.piec.initChart(this.input);
    },
    loadOneTree() {
      let pcadata = this.pca
      let arr = []
      for (var i in pcadata) {
        if (i == '86') {
          let obj = {}
          obj.date = i
          obj.value = pcadata[i]
          arr.push(obj)
        }
      }
      arr = arr[0].value
      for (var j in arr) {
        let obj = {}
        obj.name = j
        obj.value = arr[j]
        this.arrValue1.push(obj)
      }
    },
    loadtwoTree(oneData) {
      this.arrValue2 = []
      let pcadata = this.pca
      let arr = []
      for (var i in pcadata) {
        if (i == oneData) {
          let obj = {}
          obj.date = i
          obj.value = pcadata[i]
          arr.push(obj)
        }
      }
      arr = arr[0].value
      for (var j in arr) {
        let obj = {}
        obj.name = j
        obj.value = arr[j]
        this.arrValue2.push(obj)
      }
    },
    loadThreeTree(oneData) {
      this.arrValue3 = []
      let pcadata = this.pca
      let arr = []
      for (var i in pcadata) {
        if (i == oneData) {
          let obj = {}
          obj.date = i
          obj.value = pcadata[i]
          arr.push(obj)
        }
      }
      if (arr.length > 0) {
        arr = arr[0].value
        for (var j in arr) {
          let obj = {}
          obj.name = j
          obj.value = arr[j]
          this.arrValue3.push(obj)
        }
      }

    },
    childByValue: function (childValue) {
         // childValue就是子组件传过来的值
         this.total = childValue
       }
  }
}
</script>

<style lang="scss">
.txselect .el-checkbox+.el-checkbox {
    margin-right: 30px;
    margin-left: 0;
}
.txselect .el-checkbox {
    margin-right: 30px;
    margin-left: 0;
}
.txselect .el-select {
    margin: 5px;
}
.txselect .el-input--suffix .el-input__inner {
    padding-right: 20px;
}
</style>
