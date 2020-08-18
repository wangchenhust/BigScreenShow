<template>
  <div class="barChart-container">
    <div class="chart" id="chart_left1"></div>
  </div>
</template>

<script>
import echarts from "echarts";
import { mapGetters,mapState } from 'vuex'

export default {
  name: "barChart",
  data() {
    return {
      //柱状图
      datay: [
        // "资产利润率",
        // "资本利润率",
        // "不良贷款率",
        // "不良资产率",
        // "流动性比例"
      ],
      datax: [
              // 0.9, 0.8, 0.6, 0.4, 0.2
      ],
      datax_max: [100, 100, 100, 100, 100],
      option:null,
    };
  },
  computed: {
    ...mapGetters({
      getValues:'bar/getValues'
    }),
    ...mapState([
      'bar/values'
    ])
  },
  mounted() {
    this.initData();
    // this.setDatax_y();
    // this.setOption();
    // if (typeof (EventSource) == "undefined") this.timer();
  },
  watch: {
    datax: function() {//高亮
      this.setOption();
    },
    getValues:{
      handler(newVal,oldVal) {// eslint-disable-line no-unused-vars
        console.log("watch: bar store更改！！")
        this.setDatax_y();
        this.setOption();
      }
    }
  },
  methods: {
    async initData(){
      let data1=await this.$H.get('/GetData/Bar');
      this.$store.commit('bar/setValues',data1);
    },
    setDatax_y(){
      this.datax.length=0;//清空原数据
      this.datay.length=0//清空原数据
      console.log("柱状图的值："+this.getValues[0].value)
      for (let i in this.getValues){
        this.datax.push(this.getValues[i].value);
        this.datay.push(this.getValues[i].name);
      }
    },
    setOption() {
      let myChart = this.$echarts.init(document.getElementById('chart_left1'));
      let gradientColor = new this.$echarts.graphic.LinearGradient(0, 0, 1, 1, [
        { offset: 0, color: "rgb(186,39,38)" },
        { offset: 0.5, color: "rgb(238,64,61)" },
        { offset: 1, color: "rgba(221,107,102,0.7)" }
      ]);
      this.option = {
        grid: {
          //整个图表
          left: "2%",
          right: "3%",
          bottom: "0%",
          top: "15%",
          containLabel: true
        },
        tooltip: {
          //详情框
          trigger: "axis",
          axisPointer: {
            type: "none"
          },
          formatter: function(params) {
            return (
              params[0].name +
              "<br/>" +
              "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:rgba(36,207,233,0.9)'></span>" +
              params[0].seriesName +
              " : " +
              Number(params[0].value).toLocaleString() +
              "%<br/>"
            );
          }
        },
        xAxis: {
          show: false,
          type: "value"
        },
        yAxis: [
          {
            //分类说明
            type: "category",
            inverse: true,
            axisLabel: {
              show: true,
              textStyle: {
                color: "#b3ccf8"
              }
            },
            splitLine: {
              show: false
            },
            axisTick: {
              show: false
            },
            axisLine: {
              show: false
            },
            data: this.datay
          },
          {
            //数值显示
            type: "category",
            inverse: true,
            axisTick: "none",
            axisLine: "none",
            show: true,
            axisLabel: {
              textStyle: {
                color: "#b3ccf8",
                fontSize: "12"
              },
              formatter: function(value) {
                return (value).toLocaleString() + " %";
              }
            },
            data: this.datax
          }
        ],
        series: [
          {
            //亮色柱体
            name: "金额",
            type: "bar",
            zlevel: 1,
            barWidth: "6px", //宽度
            data: this.datax,
            itemStyle: {
              normal: {
                barBorderRadius: 20, //圆度
                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                  {
                    offset: 0,
                    color: "rgb(57,89,255,1)"
                  },
                  {
                    offset: 1,
                    color: "rgb(46,200,207,1)"
                  }
                ])
              }
            },
            emphasis: {
              itemStyle: {
                //颜色渐变函数LinearGradient，4和参数分别表示四个位置依次为左、下、右、上
                color: gradientColor,
                barBorderRadius: 20,
                borderColor: gradientColor,
                borderWidth: 3,
                lineWidth: 2,
                twinkle: {
                  //闪烁
                  enabled: true, //启用
                  period: 2000 //动画时间
                }
              },
              label: {
                show: false
              }
            }
          },
          {
            //暗色柱体
            name: "背景",
            type: "bar",
            barWidth: "10px",
            barGap: "-100%",
            data: this.datax_max,
            itemStyle: {
              normal: {
                color: "#00092d",
                barBorderRadius: 30
              }
            }
          }
        ]
      };
      //高亮
      this.datax.forEach((item, curidx) => {
        //取消之前的高亮
        myChart.dispatchAction({
          type: "downplay",
          seriesIndex: 0,
          dataIndex: curidx
        });
        if ((item < 0.006) & (curidx == 0)) {
          myChart.dispatchAction({
            type: "highlight",
            dataIndex: curidx
          });
        }
        if ((item < 0.11) & (curidx == 1)) {
          myChart.dispatchAction({
            type: "highlight",
            dataIndex: curidx
          });
        }
        if ((item > 0.05) & (curidx == 2)) {
          myChart.dispatchAction({
            type: "highlight",
            dataIndex: curidx
          });
        }
        if ((item > 0.04) & (curidx == 3)) {
          myChart.dispatchAction({
            type: "highlight",
            dataIndex: curidx
          });
        }
        if ((item < 0.25) & (curidx == 4)) {
          myChart.dispatchAction({
            type: "highlight",
            dataIndex: curidx
          });
        }
      });

      myChart.setOption(this.option,true);

      window.addEventListener('resize', () => {
        myChart.resize();
      });
    },
  }
};
</script>

<style lang="scss" scoped>
  .chart {
    width: 100%;
    height: 100%;
  }
  .barChart-container {
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
