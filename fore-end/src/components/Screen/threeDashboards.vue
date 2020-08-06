<template>
    <div class="threeDashboards-container">
        <div class="chart" id="chart_xx"></div>
    </div>
</template>

<script>
    import echarts from "echarts";
    export default {
        name: "threeDashboards",
        data(){
            return{
                data : [
                    { value: 50,  name: '资本充足率' },
                    { value: 66,  name: '核心资本充足率' },
                    { value: 89,  name: '核心一级资本充足率' },
                ]
            }
        },
        computed: {

        },
        mounted() {
            this.getChart();
        },
        methods: {
            getChart(){
                var myChart = echarts.init(document.getElementById('chart_xx'))
                var option ={
                    series: [
                        //中间
                        // {//外圈刻度
                        //     name: "中 外刻度",
                        //     type: "gauge",
                        //     center: ["50%", "50%"],
                        //     radius: '75%',
                        //     min: 0, //最小刻度
                        //     max: 100, //最大刻度
                        //     splitNumber: 10, //刻度数量
                        //     startAngle: 220,
                        //     endAngle: -40,
                        //     axisLine: {
                        //         show: true,
                        //         lineStyle: {
                        //             width: 0.7,
                        //             color: [
                        //                 [1, "rgba(0,0,0,0)"]
                        //             ],
                        //         },
                        //     }, //仪表盘轴线
                        //     axisLabel: {
                        //         show: false,
                        //         color: "#4b695e",
                        //         fontSize: 7,
                        //         distance: -9,
                        //         formatter: function(v) {
                        //             return v;
                        //         },
                        //     }, //刻度标签。
                        //     axisTick: {
                        //         show: true,
                        //         splitNumber: 3,
                        //         lineStyle: {
                        //             color: "#263b35",
                        //             width: 0.7,
                        //             // length:10
                        //         },
                        //         length: -5,
                        //     }, //刻度样式
                        //     splitLine: {
                        //         show: true,
                        //         length: -5,
                        //         lineStyle: {
                        //             color: "#00ffff",
                        //             width: 1
                        //         },
                        //     }, //分隔线样式
                        // },
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
                                            1, new echarts.graphic.LinearGradient(
                                            0, 0, 1, 1, [{
                                                offset: 0,
                                                color: '#6afdb6',
                                            },
                                                {
                                                    offset: 1,
                                                    color: '#006ced',
                                                }
                                            ]
                                        )
                                        ],
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
                                offsetCenter: [0, "30%"], // x, y，单位px
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
                                    color: "#00ffff"
                                },
                                formatter: function(value) {
                                    return value + '{a|}';
                                },
                                rich: {
                                    a: {
                                        fontSize: 35,
                                        color: "#00ffff"
                                    }
                                }
                            },
                            data: [this.data[0]],
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
                                            1, new echarts.graphic.LinearGradient(
                                            0, 1, 1, 0, [{
                                                offset: 0,
                                                color: '#6afdb6',
                                            },
                                                {
                                                    offset: 1,
                                                    color: '#2287ff',
                                                }
                                            ]
                                        )
                                        ],
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
                                offsetCenter: ["-10%", "80%"], // x, y，单位px
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
                            data: [this.data[1]],
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
                                            1, new echarts.graphic.LinearGradient(
                                            0, 0, 1, 1, [{
                                                offset: 0,
                                                color: '#6afdb6',
                                            },
                                                {
                                                    offset: 1,
                                                    color: '#006ced',
                                                }
                                            ]
                                        )
                                        ],
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
                                offsetCenter: ["10%", "80%"], // x, y，单位px
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
                            data: [this.data[2]],
                        },
                    ],
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
