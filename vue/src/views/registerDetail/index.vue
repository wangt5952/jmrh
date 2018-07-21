<template>
<div class="register-container" style="background: #1896d2">
  <div class="content">

    <!-- <div class="left-content" :style="{'background-image': `url('${leftImage}')`}"></div> -->

    <div class="detail-content">
      <el-card class="box-card">

        <div slot="header" class="clearfix">
          <span> <span v-if="radioData == '1'">个人</span> <span v-if="radioData == '2'">企业</span><span v-if="radioData == '3'">服务</span>注册</span>
        </div>

        <div class="item">
          <div v-if="radioData == '1'" class="">
            <el-form class="" label-width="30%" style="text-align:left">
              <el-row :gutter="20">
                <el-col :span="10">
                  <el-form-item label="姓名">
                    <el-input placeholder="请输入姓名" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="性别">
                    <el-select style="width:100px" placeholder="请选择">
                      <el-option label="男" key="1" value='1'>
                      </el-option>
                      <el-option label="女" key="0" value='0'>
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="出生日期">
                    <el-date-picker v-model="dateValue" type="date" placeholder="选择日期">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="所在地区">
                    <area-cascader :level="1" v-model="selected" :data="pcaa"></area-cascader>
                    <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                  </el-form-item>
                  <el-form-item label="联系地址">
                    <el-input placeholder="请输入联系地址" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="邮箱">
                    <el-input placeholder="请输入邮箱" style="width:80%"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="10">
                  <el-form-item label="身份证号">
                    <el-input placeholder="请输入身份证号" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="上传身份证正面">
                    <div>
                      <!--这是正面照-->
                      <div class="photo">
                        <a href="javascript">
                      <input type="file" id="IdCard">
                      </a>
                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="上传身份证反面">
                    <div>
                      <!--这是背面照-->
                      <div class="photo photo1">
                        <a href="javascript">
                        <input type="file" id="IdCardbg">
                            </a>
                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="手持身份证">
                    <div>
                      <!--这是背面照-->
                      <div class="photo photo1">
                        <a href="javascript">
                        <input type="file" id="IdCardbg">
                            </a>
                      </div>
                    </div>
                  </el-form-item>

                  <el-form-item label="手机号">
                    <el-input placeholder="请输入手机号" style="width:80%"></el-input>
                  </el-form-item>

                </el-col>

              </el-row>
              <el-row>
                <div style="text-align:center">
                  <el-button type="primary" style="width: 120px;">完善并保存</el-button>
                  <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
                </div>
              </el-row>
            </el-form>
          </div>


          <div v-if="radioData == '2'" class="">


            <el-tabs type="border-card">
              <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i> 联系人信息</span>

                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="10">
                      <el-form-item label="姓名">
                        <el-input placeholder="请输入姓名" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职务">
                        <el-input placeholder="请输入职务" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">

                      <el-form-item label="所在地区">
                        <area-cascader :level="1" v-model="selected" :data="pcaa"></area-cascader>
                        <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                      </el-form-item>
                      <el-form-item label="联系地址">
                        <el-input placeholder="请输入联系地址" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮编">
                        <el-input placeholder="请输入邮编" style="width:80%"></el-input>
                      </el-form-item>

                    </el-col>

                  </el-row>
                </el-form>


              </el-tab-pane>
              <el-tab-pane label="企业法人信息">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="姓名">
                        <el-input placeholder="请输入姓名" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职务">
                        <el-input placeholder="请输入职务" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="企业信息">
                <el-form class="" label-width="40%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="企业规模（注册资金）">
                        <el-radio v-model="registerFinance" label="1">小于2000万（含）</el-radio>
                        <el-radio v-model="registerFinance" label="2">2000-5000万</el-radio>
                        <el-radio v-model="registerFinance" label="3">5000-1亿（含）</el-radio>
                        <el-radio v-model="registerFinance" label="4">1亿-2亿（含）</el-radio>
                        <el-radio v-model="registerFinance" label="5">2亿-4亿（含）</el-radio>
                        <el-radio v-model="registerFinance" label="6">4亿及以上（含）</el-radio>
                      </el-form-item>
                      <el-form-item label="注册时间">
                        <el-date-picker v-model="registerValue" type="date" placeholder="选择日期">
                        </el-date-picker>
                      </el-form-item>
                      <el-form-item label="注册类型">
                        <el-radio v-model="registerType" label="1">内资企业</el-radio>
                        <el-radio v-model="registerType" label="2">合资企业</el-radio>
                        <el-radio v-model="registerType" label="3">外资企业</el-radio>
                      </el-form-item>
                      <el-form-item label="是否高新技术企业">
                        <el-radio v-model="registerH" label="1">是</el-radio>
                        <el-radio v-model="registerH" label="2">否</el-radio>
                      </el-form-item>
                      <el-form-item label="企业所在地性质">
                        <el-radio v-model="registerSite" label="1">国家级高新区 </el-radio>
                        <el-radio v-model="registerSite" label="2">省级高新区</el-radio>
                        <el-radio v-model="registerSite" label="3">国家级经开区 </el-radio>
                        <el-radio v-model="registerSite" label="4">省级经开区</el-radio>
                        <el-radio v-model="registerSite" label="5">其他</el-radio>
                      </el-form-item>
                      <el-form-item label="所在国家高新区">
                        <el-radio v-model="registerHSite" label="1">南京高新区</el-radio>
                        <el-radio v-model="registerHSite" label="2">无锡高新区</el-radio>
                        <el-radio v-model="registerHSite" label="3">昆山高新区 </el-radio>
                        <el-radio v-model="registerHSite" label="4">徐州高新区</el-radio>
                        <el-radio v-model="registerHSite" label="5">其他</el-radio>
                      </el-form-item>
                      <el-form-item label="是否上市">
                        <el-radio v-model="registerMarket" label="1">是</el-radio>
                        <el-radio v-model="registerMarket" label="2">否</el-radio>
                      </el-form-item>
                      <el-form-item label="上市地点">
                        <el-radio v-model="registerMarkeSite" label="1">上交所</el-radio>
                        <el-radio v-model="registerMarkeSite" label="2">深交所</el-radio>
                        <el-radio v-model="registerMarkeSite" label="3">新三板 </el-radio>
                        <el-radio v-model="registerMarkeSite" label="4">港交所</el-radio>
                        <el-radio v-model="registerMarkeSite" label="5">主版</el-radio>
                        <el-radio v-model="registerMarkeSite" label="6">中小板</el-radio>
                        <el-radio v-model="registerMarkeSite" label="7">创业板</el-radio>
                      </el-form-item>
                      <el-form-item label="所属领域">
                        <el-radio v-model="registerField" label="1">智能装备</el-radio>
                        <el-radio v-model="registerField" label="2">电子信息</el-radio>
                        <el-radio v-model="registerField" label="3">新材料 </el-radio>
                        <el-radio v-model="registerField" label="4">航空航天</el-radio>
                        <el-radio v-model="registerField" label="5">生物技术与新医药</el-radio>
                        <el-radio v-model="registerField" label="6">能源与环保</el-radio>
                        <el-radio v-model="registerField" label="7">其他</el-radio>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>


              </el-tab-pane>
              <el-tab-pane label="服务机构">

                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">
                      <el-form-item label="单位名称">
                        <el-input placeholder="请输入单位名称" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="所在地区">
                        <area-cascader :level="1" v-model="selected" :data="pcaa"></area-cascader>
                        <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                      </el-form-item>
                      <el-form-item label="联系地址">
                        <el-input placeholder="请输入联系地址" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮编">
                        <el-input placeholder="请输入邮编" style="width:80%"></el-input>
                      </el-form-item>

                      <el-form-item label="机构性质">
                        <el-radio v-model="registerNature" label="1">企业</el-radio>
                        <el-radio v-model="registerNature" label="2">科研院所</el-radio>
                        <el-radio v-model="registerNature" label="3">高等院校 </el-radio>
                        <el-radio v-model="registerNature" label="4">其他</el-radio>
                      </el-form-item>

                      <el-form-item label="机构类别">
                        <el-radio v-model="registerNatureType" label="1">研究开发</el-radio>
                        <el-radio v-model="registerNatureType" label="2">科技投融资</el-radio>
                        <el-radio v-model="registerNatureType" label="3">技术转移 </el-radio>
                        <el-radio v-model="registerNatureType" label="4">检验检测</el-radio>
                        <el-radio v-model="registerNatureType" label="5">创业孵化</el-radio>
                        <el-radio v-model="registerNatureType" label="6">知识产权</el-radio>
                        <el-radio v-model="registerNatureType" label="7">科技评估</el-radio>
                        <el-radio v-model="registerNatureType" label="8">标准认证</el-radio>
                        <el-radio v-model="registerNatureType" label="9">管理咨询</el-radio>
                        <el-radio v-model="registerNatureType" label="10">综合科技服务</el-radio>
                        <el-radio v-model="registerNatureType" label="11">其他</el-radio>
                      </el-form-item>
                    </el-col>

                  </el-row>
                </el-form>

              </el-tab-pane>
            </el-tabs>


            <el-row>
              <div style="text-align:center;margin-top: 20px;">
                <el-button type="primary" style="width: 120px;">完善并保存</el-button>
                <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
              </div>
            </el-row>

          </div>


          <div v-if="radioData == '3'" class="">
            <el-tabs type="border-card">
              <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i> 机构联系人</span>
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="姓名">
                        <el-input placeholder="请输入姓名" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职务">
                        <el-input placeholder="请输入职务" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>

              </el-tab-pane>
              <el-tab-pane label="机构法定代表人">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="姓名">
                        <el-input placeholder="请输入姓名" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="机构信息">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">
                      <el-form-item label="单位名称">
                        <el-input placeholder="请输入单位名称" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="所在地区">
                        <area-cascader :level="1" v-model="selected" :data="pcaa"></area-cascader>
                        <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                      </el-form-item>
                      <el-form-item label="联系地址">
                        <el-input placeholder="请输入联系地址" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮编">
                        <el-input placeholder="请输入邮编" style="width:80%"></el-input>
                      </el-form-item>

                      <el-form-item label="机构性质">
                        <el-radio v-model="registerNature" label="1">企业</el-radio>
                        <el-radio v-model="registerNature" label="2">科研院所</el-radio>
                        <el-radio v-model="registerNature" label="3">高等院校 </el-radio>
                        <el-radio v-model="registerNature" label="4">其他</el-radio>
                      </el-form-item>

                      <el-form-item label="机构类别">
                        <el-radio v-model="registerNatureType" label="1">研究开发</el-radio>
                        <el-radio v-model="registerNatureType" label="2">科技投融资</el-radio>
                        <el-radio v-model="registerNatureType" label="3">技术转移 </el-radio>
                        <el-radio v-model="registerNatureType" label="4">检验检测</el-radio>
                        <el-radio v-model="registerNatureType" label="5">创业孵化</el-radio>
                        <el-radio v-model="registerNatureType" label="6">知识产权</el-radio>
                        <el-radio v-model="registerNatureType" label="7">科技评估</el-radio>
                        <el-radio v-model="registerNatureType" label="8">标准认证</el-radio>
                        <el-radio v-model="registerNatureType" label="9">管理咨询</el-radio>
                        <el-radio v-model="registerNatureType" label="10">综合科技服务</el-radio>
                        <el-radio v-model="registerNatureType" label="11">其他</el-radio>
                      </el-form-item>
                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>

              <el-tab-pane label="人才队伍情况">

                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">
                      <el-form-item label="人员总数">
                        <el-input placeholder="请输入人员总数" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="本科及以上学历人员占比">
                        <el-input placeholder="请输入人员本科及以上学历人员占比" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="中高级职称以上人员占比">
                        <el-input placeholder="请输入中高级职称以上人员占比编" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                </el-form>

              </el-tab-pane>

              <el-tab-pane label="项目情况">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">
                      <el-form-item label="资助方式">
                        <el-radio v-model="registerSupport" label="1">拨款</el-radio>
                        <el-radio v-model="registerSupport" label="2">贴息</el-radio>
                        <el-radio v-model="registerSupport" label="3">减免税 </el-radio>
                        <el-radio v-model="registerSupport" label="4">其他</el-radio>
                        <el-radio v-model="registerSupport" label="5">以上全无</el-radio>
                      </el-form-item>
                      <el-form-item label="执行情况">
                        <el-radio v-model="registerImplement" label="1">申请</el-radio>
                        <el-radio v-model="registerImplement" label="2">在研</el-radio>
                        <el-radio v-model="registerImplement" label="3">验收/结题 </el-radio>
                      </el-form-item>


                    </el-col>
                  </el-row>
                  <!-- <el-form-item label=""> -->

                  <div style="margin:0 auto">

                    <el-button size="small" @click="addProject()">
                      添加项目
                    </el-button>
                    <el-table class="tableH" :data="registerPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                      <el-table-column align="center" label="项目名称">
                        <template slot-scope="scope">
                        <input  type="text" v-model="scope.row.name">
                                        </template>
                      </el-table-column>
                      <el-table-column align="center" label="起止时间">
                        <template slot-scope="scope">
                                              <span>
                                                <input  type="text" v-model="scope.row.time">
                                              </span>
                                          </template>
                      </el-table-column>
                      <el-table-column align="center" label="项目来源">
                        <template slot-scope="scope">
                        <input  type="text" v-model="scope.row.source">
                                        </template>
                      </el-table-column>
                    </el-table>
                    </div>
                  <!-- </el-form-item> -->

                </el-form>
              </el-tab-pane>
              <el-tab-pane label="所获资质及荣誉">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="22">
                    <!-- <el-form-item label=""> -->

                    <div style="margin:0 auto">

                      <el-button size="small" @click="addProject()">
                        添加荣誉
                      </el-button>
                      <el-table class="tableH" :data="registerPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                        <el-table-column align="center" label="时间">
                          <template slot-scope="scope">
                          <input  type="text" v-model="scope.row.name">
                                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="获得时间">
                          <template slot-scope="scope">
                                                <span>
                                                  <input  type="text" v-model="scope.row.time">
                                                </span>
                                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="颁发机构">
                          <template slot-scope="scope">
                          <input  type="text" v-model="scope.row.source">
                                          </template>
                        </el-table-column>
                      </el-table>
                      </div>
                    <!-- </el-form-item> -->

                  </el-row>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="主要工作成效">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="22">
                    <!-- <el-form-item label=""> -->

                    <div style="margin:0 auto">

                      <el-button size="small" @click="addProject()">
                        添加成效
                      </el-button>
                      <el-table class="tableH" :data="registerPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                        <el-table-column align="center" label="服务项目名称">
                          <template slot-scope="scope">
                          <input  type="text" v-model="scope.row.name">
                                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="服务对象">
                          <template slot-scope="scope">
                                                <span>
                                                  <input  type="text" v-model="scope.row.time">
                                                </span>
                                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="服务时间">
                          <template slot-scope="scope">
                          <input  type="text" v-model="scope.row.source">
                                          </template>
                        </el-table-column>
                      </el-table>
                      </div>
                    <!-- </el-form-item> -->
                  </el-row>
                </el-form>
              </el-tab-pane>
            </el-tabs>
            <el-row>
              <div style="text-align:center;margin-top: 20px;">
                <el-button type="primary" style="width: 120px;">完善并保存</el-button>
                <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
              </div>
            </el-row>
          </div>

        </div>

      </el-card>
    </div>
    <!-- <div class="center-content"></div> -->
    <!-- <div class="right-top"></div> -->
    <!-- <div class="left-bottom"></div> -->
  </div>
