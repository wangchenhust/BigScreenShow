<template>
  <div class="threeDashboards-container">
    <div class="chart" id="chart_xx"></div>
  </div>
</template>

<script>
  import echarts from "echarts";
  export default {
    name: "threeDashboards",
    data(){
      return{
        option: null,
        limit:[60,-5,-4,0.6],
        dataMap:{
          //第四个
          3:[{ value: 17, name: "资产利润率" }],//>=0.6
          //第三个
          2:[{ value: 7, name: "不良资产率" }],//<=4
          //第二个
          1:[{ value: 79, name: "不良贷款率" }],//<=5
          //第一个
          0:[{ value: 4, name: "核心负债依存度" }],//>=60
        },
        //4级颜色
        splitNumber:4,
        //颜色及分布
        colorTemplate:null,
        //每个指标的最大值
        max:[]
      }
    },
    computed: {

    },
    mounted() {
      this.setColorTemplate(this.splitNumber)
      this.getChart();
    },
    methods: {
      setColorTemplate(){
        let x=0;
        let y=0;
        let color=["rgba(255,0,0,0.8)","#ffa800","#ffe000","rgba(0,255,0,0.8)"];
        let colorTemplate=[[],[]];
        this.colorTemplate=[];
        //设置基本的colorTemplate
        for(let i=0;i<this.splitNumber;i++){
          colorTemplate[0][i]=[//不小于 红---
            x+=1/this.splitNumber, color[i]]

          if(i==this.splitNumber-1)
            colorTemplate[1][i]=[//不大于，绿--
              y=1,
              color[this.splitNumber-i-1]]

          colorTemplate[1][i]=[//不大于，绿--
            y+=50/(this.splitNumber-1)/100,
            color[this.splitNumber-i-1]]

        }
        //为每个指标设置colorTemplate和max
        for(let i in this.limit){
          if(this.limit[i]>25){//某指标阈值大于25，则专门分配
            this.colorTemplate[i]=[];
            this.max[i]=100;
            let x=0;
            for(let j=0;j<this.splitNumber;j++){
              if(j==0)
                this.colorTemplate[i][j]=[
                        x+=this.limit[i]/100,color[j]]
              else
                this.colorTemplate[i][j]=[
                  x+=Math.floor((100-this.limit[i])/(this.splitNumber-1))/100,color[j]]
            }
          }
          else if(this.limit[i]>0){
            this.max[i]=Math.abs(this.limit[i])*this.splitNumber;
            this.colorTemplate[i]=colorTemplate[0];
          }
          else{
            this.max[i]=Math.abs(this.limit[i])*2;
            this.colorTemplate[i]=colorTemplate[1];
          }

        }
      },
      getChart(){
        let myChart = echarts.init(document.getElementById('chart_xx'))
        //颜色
        // let color=["rgba(255,0,0,0.8)","#ffa800","#ffe000","rgba(0,255,0,0.8)"]
        // let colorTemplate1 = [[0.25, "rgba(255,0,0,0.8)"], [0.5, "#ffa800"], [0.75, "#ffe000"], [1, "rgba(0,255,0,0.8)"]];
        // let colorTemplate2 = [[0.6, "rgba(255,0,0,0.8)"], [0.7, "#ffa800"], [0.8, "#ffe000"], [1, "rgba(0,255,0,0.8)"]];

        this.option ={
          baseOption:{
            timeline: {
              axisType: 'category',
              autoPlay: true,
              playInterval: 3000,//暂停时间3s
              data: ['0', '1', '2','3'],
              left: 80,
              right: 80,
              bottom: -15,
              lineStyle: {
                color: '#179bf1',
                bottom: 10
              },
              label: {
                show:false,
                position:15,
                textStyle: {
                  // fontWeight: "bold",
                  color: "#ade3ff",
                  fontSize: 10,
                },
              },
              checkpointStyle: {
                color: '#01d8ff',
                symbolSize: 10,
                borderColor: 'rgba(1, 216, 255, 0.5)',
                borderWidth: 5
              },
              controlStyle: {
                show: false,
              },
              itemStyle: {
                borderColor: '#004b85',
                borderWidth: 1,
                shadowColor: 'rgba(1, 216, 225, 0.5)',
                shadowBlur: 7
              },
              emphasis: {
                checkpointStyle: {
                  color: '#01d8ff',
                  borderColor: 'rgba(1, 216, 255, 0.5)',
                  borderWidth: 5,
                },
                itemStyle: {
                  color: '#01d8ff',
                  borderColor: 'rgba(1, 216, 225, 0.5)',
                  borderWidth: 5
                }
              }
            },
            series: [
              {//内圈进度条
                name:"中 内圈进度条",
                type: "gauge",
                radius: '90%',
                center: ["50%", "60%"],
                startAngle: 180,		// 仪表盘起始角度,默认 225。圆心 正右手侧为0度，正上方为90度，正左手侧为180度。
                endAngle: 0,			// 仪表盘结束角度,默认 -45
                clockwise: true,		// 仪表盘刻度是否是顺时针增长,默认 true。
                min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                max: this.max[0],				// 最大的数据值,默认 100 。映射到 maxAngle。
                splitNumber: this.splitNumber,		// 仪表盘刻度的分割段数,默认 10。

                axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
                  show: true,				// 是否显示仪表盘轴线(轮廓线),默认 true。
                  lineStyle: {			// 仪表盘轴线样式。
                    color: this.colorTemplate[0], 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 12,					//轴线宽度,默认 30。
                    shadowBlur: 7,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  }
                },

                splitLine: {			// 分隔线样式。
                  show: true,				// 是否显示分隔线,默认 true。
                  length: 12,				// 分隔线线长。支持相对半径的百分比,默认 30。
                  lineStyle: {			// 分隔线样式。
                    color: "#eee",				//线的颜色,默认 #eee。
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 2,					//线度,默认 2。
                    type: "solid",				//线的类型,默认 solid。 此外还有 dashed,dotted
                    shadowBlur: 5,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  }
                },

                axisTick: {				// 刻度(线)样式。
                  show: true,				// 是否显示刻度(线),默认 true。
                  splitNumber: 5,			// 分隔线之间分割的刻度数,默认 5。
                  length: 5,				// 刻度线长。支持相对半径的百分比,默认 8。
                  lineStyle: {			// 刻度线样式。
                    color: "#eee",				//线的颜色,默认 #eee。
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 1,					//线度,默认 1。
                    type: "solid",				//线的类型,默认 solid。 此外还有 dashed,dotted
                    shadowBlur: 5,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  },
                },

                axisLabel: {			// 刻度标签。
                  show: true,				// 是否显示标签,默认 true。
                  distance: 2,			// 标签与刻度线的距离,默认 5。
                  color: "#fff",			// 文字的颜色,默认 #fff。
                  fontSize: 10,			// 文字的字体大小,默认 5。
                  formatter: "{value}",	// 刻度标签的内容格式器，支持字符串模板和回调函数两种形式。 示例:// 使用字符串模板，模板变量为刻度默认标签 {value},如:formatter: '{value} kg'; // 使用函数模板，函数参数分别为刻度数值,如formatter: function (value) {return value + 'km/h';}
                },

                pointer: {				// 仪表盘指针。
                  show: true,				// 是否显示指针,默认 true。
                  length: "80%",			// 指针长度，可以是绝对数值，也可以是相对于半径的百分比,默认 80%。
                  width: 3,				// 指针宽度,默认 8。
                },

                itemStyle: {			// 仪表盘指针样式。
                  color: "auto",			// 指针颜色，默认(auto)取数值所在的区间的颜色
                  opacity: 1,				// 图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                  borderWidth: 0,			// 描边线宽,默认 0。为 0 时无描边。
                  borderType: "solid",	// 柱条的描边类型，默认为实线，支持 'solid', 'dashed', 'dotted'。
                  borderColor: "#000",	// 图形的描边颜色,默认 "#000"。支持的颜色格式同 color，不支持回调函数。
                  shadowBlur: 5,			// (发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                  shadowColor: "#fff",	// 阴影颜色。支持的格式同color。
                },

                emphasis: {				// 高亮的 仪表盘指针样式
                  itemStyle: {
                    //高亮 和正常  两者具有同样的配置项,只是在不同状态下配置项的值不同。
                  }
                },

                title: {
                  show: true,
                  offsetCenter: [0,"37%"], // x, y，单位px
                  textStyle: {
                    color: "#ade3ff",
                    fontSize: 11,
                  },
                },
                //仪表盘详情，用于显示数据。
                detail: {
                  show: true,
                  offsetCenter: [0, "-30%"],
                  color: "auto",
                  textStyle: {
                    fontSize:25,
                  },
                  formatter: "{value}%",	// 格式化函数或者字符串
                },
                data: [this.dataMap['0'][0]],
              }
            ],
          },
          options: [
            {//第一个
              series: [
                {//中间进度条 不超过8%
                  max: this.max[0],
                  axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
                    lineStyle: {			// 仪表盘轴线样式。
                      color: this.colorTemplate[0], 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                    }
                  },
                  data: [this.dataMap['0'][0]],
                }
              ]
            },{//第二个
              series: [
                {//不超过11%
                  max: this.max[1],
                  axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
                    lineStyle: {			// 仪表盘轴线样式。
                      color: this.colorTemplate[1], 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                    }
                  },
                  data: [this.dataMap['1'][0]],
                }
              ]
            },{//第三个
              series: [
                {//4%
                  max: this.max[2],
                  axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
                    lineStyle: {			// 仪表盘轴线样式。
                      color: this.colorTemplate[2], 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                    }
                  },
                    data: [this.dataMap['2'][0]],
                }
              ]
            },
            {//第四个
              series: [
                {//4%
                  max: this.max[3],
                  axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
                    lineStyle: {			// 仪表盘轴线样式。
                      color: this.colorTemplate[3], 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                    }
                  },
                  data: [this.dataMap['3'][0]],
                }
              ]
            }
          ]
        }
        myChart.setOption(this.option,true);
        window.addEventListener('resize', () => {
          myChart.resize();
        });
      }
    }
  }
</script>

<style lang="scss" scoped>
  .chart {
    width: 100%;
    height: 100%;
  }
  .threeDashboards-container {
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
</style>
