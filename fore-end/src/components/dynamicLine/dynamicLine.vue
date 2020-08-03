<template>
        <div class="wrap-container">
          <div class="chartsdom" id="chart_dt"></div>
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




</style>