</div>
</template>

<script>
import {
  pca,
  pcaa
} from "area-data";
export default {
  data() {
    return {
      dateValue: '',
      radioData: '3',
      registerFinance: '1',
      registerType: '1',
      registerH: '1',
      registerSite: '1',
      registerHSite: '1',
      registerMarket: '1',
      registerMarkeSite: '1',
      registerField: '1',
      registerValue: '',
      registerNature: '',
      registerNatureType: '',
      registerImplement: '',
      registerSupport: '',
      registerPorcolumnDefinitions: [{
        name: '',
        time: '',
        source: ''
      }],
      loginVerify: { // 普通登录
        phone: '',
        verity: ''
      },
      selected: [],
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区

    }
  },
  mounted() {
    if (this.$route.params.radioData) {
      this.radioData = this.$route.params.radioData
    }
  },
  methods: {

    addProject() {
      this.registerPorcolumnDefinitions.push({
        name: '',
        time: '',
        source: ''
      })
      this._newVals()
      // this.editableTabsValue = newTabName;
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    toLogin() {
      this.$router.push({
        path: '/login'
      })
    },
    toRegister() {
      if (this.login_method == 'first') {
        if (!this.validata.validatoRegistere(this.registerForm)) return
        this.$message({
          message: '1',
          type: 'warning'
        });
      } else if (this.login_method == 'second') {
        if (!this.validata.validatoRegisterp(this.loginVerify)) return
        this.$message({
          message: '2',
          type: 'warning'
        });
      }

    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss">
@import "src/styles/mixin.scss";
.area-select .area-selected-trigger {
    position: relative;
    display: block;
    font-size: 14px;
    cursor: pointer;
    margin: 0;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    height: 100%;
    padding: 0 0 0 12px;
}
.area-select {
    position: relative;
    display: inline-block;
    vertical-align: top;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    height: 40px;
    cursor: pointer;
    background: #fff;
    border-radius: 4px;
    border: 1px solid #e1e2e6;
}
.el-tabs__header {
    z-index: 999;
}
.el-card__body {
    height: 600px;
}
.el-tabs--border-card {
    height: 500px;

}
.register-container {
    width: 100%;
    height: 100vh;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center center;
    background-attachment: fixed;
    position: relative;
    z-index: 1;
    overflow: hidden;
    font-size: 20px;
    .el-radio {
        margin: 0;
    }
    .el-tabs__content {
        overflow-y: auto;
        height: 400px;
        position: relative;
    }
    .content {
        width: 986px;
        height: 518px;
        position: absolute;
        top: 50%;
        left: 45%;
        z-index: 2;
        // border:1px solid #ccc;
        transform: translate(-50%,-50%);
        .detail-content,
        .left-content {
            width: 1200px;
            height: 700px;
            // border: 2px dashed  #CBBD63;
            // background-color: rgba(225, 255, 255, 0.1);
            -webkit-filter: drop-shadow(4px 4px 4px #CBBD63);
            filter: drop-shadow(4px 4px 4px #CBBD63);

        }

        .left-content {
            position: absolute;
            left: 0;
            top: 0;
            z-index: 2;
            background-repeat: no-repeat;
            background-position: center center;
            background-size: cover;
        }

        .right-content {
            margin: 0 auto;
            // background-color: rgba(225, 255, 255, 0.6);
            z-index: 3;

            .box-card {
                .clearfix {}
                .item {
                    position: relative;
                    // tabs
                    .el-form-item {
                        margin-top: -12px;
                    }
                    .el-tabs__nav-wrap {
                        width: 180px;
                        padding-left: 12px;
                        overflow: hidden;
                    }
                    // lable
                    .el-form-item__label {
                        display: block;
                        margin-left: 60px;
                        height: 36px;
                        color: #9ba3af;
                    }

                    // input-box
                    .el-form-item__content {
                        width: 340px;

                        .el-button {
                            width: 100px;
                        }
                    }
                    .toLogin {
                        display: inline-block;
                        margin-left: 20px;
                        color: darkslateblue;
                        cursor: pointer;
                        font-size: 14px;
                    }
                }
            }
        }

    }
}
</style>
