<template>
  <div class="container">
    <p></p>
    <div id="main" style="width: 600px;height: 600px;"></div>
  </div>
</template>

<script>
  import echarts from 'echarts'
  export default {
    name: "hello",
    mounted() {
      this.$nextTick(function() {
        this.drawPie('main');
      })
    },
    methods:{
      drawPie() {
        var charts = echarts.init(document.getElementById('main'))
        var option = {
          title: {
            text: "学生成绩分析雷达图"
          },
          tooltip: {},
          legend: {//学生
            data: ["拉拉","小明"]//this.$store.state.StuData
          },
          radar: {
            indicator: //this.$store.state.indexData
            [
                {
                  name: "语文1",
                  max: 150
                  },
                {
                  name: "数学1",
                  max: 150
                },
              {
                name:"英语1",
                max:100
              }
            ,]
          },
          series: [{
            name: "预算 vs 开销（Budget vs spending）",
            type: "radar",
            data: //this.$store.state.valueData
              [{
              value: [88, 84, 66],
              name: "拉拉"
            },
                {
                  value: [50, 57, 100],
                  name: "小明"
                }
            ]
          }]
        }
        charts.setOption(option);
        //异步加载数据
        // var listStuURL=window.g.listStuURL
        // var listvalueURL=window.g.listvalueURL
        // var listindexURL=window.g.listindexURL
        // $.post(listStuURL).done(function (data) {
        //   // 填入数据
        //   charts.setOption({
        //     legend: {
        //       data: data
        //     }
        //   });
        // });
        // $.post(listindexURL).done(function (data) {
        //   // 填入数据
        //   charts.setOption({
        //     radar: {
        //       indicator: data
        //     }
        //   });
        // });
        // $.post(listvalueURL).done(function (data) {
        //   // 填入数据
        //   charts.setOption({
        //     series: [{
        //       name: "学生成绩分析",
        //       type: "radar",
        //       data: data
        //     }]
        //   });
        // });
      }
    }
  }
</script>

<style scoped>

</style>
