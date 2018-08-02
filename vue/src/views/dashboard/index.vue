<template>
<div class="tab-container" style="background: none;">

  <!-- <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="我的代办" name="1"> -->

      <el-row :gutter="24" style="font-size: 15px;">
        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div style="flex:1;font-size: 30px;color: #409EFF;">68</div>
          <div style="flex:1;text-align:left;color: #97a8be;">待审栏目申请</div>
        </el-col>
        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div style="flex:1;font-size: 30px;color: #409EFF;">189</div>
          <div style="flex:1;text-align:left;color: #97a8be;">待审内容申请</div>
        </el-col>
        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div style="flex:1;font-size: 30px;color: #409EFF;">68</div>
          <div style="flex:1;text-align:left;color: #97a8be;">待审专家申请</div>
        </el-col>

        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div style="flex:1;font-size: 30px;color: #409EFF;">68</div>
          <div style="flex:1;text-align:left;color: #97a8be;">待审企业申请</div>
        </el-col>

      </el-row>
      <el-row :gutter="25" style="margin-top:20px;">
        <el-col :span="3" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;">
          <div style="flex:1;font-size: 30px;color: #409EFF;margin：0 auto">

            <div style="width: 40px;height: 40px;border-radius: 5px;display: flex;justify-content: center;align-items: center;font-weight: bold;border:2px solid #409eff;margin: 0 auto;">
              <i class="iconfont icon-naoling" slot="right"></i>
            </div>
          </div>

          <div style="flex:1;text-align:left;color: #363535;font-size: 18px;">更多代办</div>
        </el-col>
        <el-col :span="4" style="background: #fff;height: 100px;text-align: center;width:20%">
          <div style="color: #97a8be;margin-top: 30px;">待审高校院所申请</div>
          <div style="font-size: 20px;color: #409EFF;margin-top: 10px;color:#f737bf">189</div>
        </el-col>

        <el-col style="width:1px;background:#fff;height:100px">
          <div style="background: #ccc;margin-top: 35px;width:2px;height:30px">
          </div>
        </el-col>

        <el-col :span="4" style="background: #fff;height: 100px;text-align: center;">
          <div style="color: #97a8be;margin-top: 30px;">待审服务机构申请</div>
          <div style="font-size: 20px;color: #409EFF;margin-top: 10px;color:#ffd85a">68</div>
        </el-col>


        <el-col style="width:1px;background:#fff;height:100px">
          <div style="background: #ccc;margin-top: 35px;width:1px;height:30px">
          </div>
        </el-col>
        <el-col :span="4" style="background: #fff;height: 100px;text-align: center;">
          <div style="color: #97a8be;margin-top: 30px;">待审需求申请</div>
          <div style="font-size: 20px;color: #409EFF;margin-top: 10px;color:#98eca5">68</div>
        </el-col>


        <el-col style="width:1px;background:#fff;height:100px">
          <div style="background: #ccc;margin-top: 35px;width:2px;height:30px">
          </div>
        </el-col>
        <el-col :span="4" style="background: #fff;height: 100px;text-align: center;">
          <div style="color: #97a8be;margin-top: 30px;">待审成果申请</div>
          <div style="font-size: 20px;color: #409EFF;margin-top: 10px;color:#7e95de">68</div>
        </el-col>
      </el-row>

    <!-- </el-tab-pane>
    <el-tab-pane label="发起的活动申请" name="2">
      <el-row :gutter="24" style="font-size: 15px;">

        <el-col :span="9" style="padding-left:0px">
            <el-col :span="24"style="padding:0px">
              <div class="maintextc fa13 maintexth200 bg" >
                   <line-chart1 ></line-chart1>
                  </div>
            </el-col>
        </el-col>

      </el-row>


    </el-tab-pane>
    <el-tab-pane label="发起的专家申请" name="3"></el-tab-pane>
    <el-tab-pane label="发起的企业申请" name="4"></el-tab-pane>
  </el-tabs> -->

</div>
</template>


<script>

import LineChart1 from './components/LineChart1'

export default {
  components: {
    LineChart1,
  },
  data() {
    return {
      chart: null,
      activeName: '1',
    }
  },
  mounted() {

  },


}
</script>
