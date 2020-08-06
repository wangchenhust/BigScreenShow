<template>
    <div class="pieChart-container">
        <div class="chart" id="chart_left2"></div>
    </div>
</template>

<script>
    import echarts from "echarts";

    export default {
        name: "pieChart",
        data(){
            return{
                color:['#00ffff', '#00cfff', '#006ced',
                    '#ffe000', '#ffa800', '#ff5b00', '#ff3000'],
                names:['利息','投资收益','公允价值变动损益','手续费佣金收入','其他业务收入','汇总收益'],
                values:[5,10,10,10,5,5],
                list :[]
            }
        },
        computed: {

        },
        mounted() {
            this.setList();
            this.getChart();
        },
        methods: {
            setList(){
                let placeHolderStyle = {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        },
                        color: 'rgba(0, 0, 0, 0)',
                        borderColor: 'rgba(0, 0, 0, 0)',
                        borderWidth: 0
                    }
                };
                for (let i in this.values) {
                    this.list.push({
                        value: this.values[i],
                        name: this.names[i],
                        itemStyle: {
                            normal: {
                                borderWidth: 6,
                                shadowBlur: 10,
                                borderColor: this.color[i],
                                shadowColor: this.color[i],
                                color: this.color[i]
                            }
                        }
                    }, {
                        value: 2,
                        name: '',
                        itemStyle: placeHolderStyle
                    })
                }
            },
            getChart(){
                var myChart = echarts.init(document.getElementById('chart_left2'))
                let rich = {
                    white: {
                        align: 'center',
                        padding: [3, 0]
                    }
                };
                var option ={
                    tooltip: {
                        show: false
                    },
                    series: [{
                        name: '',
                        type: 'pie',
                        clockWise: false,
                        startAngle: '90',//初始角度
                        radius: ['50%', '51%'],//大小、宽度调节
                        center: ['50%', '50%'],
                        hoverAnimation: false,
                        itemStyle: {
                            normal: {
                                label: {
                                    show: true,
                                    position: 'outside',
                                    formatter:(params) => {
                                        let total = 0;
                                        for (let i in this.values) {
                                            total += this.values[i]
                                        }
                                        let percent = ((params.value / total) * 100).toFixed(0);
                                        let name = params.name.replace(/\n/g, '')
                                        if (params.name !== '') {
                                            return name + '\n{white|' + percent + '%}'
                                        } else {
                                            return ''
                                        }
                                    },
                                    rich: rich
                                },
                                labelLine: {//标签
                                    length: 6,
                                    length2: 25,
                                    show: true,
                                    color: '#00ffff'
                                }
                            }
                        },
                        data: this.list,
                        //动画效果
                        animationType: 'scale',
                        animationEasing: 'elasticOut',
                        animationDelay: function(idx) {
                            return idx * 50;
                        }
                    }]
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
    .pieChart-container {
        position: absolute;
        overflow: hidden;
        display: block;
        cursor: default;
        z-index: 0;
        left: 0px;
        top: 5px;
        width: 100%;
        height: 100%;
    }
</style>
