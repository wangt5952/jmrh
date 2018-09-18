<template>
<div class="tab-container">
  <div class="" style="height: 80% !important;overflow-y: auto;">

    <el-tabs type="border-card">
      <el-tab-pane label="企业基础信息">
        <el-form class="" label-width="40%" style="text-align:left">
          <el-row :gutter="20">
            <el-col :span="20">

              <el-form-item label="企业名称">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入企业名称" v-model="com.name" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="企业编码">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-input placeholder="请输入企业编码" v-model="com.code" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="企业规模（注册资金）">
                <span style='position: absolute;left: -170px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.registered_capital">
                  <el-checkbox label="1">小于2000万（含）</el-checkbox>
                  </br>
                  <el-checkbox label="2">2000-5000万</el-checkbox>
                  </br>
                  <el-checkbox label="3">5000-1亿（含）</el-checkbox>
                  </br>
                  <el-checkbox label="4">1亿-2亿（含）</el-checkbox>
                  </br>
                  <el-checkbox label="5">2亿-4亿（含）</el-checkbox>
                  </br>
                  <el-checkbox label="6">4亿及以上（含）</el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="注册时间">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-date-picker v-model="com.registerDate" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="选择日期">
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
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入通讯地址" v-model="com.registeraddress" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮编">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入邮编" v-model="com.registerecode" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="是否高新技术企业">
                <span style='position: absolute;left: -140px;color: #f60d0d;'></span>
                <el-select v-model="com.is_high_new_tech" style="width:100px" placeholder="请选择">
                  <el-option label="是" key="1" value='1'>
                  </el-option>
                  <el-option label="否" key="0" value='0'>
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item v-show="com.is_high_new_tech == 1" label="企业所在地性质">
                <span style='position: absolute;left: -130px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="com.registerSite">
                  <el-checkbox label="1">国家级高新区 </el-checkbox>
                  <el-checkbox label="2">省级高新区</el-checkbox>
                  <el-checkbox label="3">国家级经开区 </el-checkbox>
                  <el-checkbox label="4">省级经开区</el-checkbox>
                  <el-checkbox label="99">其他</el-checkbox>
                  <el-input v-if="com.registerSite.includes('99')" placeholder="请输入其他" v-model="com.registerSiteOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item v-show="com.is_high_new_tech == 1 && com.registerSite.includes('1')" label="所在国家高新区">
                <span v-show="com.is_high_new_tech == 1" style='position: absolute;left: -130px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="com.registerHSite">
                  <el-checkbox label="1">南京高新区</el-checkbox>
                  <el-checkbox label="2">无锡高新区</el-checkbox>
                  <el-checkbox label="3">昆山高新区 </el-checkbox>
                  <el-checkbox label="4">徐州高新区</el-checkbox>
                  <el-checkbox label="99">其他</el-checkbox>
                  <el-input v-if="com.registerHSite.includes('99')" placeholder="请输入其他" v-model="com.registerHSiteOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="是否上市">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-select v-model="com.registerMarket" style="width:100px" placeholder="请选择">
                  <el-option label="是" key="1" value='1'>
                  </el-option>
                  <el-option label="否" key="0" value='0'>
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item v-show="com.registerMarket == 1" label="股票代码">
                <span style='position: relative;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入股票代码" v-model="com.stockCode" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item v-show="com.registerMarket == 1" label="上市地点">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="com.registerMarkeSite">
                  <el-checkbox label="1">上交所</el-checkbox>
                  <el-checkbox label="2">深交所</el-checkbox>
                  <el-checkbox label="3">新三板 </el-checkbox>
                  <el-checkbox label="4">港交所</el-checkbox>
                  <el-checkbox label="5">主版</el-checkbox>
                  <el-checkbox label="6">中小板</el-checkbox>
                  <el-checkbox label="7">创业板</el-checkbox>
                  <el-checkbox label="99">其他</el-checkbox>
                  <el-input v-if="com.registerMarkeSite.includes('99')" placeholder="请输入其他" v-model="com.registerMarkeSiteOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="所属领域">
                <span style='position: absolute;left: -80px;color: #f60d0d;'>*</span>
                <el-checkbox-group v-model="com.domain">
                  <el-checkbox label="1">智能装备</el-checkbox>
                  </br>
                  <el-checkbox label="2">电子信息</el-checkbox>
                  </br>
                  <el-checkbox label="3">新材料 </el-checkbox>
                  </br>
                  <el-checkbox label="4">航空航天</el-checkbox>
                  </br>
                  <el-checkbox label="5">生物技术与新医药</el-checkbox>
                  </br>
                  <el-checkbox label="6">能源与环保</el-checkbox>
                  </br>
                  <el-checkbox label="99">其他</el-checkbox>
                  </br>
                  <el-input v-if="com.domain.includes('99')" placeholder="请输入其他" v-model="com.domainOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="主营产品">
                <span style='position: absolute;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入主营产品" v-model="com.product" style="width:80%"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="10">


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
                <span style='position: relative;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入职务" v-model="com.lpzw" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="电话/手机">
                <span style='position: relative;left: -80px;color: #f60d0d;'></span>
                <el-input placeholder="请输入电话/手机" v-model="com.lpphone" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="邮箱">
                <span style='position: relative;left: -50px;color: #f60d0d;'></span>
                <el-input placeholder="请输入邮箱" v-model="com.lpemail" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="营业执照">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile1" :file-list="com.zhengben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="法人身份证正面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile2" :file-list="com.fuben" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="法人身份证反面">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile22" :file-list="com.fuben2" list-type="picture">
                      <el-button size="small" type="primary">点击上传</el-button>
                    </el-upload>

                  </div>
                </div>
              </el-form-item>
              <el-form-item label="企业logo">
                <div>
                  <!--这是背面照-->
                  <div class="photo photo1">
                    <el-upload class="upload-demo" :http-request="uploadSectionFile3" :file-list="com.logo" list-type="picture">
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
                <span style='position: relative;left: -50px;color: #f60d0d;'></span>
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
                <span style='position: relative;left: -160px;color: #f60d0d;'></span>
                <el-input placeholder="请输入人员总数" v-model="com.researchTeamnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="本科及以上人数(人)">
                <span style='position: relative;left: -140px;color: #f60d0d;'></span>
                <el-input placeholder="请输入本科及以上人数" v-model="com.bachelorAbovenum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="中高级职称以上人员人数(人)">
                <span style='position: relative;left: -200px;color: #f60d0d;'></span>
                <el-input placeholder="请输入中高级职称人数" v-model="com.middleLevelnum" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="研发团队高层次人才(人)">
                <span style='position: relative;left: -170px;color: #f60d0d;'></span>
                <el-input placeholder="请输入研发团队人才人数" v-model="com.highleveltalentsnum" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="高层次人才类型">
                <span style='position: absolute;left: -120px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="com.highleveltalentType">
                  <el-checkbox label="1">院士</el-checkbox>
                  </br>
                  <el-checkbox label="2">国家千人</el-checkbox>
                  </br>
                  <el-checkbox label="3">青年千人 </el-checkbox>
                  </br>
                  <el-checkbox label="4">中青年科技创新领军人才</el-checkbox>
                  </br>
                  <el-checkbox label="5">长江学者</el-checkbox>
                  </br>
                  <el-checkbox label="6">国家杰青</el-checkbox>
                  </br>
                  <el-checkbox label="99">其他</el-checkbox>
                  </br>
                  <el-input v-if="com.highleveltalentType.includes('99')" placeholder="请输入其他" v-model="com.comhighleveltalentTypeOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item :label="service_research_lastt">
                <span style='position: absolute;left: -180px;color: #f60d0d;'></span>
                <el-input placeholder="请输入研发投入" v-model="com.service_research_last" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_research_beforet">
                <span style='position: relative;left: -180px;color: #f60d0d;'></span>
                <el-input placeholder="请输入研发投入" v-model="com.service_research_before" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item :label="service_research_previoust">
                <span style='position: relative;left: -180px;color: #f60d0d;'></span>
                <el-input placeholder="请输入研发投入" v-model="com.service_research_previous" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="平台建设情况">
                <span style='position: absolute;left: -100px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="com.platform">
                  <el-checkbox label="1">企业重点实验室（国家级）</el-checkbox>
                  </br>
                  <el-checkbox label="2">企业重点实验室（省级）</el-checkbox>
                  </br>
                  <el-checkbox label="3">企业院士工作站 </el-checkbox>
                  </br>
                  <el-checkbox label="4">程技术研究中心（国家级）</el-checkbox>
                  </br>
                  <el-checkbox label="5">程技术研究中心（省级）</el-checkbox>
                  </br>
                  <el-checkbox label="99">其他</el-checkbox>
                  </br>
                  <el-input v-if="com.platform.includes('99')" placeholder="请输入其他" v-model="com.platformOther" style="width:80%"></el-input>
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
                <span style='position: absolute;left: -230px;color: #f60d0d;'></span>
                <el-select v-model="com.sfkyrw" style="width:100px" placeholder="请选择">
                  <el-option label="是" key="1" value='1'>
                  </el-option>
                  <el-option label="否" key="0" value='0'>
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="“三证”已获得">
                <span style='position: absolute;left: -100px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="com.szget">
                  <el-checkbox label="1">获得武器装备科研生产单位保密资格认证一级 </el-checkbox>
                  <el-date-picker v-if="com.szget.includes('1')" v-model="com.qdtime1" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="取得时间" style="margin-left:20px;">
                  </el-date-picker>
                  </br>
                  <el-checkbox label="2">获得武器装备科研生产单位保密资格认证二级 </el-checkbox>
                  <el-date-picker v-if="com.szget.includes('2')" v-model="com.qdtime2" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="取得时间" style="margin-left:20px;">
                  </el-date-picker>
                  </br>
                  <el-checkbox label="3">获得武器装备科研生产单位保密资格认证三级 </el-checkbox>
                  <el-date-picker v-if="com.szget.includes('3')" v-model="com.qdtime3" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="取得时间" style="margin-left:20px;">
                  </el-date-picker>
                  </br>
                  <el-checkbox label="4">武器装备科研生产许可证 </el-checkbox>
                  <el-date-picker v-if="com.szget.includes('4')" v-model="com.qdtime4" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="取得时间" style="margin-left:20px;">
                  </el-date-picker>
                  </br>
                  <el-checkbox label="5">装备承制单位资格认证 </el-checkbox>
                  <el-date-picker v-if="com.szget.includes('5')" v-model="com.qdtime5" value-format="yyyy-MM-dd" format="yyyy-MM-dd" type="date" placeholder="取得时间" style="margin-left:20px;">
                  </el-date-picker>
                  </br>
                </el-checkbox-group>
              </el-form-item>



              <el-form-item label="是否计划申请">
                <span style='position: absolute;left: -100px;color: #f60d0d;'></span>
                <el-select v-model="com.szwhd" style="width:100px" placeholder="请选择">
                  <el-option label="是" key="1" value='1'>
                  </el-option>
                  <el-option label="否" key="0" value='0'>
                  </el-option>
                </el-select>
              </el-form-item>

              <el-form-item label="军民融合建设管理情况">
                <span style='position: absolute;left: -160px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="com.jmrhgn">
                  <el-checkbox label="1">内设专门机构</el-checkbox>
                  <el-checkbox label="2">设有专项经费</el-checkbox>
                  <el-checkbox label="3">有专人负责 </el-checkbox>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item v-show="com.jmrhgn == 3" label="负责人姓名">
                <span style='position: relative;left: -100px;color: #f60d0d;'></span>
                <el-input placeholder="请输入负责人姓名" v-model="com.jmrhgnName" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item v-show="com.jmrhgn == 3" label="负责人职务">
                <span style='position: relative;left: -100px;color: #f60d0d;'></span>
                <el-input placeholder="请输入负责人职务" v-model="com.jmrhgnZW" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item v-show="com.jmrhgn == 3" label="负责人电话">
                <span style='position: relative;left: -100px;color: #f60d0d;'></span>
                <el-input placeholder="请输入负责人电话" v-model="com.jmrhgnTel" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="“民参军”过程中，遇到的障碍及困难">
                <span style='position: absolute;left: -240px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="com.ydkn">
                  <el-checkbox label="1">需求信息</el-checkbox>
                  </br>
                  <el-checkbox label="2">产品准入</el-checkbox>
                  </br>
                  <el-checkbox label="3">人才保障 </el-checkbox>
                  </br>
                  <el-checkbox label="4">审批程序 </el-checkbox>
                  </br>
                  <el-checkbox label="5">资质认证 </el-checkbox>
                  </br>
                  <el-checkbox label="6">无人管理 </el-checkbox>
                  </br>
                  <el-checkbox label="99">其他 </el-checkbox>
                  </br>
                  <el-input v-if="com.ydkn.includes('99')" placeholder="请输入其他" v-model="com.comydknOther" style="width:80%"></el-input>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="“民参军”过程中企业自身存在的问题">
                <span style='position: absolute;left: -240px;color: #f60d0d;'></span>
                <el-checkbox-group v-model="com.problem">
                  <el-checkbox label="1">技术人才不足</el-checkbox>
                  </br>
                  <el-checkbox label="2">资金保障困难</el-checkbox>
                  </br>
                  <el-checkbox label="3">保密设施设备不足 </el-checkbox>
                  </br>
                  <el-checkbox label="4">需求信息不了解 </el-checkbox>
                </el-checkbox-group>
              </el-form-item>

              <el-form-item label="有参军潜力的技术成果">
                <el-input placeholder="请输入有参军潜力的技术成果" v-model="com.jscg" style="width:80%"></el-input>
              </el-form-item>

              <el-form-item label="可用于军民融合共享的资源情况（不超过200字）">

                <textarea v-model="com.zyqk" rows="3" cols="20" style="width:100%;height: 120px;">
                  </textarea>
              </el-form-item>

              <el-form-item label="对军民科技协同创新的相关建议（不超过200字）">
                <textarea v-model="com.xgjy" rows="3" cols="20" style="width:100%;height: 120px;">
                    </textarea>
              </el-form-item>

            </el-col>


          </el-row>
        </el-form>
      </el-tab-pane>


      <el-tab-pane label="创新项目">
        <el-form class="cxxm" label-width="40%" style="text-align:left">
          <el-row :gutter="24">
            <el-col :span="24">

              <div style="margin:0 auto">

                <el-button size="small" @click="addProjectcom()">
                  添加项目
                </el-button>
                <el-form v-for="item in com.comPorcolumnDefinitions" class="" label-width="40%" style="text-align:left;border: 1px solid#ccc;border-radius: 4px;margin: 15px 0;padding: 15px;">
                  <el-row :gutter="24">
                    <el-col :span="8">


                      <el-form-item label="项目名称">
                        <el-input placeholder="请输入项目名称" v-model="item.name" style="width:130%"></el-input>
                      </el-form-item>

                      <el-form-item label="起止时间">

                        <el-date-picker v-model="item.time" type="datetimerange" value-format="yyyy-MM-dd" format="yyyy-MM-dd" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['12:00:00']" style="width:130%">
                        </el-date-picker>
                      </el-form-item>


                      <el-form-item label="项目来源">
                        <el-input placeholder="请输入项目来源" v-model="item.source" style="width:130%"></el-input>
                      </el-form-item>

                    </el-col>
                    <el-col :span="16">

                      <el-form-item label="服务部门">
                        <el-checkbox-group v-model="item.fwbm">
                          <el-checkbox label="1">海军</el-checkbox>
                          <el-checkbox label="2">陆军</el-checkbox>
                          <el-checkbox label="3">空军</el-checkbox>
                          <el-checkbox label="4">火箭军</el-checkbox>
                          <el-checkbox label="5">战略支援部队</el-checkbox>
                          <el-checkbox label="6">军工企业</el-checkbox>
                          <el-checkbox label="99">其他 </el-checkbox>
                          <el-input v-if="item.fwbm.includes('99')" placeholder="请输入其他" v-model="item.fwbmOther" style="width:80%"></el-input>
                        </el-checkbox-group>
                      </el-form-item>

                      <el-form-item label="执行情况">
                        <el-checkbox-group v-model="item.zxqk">
                          <el-checkbox label="1">申请</el-checkbox>
                          <el-checkbox label="2">在研</el-checkbox>
                          <el-checkbox label="3">验收/结题 </el-checkbox>
                        </el-checkbox-group>
                      </el-form-item>

                    </el-col>

                  </el-row>
                  <div style="text-align: right;">
                    <el-button size="small" @click="delProjectcom(item)" style="color:red">
                      删除项目
                    </el-button>
                  </div>
                </el-form>

              </div>
            </el-col>

          </el-row>
        </el-form>
      </el-tab-pane>
    </el-tabs>

  </div>
  <el-row>
    <div style="padding-left: 35%;margin: 40px 0  0  0;">
      <div class="" style="padding:15px">
        <el-radio-group v-model="checkStatus">
          <el-radio :label="-1">草稿</el-radio>
          <el-radio :label="0">提交待审</el-radio>
          <el-radio v-show="userType == '0' ||  userType =='101'" :label="1">直接审核</el-radio>
        </el-radio-group>
      </div>
      <div class="">
        <el-button type="primary" style="width: 120px;" @click="saveFile(checkStatus)">保存</el-button>
        <el-button type="primary" style="width: 120px;" @click="back">返回</el-button>
      </div>

    </div>
  </el-row>

