<template>
<div class="tab-container">
  <div class="" style="padding-left:35%;margin: 0 0 40px 0;">
    <h2>{{titleName}}</h2>
  </div>

  <div v-if="userType == '1'" class="">
    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 专家信息</span>
        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="姓名">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入姓名" v-model="expert.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-select v-model="expert.sex" style="width:100px" placeholder="请选择">
                  <el-option label="男" key="1" value='1'>
                  </el-option>
                  <el-option label="女" key="0" value='0'>
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="出生日期">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-date-picker v-model="expert.bornDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
                </el-date-picker>
              </el-form-item>

              <el-form-item label="身份证号">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入身份证号" v-model="expert.code" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="上传身份证正面">
                <div>
                  <!--这是正面照-->
                  <div class="photo">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="expert.cardPositive" list-type="picture">
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
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="expert.cardSide" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="手持身份证">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="expert.cardHands" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="免冠照片">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="expert.onepicture" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="毕业院校">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入毕业院校" v-model="expert.shcool" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="学历">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入学历" v-model="expert.edu" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="学位">
                <el-input placeholder="请输入学位" v-model="expert.academic" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="研究领域">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>

                <el-checkbox-group v-model="expert.research_field">
                  <el-checkbox label="1">智能装备</el-checkbox>
                  <el-checkbox label="2">电子信息</el-checkbox>
                  <el-checkbox label="3">新材料 </el-checkbox>
                  <el-checkbox label="4">航空航天</el-checkbox>
                  <el-checkbox label="5">生物技术与新医药</el-checkbox>
                  <el-checkbox label="6">能源与环保</el-checkbox>
                  <el-checkbox label="7">管理</el-checkbox>
                  <el-checkbox label="8">其他</el-checkbox>
                  <el-input v-if="expert.research_field.includes('8')" placeholder="请输入其他" v-model="expert.research_fieldOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="研究方向">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入研究方向" v-model="expert.research_area" style="width:80%"></el-input>
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
                <el-input placeholder="请输入现任职务" v-model="expert.zwname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职称">
                <el-input placeholder="请输入职称" v-model="expert.zcname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职称级别">
                <el-checkbox-group v-model="expert.zclevel">
                  <el-checkbox label="1">正高</el-checkbox>
                  <el-checkbox label="2">副高</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="工作单位">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入职称" v-model="expert.work_unit" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <span style='position: relative;left: -60px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入手机号" v-model="expert.mobilephone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="办公电话">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入办公电话" v-model="expert.telphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="expert.fdemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="所在地区">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <area-cascader :level="1" v-model="expert.country" :data="pcaa"></area-cascader>
              </el-form-item>
              <el-form-item label="通讯地址">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入通讯地址" v-model="expert.address" style="width:80%"></el-input>
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
              <textarea v-model="expert.success_record" rows="3" cols="20" style="width:100%;height: 350px;">
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
              <textarea v-model="expert.project_desc" rows="3" cols="20" style="width:100%;height: 350px;">
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

              <el-button size="small" @click="addProjectexpert()">
                添加课题
              </el-button>
              <el-table class="tableH" :data="expert.research_record" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                <el-table-column align="center" label="项目或课题名称">
                  <template slot-scope="scope">
                  <input  type="text" v-model="scope.row.projectname">
                                  </template>
                </el-table-column>
                <el-table-column align="center" label="项目或课题来源">
                  <template slot-scope="scope">
                                        <span>
                                          <input  type="text" v-model="scope.row.projectSrc">
                                        </span>
                                    </template>
                </el-table-column>
                <el-table-column align="center" label="完成情况">
                  <template slot-scope="scope">
                  <input  type="text" v-model="scope.row.finishcon">
                                  </template>
                </el-table-column>
                <el-table-column align="center" label="完成时间">
                  <template slot-scope="scope">
                  <input  type="text" v-model="scope.row.finishtime">
                                  </template>
                </el-table-column>
                <el-table-column align="center" label="奖项名称">
                  <template slot-scope="scope">
                  <input  type="text" v-model="scope.row.rewname">
                                  </template>
                </el-table-column>
                <el-table-column align="center" label="获奖等级">
                  <template slot-scope="scope">
                  <input  type="text" v-model="scope.row.rewlevel">
                                  </template>
                </el-table-column>
                <el-table-column align="center" label="获奖时间">
                  <template slot-scope="scope">
                  <input  type="text" v-model="scope.row.rewtime">
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
      <div style="padding-left: 35%;margin: 40px 0  0  0;">
        <el-button type="primary" style="width: 120px;" @click="saveFile(expert)">保存</el-button>
      </div>
    </el-row>
  </div>


  <div v-if="userType == '2'" class="">

    <el-tabs type="border-card">
      <el-tab-pane>
        <span slot="label"><i class="el-icon-date"></i> 联系人信息</span>

        <el-form class="" label-width="30%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">
              <el-form-item label="姓名">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入姓名" v-model="com.lxname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职务">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入职务" v-model="com.lxzw" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <span style='position: relative;left: -60px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入手机号" v-model="com.lxphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="com.lxemail" style="width:80%"></el-input>
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
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入姓名" v-model="com.lpname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职务">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入职务" v-model="com.lpzw" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <span style='position: relative;left: -60px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入手机号" v-model="com.lpphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="com.lpemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="营业执照正本">
                <span style='position: absolute;left: -110px;color: #f60d0d;'>*</span>
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="com.zhengben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="营业执照副本">
                <span style='position: absolute;left: -110px;color: #f60d0d;'>*</span>
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="com.fuben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="企业logo">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="com.logo" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
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

              <el-form-item label="企业名称">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入企业名称" v-model="com.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="企业编码">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入企业编码" v-model="com.code" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="企业规模（注册资金）">
                <span style='position: absolute;left: -170px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.registered_capital">
                  <el-checkbox label="1">小于2000万（含）</el-checkbox>
                  <el-checkbox label="2">2000-5000万</el-checkbox>
                  <el-checkbox label="3">5000-1亿（含）</el-checkbox>
                  <el-checkbox label="4">1亿-2亿（含）</el-checkbox>
                  <el-checkbox label="5">2亿-4亿（含）</el-checkbox>
                  <el-checkbox label="6">4亿及以上（含）</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="注册时间">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-date-picker v-model="com.registerDate" type="date" placeholder="选择日期">
                </el-date-picker>
              </el-form-item>
              <el-form-item label="注册类型">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.registered_type">
                  <el-checkbox label="1">内资企业</el-checkbox>
                  <el-checkbox label="2">合资企业</el-checkbox>
                  <el-checkbox label="3">外资企业</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="所在地区">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <area-cascader :level="1" v-model="com.country" :data="pcaa"></area-cascader>
                <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
              </el-form-item>
              <el-form-item label="通讯地址">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入通讯地址" v-model="com.registeraddress" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮编">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮编" v-model="com.registerecode" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="是否高新技术企业">
                <span style='position: absolute;left: -140px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.is_high_new_tech">
                  <el-checkbox label="1">是</el-checkbox>
                  <el-checkbox label="2">否</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="企业所在地性质">
                <span style='position: absolute;left: -130px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.registerSite">
                  <el-checkbox label="1">国家级高新区 </el-checkbox>
                  <el-checkbox label="2">省级高新区</el-checkbox>
                  <el-checkbox label="3">国家级经开区 </el-checkbox>
                  <el-checkbox label="4">省级经开区</el-checkbox>
                  <el-checkbox label="5">其他</el-checkbox>
                  <el-input v-if="com.registerSite.includes('5')" placeholder="请输入其他" v-model="com.registerSiteOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="所在国家高新区">
                <span style='position: absolute;left: -130px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.registerHSite">
                  <el-checkbox label="1">南京高新区</el-checkbox>
                  <el-checkbox label="2">无锡高新区</el-checkbox>
                  <el-checkbox label="3">昆山高新区 </el-checkbox>
                  <el-checkbox label="4">徐州高新区</el-checkbox>
                  <el-checkbox label="5">其他</el-checkbox>
                  <el-input v-if="com.registerHSite.includes('5')" placeholder="请输入其他" v-model="com.registerHSiteOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="是否上市">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.registerMarket">
                  <el-checkbox label="1">是</el-checkbox>
                  <el-checkbox label="2">否</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="上市地点">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.registerMarkeSite">
                  <el-checkbox label="1">上交所</el-checkbox>
                  <el-checkbox label="2">深交所</el-checkbox>
                  <el-checkbox label="3">新三板 </el-checkbox>
                  <el-checkbox label="4">港交所</el-checkbox>
                  <el-checkbox label="5">主版</el-checkbox>
                  <el-checkbox label="6">中小板</el-checkbox>
                  <el-checkbox label="7">创业板</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="所属领域">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.domain">
                  <el-checkbox label="1">智能装备</el-checkbox>
                  <el-checkbox label="2">电子信息</el-checkbox>
                  <el-checkbox label="3">新材料 </el-checkbox>
                  <el-checkbox label="4">航空航天</el-checkbox>
                  <el-checkbox label="5">生物技术与新医药</el-checkbox>
                  <el-checkbox label="6">能源与环保</el-checkbox>
                  <el-checkbox label="7">其他</el-checkbox>
                  <el-input v-if="com.domain.includes('7')" placeholder="请输入其他" v-model="com.comdomainOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="主营产品">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入主营产品" v-model="com.product" style="width:80%"></el-input>
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
                <el-input placeholder="请输入发明专利数量" v-model="com.inventionNum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="国家新药数量(件)">
                <el-input placeholder="请输入国家新药数量" v-model="com.newDrugnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="实用新型数量(件)">
                <el-input placeholder="请输入实用新型数量" v-model="com.utilityModel" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="集成电路布图设计专有权数量(件)">
                <el-input placeholder="请输入集成电路布图设计专有权数量" v-model="com.integratedCircuitnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="外观设计数量(件)">
                <el-input placeholder="请输入外观设计数量" v-model="com.designnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="国防专利数量(件)">
                <el-input placeholder="请输入国防专利数量" v-model="com.nationalNum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="软件著作权数量(件)">
                <el-input placeholder="请输入软件著作权数量" v-model="com.softwareNum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="其他数量(件)">
                <el-input placeholder="请输入其他数量" v-model="com.othernum" style="width:80%"></el-input>
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
                <span style='position: relative;left: -160px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入人员总数" v-model="com.researchTeamnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="本科及以上人数(人)">
                <span style='position: relative;left: -140px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入国家新药数量" v-model="com.bachelorAbovenum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="中高级职称以上人员人数(人)">
                <span style='position: relative;left: -200px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入中高级职称人数" v-model="com.middleLevelnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="研发团队高层次人才(人)">
                <span style='position: relative;left: -170px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入研发团队人才人数" v-model="com.highleveltalentsnum" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="高层次人才类型">
                <span style='position: absolute;left: -120px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.highleveltalentType">
                  <el-checkbox label="1">院士</el-checkbox>
                  <el-checkbox label="2">国家千人</el-checkbox>
                  <el-checkbox label="3">青年千人 </el-checkbox>
                  <el-checkbox label="4">中青年科技创新领军人才</el-checkbox>
                  <el-checkbox label="5">长江学者</el-checkbox>
                  <el-checkbox label="6">国家杰青</el-checkbox>
                  <el-checkbox label="7">其他</el-checkbox>
                  <el-input v-if="com.highleveltalentType.includes('7')" placeholder="请输入其他" v-model="com.comhighleveltalentTypeOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item :label="service_research_lastt">
                <span style='position: absolute;left: -180px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入研发投入" v-model="com.service_research_last" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_research_beforet">
                <span style='position: relative;left: -180px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入研发投入" v-model="com.service_research_before" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_research_previoust">
                <span style='position: relative;left: -180px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入研发投入" v-model="com.service_research_previous" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="平台建设情况">
                <span style='position: absolute;left: -100px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.platform">
                  <el-checkbox label="1">企业重点实验室（国家级）</el-checkbox>
                  <el-checkbox label="2">企业重点实验室（省级）</el-checkbox>
                  <el-checkbox label="3">企业院士工作站 </el-checkbox>
                  <el-checkbox label="4">程技术研究中心（国家级）</el-checkbox>
                  <el-checkbox label="5">程技术研究中心（省级）</el-checkbox>
                </el-checkbox-group>
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
                <span style='position: absolute;left: -230px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.sfkyrw">
                  <el-checkbox  label="1">是</el-checkbox>
                  <el-checkbox  label="2">否</el-checkbox>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="“三证”已获得">
                <span style='position: absolute;left: -100px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.szget">
                <el-checkbox label="1">武器装备科研生产单位保密资格认证（一级、二级、三级） </el-checkbox>
                <el-checkbox label="2">武器装备科研生产许可证 </el-checkbox>
                <el-checkbox label="3">装备承制单位资格认证 </el-checkbox>
              </el-checkbox-group>
              </el-form-item>
              <el-form-item label="取得时间">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-date-picker v-model="com.qdtime" type="date" placeholder="选择日期">
                </el-date-picker>
              </el-form-item>

              <el-form-item label="“三证”未获得是否计划申请">
                <span style='position: absolute;left: -190px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.szwhd">
                <el-checkbox  label="1">是 </el-checkbox>
                <el-checkbox  label="2">否 </el-checkbox>
              </el-checkbox-group>
              </el-form-item>

              <el-form-item label="军民融合建设管理情况">
                <span style='position: absolute;left: -160px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.jmrhgn">
                <el-checkbox  label="1">内设专门机构</el-checkbox>
                <el-checkbox  label="2">设有专项经费</el-checkbox>
                <el-checkbox  label="3">有专人负责 </el-checkbox>
              </el-checkbox-group>
              </el-form-item>
              <el-form-item label="“民参军”过程中，遇到的障碍及困难">
                <span style='position: absolute;left: -240px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.ydkn">
                <el-checkbox  label="1">需求信息</el-checkbox>
                <el-checkbox  label="2">产品准入</el-checkbox>
                <el-checkbox  label="3">人才保障 </el-checkbox>
                <el-checkbox  label="4">审批程序 </el-checkbox>
                <el-checkbox  label="5">资质认证 </el-checkbox>
                <el-checkbox  label="6">无人管理 </el-checkbox>
                <el-checkbox  label="7">其他 </el-checkbox>
                <el-input v-if="com.ydkn.includes('7')" placeholder="请输入其他" v-model="com.comydknOther" style="width:80%"></el-input>
              </el-checkbox-group>
              </el-form-item>
              <el-form-item label="“民参军”过程中企业自身存在的问题">
                <span style='position: absolute;left: -240px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.problem">
                <el-checkbox  label="1">技术人才不足</el-checkbox>
                <el-checkbox  label="2">资金保障困难</el-checkbox>
                <el-checkbox  label="3">保密设施设备不足 </el-checkbox>
                <el-checkbox  label="4">需求信息不了解 </el-checkbox>
              </el-checkbox-group>
              </el-form-item>

              <el-form-item label="有参军潜力的技术成果">
                <el-input placeholder="请输入有参军潜力的技术成果" v-model="com.jscg" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="服务部门">
              <el-checkbox-group v-model="com.fwbm">
                <el-checkbox  label="1">海军</el-checkbox>
                <el-checkbox  label="2">陆军</el-checkbox>
                <el-checkbox  label="3">空军</el-checkbox>
                <el-checkbox  label="4">火箭军</el-checkbox>
                <el-checkbox  label="5">战略支援部队</el-checkbox>
                <el-checkbox  label="6">军工企业</el-checkbox>
              </el-checkbox-group>
              </el-form-item>

              <el-form-item label="执行情况">
              <el-checkbox-group v-model="com.zxqk">
                <el-checkbox  label="1">申请</el-checkbox>
                <el-checkbox  label="2">在研</el-checkbox>
                <el-checkbox  label="3">验收/结题 </el-checkbox>
              </el-checkbox-group>
              </el-form-item>
              <el-form-item label="可用于军民融合共享的资源情况（不超过200字）">

                <textarea v-model="com.zyqk" rows="3" cols="20" style="width:100%;height: 350px;">
                  </textarea>
              </el-form-item>

              <el-form-item label="对军民科技协同创新的相关建议（不超过200字）">
                <textarea v-model="com.xgjy" rows="3" cols="20" style="width:100%;height: 350px;">
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

              <div style="margin:0 auto">

                <el-button size="small" @click="addProjectcom()">
                  添加项目
                </el-button>
                <el-table class="tableH" :data="com.comPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

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
      <div style="padding-left: 35%;margin: 40px 0  0  0;">
        <el-button type="primary" style="width: 120px;" @click="saveFile(com)">保存</el-button>
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
                <span style='position: relative;left: -60px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入姓名" v-model="mech.linkman" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="职务">
                <span style='position: relative;left: -60px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入职务" v-model="mech.lxzw" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <span style='position: relative;left: -70px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入手机号" v-model="mech.lxphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: relative;left: -60px;color: #f60d0d;'>*</span>
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
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入姓名" v-model="mech.fdname" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="手机号">
                <span style='position: relative;left: -60px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入手机号" v-model="mech.fdphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="mech.fdemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="事业单位法人证书正本">
                <span style='position: absolute;left: -160px;color: #f60d0d;'>*</span>
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="mech.zhengben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="事业单位法人证书副本">
                <span style='position: absolute;left: -160px;color: #f60d0d;'>*</span>
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="mech.fuben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="机构logo">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="mech.logo" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
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
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入单位名称" v-model="mech.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="单位编码">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入单位编码" v-model="mech.code" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="所在地区">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <area-cascader :level="1" v-model="mech.selected" :data="pcaa"></area-cascader>
                <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
              </el-form-item>
              <el-form-item label="联系地址">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入联系地址" v-model="mech.address" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮编">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮编" v-model="mech.ecode" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="机构性质">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                  <el-checkbox-group v-model="mech.registerNature">
                <el-checkbox label="1">企业</el-checkbox>
                <el-checkbox label="2">科研院所</el-checkbox>
                <el-checkbox label="3">高等院校 </el-checkbox>
                <el-checkbox label="4">其他</el-checkbox>
                <el-input v-if="mech.registerNature.includes('4')" placeholder="请输入其他" v-model="mech.mechregisterNatureOther" style="width:80%"></el-input>
              </el-checkbox-group>
              </el-form-item>

              <el-form-item label="机构类别">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                  <el-checkbox-group v-model="mech.org_type">
                <el-checkbox  label="1">研究开发</el-checkbox>
                <el-checkbox  label="2">科技投融资</el-checkbox>
                <el-checkbox  label="3">技术转移 </el-checkbox>
                <el-checkbox  label="4">检验检测</el-checkbox>
                <el-checkbox  label="5">创业孵化</el-checkbox>
                <el-checkbox  label="6">知识产权</el-checkbox>
                <el-checkbox  label="7">科技评估</el-checkbox>
                <el-checkbox  label="8">标准认证</el-checkbox>
                <el-checkbox  label="9">管理咨询</el-checkbox>
                <el-checkbox  label="10">综合科技服务</el-checkbox>
                <el-checkbox  label="11">其他</el-checkbox>
                <el-input v-if="mech.org_type.includes('11')" placeholder="请输入其他" v-model="mech.mechorg_typeOther" style="width:80%"></el-input>
              </el-checkbox-group>
              </el-form-item>

              <el-form-item label="可提供的服务简介（200字以内">
                <span style='position: relative;left: -210px;color: #f60d0d;'>*</span>

                <textarea rows="3" v-model="mech.serviceAbout" cols="20" style="width:100%;height: 250px;">
                  </textarea>
              </el-form-item>

              <el-form-item :label="service_amount_lastt">
                <span style='position: relative;left: -180px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入服务收入" v-model="mech.service_amount_last" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_amount_beforet">
                <span style='position: relative;left: -180px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入服务收入" v-model="mech.service_amount_before" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_amount_previoust">
                <span style='position: relative;left: -180px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入服务收入" v-model="mech.service_amount_previous" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item :label="service_quantity_lastt">
                <span style='position: relative;left: -160px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入服务数量" v-model="mech.service_quantity_last" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_quantity_beforet">
                <span style='position: relative;left: -160px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入服务数量" v-model="mech.service_quantity_before" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_quantity_previoust">
                <span style='position: relative;left: -160px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入服务数量" v-model="mech.service_quantity_previous" style="width:80%"></el-input>
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
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input v-model="mech.perNum" placeholder="请输入人员总数" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="本科及以上学历人员占比">
                <span style='position: relative;left: -180px;color: #f60d0d;'>*</span>
                <el-input v-model="mech.underPer" placeholder="请输入人员本科及以上学历人员占比" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="中高级职称以上人员占比">
                <span style='position: relative;left: -180px;color: #f60d0d;'>*</span>
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
                <el-checkbox-group v-model="mech.registerSupport">
                <el-checkbox label="1">拨款</el-checkbox>
                <el-checkbox label="2">贴息</el-checkbox>
                <el-checkbox label="3">减免税 </el-checkbox>
                <el-checkbox label="4">以上全无</el-checkbox>
                <el-checkbox label="5">其他</el-checkbox>
                <el-input v-if="mech.registerSupport.includes('5')" placeholder="请输入其他" v-model="mech.mechregisterSupportOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="执行情况">
                <el-checkbox-group v-model="mech.registerImplement">
                <el-checkbox  label="1">申请</el-checkbox>
                <el-checkbox  label="2">在研</el-checkbox>
                <el-checkbox  label="3">验收/结题 </el-checkbox>
                </el-checkbox-group>
              </el-form-item>


            </el-col>
          </el-row>
          <!-- <el-form-item label=""> -->

          <div style="margin:0 auto">

            <el-button size="small" @click="addProjectmech()">
              添加项目
            </el-button>
            <el-table class="tableH" :data="mech.PorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

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

              <el-button size="small" @click="addhonrmech()">
                添加荣誉
              </el-button>
              <el-table class="tableH" :data="mech.honor" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                <el-table-column align="center" label="名称">
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
                    <input  type="text" v-model="scope.row.issuingAgency">
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

              <el-button size="small" @click="addcjmech()">
                添加成效
              </el-button>
              <el-table class="tableH" :data="mech.workrPorcolumnDefinitions" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

                <el-table-column align="center" label="服务项目名称">
                  <template slot-scope="scope">
                    <input  type="text" v-model="scope.row.name">
                                    </template>
                </el-table-column>
                <el-table-column align="center" label="服务对象">
                  <template slot-scope="scope">
                                          <span>
                                            <input  type="text" v-model="scope.row.object">
                                          </span>
                                      </template>
                </el-table-column>
                <el-table-column align="center" label="服务时间">
                  <template slot-scope="scope">
                    <input  type="text" v-model="scope.row.time">
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
      <div style="padding-left: 35%;margin: 40px 0  0  0;">
        <el-button type="primary" style="width: 120px;" @click="saveFile(mech)">保存</el-button>
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
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入名称" v-model="school.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="统一社会信用代码">
                <span style='position: relative;left: -140px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入统一社会信用代码" v-model="school.code" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="事业单位法人证书正本">
                <span style='position: absolute;left: -160px;color: #f60d0d;'>*</span>
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="school.zhengben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="事业单位法人证书副本">
                <span style='position: absolute;left: -160px;color: #f60d0d;'>*</span>
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="school.fuben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="高校logo">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" action="/xtcx/file/upload" :file-list="school.logo" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="所在地区">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <area-cascader :level="1" v-model="school.country" :data="pcaa"></area-cascader>
                <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
              </el-form-item>
              <el-form-item label="联系地址">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入联系地址" v-model="school.address" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: relative;left: -50px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入邮箱" v-model="school.zip_code" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="单位网址">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入单位网址" v-model="school.unit_url" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="单位简介">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入单位简介" v-model="school.major_platform" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="重大平台">
                <span style='position: relative;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入重大平台" v-model="school.introduction" style="width:80%"></el-input>
              </el-form-item>
            </el-col>



          </el-row>
        </el-form>

      </el-tab-pane>

    </el-tabs>
    <el-row>
      <div style="padding-left: 35%;margin: 40px 0  0  0;">
        <el-button type="primary" style="width: 120px;" @click="saveFile(school)">保存</el-button>
      </div>
    </el-row>
  </div>



