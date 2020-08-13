<template>
  <div style="width: 100%;height: 100px;top: 0px">
    <el-cascader v-model="selectChart"
                 :options="options"
                 @change="handleChange">
    </el-cascader>
    <el-button @click="toConfig">更改</el-button>
  </div>
</template>

<script>
export default {
  name: 'config',
  data(){
    return {
      selectChart:['line','gh_data'],
      options:[{
        value:'line',
        label:'折线图',
        children:[{
          value:'gh_data',
          label:'工商银行'
        }, {
          value:'zh_data',
          label:'中国银行'
        },{
          value:'jh_data',
          label:'建设银行'
        },{
          value:'nh_data',
          label:'农业银行'
        }
        ]
      }, {//仪表盘
        value:'gauge',
        label:'仪表盘',
        children:[{
          value:'gh_data',
          label:'工商银行'
        }, {
          value:'zh_data',
          label:'中国银行'
        },{
          value:'jh_data',
          label:'建设银行'
        },{
          value:'nh_data',
          label:'农业银行'
        }]
      }],
      bankData: {//折线图的y坐标
        gh_data:[0.1,0.2,0.3],
        zh_data:[0.13,0.5,0.6],
        jh_data:[0.5,0.34,0.58],
        nh_data:[0.59,0.21,0.14]
      },
      dashboard_data: {//仪表盘数据
        gh_data:{value: 50, name: 'RORWA'},
        zh_data:{value: 40, name: 'RORWA'},
        jh_data:{value: 30, name: 'RORWA'},
        nh_data:{value: 20, name: 'RORWA'},
      },
      configData:{
        chartType:'',
        bankChart:[],
        gaugeChart:{}
      }

    }
  },
  methods:{
    handleChange(){
      if (this.selectChart[0] == 'line'){
        this.configData.chartType = this.selectChart[0]
        this.configData.bankChart = this.bankData[this.selectChart[1]]
      }
      else if (this.selectChart[0] == 'gauge') {
        this.configData.chartType = this.selectChart[0]
        this.configData.gaugeChart = this.dashboard_data[this.selectChart[1]]
      }
    },
    toConfig(){
      this.$store.commit('changeChart',this.configData)
      // this.bus.$emit("change-config",this.configData)
    }
  }
}
</script>

<style scoped>

</style>
