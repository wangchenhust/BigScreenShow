<template>
    <div class="list-container">
        <div class="table">
            <table border="0" cellpadding="0" cellspacing="0" class="table-header">
                <tbody>
                <tr>
                    <td width="16%">
                        <span>公司</span>
                    </td>
                    <td width="30%">
                        <span>2019年资产值（万亿）</span>
                    </td>
                    <td width="30%">
                        <span>2020年资产值（万亿）</span>
                    </td>
                    <td width="12%">
                        <span>增幅%</span>
                    </td>
                    <td width="12%">
                        <span>类型</span>
                    </td>
                </tr>
                </tbody>
            </table>

            <vue-seamless-scroll :data="listData" class="seamless-warp" :class-option="optionSetting">
                <table border="0" cellpadding="0" cellspacing="0" class="item">
                    <tbody>
                    <tr v-for="(item, index) in listData" :key="index">
                        <td width="100%" class="title">
                            <span>{{ item.title }}</span>
                        </td>
                        <td width="28%">
                            <span>{{ item.data2019 }}</span>
                        </td>
                        <td width="28%">
                            <span>{{ item.data2020 }}</span>
                        </td>
                        <td width="12%" class="incre">
                            <span>{{ item.incre }}</span>
                        </td>
                        <td width="12%">
                            <span>{{ item.type }}</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </vue-seamless-scroll>
        </div>
    </div>
</template>

<script>
    export default {
        name: "list",
        data() {
            return {
                listData: [{
                    title: '工商银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '国有'
                }, {
                    title: '建设银行',
                    data2019:22.12,
                    data2020:23.22,
                    incre:4.96,
                    type: '国有'
                }, {
                    title: '农业银行',
                    data2019:21.05,
                    data2020:22.61,
                    incre:7.39,
                    type: '国有'
                }, {
                    title: '中国银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '国有'
                }, {
                    title: '交通银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '国有'
                }, {
                    title: '邮储银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '国有'
                }, {
                    title: '招商银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                }, {
                    title: '兴业银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                }, {
                    title: '浦发银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                }, {
                    title: '中信银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                }, {
                    title: '民生银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                }, {
                    title: '光大银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                }, {
                    title: '平安银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                }, {
                    title: '华夏银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                },{
                    title: '广发银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                },{
                    title: '浙商银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                },{
                    title: '渤海银行',
                    data2019:26.09,
                    data2020:27.70,
                    incre:6.18,
                    type: '股份'
                }]
            }
        },
        mounted() {
        },
        methods: {
        },
        computed: {
            optionSetting () {
                return {
                    step: 0.5, // 数值越大速度滚动越快
                    limitMoveNum: 2, // 开始无缝滚动的数据量 this.dataList.length
                    hoverStop: true, // 是否开启鼠标悬停stop
                    direction: 1, // 0向下 1向上 2向左 3向右
                    // autoPlay: false,
                    openWatch: true, // 开启数据实时监控刷新dom
                    singleHeight: 0, // 单步运动停止的高度(默认值0是无缝不停止的滚动) direction => 0/1
                    singleWidth: 0, // 单步运动停止的宽度(默认值0是无缝不停止的滚动) direction => 2/3
                    waitTime: 1000 // 单步运动停止的时间(默认值1000ms)
                }
            }
        }
    };
</script>

<style lang="scss" scoped>
    .list-container {
        position: absolute;
        overflow: hidden;
        display: block;
        cursor: default;
        z-index: 0;
        left: 0px;
        top: 8px;
        width: 100%;
        height: 90%;
        %table-style {
            width: 100%;
            height: 35px;
            table-layout: fixed;
            tr {
                td {
                    padding: 10px 5px;
                    text-align: center;
                    background-color: transparent;
                    white-space: nowrap;
                    overflow: hidden;
                    color: #ade3ff;
                    font-size: 10px;
                }
                .incre{
                    color: #FF4669
                }
                .title{
                    color: #FFC956
                }
            }
        }
    }
    .table {
        .table-header {
            @extend %table-style;
        }
        .seamless-warp {
            height: 400px;
            overflow: hidden;
            visibility: visible;
            .colorRed {
                color: #FF4669;
            }
            .colorOrange {
                color: #FFC956;
            }
            .item {
                height: auto;
                @extend %table-style;
                tr {
                    td {
                        &.title {
                            text-overflow: ellipsis;
                            display: inline-block;
                        }
                    }
                }
            }
        }
    }
</style>
