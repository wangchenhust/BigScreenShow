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
