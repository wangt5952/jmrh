<!-- <template>
<div :class="className" :style="{height:height,width:width}"></div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts 主题
var dataStyle = {
  normal: {
    label: {
      show: false
    },
    labelLine: {
      show: false
    }
  }
};
var placeHolderStyle = {
  normal: {
    color: 'rgba(0,0,0,0)',
    label: {
      show: false
    },
    labelLine: {
      show: false
    }
  },
  emphasis: {
    color: 'rgba(0,0,0,0)'
  }
};
option = {
  title: {
    text: '你幸福吗？',
    subtext: 'From ExcelHome',
    sublink: 'http://e.weibo.com/1341556070/AhQXtjbqh',
    x: 'center',
    y: 'center',
    itemGap: 20,
    textStyle: {
      color: 'rgba(30,144,255,0.8)',
      fontFamily: '微软雅黑',
      fontSize: 35,
      fontWeight: 'bolder'
    }
  },
  tooltip: {
    show: true,
    formatter: "{a} <br/>{b} : {c} ({d}%)"
  },
  legend: {
    orient: 'vertical',
    x: document.getElementById('charts').offsetWidth / 2,
    y: 45,
    itemGap: 12,
    data: ['68%的人表示过的不错', '29%的人表示生活压力很大', '3%的人表示“我姓曾”']
  },
  toolbox: {
    show: true,
    feature: {
      mark: {
        show: true
      },
      dataView: {
        show: true,
        readOnly: false
      },
      restore: {
        show: true
      },
      saveAsImage: {
        show: true
      }
    }
  },
  series: [{
      name: '1',
      type: 'pie',
      clockWise: false,
      radius: [125, 150],
      itemStyle: dataStyle,
      data: [{
          value: 68,
          name: '68%的人表示过的不错'
        },
        {
          value: 32,
          name: 'invisible',
          itemStyle: placeHolderStyle
        }
      ]
    },
    {
      name: '2',
      type: 'pie',
      clockWise: false,
      radius: [100, 125],
      itemStyle: dataStyle,
      data: [{
          value: 29,
          name: '29%的人表示生活压力很大'
        },
        {
          value: 71,
          name: 'invisible',
          itemStyle: placeHolderStyle
        }
      ]
    },
    {
      name: '3',
      type: 'pie',
      clockWise: false,
      radius: [75, 100],
      itemStyle: dataStyle,
      data: [{
          value: 3,
          name: '3%的人表示“我姓曾”'
        },
        {
          value: 97,
          name: 'invisible',
          itemStyle: placeHolderStyle
        }
      ]
    }
  ]
};

import {
  debounce
} from '@/utils'
// import moment from 'moment'
// import {
//   saleDaytime
// } from '@/api/sale';
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
      type: String
    },
  },
  data() {
    return {
      chart: null,
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
  // watch: {
  //   chartData: {
  //     deep: true,
  //     handler(val) {
  //       this.setOptions(val)
  //     }
  //   }
  // },
  methods: {
    async initChart(depRange) {
      // let saleTime = {
      //   timeType: '3',
      //   depType: 'reg',
      //   time: moment().format('YYYY-MM-DD'),
      //   sortType: 'signature_today_amount',
      //   depRange: depRange
      // }
      // let data = await saleDaytime(saleTime);
      // data = data.data.data
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption(option)
    }
  },
  watch: {
    // infoObj: function() {
    //   this.initChart(this.infoObj)
    // }
  }
}
</script> -->
