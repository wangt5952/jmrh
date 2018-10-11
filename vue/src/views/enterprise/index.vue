<template>
<div class="tab-container">
  <div class="">
    <div class="paddingb textl paddingr" style="font-size:14px">
      <span>关键字</span>
      <el-input v-model="input.objName" placeholder="可根据企业名/营业执照号/领域/信用级别查询" style="width:20%;"></el-input>
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
        <el-option label="5" key="5" value="5">
        </el-option>
      </el-select>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary">查询</el-button>
      <div class="" style="padding:10px 0px;">
        <el-button v-show="userType =='0'|| userType =='101'" @click="handleEdit" type="primary">添加企业</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 0  || userType =='101' && tfcheckStatus == 0" style="" @click="plsh" type="primary">批量审核</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plxj" type="primary">批量下架</el-button>
        <el-button v-show="userType =='0' && tfcheckStatus == 1  || userType =='101' && tfcheckStatus == 1" style="" @click="plsj" type="primary">批量上架</el-button>
        <a :href=pldcUrl target="_blank">
          <el-button v-show="userType =='0' && tfcheckStatus == 1 " style="" type="primary">批量导出</el-button>
        </a>
      </div>

    </div>
  </div>



  <el-table v-loading="loading" ref="multipleTable" @selection-change="handleSelectionChange" class="tableH" :data="list" border style="margin-top:5px;width:100%;font-size:12px;">
    <el-table-column type="selection" width="30">
    </el-table-column>
    <el-table-column align="center" label="编号" width="150">
      <template slot-scope="scope">
                    <span>{{ scope.row.number ||scope.row.form.number }}</span>
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
                                      <span v-if='scope.row.domain.includes(1)'>智能装备;</span>
                                      <span v-if='scope.row.domain.includes(2)'>电子信息;</span>
                                      <span v-if='scope.row.domain.includes(3)'>新材料;</span>
                                      <span v-if='scope.row.domain.includes(4)'>航空航天;</span>
                                      <span v-if='scope.row.domain.includes(5)'>生物技术与新医药;</span>
                                      <span v-if='scope.row.domain.includes(6)'>能源与环保;</span>
                                      <span v-if='scope.row.domain.includes(99)'>{{scope.row.domainOther}};</span>
                </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="信用级别" width="100">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.creditLevel}}</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="是否可见" width="100">
      <template slot-scope="scope">
                        <span v-if="scope.row.status == 1">可见</span>
                        <span v-if="scope.row.status == 0">不可见</span>
                    </template>
    </el-table-column>
    <el-table-column v-if="tfcheckStatus == 1" align="center" label="发布人" width="100">
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
                            <span v-show="tfcheckStatus == 0">待审核</span>
                            <span v-show="tfcheckStatus == 1">已发布</span>
                            <span v-show="tfcheckStatus == 2">驳回</span>
                        </template>
    </el-table-column>

    <el-table-column v-if="tfcheckStatus == 2" align="center" label="驳回原因">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.info}}</span>
                    </template>
    </el-table-column>

    <el-table-column v-show="userType =='0'" align="center" label="操作" width="120">
      <template slot-scope="scope">
        <div style="text-align:center" >
          <span v-show="userType =='0' && tfcheckStatus == 1 || userType =='101' && tfcheckStatus == 1" @click="handlexy(scope.row)" class="clickText" >
            信用
          </span>
            <span v-show="userType =='0' &&tfcheckStatus == 0 || userType =='101' && tfcheckStatus == 0" @click="handlesh(scope.row)" class="clickText" >
            审核
            </span>
          <span v-show="userType =='0'&& tfcheckStatus != 0 || userType =='101' &&tfcheckStatus != 0" @click="handleEdit(scope.row,'edit')" class="clickText" >
            编辑
          </span>
      <span v-show="userType =='0' &&scope.row.status == 1 && tfcheckStatus == 1 || userType =='101'  &&scope.row.status == 1 && tfcheckStatus == 1">  <span @click="handlexj(scope.row)" class="clickText" >
            下架
                </span></span>
      <span v-show="userType =='0' &&scope.row.status == 0 && tfcheckStatus == 1 || userType =='101'  &&scope.row.status == 0 && tfcheckStatus == 1">  <span @click="handlesj(scope.row)" class="clickText" >
            上架
      </span></span>
      <span v-show="userType !='0' && userType !='101'" @click="showDetail(scope.row,'edit')" class="clickText" >
        查看
      </span>
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
        <tr height=52 style='mso-height-source:userset;height:54pt' id='r1'>
          <td height=32 class=x22 style='height:44pt;'>企业名称</td>
          <td class=x23>{{detailData.name}}</td>
          <td class=x22 style='overflow:hidden;'>统一社会信用代码</td>
          <td colspan=3 class=x24>{{detailData.code}}</td>
        </tr>
        <tr height=92 style='mso-height-source:userset;height:54pt' id='r2'>
          <td height=92 class=x22 style='height:44pt;overflow:hidden;'>企业规模（注册资金）</td>
          <td class=x23 style='overflow:hidden;'>
            <span v-if="detailData.registered_capital.includes('1')">小于2000万（含）;</span>
            <span v-if="detailData.registered_capital.includes('2')">2000-5000万;</span>
            <span v-if="detailData.registered_capital.includes('3')">5000-1亿（含）;</span>
            <span v-if="detailData.registered_capital.includes('4')">1亿-2亿（含）;</span>
            <span v-if="detailData.registered_capital.includes('5')">2亿-4亿（含）;</span>
            <span v-if="detailData.registered_capital.includes('6')">4亿及以上（含）;</span>
          </td>
          <td class=x22 style='overflow:hidden;'>注册时间</td>
          <td class=x27 style='overflow:hidden;'>
            {{detailData.registerDate}}
          </td>
          <td class=x22>注册类型</td>
          <td class=x27 style='overflow:hidden;'>
            <span v-if="detailData.registered_type.includes('1')">内资企业;</span>
            <span v-if="detailData.registered_type.includes('2')">合资企业;</span>
            <span v-if="detailData.registered_type.includes('3')">外资企业;</span>
          </td>
        </tr>
        <tr height=19 id='r3'>
          <td height=19 class=x22 style='height:54.25pt;'>所在地区</td>
          <td colspan=5>
            {{detailData.search_paramobj}}
          </td>
        </tr>
        <tr height=19 id='r4'>
          <td height=19 class=x22 style='height:54.25pt;'>通讯地址</td>
          <td class=x29>{{detailData.registeraddress}}</td>
          <td class=x27>邮编</td>
          <td colspan=3 id='tc3' class=x64>{{detailData.registerecode}}</td>
        </tr>
        <tr height=66 style='mso-height-source:userset;height:66pt' id='r5'>
          <td height=66 class=x31 style='height:66pt;overflow:hidden;'>是否高新技术企业</td>
          <td class=x32 style='overflow:hidden;'>
            <span v-if="detailData.is_high_new_tech.includes('1')">是</span>
            <span v-if="detailData.is_high_new_tech.includes('2')">否</span>
          </td>
          <td class=x31 style='overflow:hidden;'>企业所在地性质</td>
          <td class=x32 style='overflow:hidden;'>
            <span v-if="detailData.registerSite.includes('1')">国家级高新区;</span>
            <span v-if="detailData.registerSite.includes('2')">省级高新区;</span>
            <span v-if="detailData.registerSite.includes('3')">国家级经开区;</span>
            <span v-if="detailData.registerSite.includes('4')">省级经开区;</span>
            <span v-if="detailData.registerSite.includes('99')">{{detailData.registerSiteOther}};</span>
            </el-checkbox-group>
          </td>
          <td class=x33 style='overflow:hidden;'>所在国家高新区</td>
          <td class=x27 style='overflow:hidden;'>
            <span v-if="detailData.registerHSite.includes('1')">南京高新区;</span>
            <span v-if="detailData.registerHSite.includes('2')">无锡高新区;</span>
            <span v-if="detailData.registerHSite.includes('3')">昆山高新区;</span>
            <span v-if="detailData.registerHSite.includes('4')">徐州高新区;</span>
            <span v-if="detailData.registerHSite.includes('99')">{{detailData.registerHSiteOther}};</span>
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

            <span v-if="detailData.registerMarket.includes('1')">是</span>
            <span v-if="detailData.registerMarket.includes('2')">否</span>

          </td>
          <td rowspan=3 height=115 class=x132 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:86.25pt;'>上市地点</td>
          <td class=x46 colspan=3>

            <span v-if="detailData.registerMarkeSite.includes('1')">上交所;</span>
            <span v-if="detailData.registerMarkeSite.includes('2')">深交所;</span>
            <span v-if="detailData.registerMarkeSite.includes('3')">新三板;</span>
            <span v-if="detailData.registerMarkeSite.includes('4')">港交所;</span>
            <span v-if="detailData.registerMarkeSite.includes('5')">主版;</span>
            <span v-if="detailData.registerMarkeSite.includes('6')">中小板;</span>
            <span v-if="detailData.registerMarkeSite.includes('7')">创业板;</span>

          </td>
        </tr>

        <tr height=19 id='r13'>
          <td height=19 class=x57 style='height:14.25pt;'>所属领域</td>
          <td colspan=5 id='tc12' class=x133>
            <span v-if="detailData.domain.includes('1')">智能装备;</span>
            <span v-if="detailData.domain.includes('2')">电子信息;</span>
            <span v-if="detailData.domain.includes('3')">新材料;</span>
            <span v-if="detailData.domain.includes('4')">航空航天;</span>
            <span v-if="detailData.domain.includes('5')">生物技术与新医药;</span>
            <span v-if="detailData.domain.includes('6')">能源与环保;</span>
            <span v-if="detailData.domain.includes('7')">管理;</span>
            <span v-if="detailData.domain.includes('99')">{{detailData.domainOther}};</span>
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
          <td class=x27 style='overflow:hidden;'>国防专利</td>
          <td colspan=2 id='tc16' class=x64>{{detailData.inventionNum}}件</td>
        </tr>
        <tr height=19 id='r18'>
          <td height=19 class=x66 style='height:14.25pt;'></td>
          <td class=x65>软件著作权</td>
          <td class=x64>{{detailData.inventionNum}}件</td>
          <td class=x27>其他<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></td>
          <td colspan=2 id='tc17' class=x64>{{detailData.inventionNum}}件</td>
        </tr>
        <tr height=68 style='mso-height-source:userset;height:58pt' id='r19'>
          <td rowspan=2 height=58 class=x70>企业研发情况</td>
          <td class=x71 style='overflow:hidden;'>研发团队人员总数</td>
          <td class=x71 style='overflow:hidden;'>本科及以上人数</td>
          <td class=x71 style='overflow:hidden;'>中高级职称以上人员人数</td>
          <td class=x72 style='overflow:hidden;'>研发团队高层次人才</td>
          <td class=x62 style='overflow:hidden;'>高层次人才类型</td>
        </tr>
        <tr height=48 style='mso-height-source:userset;height:48pt' id='r20'>
          <td class=x72>
            {{detailData.researchTeamnum}}人
          </td>
          <td class=x74>
            {{detailData.bachelorAbovenum}}人
          </td>
          <td class=x74>{{detailData.middleLevelnum}}人</td>
          <td class=x74>{{detailData.researchTeamnum}}人</td>
          <td class=x33 style='overflow:hidden;'>
            <span v-if="detailData.highleveltalentType.includes('1')">院士;</span>
            <span v-if="detailData.highleveltalentType.includes('2')">国家千人;</span>
            <span v-if="detailData.highleveltalentType.includes('3')">青年千人;</span>
            <span v-if="detailData.highleveltalentType.includes('4')">中青年科技创新领军人才;</span>
            <span v-if="detailData.highleveltalentType.includes('5')">长江学者;</span>
            <span v-if="detailData.highleveltalentType.includes('6')">国家杰青;</span>
            <span v-if="detailData.highleveltalentType.includes('99')">{{detailData.comhighleveltalentTypeOther}};</span>
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
            <span v-if="detailData.platform.includes('1')">企业重点实验室（国家级）;</span>
            <span v-if="detailData.platform.includes('2')">企业重点实验室（省级）;</span>
            <span v-if="detailData.platform.includes('3')">企业院士工作站;</span>
            <span v-if="detailData.platform.includes('4')">中青年科技创新领军人才;</span>
            <span v-if="detailData.platform.includes('5')">程技术研究中心（国家级）;</span>
            <span v-if="detailData.platform.includes('6')">程技术研究中心（省级）;</span>
            <span v-if="detailData.platform.includes('99')">{{detailData.platformOther}};</span>

          </td>
        </tr>
        <tr height=45 style='mso-height-source:userset;height:33.75pt' id='r24'>
          <td rowspan=8 height=64 class=x70 style='border-right:1px solid windowtext;height:48pt;overflow:hidden;'>军民科技协同创新情况</td>
          <td class=x85 style='overflow:hidden;'>是否承担过武器装备科研生产任务</td>
          <td colspan=4 id='tc19' class=x120 style='border-right:1px solid windowtext;'>
            <span v-if="detailData.sfkyrw.includes('1')">是</span>
            <span v-if="detailData.sfkyrw.includes('2')">否</span>

          </td>
        </tr>
        <tr height=19 id='r25'>
          <td rowspan=7 height=95 class=x103 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:71.25pt;overflow:hidden;'>“三证”拥有情况</td>
          <td rowspan=5 height=57 class=x104 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:42.75pt;'>已获得</td>
          <td colspan=3 id='tc20' class=x111 style='border-right:1px solid windowtext;overflow:hidden;'>

            <span v-if="detailData.szget.includes('1')">获得武器装备科研生产单位保密资格认证一级;</span>
            <span v-if="detailData.szget.includes('1') && detailData.qdtime1">{{detailData.qdtime1}}年取得</span></br>


          </td>
        </tr>
        <tr height=19 id='r26'>
          <td colspan=3 id='tc21' class=x115 style='border-right:1px solid windowtext;overflow:hidden;'>
            <span v-if="detailData.szget.includes('2')">获得武器装备科研生产单位保密资格认证二级;</span>
            <span v-if="detailData.szget.includes('2') && detailData.qdtime2">{{detailData.qdtime2}}年取得</span></br>


          </td>
        </tr>
        <tr height=19 id='r26'>
          <td colspan=3 id='tc21' class=x115 style='border-right:1px solid windowtext;overflow:hidden;'>
            <span v-if="detailData.szget.includes('3')">获得武器装备科研生产单位保密资格认证三级;</span>
            <span v-if="detailData.szget.includes('3') && detailData.qdtime3">{{detailData.qdtime3}}年取得</span></br>
          </td>
        </tr>
        <tr height=19 id='r26'>
          <td colspan=3 id='tc21' class=x115 style='border-right:1px solid windowtext;overflow:hidden;'>
            <span v-if="detailData.szget.includes('4')">武器装备科研生产许可证</span>
            <span v-if="detailData.szget.includes('4') && detailData.qdtime4">{{detailData.qdtime4}}年取得</span>

          </td>
        </tr>
        <tr height=19 id='r27'>
          <td colspan=3 id='tc22' class=x116>
            <span v-if="detailData.szget.includes('5')">装备承制单位资格认证</span>
            <span v-if="detailData.szget.includes('5') && detailData.qdtime5">{{detailData.qdtime5}}年取得</span>
          </td>
        </tr>
        <tr height=19 id='r28'>
          <td rowspan=2 height=38 class=x104 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:28.5pt;'>未获得</td>
          <td rowspan=2 height=38 class=x122 style='border-bottom:1px solid windowtext;height:28.5pt;'>是否计划申请</td>
          <td colspan=2 rowspan=2 id='tc23' class=x88 style='border-right:1px solid windowtext;'>
            <span v-if="detailData.szwhd.includes('1')">是</span>
            <span v-if="detailData.szwhd.includes('2')">否</span>

          </td>
        </tr>
        <tr height=19 id='r29'>
        </tr>
        <tr height=30 style='mso-height-source:userset;height:22.5pt' id='r30'>
          <td height=30 class=x75 style='height:22.5pt;'></td>
          <td class=x103 style='overflow:hidden;'>军民融合建设管理情况</td>
          <td colspan=4 id='tc25' class=x120>
            <span v-if="detailData.jmrhgn.includes('1')">内设专门机构</span>
            <span v-if="detailData.jmrhgn.includes('2')">设有专项经费</span>
            <span v-if="detailData.jmrhgn.includes('3')">有专人负责</span>
          </td>
        </tr>
        <tr height=120 style='mso-height-source:userset;height:90pt' id='r31'>
          <td colspan=1 :rowspan=(detailData.comPorcolumnDefinitions.length+1) height=158 class=x72 style='border-right:1px solid windowtext;height:118.5pt;overflow:hidden;'>承担的省部级以上军民科技协同创新项目</td>
          <td colspan=1 class=x104>项目名称</td>
          <td colspan=1 class=x104 style='overflow:hidden;'>起止时间</td>
          <td colspan=1  class=x105 style='overflow:hidden;'>项目来源</td>
          <td colspan=1  class=x105 style='overflow:hidden;'>服务部门</td>
          <td colspan=1  class=x105 style='overflow:hidden;'>执行情况</td>
        </tr>
        <tr v-for="item in detailData.comPorcolumnDefinitions" height=19 id='r32'>
          <td colspan=1 class=x108>{{item.name}}</td>
          <td colspan=1 class=x108>{{item.time}}</td>
          <td colspan=1 class=x109>{{item.source}}</td>
          <td colspan=1 class=x109>
            <span v-if="item.fwbm.includes('1')">海军;</span>
            <span v-if="item.fwbm.includes('2')">陆军;</span>
            <span v-if="item.fwbm.includes('3')">空军;</span>
            <span v-if="item.fwbm.includes('4')">火箭军;</span>
            <span v-if="item.fwbm.includes('5')">战略支援部队;</span>
            <span v-if="item.fwbm.includes('6')">军工企业;</span>
            <span v-if="item.fwbm.includes('99')">{{item.fwbmOther}};</span>
          </td>
          <td colspan=1 class=x109>
            <span v-if="item.zxqk.includes('1')">申请;</span>
            <span v-if="item.zxqk.includes('2')">在研;</span>
            <span v-if="item.zxqk.includes('3')">验收/结题;</span>
          </td>
        </tr>


        <tr height=19 id='r40'>
          <td colspan=2 id='tc27' height=19 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;overflow:hidden;'>“民参军”过程中，遇到的障碍及困难</td>
          <td colspan=4 id='tc28' class=x120>
            <span v-if="detailData.ydkn.includes('1')">需求信息;</span>
            <span v-if="detailData.ydkn.includes('2')">产品准入;</span>
            <span v-if="detailData.ydkn.includes('3')">人才保障;</span>
            <span v-if="detailData.ydkn.includes('4')">审批程序;</span>
            <span v-if="detailData.ydkn.includes('5')">资质认证;</span>
            <span v-if="detailData.ydkn.includes('6')">无人管理;</span>
            <span v-if="detailData.ydkn.includes('99')">{{detailData.comydknOther}};</span>

          </td>
        </tr>
        <tr height=19 id='r41'>
          <td colspan=2 id='tc29' height=19 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;overflow:hidden;'>“民参军”过程中企业自身存在的问题</td>
          <td colspan=4 id='tc30' class=x120>
            <span v-if="detailData.problem.includes('1')">技术人才不足;</span>
            <span v-if="detailData.problem.includes('2')">资金保障困难;</span>
            <span v-if="detailData.problem.includes('3')">保密设施设备不足;</span>
            <span v-if="detailData.problem.includes('4')">需求信息不了解;</span>
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
              <el-option label="5" key="5" value="5">
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
      pldcUrl: "",
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
      detailData: {
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
        registered_capital: '',
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
        szget: [],
        ydkn: '',
        problem: '',
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
        }],
        search_paramobj: ''
      },
      tfcheckStatus: '',

    }
  },
  created() {
    this.pldcUrl = this.docUrl + '/lib/exportLib?objName=' + this.input.objName + '&checkStatus=1&userType=2&creditLevel=' + this.input.creditLevel + '&status=' + this.input.status + '&token=' + window.sessionStorage.getItem('token')
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
     this.dialogShowLevel = false
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
          this.dialogShowSH = false
          this.$message({
            message: '保存成功',
            type: 'success'
          });
          this.loadPageList()
        }
      } else {
        if (rej.info == "") {
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
            this.dialogShowSH = false
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
        return
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

      if (this.multipleSelection.length == 0) {
        this.$message({
          type: 'success',
          message: '请勾选下架内容!'
        });
        return
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
      } else if (this.input.checkStatus == 0 || this.input.checkStatus == -1 || this.input.checkStatus == 2 ) {
        objData = data.detail
      }
      this.detailData = JSON.parse(objData)
      this.loadOneTree(this.detailData.country[0])
      this.loadtwoTree(this.detailData.country[0], this.detailData.country[1])
      this.loadThreeTree(this.detailData.country[1], this.detailData.country[2])
      this.detailData.search_paramobj = this.arrValue1 + '-' + this.arrValue2 + '-' + this.arrValue3
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

        if (this.input.checkStatus == 1 ) {
          objId = data.form.id
          objData = data.form.detail
          objnumber = data.form.number
        } else if (this.input.checkStatus == 0 || this.input.checkStatus == -1|| this.input.checkStatus == 2) {
          objId = data.id
          objData = data.detail
        }

        this.$router.push({
          name: 'enterpriseEdit',
          params: {
            objId: objId,
            objnumber: objnumber,
            objData: objData
          }
        })
      } else {
        this.$router.push({
          name: 'enterpriseEdit'
        })
      }
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