</div>
</template>

<script>
import 'vue-area-linkage/dist/index.css'; // v2 or higher
import contrary from '@/assets/images/QQ.png'
import contrary2 from '@/assets/images/QQ.png'
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
import {
  getexpert,
  addLib,
  libupload
} from '@/api/library'
export default {
  data() {
    return {
      userType: '3',
      dateValue: '',
      contrary: '',
      contrary2: '',
      selected: [],
      titleName: '',
      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
      arrValue1: '',
      arrValue2: '',
      arrValue3: '',
      service_amount_lastt: '',
      service_amount_beforet: '',
      service_amount_previoust: '',
      service_quantity_lastt: '',
      service_quantity_beforet: '',
      service_quantity_previoust: '',
      service_research_lastt: '',
      service_research_beforet: '',
      service_research_previoust: '',
      com: {
        zhengben: [{
          name: '默认',
          url: this.imgBaseUrl + `/jmrhupload/def/companyZZ.png`
        }],
        fuben: [{
          name: '默认',
          url: this.imgBaseUrl + `/jmrhupload/def/idfront.png`
        }],
        fuben2: [{
          name: '默认',
          url: this.imgBaseUrl + `/jmrhupload/def/idback.png`
        }],
        logo: [{
          name: '默认',
          url: this.imgBaseUrl + `/jmrhupload/def/qylogo.png`
        }],
        lxname: '123',
        lxzw: '123',
        search_param: [],
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
        is_high_new_tech: '',
        registerSite: [],
        registerSiteOther: '',
        registerHSite: [],
        registerHSiteOther: '',
        registerMarket: [],
        stockCode: '',
        registerMarkeSite: [],
        registerMarkeSiteOther: '',
        domain: [],
        domainOther: '',
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
        comhighleveltalentTypeOther: '',
        service_research_last: '',
        service_research_before: '',
        service_research_previous: '',
        platform: [],
        platformOther: '',
        sfkyrw: [],
        szget: [],
        qdtime: '',
        qdtime2: '',
        qdtime3: '',
        qdtime4: '',
        qdtime5: '',
        szwhd: [],
        jmrhgn: [],
        jmrhgnName: '',
        jmrhgnZW: '',
        jmrhgnTel: '',
        ydkn: [],
        comydknOther: '',
        problem: [],
        jscg: '',
        zyqk: '',
        xgjy: '',
        comPorcolumnDefinitions: [{
          name: '',
          time: [],
          source: '',
          fwbm: [],
          fwbmOther: '',
          zxqk: [],
        }]
      },
      checkStatus: 1
    }
  },
  async mounted() {
    this.listLoading = false
    if (this.$route.params.objData) {
      this.com = JSON.parse(this.$route.params.objData)
    }

    this.userType = window.sessionStorage.getItem('userType')
    if (this.userType == '0' || this.userType == '101') {
      this.checkStatus = 1
    } else {
      this.checkStatus = 0
    }
    var myDate = new Date();
    this.service_amount_lastt = parseInt(myDate.getFullYear() - 1) + '年服务收入（万元）';
    this.service_amount_beforet = parseInt(myDate.getFullYear() - 2) + '年服务收入（万元）';
    this.service_amount_previoust = parseInt(myDate.getFullYear() - 3) + '年服务收入（万元）';

    this.service_quantity_lastt = parseInt(myDate.getFullYear() - 1) + '年服务数量（次）';
    this.service_quantity_beforet = parseInt(myDate.getFullYear() - 2) + '年服务数量（次）';
    this.service_quantity_previoust = parseInt(myDate.getFullYear() - 3) + '年服务数量（次）';


    this.service_research_lastt = parseInt(myDate.getFullYear() - 1) + '年研发投入（万元）';
    this.service_research_beforet = parseInt(myDate.getFullYear() - 2) + '年研发投入（万元）';
    this.service_research_previoust = parseInt(myDate.getFullYear() - 3) + '年研发投入（万元）';
  },
  computed: {},
  methods: {

    async uploadSectionFile1(param) {
      this.com.zhengben = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `/jmrhupload/user/` + data
        this.com.zhengben.push(arro)
      }
    },

    async uploadSectionFile2(param) {
      this.com.fuben = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `/jmrhupload/user/` + data
        this.com.fuben.push(arro)
      }
    },
    async uploadSectionFile22(param) {
      this.com.fuben2 = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `/jmrhupload/user/` + data
        this.com.fuben2.push(arro)
      }
    },
    async uploadSectionFile3(param) {
      this.com.logo = []
      var fileObj = param.file;
      // 接收上传文件的后台地址
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      // 其他参数
      // form.append("xxx", xxx);
      let {
        data,
        success
      } = await libupload(form)
      if (success) {
        let arro = {}
        arro.name = data.fileName,
          arro.url = this.imgBaseUrl + `/jmrhupload/user/` + data
        this.com.logo.push(arro)
      }
    },
    back() {
      window.history.go(-1);
    },

    addCN(data) {
      debugger
      if (JSON.stringify(data.registered_capital).includes("1")) data.search_param.push('小于2000万')
      if (JSON.stringify(data.registered_capital).includes("2")) data.search_param.push('2000-5000万')
      if (JSON.stringify(data.registered_capital).includes("3")) data.search_param.push('5000-1亿')
      if (JSON.stringify(data.registered_capital).includes("4")) data.search_param.push('1亿-2亿')
      if (JSON.stringify(data.registered_capital).includes("5")) data.search_param.push('2亿-4亿')
      if (JSON.stringify(data.registered_capital).includes("6")) data.search_param.push('4亿及以上')

      if (JSON.stringify(data.registered_type).includes(1)) data.search_param.push('内资企业')
      if (JSON.stringify(data.registered_type).includes(2)) data.search_param.push('合资企业')
      if (JSON.stringify(data.registered_type).includes(3)) data.search_param.push('外资企业')

      if (JSON.stringify(data.registerSite).includes(1)) data.search_param.push('国家级高新区')
      if (JSON.stringify(data.registerSite).includes(2)) data.search_param.push('省级高新区')
      if (JSON.stringify(data.registerSite).includes(3)) data.search_param.push('国家级经开区')
      if (JSON.stringify(data.registerSite).includes(4)) data.search_param.push('省级经开区')

      if (JSON.stringify(data.registerHSite).includes(1)) data.search_param.push('南京高新区')
      if (JSON.stringify(data.registerHSite).includes(2)) data.search_param.push('无锡高新区')
      if (JSON.stringify(data.registerHSite).includes(3)) data.search_param.push('昆山高新区')
      if (JSON.stringify(data.registerHSite).includes(4)) data.search_param.push('徐州高新区')

      if (JSON.stringify(data.registerMarkeSite).includes(1)) data.search_param.push('上交所')
      if (JSON.stringify(data.registerMarkeSite).includes(2)) data.search_param.push('深交所')
      if (JSON.stringify(data.registerMarkeSite).includes(3)) data.search_param.push('新三板')
      if (JSON.stringify(data.registerMarkeSite).includes(4)) data.search_param.push('港交所')
      if (JSON.stringify(data.registerMarkeSite).includes(5)) data.search_param.push('主版')
      if (JSON.stringify(data.registerMarkeSite).includes(6)) data.search_param.push('中小板')
      if (JSON.stringify(data.registerMarkeSite).includes(7)) data.search_param.push('创业板')

      if (JSON.stringify(data.domain).includes(1)) data.search_param.push('智能装备')
      if (JSON.stringify(data.domain).includes(2)) data.search_param.push('电子信息')
      if (JSON.stringify(data.domain).includes(3)) data.search_param.push('新材料')
      if (JSON.stringify(data.domain).includes(4)) data.search_param.push('航空航天')
      if (JSON.stringify(data.domain).includes(5)) data.search_param.push('生物技术与新医药')
      if (JSON.stringify(data.domain).includes(6)) data.search_param.push('能源与环保')

      if (JSON.stringify(data.highleveltalentType).includes(1)) data.search_param.push('院士')
      if (JSON.stringify(data.highleveltalentType).includes(2)) data.search_param.push('国家千人')
      if (JSON.stringify(data.highleveltalentType).includes(3)) data.search_param.push('青年千人')
      if (JSON.stringify(data.highleveltalentType).includes(4)) data.search_param.push('中青年科技创新领军人才')
      if (JSON.stringify(data.highleveltalentType).includes(5)) data.search_param.push('长江学者')
      if (JSON.stringify(data.highleveltalentType).includes(6)) data.search_param.push('国家杰青')

      if (JSON.stringify(data.platform).includes(1)) data.search_param.push('企业重点实验室（国家级）')
      if (JSON.stringify(data.platform).includes(2)) data.search_param.push('企业重点实验室（省级）')
      if (JSON.stringify(data.platform).includes(3)) data.search_param.push('企业院士工作站')
      if (JSON.stringify(data.platform).includes(4)) data.search_param.push('程技术研究中心（国家级）')
      if (JSON.stringify(data.platform).includes(5)) data.search_param.push('程技术研究中心（省级）')

      if (JSON.stringify(data.szget).includes(1)) data.search_param.push('获得武器装备科研生产单位保密资格认证一级')
      if (JSON.stringify(data.szget).includes(2)) data.search_param.push('获得武器装备科研生产单位保密资格认证二级')
      if (JSON.stringify(data.szget).includes(3)) data.search_param.push('获得武器装备科研生产单位保密资格认证三级')
      if (JSON.stringify(data.szget).includes(4)) data.search_param.push('武器装备科研生产许可证')
      if (JSON.stringify(data.szget).includes(5)) data.search_param.push('装备承制单位资格认证')

      if (JSON.stringify(data.jmrhgn).includes(1)) data.search_param.push('内设专门机构')
      if (JSON.stringify(data.jmrhgn).includes(2)) data.search_param.push('设有专项经费')
      if (JSON.stringify(data.jmrhgn).includes(3)) data.search_param.push('有专人负责')

      if (JSON.stringify(data.ydkn).includes(1)) data.search_param.push('需求信息')
      if (JSON.stringify(data.ydkn).includes(2)) data.search_param.push('产品准入')
      if (JSON.stringify(data.ydkn).includes(3)) data.search_param.push('人才保障')
      if (JSON.stringify(data.ydkn).includes(4)) data.search_param.push('审批程序')
      if (JSON.stringify(data.ydkn).includes(5)) data.search_param.push('资质认证')
      if (JSON.stringify(data.ydkn).includes(6)) data.search_param.push('无人管理')

      if (JSON.stringify(data.problem).includes(1)) data.search_param.push('技术人才不足')
      if (JSON.stringify(data.problem).includes(2)) data.search_param.push('资金保障困难')
      if (JSON.stringify(data.problem).includes(3)) data.search_param.push('保密设施设备不足')
      if (JSON.stringify(data.problem).includes(4)) data.search_param.push('需求信息不了解')


      let arr = data.comPorcolumnDefinitions
      let arr1 = []
      let arr2 = []
      for (var i in arr) {
        arr1 = arr[i].fwbm
        arr2 = arr[i].zxqk
      }
      if (arr1.includes("1")) data.search_param.push('海军')
      if (arr1.includes("2")) data.search_param.push('陆军')
      if (arr1.includes("3")) data.search_param.push('空军')
      if (arr1.includes("4")) data.search_param.push('火箭军')
      if (arr1.includes("5")) data.search_param.push('战略支援部队')
      if (arr1.includes("6")) data.search_param.push('军工企业')

      if (arr2.includes("1")) data.search_param.push('申请')
      if (arr2.includes("2")) data.search_param.push('在研')
      if (arr2.includes("3")) data.search_param.push('验收/结题')

      if (data.country.length > 0) {
        this.loadOneTree(data.country[0])
        this.loadtwoTree(data.country[0], data.country[1])
        this.loadThreeTree(data.country[1], data.country[2])
        data.search_param.push(this.arrValue1)
        data.search_param.push(this.arrValue2)
        data.search_param.push(this.arrValue3)
      }
      data.search_param.push(JSON.stringify(data))
      return data
    },

    loadOneTree(code) {
      let pcadata = this.pcaa
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
        if (j == code) {
          let obj = {}
          obj.name = j
          obj.value = arr[j]
          this.arrValue1 = obj.value
        }
      }
    },
    loadtwoTree(code1, code2) {
      this.arrValue2 = []
      let pcadata = this.pcaa
      let arr = []
      for (var i in pcadata) {
        if (i == code1) {
          let obj = {}
          obj.date = i
          obj.value = pcadata[i]
          arr.push(obj)
        }
      }
      arr = arr[0].value
      for (var j in arr) {
        if (j == code2) {
          let obj = {}
          obj.name = j
          obj.value = arr[j]
          this.arrValue2 = obj.value
        }
      }
    },
    loadThreeTree(code1, code2) {
      this.arrValue3 = []
      let pcadata = this.pcaa
      let arr = []
      for (var i in pcadata) {
        if (i == code1) {
          let obj = {}
          obj.date = i
          obj.value = pcadata[i]
          arr.push(obj)
        }
      }
      if (arr.length > 0) {
        arr = arr[0].value
        for (var j in arr) {
          if (j == code2) {
            let obj = {}
            obj.name = j
            obj.value = arr[j]
            this.arrValue3 = obj.value
          }
        }
      }
    },
    async saveFile(checkStatus) {
      if (!this.validata.validaEnterprise(this.com)) return

      let arr = {}
      arr.formType = '2'
      arr.checkStatus = checkStatus
      arr.id = this.$route.params.objId
      this.com.search_param = []
      this.com = this.addCN(this.com)
      arr.detail = JSON.stringify(this.com)
      let {
        data,
        success
      } = await addLib(arr)

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
        source: '',
        fwbm: [],
        zxqk: [],
      })
    },
    delProjectcom(item) {
      this.com.comPorcolumnDefinitions.splice(this.com.comPorcolumnDefinitions.indexOf(item), 1)
      this.$message({
        type: 'info',
        message: '删除成功'
      });
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
.tools {
  height: 5%
}

.cxxm .el-input__inner {
  padding: 0 30px !important;
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
