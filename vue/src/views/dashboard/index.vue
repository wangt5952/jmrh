<template>
<div class="tab-container" style="background: none;">

  <el-tabs v-model="activeName" @tab-click="handleClick" style=" overflow-y: auto;">
    <div label="我的代办（管理员代办）" v-if="userType =='0'" name="1">

      <el-row :gutter="24" style="font-size: 12px;">
        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div @click="Toexpert2('contentManage')" style="flex:1;font-size: 20px;color: #409EFF;cursor: pointer;">{{contentNums || 0}}</div>
          <div style="flex:1;text-align:left;color: #97a8be;cursor: pointer;">待审内容申请</div>
        </el-col>
        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div @click="Toexpert2('activeManage')" style="flex:1;font-size: 20px;color: #409EFF;cursor: pointer;">{{activeNums || 0}}</div>
          <div style="flex:1;text-align:left;color: #97a8be;cursor: pointer;">待审活动申请</div>
        </el-col>
        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div @click="Toexpert('expert')" style="flex:1;font-size: 20px;color: #409EFF;cursor: pointer;">{{expertNums || 0}}</div>
          <div style="flex:1;text-align:left;color: #97a8be;cursor: pointer;">待审专家申请</div>
        </el-col>

        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div @click="Toexpert('enterprise')" style="flex:1;font-size: 20px;color: #409EFF;cursor: pointer;">{{enterpriseNums|| 0}}</div>
          <div style="flex:1;text-align:left;color: #97a8be;cursor: pointer;">待审企业申请</div>
        </el-col>

      </el-row>
      <el-row :gutter="25" style="margin-top:20px;font-size: 12px;">

        <el-col :span="5" style="background: #fff;height: 100px;text-align: center;width:20%;">
          <div style="color: #97a8be;margin-top: 30px;cursor: pointer;">待审高校院所申请</div>
          <div @click="Toexpert('hschool')" style="font-size: 20px;color: #409EFF;margin-top: 10px;color:#f737bf;cursor: pointer;">{{hschoolNums|| 0}}</div>
        </el-col>

        <el-col style="width:1px;background:#fff;height:100px">
          <div style="background: #ccc;margin-top: 35px;width:2px;height:30px">
          </div>
        </el-col>

        <el-col :span="5" style="background: #fff;height: 100px;text-align: center;">
          <div style="color: #97a8be;margin-top: 30px;cursor: pointer;">待审服务机构申请</div>
          <div @click="Toexpert('mechanism')" style="font-size: 20px;color: #409EFF;margin-top: 10px;color:#ffd85a;cursor: pointer;">{{mechanismNums|| 0}}</div>
        </el-col>


        <el-col style="width:1px;background:#fff;height:100px">
          <div style="background: #ccc;margin-top: 35px;width:1px;height:30px">
          </div>
        </el-col>
        <el-col :span="5" style="background: #fff;height: 100px;text-align: center;">
          <div style="color: #97a8be;margin-top: 30px;cursor: pointer;">待审需求申请</div>
          <div @click="Toexpert('demandLibrary')" style="font-size: 20px;color: #409EFF;margin-top: 10px;color:#98eca5;cursor: pointer;">{{demandLibraryNums|| 0}}</div>
        </el-col>


        <el-col style="width:1px;background:#fff;height:100px">
          <div style="background: #ccc;margin-top: 35px;width:2px;height:30px">
          </div>
        </el-col>
        <el-col :span="5" style="background: #fff;height: 100px;text-align: center;">
          <div style="color: #97a8be;margin-top: 30px;cursor: pointer;">待审成果申请</div>
          <div @click="Toexpert('achieveLibrary')" style="font-size: 20px;color: #409EFF;margin-top: 10px;color:#7e95de;cursor: pointer;">{{achieveLibraryNums|| 0}}</div>
        </el-col>
      </el-row>

    </div>
    <!-- <div label="地方管理员代办" name="2">

      <el-row :gutter="24" style="font-size: 12px;">
        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div @click="Toexpert2('activeManage')" style="flex:1;font-size: 20px;color: #409EFF;">{{activeNums || 0}}</div>
          <div style="flex:1;text-align:left;color: #97a8be;">我的活动申请</div>
        </el-col>
        <el-col :span="5" style="display:flex;background: #fff;height: 100px; display: flex;justify-content:center; align-items:Center;text-align:center;margin-right: 20px;">
          <div @click="Toexpert2('contentManage')" style="flex:1;font-size: 20px;color: #409EFF;">{{contentNums || 0}}</div>
          <div style="flex:1;text-align:left;color: #97a8be;">我的内容申请</div>
        </el-col>

      </el-row>

    </div> -->
    <div label="发起的专家申请" v-if="userType =='1'" name="5">

      <el-row :gutter="24" style="font-size: 14px;">

        <el-col :span="5" style="">
          <el-col :span="24" style="padding:0px">
            <div class=" bg" style="height: 150px;">
              <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              加入平台</h3>
              <div class="" style="display:flex;justify-content:center; align-items:Center;height:104px">
                <div class="" style="flex:4;">
                  <div class="" style="flex:4;padding: 10px;">
                    申请状态 <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 2">驳回</span>
                    <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 0">待审核</span>
                    <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 1">审核通过</span>
                  </div>
                  <div v-show="comLibrarycheckStatus == 2" class="" style="flex:4;padding: 10px;">
                    驳回原因 <span style="color:#ccc">{{comLibraryinfo}}</span>
                  </div>

                </div>
                <div class="" style="flex:1;text-align: right;padding-right:20px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="TouserDetail" type="primary">查看/编辑</el-button>
                </div>

              </div>
            </div>
            <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
              <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                    专家对接</h3>
              <div class="" style="display:flex;justify-content:center; align-items:Center;">
                <div class="" style="flex:4;padding: 10px;">
                  <div class="" style="cursor: pointer;">
                    <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                  </div>
                  <div class="" style="cursor: pointer;margin-top:15px">
                    <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                  </div>
                  <div class="" style="cursor: pointer;margin-top:15px">
                    <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                  </div>
                </div>

                <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
                </div>

              </div>
            </div>
          </el-col>
        </el-col>


        <el-col :span="5" style="padding-left:0px;font-size: 14px;">


          <div style="height: 150px;width:100%;">

          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                  需求对接</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>
          </div>
        </el-col>

        <el-col :span="5" style="padding-left:0px;font-size: 14px;">

          <div style="height: 150px;width:100%;">
          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                  成果对接</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="cursor: pointer;flex:4;padding: 10px;">
                <div class="">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>
          </div>
        </el-col>
        <el-col :span="5" style="padding-left:0px;font-size: 14px;">

          <div class=" " style="height: 150px;width:100%;">

          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                          服务对接</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>

          </div>
        </el-col>
      </el-row>
      <el-row :gutter="24" style="font-size: 12px;margin-top:15px">

        <el-col :span="5" style="display:flex;">
          <div class="bg" style="height: 110px;width:100%; display: flex;justify-content:center; align-items:Center;padding-left:0;text-align:center;cursor: pointer;">
            <div @click="Toexpert('expert')" style="flex:1;font-size: 20px;color: #409EFF;">{{expertNums || 0}}</div>
            <div style="flex:1;text-align:left;color: #97a8be;">待处理的专家对接</div>
          </div>
        </el-col>

      </el-row>
    </div>
    <div label="发起的企业申请" v-if="userType =='2'" name="3">

      <el-row :gutter="24" style="font-size: 14px;">

        <el-col :span="5" style="">
          <el-col :span="24" style="padding:0px">
            <div class=" bg" style="height: 150px;">
              <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              加入平台</h3>
              <div class="" style="display:flex;justify-content:center; align-items:Center;height:104px">
                <div class="" style="flex:4;">
                  <div class="" style="flex:4;padding: 10px;">
                    申请状态 <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 2">驳回</span>
                    <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 0">待审核</span>
                    <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 1">审核通过</span>
                  </div>
                  <div v-show="comLibrarycheckStatus == 2" class="" style="flex:4;padding: 10px;">
                    驳回原因 <span style="color:#ccc">{{comLibraryinfo}}</span>
                  </div>

                </div>
                <div class="" style="flex:1;text-align: right;padding-right:20px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="TouserDetail" type="primary">查看/编辑</el-button>
                </div>

              </div>
            </div>
            <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
              <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                专家对接</h3>
              <div class="" style="display:flex;justify-content:center; align-items:Center;">
                <div class="" style="flex:4;padding: 10px;">
                  <div class="" style="cursor: pointer;">
                    <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                  </div>
                  <div class="" style="cursor: pointer;margin-top:15px">
                    <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                  </div>
                  <div class="" style="cursor: pointer;margin-top:15px">
                    <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                  </div>
                </div>

                <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
                </div>

              </div>
            </div>
          </el-col>
        </el-col>


        <el-col :span="5" style="padding-left:0px;font-size: 14px;">

          <div class=" bg" style="height: 150px;width:100%;">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              发布需求</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" @click="Todemand('demandLibrary','1')" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 {{demandpassNums|| 0}}
                </div>
                <div class="" @click="Todemand('demandLibrary','2')" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 {{demandbhNums|| 0}}
                </div>
                <div class="" @click="Todemand('demandLibrary','0')" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 {{demandshNums|| 0}}
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="Todemand('demandLibraryEdit','2')" type="primary">发布新需求</el-button>
              </div>

            </div>
          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              需求对接</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 {{achievepassNums}}
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 {{achievebhNums}}
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 {{achieveshNums}}
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>
          </div>
        </el-col>

        <el-col :span="5" style="padding-left:0px;font-size: 14px;">

          <div class=" bg" style="height: 150px;width:100%;">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              发布成果</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div @click="Todemand('achieveLibrary','1')" class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 {{achievepassNums|| 0}}
                </div>
                <div @click="Todemand('achieveLibrary','2')" class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 {{achievebhNums|| 0}}
                </div>
                <div @click="Todemand('achieveLibrary','0')" class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 {{achieveshNums|| 0}}
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="Todemand('achieveLibraryEdit','0')" type="primary">发布新成果</el-button>
              </div>

            </div>
          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              成果对接</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>
          </div>
        </el-col>
        <el-col :span="5" style="padding-left:0px;font-size: 14px;">

          <div class=" " style="height: 150px;width:100%;">

          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                      服务对接</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>

          </div>
        </el-col>
      </el-row>
      <el-row :gutter="24" style="font-size: 12px;margin-top:15px">
        <el-col :span="5" style="display:flex;">
          <div class="bg" style="height: 110px;width:100%; display: flex;justify-content:center; align-items:Center;text-align:center;cursor: pointer;">
            <div @click="Toexpert2('activeManage')" style="flex:1;font-size: 20px;color: #409EFF;">{{activeNums || 0}}</div>
            <div style="flex:2;text-align:left;color: #97a8be;">待处理的需求对接</div>
          </div>
        </el-col>
        <el-col :span="5" style="display:flex;">
          <div class="bg" style="height: 110px;width:100%; display: flex;justify-content:center; align-items:Center;padding-left:0;text-align:center;cursor: pointer;">
            <div @click="Toexpert2('contentManage')" style="flex:1;font-size: 20px;color: #409EFF;">{{contentNums || 0}}</div>
            <div style="flex:2;text-align:left;color: #97a8be;">待处理的成果对接</div>
          </div>
        </el-col>

      </el-row>
    </div>
    <div label="发起的服务机构申请" v-if="userType =='3'" name="4">

      <el-row :gutter="24" style="font-size: 14px;">

        <el-col :span="5" style="">
          <el-col :span="24" style="padding:0px">
            <div class=" bg" style="height: 150px;">
              <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              加入平台</h3>
              <div class="" style="display:flex;justify-content:center; align-items:Center;height:104px">
                <div class="" style="flex:4;">
                  <div class="" style="flex:4;padding: 10px;">
                    申请状态 <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 2">驳回</span>
                    <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 0">待审核</span>
                    <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 1">审核通过</span>
                  </div>
                  <div v-show="comLibrarycheckStatus == 2" class="" style="flex:4;padding: 10px;">
                    驳回原因 <span style="color:#ccc">{{comLibraryinfo}}</span>
                  </div>

                </div>
                <div class="" style="flex:1;text-align: right;padding-right:20px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="TouserDetail" type="primary">查看/编辑</el-button>
                </div>

              </div>
            </div>
            <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
              <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                  专家对接</h3>
              <div class="" style="display:flex;justify-content:center; align-items:Center;">
                <div class="" style="flex:4;padding: 10px;">
                  <div class="" style="cursor: pointer;">
                    <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                  </div>
                  <div class="" style="cursor: pointer;margin-top:15px">
                    <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                  </div>
                  <div class="" style="cursor: pointer;margin-top:15px">
                    <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                  </div>
                </div>

                <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
                </div>

              </div>
            </div>
          </el-col>
        </el-col>


        <el-col :span="5" style="padding-left:0px;font-size: 14px;">


          <div class=" bg" style="height: 150px;width:100%;">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              发布需求</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" @click="Todemand('demandLibrary','1')" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 {{demandpassNums|| 0}}
                </div>
                <div class="" @click="Todemand('demandLibrary','2')" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 {{demandbhNums|| 0}}
                </div>
                <div class="" @click="Todemand('demandLibrary','0')" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 {{demandshNums|| 0}}
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="Todemand('demandLibraryEdit','2')" type="primary">发布新需求</el-button>
              </div>

            </div>
          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                需求对接</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>
          </div>
        </el-col>

        <el-col :span="5" style="padding-left:0px;font-size: 14px;">

          <div class=" bg" style="height: 150px;width:100%;">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              发布成果</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div @click="Todemand('achieveLibrary','1')" class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 {{achievepassNums|| 0}}
                </div>
                <div @click="Todemand('achieveLibrary','2')" class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 {{achievebhNums|| 0}}
                </div>
                <div @click="Todemand('achieveLibrary','0')" class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 {{achieveshNums|| 0}}
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="Todemand('achieveLibraryEdit','0')" type="primary">发布新成果</el-button>
              </div>

            </div>
          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                成果对接</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>
          </div>
        </el-col>
        <el-col :span="5" style="padding-left:0px;font-size: 14px;">

          <div class=" " style="height: 150px;width:100%;">

          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                        服务对接</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>

          </div>
        </el-col>
      </el-row>
      <el-row :gutter="24" style="font-size: 12px;margin-top:15px">
        <el-col :span="5" style="display:flex;">
          <div class="bg" style="height: 110px;width:100%; display: flex;justify-content:center; align-items:Center;text-align:center;cursor: pointer;">
            <div @click="Toexpert2('activeManage')" style="flex:1;font-size: 20px;color: #409EFF;">{{activeNums || 0}}</div>
            <div style="flex:2;text-align:left;color: #97a8be;">待处理的需求对接</div>
          </div>
        </el-col>
        <el-col :span="5" style="display:flex;">
          <div class="bg" style="height: 110px;width:100%; display: flex;justify-content:center; align-items:Center;padding-left:0;text-align:center;cursor: pointer;">
            <div @click="Toexpert2('contentManage')" style="flex:1;font-size: 20px;color: #409EFF;">{{contentNums || 0}}</div>
            <div style="flex:2;text-align:left;color: #97a8be;">待处理的成果对接</div>
          </div>
        </el-col>


        <el-col :span="5" style="display:flex;">
          <div class="bg" style="height: 110px;width:100%; display: flex;justify-content:center; align-items:Center;padding-left:0;text-align:center;cursor: pointer;">
            <div @click="Toexpert('enterprise')" style="flex:1;font-size: 20px;color: #409EFF;">{{enterpriseNums|| 0}}</div>
            <div style="flex:1;text-align:left;color: #97a8be;">待处理的服务对接</div>
          </div>
        </el-col>

      </el-row>
    </div>


    <div label="发起的高校申请11" v-if="userType =='4'" name="6">

      <el-row :gutter="24" style="font-size: 14px;">

        <el-col :span="5" style="">
          <el-col :span="24" style="padding:0px">
            <div class=" bg" style="height: 150px;">
              <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              加入平台</h3>
              <div class="" style="display:flex;justify-content:center; align-items:Center;height:104px">
                <div class="" style="flex:4;">
                  <div class="" style="flex:4;padding: 10px;">
                    申请状态 <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 2">驳回</span>
                    <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 0">待审核</span>
                    <span style="color:#3a9fff" v-show="comLibrarycheckStatus == 1">审核通过</span>
                  </div>
                  <div v-show="comLibrarycheckStatus == 2" class="" style="flex:4;padding: 10px;">
                    驳回原因 <span style="color:#ccc">{{comLibraryinfo}}</span>
                  </div>

                </div>
                <div class="" style="flex:1;text-align: right;padding-right:20px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="TouserDetail" type="primary">查看/编辑</el-button>
                </div>

              </div>
            </div>
            <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
              <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              专家对接</h3>
              <div class="" style="display:flex;justify-content:center; align-items:Center;">
                <div class="" style="flex:4;padding: 10px;">
                  <div class="" style="cursor: pointer;">
                    <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                  </div>
                  <div class="" style="cursor: pointer;margin-top:15px">
                    <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                  </div>
                  <div class="" style="cursor: pointer;margin-top:15px">
                    <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                  </div>
                </div>

                <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                  <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
                </div>

              </div>
            </div>
          </el-col>
        </el-col>


        <el-col :span="5" style="padding-left:0px;font-size: 14px;">


          <div class=" bg" style="height: 150px;width:100%;">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              发布需求</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" @click="Todemand('demandLibrary','1')" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 {{demandpassNums|| 0}}
                </div>
                <div class="" @click="Todemand('demandLibrary','2')" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 {{demandbhNums|| 0}}
                </div>
                <div class="" @click="Todemand('demandLibrary','0')" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 {{demandshNums|| 0}}
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="Todemand('demandLibraryEdit','2')" type="primary">发布新需求</el-button>
              </div>

            </div>
          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
            需求对接</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>
          </div>
        </el-col>

        <el-col :span="5" style="padding-left:0px;font-size: 14px;">

          <div class=" bg" style="height: 150px;width:100%;">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
              发布成果</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div @click="Todemand('achieveLibrary','1')" class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 {{achievepassNums|| 0}}
                </div>
                <div @click="Todemand('achieveLibrary','2')" class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 {{achievebhNums || 0}}
                </div>
                <div @click="Todemand('achieveLibrary','0')" class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 {{achieveshNums|| 0}}
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="Todemand('achieveLibraryEdit','0')" type="primary">发布新成果</el-button>
              </div>

            </div>
          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
            成果对接</h3>
            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>
          </div>
        </el-col>
        <el-col :span="5" style="padding-left:0px;font-size: 14px;">

          <div class=" " style="height: 150px;width:100%;">

          </div>
          <div class=" bg" style="height: 150px;width:100%;margin-top:15px">
            <h3 style="font-size: 12px;margin: 0;padding: 10px;color: #a09b9b;font-weight: 500;border-bottom: 1px solid#d8d6d6;">
                    服务对接</h3>

            <div class="" style="display:flex;justify-content:center; align-items:Center;">
              <div class="" style="flex:4;padding: 10px;">
                <div class="" style="cursor: pointer;">
                  <i class="iconfont icon-naoling" slot="right"></i> 已通过 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 被驳回 10
                </div>
                <div class="" style="cursor: pointer;margin-top:15px">
                  <i class="iconfont icon-naoling" slot="right"></i> 待审核 10
                </div>
              </div>

              <div class="" style="flex:1;text-align: right;padding-right: 10px;">
                <el-button style=" padding: 5px;font-size: 12px;" @click="handleCreate" type="primary">发起对接</el-button>
              </div>

            </div>

          </div>
        </el-col>
      </el-row>
      <el-row :gutter="24" style="font-size: 12px;margin-top:15px">
        <el-col :span="5" style="display:flex;">
          <div class="bg" style="height: 110px;width:100%; display: flex;justify-content:center; align-items:Center;text-align:center;cursor: pointer;">
            <div @click="Toexpert2('activeManage')" style="flex:1;font-size: 20px;color: #409EFF;">{{activeNums || 0}}</div>
            <div style="flex:2;text-align:left;color: #97a8be;">待处理的需求对接</div>
          </div>
        </el-col>
        <el-col :span="5" style="display:flex;">
          <div class="bg" style="height: 110px;width:100%; display: flex;justify-content:center; align-items:Center;padding-left:0;text-align:center;cursor: pointer;">
            <div @click="Toexpert2('contentManage')" style="flex:1;font-size: 20px;color: #409EFF;">{{contentNums || 0}}</div>
            <div style="flex:2;text-align:left;color: #97a8be;">待处理的成果对接</div>
          </div>
        </el-col>


      </el-row>
    </div>


    <el-dialog title="发起对接" :visible.sync="dialogFormVisible" width="50%" top='9%'>

      <el-form class="" label-width="30%" style="text-align:left">


        <el-row :gutter="24">
          <el-col :span="24">
            <table v-show="!show" cellpadding=0 cellspacing=0 border="0" style="width:100%;border: 1px solid#ccc;height: 350px;">

              <tr style="border-bottom: 1px solid#ccc;">
                <td style="width:150px;padding:20px">&nbsp;&nbsp;分类类型</td>
                <td>
                  <el-select v-model="meeting.typeId" style="height:30px;width:80%" placeholder="请选择">
                    <el-option label="专家对接" :key=0 :value=0>
                    </el-option>
                    <el-option label="需求对接" :key=1 :value=1>
                    </el-option>
                    <el-option label="成果对接" :key=2 :value=2>
                    </el-option>
                    <el-option label="服务对接" :key=3 :value=3>
                    </el-option>
                  </el-select>
                </td>
              </tr>
              <tr style="border-bottom: 1px solid#ccc;">
                <td style="width:150px;padding:20px;">&nbsp;&nbsp;发起联系人姓名</td>
                <td>
                  <el-input v-model="meeting.fcn" placeholder="请输入发起联系人姓名" style="width:80%"></el-input>
                </td>
                <td style="width:150px;padding:20px;">&nbsp;&nbsp;发起联系人电话</td>
                <td>
                  <el-input v-model="meeting.fcp" placeholder="请输入发起联系人电话" style="width:80%"></el-input>
                </td>
              </tr>

              <tr style="border-bottom: 1px solid#ccc;">
                <td style="width:150px;padding:20px;">&nbsp;&nbsp;发起联系人邮箱</td>
                <td>
                  <el-input v-model="meeting.fcm" placeholder="请输入发起联系人邮箱" style="width:80%"></el-input>
                </td>
                <td style="width:150px;padding:20px;">&nbsp;&nbsp;发起联系人地址</td>
                <td>
                  <el-input v-model="meeting.fca" placeholder="请输入发起联系人地址" style="width:80%"></el-input>
                </td>
              </tr>

              <tr style="border-bottom: 1px solid#ccc;">
                <td style="width:150px;padding:20px;">&nbsp;&nbsp;内容ID</td>
                <td>
                  <el-input v-model="meeting.appId" placeholder="请输入内容ID" style="width:80%"></el-input>
                </td>
                <td style="width:150px;padding:20px;">&nbsp;&nbsp;申请说明</td>
                <td>
                  <el-input v-model="meeting.appNote" placeholder="请输入申请说明" style="width:80%"></el-input>
                </td>
              </tr>
            </table>

          </el-col>

        </el-row>
      </el-form>

      <span v-show="!show" slot="footer" class="dialog-footer">
          <el-button type="primary" style="" @click="saveObj">保存</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
        </span>
    </el-dialog>
  </el-tabs>

