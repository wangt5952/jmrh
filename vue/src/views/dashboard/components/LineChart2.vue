<template>
<div :class="className" :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts 主题
import {
  debounce
} from '@/utils'

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
      default: '100%'
    },
    height: {
      type: String,
      default: '160px'
    },
    infoObj: {
      type: Object
    },
  },
  data() {
    return {
      chart: null,
    }
  },
  mounted() {
    this.initChart(this.infoObj)
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
  watch: {
    chartData: {
      deep: true,
      handler(val) {
        this.setOptions(val)
      }
    }
  },
  methods: {

    initChart(data) {
      // let valData
      // if (data.zzkcxsb) {
      //   valData = data.zzkcxsb.toFixed(2)
      // } else {
      //   valData = 0
      // }
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            show: false, // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: ['危险区域', '安全区域', ]
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          show: false,
        },
        yAxis: {
          show: false,
          type: 'category',
          data: ['周一', '周二', '周三']
        },
        series: [{
            name: '危险区域',
            type: 'bar',
            stack: '总量',
            label: {
              normal: {
                show: true,
                position: 'insideRight',
                formatter: '',
              }
            },
            itemStyle: {
              normal: {
                color: '#e029a8',
                formatter: '',
              }
            },
            data: [3]

          },
          {
            name: '安全区域',
            type: 'bar',
            stack: '总量',
            label: {
              normal: {
                show: true,
                position: 'insideRight',
                formatter: '{c}',
              }
            },
            itemStyle: {
              normal: {
                color: '#46cbf3',
                show: false,
              }
            },
            data: ['1']

          },
          {
            name: '危险区域',
            type: 'bar',
            stack: '总量',
            label: {
              normal: {
                show: true,
                position: 'insideRight',
                formatter: '',
              }
            },
            itemStyle: {
              normal: {
                color: '#e029a8'
              }
            },
            data: [10]

          }
        ]

      })
    }
  },
  watch: {
    infoObj: function() {
      this.initChart(this.infoObj)
    },
  }
}
</script>
