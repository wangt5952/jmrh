<template>
<div class="tab-container">
  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span>关键字</span>
      <el-input v-model="input.objName" placeholder="" style="width:200px;"></el-input>
      <span style="margin-left: 15px;">是否可见</span>
      <el-select v-model="input.status" style="width:100px;height:30px" placeholder="请选择">
        <el-option label="请选择" key="" value="">
        </el-option>
        <el-option label="可见" key=1 value=1>
        </el-option>
        <el-option label="不可见" key=0 value=0>
        </el-option>
      </el-select>
      <span style="margin-left: 15px;">状态</span>
      <el-select v-model="input.checkStatus" style="width:100px;height:30px" placeholder="请选择">
        <el-option label="草稿" :key=-1 :value=-1>
        </el-option>
        <el-option label="待审核" :key=0 :value=0>
        </el-option>
        <el-option label="已发布" :key=1 :value=1>
        </el-option>
        <el-option label="驳回" :key=2 :value=2>
        </el-option>
      </el-select>
      <span style="margin-left: 15px;">信用级别</span>
      <el-select v-model="input.creditLevel" style="width:100px;height:30px" placeholder="请选择">
        <el-option label="请选择" key="" value="">
        </el-option>
        <el-option label="1" key="1" value="1">
        </el-option>
        <el-option label="2" key="2" value="2">
        </el-option>
        <el-option label="3" key="3" value="3">
        </el-option>
        <el-option label="4" key="4" value="4">
        </el-option>
        <el-option label="5" key="5" value="4">
        </el-option>
      </el-select>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary">查询</el-button>
      <div class="" style="padding:10px 0px;">
        <el-button style="" @click="handleEdit" type="primary">添加企业</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1" style="" @click="plsj" type="primary">批量上架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1" style="" @click="pldc" type="primary">批量导出</el-button>
      </div>

    </div>
  </div>



  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:5px;width:100%;font-size:12px;">
    <el-table-column type="selection" width="30">
    </el-table-column>
    <el-table-column align="center" label="编号" width="120">
      <template slot-scope="scope">
                    <span>{{ scope.row.number }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="企业名">
      <template slot-scope="scope">
                    <div @click="showDetail(scope.row,'edit')" class="clickText" >{{ scope.row.name }}</div>
                </template>
    </el-table-column>
    <el-table-column align="center" label="营业执照号">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.code}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 0" align="center" label="创建时间">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.createTime | formatTime}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 0" align="center" label="修改时间 ">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.updateTime | formatTime}}</span>
                </template>
    </el-table-column>



    <el-table-column v-if="tfcheckStatus == 1" align="center" label="所属领域">
      <template slot-scope="scope">
                                      <span v-if='scope.row.domain.includes(1)'>智能装备</span>
                                      <span v-if='scope.row.domain.includes(2)'>电子信息</span>
                                      <span v-if='scope.row.domain.includes(3)'>新材料</span>
                                      <span v-if='scope.row.domain.includes(4)'>航空航天</span>
                                      <span v-if='scope.row.domain.includes(5)'>生物技术与新医药</span>
                                      <span v-if='scope.row.domain.includes(6)'>能源与环保</span>
                                      <span v-if='scope.row.domain.includes(99)'>{{scope.row.domainOther}}</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="信用级别"  width="70">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.creditLevel}}</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="是否可见"  width="70">
      <template slot-scope="scope">
                        <span v-if="scope.row.status == 1">可见</span>
                        <span v-if="scope.row.status == 0">不可见</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="发布人"  width="80">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.creater}}</span>
                    </template>
    </el-table-column>


    <el-table-column v-if="tfcheckStatus == 1" align="center" label="审核用户">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.creater}}</span>
                    </template>
    </el-table-column>
    <el-table-column  align="center" label="状态" width="70px;">
      <template slot-scope="scope">
                            <span v-show="tfcheckStatus == -1">草稿</span>
                            <span v-show="tfcheckStatus == 0">审核</span>
                            <span v-show="tfcheckStatus == 1">已发布</span>
                            <span v-show="tfcheckStatus == 2">驳回</span>
                        </template>
    </el-table-column>

    <el-table-column v-show="userType =='0'" align="center" label="操作"  width="120">
      <template slot-scope="scope">
                                <div style="text-align:center" >
                      <span v-if="tfcheckStatus == 1" @click="handlexy(scope.row)" class="clickText">
                        信用
                      </span>
                        <span v-if="tfcheckStatus == 0" @click="handlesh(scope.row)" class="clickText">
                          审核
                        </span>
                      <span @click="handleEdit(scope.row,'edit')" class="clickText">
                        编辑
                      </span>
                  <span v-if="scope.row.status == 1 && tfcheckStatus == 1">  <span @click="handlexj(scope.row)" class="clickText">
                                            下架
                            </span></span>
                  <span v-if="scope.row.status == 0 && tfcheckStatus == 1">  <span @click="handlesj(scope.row)" class="clickText">
                                            上架
                  </span></span>

                    </div>
                </template>
    </el-table-column>

  </el-table>
  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>

  <el-dialog title="企业详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
    <div class="textr paddinga">
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handlePrint">打印</el-button>

      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
      <table class="tableExcelText" id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>
        <tr height=27 style='mso-height-source:userset;height:20.25pt' id='r0'>
          <td colspan=6 id='tc0' height=27 class=x21>军民科技协同创新相关企业信息表</td>
        </tr>
        <tr height=32 style='mso-height-source:userset;height:24pt' id='r1'>
          <td height=32 class=x22 style='height:24pt;'>企业名称</td>
          <td class=x23>{{detailData.name}}</td>
          <td class=x22 style='overflow:hidden;'>统一社会信用代码</td>
          <td colspan=3 class=x24>{{detailData.name}}</td>
        </tr>
        <tr height=192 style='mso-height-source:userset;height:144pt' id='r2'>
          <td height=192 class=x22 style='height:144pt;overflow:hidden;'>企业规模（注册资金）</td>
          <td class=x23 style='overflow:hidden;'>
            <el-checkbox-group v-model="detailData.registered_capital">
              <el-checkbox label="1">小于2000万（含）</el-checkbox>
              <el-checkbox label="2">2000-5000万</el-checkbox>
              <el-checkbox label="3">5000-1亿（含）</el-checkbox>
              <el-checkbox label="4">1亿-2亿（含）</el-checkbox>
              <el-checkbox label="5">2亿-4亿（含）</el-checkbox>
              <el-checkbox label="6">4亿及以上（含）</el-checkbox>
            </el-checkbox-group>
          </td>
          <td class=x22 style='overflow:hidden;'>注册时间</td>
          <td class=x27 style='overflow:hidden;'>
            {{detailData.registerDate}}
          </td>
          <td class=x22>注册类型</td>
          <td class=x27 style='overflow:hidden;'>
            <el-checkbox-group v-model="detailData.registered_type">
              <el-checkbox label="1">内资企业</el-checkbox>
              <el-checkbox label="2">合资企业</el-checkbox>
              <el-checkbox label="3">外资企业</el-checkbox>
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=19 id='r3'>
          <td height=19 class=x22 style='height:14.25pt;'>所在地区</td>
          <td colspan=5>
            <area-cascader :level="1" v-model="detailData.country" :data="pcaa"></area-cascader>
          </td>
        </tr>
        <tr height=19 id='r4'>
          <td height=19 class=x22 style='height:14.25pt;'>通讯地址</td>
          <td class=x29>{{detailData.registeraddress}}</td>
          <td class=x27>邮编</td>
          <td colspan=3 id='tc3' class=x64>{{detailData.registerecode}}</td>
        </tr>
        <tr height=448 style='mso-height-source:userset;height:336pt' id='r5'>
          <td height=448 class=x31 style='height:336pt;overflow:hidden;'>是否高新技术企业</td>
          <td class=x32 style='overflow:hidden;'>
            <el-checkbox-group v-model="detailData.is_high_new_tech">
              <el-checkbox label="1">是</el-checkbox>
              <el-checkbox label="2">否</el-checkbox>
            </el-checkbox-group>
          </td>
          <td class=x31 style='overflow:hidden;'>企业所在地性质</td>
          <td class=x32 style='overflow:hidden;'>
            <el-checkbox-group v-model="detailData.registerSite">
              <el-checkbox label="1">国家级高新区 </el-checkbox>
              <el-checkbox label="2">省级高新区</el-checkbox>
              <el-checkbox label="3">国家级经开区 </el-checkbox>
              <el-checkbox label="4">省级经开区</el-checkbox>
              <el-checkbox label="5">其他</el-checkbox>
              <!-- <el-input v-show="detailData.registerSite.includes('5')" placeholder="请输入其他" v-model="detailData.registerSiteOther" style="width:80%"></el-input> -->
            </el-checkbox-group>
          </td>
          <td class=x33 style='overflow:hidden;'>所在国家高新区</td>
          <td class=x27 style='overflow:hidden;'>
            <el-checkbox-group v-model="detailData.registerHSite">
              <el-checkbox label="1">南京高新区</el-checkbox>
              <el-checkbox label="2">无锡高新区</el-checkbox>
              <el-checkbox label="3">昆山高新区 </el-checkbox>
              <el-checkbox label="4">徐州高新区</el-checkbox>
              <el-checkbox label="9999">其他</el-checkbox>
              <!-- <span v-show="detailData.registerHSite.includes('9999')"  style="width:80%">{{detailData.registerHSite}}</span> -->
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=19 id='r6'>
          <td rowspan=4 height=76 class=x132 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:57pt;'>企业法人信息</td>
          <td colspan=2 id='tc4' class=x71>姓名：{{detailData.lpname}}</td>
          <td rowspan=4 class=x37>联系人信息</td>
          <td colspan=2 id='tc5' class=x71>姓名：{{detailData.lxname}}</td>
        </tr>
        <tr height=19 id='r7'>
          <td colspan=2 id='tc6' class=x71>手机：{{detailData.lpphone}}</td>
          <td colspan=2 id='tc7' class=x71>职务：{{detailData.lxzw}}</td>
        </tr>
        <tr height=19 id='r8'>
          <td colspan=2 id='tc8' class=x71>邮箱：{{detailData.lpemail}}</td>
          <td colspan=2 id='tc9' class=x71>手机：{{detailData.lxphone}}</td>
        </tr>
        <tr height=19 id='r9'>
          <td colspan=2 id='tc10' class=x71></td>
          <td colspan=2 id='tc11' class=x71>邮箱：{{detailData.lxemail}}</td>
        </tr>
        <tr height=19 id='r10'>
          <td rowspan=3 height=115 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:86.25pt;'>是否上市</td>
          <td rowspan=3 height=115 class=x30 style='border-bottom:1px solid windowtext;height:86.25pt;overflow:hidden;'>
            <el-checkbox-group v-model="detailData.registerMarket">
              <el-checkbox label="1">是</el-checkbox>
              <el-checkbox label="2">否</el-checkbox>
            </el-checkbox-group>
          </td>
          <td rowspan=3 height=115 class=x132 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:86.25pt;'>上市地点</td>
          <td class=x46 colspan=3>
            <el-checkbox-group v-model="detailData.registerMarkeSite">
              <el-checkbox label="1">上交所</el-checkbox>
              <el-checkbox label="2">深交所</el-checkbox>
              <el-checkbox label="3">新三板 </el-checkbox>
              <el-checkbox label="4">港交所</el-checkbox>
              <el-checkbox label="5">主版</el-checkbox>
              <el-checkbox label="6">中小板</el-checkbox>
              <el-checkbox label="7">创业板</el-checkbox>
            </el-checkbox-group>
          </td>
        </tr>

        <tr height=19 id='r13'>
          <td height=19 class=x57 style='height:14.25pt;'>所属领域</td>
          <td colspan=5 id='tc12' class=x133>
            <el-checkbox-group v-model="detailData.domain">
              <el-checkbox label="1">智能装备</el-checkbox>
              <el-checkbox label="2">电子信息</el-checkbox>
              <el-checkbox label="3">新材料 </el-checkbox>
              <el-checkbox label="4">航空航天</el-checkbox>
              <el-checkbox label="5">生物技术与新医药</el-checkbox>
              <el-checkbox label="6">能源与环保</el-checkbox>
              <el-checkbox label="999">其他</el-checkbox>
              <!-- <el-input v-show="detailData.domain.includes('999')" placeholder="请输入其他" v-model="detailData.comdomainOther" style="width:80%"></el-input> -->
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=19 id='r14'>
          <td height=19 class=x22 style='height:14.25pt;'>主营产品</td>
          <td colspan=5 id='tc13' class=x64>{{detailData.product}}</td>
        </tr>
        <tr height=32 style='mso-height-source:userset;height:24pt' id='r15'>
          <td height=32 class=x62 style='height:24pt;overflow:hidden;'>获得知识产权情况</td>
          <td class=x63>发明专利</td>
          <td class=x64>{{detailData.inventionNum}}件</td>
          <td class=x65>国家新药</td>
          <td colspan=2 id='tc14' class=x64>{{detailData.inventionNum}}件</td>
        </tr>
        <tr height=48 style='mso-height-source:userset;height:36pt' id='r16'>
          <td height=48 class=x66 style='height:36pt;'></td>
          <td class=x67>实用新型</td>
          <td class=x64>{{detailData.inventionNum}}件</td>
          <td class=x68 style='overflow:hidden;'>集成电路布图设计专有权</td>
          <td colspan=2 id='tc15' class=x64>{{detailData.inventionNum}}件</td>
        </tr>
        <tr height=64 style='mso-height-source:userset;height:48pt' id='r17'>
          <td height=64 class=x66 style='height:48pt;'></td>
          <td class=x65>外观设计</td>
          <td class=x69>{{detailData.inventionNum}}件</td>
          <td class=x27 style='overflow:hidden;'>国防专利(指与国防和军队建设有关的知识产权)</td>
          <td colspan=2 id='tc16' class=x64>{{detailData.inventionNum}}件</td>
        </tr>
        <tr height=19 id='r18'>
          <td height=19 class=x66 style='height:14.25pt;'></td>
          <td class=x65>软件著作权</td>
          <td class=x64>{{detailData.inventionNum}}件</td>
          <td class=x27>其他<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></td>
          <td colspan=2 id='tc17' class=x64>{{detailData.inventionNum}}件</td>
        </tr>
        <tr height=144 style='mso-height-source:userset;height:108pt' id='r19'>
          <td rowspan=2 height=288 class=x70>企业研发情况</td>
          <td class=x71 style='overflow:hidden;'>研发团队人员总数</td>
          <td class=x71 style='overflow:hidden;'>本科及以上人数（占比）</td>
          <td class=x71 style='overflow:hidden;'>中高级职称以上人员人数（占比）</td>
          <td class=x72 style='overflow:hidden;'>研发团队高层次人才（指入选国家各类人才培养计划人员，包括院士、千人、青年千人等）</td>
          <td class=x62 style='overflow:hidden;'>高层次人才类型</td>
        </tr>
        <tr height=144 style='mso-height-source:userset;height:108pt' id='r20'>
          <td class=x72>
            {{detailData.researchTeamnum}}人
          </td>
          <td class=x74>
            {{detailData.bachelorAbovenum}}人
          </td>
          <td class=x74>{{detailData.middleLevelnum}}人</td>
          <td class=x74>{{detailData.researchTeamnum}}人</td>
          <td class=x33 style='overflow:hidden;'>
            <el-checkbox-group v-model="detailData.highleveltalentType">
              <el-checkbox label="1">院士</el-checkbox>
              <el-checkbox label="2">国家千人</el-checkbox>
              <el-checkbox label="3">青年千人 </el-checkbox>
              <el-checkbox label="4">中青年科技创新领军人才</el-checkbox>
              <el-checkbox label="5">长江学者</el-checkbox>
              <el-checkbox label="6">国家杰青</el-checkbox>
              <el-checkbox label="7">其他</el-checkbox>
              <!-- <el-input v-show="detailData.highleveltalentType.includes('7')" placeholder="请输入其他" v-model="detailData.comhighleveltalentTypeOther" style="width:80%"></el-input> -->
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=48 style='mso-height-source:userset;height:36pt' id='r21'>
          <td class=x76 style='overflow:hidden;'>近3年研发投入（万元）</td>
          <td class=x77>{{lastt}}年研发投入（万元）</td>
          <td colspan=2 class=x74>{{beforet}}年研发投入（万元）</td>
          <td colspan=3 class=x74>{{previoust}}年研发投入（万元）</td>
        </tr>
        <tr height=19 id='r22'>
          <td></td>
          <td class=x79>{{detailData.service_research_last}}</td>
          <td colspan=2 class=x74>{{detailData.service_research_before}}</td>
          <td colspan=3 class=x27>{{detailData.service_research_previous}}</td>
        </tr>
        <tr height=30 style='mso-height-source:userset;height:22.5pt' id='r23'>
          <td height=30 class=x81 style='height:22.5pt;'>平台建设情况</td>
          <td colspan=5 id='tc18' class=x120>
            <el-checkbox-group v-model="detailData.platform">
              <el-checkbox label="1">企业重点实验室（国家级）</el-checkbox>
              <el-checkbox label="2">企业重点实验室（省级）</el-checkbox>
              <el-checkbox label="3">企业院士工作站 </el-checkbox>
              <el-checkbox label="4">程技术研究中心（国家级）</el-checkbox>
              <el-checkbox label="5">程技术研究中心（省级）</el-checkbox>
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=45 style='mso-height-source:userset;height:33.75pt' id='r24'>
          <td rowspan=6 height=64 class=x70 style='border-right:1px solid windowtext;height:48pt;overflow:hidden;'>军民科技协同创新情况</td>
          <td class=x85 style='overflow:hidden;'>是否承担过武器装备科研生产任务</td>
          <td colspan=4 id='tc19' class=x120 style='border-right:1px solid windowtext;'>
            <el-checkbox-group v-model="detailData.sfkyrw">
              <el-checkbox label="1">是</el-checkbox>
              <el-checkbox label="2">否</el-checkbox>
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=19 id='r25'>
          <td rowspan=5 height=95 class=x103 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:71.25pt;overflow:hidden;'>“三证”拥有情况</td>
          <td rowspan=3 height=57 class=x104 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:42.75pt;'>已获得</td>
          <td colspan=3 id='tc20' class=x111 style='border-right:1px solid windowtext;overflow:hidden;'>
            <font class="font7">武器装备科研生产单位保密资格认证（一级、二级、三级）</font>
            <font class="font11">{{detailData.qdtime}}年</font>
            <font class="font7">取得</font>
          </td>
        </tr>
        <tr height=19 id='r26'>
          <td height=19 class=x75 style='height:14.25pt;'></td>
          <td colspan=3 id='tc21' class=x115 style='border-right:1px solid windowtext;overflow:hidden;'>
            <font class="font7">武器装备科研生产许可证<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font11">{{detailData.qdtime2}}年</font>
            <font class="font7">取得</font>
          </td>
        </tr>
        <tr height=19 id='r27'>
          <td height=19 class=x75 style='height:14.25pt;'></td>
          <td colspan=3 id='tc22' class=x116>
            <font class="font7">装备承制单位资格认证<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font11">{{detailData.qdtime3}}年</font>
            <font class="font7">取得</font>
          </td>
        </tr>
        <tr height=19 id='r28'>
          <td rowspan=2 height=38 class=x104 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:28.5pt;'>未获得</td>
          <td rowspan=2 height=38 class=x122 style='border-bottom:1px solid windowtext;height:28.5pt;'>是否计划申请</td>
          <td colspan=2 rowspan=2 id='tc23' class=x88 style='border-right:1px solid windowtext;'>
            <el-checkbox-group v-model="detailData.sfkyrw">
              <el-checkbox label="1">是</el-checkbox>
              <el-checkbox label="2">否</el-checkbox>
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=19 id='r29'>
        </tr>
        <tr height=30 style='mso-height-source:userset;height:22.5pt' id='r30'>
          <td height=30 class=x75 style='height:22.5pt;'></td>
          <td class=x103 style='overflow:hidden;'>军民融合建设管理情况</td>
          <td colspan=4 id='tc25' class=x120>
            <el-checkbox-group v-model="detailData.jmrhgn">
              <el-checkbox label="1">内设专门机构</el-checkbox>
              <el-checkbox label="2">设有专项经费</el-checkbox>
              <el-checkbox label="3">有专人负责 </el-checkbox>
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=120 style='mso-height-source:userset;height:90pt' id='r31'>
          <td rowspan=3 height=158 class=x72 style='border-right:1px solid windowtext;height:118.5pt;overflow:hidden;'>承担的省部级以上军民科技协同创新项目</td>
          <td class=x104>项目名称</td>
          <td colspan=2 class=x104 style='overflow:hidden;'>起止时间<br><span style='mso-spacerun:yes'>&nbsp; </span>年<span style='mso-spacerun:yes'>&nbsp; </span>月--<span style='mso-spacerun:yes'>&nbsp; </span>年<span style='mso-spacerun:yes'>&nbsp; </span>月</td>
          <td colspan=2 class=x105 style='overflow:hidden;'>项目来源<br>(按照“国家/省级XX项目”的形式填写，如江苏省军民融合发展引导资金项目)</td>
        </tr>
        <tr v-for="item in detailData.comPorcolumnDefinitions" height=19 id='r32'>
          <td class=x108>{{item.name}}</td>
          <td colspan=2 class=x108>{{item.time}}</td>
          <td colspan=2 class=x109>{{item.source}}</td>
        </tr>


        <tr height=19 id='r40'>
          <td colspan=2 id='tc27' height=19 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;overflow:hidden;'>服务部门</td>
          <td colspan=4 id='tc28' class=x120>
            <el-checkbox-group v-model="detailData.fwbm">
              <el-checkbox label="1">海军</el-checkbox>
              <el-checkbox label="2">陆军</el-checkbox>
              <el-checkbox label="3">空军</el-checkbox>
              <el-checkbox label="4">火箭军</el-checkbox>
              <el-checkbox label="5">战略支援部队</el-checkbox>
              <el-checkbox label="6">军工企业</el-checkbox>
            </el-checkbox-group>
          </td>
        </tr>

        <tr height=19 id='r40'>
          <td colspan=2 id='tc27' height=19 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;overflow:hidden;'>执行情况</td>
          <td colspan=4 id='tc28' class=x120>
            <el-checkbox-group v-model="detailData.zxqk">
              <el-checkbox label="1">申请</el-checkbox>
              <el-checkbox label="2">在研</el-checkbox>
              <el-checkbox label="3">验收/结题 </el-checkbox>
            </el-checkbox-group>
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=19 id='r40'>
          <td colspan=2 id='tc27' height=19 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;overflow:hidden;'>“民参军”过程中，遇到的障碍及困难</td>
          <td colspan=4 id='tc28' class=x120>
            <el-checkbox-group v-model="detailData.ydkn">
              <el-checkbox label="1">需求信息</el-checkbox>
              <el-checkbox label="2">产品准入</el-checkbox>
              <el-checkbox label="3">人才保障 </el-checkbox>
              <el-checkbox label="4">审批程序 </el-checkbox>
              <el-checkbox label="5">资质认证 </el-checkbox>
              <el-checkbox label="6">无人管理 </el-checkbox>
              <el-checkbox label="7">其他 </el-checkbox>
              <!-- <el-input v-show="detailData.ydkn.includes('7')" placeholder="请输入其他" v-model="detailData.comydknOther" style="width:80%"></el-input> -->
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=19 id='r41'>
          <td colspan=2 id='tc29' height=19 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;overflow:hidden;'>“民参军”过程中企业自身存在的问题</td>
          <td colspan=4 id='tc30' class=x120>
            <el-checkbox-group v-model="detailData.problem">
              <el-checkbox label="1">技术人才不足</el-checkbox>
              <el-checkbox label="2">资金保障困难</el-checkbox>
              <el-checkbox label="3">保密设施设备不足 </el-checkbox>
              <el-checkbox label="4">需求信息不了解 </el-checkbox>
            </el-checkbox-group>
          </td>
        </tr>
        <tr height=19 id='r42'>
          <td colspan=2 id='tc31' height=19 class=x117 style='border-right:1px solid windowtext;height:14.25pt;overflow:hidden;'>对军民科技协同创新的相关建议（不超过200字）</td>
          <td colspan=6 class=x122>{{detailData.xgjy}}</td>
        </tr>
        <tr height=19 id='r43'>
          <td colspan=2 id='tc32' height=19 class=x117 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;'>有参军潜力的技术成果</td>
          <td colspan=6 class=x130>{{detailData.jscg}}</td>
        </tr>

      </table>

    </div>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dialogShowDep = false">关闭</el-button>
    </span>
  </el-dialog>


  <el-dialog title="信用等级" :visible.sync="dialogShowLevel" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="24">
        <el-col :span="24">

          <el-form-item label="信用等级">
            <el-select v-model="xyset.creditLevel" style="height:30px" placeholder="请选择">
              <el-option label="请选择" key="" value="">
              </el-option>
              <el-option label="1" key="1" value="1">
              </el-option>
              <el-option label="2" key="2" value="2">
              </el-option>
              <el-option label="3" key="3" value="3">
              </el-option>
              <el-option label="4" key="4" value="4">
              </el-option>
              <el-option label="5" key="5" value="4">
              </el-option>
            </el-select>
          </el-form-item>

        </el-col>

      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
        <el-button type="primary" style="" @click="saveFile(xyset)">保存</el-button>
        <el-button type="primary" @click="dialogShowLevel = false">关闭</el-button>
      </span>
  </el-dialog>


  <el-dialog title="审核" :visible.sync="dialogShowSH" width="30%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">
      <el-row :gutter="24">
        <el-col :span="24">

          <el-form-item label="审核">
            <el-select v-model="rej.way" style="height:30px" placeholder="请选择">

              <el-option label="通过" key="1" value="1">
              </el-option>
              <el-option label="驳回" key="2" value="2">
              </el-option>
            </el-select>
          </el-form-item>


          <el-form-item v-show="rej.way == '2'" label="驳回原因">
            <el-input v-model="rej.info" placeholder=""></el-input>
          </el-form-item>
        </el-col>

      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
        <el-button type="primary" style="" @click="saveReject(rej)">保存</el-button>
        <el-button type="primary" @click="dialogShowSH = false">关闭</el-button>
      </span>
  </el-dialog>


