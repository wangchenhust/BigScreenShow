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
        dataMap:{
          //第1个
          0:[],
          //第2个
          1:[],
          //第3个
          2:[],
        },
        // dataa:[{name:"资产利润率",value:2},
        //
        //   {name:"资本利润率",value:13},
        //
        //   {name:"不良贷款率",value:1},
        //
        //   {name:"流动性比例",value:35},
        //
        //   {name:"不良资产率",value:2},
        //
        //   {name:"核心负债依存度",value:69},
        //
        //   {name:"核心资本充足率",value:14},
        //
        //   {name:"资本充足率",value:17},
        //
        //   {name:"核心一级资本充足率",value:13}],
      }
    },
    computed: {
      ...mapGetters({
        getValues:'panel/getValues'
      }),
      ...mapState([
        'panel/values'
      ])
    },
    //监听store的value变化, 当数据库变化时，监听器监听到，并把监听到的数据放store,组件监听store的变化即可
    watch:{
      getValues:{
        handler(newVal,oldVal) {// eslint-disable-line no-unused-vars
          this.setDataMap()
          this.getChart()
        }
      }
    },
    mounted() {
      this.initData();
      this.setDataMap();
      this.getChart();
    },
    methods: {
      //初始化时向后端取数据放到store中
      async initData(){
        let data1=await this.$H.get('/GetData/Panel');
        this.$store.commit('panel/setValues',data1);
      },
      setDataMap(){
        this.dataMap={
          //第1个
          0:[],
          //第2个
          1:[],
          //第3个
          2:[],
        };//清空
        var label=['0','1','2'];
        var count=label.length-1;
        for (let i in this.getValues){
          var index=label[count];
          this.dataMap[index].push(
            this.getValues[i]
          );
          count-=((i+1)%3==0)?1:0;
        }
        console.log(this.dataMap);
      },
      getChart(){
        let myChart = echarts.init(document.getElementById('chart_xx'))
        let colorTemplate1 = [[0.25, "rgba(255,0,0,0.8)"], [0.5, "#ffa800"], [0.75, "#ffe000"], [1, "rgba(0,255,0,0.8)"]];
        let colorTemplate2 = [[0.6, "rgba(255,0,0,0.8)"], [0.7, "#ffa800"], [0.8, "#ffe000"], [1, "rgba(0,255,0,0.8)"]];

        this.option ={
          baseOption:{
            timeline: {
              axisType: 'category',
              autoPlay: true,
              playInterval: 3000,//暂停时间3s
              data: ['2020', '2019', '2018'],
              left: 80,
              right: 80,
              bottom: -10,
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
                radius: '80%',
                center: ["50%", "55%"],
                startAngle: 180,		// 仪表盘起始角度,默认 225。圆心 正右手侧为0度，正上方为90度，正左手侧为180度。
                endAngle: 0,			// 仪表盘结束角度,默认 -45
                clockwise: true,		// 仪表盘刻度是否是顺时针增长,默认 true。
                min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                max: 32,				// 最大的数据值,默认 100 。映射到 maxAngle。
                splitNumber: 4,		// 仪表盘刻度的分割段数,默认 10。

                axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
                  show: true,				// 是否显示仪表盘轴线(轮廓线),默认 true。
                  lineStyle: {			// 仪表盘轴线样式。
                    color: colorTemplate1, 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
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
              },
              //左边
              {//内圈进度条
                name:"左 内圈进度条",
                type: "gauge",
                radius: '70%',
                center: ['20%', '47%'], // 默认全局居中
                startAngle: 200,
                endAngle: 50,
                clockwise: true,		// 仪表盘刻度是否是顺时针增长,默认 true。
                min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                max: 16,				// 最大的数据值,默认 100 。映射到 maxAngle。
                splitNumber: 4,		// 仪表盘刻度的分割段数,默认 10。

                axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
                  show: true,				// 是否显示仪表盘轴线(轮廓线),默认 true。
                  lineStyle: {			// 仪表盘轴线样式。
                    color: colorTemplate1, 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 12,					//轴线宽度,默认 30。
                    shadowBlur: 5,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  }
                },

                splitLine: {			// 分隔线样式。
                  show: true,				// 是否显示分隔线,默认 true。
                  length:12,				// 分隔线线长。支持相对半径的百分比,默认 30。
                  lineStyle: {			// 分隔线样式。
                    color: "#eee",				//线的颜色,默认 #eee。
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 1,					//线度,默认 2。
                    type: "solid",				//线的类型,默认 solid。 此外还有 dashed,dotted
                    shadowBlur: 5,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  }
                },

                axisTick: {				// 刻度(线)样式。
                  show: true,				// 是否显示刻度(线),默认 true。
                  splitNumber: 3,			// 分隔线之间分割的刻度数,默认 5。
                  length: 5,				// 刻度线长。支持相对半径的百分比,默认 8。
                  lineStyle: {			// 刻度线样式。
                    color: "#eee",				//线的颜色,默认 #eee。
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 0.8,					//线度,默认 1。
                    type: "solid",				//线的类型,默认 solid。 此外还有 dashed,dotted
                    shadowBlur: 5,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  },
                },

                axisLabel: {			// 刻度标签。
                  show: true,				// 是否显示标签,默认 true。
                  distance: 2,			// 标签与刻度线的距离,默认 5。
                  color: "#fff",			// 文字的颜色,默认 #fff。
                  fontSize: 8,			// 文字的字体大小,默认 5。
                  formatter: "{value}",	// 刻度标签的内容格式器，支持字符串模板和回调函数两种形式。 示例:// 使用字符串模板，模板变量为刻度默认标签 {value},如:formatter: '{value} kg'; // 使用函数模板，函数参数分别为刻度数值,如formatter: function (value) {return value + 'km/h';}
                },

                pointer: {				// 仪表盘指针。
                  show: true,				// 是否显示指针,默认 true。
                  length: "70%",			// 指针长度，可以是绝对数值，也可以是相对于半径的百分比,默认 80%。
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
                  offsetCenter: ["-10%", "65%"], // x, y，单位px
                  textStyle: {
                    // fontWeight: "bold",
                    color: "#ade3ff",
                    fontSize: 11,
                  },
                },
                //仪表盘详情，用于显示数据。
                detail: {
                  show: true,
                  offsetCenter: ["-10%", "-20%"],
                  color: "auto",
                  textStyle: {
                    fontSize:25,
                  },
                  formatter: "{value}%",	// 格式化函数或者字符串
                },
                data: [this.dataMap['0'][1]],
              },
              //右边
              {//内圈进度条
                name:"右 内圈进度条",
                type: "gauge",
                radius: '70%',
                center: ['80%', '47%'],
                startAngle: 130,
                endAngle: -20,
                clockwise: true,		// 仪表盘刻度是否是顺时针增长,默认 true。
                min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                max: 20,				// 最大的数据值,默认 100 。映射到 maxAngle。
                splitNumber: 4,		// 仪表盘刻度的分割段数,默认 10。

                axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
                  show: true,				// 是否显示仪表盘轴线(轮廓线),默认 true。
                  lineStyle: {			// 仪表盘轴线样式。
                    color: colorTemplate1, 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 12,					//轴线宽度,默认 30。
                    shadowBlur: 5,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  }
                },

                splitLine: {			// 分隔线样式。
                  show: true,				// 是否显示分隔线,默认 true。
                  length:12,				// 分隔线线长。支持相对半径的百分比,默认 30。
                  lineStyle: {			// 分隔线样式。
                    color: "#eee",				//线的颜色,默认 #eee。
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 1,					//线度,默认 2。
                    type: "solid",				//线的类型,默认 solid。 此外还有 dashed,dotted
                    shadowBlur: 5,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  }
                },

                axisTick: {				// 刻度(线)样式。
                  show: true,				// 是否显示刻度(线),默认 true。
                  splitNumber: 3,			// 分隔线之间分割的刻度数,默认 5。
                  length: 5,				// 刻度线长。支持相对半径的百分比,默认 8。
                  lineStyle: {			// 刻度线样式。
                    color: "#eee",				//线的颜色,默认 #eee。
                    opacity: 1,					//图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                    width: 0.8,					//线度,默认 1。
                    type: "solid",				//线的类型,默认 solid。 此外还有 dashed,dotted
                    shadowBlur: 5,				//(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                    shadowColor: "#fff",		//阴影颜色。支持的格式同color。
                  },
                },

                axisLabel: {			// 刻度标签。
                  show: true,				// 是否显示标签,默认 true。
                  distance: 2,			// 标签与刻度线的距离,默认 5。
                  color: "#fff",			// 文字的颜色,默认 #fff。
                  fontSize: 8,			// 文字的字体大小,默认 5。
                  formatter: "{value}",	// 刻度标签的内容格式器，支持字符串模板和回调函数两种形式。 示例:// 使用字符串模板，模板变量为刻度默认标签 {value},如:formatter: '{value} kg'; // 使用函数模板，函数参数分别为刻度数值,如formatter: function (value) {return value + 'km/h';}
                },

                pointer: {				// 仪表盘指针。
                  show: true,				// 是否显示指针,默认 true。
                  length: "70%",			// 指针长度，可以是绝对数值，也可以是相对于半径的百分比,默认 80%。
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
                  offsetCenter: ["10%", "65%"], // x, y，单位px
                  textStyle: {
                    // fontWeight: "bold",
                    color: "#ade3ff",
                    fontSize: 11,
                  },
                },
                //仪表盘详情，用于显示数据。
                detail: {
                  show: true,
                  offsetCenter: ["10%", "-20%"],
                  color: "auto",
                  textStyle: {
                    fontSize:25,
                  },
                  formatter: "{value}%",	// 格式化函数或者字符串
                },
                data: [this.dataMap['0'][2]],
              },
            ],
          },
          options: [
            {//第一个
              series: [
                {//中间进度条 不超过8%
                  data: [this.dataMap['0'][0]],
                },
                {//左进度条 不超过4%
                  data: [this.dataMap['0'][1]],
                },
                {//右进度条 不超过5%
                  data: [this.dataMap['0'][2]],
                }
              ]
            },{//第二个
              series: [
                {//不超过11%
                  min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                  max: 44,				// 最大的数据值,默认 100 。映射到 maxAngle。
                  data: [this.dataMap['1'][0]],
                },
                {//不超过0.6%
                  min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                  max: 2.4,
                  data: [this.dataMap['1'][1]],
                },
                {//不超过5%
                  min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                  max: 20,
                  data: [this.dataMap['1'][2]],
                }
              ]
            },{//第三个
              series: [
                {//4%
                    min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                    max: 20,
                    data: [this.dataMap['2'][0]],
                },
                {//25%
                  min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                  max: 100,
                  data: [this.dataMap['2'][1]],
                },
                {//60%
                  min: 0,					// 最小的数据值,默认 0 。映射到 minAngle。
                  max: 100,
                  splitNumber: 4,		// 仪表盘刻度的分割段数,默认 10。
                  axisLine: {				// 仪表盘轴线(轮廓线)相关配置。
                    lineStyle: {			// 仪表盘轴线样式。
                      color: colorTemplate2, 	//仪表盘的轴线可以被分成不同颜色的多段。每段的  结束位置(范围是[0,1]) 和  颜色  可以通过一个数组来表示。默认取值：[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]
                    }
                  },
                  data: [this.dataMap['2'][2]],
                }
              ]
            }]
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
