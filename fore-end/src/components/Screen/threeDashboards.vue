<template>
    <div class="gauge-container" id="carousel_box">
      <el-carousel
          :autoplay="true"
          :loop="true"
          interval="4000"
          ref="carousel"
          indicator-position="none"
          height="140px"
      >
        <el-carousel-item name="gaugeOne">
          <div class="chart" id="chart_gauge1"></div>
        </el-carousel-item>
        <el-carousel-item name="gaugeTwo">
          <div class="chart" id="chart_gauge2"></div>
        </el-carousel-item>
        <el-carousel-item name="gaugeThree">
          <div class="chart" id="chart_gauge3"></div>
        </el-carousel-item>
      </el-carousel>
    </div>
</template>

<script>
import echarts from "echarts";
export default {
  name: "threeDashboards",
  data() {
    return {
      //2018年数据
      data1: [
        { value: 7, name: "资本充足率" },
        { value: 66, name: "核心资本充足率" },
        { value: 89, name: "核心一级资本充足率" }
      ],
      //2019年数据
      data2: [
        { value: 40, name: "资本充足率" },
        { value: 3, name: "核心资本充足率" },
        { value: 79, name: "核心一级资本充足率" }
      ],
      //2020年数据
      data3: [
        { value: 30, name: "资本充足率" },
        { value: 46, name: "核心资本充足率" },
        { value: 4, name: "核心一级资本充足率" }
      ],
      series: [
        //左边
        {
          //外圈刻度
          name: "左 外刻度",
          type: "gauge",
          center: ["20%", "47%"], // 默认全局居中
          radius: "55%",
          min: 0, //最小刻度
          max: 100, //最大刻度
          splitNumber: 10, //刻度数量
          startAngle: 200,
          endAngle: 50,
          axisLine: {
            show: true,
            lineStyle: {
              width: 0.7,
              color: [[1, "rgba(0,0,0,0)"]]
            }
          }, //仪表盘轴线
          axisLabel: {
            show: false,
            color: "#4b695e",
            fontSize: 7,
            distance: -15,
            formatter: function(v) {
              return v;
            }
          }, //刻度标签。
          axisTick: {
            show: true,
            splitNumber: 2,
            lineStyle: {
              color: "#263b35",
              width: 0.7
              // length:10
            },
            length: -5
          }, //刻度样式
          splitLine: {
            show: true,
            length: -5,
            lineStyle: {
              color: "#00ffff",
              width: 1
            }
          } //分隔线样式
        },
        //右边
        {
          //外圈刻度
          name: "右 外刻度",
          type: "gauge",
          center: ["80%", "47%"],
          radius: "55%",
          min: 0, //最小刻度
          max: 100, //最大刻度
          splitNumber: 10, //刻度数量
          //             startAngle: 255,
          //     endAngle: 480,
          startAngle: 130,
          endAngle: -20,
          axisLine: {
            show: true,
            lineStyle: {
              width: 0.7,
              color: [[1, "rgba(0,0,0,0)"]]
            }
          }, //仪表盘轴线
          axisTick: {
            show: true,
            splitNumber: 2,
            lineStyle: {
              color: "#263b35",
              width: 0.7
              // length:10
            },
            length: -5
          }, //刻度样式
          splitLine: {
            show: true,
            length: -5,
            lineStyle: {
              color: "#00ffff",
              width: 1
            }
          } //分隔线样式
        }
      ]
    };
  },
  computed: {},
  mounted() {
    this.getChart();
  },
  methods: {
    getChart() {
      //获取祖先元素宽高，因为走马灯el-carousel-item标签的display:none,如果不预设宽高，会默认为100px而不是100%
      var dataHeight = document.getElementById("carousel_box").offsetHeight;
      var dataWidth = document.getElementById("carousel_box").offsetWidth;
      //初始化修改装有echart的div宽高
      document.getElementById("chart_gauge1").style.height =
        dataHeight - 10 + "px";
      document.getElementById("chart_gauge1").style.width = dataWidth + "px";
      document.getElementById("chart_gauge2").style.height =
        dataHeight - 10 + "px";
      document.getElementById("chart_gauge2").style.width = dataWidth + "px";
      document.getElementById("chart_gauge3").style.height =
        dataHeight - 10 + "px";
      document.getElementById("chart_gauge3").style.width = dataWidth + "px";
      var myChart1 = echarts.init(document.getElementById("chart_gauge1"));
      var myChart2 = echarts.init(document.getElementById("chart_gauge2"));
      var myChart3 = echarts.init(document.getElementById("chart_gauge3"));
      var color1 = new echarts.graphic.LinearGradient(0, 0, 1, 1, [
        {
          offset: 0,
          color: "#6afdb6"
        },
        {
          offset: 1,
          color: "#006ced"
        }
      ]);
      var color2 = new echarts.graphic.LinearGradient(0, 0, 1, 1, [
        {
          offset: 0,
          color: "#FF0066"
        },
        {
          offset: 1,
          color: "#990000"
        }
      ]);
      //资本充足率阈值
      var threshold1 = 8;
      //核心资本充足率阈值
      var threshold2 = 4;
      //核心一级资本充足率阈值
      var threshold3 = 5;
      var option1 = {
        series: [
          {
            //内圈进度条
            name: "中 内圈进度条",
            type: "gauge",
            radius: "85%",
            center: ["50%", "55%"],
            splitNumber: 0, //刻度数量
            startAngle: 180,
            endAngle: 0,
            axisLine: {
              show: true,
              lineStyle: {
                width: 10,
                color: [
                  [1, this.data1[0].value >= threshold1 ? color1 : color2]
                ]
              }
            },
            //分隔线样式。
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            axisTick: {
              show: false
            },
            pointer: {
              show: true,
              length: "80%",
              width: "2%"
            },
            title: {
              show: true,
              offsetCenter: [0, "30%"], // x, y，单位px
              textStyle: {
                // fontWeight: "bold",
                color: "#ade3ff",
                fontSize: 11
              }
            },
            //仪表盘详情，用于显示数据。
            detail: {
              show: true,
              offsetCenter: [0, "-30%"],
              color: "#ffffff",
              textStyle: {
                fontSize: 30,
                color: this.data1[0].value >= threshold1 ? "#00ffff" : "red"
              },
              formatter: function(value) {
                return value + "{a|}";
              },
              rich: {
                a: {
                  fontSize: 35,
                  color: "#00ffff"
                }
              }
            },
            data: [this.data1[0]]
          },
          //左边
          this.series[0],
          {
            //内圈进度条
            name: "左 内圈进度条",
            type: "gauge",
            radius: "50%",
            center: ["20%", "47%"], // 默认全局居中
            splitNumber: 0, //刻度数量
            startAngle: 200,
            endAngle: 50,
            axisLine: {
              show: true,
              lineStyle: {
                width: 8,
                color: [
                  [
                    1,
                    Number(this.data1[1].value) >= threshold2 ? color1 : color2
                  ]
                ]
              }
            },
            //分隔线样式。
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            axisTick: {
              show: false
            },
            pointer: {
              show: true,
              length: "80%",
              width: "2%"
            },
            title: {
              show: true,
              offsetCenter: ["-10%", "80%"], // x, y，单位px
              textStyle: {
                // fontWeight: "bold",
                color: "#ade3ff",
                fontSize: 11
              }
            },
            //仪表盘详情，用于显示数据。
            detail: {
              show: true,
              offsetCenter: ["-10%", "-20%"],
              color: "#ffffff",
              textStyle: {
                fontSize: 15,
                color: this.data1[1].value >= threshold2 ? "#00ffff" : "red"
              },
              formatter: function(value) {
                return value + "{a|}";
              },
              rich: {
                a: {
                  fontSize: 25,
                  color: "#00ffff"
                }
              }
            },
            data: [this.data1[1]]
          },
          //右边
          this.series[1],
          {
            //内圈进度条
            name: "右 内圈进度条",
            type: "gauge",
            radius: "50%",
            center: ["80%", "47%"],
            splitNumber: 0, //刻度数量
            startAngle: 130,
            endAngle: -20,
            axisLine: {
              show: true,
              lineStyle: {
                width: 8,
                color: [
                  [1, this.data1[2].value >= threshold3 ? color1 : color2]
                ]
              }
            },
            //分隔线样式。
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            axisTick: {
              show: false
            },
            pointer: {
              show: true,
              length: "80%",
              width: "2%"
            },
            title: {
              show: true,
              offsetCenter: ["10%", "80%"], // x, y，单位px
              textStyle: {
                // fontWeight: "bold",
                color: "#ade3ff",
                fontSize: 11
              }
            },
            //仪表盘详情，用于显示数据。
            detail: {
              show: true,
              offsetCenter: ["10%", "-20%"],
              color: "#ffffff",
              textStyle: {
                fontSize: 15,
                color: this.data1[2].value >= threshold3 ? "#00ffff" : "red"
              },
              formatter: function(value) {
                return value + "{a|}";
              },
              rich: {
                a: {
                  fontSize: 25,
                  color: "#00ffff"
                }
              }
            },
            data: [this.data1[2]]
          }
        ]
      };
      var option2 = {
        series: [
          {
            //内圈进度条
            name: "中 内圈进度条",
            type: "gauge",
            radius: "85%",
            center: ["50%", "55%"],
            splitNumber: 0, //刻度数量
            startAngle: 180,
            endAngle: 0,
            axisLine: {
              show: true,
              lineStyle: {
                width: 10,
                color: [
                  [1, this.data2[0].value >= threshold1 ? color1 : color2]
                ]
              }
            },
            //分隔线样式。
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            axisTick: {
              show: false
            },
            pointer: {
              show: true,
              length: "80%",
              width: "2%"
            },
            title: {
              show: true,
              offsetCenter: [0, "30%"], // x, y，单位px
              textStyle: {
                // fontWeight: "bold",
                color: "#ade3ff",
                fontSize: 11
              }
            },
            //仪表盘详情，用于显示数据。
            detail: {
              show: true,
              offsetCenter: [0, "-30%"],
              color: "#ffffff",
              textStyle: {
                fontSize: 30,
                color: this.data2[0].value >= threshold1 ? "#00ffff" : "red"
              },
              formatter: function(value) {
                return value + "{a|}";
              },
              rich: {
                a: {
                  fontSize: 35,
                  color: "#00ffff"
                }
              }
            },
            data: [this.data2[0]]
          },
          //左边
          this.series[0],
          {
            //内圈进度条
            name: "左 内圈进度条",
            type: "gauge",
            radius: "50%",
            center: ["20%", "47%"], // 默认全局居中
            splitNumber: 0, //刻度数量
            startAngle: 200,
            endAngle: 50,
            axisLine: {
              show: true,
              lineStyle: {
                width: 8,
                color: [
                  [1, this.data2[1].value >= threshold2 ? color1 : color2]
                ]
              }
            },
            //分隔线样式。
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            axisTick: {
              show: false
            },
            pointer: {
              show: true,
              length: "80%",
              width: "2%"
            },
            title: {
              show: true,
              offsetCenter: ["-10%", "80%"], // x, y，单位px
              textStyle: {
                // fontWeight: "bold",
                color: "#ade3ff",
                fontSize: 11
              }
            },
            //仪表盘详情，用于显示数据。
            detail: {
              show: true,
              offsetCenter: ["-10%", "-20%"],
              color: "#ffffff",
              textStyle: {
                fontSize: 15,
                color: this.data2[1].value >= threshold2 ? "#00ffff" : "red"
              },
              formatter: function(value) {
                return value + "{a|}";
              },
              rich: {
                a: {
                  fontSize: 25,
                  color: "#00ffff"
                }
              }
            },
            data: [this.data2[1]]
          },
          //右边
          this.series[1],
          {
            //内圈进度条
            name: "右 内圈进度条",
            type: "gauge",
            radius: "50%",
            center: ["80%", "47%"],
            splitNumber: 0, //刻度数量
            startAngle: 130,
            endAngle: -20,
            axisLine: {
              show: true,
              lineStyle: {
                width: 8,
                color: [
                  [1, this.data2[2].value >= threshold3 ? color1 : color2]
                ]
              }
            },
            //分隔线样式。
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            axisTick: {
              show: false
            },
            pointer: {
              show: true,
              length: "80%",
              width: "2%"
            },
            title: {
              show: true,
              offsetCenter: ["10%", "80%"], // x, y，单位px
              textStyle: {
                // fontWeight: "bold",
                color: "#ade3ff",
                fontSize: 11
              }
            },
            //仪表盘详情，用于显示数据。
            detail: {
              show: true,
              offsetCenter: ["10%", "-20%"],
              color: "#ffffff",
              textStyle: {
                fontSize: 15,
                color: this.data2[2].value >= threshold3 ? "#00ffff" : "red"
              },
              formatter: function(value) {
                return value + "{a|}";
              },
              rich: {
                a: {
                  fontSize: 25,
                  color: "#00ffff"
                }
              }
            },
            data: [this.data2[2]]
          }
        ]
      };
      var option3 = {
        series: [
          {
            //内圈进度条
            name: "中 内圈进度条",
            type: "gauge",
            radius: "85%",
            center: ["50%", "55%"],
            splitNumber: 0, //刻度数量
            startAngle: 180,
            endAngle: 0,
            axisLine: {
              show: true,
              lineStyle: {
                width: 10,
                color: [
                  [1, this.data3[0].value >= threshold1 ? color1 : color2]
                ]
              }
            },
            //分隔线样式。
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            axisTick: {
              show: false
            },
            pointer: {
              show: true,
              length: "80%",
              width: "2%"
            },
            title: {
              show: true,
              offsetCenter: [0, "30%"], // x, y，单位px
              textStyle: {
                // fontWeight: "bold",
                color: "#ade3ff",
                fontSize: 11
              }
            },
            //仪表盘详情，用于显示数据。
            detail: {
              show: true,
              offsetCenter: [0, "-30%"],
              color: "#ffffff",
              textStyle: {
                fontSize: 30,
                color: this.data3[0].value >= threshold1 ? "#00ffff" : "red"
              },
              formatter: function(value) {
                return value + "{a|}";
              },
              rich: {
                a: {
                  fontSize: 35,
                  color: "#00ffff"
                }
              }
            },
            data: [this.data3[0]]
          },
          //左边
          this.series[0],
          {
            //内圈进度条
            name: "左 内圈进度条",
            type: "gauge",
            radius: "50%",
            center: ["20%", "47%"], // 默认全局居中
            splitNumber: 0, //刻度数量
            startAngle: 200,
            endAngle: 50,
            axisLine: {
              show: true,
              lineStyle: {
                width: 8,
                color: [
                  [1, this.data3[1].value >= threshold2 ? color1 : color2]
                ]
              }
            },
            //分隔线样式。
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            axisTick: {
              show: false
            },
            pointer: {
              show: true,
              length: "80%",
              width: "2%"
            },
            title: {
              show: true,
              offsetCenter: ["-10%", "80%"], // x, y，单位px
              textStyle: {
                // fontWeight: "bold",
                color: "#ade3ff",
                fontSize: 11
              }
            },
            //仪表盘详情，用于显示数据。
            detail: {
              show: true,
              offsetCenter: ["-10%", "-20%"],
              color: "#ffffff",
              textStyle: {
                fontSize: 15,
                color: this.data3[1].value >= threshold2 ? "#00ffff" : "red"
              },
              formatter: function(value) {
                return value + "{a|}";
              },
              rich: {
                a: {
                  fontSize: 25,
                  color: "#00ffff"
                }
              }
            },
            data: [this.data3[1]]
          },
          //右边
          this.series[1],
          {
            //内圈进度条
            name: "右 内圈进度条",
            type: "gauge",
            radius: "50%",
            center: ["80%", "47%"],
            splitNumber: 0, //刻度数量
            startAngle: 130,
            endAngle: -20,
            axisLine: {
              show: true,
              lineStyle: {
                width: 8,
                color: [
                  [1, this.data3[2].value >= threshold3 ? color1 : color2]
                ]
              }
            },
            //分隔线样式。
            splitLine: {
              show: false
            },
            axisLabel: {
              show: false
            },
            axisTick: {
              show: false
            },
            pointer: {
              show: true,
              length: "80%",
              width: "2%"
            },
            title: {
              show: true,
              offsetCenter: ["10%", "80%"], // x, y，单位px
              textStyle: {
                // fontWeight: "bold",
                color: "#ade3ff",
                fontSize: 11
              }
            },
            //仪表盘详情，用于显示数据。
            detail: {
              show: true,
              offsetCenter: ["10%", "-20%"],
              color: "#ffffff",
              textStyle: {
                fontSize: 15,
                color: this.data3[2].value >= threshold3 ? "#00ffff" : "red"
              },
              formatter: function(value) {
                return value + "{a|}";
              },
              rich: {
                a: {
                  fontSize: 25,
                  color: "#00ffff"
                }
              }
            },
            data: [this.data3[2]]
          }
        ]
      };
      myChart1.setOption(option1);
      myChart2.setOption(option2);
      myChart3.setOption(option3);
    }
  }
};
</script>

<style lang="scss" scoped>
  .gauge-container {
    position: absolute;
    overflow: hidden;
    display: block;
    cursor: default;
    z-index: 0;
    left: 0px;
    top: 10px;
    width: 100%;
    height: 100%;
  }
  .el-carousel {
    height: 100%;
    width: 100%;
  }
  .el-carousel__item .chart {
    width: 100%;
    height: 100%;
  }
</style>
