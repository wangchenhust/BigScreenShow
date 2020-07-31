<template>
  <div class="wrap-container sn-container">
    <div class="sn-content">
      <div class="sn-title">饼图</div>
      <div class="sn-body">
        <div class="wrap-container ring-pie">

          <div class="chartsdom" id="chart_rp"></div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name: "ringPie",
  mounted() {
    this.getEchart();
  },
  methods: {
    getEchart() {
      var myChart = echarts.init(document.getElementById('chart_rp'));
      var option ={
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },

        visualMap: {
          show: false,
          min: 80,
          max: 600,
          inRange: {
            colorLightness: [0, 1]
          }
        },
        series: [
          {
            name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '50%'],
            data: [
              {value: 335, name: '直接访问'},
              {value: 310, name: '邮件营销'},
              {value: 274, name: '联盟广告'},
              {value: 235, name: '视频广告'},
              {value: 400, name: '搜索引擎'}
            ].sort(function (a, b) { return a.value - b.value; }),
            roseType: 'radius',
            label: {
              color: '#eee'
            },
            labelLine: {
              lineStyle: {
                color: 'rgba(255, 255, 255, 0.3)'
              },
              smooth: 0.2,
              length: 10,
              length2: 20
            },
            itemStyle: {
              color: '#24d1fd',
              shadowBlur: 200,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            },

            animationType: 'scale',
            animationEasing: 'elasticOut'
          }
        ]
      }

      myChart.setOption(option);

    }
  },
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
    margin: 650px 50px 50px 30px;
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
