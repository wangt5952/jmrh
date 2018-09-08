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
      default: '384px'
    },
    height: {
      type: String,
      default: '380px'
    },
  },
  data() {
    return {
      chart: null,
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
     debugger
      let obj ={}
      let arrobjdata = []
      let arrobjdatas = []
      if(objdata.site1 != "") arrobjdata.push(objdata.site1)
      if(objdata.site2 != "") arrobjdata.push(objdata.site2)
      if(objdata.site3 != "") arrobjdata.push(objdata.site3)
      obj.site = arrobjdata
      obj.sex = objdata.sex
      obj.domain =objdata.domain
      obj.education = objdata.education
      obj.degree = objdata.degree

      if(objdata.sites1 != "") arrobjdatas.push(objdata.sites1)
      if(objdata.sites2 != "") arrobjdatas.push(objdata.sites2)
      if(objdata.sites3 != "") arrobjdatas.push(objdata.sites3)
      obj.sites = arrobjdata
      obj.sexs = objdata.sexs
      obj.domains =objdata.domains
      obj.educations = objdata.educations
      obj.degrees = objdata.degrees
      debugger
      let arr
      let {
        data,
        success
      } = await gettjlibqy(obj)
      if (success) {
        arr = data.user
      } else {
        arr = []
      }

      let arrsex = []
      let arrcount = []
      let arrresearchField = []
      let arrdate = []
      for (var i in arr) {
        arrsex.push(arr[i].sex)
        arrcount.push(arr[i].count)
        arrresearchField.push(arr[i].researchField)
      }
      Object.keys(arr[0]).forEach(v => {
        let obj = {}
        arrdate.push(v)
      })

      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
          orient: 'vertical',
          x: 'left',
          data: arrdate
        },
        series: [{
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['40%', '50%'],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: false,
              position: 'center'
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: '30',
                fontWeight: 'bold'
              }
            }
          },
          labelLine: {
            normal: {
              show: false
            }
          },
          data: [{
              value: 335,
              name: '待审核'
            },
            {
              value: 310,
              name: '已通过'
            },
            {
              value: 234,
              name: '被驳回'
            },
          ]
        }]
      })
    }
  },
  watch: {
    // infoObj: function() {
    //   this.initChart(this.infoObj)
    // }
  }
}
</script>
