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
              <el-select v-model="input.sites1" @change="loadtwoTree(input.sites1)" style="width:85px;height:30px" placeholder="省">
                <el-option v-for="item in arrValue1" :label=item.value :key=item.value :value=item.name>
                </el-option>
              </el-select>
              <el-select v-model="input.sites2" @change="loadThreeTree(input.sites2)" style="width:85px;height:30px" placeholder="市">
                <el-option v-for="item in arrValue2" :label=item.value :key=item.value :value=item.name>
                </el-option>
              </el-select>
              <el-select v-model="input.sites3" style="width:85px;height:30px" placeholder="区">
                <el-option v-for="item in arrValue3" :label=item.value :key=item.value :value=item.name>
                </el-option>
              </el-select>
              <el-select v-model="input.domains" style="width:80px;height:30px" placeholder="领域">
                <el-option label="智能装备" :key=1 :value=1>
                </el-option>
                <el-option label="电子信息" :key=2 :value=2>
                </el-option>
                <el-option label="新材料" :key=3 :value=3>
                </el-option>
                <el-option label="航空航天" :key=4 :value=4>
                </el-option>
                <el-option label="生物技术与新医药" :key=5 :value=5>
                </el-option>
                <el-option label="能源与环保" :key=6 :value=6>
                </el-option>
                <el-option label="管理" :key=7 :value=7>
                </el-option>
                <el-option label="其他" :key=99 :value=99>
                </el-option>
              </el-select>

              <el-select v-model="input.stages" style="width:110px;height:30px" placeholder="所处阶段">
                <el-option label="小学" key="1" value="1">
                </el-option>
                <el-option label="初中" key="2" value="2">
                </el-option>
                <el-option label="高中" key="3" value="3">
                </el-option>
                <el-option label="大专" key="4" value="4">
                </el-option>
                <el-option label="本科" key="5" value="5">
                </el-option>
                <el-option label="研究生" key="6" value="6">
                </el-option>
                <el-option label="博士" key="7" value="7">
                </el-option>
                <el-option label="其他" key="99" value="99">
                </el-option>
              </el-select>
                <el-button style="" @click="loadData" type="primary">统计</el-button>
            </div>
              <div class="" style="display: flex;">
              <el-checkbox-group v-model="input.site1" style="margin:10px 0px;float:right">
                <el-checkbox label="1">省</el-checkbox>
              </el-checkbox-group>
              <el-checkbox-group v-model="input.site2" style="margin:10px 0px;float:right">
                <el-checkbox label="2">市</el-checkbox>
              </el-checkbox-group>
              <el-checkbox-group v-model="input.site3" style="margin:10px 0px;float:right">
                <el-checkbox label="3">区 </el-checkbox>
              </el-checkbox-group>
              <el-checkbox-group v-model="input.domain" style="margin:10px 0px;float:right">
                <el-checkbox label="5">领域</el-checkbox>
              </el-checkbox-group>
              <el-checkbox-group v-model="input.stage" style="margin:10px 0px;float:right">
                <el-checkbox label="5">所处阶段</el-checkbox>
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
        site1: true,
        site2: false,
        site3: false,
        domain: false,
        stage: false,

        sites1: '',
        sites2: '',
        sites3: '',
        domains: '',
        stages: '',
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
      if(!this.input.site1  && !this.input.site2  && !this.input.site3 && !this.input.domain && !this.input.stage){
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
