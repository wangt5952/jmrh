<template>
<div class="tab-container" style="background: #fff;">
  <div class="textl" style="font-size:14px;height:100%;padding: 40px;">

    <el-row :gutter="24" style="font-size: 12px;height:100%">

      <el-col :span="11" style="background: #fff; margin-right: 20px;cursor: pointer;height:100%">
        <div class="" style="width: 300px;height: 100px;margin: 0 auto;background: #ccc;display:flex;justify-content:center; align-items:Center;text-align:center;">
          <span style="flex:1;text-align:right;color: #fff;font-size: 24px;">总数</span>
          <span @click="Toexpert2('activeManage')" style="flex:1;font-size: 24px;color: #409EFF;text-align:center;">{{activeNums || 0}}</span>
        </div>
        <div class="" style="width: 450px;height:70%;margin: 0 auto;display:flex;justify-content:center; align-items:Center;text-align:center;padding-top:2%">
          <line-chart></line-chart>
        </div>
      </el-col>
      <el-col :span="11" style="background: #fff; margin-right: 20px;cursor: pointer;height:100%">
        <div class="txselect" style="height:100px;display:flex">
          <div class="" style="flex:10">
            <el-select v-model="input.site1" @change="loadtwoTree(input.site1)" style="width:85px;height:30px" placeholder="省">
              <el-option v-for="item in arrValue1" :label=item.value :key=item.value :value=item.name>
              </el-option>
            </el-select>
            <el-select v-model="input.site2" @change="loadThreeTree(input.site2)" style="width:85px;height:30px" placeholder="市">
              <el-option v-for="item in arrValue2" :label=item.value :key=item.value :value=item.name>
              </el-option>
            </el-select>
            <el-select v-model="input.site3" style="width:85px;height:30px" placeholder="区">
              <el-option v-for="item in arrValue3" :label=item.value :key=item.value :value=item.name>
              </el-option>
            </el-select>
            <el-select v-model="input.nature" style="width:80px;height:30px" placeholder="领域">
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
            <el-select v-model="input.sex" style="width:80px;height:30px" placeholder="性别">
              <el-option label="男" :key=-1 :value=-1>
              </el-option>
              <el-option label="女" :key=0 :value=0>
              </el-option>
            </el-select>

            <el-select v-model="input.education" style="width:100px;height:30px" placeholder="请选择学历">
              <el-option label="请选择" key="" value="">
              </el-option>
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

            <el-select v-model="input.degree" style="width:100px;height:30px" placeholder="请选择学位">
              <el-option label="请选择" key="" value="">
              </el-option>
              <el-option label="学士" key="1" value="1">
              </el-option>
              <el-option label="硕士" key="2" value="2">
              </el-option>
              <el-option label="博士" key="3" value="3">
              </el-option>
              <el-option label="其他" key="99" value="99">
              </el-option>
            </el-select>

            <el-checkbox-group v-model="input.sites1" style="margin:10px 5px;">
              <el-checkbox label="1">省</el-checkbox>
            </el-checkbox-group>
            <el-checkbox-group v-model="input.site2s" style="margin:10px 5px;">
              <el-checkbox label="2">市</el-checkbox>
            </el-checkbox-group>
            <el-checkbox-group v-model="input.site3s" style="margin:10px 5px;">
              <el-checkbox label="3">区 </el-checkbox>
            </el-checkbox-group>
            <el-checkbox-group v-model="input.sexs" style="margin:10px 5px;">
              <el-checkbox label="5">性别</el-checkbox>
            </el-checkbox-group>
            <el-checkbox-group v-model="input.education" style="margin:10px 5px;">
              <el-checkbox label="6">学历</el-checkbox>
            </el-checkbox-group>
            <el-checkbox-group v-model="input.degrees" style="margin:10px 5px;">
              <el-checkbox label="6">学位</el-checkbox>
            </el-checkbox-group>
          </div>
          <div class="" style="flex:1;margin:5px;">
            <el-button style="" @click="loadPageList" type="primary">统计</el-button>
          </div>
        </div>
        <div class="" style="height:80%">
          <pie-chart :chartData="input"></pie-chart>
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
        site1: [],
        site2: [],
        site3: [],
        domain: '',
        sex: '',
        education: '',
        degree: '',
        site1s: [],
        site2s: [],
        site3s: [],
        domains: '',
        sexs: '',
        educations: '',
        degrees: '',
      },
      pca: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      arrValue1: [],
      arrValue2: [],
      arrValue3: [],
    }
  },
  created() {

    // this.loadPageList()
  },
  mounted() {
    this.loadOneTree()
  },
  methods: {
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
      debugger
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
    // async loadPageList() {
    //   let {
    //     data,
    //     success
    //   } = await gettjlib()
    //   if (success) {
    //     this.info = data.month
    //   }
    // },
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
