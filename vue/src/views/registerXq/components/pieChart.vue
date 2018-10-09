<template>
<div :class="className" :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts 主题

import {
  debounce
} from '@/utils'

import {
  gettjlibqy,
} from '@/api/statistics'

import {
  pca,
  pcaa
} from "area-data";
export default {
  props: {
    className: {
      type: String,
      default: 'charts'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    chartData: {
      type: Object
    },
    width: {
      type: String,
      default: '484px'
    },
    height: {
      type: String,
      default: '340px'
    },
  },
  data() {
    return {
      chart: null,
      pca: pcaa, //最多省市区三级，结合:level='2'选择，0省、1省市、2省市区
    }
  },
  mounted() {
    this.initChart(this.chartData)
    if (this.autoResize) {
      this.__resizeHanlder = debounce(() => {
        if (this.chart) {
          this.chart.resize()
        }
      }, 100)
      window.addEventListener('resize', this.__resizeHanlder)
    }
    // 监听侧边栏的变化
    const sidebarElm = document.getElementsByClassName('sidebar-container')[0]
    sidebarElm.addEventListener('transitionend', this.__resizeHanlder)
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    if (this.autoResize) {
      window.removeEventListener('resize', this.__resizeHanlder)
    }

    const sidebarElm = document.getElementsByClassName('sidebar-container')[0]
    sidebarElm.removeEventListener('transitionend', this.__resizeHanlder)

    this.chart.dispose()
    this.chart = null
  },
  // watch: {
  //   chartData: {
  //     deep: true,
  //     handler(val) {
  //       this.setOptions(val)
  //     }
  //   }
  // },
  methods: {
    async initChart(objdata) {
      let obj = {
        site: '0'
      }
      let arrobjdata = []
      let arrobjdatas = []
      if (objdata.site1) arrobjdata.push('1')
      if (objdata.site2) arrobjdata.push('2')
      if (objdata.site3) arrobjdata.push('3')
      if (arrobjdata.includes('1')) {
        obj.site = '1'
      }
      if (arrobjdata.includes('2')) {
        obj.site = '2'
      }
      if (arrobjdata.includes('3')) {
        obj.site = '3'
      }

      objdata.domain ? obj.domain = '1' : obj.domain = '0'

      if (objdata.sites1 != '') arrobjdatas.push(objdata.sites1)
      if (objdata.sites2 != '') arrobjdatas.push(objdata.sites2)
      if (objdata.sites3 != '') arrobjdatas.push(objdata.sites3)

      arrobjdatas.length == 0 ? obj.sites = null : obj.sites = arrobjdatas

      let domains = []
      domains.push(objdata.domains)
      objdata.domains != '' ? obj.domains = domains : obj.domains = null


      let arr = []
      let {
        data,
        success
      } = await gettjlibqy(obj, '7')
      if (success) {
        if (data.lib.length == 0) {
          this.$message({
            message: '没有获取到数据！',
            type: 'warning'
          });
          return
        }
        arr = data.lib
      }

      let arrcount = []
      for (var i in arr) {
        let objArr = {}
        let country, domain, sex, degree, count, researchField
        arr[i].country ? country = arr[i].country + " " : country = ''
        arr[i].domain ? domain = arr[i].domain + " " : domain = ''
        arr[i].degree ? degree = arr[i].degree + " " : degree = ''
        arr[i].sex ? sex = arr[i].sex + " " : sex = ''
        arr[i].researchField ? researchField = arr[i].researchField : researchField = ''
        objArr.name = country + domain + degree + sex + researchField
        objArr.value = arr[i].count
        arrcount.push(objArr)
      }



      let arrdate = []
      for (var v in arrcount) {
        arrdate.push(arrcount[v].name)
      }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          x: 'right',
            type: 'scroll',
          data: arrdate
        },
        series: [{
          name: '需求统计',
          type: 'pie',
          radius: '55%',
          center: ['40%', '50%'], //不镂空
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: false,
              position: 'center'
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: '12',
                fontWeight: 'bold',
                color: '#fff'
              }
            }
          },
          labelLine: {
            normal: {
              show: false
            }
          },
          data: arrcount
        }]
      })
    },
    fliterCountry(data) {
      // this.arrValue2 = []
      // let pcadata = this.pca
      // let arr = []
      // for (var i in pcadata) {
      //   if (i == oneData) {
      //     let obj = {}
      //     obj.date = i
      //     obj.value = pcadata[i]
      //     arr.push(obj)
      //   }
      // }
      // arr = arr[0].value
      // for (var j in arr) {
      //   let obj = {}
      //   obj.name = j
      //   obj.value = arr[j]
      //   this.arrValue2.push(obj)
      // }
      return data
    },
    fliterEducation(data) {
      if (data == '1') {
        data = '小学'
      } else if (data == '2') {
        data = '初中'
      } else if (data == '3') {
        data = '高中'
      } else if (data == '4') {
        data = '大专'
      } else if (data == '5') {
        data = '本科'
      } else if (data == '6') {
        data = '研究生'
      } else if (data == '7') {
        data = '博士'
      } else if (data == '99') {
        data = '其他'
      }
      return data
    },
    fliterDegree(data) {
      if (data == '1') {
        data = '学士'
      } else if (data == '2') {
        data = '硕士'
      } else if (data == '3') {
        data = '博士'
      } else if (data == '99') {
        data = '其他'
      }
      return data
    },
    fliterSex(data) {
      if (data == '1') {
        data = '男'
      } else if (data == '0') {
        data = '女'
      }
      return data
    },
    fliterResearchField(data) {
      if (data == '1') {
        data = '智能装备'
      } else if (data == '2') {
        data = '电子信息'
      } else if (data == '3') {
        data = '新材料'
      } else if (data == '4') {
        data = '航空航天'
      } else if (data == '5') {
        data = '生物技术与新医药'
      } else if (data == '6') {
        data = '能源与环保'
      } else if (data == '7') {
        data = '管理'
      } else if (data == '99') {
        data = '其他'
      }
      return data
    },
  },
  watch: {
    // infoObj: function() {
    //   this.initChart(this.infoObj)
    // }
  }
}
</script>
