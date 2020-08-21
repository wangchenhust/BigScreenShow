<template>
  <div class="threeDashboards-container">
    <div class="chart" id="chart_xx"></div>
  </div>
</template>

<script>
  import echarts from "echarts";
  import {mapGetters, mapState} from "vuex";
  export default {
    name: "threePanels",
    data(){
      return{
        option: null,
        //颜色
        color:["rgba(255,0,0,0.8)","#ffa800","#ffe000","rgba(0,255,0,0.8)"],
        //各个指标的阈值
        limit:[],//[8,4,5,60],
        //默认数据
        dataMap:{
          // //第五个
          // 4:[],
          //第四个
          3:[],
          //第三个
          2:[],
          //第二个
          1:[],
          //第一个
          0:[],
        },
        //4级颜色
        splitNumber:4,
        //颜色及分布
        colorTemplate:null,
        //每个指标的最大值
        max:[],
        //图例
        legendData:['风险报警','高风险','中风险','安全'],
        //时间轴的label
        label:['0','1','2','3']
      }
    },
    computed: {
      ...mapGetters(
        {getValues:'panel/getValues',getRisk:'risk/getValues'}),
      ...mapState(
         ['panel/values','risk/values'])
    },
    //监听store的value变化, 当数据库变化时，监听器监听到，并把监听到的数据放store,组件监听store的变化即可
    watch:{
      getValues:{
        handler(newVal,oldVal) {// eslint-disable-line no-unused-vars
          console.log("watch: panel store更改！！")
          this.setLimit();//设置阈值列表，跟着getValues的name变化而变化
          this.setColorTemplate(this.splitNumber);
          this.setDataMap()
          this.getChart()
        }
      }
    },
    mounted() {
      this.initData();
      // this.setLimit();//设置阈值列表，跟着getValues的name变化而变化
      // this.setColorTemplate(this.splitNumber);
      // this.setDataMap();
      // this.getChart();
    },
    methods: {
      //初始化时向后端取数据放到store中
      async initData(){
        let data1=await this.$H.get('/GetData/Panel');
        this.$store.commit('panel/setValues',data1);
      },
      //根据limit数组设置颜色分布
      setColorTemplate(){
        let x=0;
        let y=0;
        let colorTemplate=[[],[]];
        this.colorTemplate=[];
        //设置基本的colorTemplate
        for(let i=0;i<this.splitNumber;i++){
          colorTemplate[0][i]=[//不小于 红---
            x+=1/this.splitNumber, this.color[i]]

          if(i==this.splitNumber-1)
            colorTemplate[1][i]=[//不大于，绿--
              y=1,
              this.color[this.splitNumber-i-1]]

          colorTemplate[1][i]=[//不大于，绿--
            y+=50/(this.splitNumber-1)/100,
            this.color[this.splitNumber-i-1]]

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
                  x+=this.limit[i]/100,this.color[j]]
              else if(j==this.splitNumber-1)
                this.colorTemplate[i][j]=[
                  x=1,this.color[j]]
              else
                this.colorTemplate[i][j]=[
                  x+=Math.floor((100-this.limit[i])/(this.splitNumber-1))/100,
                  this.color[j]]
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
      //初始化limit数组
      setLimit(){
        this.limit=[];
        for(let j in this.getValues){
          for(let i in this.getRisk){
            // console.log(this.getRisk[i].name+" 与 "+this.getValues[j].name);
            if(this.getRisk[i].name.toString().localeCompare(this.getValues[j].name)==0){
              // console.log("相等push: "+this.getRisk[i].value);
              this.limit.push(this.getRisk[i].value);
              break;
            }
            else{
              // console.log("不等");
            }
          }
        }
        console.log("仪表盘阈值列表："+this.limit);
      },
      setDataMap(){
        // console.log("阈值2：");
        // console.log(this.getRisk);
        this.dataMap={
          //第1个
          0:[],
          //第2个
          1:[],
          //第3个
          2:[],
          //第4个
          3:[],
          // //第五个
          // 4:[]
        };//清空
        var count =0;
        for (let i in this.getValues){
          var index=this.label[count];
            this.dataMap[index].push(
                    this.getValues[i]
            );
          count++;
        }
      },
      getChart(){
        let myChart = echarts.init(document.getElementById('chart_xx'))

        function setText(limit) {
          if(limit>0)
            return '目标值：>='+Math.abs(limit)+'%';
          else
            return '目标值：<='+Math.abs(limit)+'%';
        }

        // var setOpions=()=>{//可以调用到this
        //   let options=[];
        //   for(let i in this.limit){
        //     options.push({//第i+1个
        //       series: [
        //         {//中间进度条 不超过8%
        //           max: this.max[i],
        //           axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
        //             lineStyle: {			// 仪表盘轴线样式。
        //               color: this.colorTemplate[i], 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
        //             }
        //           },
        //           data: [this.dataMap[this.limit[i]][i]],//时间轴的下标是limit元素
        //         },
        //         {
        //           detail: {
        //             formatter: setText(this.limit[i]),
        //           }
        //         }
        //       ]
        //     })
        //   }
        //   return options;
        // }

        this.option ={
          baseOption:{
            //时间轴
            timeline: {
              axisType: 'category',
              autoPlay: true,
              // orient: "vertical",//垂直
              playInterval: 3000,//暂停时间3s
              data: this.label,
              left: 80,
              right: 80,
              bottom: -20,
              // top:10,
              // left: 280,
              // right: 10,
              // bottom: 30,
              symbolSize: 7,//轴点大小
              lineStyle: {
                color: '#179bf1',
                // bottom: 10
                width:1.3,
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
                symbolSize: 7,
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
                shadowBlur: 10
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
            //图例
            color:this.color,//图例的颜色
            legend: {
              show: true,
              icon: 'circle',//图例形状
              orient: 'vertical',  //垂直显示
              bottom: 60,
              left:5,
              itemWidth: 9, // 图例标记的图形宽度。[ default: 25 ]
              itemHeight: 9, // 图例标记的图形高度。[ default: 14 ]
              itemGap: 8, // 图例每项之间的间隔。[ default: 10 ]横向布局时为水平间隔，纵向布局时为纵向间隔。
              textStyle: {
                fontSize: 9,
                color: '#fff'//ade3ff
              },
              data: this.legendData,
            },
            xAxis:[{
              type:'category',
              data:[],
              show:false,
              axisLine:{show:false},
              splitLine:{show:false},
              splitArea:{
                interval:'auto',
                show:false}
            }],
            yAxis:[{
              type:'value',
              show:false,
              axisLine:{show:false},
              splitLine:{show:false}
            }],

            series: [
              {//内圈进度条
                name:"中 内圈进度条",
                type: "gauge",
                radius: '105%',
                center: ["50%", "63%"],
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
                  distance: 5,			// 标签与刻度线的距离,默认 5。
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
                  offsetCenter: [0,"17%"], // x, y，单位px
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
              },
              {
                type: 'gauge',
                radius: '90%',
                splitNumber: 10,
                splitLine: {
                  show: false
                },
                min: 0,
                max: 100,
                startAngle: 225,
                endAngle: -45,
                axisLabel: {
                  show: false
                },
                axisLine: {
                  show: false,
                },
                pointer: {
                  show: 0
                },
                axisTick: {
                  show: false
                },
                detail: {
                  show: true,
                  offsetCenter: [0, '70%'],
                  formatter: setText(this.limit[0]),
                  textStyle: {
                    fontSize: 11,
                    color: '#ffeb7b',
                    lineHeight: 50,
                    fontWeight: '100',
                  }
                }
              },
              //图例
              {
                name:this.legendData[0],
                type:'bar',
                barWidth:'50%',
                data:[0],
                // itemStyle:{normal:{color:'#4094D0',}}
              },
              //图例
              {
                name:this.legendData[1],
                type:'bar',
                barWidth:'50%',
                data:[0],
                // itemStyle:{normal:{color:'#EE843C',}}
              },
              //图例
              {
                name:this.legendData[2],
                type:'bar',
                barWidth:'50%',
                data:[0],
                // itemStyle:{normal:{color:'#EE843C',}}
              },
              //图例
              {
                name:this.legendData[3],
                type:'bar',
                barWidth:'50%',
                data:[0],
                // itemStyle:{normal:{color:'#EE843C',}}
              },
            ],
          },
          options: //setOpions,
          [
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
                },
                {
                  detail: {
                    formatter: setText(this.limit[0]),
                  }
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
                },
                {
                  detail: {
                    formatter: setText(this.limit[1]),
                  }
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
                },
                {
                  detail: {
                    formatter: setText(this.limit[2]),
                  }
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
                },
                {
                  detail: {
                    formatter: setText(this.limit[3]),
                  }
                }
              ]
            },
            // {//第五个
            //   series: [
            //     {//4%
            //       max: this.max[4],
            //       axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
            //         lineStyle: {			// 仪表盘轴线样式。
            //           color: this.colorTemplate[4], 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
            //         }
            //       },
            //       data: [this.dataMap['4'][0]],
            //     },
            //     {
            //       detail: {
            //         formatter: setText(this.limit[4]),
            //       }
            //     }
            //   ]
            // },
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
