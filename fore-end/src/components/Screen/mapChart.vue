<template>
    <div class="map-container">
        <div class="chart" id="chart_middel2"></div>
    </div>
</template>

<script>
    import echarts from "echarts";
    export default {
        name: "mapChart",
        data(){
            return{
                mapName : 'china',
                data : [
                    {name:"湖北",value:116},
                    {name:"湖南",value:114},
                    {name:"重庆",value:91},
                    {name:"四川",value:125},
                    {name:"贵州",value:62},
                    {name:"云南",value:83},
                    {name:"西藏",value:9},
                    {name:"陕西",value:80},
                    {name:"甘肃",value:56},
                    {name:"青海",value:10},
                    {name:"宁夏",value:18},
                    {name:"新疆",value:180},
                    {name:"广东",value:123},
                    {name:"广西",value:59}
                ],
                geoCoordMap : {
                    '台湾': [121.5135,25.0308],
                    '黑龙江': [127.9688, 45.368],
                    '内蒙古': [110.3467, 41.4899],
                    "吉林": [125.8154, 44.2584],
                    '北京市': [116.4551, 40.2539],
                    "辽宁": [123.1238, 42.1216],
                    "河北": [114.4995, 38.1006],
                    "天津": [117.4219, 39.4189],
                    "山西": [112.3352, 37.9413],
                    "陕西": [109.1162, 34.2004],
                    "甘肃": [103.5901, 36.3043],
                    "宁夏": [106.3586, 38.1775],
                    "青海": [101.4038, 36.8207],
                    "新疆": [87.9236, 43.5883],
                    "西藏": [91.11, 29.97],
                    "四川": [103.9526, 30.7617],
                    "重庆": [108.384366, 30.439702],
                    "山东": [117.1582, 36.8701],
                    "河南": [113.4668, 34.6234],
                    "江苏": [118.8062, 31.9208],
                    "安徽": [117.29, 32.0581],
                    "湖北": [114.3896, 30.6628],
                    "浙江": [119.5313, 29.8773],
                    "福建": [119.4543, 25.9222],
                    "江西": [116.0046, 28.6633],
                    "湖南": [113.0823, 28.2568],
                    "贵州": [106.6992, 26.7682],
                    "云南": [102.9199, 25.4663],
                    "广东": [113.12244, 23.009505],
                    "广西": [108.479, 23.1152],
                    "海南": [110.3893, 19.8516],
                    '上海': [121.4648, 31.2891],

                }
            }
        },
        computed: {

        },
        mounted() {
            this.getChart();
        },
        methods: {
            convertData(data) {
                var res = [];
                for (var i = 0; i < data.length; i++) {
                    var geoCoord = this.geoCoordMap[data[i].name];
                    if (geoCoord) {
                        res.push({
                            name: data[i].name,
                            value: geoCoord.concat(data[i].value),
                        });
                    }
                }
                return res;
            },
            getChart(){
                var myChart = echarts.init(document.getElementById('chart_middel2'));

                var option = {
                    // backgroundColor: 'rgba(0, 10, 52, 1)',
                    tooltip: {//小浮框
                        padding: 0,
                        enterable: true,
                        transitionDuration: 1,
                        textStyle: {
                            color: "#fff",
                            decoration: 'none',
                        },
                    },
                    geo: {//地图
                        map: this.mapName,
                        show: true,
                        roam: true,//可拖拽、放大
                        label: {
                            emphasis: {
                                show: false,
                                color: "#fff"
                            }
                        },
                        //   放大我们的地图
                        zoom: 1.6,
                        itemStyle: {
                            normal: {
                                areaColor: "rgba(43, 196, 243, 0.42)",
                                borderColor: "rgba(43, 196, 243, 1)",
                                borderWidth: 0.7
                            },
                            emphasis: {
                                areaColor: "#2B91B7"
                            }
                        }
                    },
                    series: [{
                        name: '散点值',
                        type: 'scatter',
                        coordinateSystem: 'geo',
                        data: this.convertData(this.data),
                        symbolSize: 5,
                        label:
                            {
                                normal: {
                                    formatter: '{b}',
                                    position: 'right',
                                    show: true
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                        itemStyle: {
                            normal: {
                                color: '#fff'
                            }
                        }
                    },
                        {
                            type: 'map',
                            map: this.mapName,
                            geoIndex: 0,
                            aspectScale: 0.75, //长宽比
                            showLegendSymbol: false, // 存在legend时显示
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: false,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                }
                            },
                            roam: true,
                            itemStyle: {
                                normal: {
                                    areaColor: '#031525',
                                    borderColor: '#3B5077',
                                },
                                emphasis: {
                                    areaColor: '#2B91B7'
                                }
                            },
                            animation: false,
                            data: this.data
                        },
                        {
                            name: '黄点',
                            type: 'effectScatter',
                            coordinateSystem: 'geo',
                            data: this.convertData(this.data),
                            symbolSize: function(val) {
                                return val[2] / 10;
                            },
                            showEffectOn: 'render',
                            rippleEffect: {
                                brushType: 'stroke'
                            },
                            hoverAnimation: true,
                            label: {
                                normal: {
                                    formatter: '{b}',
                                    position: 'left',
                                    show: false
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: 'yellow',
                                    shadowBlur: 10,
                                    shadowColor: 'yellow'
                                }
                            },
                            zlevel: 1
                        },

                    ]
                }
                myChart.setOption(option);
            },

        }
    }
</script>

<style lang="scss" scoped>
    .chart {
        height: 20.125rem;
        width: 100%;
    }
    .map-container {
        position: absolute;
        z-index: 8;
        left: 0px;
        top: 0px;
        width: 100%;
        height: 100%;
    }
</style>
