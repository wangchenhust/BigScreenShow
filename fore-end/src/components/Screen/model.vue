<template>
    <div class="model-container">
        <div class="chart" id="chart_xx"></div>
    </div>
</template>

<script>
    import echarts from "echarts";
    export default {
        name: "model",
        data(){
            return{
                data:[{name:'济南',value:'100万'},{name:'菏泽',value:'100万'}]
            }
        },
        computed: {

        },
        mounted() {
            // this.getChart();
            this.mapGeo(this.data)
        },
        methods: {
            getChart(){
                var myChart = echarts.init(document.getElementById('chart_xx'))
                var option ={

                }
                myChart.setOption(option);
            },
            mapGeo(data) {
                var chart = echarts.init(document.getElementById('chart_xx'));
                // var mapUrl = 'json/beijing.json';
                $.getJSON('json/beijing.json', function(mapJson){
                    chart.registerMap('beijing', mapJson);

                    chart.setOption({
                        tooltip: {
                            trigger: 'item',
                            formatter: function formatter(params) {
                                return params.name + "<br/>\u70B9\u4F4D\u5907\u6848\u6570:" + params.data.locationCount + "<br/>\n                                \u70B9\u4F4D\u6D3B\u8DC3:" + params.data.active + "<br/>\n                                \u5F53\u65E5\u8FC7\u8F66\u8F86:" + params.data.pass + "<br/>\n                                \u5F53\u65E5\u8FC7\u8F66\u8F86\u53BB\u91CD:" + params.data.remoPass + "<br/>";
                            },
                            position:'inside'  //设置定位很重要不然提示框会偏移
                        },
                        series: [{
                            name: '北京市各区多维数据统计',
                            type: 'map',
                            map: 'beijing',
                            radius: '80%',
                            layoutCenter: ['50%', '50%'],
                            layoutSize: '100%',
                            label: {
                                normal: {
                                    show: true,
                                    textStyle: { color: "#fff" }
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: { color: "#fff" }
                                }
                            },
                            itemStyle: {
                                normal: {
                                    borderWidth: .5, //区域边框宽度
                                    borderColor: '#fff', //区域边框颜色
                                    areaColor: "#0a89e2" //区域颜色
                                },
                                emphasis: {
                                    borderWidth: .5,
                                    borderColor: '#fff',
                                    areaColor: "#f7981f"
                                }
                            },
                            // data : data//人口数据：例如[{name:'济南',value:'100万'},{name:'菏泽'，value:'100万'}......]
                            data: data
                        }]
                    });
                });
                var hourIndex=0;
                var fhourTime=null;
                fhourTime=setInterval(function(){
                    chart.dispatchAction({
                        type:"downplay",
                        seriesIndex:0,

                    });
                    chart.dispatchAction({
                        type:"highlight",
                        seriesIndex:0,
                        dataIndex:hourIndex
                    });
                    chart.dispatchAction({
                        type:"showTip",
                        seriesIndex:0,
                        dataIndex:hourIndex
                    });
                    hourIndex++;
                    if(hourIndex>data.length){
                        hourIndex=0;
                    }
                },3000);
                //鼠标移入停止轮播
                chart.on("mousemove", function(e) {
                    clearInterval(fhourTime)
                    chart.dispatchAction({
                        type: "downplay",
                        seriesIndex: 0,
                    });
                    chart.dispatchAction({
                        type: "highlight",
                        seriesIndex: 0,
                        dataIndex: e.dataIndex
                    });
                    chart.dispatchAction({
                        type: "showTip",
                        seriesIndex: 0,
                        dataIndex: e.dataIndex
                    });
                })
                //鼠标移出恢复轮播
                chart.on("mouseout", function() {
                    fhourTime = setInterval(function() {
                        chart.dispatchAction({
                            type: "downplay",
                            seriesIndex: 0,

                        });
                        chart.dispatchAction({
                            type: "highlight",
                            seriesIndex: 0,
                            dataIndex: hourIndex
                        });
                        chart.dispatchAction({
                            type: "showTip",
                            seriesIndex: 0,
                            dataIndex: hourIndex
                        });
                        hourIndex++;
                        if (hourIndex > data.length) {
                            hourIndex = 0;
                        }
                    }, 3000);
                })
    },
        }
    }
</script>

<style lang="scss" scoped>
    .chart {
        width: 100%;
        height: 100%;
    }
    .model-container {
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
