<template>
    <div class="radarChart-container">
        <div class="chart" id="chart_right1"></div>
    </div>
</template>

<script>
    import echarts from "echarts";

    export default {
        name: "radarChart1",
        data(){
            return{
                color: ['#e92b77', '#f9cf67', '#00c2ff','#62fff6'],
                //银行表
                legendData:['工行', '中行', '建行','农行'],
                //指标表
                indicatorData:[{//指标
                        text: '资本充足率',
                        max: 100
                    }, {
                        text: '核心一级资本充足率',
                        max: 100
                    }, {
                        text: '资产利润率',
                        max: 100
                    }, {
                        text: '不良贷款率',
                        max: 100
                    }, {
                        text: '净息差',
                        max: 100
                    }
                ],
                //指标值表
                seriesData_values:[[37, 100, 12, 50, 25],
                    [85, 65, 55, 100, 82],[50, 20, 45, 30, 100],[10, 50, 90, 80, 80]]
            }
        },
        computed: {

        },
        mounted() {
            this.getChart();
        },
        methods: {
            getChart(){
                var myChart = echarts.init(document.getElementById('chart_right1'))
                var option ={
                    color:this.color,//图例的颜色
                    legend: {
                        show: true,
                        icon: 'circle',//图例形状
                        bottom: 10,
                        center: 0,
                        itemWidth: 7, // 图例标记的图形宽度。[ default: 25 ]
                        itemHeight: 7, // 图例标记的图形高度。[ default: 14 ]
                        itemGap: 10, // 图例每项之间的间隔。[ default: 10 ]横向布局时为水平间隔，纵向布局时为纵向间隔。
                        textStyle: {
                            fontSize: 7,
                            color: '#ade3ff'
                        },
                        data: this.legendData,
                    },
                    radar: [{
                        indicator: this.indicatorData,
                        textStyle: {
                            color: 'red'
                        },
                        center: ['50%', '50%'],
                        radius: 50,
                        startAngle: 90,
                        splitNumber: 4,
                        orient: 'horizontal', // 图例列表的布局朝向,默认'horizontal'为横向,'vertical'为纵向.
                        name: {
                            formatter: '{value}',
                            textStyle: {
                                fontSize: 10, //外圈标签字体大小
                                color: '#b3ccf8' //外圈标签字体颜色
                            }
                        },
                        splitArea: { // 坐标轴在 grid 区域中的分隔区域，默认不显示。
                            show: true,
                            areaStyle: { // 分隔区域的样式设置。
                                color: ['#141c42', '#141c42'], // 分隔区域颜色。分隔区域会按数组中颜色的顺序依次循环设置颜色。默认是一个深浅的间隔色。
                            }
                        },
                        axisLine: { //指向外圈文本的分隔线样式
                            lineStyle: {
                                color: '#153269'
                            }
                        },
                        splitLine: {
                            lineStyle: {
                                color: '#113865', // 分隔线颜色
                                width: 1, // 分隔线线宽
                            }
                        }
                    }, ],
                    series: [{
                        name: '雷达图',
                        type: 'radar',
                        itemStyle: {
                            emphasis: {
                                lineStyle: {
                                    width: 2
                                }
                            }
                        },
                        data: [{
                            name: this.legendData[0],
                            value: this.seriesData_values[0],
                            areaStyle: {
                                normal: { // 单项区域填充样式
                                    color: {
                                        type: 'linear',
                                        x: 0, //右
                                        y: 0, //下
                                        x2: 1, //左
                                        y2: 1, //上
                                        colorStops: [{
                                            offset: 0,
                                            color: this.color[0]
                                        }, {
                                            offset: 0.5,
                                            color: 'rgba(0,0,0,0)'
                                        }, {
                                            offset: 1,
                                            color: this.color[0]
                                        }],
                                        globalCoord: false
                                    },
                                    opacity: 1 // 区域透明度
                                }
                            },
                            symbolSize: 1, // 单个数据标记的大小，可以设置成诸如 10 这样单一的数字，也可以用数组分开表示宽和高，例如 [20, 10] 表示标记宽为20，高为10。
                            itemStyle: {
                                normal: { //图形悬浮效果
                                    borderColor: this.color[0],
                                    borderWidth: 1
                                }
                            }
                        }, {
                            name: this.legendData[1],
                            value: this.seriesData_values[1],
                            symbolSize: 1,
                            itemStyle: {
                                normal: {
                                    borderColor: this.color[1],
                                    borderWidth: 1,
                                }
                            },
                            areaStyle: {
                                normal: { // 单项区域填充样式
                                    color: {
                                        type: 'linear',
                                        x: 1, //右
                                        y: 0, //下
                                        x2: 0, //左
                                        y2: 1, //上
                                        colorStops: [{
                                            offset: 0,
                                            color: this.color[1]
                                        }, {
                                            offset: 0.5,
                                            color: 'rgba(0,0,0,0)'
                                        }, {
                                            offset: 1,
                                            color: this.color[1]
                                        }],
                                        globalCoord: false
                                    },
                                    opacity: 1 // 区域透明度
                                }
                            },
                            lineStyle: {
                                normal: {
                                    opacity: 0.5,// 图形透明度
                                    color:this.color[1]
                                }
                            }
                        }, {
                            name: this.legendData[2],
                            value: this.seriesData_values[2],
                            symbolSize: 1,
                            itemStyle: {
                                normal: {
                                    borderColor: this.color[2],
                                    borderWidth: 1,
                                }
                            },
                            areaStyle: {
                                normal: { // 单项区域填充样式
                                    color: {
                                        type: 'linear',
                                        x: 0, //右
                                        y: 0, //下
                                        x2: 1, //左
                                        y2: 1, //上
                                        colorStops: [{
                                            offset: 0,
                                            color: this.color[2]
                                        }, {
                                            offset: 0.5,
                                            color: 'rgba(0,0,0,0)'
                                        }, {
                                            offset: 1,
                                            color: this.color[2]
                                        }],
                                        globalCoord: false
                                    },
                                    opacity: 1 // 区域透明度
                                }
                            },
                            lineStyle: {
                                normal: {
                                    opacity: 0.5// 图形透明度
                                }
                            }
                        }, {
                            name: this.legendData[3],
                            value: this.seriesData_values[3],
                            symbolSize: 1,
                            itemStyle: {
                                normal: {
                                    borderColor: this.color[3],
                                    borderWidth: 1,
                                }
                            },
                            areaStyle: {
                                normal: { // 单项区域填充样式
                                    color: {
                                        type: 'linear',
                                        x: 0, //右
                                        y: 0, //下
                                        x2: 1, //左
                                        y2: 1, //上
                                        colorStops: [{
                                            offset: 0,
                                            color: this.color[3]
                                        }, {
                                            offset: 0.5,
                                            color: 'rgba(0,0,0,0)'
                                        }, {
                                            offset: 1,
                                            color: this.color[3]
                                        }],
                                        globalCoord: false
                                    },
                                    opacity: 1 // 区域透明度
                                }
                            },
                            lineStyle: {
                                normal: {
                                    opacity: 0.4// 图形透明度
                                }
                            }
                        }]
                    }, ]
                }
                myChart.setOption(option);
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
    .radarChart-container {
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