</div>
</template>

<script>
import 'vue-area-linkage/dist/index.css'; // v2 or higher
import {
  getUserDetail,
  setUserDetail
} from '@/api/login'


import {
  pca,
  pcaa
} from "area-data";
import {
  getAllrole
} from '@/api/role'
import {
  depgetAll
} from '@/api/department'
import {
  getenterprise,
  addLib,
  delLib,
  rejectUserDetail,
  offUserDetail,
  onUserDetail,
  PLrejectUserDetail,
  PLoffUserDetails,
  PLonUserDetails,
  updateLevel
} from '@/api/library'
import table2excel from 'table2excel'
import printArea from 'printArea'

export default {
  data() {
    return {
      rej: {
        way: '1',
        info: '',
        formId: ''
      },
      xyset: {
        creditLevel: '',
        id: ''
      },
      input: {
        objName: '',
        status: '',
        checkStatus: 1,
        creditLevel: '',
      },
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      dialogFormVisible: false,
      dialogShowRole: false,
      dialogShowDep: false,
      dialogShowSH: false,
      dialogShowLevel: false,
      dialogadd: false,
      dialogsave: false,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
      },
      total: null,
      textMap: {
        update: '编辑',
        dep: '部门详情',
        role: '角色详情',
        create: '添加'
      },

      pcaa: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
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
        registered_capital: '1',
        registerDate: '',
        registered_type: '',
        country: '',
        registeraddress: '',
        registerecode: '',
        is_high_new_tech: '',
        registerSite: '',
        registerHSite: '',
        registerMarket: '',
        registerMarkeSite: '',
        domain: '',
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
        highleveltalentType: '',
        service_research_last: '',
        service_research_before: '',
        service_research_previous: '',
        platform: '',
        sfkyrw: '',
        qdtime: '',
        szwhd: '',
        jmrhgn: '',
        ydkn: '',
        problem: '',
        jscg: '',
        zyqk: '',
        xgjy: '',
        fwbm: '',
        zxqk: '',
        comPorcolumnDefinitions: [{
          name: '',
          time: '',
          source: ''
        }],
      },
      treeData: [],
      loading: true,
      userType: '',
      multipleSelection: [],
      detailData: '',
      tfcheckStatus: '',

    }
  },
  async mounted() {
    if (typeof this.$route.query.checkStatus == 'number') {
      this.input.checkStatus = this.$route.query.checkStatus
    }
    this.listLoading = false
    this.loadPageList()
    this.tfcheckStatus = this.input.checkStatus
    this.userType = window.sessionStorage.getItem('userType')

  },
  computed: {},
  methods: {
    async saveFile(objdata) {
      let {
        data,
        success
      } = await updateLevel(objdata, '2')
      this.$message({
        message: '保存成功',
        type: 'success'
      });
      this.loadPageList()
    },
    handlexy(data) {
      this.dialogShowLevel = true
      this.xyset.id = data.id
    },

    async saveReject(rej) {
      if (rej.way == '1') {
        let arr = []
        arr.push(rej.formId)
        let {
          data,
          success
        } = await PLrejectUserDetail(arr)
        if (success) {
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.loadPageList()
        }
      } else {
        if(rej.info==""){
          this.$message({
            message: '请输入驳回原因！',
            type: 'success'
          });
          return
        }
        let {
          data,
          success
        } = await rejectUserDetail(rej)
        if (success) {
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.loadPageList()
        }
      }


    },
    handleSelectionChange(val) {
      let arr = []
      for (let i in val) {
        arr.push(val[i].id)
      }
      this.multipleSelection = arr;
    },
    async plsh() {
      let {
        data,
        success
      } = await PLrejectUserDetail(this.multipleSelection)
      if (success) {
        this.$message({
          type: 'success',
          message: '审核成功!'
        });
        this.loadPageList()
      }


    },

    async plsj() {
      if (this.multipleSelection.length == 0) {
        this.$message({
          type: 'success',
          message: '请勾选上架内容!'
        });
      }
      let {
        data,
        success
      } = await PLonUserDetails(this.multipleSelection, '2')
      if (success) {
        this.$message({
          type: 'success',
          message: '上架成功!'
        });
        this.loadPageList()
      }
    },
    async plxj() {
    if(this.multipleSelection.length == 0){
      this.$message({
        type: 'success',
        message: '请勾选下架内容!'
      });
    }
      let {
        data,
        success
      } = await PLoffUserDetails(this.multipleSelection, '2')
      if (success) {
        this.$message({
          type: 'success',
          message: '下架成功!'
        });
        this.loadPageList()
      }
    },
    pldc() {
      this.$message({
        type: 'success',
        message: '导出成功!'
      });
    },
    handlesh(data) {
      this.dialogShowSH = true
      this.rej.formId = data.id
    },
    async handlexj(params) {

      let obj = {}
      obj.id = params.id
      obj.type = '2'
      let {
        data,
        success
      } = await offUserDetail(obj)
      if (success) {
        this.$message({
          message: '下架成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
    async handlesj(params) {
      let obj = {}
      obj.id = params.id
      obj.type = '2'
      let {
        data,
        success
      } = await onUserDetail(obj)
      if (success) {
        this.$message({
          message: '上架成功',
          type: 'success'
        });
        this.loadPageList()
      }
    },
    handlePrint() {
      $("#tablePrint").printArea();
    },
    handleDownload() { //导出
      $("#tableExcel").table2excel({
        exclude: ".noExl", //过滤位置的 css 类名
        filename: new Date().getTime() + ".xls", //文件名称
        name: "Excel Document Name.xlsx",
        exclude_img: true,
        exclude_links: true,
        exclude_inputs: true
      })
    },
    showDetail(data) {
      this.dialogShowDep = true
      let objData
      if (this.input.checkStatus == 1) {
        objData = data.form.detail
      } else if (this.input.checkStatus == 0 || this.input.checkStatus == -1) {
        objData = data.detail
      }
      this.detailData = JSON.parse(objData)
    },
    async loadPageList() {

      if (this.input) {
        this.listQuery.objName = this.input.objName
        this.listQuery.status = this.input.status
        this.listQuery.checkStatus = this.input.checkStatus

        this.tfcheckStatus = this.input.checkStatus

        this.listQuery.creditLevel = this.input.creditLevel
      } else {
        this.listQuery.objName = ''
      }
      let {
        data,
        success
      } = await getenterprise(this.listQuery)
      if (success) {
        this.list = data.list
        this.total = data.total
        this.loading = false
      }
    },
    handleSizeChange(val) {
      if (!isNaN(val)) {
        this.listQuery.limit = val
      }
      this.loadPageList()
    },
    handleCurrentChange(val) {
      if (!isNaN(val)) {
        this.listQuery.page = val
      }
      this.loadPageList()
    },
    async loadoptions() {
      let getAlldata = await getAllrole()
      let arr = []
      getAlldata = getAlldata.data
      for (let i = 0; i < getAlldata.length; i++) {
        let obja = {}
        obja.label = getAlldata[i].roleName
        obja.value = getAlldata[i].id
        arr.push(obja)
      }
      this.options = arr
      this.loadgetUserId()
      this.loadgetdep()
    },
    async loadgetUserId() {
      let aa = this.obj
      let getUserIddata = await getUserId(this.obj.id)
      getUserIddata = getUserIddata.data.roles
      for (let i = 0; i < getUserIddata.length; i++) {
        getUserIddata[i].label = getUserIddata[i].roleName
        getUserIddata[i].value = getUserIddata[i].id
      }
      debugger
      this.selected = getUserIddata

    },
    async loadgetdep() {
      let depData = await depgetAll()
      this.treeData = depData.data
    },
    async ondep1Change(val) {
      if (val) {
        val = val
      }
    },
    async addCreate(obj) {

      if (!this.validata.validausr(obj)) return
      obj.method = 'post'
      let arr = []
      let getAlldata = this.selected
      for (let i = 0; i < getAlldata.length; i++) {
        let obja = {}
        obja.id = getAlldata[i].value
        arr.push(obja)
      }
      obj.roles = arr
      let data = await addUser(obj)
      if (data.code === 10000) {
        this.$message({
          message: '添加成功',
          type: 'success'
        });
        this.loadPageList()
        this.dialogFormVisible = false
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
      }
    },
    async saveCreate(obj) {
      // if (!this.validata.validausr(obj)) return
      let arr = {}
      arr.formType = '2'
      arr.detail = JSON.stringify(obj)
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
        this.loadPageList()
      } else {
        this.$message({
          message: data.message,
          type: 'success'
        });
      }
    },
    async handleEdit(data, type) {
      if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await delLib(data)
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
      } else if (type === 'edit') {
        let objId, objData
        if (this.input.checkStatus == 1) {
          objId = data.form.id
          objData = data.form.detail
        } else if (this.input.checkStatus == 0 || this.input.checkStatus == -1) {
          objId = data.id
          objData = data.detail
        }
        this.$router.push({
          name: 'enterpriseEdit',
          params: {
            objId: objId,
            objData: objData
          }
        })
      } else {
        this.$router.push({
          name: 'enterpriseEdit'
        })
      }
    },
    onDate1Change(val) {
      this.obj.loanDate = val
    },
    onDate2Change(val) {
      this.obj.appointmentRepaymentDate = val
    },
    onDate3Change(val) {
      this.obj.interestPayTime = val
    },
  }
}
</script>

<style lang="scss">
@import '../../styles/index.scss'; // 全局自定义的css样式
.el-checkbox__input.is-indeterminate .el-checkbox__inner,
.tableExcelText .el-checkbox__input.is-checked .el-checkbox__inner {
    background-color: #94989c;
    border-color: #94989c;
}
.tableExcelText .el-checkbox__input.is-checked+.el-checkbox__label {
    color: #000;
}
</style>
