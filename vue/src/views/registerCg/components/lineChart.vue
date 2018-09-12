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
  gettjlib,
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
      default: '784px'
    },
    height: {
      type: String,
      default: '300px'
    },
  },
  data() {
    return {
      chart: null,
      childValue: '我是子组件的数据'
    }
  },
  mounted() {
    this.initChart()
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

  methods: {
    async initChart() {
      let arr,arr2
      let {
        data,
        success
      } = await gettjlib('6')
      if (success) {
        arr = data.month
        arr2 = data.monthLJ
         this.$emit('childByValue', data.total)
       } else {
         arr = []
           arr2 = []
       }
       let arrdate = []
       let arrValue = []
       let arrValue2 = []
       arr.forEach((v, i) => {
         Object.keys(v).forEach(v => {
           // obj.date = v
           // obj.value= arr[i][v]
           arrdate.push(v)
           arrValue.push(arr[i][v])
         })
       })
       arr2.forEach((v, i) => {
         Object.keys(v).forEach(v => {
           arrValue2.push(arr2[i][v])
         })
       })
      // data = data.data.data
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: { // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
            data: ['新增','累计'],
            align: 'left',
            left: 10
        },
        xAxis: {
          type: 'category',
          data: arrdate
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: '新增',
          type: 'bar',
          data: arrValue
        },{
          name: '累计',
          type: 'bar',
          data: arrValue2
        }, ]
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
