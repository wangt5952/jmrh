<template>
<div class="tab-container">
  <div class="tools">
    <div class="paddingb textl paddingr">
      <el-input v-model="input" placeholder="请输入内容" style="width: 15%;"></el-input>
      <el-button style="margin-left:20px" @click="loadPageList" type="primary" icon="el-icon-search"></el-button>
      <el-button v-if="userType =='0'" style="margin-left: 10px;" @click="handleEdit" type="primary" icon="el-icon-edit">添加企业</el-button>


    </div>
  </div>


  <el-table v-loading="loading" class="tableH" :data="list" border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">
    <el-table-column type="index" align="center" label="ID">

    </el-table-column>
    <el-table-column align="center" label="企业名">
      <template slot-scope="scope">
                    <span>{{ scope.row.fullName }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="营业执照号">
      <template slot-scope="scope">
                    <span>{{ scope.row.fullName }}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="联系人姓名">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.dpartmentId}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="联系人手机号">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.dpartmentId}}</span>
                </template>
    </el-table-column>

    <el-table-column align="center" label="领域">
      <template slot-scope="scope">
                    <span>
                        {{ scope.row.dpartmentId}}</span>
                </template>
    </el-table-column>
    <el-table-column align="center" label="状态">
      <template slot-scope="scope">
                        <span>
                            {{ scope.row.dpartmentId}}</span>
                    </template>
    </el-table-column>

    <el-table-column align="center" label="">
      <template slot-scope="scope">

                      <div style="margin:2% 2% 2% 2%">
                          <el-button size="small" @click="showDetail(scope.row,'edit')" type=""  class="el-icon-edit colorblue borderblue">查看详情</el-button>
                      </div>
                    </template>
    </el-table-column>
    <el-table-column v-if="userType =='0'" align="center" label="操作">
      <template slot-scope="scope">
                    <div style="margin:2% 2% 2% 2%">
                        <el-button size="small" @click="handleEdit(scope.row,'edit')" type=""  class="el-icon-edit colorblue borderblue"></el-button>
                        <el-button size="small" @click="handleEdit(scope.row,'del')"  type=""  class="el-icon-delete colorred borderred"></el-button>
                    </div>
                </template>
    </el-table-column>

  </el-table>

  <div class="pagination-container pageH" style="padding-top:20px">
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"></el-pagination>
  </div>

  <el-dialog title="企业详情" :visible.sync="dialogShowDep" width="60%" top='5%'>
    <div class="textr paddinga">
      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handlePrint">打印</el-button>

      <el-button class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <div id="tablePrint" class="textc" style="font-size:12px;height:100%;">
      <table id="tableExcel" cellpadding=0 cellspacing=0 border="1px solid#000;" style='margin:0%;width:100%'>
        <tr height=27 style='mso-height-source:userset;height:20.25pt' id='r0'>
          <td colspan=6 id='tc0' height=27 class=x21 >军民科技协同创新相关企业信息表</td>
        </tr>
        <tr height=32 style='mso-height-source:userset;height:24pt' id='r1'>
          <td height=32 class=x22 style='height:24pt;'>企业名称</td>
          <td class=x23></td>
          <td class=x22 style='overflow:hidden;'>统一社会信用代码</td>
          <td class=x24></td>
          <td colspan=2 id='tc1' class=x26 ></td>
        </tr>
        <tr height=192 style='mso-height-source:userset;height:144pt' id='r2'>
          <td height=192 class=x22 style='height:144pt;overflow:hidden;'>企业规模（注册资金）</td>
          <td class=x23 style='overflow:hidden;'>□小于2000万（含）<br>□2000-5000万（含）<br>□5000-1亿（含）<br>□1亿-2亿（含）<br>□2亿-4亿（含）<br>□4亿及以上（含）</td>
          <td class=x22 style='overflow:hidden;'>注册时间（以营业执照时间为准）</td>
          <td class=x27 style='overflow:hidden;'><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>年<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span>月<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>日</td>
          <td class=x22>注册类型</td>
          <td class=x27 style='overflow:hidden;'>□内资企业<br>□合资企业<br>□外资企业</td>
        </tr>
        <tr height=19  id='r3'>
          <td height=19 class=x22 style='height:14.25pt;'>所在地区</td>
          <td class=x23><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>省</td>
          <td class=x24><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span>市</td>
          <td class=x28><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>区（县）</td>
          <td colspan=2 id='tc2' class=x26 >（该选项下拉选取）</td>
        </tr>
        <tr height=19  id='r4'>
          <td height=19 class=x22 style='height:14.25pt;'>通讯地址</td>
          <td class=x29></td>
          <td class=x27>邮编</td>
          <td colspan=3 id='tc3' class=x64 ></td>
        </tr>
        <tr height=448 style='mso-height-source:userset;height:336pt' id='r5'>
          <td height=448 class=x31 style='height:336pt;overflow:hidden;'>是否高新技术企业</td>
          <td class=x32 style='overflow:hidden;'>□是<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><br>□否</td>
          <td class=x31 style='overflow:hidden;'>企业所在地性质</td>
          <td class=x32 style='overflow:hidden;'>□国家级高新区<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><br>□省级高新区<br>□国家级经开区<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><br>□省级经开区<br>□其他</td>
          <td class=x33 style='overflow:hidden;'>所在国家高新区<br>（会有更新）</td>
          <td class=x27 style='overflow:hidden;'>□南京高新区<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□苏州高新区<br>□无锡高新区<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□常州高新区<br>□苏州工业园区<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>□泰州医药高新区<br>□昆山高新区
            <span
              style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□江阴高新区<br>□徐州高新区<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□武进高新区<br>□南通高新区<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□镇江高新区<br>□连云港高新区<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>□盐城高新区<br>□常熟高新区
              <span
                style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□扬州高新区<br>□淮安高新区<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□宿迁高新区</td>
        </tr>
        <tr height=19  id='r6'>
          <td rowspan=4 height=76 class=x132 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:57pt;'>企业法人信息</td>
          <td colspan=2 id='tc4' class=x71 >姓名</td>
          <td class=x37>联系人信息</td>
          <td colspan=2 id='tc5' class=x71 >姓名</td>
        </tr>
        <tr height=19  id='r7'>
          <td colspan=2 id='tc6' class=x71 >手机</td>
          <td class=x39></td>
          <td colspan=2 id='tc7' class=x71 >职务</td>
        </tr>
        <tr height=19  id='r8'>
          <td colspan=2 id='tc8' class=x71 >邮箱</td>
          <td class=x40></td>
          <td colspan=2 id='tc9' class=x71 >手机</td>
        </tr>
        <tr height=19  id='r9'>
          <td colspan=2 id='tc10' class=x71 ></td>
          <td class=x42></td>
          <td colspan=2 id='tc11' class=x71 >邮箱</td>
        </tr>
        <tr height=19  id='r10'>
          <td rowspan=3 height=115 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:86.25pt;'>是否上市</td>
          <td rowspan=3 height=115 class=x30 style='border-bottom:1px solid windowtext;height:86.25pt;overflow:hidden;'>□是<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><br>□否</td>
          <td rowspan=3 height=115 class=x127 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:86.25pt;'>股票代码</td>
          <td rowspan=3 height=115 class=x132 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:86.25pt;'>上市地点</td>
          <td class=x46>□上交所</td>
          <td class=x46></td>
        </tr>
        <tr height=48 style='mso-height-source:userset;height:36pt' id='r11'>
          <td class=x51>□深交所<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></td>
          <td class=x52 style='overflow:hidden;'>□主版<br>□中小板<span style='mso-spacerun:yes'>&nbsp; </span><br>□创业板</td>
        </tr>
        <tr height=48 style='mso-height-source:userset;height:36pt' id='r12'>
          <td class=x56 style='overflow:hidden;'>□新三板 <br>□港交所<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span><br>□其他</td>
          <td class=x46></td>
        </tr>
        <tr height=19  id='r13'>
          <td height=19 class=x57 style='height:14.25pt;'>所属领域</td>
          <td colspan=5 id='tc12' class=x133 >□智能装备<span style='mso-spacerun:yes'>&nbsp; </span>□电子信息<span style='mso-spacerun:yes'>&nbsp; </span>□新材料<span style='mso-spacerun:yes'>&nbsp; </span>□航空航天<span style='mso-spacerun:yes'>&nbsp; </span>□生物技术与新医药<span style='mso-spacerun:yes'>&nbsp; </span>□能源与环保
            <span
              style='mso-spacerun:yes'>&nbsp; </span>□其他</td>
        </tr>
        <tr height=19  id='r14'>
          <td height=19 class=x22 style='height:14.25pt;'>主营产品</td>
          <td colspan=5 id='tc13' class=x64 ></td>
        </tr>
        <tr height=32 style='mso-height-source:userset;height:24pt' id='r15'>
          <td height=32 class=x62 style='height:24pt;overflow:hidden;'>获得知识产权情况</td>
          <td class=x63>发明专利</td>
          <td class=x64>件</td>
          <td class=x65>国家新药</td>
          <td colspan=2 id='tc14' class=x64 >件</td>
        </tr>
        <tr height=48 style='mso-height-source:userset;height:36pt' id='r16'>
          <td height=48 class=x66 style='height:36pt;'></td>
          <td class=x67>实用新型</td>
          <td class=x64>件</td>
          <td class=x68 style='overflow:hidden;'>集成电路布图设计专有权</td>
          <td colspan=2 id='tc15' class=x64 >件</td>
        </tr>
        <tr height=64 style='mso-height-source:userset;height:48pt' id='r17'>
          <td height=64 class=x66 style='height:48pt;'></td>
          <td class=x65>外观设计</td>
          <td class=x69>件</td>
          <td class=x27 style='overflow:hidden;'>国防专利(指与国防和军队建设有关的知识产权)</td>
          <td colspan=2 id='tc16' class=x64 >件</td>
        </tr>
        <tr height=19  id='r18'>
          <td height=19 class=x66 style='height:14.25pt;'></td>
          <td class=x65>软件著作权</td>
          <td class=x64>件</td>
          <td class=x27>其他<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></td>
          <td colspan=2 id='tc17' class=x64 > 件</td>
        </tr>
        <tr height=144 style='mso-height-source:userset;height:108pt' id='r19'>
          <td rowspan=2 height=288 class=x70 >企业研发情况</td>
          <td class=x71 style='overflow:hidden;'>研发团队人员总数</td>
          <td class=x71 style='overflow:hidden;'>本科及以上人数（占比）</td>
          <td class=x71 style='overflow:hidden;'>中高级职称以上人员人数（占比）</td>
          <td class=x72 style='overflow:hidden;'>研发团队高层次人才（指入选国家各类人才培养计划人员，包括院士、千人、青年千人等）</td>
          <td class=x62 style='overflow:hidden;'>高层次人才类型</td>
        </tr>
        <tr height=144 style='mso-height-source:userset;height:108pt' id='r20'>
          <td class=x72>
            <font class="font4"><span style='mso-spacerun:yes'>&nbsp; </span></font>
            <font class="font9"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font4">人</font>
          </td>
          <td class=x74>
            <font class="font10"><span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></font>
            <font class="font5">人（</font>
            <font class="font10"><span style='mso-spacerun:yes'>&nbsp; </span></font>
            <font class="font5">%）</font>
          </td>
          <td class=x74><span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>人（<span style='mso-spacerun:yes'>&nbsp; </span>%）</td>
          <td class=x74><span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>人（<span style='mso-spacerun:yes'>&nbsp; </span>%）</td>
          <td class=x33 style='overflow:hidden;'>□院士<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□国家千人<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span><br>□青年千人<span style='mso-spacerun:yes'>&nbsp; </span>□中青年科技创新领军人才<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span><br>□长江学者
            <span
              style='mso-spacerun:yes'>&nbsp; </span>□国家杰青<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span><br>□其他</td>
        </tr>
        <tr height=48 style='mso-height-source:userset;height:36pt' id='r21'>
          <td height=48 class=x75 style='height:36pt;'></td>
          <td class=x76 style='overflow:hidden;'>近3年研发投入（万元）</td>
          <td class=x77>2017年</td>
          <td class=x74>2016年</td>
          <td class=x74>2015年</td>
          <td class=x27 style='overflow:hidden;'>注：具体年份根据实际情况可变化</td>
        </tr>
        <tr height=19  id='r22'>
          <td height=19 class=x75 style='height:14.25pt;'></td>
          <td class=x78></td>
          <td class=x79></td>
          <td class=x74></td>
          <td class=x27></td>
          <td class=x80></td>
        </tr>
        <tr height=30 style='mso-height-source:userset;height:22.5pt' id='r23'>
          <td height=30 class=x81 style='height:22.5pt;'>平台建设情况</td>
          <td colspan=5 id='tc18' class=x120 >
            <font class="font7">□企业重点实验室（□国家级<span style='mso-spacerun:yes'>&nbsp; </span>□省级）<br>□企业院士工作站<br>□工程技术研究中心（□国家级<span style='mso-spacerun:yes'>&nbsp; </span>□省级）<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><br>□其他（请列举：</font>
            <font
              class="font11"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font>
              <font class="font7"> ）</font>
          </td>
        </tr>
        <tr height=45 style='mso-height-source:userset;height:33.75pt' id='r24'>
          <td rowspan=2 height=64 class=x70 style='border-right:1px solid windowtext;height:48pt;overflow:hidden;'>军民科技协同创新情况</td>
          <td class=x85 style='overflow:hidden;'>是否承担过武器装备科研生产任务</td>
          <td colspan=4 id='tc19' class=x120 style='border-right:1px solid windowtext;'>□是<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>□否</td>
        </tr>
        <tr height=19  id='r25'>
          <td rowspan=5 height=95 class=x103 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:71.25pt;overflow:hidden;'>“三证”拥有情况</td>
          <td rowspan=3 height=57 class=x104 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:42.75pt;'>已获得</td>
          <td colspan=3 id='tc20' class=x111 style='border-right:1px solid windowtext;overflow:hidden;'>
            <font class="font7">□武器装备科研生产单位保密资格认证（一级、二级、三级）</font>
            <font class="font11"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>年</font>
            <font class="font7">取得</font>
          </td>
        </tr>
        <tr height=19  id='r26'>
          <td height=19 class=x75 style='height:14.25pt;'></td>
          <td colspan=3 id='tc21' class=x115 style='border-right:1px solid windowtext;overflow:hidden;'>
            <font class="font7">□武器装备科研生产许可证<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font11"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>年</font>
            <font class="font7">取得</font>
          </td>
        </tr>
        <tr height=19  id='r27'>
          <td height=19 class=x75 style='height:14.25pt;'></td>
          <td colspan=3 id='tc22' class=x116 >
            <font class="font7">□装备承制单位资格认证<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font>
            <font class="font11"><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>年</font>
            <font class="font7">取得</font>
          </td>
        </tr>
        <tr height=19  id='r28'>
          <td height=19 class=x75 style='height:14.25pt;'></td>
          <td rowspan=2 height=38 class=x104 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:28.5pt;'>未获得</td>
          <td rowspan=2 height=38 class=x122 style='border-bottom:1px solid windowtext;height:28.5pt;'>是否计划申请</td>
          <td colspan=2 id='tc23' class=x88 style='border-right:1px solid windowtext;'>□是</td>
        </tr>
        <tr height=19  id='r29'>
          <td height=19 class=x75 style='height:14.25pt;'></td>
          <td colspan=2 id='tc24' class=x99 >□否</td>
        </tr>
        <tr height=30 style='mso-height-source:userset;height:22.5pt' id='r30'>
          <td height=30 class=x75 style='height:22.5pt;'></td>
          <td class=x103 style='overflow:hidden;'>军民融合建设管理情况</td>
          <td colspan=4 id='tc25' class=x120 >□内设专门机构<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>□设有专项经费<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□有专人负责（姓名/职务：<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>电话：<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>）
            <span
              style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
          </td>
        </tr>
        <tr height=120 style='mso-height-source:userset;height:90pt' id='r31'>
          <td rowspan=3 height=158 class=x72 style='border-right:1px solid windowtext;height:118.5pt;overflow:hidden;'>承担的省部级以上军民科技协同创新项目</td>
          <td class=x104>项目名称</td>
          <td class=x104 style='overflow:hidden;'>起止时间<br><span style='mso-spacerun:yes'>&nbsp; </span>年<span style='mso-spacerun:yes'>&nbsp; </span>月--<span style='mso-spacerun:yes'>&nbsp; </span>年<span style='mso-spacerun:yes'>&nbsp; </span>月</td>
          <td class=x105 style='overflow:hidden;'>项目来源<br>(按照“国家/省级XX项目”的形式填写，如江苏省军民融合发展引导资金项目)</td>
          <td class=x106>服务部门</td>
          <td class=x107>执行情况</td>
        </tr>
        <tr height=19  id='r32'>
          <td class=x108></td>
          <td class=x108></td>
          <td class=x109></td>
          <td class=x110>□海军</td>
          <td class=x111>□申请</td>
        </tr>
        <tr height=19  id='r33'>
          <td class=x112></td>
          <td class=x112></td>
          <td class=x113></td>
          <td class=x110>□陆军</td>
          <td class=x114>□在研</td>
        </tr>
        <tr height=19  id='r34'>
          <td height=19 class=x66 style='height:14.25pt;'></td>
          <td class=x112></td>
          <td class=x112></td>
          <td class=x113></td>
          <td class=x110>□空军</td>
          <td class=x114>□验收/结题</td>
        </tr>
        <tr height=19  id='r35'>
          <td height=19 class=x66 style='height:14.25pt;'></td>
          <td class=x112></td>
          <td class=x112></td>
          <td class=x113></td>
          <td class=x110>□火箭军</td>
          <td rowspan=4 height=87 class=x116 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:65.25pt;'></td>
        </tr>
        <tr height=30 style='mso-height-source:userset;height:22.5pt' id='r36'>
          <td height=30 class=x66 style='height:22.5pt;'></td>
          <td class=x112></td>
          <td class=x112></td>
          <td class=x113></td>
          <td class=x110 style='overflow:hidden;'>□战略支援部队</td>
        </tr>
        <tr height=19  id='r37'>
          <td height=19 class=x66 style='height:14.25pt;'></td>
          <td class=x112></td>
          <td class=x112></td>
          <td class=x113></td>
          <td class=x110>□军工企业</td>
        </tr>
        <tr height=19  id='r38'>
          <td height=19 class=x66 style='height:14.25pt;'></td>
          <td class=x112></td>
          <td class=x112></td>
          <td class=x113></td>
          <td class=x110>□其他</td>
        </tr>
        <tr height=19  id='r39'>
          <td colspan=2 id='tc26' height=19 class=x117 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;overflow:hidden;'>可用于军民融合共享的资源情况（不超过200字）</td>
          <td class=x118></td>
          <td class=x118></td>
          <td class=x118></td>
          <td class=x119></td>
        </tr>
        <tr height=19  id='r40'>
          <td colspan=2 id='tc27' height=19 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;overflow:hidden;'>“民参军”过程中，遇到的障碍及困难</td>
          <td colspan=4 id='tc28' class=x120 >□需求信息<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>□产品准入<span style='mso-spacerun:yes'>&nbsp; </span>□人才保障<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span>□审批程序<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□资质认证
            <span
              style='mso-spacerun:yes'>&nbsp; </span>□无人管理<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span>□其他<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></td>
        </tr>
        <tr height=19  id='r41'>
          <td colspan=2 id='tc29' height=19 class=x71 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;overflow:hidden;'>“民参军”过程中企业自身存在的问题</td>
          <td colspan=4 id='tc30' class=x120 >□技术人才不足<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□资金保障困难<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□保密设施设备不足<span style='mso-spacerun:yes'>&nbsp;&nbsp; </span>□需求信息不了解<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></td>
        </tr>
        <tr height=19  id='r42'>
          <td colspan=2 id='tc31' height=19 class=x117 style='border-right:1px solid windowtext;height:14.25pt;overflow:hidden;'>对军民科技协同创新的相关建议（不超过200字）</td>
          <td class=x122></td>
          <td class=x123></td>
          <td class=x124></td>
          <td class=x125></td>
        </tr>
        <tr height=19  id='r43'>
          <td colspan=2 id='tc32' height=19 class=x117 style='border-right:1px solid windowtext;border-bottom:1px solid windowtext;height:14.25pt;'>有参军潜力的技术成果</td>
          <td colspan=2 id='tc33' class=x128 style='border-bottom:1px solid windowtext;overflow:hidden;'>请点击链接，进入成果资源库</td>
          <td class=x130></td>
          <td class=x131></td>
        </tr>
        <tr height=0 style='display:none'>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
          <td width=72 style='width:54pt'></td>
        </tr>
      </table>

    </div>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dialogShowDep = false">关闭</el-button>
    </span>
  </el-dialog>



  <el-dialog title="企业信息填写" :visible.sync="dialogFormVisible" width="60%" top='5%'>

    <el-form class="" label-width="30%" style="text-align:left">

      <el-row :gutter="24">

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
                  <el-form-item label="营业执照正本">
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
                    <el-input placeholder="请输入企业名称" v-model="com.enterprise_name" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="企业规模（注册资金）">
                    <el-radio v-model="com.registered_capital" label="1">小于2000万（含）</el-radio>
                    <el-radio v-model="com.registered_capital" label="2">2000-5000万</el-radio>
                    <el-radio v-model="com.registered_capital" label="3">5000-1亿（含）</el-radio>
                    <el-radio v-model="com.registered_capital" label="4">1亿-2亿（含）</el-radio>
                    <el-radio v-model="com.registered_capital" label="5">2亿-4亿（含）</el-radio>
                    <el-radio v-model="com.registered_capital" label="6">4亿及以上（含）</el-radio>
                  </el-form-item>
                  <el-form-item label="注册时间">
                    <el-date-picker v-model="com.registerDate" type="date" placeholder="选择日期">
                    </el-date-picker>
                  </el-form-item>
                  <el-form-item label="注册类型">
                    <el-radio v-model="com.registered_type" label="1">内资企业</el-radio>
                    <el-radio v-model="com.registered_type" label="2">合资企业</el-radio>
                    <el-radio v-model="com.registered_type" label="3">外资企业</el-radio>
                  </el-form-item>
                  <el-form-item label="所在地区">
                    <area-cascader :level="1" v-model="com.country" :data="pcaa"></area-cascader>
                    <!-- <area-cascader v-model="selected" :level="1" :data="pca"></area-cascader> -->
                  </el-form-item>
                  <el-form-item label="通讯地址">
                    <el-input placeholder="请输入通讯地址" v-model="com.registeraddress" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="邮编">
                    <el-input placeholder="请输入邮编" v-model="com.registerecode" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="是否高新技术企业">
                    <el-radio v-model="com.is_high_new_tech" label="1">是</el-radio>
                    <el-radio v-model="com.is_high_new_tech" label="2">否</el-radio>
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
                    <el-radio v-model="com.domain" label="1">智能装备</el-radio>
                    <el-radio v-model="com.domain" label="2">电子信息</el-radio>
                    <el-radio v-model="com.domain" label="3">新材料 </el-radio>
                    <el-radio v-model="com.domain" label="4">航空航天</el-radio>
                    <el-radio v-model="com.domain" label="5">生物技术与新医药</el-radio>
                    <el-radio v-model="com.domain" label="6">能源与环保</el-radio>
                    <el-radio v-model="com.domain" label="7">其他</el-radio>
                  </el-form-item>
                  <el-form-item label="主营产品">
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
                    <el-input placeholder="请输入人员总数" v-model="com.researchTeamnum" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="本科及以上人数(人)">
                    <el-input placeholder="请输入国家新药数量" v-model="com.bachelorAbovenum" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="中高级职称以上人员人数(人)">
                    <el-input placeholder="请输入中高级职称人数" v-model="com.middleLevelnum" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="研发团队高层次人才(人)">
                    <el-input placeholder="请输入研发团队人才人数" v-model="com.highleveltalentsnum" style="width:80%"></el-input>
                  </el-form-item>

                  <el-form-item label="高层次人才类型">
                    <el-radio v-model="com.highleveltalentType" label="1">院士</el-radio>
                    <el-radio v-model="com.highleveltalentType" label="2">国家千人</el-radio>
                    <el-radio v-model="com.highleveltalentType" label="3">青年千人 </el-radio>
                    <el-radio v-model="com.highleveltalentType" label="4">中青年科技创新领军人才</el-radio>
                    <el-radio v-model="com.highleveltalentType" label="5">长江学者</el-radio>
                    <el-radio v-model="com.highleveltalentType" label="6">国家杰青</el-radio>
                    <el-radio v-model="com.highleveltalentType" label="7">其他</el-radio>
                  </el-form-item>

                  <el-form-item :label="service_research_lastt">
                    <el-input placeholder="请输入研发投入" v-model="com.service_research_last" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item :label="service_research_beforet">
                    <el-input placeholder="请输入研发投入" v-model="com.service_research_before" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item :label="service_research_previoust">
                    <el-input placeholder="请输入研发投入" v-model="com.service_research_previous" style="width:80%"></el-input>
                  </el-form-item>
                  <el-form-item label="平台建设情况">
                    <el-radio v-model="com.platform" label="1">企业重点实验室（国家级）</el-radio>
                    <el-radio v-model="com.platform" label="2">企业重点实验室（省级）</el-radio>
                    <el-radio v-model="com.platform" label="3">企业院士工作站 </el-radio>
                    <el-radio v-model="com.platform" label="4">程技术研究中心（国家级）</el-radio>
                    <el-radio v-model="com.platform" label="5">程技术研究中心（省级）</el-radio>
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
                    <el-radio v-model="com.sfkyrw" label="1">是</el-radio>
                    <el-radio v-model="com.sfkyrw" label="2">否</el-radio>
                  </el-form-item>

                  <el-form-item label="“三证”已获得">
                    <el-radio v-model="com.szget" label="1">武器装备科研生产单位保密资格认证（一级、二级、三级） </el-radio>
                    <el-radio v-model="com.szget" label="2">武器装备科研生产许可证 </el-radio>
                    <el-radio v-model="com.szget" label="3">装备承制单位资格认证 </el-radio>
                  </el-form-item>
                  <el-form-item label="取得时间">
                    <el-date-picker v-model="com.qdtime" type="date" placeholder="选择日期">
                    </el-date-picker>
                  </el-form-item>

                  <el-form-item label="“三证”未获得是否计划申请">
                    <el-radio v-model="com.szwhd" label="1">是 </el-radio>
                    <el-radio v-model="com.szwhd" label="2">否 </el-radio>
                  </el-form-item>

                  <el-form-item label="军民融合建设管理情况">
                    <el-radio v-model="com.jmrhgn" label="1">内设专门机构</el-radio>
                    <el-radio v-model="com.jmrhgn" label="2">设有专项经费</el-radio>
                    <el-radio v-model="com.jmrhgn" label="3">有专人负责 </el-radio>
                  </el-form-item>
                  <el-form-item label="“民参军”过程中，遇到的障碍及困难">
                    <el-radio v-model="com.ydkn" label="1">需求信息</el-radio>
                    <el-radio v-model="com.ydkn" label="2">产品准入</el-radio>
                    <el-radio v-model="com.ydkn" label="3">人才保障 </el-radio>
                    <el-radio v-model="com.ydkn" label="4">审批程序 </el-radio>
                    <el-radio v-model="com.ydkn" label="5">资质认证 </el-radio>
                    <el-radio v-model="com.ydkn" label="6">无人管理 </el-radio>
                    <el-radio v-model="com.ydkn" label="7">其他 </el-radio>
                  </el-form-item>
                  <el-form-item label="“民参军”过程中企业自身存在的问题">
                    <el-radio v-model="com.problem" label="1">技术人才不足</el-radio>
                    <el-radio v-model="com.problem" label="2">资金保障困难</el-radio>
                    <el-radio v-model="com.problem" label="3">保密设施设备不足 </el-radio>
                    <el-radio v-model="com.problem" label="4">需求信息不了解 </el-radio>
                  </el-form-item>

                  <el-form-item label="有参军潜力的技术成果">
                    <el-input placeholder="请输入有参军潜力的技术成果" v-model="com.jscg" style="width:80%"></el-input>
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


                  <el-form-item label="服务部门">
                    <el-radio v-model="com.fwbm" label="1">海军</el-radio>
                    <el-radio v-model="com.fwbm" label="2">陆军</el-radio>
                    <el-radio v-model="com.fwbm" label="3">空军</el-radio>
                    <el-radio v-model="com.fwbm" label="4">火箭军</el-radio>
                    <el-radio v-model="com.fwbm" label="5">战略支援部队</el-radio>
                    <el-radio v-model="com.fwbm" label="6">军工企业</el-radio>
                  </el-form-item>

                  <el-form-item label="执行情况">
                    <el-radio v-model="com.zxqk" label="1">申请</el-radio>
                    <el-radio v-model="com.zxqk" label="2">在研</el-radio>
                    <el-radio v-model="com.zxqk" label="3">验收/结题 </el-radio>
                  </el-form-item>
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


      </el-row>
    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary" v-if="dialogadd == true" @click="addCreate(obj)">添加</el-button>
      <el-button type="primary" v-if="dialogsave == true"  @click="saveCreate(obj)">修改</el-button>
      <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
    </span>
  </el-dialog>


