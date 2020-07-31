<template>
  <div class="wrap-container sn-container">
    <div class="sn-content">
      <div class="sn-title">仪表盘</div>
      <div class="sn-body">
        <div class="wrap-container">
          <div class="chartsdom" id="chart_gauge"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name: "gauge",
  mounted() {
    this.getEchart();
  },
  methods: {
    getEchart() {
      var myChart = echarts.init(document.getElementById('chart_gauge'));
      var option = {
        tooltip: {
          formatter: "{a} <br/>{b} : {c}%"
        },
        series: [
          {
            startAngle: 340, //开始角度 左侧角度
            endAngle: 0, //结束角度 右侧
            name: '健康值',
            type: 'gauge',
            detail: {
              offsetCenter: [0,"30%"],    //设置仪表盘下方显示内容位置
              formatter:'{value}%',
              textStyle:{color:'white',fontWeight: 'bolder',fontSize:35},
            },
            title : {               //设置仪表盘中间显示文字样式
              textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                fontWeight: 'bolder',
                fontSize: 20,
                fontStyle: 'italic',
                color:"white"
              }
            },
            data: [{
              value: 30,
              name: 'xx指数',
            }],
            center: ["47%", "45%"], // 默认全局居中
            splitLine : {           //分割线样式（及10、20等长线样式）
              length : 15,
              lineStyle: {            // 分隔线样式。
                color: "#eee",              //线的颜色,默认 #eee。
                opacity: 1,                 //图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
                width: 2,                   //线度,默认 2。
                type: "solid",              //线的类型,默认 solid。 此外还有 dashed,dotted
                shadowBlur: 10,             //(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                shadowColor: "#fff",        //阴影颜色。支持的格式同color。
              }
            },
            pointer : { //指针样式
              length: '80%'
            },
            axisLine:{
              show : true,// 是否显示仪表盘轴线(轮廓线),默认 true。
              lineStyle : { // 属性lineStyle控制线条样式
                shadowBlur: 10,             //(发光效果)图形阴影的模糊大小。该属性配合 shadowColor,shadowOffsetX, shadowOffsetY 一起设置图形的阴影效果。
                shadowColor: "#fff",        //阴影颜色。支持的格式同color。
                color : [ //表盘颜色
                  [ 0.2, "#20AE51" ],//0-50%处的颜色"#20AE51"
                  [ 0.5, "#1e90ff" ],//0-50%处的颜色
                  [ 0.7, "#FFED44" ],//51%-70%处的颜色
                  [ 0.9, "#FF9618" ],//70%-90%处的颜色
                  [ 1,"#DA462C" ]//90%-100%处的颜色
                ],
                width : 15//表盘宽度
              }
            },
            axisLabel : { //文字样式（及“10”、“20”等文字样式）
              color : "white",
              distance : 5 //文字离表盘的距离
            },
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
    left: 512px;
    top: 2838px;
    width: 432px;
    height: 400px;
  }
  .chartsdom {
    width: 80%;
    height: 80%;
    /*border: 2px solid  #00ffc6;*/
    padding: 47px;
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
    margin: 650px 50px 50px 1380px;
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

