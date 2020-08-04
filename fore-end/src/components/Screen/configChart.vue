<template>
    <div class="configChart-container">
        <el-cascader v-model="newSelectChart"
                     :options="options"
                     @change="handleChange">
        </el-cascader>
        <div class="chart" id="newConfigChart"></div>
    </div>
</template>

<script>
    export default {
        name: "configChart",
        data () {
            return {
                options:[{
                    value:'line',
                    label:'折线图',
                    children:[{
                        value:'default',
                        label:'默认'
                    }, {
                        value:'pingan',
                        label:'平安银行'
                    },{
                        value:'ningbo',
                        label:'宁波银行'
                    },{
                        value:'jiangyin',
                        label:'江阴银行'
                    }
                    ]
                },{
                    value:'gauge',
                    label:'仪表盘',
                    children:[{
                        value:'default',
                        label:'默认'
                    }]
                }],
                newSelectChart:['line','default'],
                selectChart:'line',
                chartData:'default',
                bankData: {
                    default:[10,20,30,40],
                    pingan:[13.22,11.5,11.2,11.53],
                    ningbo:[15.57,14.86,13.58,12.25],
                    jiangyin:[15.29,15.21,14.14,13.94]
                }
            }
        },
        mounted(){
            this.option = {
                title: {
                    text: '折线图' ,
                    textStyle:{fontsize:20}
                },
                tooltip: {},
                xAxis: {
                    type:'category',
                    data: [2019,2018,2017,2016]
                },
                yAxis: {
                    type:'value'
                },
                series: [{
                    name: '数值',
                    type: 'line',
                    data: [10,20,30,40]
                }]
            }
            this.drawCharts(this.option)
        },
        methods: {
            drawCharts(option){
                // let configCharts = this.$echarts.init(document.getElementById('configChart'))
                // configCharts.clear()
                // configCharts.setOption(option)
                let newConfigCharts = this.$echarts.init(document.getElementById('newConfigChart'))
                newConfigCharts.clear()
                newConfigCharts.setOption(option)
            },
            handleChange(newSelectChart){
                let newConfigCharts = this.$echarts.init(document.getElementById('newConfigChart'))
                newConfigCharts.clear()
                if (newSelectChart[0] == 'line'){
                    let newOption = this.option
                    newOption.series[0].data = this.bankData[newSelectChart[1]]
                    newConfigCharts.setOption(newOption)
                }
                else if (newSelectChart[0] == 'gauge'){
                    newConfigCharts.setOption({
                        tooltip: {
                            formatter: '{a} <br/>{b} : {c}%'
                        },
                        toolbox: {
                            feature: {
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        series: [
                            {
                                name: '业务指标',
                                type: 'gauge',
                                detail: {formatter: '{value}%'},
                                data: [{value: 50, name: '完成率'}]
                            }
                        ]
                    })
                }
            }


        }
    }
</script>

<style lang="scss" scoped>
    .chart {
        width: 100%;
        height: 100%;
        /*border: 1px solid #12fe81;*/
    }
    .configChart-container {
        position: absolute;
        overflow: hidden;
        display: block;
        cursor: default;
        z-index: 0;
        left: 0px;
        top: 0px;
        width: 100%;
        height: 100%;
    }
</style>
