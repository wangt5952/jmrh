<template>
<div class="tab-container" style="background: none;">

  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="我的代办" name="1">

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

    </el-tab-pane>
    <el-tab-pane label="发起的活动申请" name="2">
      <el-row :gutter="24" style="font-size: 15px;">

        <el-col :span="6" style="padding-left:0px">
          <el-col :span="24" style="padding:0px">
            <div class=" bg">
              <line-chart1></line-chart1>
            </div>
          </el-col>
        </el-col>
        <el-col :span="12" style="padding-left:0px">
          <el-col :span="24" style="padding:0px">
            <div class=" bg">
              <line-chart2></line-chart2>
            </div>
          </el-col>
        </el-col>
      </el-row>


    </el-tab-pane>
    <el-tab-pane label="发起的专家申请" name="3">
      <el-row :gutter="24" style="">

        <el-col :span="6" style="">
          <el-col :span="24" style="padding:0px">
            <div class=" bg" style="height: 415px;">
              <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                我发起的专家申请</h3>

              <line-chart3></line-chart3>
            </div>
          </el-col>
        </el-col>


        <el-col :span="7" style="padding-left:0px;font-size: 14px;">
          <div class=" bg" style="height: 200px;width:100%">
            <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              我发起的专家对接申请</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
              </div>
              <div class="" style="flex:1;text-align: right;padding: 10px;">
              </div>
            </div>
          </div>
          <div class=" bg" style="height: 200px;width:100%;margin-top:15px">
            <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              我发起的成果对接申请</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
              </div>
              <div class="" style="flex:1;text-align: right;padding: 10px;">
              </div>
            </div>
          </div>
        </el-col>

        <el-col :span="7" style="padding-left:0px;font-size: 14px;">
          <div class=" bg" style="height: 200px;width:100%">
            <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              我发起的需求对接申请</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
              </div>
              <div class="" style="flex:1;text-align: right;padding: 10px;">
              </div>
            </div>
          </div>
          <div class=" bg" style="height: 200px;width:100%;margin-top:15px">
            <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              向我发起的专家对接申请</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
              </div>
              <div class="" style="flex:1;text-align: right;padding: 10px;">
              </div>
            </div>
          </div>
        </el-col>
      </el-row>

    </el-tab-pane>
    <el-tab-pane label="发起的企业申请" name="4">

      <el-row :gutter="24" style="font-size: 14px;">

        <el-col :span="6" style="">
          <el-col :span="24" style="padding:0px">
            <div class=" bg" style="height: 315px;">
              <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                我发起的企业申请</h3>
                <div class="" style="display:flex;justify-content:center; align-items:Center;">
                  <div class="" style="flex:4;padding: 10px;">
                    申请状态  <span style="color:#3a9fff">驳回</span>
                  </div>
                  <div class="" style="flex:1;text-align: right;padding-right:20px;">
                    <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
                  </div>
                </div>
                <div class="" style="display:flex;justify-content:center; align-items:Center;">
                  <div class="" style="flex:4;padding: 10px;">
                    驳回原因  <span style="color:#ccc">原因。。。。。</span>
                  </div>

                </div>
            </div>
            <div class=" bg" style="height: 200px;width:100%;margin-top:15px">
              <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                我发起的需求对接申请</h3>

              <div class="" style="display:flex;justify-content:center; align-items:Center;">
                <div class="" style="flex:4;padding: 10px;">
                    <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
                </div>
              </div>
              <div class="" style="display:flex;justify-content:center; align-items:Center;">
                <div class="" style="flex:4;padding: 10px;">
                    <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
                </div>
              </div>
              <div class="" style="display:flex;justify-content:center; align-items:Center;">
                <div class="" style="flex:4;padding: 10px;">
                    <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
                <div class="" style="flex:1;text-align: right;padding: 10px;">
                </div>
              </div>
            </div>
          </el-col>
        </el-col>


        <el-col :span="7" style="padding-left:0px;font-size: 14px;">
          <div class=" bg" style="width:100%;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
            <div style="flex:1;font-size: 30px;color: #409EFF;">68</div>
            <div style="flex:1;text-align:left;color: #97a8be;">向我发起的需求对接申请</div>
          </div>

          <div class=" bg" style="height: 200px;width:100%;margin-top:15px">
            <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              我发起的成果申请</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
              </div>
              <div class="" style="flex:1;text-align: right;padding: 10px;">
              </div>
            </div>
          </div>
          <div class=" bg" style="height: 200px;width:100%;margin-top:15px">
            <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              我发起的成果对接申请</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
              </div>
              <div class="" style="flex:1;text-align: right;padding: 10px;">
              </div>
            </div>
          </div>
        </el-col>

        <el-col :span="7" style="padding-left:0px;font-size: 14px;">
          <div class=" bg" style="width:100%;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
            <div style="flex:1;font-size: 30px;color: #409EFF;">68</div>
            <div style="flex:1;text-align:left;color: #97a8be;">向我发起的成果对接申请</div>
          </div>
          <div class=" bg" style="height: 200px;width:100%;margin-top:15px">
            <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              我发起的专家对接申请</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
              </div>
              <div class="" style="flex:1;text-align: right;padding: 10px;">
              </div>
            </div>
          </div>
          <div class=" bg" style="height: 200px;width:100%;margin-top:15px">
            <h3 style="font-size: 16px;margin: 0;padding: 15px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              向我发起的专家对接申请</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
              </div>
              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="" type="primary" >查看</el-button>
              </div>
            </div>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
              </div>
              <div class="" style="flex:1;text-align: right;padding: 10px;">
              </div>
            </div>
          </div>
        </el-col>
      </el-row>

    </el-tab-pane>
  </el-tabs>

</div>
</template>


<script>
import LineChart1 from './components/LineChart1'
import LineChart2 from './components/LineChart2'
import LineChart3 from './components/LineChart3'

export default {
  components: {
    LineChart1,
    LineChart2,
    LineChart3
  },
  data() {
    return {
      chart: null,
      activeName: '4',
    }
  },
  mounted() {

  },


}
</script>
