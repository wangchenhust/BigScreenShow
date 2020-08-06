<template>
    <div class="dashboard-container">
        <div class="chart" id="chart_left3"></div>
    </div>
</template>

<script>
    import echarts from "echarts";

    export default {
        name: "dashboard",
        data(){
            return{
                data:80,
                dataChar:'80',
                colorSet : {
                    color: '#468EFD'
                }
            }
        },
        computed: {

        },
        mounted() {
            this.getChart();
        },
        methods: {
            getChart(){
                var myChart = echarts.init(document.getElementById('chart_left3'))
                var option ={
                    title: {
                        show: true,
                        text: '资本充足率',
                        x: '49%',
                        y: '76%',
                        z: 8,//优先级
                        textAlign: 'center',
                        textStyle: {
                            color: 'rgba(69, 161, 255,0.9)',
                            fontStyle: 'normal',
                            fontFamily: '微软雅黑',
                            fontSize: 10,
                        },
                    },
                    series: [
                        {
                            name: "内部进度条",
                            type: "gauge",
                            // center: ['20%', '50%'],
                            radius: '68%',//大小
                            z: 0,
                            splitNumber: 10,//大刻度单位数量
                            axisLine: {
                                lineStyle: {
                                    color: [
                                        [this.data / 100, this.colorSet.color],
                                        [1, "#0f274f"]
                                    ],
                                    width: 2//进度条宽度
                                }
                            },
                            axisLabel: {
                                show: false,
                            },
                            axisTick: {
                                show: false,

                            },
                            splitLine: {
                                show: false,
                            },
                            pointer: {
                                show: false,
                            },
                        },
                        {
                            name: '外部刻度',
                            type: 'gauge',
                            // center: ['20%', '50%'],
                            radius: '80%',
                            min: 0, //最小刻度
                            max: 100, //最大刻度
                            splitNumber: 10, //刻度数量
                            startAngle: 225,
                            endAngle: -45,
                            axisLine: {
                                show: true,
                                lineStyle: {
                                    width: 1,
                                    color: [
                                        [1, 'rgba(0,0,0,0)']
                                    ]
                                }
                            },
                            //仪表盘轴线
                            axisLabel: {
                                show: true,
                                color: '#4d5bd1',
                                distance: 9,//刻线值与刻线距离
                                fontSize:6,//刻线值大小
                                formatter: function (v) {
                                    switch (v + '') {
                                        case '0':
                                            return '0';
                                        case '10':
                                            return '10';
                                        case '20':
                                            return '20';
                                        case '30':
                                            return '30';
                                        case '40':
                                            return '40';
                                        case '50':
                                            return '50';
                                        case '60':
                                            return '60';
                                        case '70':
                                            return '70';
                                        case '80':
                                            return '80';
                                        case '90':
                                            return '90';
                                        case '100':
                                            return '100';
                                    }
                                }
                            }, //刻度标签。
                            axisTick: {
                                show: true,
                                splitNumber: 6,//小刻线单位数量
                                lineStyle: {
                                    color: this.colorSet.color, //用颜色渐变函数不起作用
                                    width: 0.7,
                                },
                                length: -4//小刻线长度
                            }, //刻度样式
                            splitLine: {
                                show: true,
                                length: -8,//大刻线长度
                                lineStyle: {
                                    color: this.colorSet.color, //用颜色渐变函数不起作用
                                    width: 1,
                                }
                            }, //分隔线样式
                            detail: {
                                show: false
                            },
                            pointer: {
                                show: false
                            }
                        },
                        /*内部圈*/
                        {
                            type: 'pie',
                            radius: ['0', '40%'],
                            center: ['50%', '50%'],
                            z: 8,
                            hoverAnimation: false,
                            data: [{
                                name: '资本充足率',
                                value: this.dataChar,
                                itemStyle: {
                                    normal: {
                                        color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                            offset: 0,
                                            color: '#4FADFD'
                                        }, {
                                            offset: 1,
                                            color: '#fff'//#28E8FA
                                        }])
                                    }
                                },
                                label: {
                                    normal: {
                                        rich: {
                                            a: {
                                                color: '#468EFD',
                                                align: 'center',
                                                fontSize: 18,
                                                fontWeight: "bold"
                                            },
                                        },
                                        formatter: function (params) {
                                            return "{a|" + params.value + "}";
                                        },
                                        position: 'center',
                                        show: true
                                    }
                                },
                                labelLine: {
                                    show: false
                                }
                            }],
                        },
                        /*外一层*/
                        {
                            type: "pie",
                            radius: "45%",
                            startAngle: 220,
                            endAngle: -40,
                            hoverAnimation: false,
                            center: ["50%", "50%"],
                            avoidLabelOverlap: false,
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            },
                            data: [{
                                value: 1
                            }],
                            itemStyle: {
                                normal: {
                                    color: {
                                        type: "linear",
                                        x: 0,
                                        y: 0,
                                        x2: 0,
                                        y2: 1,
                                        colorStops: [{
                                            offset: 0,
                                            color: "rgba(69, 161, 255,0.8)"
                                        }, {
                                            offset: 1,
                                            color: "rgba(69, 161, 255,0.5)"
                                        }]
                                    }
                                }
                            }
                        },
                        //外二层圈
                        {
                            type: "pie",
                            radius: "50%",
                            center: ["50%", "50%"],
                            avoidLabelOverlap: false,
                            z: 1,
                            hoverAnimation: false,
                            label: {
                                show: false
                            },
                            labelLine: {
                                show: false
                            },
                            data: [{
                                "value": 1
                            }],
                            itemStyle: {
                                normal: {
                                    color: {
                                        type: "linear",
                                        x: 0,
                                        y: 0,
                                        x2: 0,
                                        y2: 1,
                                        colorStops: [{
                                            offset: 0,
                                            color: "rgba(69, 161, 255,0.5)"
                                        }, {
                                            offset: 1,
                                            color: "rgba(69, 161, 255,0.3)"
                                        }]
                                    }
                                }
                            }
                        },
                        //最外层圈
                        {
                            type: "pie",
                            radius: "55%",
                            center: ["50%", "50%"],
                            avoidLabelOverlap: false,
                            z: 1,
                            hoverAnimation: false,
                            label: {
                                show: false,
                            },
                            labelLine: {
                                show: false
                            },
                            data: [{
                                value: 1
                            }],
                            itemStyle: {
                                normal: {
                                    color: {
                                        type: "linear",
                                        x: 0,
                                        y: 0,
                                        x2: 0,
                                        y2: 1,
                                        colorStops: [{
                                            offset: 0,
                                            color: "rgba(69, 161, 255,0.3)"
                                        },
                                            {
                                                offset: 1,
                                                color: "rgba(69, 161, 255,0)"
                                            }
                                        ]
                                    }
                                }
                            }
                        }
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
    .dashboard-container {
        position: absolute;
        overflow: hidden;
        display: block;
        cursor: default;
        z-index: 0;
        left: 0px;
        top: 13px;
        width: 100%;
        height: 100%;
    }
</style>
