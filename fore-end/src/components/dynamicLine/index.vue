<template>
  <div class="wrap-container sn-container">

    <div class="sn-content">
      <div class="sn-title">折线图</div>

      <div class="sn-body">
        <div class="wrap-container">
          <div class="chartsdom" id="chart_dt"></div>
        </div>

      </div>
    </div>

  </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name: "dynamicLine",
  mounted() {
    this.$nextTick(function() {
      this.getEchart('chart_dt');
    })
  },
  methods: {
    getEchart() {
      var myChart = echarts.init(document.getElementById('chart_dt'))
      var option ={
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#283b56'
              }
            }
          }
        },
        // legend: {
        //   data: ['Step Start', 'Step Middle', 'Step End']
        // },
        grid: {
          top: '10%',
          left: '3%',
          right: '12%',
          bottom: '3%',
          containLabel: true
        },
        // toolbox: {下载按钮
        //   feature: {
        //     saveAsImage: {}
        //   }
        // },
        // color: ['#b54c5d'],
        calculable: true,
        xAxis: {
          type: 'category',
          name: '星期',
          boundaryGap:  false,
          /*改变x轴颜色*/
          axisLine: {
            lineStyle: {
              color: '#00c2ff',
              width: 1, //这里是为了突出显示加上的
            }
          },
          axisTick: {
            show: false
          },
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value',
          scale: true,
          name: '数额',
          min: 0,
          boundaryGap:  false,
          /*改变y轴颜色*/
          axisLine: {
            lineStyle: {
              color: '#00c2ff',
              width: 1, //这里是为了突出显示加上的
            }
          },
          splitArea: {
            show: false
          }
        },
        series:
            [
              {
                name: '数额1',
                type: 'line',
                // step: 'start',方字形图
                // xAxisIndex: 0,
                // yAxisIndex: 0,
                // itemStyle: {去掉节点
                //   opacity: 0,
                // },
                // smooth: true,平滑曲线
                data: [54, 86, 46, 134, 77, 96, 89]
              }
            ]
      }
      myChart.setOption(option);
    }
  }
};
</script>

<style lang="scss" scoped>
.sn-container {
  left: 1282px;
  top: 1548px;
  width: 586px;
  height: 400px;
}
  .chartsdom {
    width: 100%;
    height: 100%;
  }
  .wrap-container {
    position: absolute;
    overflow: hidden;
    display: block;
    cursor: default;
    z-index: 0;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
  }

  .sn-container {
    position: absolute;
  }
  .sn-content {
    position: absolute;
    top: 0;
    left: 0;
    width: 500px;
    height: 500px;
    border: 2px solid rgba(92, 255, 255, 0.1);
    background: rgba(10, 106, 255, 0.1) url(../../assets/img/wg.png) repeat 50% 50% !important;
    background-blend-mode: screen;
    margin: 120px 50px 50px 30px;
    overflow: visible;
  }
    .sn-title {
      position: absolute;
      height: 26px;
      font-size: 16px;
      color: #00c2ff;
      line-height: 26px;
      padding: 0 0 0 20px;
      left: 10px;
      right: 10px;
      top: 10px;
      &:before {
        content: "";
        position: absolute;
        left: 0;
        top: 50%;
        margin-top: -7px;
        width: 14px;
        height: 14px;
        background: url(../../assets/img/sn-title.png) no-repeat 0% 0%;
      }
    }

    .sn-body {
      position: absolute;
      top: 40px;
      left: 0;
      right: 0;
      bottom: 0;
    }

</style>
