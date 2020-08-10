<template>
    <div class="threeDashboards-container">
        <div class="chart" id="chart_xx"></div>
    </div>
</template>

<script>
    import echarts from "echarts";
    export default {
        name: "threeDashboards2",
        data(){
            return{
                option: null,
                dataMap:{
                    //2018年
                    2:[{ value: 100,  name: '资本充足率' },
                        { value: 60,  name: '核心资本充足率' },
                        { value: 90,  name: '核心一级资本充足率' },],
                    //2019年
                    1:[{ value: 30,  name: '资本充足率' },
                        { value: 40,  name: '核心资本充足率' },
                        { value: 80,  name: '核心一级资本充足率' },],
                    //2020年
                    0:[{ value: 10,  name: '资本充足率' },
                        { value: 20,  name: '核心资本充足率' },
                        { value: 70,  name: '核心一级资本充足率' },],
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
                let myChart = echarts.init(document.getElementById('chart_xx'))
                this.option ={
                    baseOption:{
                        timeline: {
                            axisType: 'category',
                            autoPlay: true,
                            playInterval: 4000,//暂停时间3s
                            data: ['2020', '2019', '2018'],
                            left: 80,
                            right: 80,
                            bottom: 2,
                            lineStyle: {
                                color: '#179bf1',
                                bottom: 10
                            },
                            label: {
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
                                radius: '85%',
                                center: ["50%", "55%"],
                                splitNumber: 0, //刻度数量
                                startAngle: 180,
                                endAngle: 0,
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['0'][0].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                //分隔线样式。
                                splitLine: {
                                    show: false,
                                },
                                axisLabel: {
                                    show: false,
                                },
                                axisTick: {
                                    show: false,
                                },
                                pointer: {
                                    show: true,
                                    length: "80%",
                                    width: "2%",
                                },
                                title: {
                                    show: true,
                                    offsetCenter: [0, "20%"], // x, y，单位px
                                    textStyle: {
                                        // fontWeight: "bold",
                                        color: "#ade3ff",
                                        fontSize: 11,
                                    },
                                },
                                //仪表盘详情，用于显示数据。
                                detail: {
                                    show: true,
                                    offsetCenter: [0, "-30%"],
                                    color: "#ffffff",
                                    textStyle: {
                                        fontSize:30,
                                        color: "#00ffff",
                                    },
                                    formatter: function(value) {
                                        return value + '{a|}';
                                    },
                                    rich: {
                                        a: {
                                            fontSize: 30,
                                            color: "#00ffff"
                                        }
                                    }
                                },
                                data: [this.dataMap['0'][0]],
                            },
                            //左边
                            {//外圈刻度
                                name: "左 外刻度",
                                type: "gauge",
                                center: ['20%', '47%'], // 默认全局居中
                                radius: '55%',
                                min: 0, //最小刻度
                                max: 100, //最大刻度
                                splitNumber: 10, //刻度数量
                                startAngle: 200,
                                endAngle: 50,
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 0.7,
                                        color: [
                                            [1, "rgba(0,0,0,0)"]
                                        ],
                                    },
                                }, //仪表盘轴线
                                axisLabel: {
                                    show: false,
                                    color: "#4b695e",
                                    fontSize: 7,
                                    distance: -15,
                                    formatter: function(v) {
                                        return v;
                                    },
                                }, //刻度标签。
                                axisTick: {
                                    show: true,
                                    splitNumber: 2,
                                    lineStyle: {
                                        color: "#263b35",
                                        width: 0.7,
                                        // length:10
                                    },
                                    length: -5,
                                }, //刻度样式
                                splitLine: {
                                    show: true,
                                    length: -5,
                                    lineStyle: {
                                        color: "#00ffff",
                                        width: 1
                                    },
                                }, //分隔线样式
                            },
                            {//内圈进度条
                                name:"左 内圈进度条",
                                type: "gauge",
                                radius: '50%',
                                center: ['20%', '47%'], // 默认全局居中
                                splitNumber: 0, //刻度数量
                                startAngle: 200,
                                endAngle: 50,
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 8,
                                        color: [
                                            [
                                                this.dataMap['0'][1].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                //分隔线样式。
                                splitLine: {
                                    show: false,
                                },
                                axisLabel: {
                                    show: false,
                                },
                                axisTick: {
                                    show: false,
                                },
                                pointer: {
                                    show: true,
                                    length: "80%",
                                    width: "2%",
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
                                    color: "#ffffff",
                                    textStyle: {
                                        fontSize:15,
                                        color: "#00ffff"
                                    },
                                    formatter: function(value) {
                                        return value + '{a|}';
                                    },
                                    rich: {
                                        a: {
                                            fontSize: 25,
                                            color: "#00ffff"
                                        }
                                    }
                                },
                                data: [this.dataMap['0'][1]],
                            },
                            //右边
                            {//外圈刻度
                                name: "右 外刻度",
                                type: "gauge",
                                center: ['80%', '47%'],
                                radius: '55%',
                                min: 0, //最小刻度
                                max: 100, //最大刻度
                                splitNumber: 10, //刻度数量
//             startAngle: 255,
// 			endAngle: 480,
                                startAngle: 130,
                                endAngle: -20,
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 0.7,
                                        color: [
                                            [1, "rgba(0,0,0,0)"]
                                        ],
                                    },
                                }, //仪表盘轴线
                                // axisLabel: {
                                //     show: true,
                                //     color: "#4b695e",
                                //     fontSize: 7,
                                //     distance: -15,
                                //     formatter: function(v) {
                                //         return v;
                                //     },
                                // }, //刻度标签。
                                axisTick: {
                                    show: true,
                                    splitNumber: 2,
                                    lineStyle: {
                                        color: "#263b35",
                                        width: 0.7,
                                        // length:10
                                    },
                                    length: -5,
                                }, //刻度样式
                                splitLine: {
                                    show: true,
                                    length: -5,
                                    lineStyle: {
                                        color: "#00ffff",
                                        width: 1
                                    },
                                }, //分隔线样式
                            },
                            {//内圈进度条
                                name:"右 内圈进度条",
                                type: "gauge",
                                radius: '50%',
                                center: ['80%', '47%'],
                                splitNumber: 0, //刻度数量
                                startAngle: 130,
                                endAngle: -20,
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 8,
                                        color: [
                                            [
                                                this.dataMap['0'][2].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.2,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                //分隔线样式。
                                splitLine: {
                                    show: false,
                                },
                                axisLabel: {
                                    show: false,
                                },
                                axisTick: {
                                    show: false,
                                },
                                pointer: {
                                    show: true,
                                    length: "80%",
                                    width: "2%",
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
                                    color: "#ffffff",
                                    textStyle: {
                                        fontSize:15,
                                        color: "#00ffff"
                                    },
                                    formatter: function(value) {
                                        return value + '{a|}';
                                    },
                                    rich: {
                                        a: {
                                            fontSize: 25,
                                            color: "#00ffff"
                                        }
                                    }
                                },
                                data: [this.dataMap['0'][2]],
                            },
                        ],
                    },
                    options: [{
                        series: [
                            {
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['0'][0].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                data: [this.dataMap['0'][0]],
                            },{},{
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['0'][1].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                data: [this.dataMap['0'][1]],
                            },{},{
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['0'][2].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                data: [this.dataMap['0'][2]],
                            }
                        ]
                    },{
                        series: [
                            {
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['1'][0].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                data: [this.dataMap['1'][0]],
                            },{},{
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['1'][1].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                data: [this.dataMap['1'][1]],
                            },{},{
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['1'][2].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                data: [this.dataMap['1'][2]],
                            }
                        ]
                    },{
                        series: [
                            {
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['2'][0].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                data: [this.dataMap['2'][0]],
                            },{},{
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['2'][1].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                data: [this.dataMap['2'][1]],
                            },{},{
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        width: 10,
                                        color: [
                                            [
                                                this.dataMap['2'][2].value/100, new echarts.graphic.LinearGradient(
                                                0, 1, 1, 0, [
                                                    {
                                                        offset: 0,
                                                        color: 'rgba(255,0,0,1)',
                                                    },
                                                    {
                                                        offset: 0.08,
                                                        color: '#ffa800',
                                                    },
                                                    {
                                                        offset: 0.1,
                                                        color: 'rgba(239,232,17,1)',
                                                    },
                                                    {
                                                        offset: 1,
                                                        color: '#00ffff',
                                                    }
                                                ]
                                            )
                                            ],
                                            [
                                                1, 'rgba(255,255,255,.0)'
                                            ]
                                        ],
                                    },
                                },
                                data: [this.dataMap['2'][2]],
                            }
                        ]
                    }]
                }
                myChart.setOption(this.option,true);
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