</div>
</template>

<script>
import 'vue-area-linkage/dist/index.css'; // v2 or higher
import {
  pca,
  pcaa
} from "area-data";
import {
  getAllrole,
} from '@/api/role'
import {
  getUserDetail,
  setUserDetail
} from '@/api/login'

export default {
  data() {
    return {
      userType: '',
      dateValue: '',
      selected: [],
      titleName: '',
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      service_amount_lastt: '',
      service_amount_beforet: '',
      service_amount_previoust: '',
      service_quantity_lastt: '',
      service_quantity_beforet: '',
      service_quantity_previoust: '',
      service_research_lastt: '',
      service_research_beforet: '',
      service_research_previoust: '',
      expert: {
        cardPositive: [],
        cardSide: [],
        cardHands: [],
        onepicture: [],
        name: '',
        sex: '',
        bornDate: '',
        code: '',
        shcool: '',
        edu: '',
        academic: '',
        research_field: [],
        research_fieldOther:'',
        research_area: '',
        zwname: '',
        zcname: '',
        zclevel: [],
        work_unit: '',
        mobilephone: '',
        telphone: '',
        fdemail: '',
        country: '',
        address: '',
        success_record: '',
        project_desc: '',
        research_record: [{
          projectname: '',
          projectSrc: '',
          finishcon: '',
          finishtime: '',
          rewname: '',
          rewlevel: '',
          rewtime: ''
        }]
      },
      school: {
        zhengben: [],
        fuben: [],
        logo: [],
        introduction: '',
        major_platform: '',
        unit_url: '',
        zip_code: '',
        address: '',
        country: '',
        code: '',
        name: '',
      },
      mech: {
        zhengben: [],
        fuben: [],
        logo: [],
        code: '',
        registerNature: [],
        mechregisterNatureOther:'',
        org_type: [],
        mechorg_typeOther:'',
        serviceAbout: '',
        service_amount_last: '',
        service_amount_before: '',
        service_amount_previous: '',
        service_quantity_last: '',
        service_quantity_before: '',
        service_quantity_previous: '',
        registerImplement: [],
        registerSupport: [],
        mechregisterSupportOther:'',
        honor: [{
          name: '',
          time: '',
          issuingAgency: ''
        }],
        PorcolumnDefinitions: [{
          name: '',
          time: '',
          source: ''
        }],
        workrPorcolumnDefinitions: [{
          name: '',
          object: '',
          time: ''
        }],
        MHPer: '',
        underPer: '',
        perNum: '',
        ecode: '',
        address: '',
        selected: '',
        name: '',
        fdemail: '',
        fdphone: '',
        fdname: '',
        lxemail: '',
        lxphone: '',
        lxzw: '',
        linkman: ''
      },
      com: {
        zhengben: [],
        fuben: [],
        logo: [],
        lxname: '',
        lxzw: '',
        lxphone: '',
        lxemail: '',
        lpname: '',
        lpzw: '',
        lpphone: '',
        lpemail: '',
        name: '',
        code: '',
        registered_capital: [],
        registerDate: '',
        registered_type: [],
        country: '',
        registeraddress: '',
        registerecode: '',
        is_high_new_tech: [],
        registerSite: [],
        registerSiteOther:'',
        registerHSite: [],
        registerHSiteOther:'',
        registerMarket: [],
        registerMarkeSite: [],
        domain: [],
        comdomainOther :'',
        product: '',
        inventionNum: '',
        newDrugnum: '',
        utilityModel: '',
        integratedCircuitnum: '',
        designnum: '',
        nationalNum: '',
        softwareNum: '',
        othernum: '',
        researchTeamnum: '',
        bachelorAbovenum: '',
        middleLevelnum: '',
        highleveltalentsnum: '',
        highleveltalentType: [],
        comhighleveltalentTypeOther:'',
        service_research_last: '',
        service_research_before: '',
        service_research_previous: '',
        platform: [],
        sfkyrw: [],
        szget: [],
        qdtime: '',
        szwhd: [],
        jmrhgn: [],
        ydkn: [],
        comydknOther:'',
        problem: [],
        jscg: '',
        zyqk: '',
        xgjy: '',
        fwbm: [],
        zxqk: [],
        comPorcolumnDefinitions: [{
          name: '',
          time: '',
          source: ''
        }],
      },
    }
  },
  async mounted() {
    this.listLoading = false
    if (window.sessionStorage.getItem('userType')) {
      this.userType = window.sessionStorage.getItem('userType')
    }
    if (this.userType == '1') {
      this.titleName = '专家资料完善'
    } else if (this.userType == '2') {

      this.titleName = '企业资料完善'
    } else if (this.userType == '3') {
      this.titleName = '服务机构资料完善'
    } else if (this.userType == '4') {
      this.titleName = '高效院所资料完善'
    }
    // this.loadPageList()
    var myDate = new Date();
    this.service_amount_lastt = myDate.getFullYear() + '年服务收入（万元）';
    this.service_amount_beforet = parseInt(myDate.getFullYear() - 1) + '年服务收入（万元）';
    this.service_amount_previoust = parseInt(myDate.getFullYear() - 2) + '年服务收入（万元）';

    this.service_quantity_lastt = myDate.getFullYear() + '年服务数量（次）';
    this.service_quantity_beforet = parseInt(myDate.getFullYear() - 1) + '年服务数量（次）';
    this.service_quantity_previoust = parseInt(myDate.getFullYear() - 2) + '年服务数量（次）';


    this.service_research_lastt = myDate.getFullYear() + '年研发投入（万元）';
    this.service_research_beforet = parseInt(myDate.getFullYear() - 1) + '年研发投入（万元）';
    this.service_research_previoust = parseInt(myDate.getFullYear() - 2) + '年研发投入（万元）';
  },
  computed: {},
  methods: {
    async loadPageList() {
      let {
        data,
        success
      } = await getUserDetail()
      if (this.userType == '1') {
        this.expert = JSON.parse(data.detail)
      } else if (this.userType == '2') {
        this.com = JSON.parse(data.detail)
      } else if (this.userType == '3') {
        this.mech = JSON.parse(data.detail)
      } else if (this.userType == '4') {
        this.school = JSON.parse(data.detail)
      }
      this.loading = false
    },

    async saveFile(obj) {
      // if (!this.validata.validaRole(obj)) return

      let arr = []
      obj.method = 'post'
      let {
        data,
        success
      } = await setUserDetail(obj)

      if (success) {
        this.$message({
          message: '保存成功',
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


    async handleEdit(data, type) {
      if (type === 'edit') {
        this.obj = data
        this.dialogStatus = 'update'
        this.dialogsave = true
        this.dialogadd = false
        this.dialogFormVisible = true
      } else if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await delRole(data.id)
          this.list.splice(this.list.indexOf(data), 1)
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          // this.splice(data.id, 1);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      }
    },
    addProjectexpert() {
      this.expert.research_record.push({
        projectname: '',
        projectSrc: '',
        finishcon: '',
        finishtime: '',
        rewname: '',
        rewlevel: '',
        rewtime: ''
      })
    },
    addProjectcom() {
      this.com.comPorcolumnDefinitions.push({
        name: '',
        time: '',
        source: ''
      })
    },
    addProjectmech() {
      this.mech.PorcolumnDefinitions.push({
        name: '',
        time: '',
        source: ''
      })
    },
    addhonrmech() {
      this.mech.honor.push({
        name: '',
        time: '',
        issuingAgency: ''
      })
    },
    addcjmech() {
      this.mech.workrPorcolumnDefinitions.push({
        name: '',
        object: '',
        time: ''
      })
    },
  }
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
</style>

<style>
.tab-container {
  padding: 30px;
  overflow: auto;
}

.tools {
  height: 5%
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
  padding: 0px 0px 0px 12px;
}
</style>
