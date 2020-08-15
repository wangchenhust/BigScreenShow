<template>
    <div class="pieChart-container">
        <div class="chart" id="chart_left2"></div>
    </div>
</template>

<script>
    import echarts from "echarts";
    import { mapGetters,mapState } from 'vuex'

    export default {
        name: "pieChart",
        data(){
            return{
                color:['#00ffff', '#00cfff', '#006ced',
                    '#ffe000', '#ffa800', '#ff5b00', '#ff3000'],
                // color: ['rgb(255, 153, 153)', 'rgb(255, 176, 63)', 'rgb(61, 186, 45)',
                //     'rgb(43, 166, 254)','rgb(255,222,0)','rgb(255,0,0)'],
                list :[],
                myCharts:null,
                option:null
            }
        },
        computed: {
            ...mapGetters({
                getValues:'pie/getValues'
                // ...
            }),
            ...mapState([
                'pie/values'
            ])
        },
        watch:{//监听store的value变化
            getValues:{
                handler(newVal,oldVal) {// eslint-disable-line no-unused-vars
                    this.setList()
                    this.setOption()
                }
            }
        },
        mounted() {
            //console.log(this.getValues)
            this.initData();
            this.setList();
            this.getChart();
            this.setOption();
            if (typeof (EventSource) == "undefined") this.timer();
        },
        methods: {
            async initData(){
               let data1=await this.$H.get('/GetData/Pie');
               this.$store.commit('pie/setValues',data1)
            },
            setList(){
                this.list.length=0
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
                for (let i in this.getValues) {
                    this.list.push({
                        value: this.getValues[i].value,
                        name: this.getValues[i].name,
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
                this.myChart = echarts.init(document.getElementById('chart_left2'))
                window.addEventListener('resize', () => {
                    this.myChart.resize();
                });
            },
            setOption(){
                // let rich = {
                //     white: {
                //         align: 'center',
                //         padding: [3, 0]
                //     }
                // };
                this.option ={
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
                                        for (let i in this.getValues) {
                                            total += this.getValues[i].value
                                        }
                                        let percent = ((params.value / total) * 100).toFixed(0);
                                        let name = params.name.replace(/\n/g, '')
                                        if (params.name !== '') {
                                            if(params.name.length<=7)
                                                return name + ' ' + percent + '%'
                                            else{
                                                var halflen=params.name.length/2;
                                                return name.toString().substr(0,halflen) +'\n'+name.toString().substring(halflen)+' '+ percent + '%'
                                            }
                                        } else {
                                            return ''
                                        }
                                    },
                                    // rich: rich
                                },
                                // labelLine: {//标签
                                //     length: 3,
                                //     length2: 25,
                                //     show: true,
                                //     color: '#00ffff'
                                // }
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
// <<<<<<< HEAD
//                 myChart.setOption(option);
//                 window.addEventListener('resize', () => {
//                     myChart.resize();
//                 });
// =======
                this.myChart.setOption(this.option,true);
            },
            timer() {
                return setInterval(() => {
                    this.initData()
                }, 5000)
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
        top: 10px;
        width: 100%;
        height: 100%;
    }
</style>
