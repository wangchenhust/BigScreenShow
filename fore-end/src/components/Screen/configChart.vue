<template>
    <div>
        <h2>{{chartName}}</h2>
        <div class="configChart-container">
            <div class="chart" id="newConfigChart"></div>
        </div>
    </div>

</template>

<script>
    import echarts from "echarts";
    import {mapGetters} from 'vuex'
    export default {
        name: "configChart",
        data () {
            return {
                option:{},
                newSelectChart:['line','gh_data'],
                xAxis_data:['资本充足率','核心一级资本充足率','资产利润率'],//折线图的x坐标
                colorSet : {//仪表盘所用颜色
                    color: '#468EFD'
                },
                testChart:{},
                chartNameData: {
                    zbchongzu:'资本充足率',
                    corezbchongzu:'核心资本充足率',
                    corefirstzbchongzu:'核心一级资本充足率',
                    zichanprofit:'资产利润率',
                    baddaikuan:'不良贷款率',
                    badzichan:'不良资产率',
                    liudongbili:'流动性比例',
                    corefuzhai:'核心负债依存度',
                    rorwa:'风险加权资产收益率',
                },
                chartName:''
            }
        },
        computed:{
            ...mapGetters(['getConfigInfo'])
        },
        mounted(){
            this.testChart = JSON.parse(localStorage.getItem('config'))
            this.chartName = this.chartNameData[this.testChart.chartName]
            this.initOption(this.testChart)
            this.drawCharts(this.option)
        },
        watch:{
            getConfigInfo:function(newVal){
                this.initOption(newVal)
                this.drawCharts(this.option)
            }
        },
        methods: {
            initOption(chartOption){
                if (chartOption.chartType =='line'){
                    this.option = {//折线图
                        calculable: true,
                        grid: {
                            left: "3%",
                            right: "8%",
                            bottom: "7%",
                            height:"70%",
                            containLabel: true
                        },
                        xAxis: [{
                            type: 'category',
                            boundaryGap: false,
                            data:['第一季度','第二季度'],
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: "#ade3ff", //X轴文字颜色
                                    fontSize: 10
                                }
                            },
                            axisLine: {
                                show: false,
                                color:'#A582EA'
                            },
                            splitLine: {
                                show: false
                            },
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
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    color: '#00BFF3',
                                    opacity:0.25
                                }
                            },
                            axisLine: {
                                show: false,
                            },
                            axisTick: {
                                show: false,
                            },
                    }],
                        series: [{
                        name: '指标',
                        type: 'line',
                        data: chartOption.bankChart,
                            showAllSymbol: true,
                            symbol: 'circle',
                            symbolSize: 7,
                            lineStyle: {
                                normal: {
                                    width:1.5,
                                    color: 'rgba(255,80,124,1)',//'#2CABE3',
                                },
                            },
                            label: {
                                show: true,
                                position: 'top',
                                textStyle: {
                                    color: 'rgba(255,80,124,1)',//'#2CABE3',
                                },
                            },
                            itemStyle: {
                                color: "#fff",
                                borderColor: 'rgba(255,80,124,1)',//'#2CABE3',
                                borderWidth: 2,
                            },
                            areaStyle: {
                                normal: {
                                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                        {
                                            offset: 0,
                                            color: 'rgba(255,80,124,0.5)',//'rgba(81,150,164,0.3)'
                                        },
                                        {
                                            offset: 1,
                                            color: 'rgba(255,80,124,0)'
                                        }
                                    ], false),
                                }
                            },
                    }]
                    }
                }
                else if (chartOption.chartType == 'gauge'){
                    this.option = {//仪表盘
                        title: {
                            show: true,
                                text: 'xx指标',
                                x: '49%',
                                y: '87%',
                                z: 8,//优先级
                                textAlign: 'center',
                                textStyle: {
                                color: '#ade3ff',
                                    fontStyle: 'normal',
                                    fontFamily: '微软雅黑',
                                    fontSize: 10,
                            },
                        },
                        series: [
                            {
                                name: "内部进度条",
                                type: "gauge",
                                center: ['50%', '60%'],
                                radius: '68%',//大小
                                z: 0,
                                splitNumber: 10,//大刻度单位数量
                                axisLine: {
                                    lineStyle: {
                                        color: [
                                            [chartOption.gaugeChart, this.colorSet.color],
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
                                center: ['50%', '60%'],
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
                                center: ['50%', '60%'],
                                z: 8,
                                hoverAnimation: false,
                                data: [{
                                    name: '资本充足率',
                                    value: chartOption.gaugeChart,
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
                                                    fontSize: 20,
                                                    fontWeight: "bold"
                                                },
                                            },
                                            formatter: function (params) {
                                                return "{a|" + params.value*100 + "%}";
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
                                center: ["50%", "60%"],
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
                                center: ["50%", "60%"],
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
                                center: ["50%", "60%"],
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
                }
            },
            drawCharts(option){
                let newConfigCharts = this.$echarts.init(document.getElementById('newConfigChart'))
                newConfigCharts.clear()
                newConfigCharts.setOption(option)
            },
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
    h2 {
        height: 0.6rem;
        line-height: 0.6rem;
        text-align: center;
        color: #fff;
        font-size: 0.7rem;
        font-weight: 400;
        margin-top: -9px;
    }
</style>
