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
      selectChart:[],
      options:[{
        value:'line',
        label:'折线图',
        children:[{
          value:'zbchongzu',
          label:'资本充足率'
        }, {
          value:'corezbchongzu',
          label:'核心资本充足率'
        },{
          value:'corefirstzbchongzu',
          label:'核心一级资本充足率'
        },{
          value:'zichanprofit',
          label:'资产利润率'
        },{
          value:'zibenprofit',
          label:'资本利润率'
        },{
          value:'baddaikuan',
          label:'不良贷款率'
        },{
          value:'badzichan',
          label:'不良资产率'
        },{
          value:'liudongbili',
          label:'流动性比例'
        },{
          value:'corefuzhai',
          label:'核心负债依存度'
        },{
          value:'rorwa',
          label:'风险加权资产收益率'
        }
        ]
        },
        {//仪表盘
        value:'gauge',
        label:'仪表盘',
        children:[{
          value:'zbchongzu',
          label:'资本充足率',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        }, {
          value:'corezbchongzu',
          label:'核心资本充足率',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        },{
          value:'corefirstzbchongzu',
          label:'核心一级资本充足率',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        },{
          value:'zichanprofit',
          label:'资产利润率',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        },{
          value:'zibenprofit',
          label:'资本利润率',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        },
          {
          value:'baddaikuan',
          label:'不良贷款率',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        },{
          value:'badzichan',
          label:'不良资产率',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        },{
          value:'liudongbili',
          label:'流动性比例',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        },{
          value:'corefuzhai',
          label:'核心负债依存度',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        },{
          value:'rorwa',
          label:'风险加权资产收益率',
          children:[{
            value:'season1',
            label:'第一季度'
          },{
            value:'season2',
            label:'第二季度'
          }]
        }
        ]
      }],
      bankData:{
        zbchongzu:{
          season1:0.1,
          season2:0.2
        },
        corezbchongzu:{
          season1:0.3,
          season2:0.25
        },
        corefirstzbchongzu:{
          season1:0.4,
          season2:0.3
        },
        zichanprofit:{
          season1:0.22,
          season2:0.44
        },
        zibenprofit:{
          season1:0.33,
          season2:0.55
        },
        baddaikuan:{
          season1:0.5,
          season2:0.15
        },
        badzichan:{
          season1:0.44,
          season2:0.33
        },
        liudongbili:{
          season1:0.35,
          season2:0.45
        },
        corefuzhai:{
          season1:0.1,
          season2:0.2
        },
        rorwa:{
          season1:0.5,
          season2:0.8
        }
      },
      configData:{
        chartType:'',
        bankChart:[],
        gaugeChart:50,
        chartName:''
      }

    }
  },
  created() {
    this.getData();
    //设置sse监听器
    this.$sse.getSource().addEventListener('peizhivalues', this.peizhiListener)
  },
  methods:{
    peizhiListener(e){
      var data1=JSON.parse(e.data);
      // console.log(data1)
      console.log("监听器：peizhi store更改！！")
      this.getData(data1)
      this.handleChange();
      this.toConfig();
    },
    async getData(data){
      let data1=null;
      if(data==undefined) {
        data1 = await this.$H.get('/GetData/ConfigData');
      }
      else{
        data1=data;
      }
      for(let i=0;i<data1.length;i++){
        switch (data1[i].name) {
          case "资本充足率":
            this.setData("zbchongzu",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          case "核心资本充足率":
            this.setData("corezbchongzu",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          case "核心一级资本充足率":
            this.setData("corefirstzbchongzu",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          case "资产利润率":
            this.setData("zichanprofit",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          case "资本利润率":
            this.setData("zibenprofit",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          case "不良贷款率":
            this.setData("baddaikuan",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          case "不良资产率":
            this.setData("badzichan",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          case "流动性比例":
            this.setData("liudongbili",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          case "核心负债依存度":
            this.setData("corefuzhai",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          case "RORWA":
            this.setData("rorwa",data1[i].time,Math.floor(data1[i].value/100 * 100) / 100);
            break;
          //........
        }
      }
    },
    setData(name,season,value){
      if(season=="第一季度"){
        this.bankData[name]["season1"]=value;
      }
      else{
        this.bankData[name]["season2"]=value;
      }
    },
    handleChange(){
      if (this.selectChart[0] == 'line'){
        this.configData.chartType = this.selectChart[0]
        this.configData.bankChart.length=0
        for (let i in this.bankData[this.selectChart[1]]){
          this.configData.bankChart.push(this.bankData[this.selectChart[1]][i])
        }
        this.configData.chartName = this.selectChart[1]
      }
      else if (this.selectChart[0] == 'gauge') {
        this.configData.chartType = this.selectChart[0]
        this.configData.gaugeChart = this.bankData[this.selectChart[1]][this.selectChart[2]]
        this.configData.chartName = this.selectChart[1]
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
