<template>
  <div class="lineChart-container">
    <div class="chart" id="chart_middel3"></div>
  </div>
</template>

<script>
import echarts from "echarts";
export default {
  name: "lineChart",
  data() {
    return {
      values: [
        [0, 0.2, 0.3, 0.3, 0.5, 0.6, 0.5, 0.3, 0.6, 0.5, 0.5, 0.8],
        [0.9, 0.5, 0.7, 0.8, 0.6, 0.7, 0.8, 0.7, 0.7, 0.6, 0.8, 0.3]
      ],
      legendData: ["资本充足率", "核心资本充足率"],
      datax: [
        "1月",
        "2月",
        "3月",
        "4月",
        "5月",
        "6月",
        "7月",
        "8月",
        "9月",
        "10月",
        "11月",
        "12月"
      ]
    };
  },
  computed: {},
  mounted() {
    this.getChart();
  },
  methods: {
    getChart() {
      var myChart = echarts.init(document.getElementById("chart_middel3"));
      var option = {
        tooltip: {
          //详情框
          trigger: "axis",
          formatter: function(params) {
            return (
              params[0].name +
              "<br/>" +
              "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:rgba(36,207,233,0.9)'></span>" +
              params[0].seriesName +
              " : " +
              Number(params[0].value.toFixed(2) * 100).toLocaleString() +
              "%<br/>" +
              "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:rgba(255,80,124,1)'></span>" +
              params[1].seriesName +
              " : " +
              Number(params[1].value.toFixed(2) * 100).toLocaleString() +
              "%<br/>"
            );
          }
        },
        legend: {
          data: this.legendData,
          top: 20,
          textStyle: {
            fontSize: 10,
            color: "#ade3ff"
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "2%",
          containLabel: true
        },
        xAxis: {
          type: "category",
          boundaryGap: false, //坐标轴两边留白
          data: this.datax,
          axisLabel: {
            //坐标轴刻度标签的相关设置。
            interval: 0, //设置为 1，表示『隔一个标签显示一个标签』
            textStyle: {
              color: "#ade3ff",
              fontStyle: "normal",
              fontFamily: "微软雅黑",
              fontSize: 10
            }
            //rotate:50,
          },
          axisTick: {
            //坐标轴刻度相关设置。
            show: false
          },
          axisLine: {
            //坐标轴轴线相关设置
            lineStyle: {
              color: "#E5E9ED",
              opacity: 0.5 //x轴
            }
          },
          splitLine: {
            //坐标轴在 grid 区域中的分隔线。
            show: true,
            lineStyle: {
              color: "#E5E9ED",
              opacity: 0.1
            }
          }
        },
        yAxis: [
          {
            type: "value",
            splitNumber: 3,
            axisLabel: {
              textStyle: {
                color: "#ade3ff",
                fontStyle: "normal",
                fontFamily: "微软雅黑",
                fontSize: 10
              },
              formatter: function(params) {
                var newParamsName = "";
                newParamsName = params * 100 + " %";
                return newParamsName;
              }
            },
            axisLine: {
              lineStyle: {
                color: "#E5E9ED",
                opacity: 0.5 //y轴
              }
            },
            axisTick: {
              show: false
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: "#E5E9ED",
                opacity: 0.1
              }
            }
          }
        ],
        series: [
          {
            name: this.legendData[0],
            type: "line",
            itemStyle: {
              normal: {
                color: "#3A84FF",
                lineStyle: {
                  color: "#3A84FF",
                  width: 1
                },
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                    {
                      offset: 0,
                      color: "rgba(58,132,255,0)"
                    },
                    {
                      offset: 1,
                      color: "rgba(58,132,255,0.35)"
                    }
                  ])
                }
              }
            },
            data: this.values[0]
          },
          {
            name: this.legendData[1],
            type: "line",
            itemStyle: {
              normal: {
                color: "rgba(255,80,124,1)",
                lineStyle: {
                  color: "rgba(255,80,124,1)",
                  width: 1
                },
                areaStyle: {
                  color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [
                    {
                      offset: 0,
                      color: "rgba(255,80,124,0)"
                    },
                    {
                      offset: 1,
                      color: "rgba(255,80,124,0.35)"
                    }
                  ])
                }
              }
            },
            data: this.values[1]
          }
        ]
      };
      let effectScatterData1 = [];
      let effectScatterData2 = [];
      let data1 = this.values[0];
      let data2 = this.values[1];
      for (let i = 0; i < data1.length; i++) {
        if (data1[i] > 0.5) {
          effectScatterData1.push([this.datax[i], data1[i]]);
        }
        if (data2[i] > 0.7) {
          effectScatterData2.push([this.datax[i], data2[i]]);
        }
      }
      //涟漪效果
      let effectScatter1 = {
        name: "告警",
        type: "effectScatter",
        coordinateSystem: "cartesian2d",
        data: effectScatterData1, //2d坐标系
        symbolSize: 8,
        showEffectOn: "render",
        //涟漪特效相关配置
        rippleEffect: {
          brushType: "stroke", //波纹绘制方式fill或stroke
          scale: 4, //波纹的最大缩放比
          period: 1.5 //动画周期，秒数
        },
        hoverAnimation: true,
        label: {
          // formatter: "{b}",
          position: "top",
          show: true,
          textStyle: {
            color: "white",
            fontSize: 14
          }
        },
        itemStyle: {
          color: "rgb(199,84,80)",
          shadowBlur: 8,
          shadowColor: "rgb(199,84,80)"
        },
        zlevel: 1
      };
      let effectScatter2 = {
        name: "告警",
        type: "effectScatter",
        coordinateSystem: "cartesian2d",
        data: effectScatterData2, //2d坐标系
        symbolSize: 8,
        showEffectOn: "render",
        //涟漪特效相关配置
        rippleEffect: {
          brushType: "stroke",
          scale: 4, //波纹的最大缩放比
          period: 1.5 //动画周期，秒数
        },
        hoverAnimation: true,
        label: {
          // formatter: "{b}",
          position: "top",
          show: false,
          textStyle: {
            color: "white",
            fontSize: 14
          }
        },
        itemStyle: {
          color: "rgb(58,136,255)",
          shadowBlur: 8,
          shadowColor: "rgb(58,136,255)"
        },
        zlevel: 1
      };
      option.series.push(effectScatter1);
      option.series.push(effectScatter2);
      myChart.setOption(option, true);
      option.series.pop(effectScatter1);
      option.series.pop(effectScatter2);
    }
  },
  watch: {
    values: function() {
      this.getChart();
    }
  }
};
</script>

<style lang="scss" scoped>
.chart {
  width: 100%;
  height: 100%;
  /*border: 1px solid #12fe81;*/
}
.lineChart-container {
  position: absolute;
  overflow: hidden;
  display: block;
  cursor: default;
  z-index: 0;
  left: -5px;
  top: -5px;
  width: 100%;
  height: 100%;
}
</style>