</div>
</template>

<script>
import {
  getUserDetail,
  setUserDetail
} from '@/api/login'
import {
  getenterprise,
  addLib
} from '@/api/library'

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

import table2excel from 'table2excel'
import printArea from 'printArea'

export default {
  data() {
    return {
      input: '',
      bank: '1',
      list: [],
      timeType: '1',
      dialogStatus: '',
      dialogFormVisible: false,
      dialogShowRole: false,
      dialogShowDep: false,
      dialogadd: false,
      dialogsave: false,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id',
        objName: ''
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
        enterprise_name: '',
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

    }
  },
  async mounted() {
    this.listLoading = false
    this.loadPageList()
    this.userType = window.sessionStorage.getItem('userType')

  },
  computed: {},
  methods: {

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
    showDetail() {
      this.dialogShowDep = true
    },
    async loadPageList() {
      if (this.input) {
        this.listQuery.objName = this.input
      } else {
        this.listQuery.objName = ''
      }
      let {
        data,
        success
      } = await getenterprise(this.listQuery)
      if (success) {
        // this.list = data.list
        this.list = [{
          'name': 1
        }, {
          'name': 1
        }]
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
      let {
        data,
        success
      } = await addLib(obj)

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
      if (type === 'del') {
        this.$confirm('此操作将删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          let del = await delUser(data.id)
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
        this.expert = data
        this.dialogsave = true
        this.dialogadd = false
        this.dialogFormVisible = true
      } else {
        this.dialogsave = true
        this.dialogadd = false
        this.dialogFormVisible = true
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
</style>

<style>
.tab-container {
  padding: 30px;
}

.el-table__body-wrapper {
  overflow: auto;
  position: relative;
  height: 89%;
}

.el-dialog__header {
  border-bottom: 1px solid#d8d6d6;
  padding-bottom: 10px;
}

/*
.el-dialog__title {
  line-height: 1;
  font-size: 25px;
  font-weight: 700;
  text-align: center;
  color: #1f2d3d;
} */

.el-dialog__body {
  margin-top: 30px;
  padding: 0px 20px;
  color: #48576a;
  font-size: 14px;
}

.el-dialog__footer {
  text-align: center;
}

.aaa {
  width: 95%
}

.el-dialog__wrapper {
  overflow: none;
}
</style>