</div>
</template>


<script>
import LineChart1 from './components/LineChart1'
import LineChart2 from './components/LineChart2'
import LineChart3 from './components/LineChart3'
import {
  getSubmitLibSum,
  getSubmitLib
} from '@/api/dashboard'

import {
  saveMeeting
} from '@/api/buttManage'

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
      expertNums: '',
      enterpriseNums: '',
      mechanismNums: '',
      hschoolNums: '',
      achieveLibraryNums: '',
      demandLibraryNums: '',
      activeNums: '',
      contentNums: '',
      userType: '',

      comLibraryNums: '',
      comLibrarycheckStatus: '',
      comLibraryinfo: '',
      demandshNums: '',
      demandpassNums: '',
      demandbhNums: '',
      achieveshNums: '',
      achievepassNums: '',
      achievebhNums: '',
      meeting: {
        typeId: '',
        fcn: '',
        fcp: '',
        fcm: '',
        fca: '',
        appId: '',
      },
      dialogFormVisible :false
    }
  },
  mounted() {
    this.userType = window.sessionStorage.getItem('userType')
    if (this.userType == 0) {
      this.loadPageList()
    } else {
      this.loadSubmitLib()
    }
  },
  methods: {
    async loadPageList() {
      let {
        data,
        success
      } = await getSubmitLibSum()
      if (data.length > 0) {
        for (var i in data) {
          if (data[i].formType == 1) {
            this.expertNums = data[i].count
          }
          if (data[i].formType == 2) {
            this.enterpriseNums = data[i].count
          }
          if (data[i].formType == 3) {
            this.mechanismNums = data[i].count
          }
          if (data[i].formType == 4) {
            this.hschoolNums = data[i].count
          }
          if (data[i].formType == 6) {
            this.achieveLibraryNums = data[i].count
          }
          if (data[i].formType == 7) {
            this.demandLibraryNums = data[i].count
          }
          if (data[i].formType == 8) {
            this.activeNums = data[i].count
          }
          if (data[i].formType == 9) {
            this.contentNums = data[i].count
          }

        }

      } else {
        this.expertNums = 0
        this.enterpriseNums = 0
        this.mechanismNums = 0
        this.hschoolNums = 0
        this.achieveLibraryNums = 0
        this.demandLibraryNums = 0
        this.activeNums = 0
        this.contentNums = 0
      }
    },
    async loadSubmitLib() {
      let {
        data,
        success
      } = await getSubmitLib()
      if (data.length > 0) {

        for (var i in data) {
          if (data[i].formType == 2) {
            this.comLibraryNums = data[i].count
            this.comLibrarycheckStatus = data[i].checkStatus
            if (data[i].checkStatus == 2) {
              this.comLibraryinfo = data[i].info
            }
          }
          if (data[i].formType == 6 && data[i].checkStatus == 0) {
            this.demandshNums = data[i].count
          }
          if (data[i].formType == 6 && data[i].checkStatus == 1) {
            this.demandpassNums = data[i].count
          }
          if (data[i].formType == 6 && data[i].checkStatus == 2) {
            this.demandbhNums = data[i].count
          }
          if (data[i].formType == 7 && data[i].checkStatus == 0) {
            this.achieveshNums = data[i].count
          }
          if (data[i].formType == 7 && data[i].checkStatus == 1) {
            this.achievepassNums = data[i].count
          }
          if (data[i].formType == 7 && data[i].checkStatus == 2) {
            this.achievebhNums = data[i].count
          }



        }

      } else {
        this.expertNums = 0
        this.enterpriseNums = 0
        this.mechanismNums = 0
        this.hschoolNums = 0
        this.achieveLibraryNums = 0
        this.demandLibraryNums = 0
        this.activeNums = 0
        this.contentNums = 0
      }


    },

    async handleCreate() {
      this.dialogFormVisible = true
      this.meeting = {
        typeId: '',
        fcn: '',
        fcp: '',
        fcm: '',
        fca: '',
        appId: '',
      }
    },
    async saveObj() {
      // if (!this.validata.validameeting(this.meeting)) return
        var {
          data,
          success
        } = await saveMeeting(this.meeting)
      if (success) {
        this.$message({
          message: message,
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

    TouserDetail(path, num) {
      this.$router.push({
        path: '/index/userDetail'
      })
    },
    Todemand(path, num) {
      this.$router.push({
        path: '/manage3/' + path,
        query: {
          checkStatus: num,
        }

      })
    },
    Toexpert(path) {
      this.$router.push({
        path: '/manage3/' + path,
        query: {
          checkStatus: 0,
        }

      })
    },

    Toexpert2(path) {
      this.$router.push({
        path: '/manage2/' + path,
        query: {
          checkStatus: 0,
        }

      })
    }

  }
}
</script>
