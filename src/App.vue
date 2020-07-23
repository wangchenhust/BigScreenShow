<template>
  <div>
      <el-row>
          <el-col :span="24">
              <i class="el-icon-platform-eleme">试试elemenui</i>
          </el-col>
      </el-row>
      <div id="myChart" style="height: 300px;width: 300px"></div>
      <div id="axiosChart" style="height: 300px;width: 300px"></div>
  </div>
</template>

<script>
  import {mapState} from 'vuex'
  export default {
    name: 'App',
    data () {
      return {
      }
    },
    mounted(){
      this.drawLine();
    },
    created(){

    },
    computed: {
        ...mapState({
            xData:state=>state.xData,
            chartData:state=>state.chartData,
            chartType:state=>state.chartType
        }),
    },
    methods: {
      // getData(){
      //     this.$axios.get('http://127.0.0.1:8848/get/')
      //         .then(function (response) {
      //             //alert(response.data)
      //             console.log(response)
      //             //this.$store.commit('changeData',response)
      //     })
      // },
      drawLine(){
          let axiosChart =this.$echarts.init(document.getElementById('axiosChart'))
          this.$axios.get('http://127.0.0.1:8848/get/')
              .then(function (response) {
                  //alert(response.data)
                  console.log(response)
                  let testData = response.data.xData
                  let testChartData = response.data.chartsData
                  testData.split(",").map(Number)
                  testChartData.split(",").map(Number)
                  console.log(testChartData)
                  console.log(testData)
                  axiosChart.setOption(
                  {
                      title: { text: '在Vue中使用echarts' ,
                          textStyle:{fontsize:20}
                      },
                      tooltip: {},
                      xAxis: {
                          type:'category',
                          //data:testData
                          data: [1,2,3,4,5]
                      },
                      yAxis: {
                          type:'value'
                      },
                      series: [{
                          name: '销量',
                          type: 'line',
                          //data:testChartData
                          data: [5,20,36,10,13]
                      }]
                  }
                  )
                  //this.$store.commit('changeData',response)
              })
          // 基于准备好的dom，初始化echarts实例
          let myChart = this.$echarts.init(document.getElementById('myChart'))
          // 绘制图表
          myChart.setOption({
              title: { text: '在Vue中使用echarts' ,
                  textStyle:{fontsize:20}
              },
              tooltip: {},
              xAxis: {
                  type:'category',
                  //data: res.xData
                  data: this.xData,
              },
              yAxis: {
                  type:'value'
              },
              series: [{
                  name: '销量',
                  type: this.chartType,
                  data: this.chartData
              }]
          });

      }
    }
  }
</script>

