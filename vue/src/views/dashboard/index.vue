<template>
<div class="dashboard-container">

  <el-row class="table-group" :gutter="40">
    <el-col :span="24" style="padding:0px;background: #fff;" >
      <div class="maintextc fa13  bg martop5">
        <div class="paddingl10 paddingt5 paddingb5  borderb">今日状态</div>
        <div class="paddinga15">
          <!-- <el-col v-for='x in modelList' :span="8" style="padding-left: 5px;padding-right: 5px;">
            <div class="table">
              <div class="table-description">
                <div class="fk1"></div>
                <div class="table-text">{{x.mdmModel}}</div>
                <div class="table-numt">同步{{x.successNum}}</div>
              </div>
            </div>
          </el-col> -->
        </div>
      </div>
    </el-col>

    <el-col :span="24" style="padding:0px;background: #fff;">
      <div class="maintextc fa13  bg" style="background: #fff;">
        <div class="paddingl10 paddingt5 paddingb5  borderb">今日代办状态</div>
        <div class="paddinga15">
          <!-- <el-col v-for='x in modelfailList' :span="8" style="padding-left: 5px;padding-right: 5px;">
            <div class="table">
              <div class="table-description">
                <div class="fk1"></div>
                <div class="table-text">{{x.mdmModel}}</div>
                <div style="  color: #ff2727;" class="table-numt">同步{{x.failNum}}</div>
              </div>
            </div>
          </el-col> -->

        </div>
      </div>
    </el-col>
  </el-row>
  <div class="martop5" style="padding:30px;border:solid 1px #e6e6e6;">
    <h3 style="margin:0">代办列表一览</h3>
    <el-table class="tableH"  border style="margin-top:20px;width:100%;font-size:12px;overflow-y:auto">

      <!-- <el-table-column v-for='x in listtop' align="center" :label="x">

        <template slot-scope="scope">
                      <span>{{ scope.row.children[x] }}</span>
                      </template>
      </el-table-column> -->


    </el-table>
  </div>

</div>
</template>

<script>
import {
  mapGetters
} from 'vuex'



export default {

  data() {
    return {
      // listtop: ['数据类型', '数据量'],
      modelList: [{
          mdmModel: '人员',
          successNum: '10',
        },
        {
          mdmModel: '人员1',
          successNum: '10',
        },
        {
          mdmModel: '人员2',
          successNum: '10',
        }
      ],
      modelfailList: [{
        mdmModel: '人员',
        failNum: '2',
      }, {
        mdmModel: '人员2',
        failNum: '2',
      }, {
        mdmModel: '人员3',
        failNum: '2',
      }, {
        mdmModel: '人员4',
        failNum: '2',
      }],
      list: [{
        dataType: '人员',
        dataNum: '4000',
        EHR: '4000',
        OA: '4000',
        ERP: '4000',
        NC: '4000',
        shangye: '4000',
        wuye: '4000',
        baorong: '4000'
      }, {
        dataType: '行政组织',
        dataNum: '300',
        EHR: '300',
        OA: '300',
        ERP: '200',
        NC: '200',
        shangye: '200',
        wuye: '200',
        baorong: '200'
      }, {
        dataType: '财务组织',
        dataNum: '300',
        EHR: '',
        OA: '',
        ERP: '200',
        NC: '200',
        shangye: '200',
        wuye: '200',
        baorong: '200'
      }, {
        dataType: '产品',
        dataNum: '100',
        EHR: '',
        OA: '',
        ERP: '100',
        NC: '100',
        shangye: '50',
        wuye: '50',
        baorong: ' '
      }, {
        dataType: '项目',
        dataNum: '',
        EHR: '',
        OA: '',
        ERP: '100',
        NC: '100',
        shangye: '50',
        wuye: '50',
        baorong: ' '
      }, {
        dataType: '用户',
        dataNum: '4000',
        EHR: '4000',
        OA: '3000',
        ERP: '100',
        NC: '100',
        shangye: '500',
        wuye: '500',
        baorong: ' '
      }],
    }
  },
  name: 'dashboard',
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  },
  mounted() {
    this.loadMain()
  },
  methods: {
    async loadMain() {
      // let data = await getMain()
      // let main1 = data.data.main1
      // let model = {}
      // let arr = []
      // for (var x in main1) {
      //   if (main1[x].model != null) {
      //     model.mdmModel = main1[x].model.mdmModel
      //     model.successNum = main1[x].successNum
      //     model.failNum = main1[x].failNum
      //     arr.push(model)
      //   }
      // }
      // this.modelList = arr
      // this.modelfailList = arr
      //
      // let main2 = data.data.main2
      // let listch = main2[0].children
      // for (var x in listch) {
      //   this.listtop.push(x)
      // }
      // let dataType = this.listtop
      //
      // for (var x in main2) {
      //   main2[x].children.数据类型 = main2[x].name
      //   main2[x].children.数据量 = main2[x].count
      // }
      //
      // this.list = main2
    }
  }
}
</script>

<style lang="scss">
</style>
<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
    &-container {
        margin: 30px;
    }
    &-text {
        font-size: 30px;
        line-height: 46px;
    }
}
.el-col {
    padding: 10px;
}

.table-group {
    .table {
        height: 50px;
        cursor: pointer;
        font-size: 12px;
        position: relative;
        overflow: hidden;
        color: #666;
        background: #f1f0f0;
        box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
        border-color: rgba(0, 0, 0, .05);
        &:hover {
            .table-icon-wrapper {
                color: #fff;
            }
            .icon-people {
                background: #40c9c6;
            }
            .icon-message {
                background: #36a3f7;
            }
            .icon-money {
                background: #f4516c;
            }
            .icon-shoppingCard {
                background: #34bfa3;
            }
        }
        .icon-people {
            color: #40c9c6;
        }
        .icon-message {
            color: #36a3f7;
        }
        .icon-money {
            color: #f4516c;
        }
        .icon-shoppingCard {
            color: #34bfa3;
        }
        .table-icon-wrapper {
            float: right;
            margin: 14px 0 0 14px;
            padding: 16px;
            transition: all 0.38s ease-out;
            border-radius: 6px;
        }
        .table-icon {
            float: left;
            font-size: 48px;
        }
        .table-description {
            font-weight: bold;
            margin: 5px;
            padding: 5px;
            margin-left: 0;
            .table-text {
                padding-left: 15px;
                line-height: 12px;
                color: rgba(0, 0, 0, 0.45);
                font-size: 10px;
                margin-bottom: 4px;
                text-align: left;
            }
            .table-percent {
                font-size: 18px;
                text-align: center;
                color: #000;
            }
            .table-numt {
                color: #8a8a8a;
                text-align: center;
                font-weight: 100;
            }
        }

    }
}
.imagetable {
    font-family: verdana,arial,sans-serif;
    font-size: 11px;
    color: #878d99;
    border-width: 1px;
    border-color: #e6ebf5;
    border-collapse: collapse;
}
.imagetable th {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #e6ebf5;
}
.imagetable td {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #e6ebf5;
}
</style>
