<template>
    <div class="barChart-container">
        <div class="chart" id="chart_left1"></div>
    </div>
</template>

<script>
    import echarts from "echarts";
    export default {
        name: "barChart",
        data(){
            return{
                datay:['现金资产','贷款资产','证券资产','固定资产','汇差资产'],
                datax:[50000000, 22000000, 10000000, 5000000, 10000000],
                datax_max:[50000000,50000000,50000000,50000000,50000000]
            }
        },
        computed: {

        },
        mounted() {
            this.getChart();
        },
        methods: {
            getChart(){
                var myChart = echarts.init(document.getElementById('chart_left1'))
                var option ={
                    grid: {//整个图表
                        left: '3%',
                        right: '3%',
                        bottom: '1%',
                        top: '15%',
                        containLabel: true
                    },
                    tooltip: {//详情框
                        trigger: 'axis',
                        axisPointer: {
                            type: 'none'
                        },
                        formatter: function(params) {
                            return params[0].name + '<br/>' +
                                "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:rgba(36,207,233,0.9)'></span>" +
                                params[0].seriesName + ' : ' + Number((params[0].value.toFixed(4) / 10000).toFixed(2)).toLocaleString() + ' 万元<br/>'
                        }
                    },
                    xAxis: {
                        show: false,
                        type: 'value'
                    },
                    yAxis: [{//分类说明
                        type: 'category',
                        inverse: true,
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#b3ccf8'
                            },
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
                    }, {//数值显示
                        type: 'category',
                        inverse: true,
                        axisTick: 'none',
                        axisLine: 'none',
                        show: true,
                        axisLabel: {
                            textStyle: {
                                color: '#b3ccf8',
                                fontSize: '12'
                            },
                            formatter: function(value) {
                                if (value >= 10000) {
                                    return (value / 10000).toLocaleString() + '万';
                                } else {
                                    return value.toLocaleString();
                                }
                            },
                        },
                        data: this.datax
                    }],
                    series: [{//亮色柱体
                        name: '金额',
                        type: 'bar',
                        zlevel: 1,
                        barWidth: '8px',//宽度
                        data: this.datax,
                        itemStyle: {
                            normal: {
                                barBorderRadius: 20,//圆度
                                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                    offset: 0,
                                    color: 'rgb(57,89,255,1)'
                                }, {
                                    offset: 1,
                                    color: 'rgb(46,200,207,1)'
                                }]),
                            },
                        }
                    },
                        {//暗色柱体
                            name: '背景',
                            type: 'bar',
                            barWidth:'10px',
                            barGap: '-100%',
                            data: this.datax_max,
                            itemStyle: {
                                normal: {
                                    color: '#00092d',
                                    barBorderRadius: 30,
                                }
                            },
                        },
                    ]
                }
                myChart.setOption(option);
            }
        }
    }
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
