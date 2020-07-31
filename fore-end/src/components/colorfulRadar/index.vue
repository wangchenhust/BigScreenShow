<template>
  <div class="wrap-container sn-container">
    <div class="sn-content">
      <div class="sn-title">雷达</div>
      <div class="sn-body">
        <div class="wrap-container">
          <div class="chartsdom" id="chart_radar"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name: "colorfulRadar",
  data(){
    return {
      dataBJ: [
        [85, 62, 71, 1.195, 60, 8, 2],
        [25, 11, 21, 0.65, 34, 9, 2]
      ],
      dataGZ: [
        [26, 37, 27, 1.163, 27, 13, 1],
        [85, 62, 71, 1.195, 60, 8, 2]
      ],
      dataSH: [
        [91, 45, 125, 0.82, 34, 23, 1],
        [85, 62, 71, 1.195, 60, 8, 2]
      ],
      lineStyle: {
        normal: {
          width: 1.5,
          opacity: 1
        }
      }
    }
  },
  mounted() {
    this.getEchart();
  },
  methods: {
    getEchart() {
      var myChart = echarts.init(document.getElementById('chart_radar'));
      var option  = {
        legend: {
          bottom: 5,
          data: ['北京', '上海', '广州'],
          itemGap: 20,
          textStyle: {
            color: '#fff',
            fontSize: 14
          },
          selectedMode: 'single'
        },
        radar: {
          indicator: [
            {name: 'AQI', max: 300},
            {name: 'PM2.5', max: 250},
            {name: 'PM10', max: 300},
            {name: 'CO', max: 5},
            {name: 'NO2', max: 200},
            {name: 'SO2', max: 100}
          ],
          shape: 'circle',
          splitNumber: 5,
          name: {
            textStyle: {
              color: 'rgb(238, 197, 102)'
            }
          },
          splitLine: {
            lineStyle: {
              color: [
                'rgba(238, 197, 102, 0.1)', 'rgba(238, 197, 102, 0.2)',
                'rgba(238, 197, 102, 0.4)', 'rgba(238, 197, 102, 0.6)',
                'rgba(238, 197, 102, 0.8)', 'rgba(238, 197, 102, 1)'
              ].reverse()
            }
          },
          splitArea: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: 'rgba(238, 197, 102, 0.5)'
            }
          }
        },
        series: [
          {
            name: '北京',
            type: 'radar',
            lineStyle: this.lineStyle,
            data: this.dataBJ,
            symbol: 'none',
            itemStyle: {
              color: '#F9713C'
            },
            areaStyle: {
              opacity: 0.3
            }
          },
          {
            name: '上海',
            type: 'radar',
            lineStyle: this.lineStyle,
            data: this.dataSH,
            symbol: 'none',
            itemStyle: {
              color: '#B3E4A1'
            },
            areaStyle: {
              opacity: 0.3
            }
          },
          {
            name: '广州',
            type: 'radar',
            lineStyle: this.lineStyle,
            data: this.dataGZ,
            symbol: 'none',
            itemStyle: {
              color: 'rgb(238, 197, 102)'
            },
            areaStyle: {
              opacity: 0.3
            }
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
    /*position: absolute;*/
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
    /*position: absolute;*/
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
    margin: 120px 50px 50px 1380px;
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
