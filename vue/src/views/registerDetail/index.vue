<template>
<div class="register-container" style="background: #1896d2">
  <div class="content">

    <!-- <div class="left-content" :style="{'background-image': `url('${leftImage}')`}"></div> -->

    <div class="detail-content">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span><span v-if="userType == '0'">个人</span> <span v-if="userType == '1'">专家</span> <span v-if="userType == '2'">企业</span><span v-if="userType == '3'">服务机构</span><span v-if="userType == '4'">高校院所</span>注册</span>
        </div>

        <div class="item">
          <div v-if="userType == '0'" class="">
            <el-form class="" label-width="30%" style="text-align:left">
              <el-row :gutter="20">
                <el-col :span="10">
                  <el-form-item label="姓名">
                    <el-input v-model="per.name" placeholder="请输入姓名" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="性别">
                    <el-select v-model="per.sex" style="width:100px" placeholder="请选择">
                      <el-option label="男" key="1" value='1'>
                      </el-option>
                      <el-option label="女" key="0" value='0'>
                      </el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="出生日期">
                    <el-date-picker v-model="per.dateValue" type="date" placeholder="选择日期">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="所在地区">
                    <area-cascader :level="1" v-model="per.address" :data="pcaa"></area-cascader>
                    <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                  </el-form-item>
                  <el-form-item label="联系地址">
                    <el-input placeholder="请输入联系地址" v-model="per.selected" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="邮箱">
                    <el-input placeholder="请输入邮箱" v-model="per.ecode" style="width:80%"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="10">
                  <el-form-item label="身份证号">
                    <el-input placeholder="请输入身份证号" v-model="per.id" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="上传身份证正面">
                    <div>
                      <!--这是正面照-->
                      <div class="photo">
                        <el-upload class="upload-demo" action="" :on-change="handleChange1" :on-preview="handlePreview" :on-remove="handleRemove1" :file-list="per.cardPositive" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                        </el-upload>
                        <!-- <input type="file" @change="uploadImg($event)" id="IdCard"> -->
                        <!-- <input type="file"  @change="per.cardPositive"  id="IdCard"> -->

                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="上传身份证反面">
                    <div>
                      <!--这是背面照-->
                      <div class="photo photo1">
                        <el-upload class="upload-demo" action="" :on-change="handleChange2" :on-preview="handlePreview" :on-remove="handleRemove2" :file-list="per.cardSide" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                        </el-upload>

                      </div>
                    </div>
                  </el-form-item>
                  <el-form-item label="手持身份证">
                    <div>
                      <!--这是背面照-->
                      <div class="photo photo1">
                        <el-upload class="upload-demo" action="" :on-change="handleChange3" :on-preview="handlePreview" :on-remove="handleRemove2" :file-list="per.cardHands" list-type="picture">
                          <el-button size="small" type="primary">点击上传</el-button>
                        </el-upload>

                      </div>
                    </div>
                  </el-form-item>

                  <el-form-item label="手机号">
                    <el-input placeholder="请输入手机号" v-model="per.phone" style="width:80%"></el-input>
                  </el-form-item>

                </el-col>

              </el-row>
              <el-row>
                <div style="text-align:center">
                  <el-button type="primary" @click="saveFile" style="width: 120px;">完善并保存</el-button>
                  <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
                </div>
              </el-row>
            </el-form>
          </div>
          <div v-if="userType == '1'" class="">
            <el-tabs type="border-card">
              <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i> 专家信息</span>
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="姓名">
                        <el-input placeholder="请输入姓名" v-model="mech.lxname" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="性别">
                        <el-select v-model="per.sex" style="width:100px" placeholder="请选择">
                          <el-option label="男" key="1" value='1'>
                          </el-option>
                          <el-option label="女" key="0" value='0'>
                          </el-option>
                        </el-select>
                      </el-form-item>

                      <el-form-item label="出生日期">
                        <el-date-picker v-model="per.dateValue" type="date" placeholder="选择日期">
                        </el-date-picker>
                      </el-form-item>

                      <el-form-item label="身份证号">
                        <el-input placeholder="请输入身份证号" v-model="per.id" style="width:80%"></el-input>
                      </el-form-item>

                      <el-form-item label="毕业院校">
                        <el-input placeholder="请输入毕业院校" v-model="mech.lxzw" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="学历">
                        <el-input placeholder="请输入学历" v-model="mech.lxzw" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="学位">
                        <el-input placeholder="请输入学位" v-model="mech.lxzw" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="研究领域">
                        <el-radio v-model="mech.registerNature" label="1">智能装备</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">电子信息</el-radio>
                        <el-radio v-model="mech.registerNature" label="3">新材料 </el-radio>
                        <el-radio v-model="mech.registerNature" label="4">航空航天</el-radio>
                        <el-radio v-model="mech.registerNature" label="5">生物技术与新医药</el-radio>
                        <el-radio v-model="mech.registerNature" label="6">能源与环保</el-radio>
                        <el-radio v-model="mech.registerNature" label="7">管理</el-radio>
                        <el-radio v-model="mech.registerNature" label="8">其他</el-radio>
                      </el-form-item>

                      <el-form-item label="研究方向">
                        <el-input placeholder="请输入研究方向" v-model="mech.lxzw" style="width:80%"></el-input>
                      </el-form-item>

                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>

              </el-tab-pane>
              <el-tab-pane label="联系方式">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="现任职务">
                        <el-input placeholder="请输入现任职务" v-model="mech.fdname" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职称">
                        <el-input placeholder="请输入职称" v-model="mech.fdname" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职称级别">
                        <el-radio v-model="mech.registerNature" label="1">正高</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">副高</el-radio>
                      </el-form-item>
                      <el-form-item label="工作单位">
                        <el-input placeholder="请输入职称" v-model="mech.fdname" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" v-model="mech.fdphone" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="办公电话">
                        <el-input placeholder="请输入办公电话" v-model="mech.fdphone" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" v-model="mech.fdemail" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="所在地区">
                        <area-cascader :level="1" v-model="mech.selected" :data="pcaa"></area-cascader>
                      </el-form-item>
                      <el-form-item label="通讯地址">
                        <el-input placeholder="请输入通讯地址" v-model="mech.fdemail" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>


              <el-tab-pane label="主要学术成就/研究成果/管理成就">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="24">
                    <el-col :span="24">
                      <textarea rows="3" cols="20" style="width:100%;height: 350px;">
                      </textarea>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="主要产学研合作项目情况（国防军工类项目）">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="24">
                    <el-col :span="24">
                      <textarea rows="3" cols="20" style="width:100%;height: 350px;">
                      </textarea>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="近5年专业研究及获奖情况">
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="22">
                    <!-- <el-form-item label=""> -->

                    <div style="margin:0 auto">

                      <el-button size="small" @click="addProject()">
                        添加课题
                      </el-button>
                      <el-table class="tableH" :data="mech.registerPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                        <el-table-column align="center" label="项目或课题名称">
                          <template slot-scope="scope">
                          <input  type="text" v-model="scope.row.name">
                                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="项目或课题来源">
                          <template slot-scope="scope">
                                                <span>
                                                  <input  type="text" v-model="scope.row.time">
                                                </span>
                                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="完成情况">
                          <template slot-scope="scope">
                          <input  type="text" v-model="scope.row.source">
                                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="完成时间">
                          <template slot-scope="scope">
                          <input  type="text" v-model="scope.row.source">
                                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="奖项名称">
                          <template slot-scope="scope">
                          <input  type="text" v-model="scope.row.source">
                                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="获奖等级">
                          <template slot-scope="scope">
                          <input  type="text" v-model="scope.row.source">
                                          </template>
                        </el-table-column>
                        <el-table-column align="center" label="获奖时间">
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
                <el-button type="primary" style="width: 120px;" @click="saveFile">完善并保存</el-button>
                <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
              </div>
            </el-row>
          </div>


          <div v-if="userType == '2'" class="">


            <el-tabs type="border-card">
              <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i> 联系人信息</span>

                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="10">
                      <el-form-item label="姓名">
                        <el-input placeholder="请输入姓名" v-model="com.lxname" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职务">
                        <el-input placeholder="请输入职务" v-model="com.lxzw" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" v-model="com.lxphone" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" v-model="com.lxemail" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">

                      <el-form-item label="所在地区">
                        <area-cascader :level="1" v-model="com.lxselected" :data="pcaa"></area-cascader>
                        <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                      </el-form-item>
                      <el-form-item label="联系地址">
                        <el-input placeholder="请输入联系地址" v-model="com.lxaddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮编">
                        <el-input placeholder="请输入邮编" v-model="com.lxecode" style="width:80%"></el-input>
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
                        <el-input placeholder="请输入姓名" v-model="com.lpname" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职务">
                        <el-input placeholder="请输入职务" v-model="com.lpzw" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" v-model="com.lpphone" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" v-model="com.lpemail" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>
              <el-tab-pane label="企业基础信息">
                <el-form class="" label-width="40%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="企业规模（注册资金）">
                        <el-radio v-model="com.registerFinance" label="1">小于2000万（含）</el-radio>
                        <el-radio v-model="com.registerFinance" label="2">2000-5000万</el-radio>
                        <el-radio v-model="com.registerFinance" label="3">5000-1亿（含）</el-radio>
                        <el-radio v-model="com.registerFinance" label="4">1亿-2亿（含）</el-radio>
                        <el-radio v-model="com.registerFinance" label="5">2亿-4亿（含）</el-radio>
                        <el-radio v-model="com.registerFinance" label="6">4亿及以上（含）</el-radio>
                      </el-form-item>
                      <el-form-item label="注册时间">
                        <el-date-picker v-model="com.registerValue" type="date" placeholder="选择日期">
                        </el-date-picker>
                      </el-form-item>
                      <el-form-item label="注册类型">
                        <el-radio v-model="com.registerType" label="1">内资企业</el-radio>
                        <el-radio v-model="com.registerType" label="2">合资企业</el-radio>
                        <el-radio v-model="com.registerType" label="3">外资企业</el-radio>
                      </el-form-item>
                      <el-form-item label="所在地区">
                        <area-cascader :level="1" v-model="com.registerselected" :data="pcaa"></area-cascader>
                        <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                      </el-form-item>
                      <el-form-item label="通讯地址">
                        <el-input placeholder="请输入通讯地址" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮编">
                        <el-input placeholder="请输入邮编" v-model="com.registerecode" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="是否高新技术企业">
                        <el-radio v-model="com.registerH" label="1">是</el-radio>
                        <el-radio v-model="com.registerH" label="2">否</el-radio>
                      </el-form-item>
                      <el-form-item label="企业所在地性质">
                        <el-radio v-model="com.registerSite" label="1">国家级高新区 </el-radio>
                        <el-radio v-model="com.registerSite" label="2">省级高新区</el-radio>
                        <el-radio v-model="com.registerSite" label="3">国家级经开区 </el-radio>
                        <el-radio v-model="com.registerSite" label="4">省级经开区</el-radio>
                        <el-radio v-model="com.registerSite" label="5">其他</el-radio>
                      </el-form-item>
                      <el-form-item label="所在国家高新区">
                        <el-radio v-model="com.registerHSite" label="1">南京高新区</el-radio>
                        <el-radio v-model="com.registerHSite" label="2">无锡高新区</el-radio>
                        <el-radio v-model="com.registerHSite" label="3">昆山高新区 </el-radio>
                        <el-radio v-model="com.registerHSite" label="4">徐州高新区</el-radio>
                        <el-radio v-model="com.registerHSite" label="5">其他</el-radio>
                      </el-form-item>
                      <el-form-item label="是否上市">
                        <el-radio v-model="com.registerMarket" label="1">是</el-radio>
                        <el-radio v-model="com.registerMarket" label="2">否</el-radio>
                      </el-form-item>
                      <el-form-item label="上市地点">
                        <el-radio v-model="com.registerMarkeSite" label="1">上交所</el-radio>
                        <el-radio v-model="com.registerMarkeSite" label="2">深交所</el-radio>
                        <el-radio v-model="com.registerMarkeSite" label="3">新三板 </el-radio>
                        <el-radio v-model="com.registerMarkeSite" label="4">港交所</el-radio>
                        <el-radio v-model="com.registerMarkeSite" label="5">主版</el-radio>
                        <el-radio v-model="com.registerMarkeSite" label="6">中小板</el-radio>
                        <el-radio v-model="com.registerMarkeSite" label="7">创业板</el-radio>
                      </el-form-item>
                      <el-form-item label="所属领域">
                        <el-radio v-model="com.registerField" label="1">智能装备</el-radio>
                        <el-radio v-model="com.registerField" label="2">电子信息</el-radio>
                        <el-radio v-model="com.registerField" label="3">新材料 </el-radio>
                        <el-radio v-model="com.registerField" label="4">航空航天</el-radio>
                        <el-radio v-model="com.registerField" label="5">生物技术与新医药</el-radio>
                        <el-radio v-model="com.registerField" label="6">能源与环保</el-radio>
                        <el-radio v-model="com.registerField" label="7">其他</el-radio>
                      </el-form-item>
                      <el-form-item label="主营产品">
                        <el-input placeholder="请输入主营产品" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>

              <el-tab-pane label="获得知识产权情况">
                <el-form class="" label-width="40%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">
                      <el-form-item label="发明专利数量(件)">
                        <el-input placeholder="请输入发明专利数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="国家新药数量(件)">
                        <el-input placeholder="请输入国家新药数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="实用新型数量(件)">
                        <el-input placeholder="请输入实用新型数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="集成电路布图设计专有权数量(件)">
                        <el-input placeholder="请输入集成电路布图设计专有权数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="外观设计数量(件)">
                        <el-input placeholder="请输入外观设计数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="国防专利数量(件)">
                        <el-input placeholder="请输入国防专利数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="软件著作权数量(件)">
                        <el-input placeholder="请输入软件著作权数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="其他数量(件)">
                        <el-input placeholder="请输入其他数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>

                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>

              <el-tab-pane label="企业研发情况">
                <el-form class="" label-width="40%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">
                      <el-form-item label="研发团队人员总数(人)">
                        <el-input placeholder="请输入发明专利数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="本科及以上人数(人)">
                        <el-input placeholder="请输入国家新药数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="中高级职称以上人员人数(人)">
                        <el-input placeholder="请输入实用新型数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="研发团队高层次人才(人)">
                        <el-input placeholder="请输入集成电路布图设计专有权数量" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>

                      <el-form-item label="高层次人才类型">
                        <el-radio v-model="mech.registerNature" label="1">院士</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">国家千人</el-radio>
                        <el-radio v-model="mech.registerNature" label="3">青年千人 </el-radio>
                        <el-radio v-model="mech.registerNature" label="4">中青年科技创新领军人才</el-radio>
                        <el-radio v-model="mech.registerNature" label="4">长江学者</el-radio>
                        <el-radio v-model="mech.registerNature" label="4">国家杰青</el-radio>
                        <el-radio v-model="mech.registerNature" label="4">其他</el-radio>
                      </el-form-item>

                      <el-form-item label="近3年研发投入（万元）">
                        <el-input placeholder="请输入近3年研发投入" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="平台建设情况">
                        <el-radio v-model="mech.registerNature" label="1">企业重点实验室（国家级）</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">企业重点实验室（省级）</el-radio>
                        <el-radio v-model="mech.registerNature" label="3">企业院士工作站 </el-radio>
                        <el-radio v-model="mech.registerNature" label="4">程技术研究中心（国家级）</el-radio>
                        <el-radio v-model="mech.registerNature" label="5">程技术研究中心（省级）</el-radio>
                      </el-form-item>
                    </el-col>

                    <el-col :span="10">


                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>

              <el-tab-pane label="创新情况">
                <el-form class="" label-width="40%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="是否承担过武器装备科研生产任务">
                        <el-radio v-model="mech.registerNature" label="1">是</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">否</el-radio>
                      </el-form-item>

                      <el-form-item label="“三证”已获得">
                        <el-radio v-model="mech.registerNature" label="3">武器装备科研生产单位保密资格认证（一级、二级、三级） </el-radio>
                        <el-radio v-model="mech.registerNature" label="3">武器装备科研生产许可证 </el-radio>
                        <el-radio v-model="mech.registerNature" label="3">装备承制单位资格认证 </el-radio>
                      </el-form-item>
                      <el-form-item label="取得时间">
                        <el-date-picker v-model="com.registerValue" type="date" placeholder="选择日期">
                        </el-date-picker>
                      </el-form-item>

                      <el-form-item label="“三证”未获得是否计划申请">
                        <el-radio v-model="mech.registerNature" label="3">是 </el-radio>
                        <el-radio v-model="mech.registerNature" label="3">否 </el-radio>
                      </el-form-item>

                      <el-form-item label="军民融合建设管理情况">
                        <el-radio v-model="mech.registerNature" label="1">内设专门机构</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">设有专项经费</el-radio>
                        <el-radio v-model="mech.registerNature" label="3">有专人负责 </el-radio>
                      </el-form-item>
                      <el-form-item label="“民参军”过程中，遇到的障碍及困难">
                        <el-radio v-model="mech.registerNature" label="1">需求信息</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">产品准入</el-radio>
                        <el-radio v-model="mech.registerNature" label="3">人才保障 </el-radio>
                        <el-radio v-model="mech.registerNature" label="3">审批程序 </el-radio>
                        <el-radio v-model="mech.registerNature" label="3">资质认证 </el-radio>
                        <el-radio v-model="mech.registerNature" label="3">无人管理 </el-radio>
                        <el-radio v-model="mech.registerNature" label="3">其他  </el-radio>
                      </el-form-item>
                      <el-form-item label="“民参军”过程中企业自身存在的问题">
                        <el-radio v-model="mech.registerNature" label="1">技术人才不足</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">资金保障困难</el-radio>
                        <el-radio v-model="mech.registerNature" label="3">保密设施设备不足 </el-radio>
                        <el-radio v-model="mech.registerNature" label="3">需求信息不了解 </el-radio>
                      </el-form-item>

                      <el-form-item label="有参军潜力的技术成果">
                        <el-input placeholder="请输入有参军潜力的技术成果" v-model="com.registeraddress" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="可用于军民融合共享的资源情况（不超过200字）">

                        <textarea rows="3" cols="20" style="width:100%;height: 350px;">
                        </textarea>
                      </el-form-item>

                      <el-form-item label="对军民科技协同创新的相关建议（不超过200字）">
                        <textarea rows="3" cols="20" style="width:100%;height: 350px;">
                          </textarea>
                      </el-form-item>

                    </el-col>


                  </el-row>
                </el-form>
              </el-tab-pane>


              <el-tab-pane label="创新项目">
                <el-form class="" label-width="40%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">


                      <el-form-item label="服务部门">
                        <el-radio v-model="mech.registerNature" label="1">海军</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">陆军</el-radio>
                        <el-radio v-model="mech.registerNature" label="3">空军</el-radio>
                        <el-radio v-model="mech.registerNature" label="4">火箭军</el-radio>
                        <el-radio v-model="mech.registerNature" label="5">战略支援部队</el-radio>
                        <el-radio v-model="mech.registerNature" label="6">军工企业</el-radio>
                      </el-form-item>

                      <el-form-item label="执行情况">
                        <el-radio v-model="mech.registerNature" label="1">申请</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">在研</el-radio>
                        <el-radio v-model="mech.registerNature" label="3">验收/结题 </el-radio>
                      </el-form-item>
                      <div style="margin:0 auto">

                        <el-button size="small" @click="addProject()">
                          添加项目
                        </el-button>
                        <el-table class="tableH" :data="mech.registerPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

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
                    </el-col>

                  </el-row>
                </el-form>
              </el-tab-pane>
            </el-tabs>


            <el-row>
              <div style="text-align:center;margin-top: 20px;">
                <el-button type="primary" style="width: 120px;" @click="saveFile">完善并保存</el-button>
                <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
              </div>
            </el-row>

          </div>


          <div v-if="userType == '3'" class="">
            <el-tabs type="border-card">
              <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i> 机构联系人</span>
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="姓名">
                        <el-input placeholder="请输入姓名" v-model="mech.lxname" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="职务">
                        <el-input placeholder="请输入职务" v-model="mech.lxzw" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" v-model="mech.lxphone" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" v-model="mech.lxemail" style="width:80%"></el-input>
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
                        <el-input placeholder="请输入姓名" v-model="mech.fdname" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="手机号">
                        <el-input placeholder="请输入手机号" v-model="mech.fdphone" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" v-model="mech.fdemail" style="width:80%"></el-input>
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
                        <el-input placeholder="请输入单位名称" v-model="mech.comname" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="所在地区">
                        <area-cascader :level="1" v-model="mech.selected" :data="pcaa"></area-cascader>
                        <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                      </el-form-item>
                      <el-form-item label="联系地址">
                        <el-input placeholder="请输入联系地址" v-model="mech.address" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮编">
                        <el-input placeholder="请输入邮编" v-model="mech.ecode" style="width:80%"></el-input>
                      </el-form-item>

                      <el-form-item label="机构性质">
                        <el-radio v-model="mech.registerNature" label="1">企业</el-radio>
                        <el-radio v-model="mech.registerNature" label="2">科研院所</el-radio>
                        <el-radio v-model="mech.registerNature" label="3">高等院校 </el-radio>
                        <el-radio v-model="mech.registerNature" label="4">其他</el-radio>
                      </el-form-item>

                      <el-form-item label="机构类别">
                        <el-radio v-model="mech.registerNatureType" label="1">研究开发</el-radio>
                        <el-radio v-model="mech.registerNatureType" label="2">科技投融资</el-radio>
                        <el-radio v-model="mech.registerNatureType" label="3">技术转移 </el-radio>
                        <el-radio v-model="mech.registerNatureType" label="4">检验检测</el-radio>
                        <el-radio v-model="mech.registerNatureType" label="5">创业孵化</el-radio>
                        <el-radio v-model="mech.registerNatureType" label="6">知识产权</el-radio>
                        <el-radio v-model="mech.registerNatureType" label="7">科技评估</el-radio>
                        <el-radio v-model="mech.registerNatureType" label="8">标准认证</el-radio>
                        <el-radio v-model="mech.registerNatureType" label="9">管理咨询</el-radio>
                        <el-radio v-model="mech.registerNatureType" label="10">综合科技服务</el-radio>
                        <el-radio v-model="mech.registerNatureType" label="11">其他</el-radio>
                      </el-form-item>

                      <el-form-item label="可提供的服务简介（200字以内">

                        <textarea rows="3" cols="20" style="width:100%;height: 350px;">
                        </textarea>
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
                        <el-input v-model="mech.perNum" placeholder="请输入人员总数" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="本科及以上学历人员占比">
                        <el-input v-model="mech.underPer" placeholder="请输入人员本科及以上学历人员占比" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="中高级职称以上人员占比">
                        <el-input v-model="mech.MHPer" placeholder="请输入中高级职称以上人员占比编" style="width:80%"></el-input>
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
                        <el-radio v-model="mech.registerSupport" label="1">拨款</el-radio>
                        <el-radio v-model="mech.registerSupport" label="2">贴息</el-radio>
                        <el-radio v-model="mech.registerSupport" label="3">减免税 </el-radio>
                        <el-radio v-model="mech.registerSupport" label="4">其他</el-radio>
                        <el-radio v-model="mech.registerSupport" label="5">以上全无</el-radio>
                      </el-form-item>
                      <el-form-item label="执行情况">
                        <el-radio v-model="mech.registerImplement" label="1">申请</el-radio>
                        <el-radio v-model="mech.registerImplement" label="2">在研</el-radio>
                        <el-radio v-model="mech.registerImplement" label="3">验收/结题 </el-radio>
                      </el-form-item>


                    </el-col>
                  </el-row>
                  <!-- <el-form-item label=""> -->

                  <div style="margin:0 auto">

                    <el-button size="small" @click="addProject()">
                      添加项目
                    </el-button>
                    <el-table class="tableH" :data="mech.registerPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

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
                      <el-table class="tableH" :data="mech.registerPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

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
                      <el-table class="tableH" :data="mech.registerPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

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
                <el-button type="primary" style="width: 120px;" @click="saveFile">完善并保存</el-button>
                <el-button type="primary" style="width: 120px;">不完善并保存</el-button>
              </div>
            </el-row>
          </div>

          <div v-if="userType == '4'" class="">
            <el-tabs type="border-card">
              <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i> 高校基本信息</span>
                <el-form class="" label-width="30%" style="text-align:left">
                  <el-row :gutter="20">
                    <el-col :span="20">

                      <el-form-item label="名称">
                        <el-input placeholder="请输入名称" v-model="school.name" style="width:80%"></el-input>
                      </el-form-item>

                      <el-form-item label="所在地区">
                        <area-cascader :level="1" v-model="school.selected" :data="pcaa"></area-cascader>
                        <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                      </el-form-item>
                      <el-form-item label="联系地址">
                        <el-input placeholder="请输入联系地址" v-model="school.address" style="width:80%"></el-input>
                      </el-form-item>
                      <el-form-item label="邮箱">
                        <el-input placeholder="请输入邮箱" v-model="school.email" style="width:80%"></el-input>
                      </el-form-item>

                      <el-form-item label="单位网址">
                        <el-input placeholder="请输入单位网址" v-model="school.network" style="width:80%"></el-input>
                      </el-form-item>

                      <el-form-item label="单位简介">
                        <el-input placeholder="请输入单位简介" v-model="school.desc" style="width:80%"></el-input>
                      </el-form-item>
                    </el-col>



                  </el-row>
                </el-form>

              </el-tab-pane>

            </el-tabs>
            <el-row>
              <div style="text-align:center;margin-top: 20px;">
                <el-button type="primary" style="width: 120px;" @click="saveFile">完善并保存</el-button>
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
import {
  getUserDetail
} from '@/api/login'
export default {
  data() {
    return {
      school: {
        desc: '',
        network: '',
        email: '',
        address: '',
        selected: '',
        name: '',
      },
      mech: {
        registerNature: '',
        registerNatureType: '',
        registerImplement: '',
        registerSupport: '',
        registerPorcolumnDefinitions: [{
          name: '',
          time: '',
          source: ''
        }],
        MHPer: '',
        underPer: '',
        perNum: '',
        ecode: '',
        address: '',
        selected: '',
        comname: '',
        fdemail: '',
        fdphone: '',
        fdname: '',
        lxemail: '',
        lxphone: '',
        lxzw: '',
        lxname: ''
      },
      com: {
        lxname: '',
        lxzw: '',
        lxphone: '',
        lxemail: '',
        lxselected: '',
        lxaddress: '',
        lxecode: '',
        lpname: '',
        lpzw: '',
        lpphone: '',
        lpemail: '',
        registerFinance: '1',
        registerValue: '',
        registerType: '1',
        registerH: '1',
        registerSite: '1',
        registerHSite: '1',
        registerMarket: '1',
        registerMarkeSite: '1',
        registerField: '1',
      },
      per: {
        name: '',
        dateValue: '',
        sex: '',
        selected: '',
        address: '',
        ecode: '',
        id: '',
        cardPositive: [],
        cardSide: [],
        cardHands: [],
        phone: '',
      },
      userType: '2',
      registerValue: '',
      loginVerify: { // 普通登录
        phone: '',
        verity: ''
      },
      selected: [],
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区

    }
  },
  async mounted() {
    if (this.$route.params.userType) {
      this.userType = this.$route.params.userType
      let {
        data,
        success
      } = await getUserDetail()
      if (this.userType == '2') {
        this.com = JSON.parse(data.detail)

        debugger
      }
    }


  },
  methods: {
    handleChange1(file, fileList) {
      let obj = {}
      obj.name = fileList.slice(-3)[0].name
      obj.url = fileList.slice(-3)[0].url
      this.per.cardPositive.push(obj)

    },
    handleChange2(file, fileList) {
      let obj = {}
      obj.name = fileList.slice(-3)[0].name
      obj.url = fileList.slice(-3)[0].url
      this.per.cardSide.push(obj)

    },
    handleChange3(file, fileList) {
      let obj = {}
      obj.name = fileList.slice(-3)[0].name
      obj.url = fileList.slice(-3)[0].url
      this.per.cardHands.push(obj)

    },
    handleRemove1(file, fileList) {
      this.per.cardPositive = []
    },
    handleRemove2(file, fileList) {
      this.per.cardSide = []
    },
    handleRemove3(file, fileList) {
      this.per.cardHands = []
    },
    handlePreview(file) {
      console.log(file);
    },
    uploadImg: function(event) {
      var file = event.target.files; // (利用console.log输出看结构就知道如何处理档案资料)
      console.log(file)
      // do something...
    },
    saveFile() {
      let obj = this.per
      let obj2 = this.com
      let obj3 = JSON.stringify(this.mech)
      let obj4 = this.school
      debugger
      return
    },
    addProject() {
      this.mech.registerPorcolumnDefinitions.push({
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
.upload-demo {
    display: flex;
}
.el-upload-list--picture .el-upload-list__item {
    width: 220px;
    margin-top: 0;
    height: 42px;
    padding: 0;
    margin-left: 10px;
}
.el-upload-list--picture .el-upload-list__item-thumbnail {
    width: 30px;
    height: 30px;
    margin-left: 20px;
}
.el-upload-list--picture .el-upload-list__item.is-success .el-upload-list__item-name {
    line-height: 40px;
    margin-top: 0;
}
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
    // height: 600px;
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
