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
    import echarts from "echarts";

    export default {
        name: "configChart",
        data () {
            return {
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
                },
                    {//仪表盘
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
                newSelectChart:['line','default'],
                selectChart:'line',
                chartData:'default',
                bankData: {//折线图的y坐标

                    gh_data:[0.1,0.2,0.3],
                    zh_data:[0.13,0.5,0.6],
                    jh_data:[0.5,0.34,0.58],
                    nh_data:[0.59,0.21,0.14]
                },
                xAxis_data:['资本充足率','核心一级资本充足率','资产利润率'],//折线图的x坐标
                dashboard_data: {//仪表盘数据
                    gh_data:{value: 50, name: 'RORWA'},
                    zh_data:{value: 40, name: 'RORWA'},
                    jh_data:{value: 30, name: 'RORWA'},
                    nh_data:{value: 20, name: 'RORWA'},
                },
                colorSet : {//仪表盘所用颜色
                    color: '#468EFD'
                }
            }
        },
        mounted(){
            this.option = {//折线图
                calculable: true,
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data:this.xAxis_data,
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: "#ade3ff", //X轴文字颜色
                            fontSize: 10
                        }
                    },
                    axisTick:{//坐标轴刻度相关设置。
                        show: false,
                    },
                    axisLine:{//坐标轴轴线相关设置
                        lineStyle:{
                            color:'#E5E9ED',
                            opacity:0.5//x轴
                        }
                    },
                    splitLine: { //坐标轴在 grid 区域中的分隔线。
                        show: true,
                        lineStyle: {
                            color: '#E5E9ED',
                            opacity:0.1
                        }
                    }

                }],
                yAxis: [{
                    type: 'value',
                    splitNumber: 3,
                    axisLabel: {
                        // formatter: '{value} ',
                        color: '#ade3ff',
                        textStyle: {
                            fontSize: 10
                        },
                        formatter:function(params) {
                            var newParamsName = "";
                            newParamsName = params*100+" %";
                            return newParamsName
                        }
                    },
                    axisLine:{
                        lineStyle:{
                            color:'#E5E9ED',
                            opacity:0.5//y轴
                        }
                    },
                    axisTick:{
                        show: false
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: '#E5E9ED',
                            opacity:0.1
                        }
                    }
                }],
                series: [{
                    name: '指标',
                    type: 'line',
                    data: this.bankData.gh_data,
                    lineStyle: {
                        normal: {
                            width: 2,
                            color: {
                                type: 'linear',

                                colorStops: [{
                                    offset: 0,
                                    color: '#57BD9F' // 0% 处的颜色
                                }, {
                                    offset: 1,
                                    color: '#19BC8C' // 100% 处的颜色
                                }],
                                globalCoord: false // 缺省为 false
                            },
                            shadowColor: 'rgba(72,216,191, 0.3)',
                            shadowBlur: 6,
                            shadowOffsetY: 10
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#57BD9F',
                            borderWidth: 8,
                            borderColor: "#57BD9F"
                        }
                    },
                    markPoint: {
                        data: [{
                            type: 'max',
                            name: '最大值'
                        },
                            {
                                type: 'min',
                                name: '最小值'
                            }
                        ]
                    },

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
                    newConfigCharts.setOption({//仪表盘
                        title: {
                            show: true,
                            text: 'RORWA',
                            x: '49%',
                            y: '55%',
                            z: 8,//优先级
                            textAlign: 'center',
                            textStyle: {
                                color: '#006ced',
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
                                            [this.dashboard_data.gh_data.value / 100, this.colorSet.color],
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
                                    value: this.dashboard_data.gh_data.value,
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
