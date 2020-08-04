<template>
    <div class="screen-container">
        <div class="wrap">
<!--            大屏首部-->
            <header>
<!--                天气显示-->
                <div class="weather">
                    <img :src="imgSrc">
                    <span class="temperature"> {{weatherData.tem}}°</span>
                    <span class="city">{{ weatherData.city }}</span>
                </div>
<!--                标题-->
                <h2>全国银行近年数据统计大屏</h2>
<!--                时间日期-->
                <div class="showTime">
<!--                    北京时间-->
                    <span class="getTime">
                        {{nowTime}}
                    </span>
<!--                    日期-->
                    <span class="getDate">
<!--                        星期-->
                        <span class="day">{{nowDay}}</span>
<!--                        日期-->
                        <span class="date"> {{nowDate}}</span>
                    </span>
                </div>
            </header>
<!--            大屏主要部分-->
            <section class="major">
<!--                左侧-->
                <div class="left part">
<!--                    柱状图模块-->
                    <div class="panel">
                        <h2>近年工行资产构成</h2>
<!--                        柱状图组件-->
                        <barChart />
                        <div class="panel-footer"></div>
                    </div>
<!--                    饼图模块-->
                    <div class="panel">
                        <h2>近年工行营业收入</h2>
<!--                        饼图组件-->
                        <pieChart />
                        <div class="panel-footer"></div>
                    </div>
<!--                    仪表盘模块-->
                    <div class="panel">
                        <h2>近年工行核心资本充足率</h2>
<!--                        仪表盘组件-->
                        <dashboard />
                        <div class="panel-footer"></div>
                    </div>
                </div>
<!--                中间-->
                <div class="middel part">
<!--                    报警提示-->
                    <div class="resume">
                        <div class="resume-hd">
                            <ul>
                                <li>
<!--                                    水球图-->
                                    <waterSphereChart />
                                </li>
                                <li>10</li>
                                <li>5</li>
                            </ul>
                        </div>
                        <div class="resume-bd">
                            <ul>
                                <li>安全比率</li>
                                <li>全部指标项数</li>
                                <li>当前风险指标项数</li>
                            </ul>
                        </div>
                    </div>
<!--                    地图-->
                    <div class="map_part">
<!--                        地图-->
                        <div class="map"></div>
<!--                        地图详情-->
                        <div class="map_info"></div>
<!--                        地图背景图片-->
                        <div class="map1"></div>
                        <div class="map2"></div>
                        <div class="map3"></div>
                    </div>
<!--                    折线图模块-->
                    <div class="panel below">
                        <h2>2020年四大行资本充足率与成本收入比</h2>
<!--                        折线图组件--->
                        <lineChart />
                        <div class="panel-footer"></div>
                    </div>
                </div>
<!--                右侧-->
                <div class="right part">
<!--                    雷达图模块-->
                    <div class="panel">
                        <h2>近年四大行对比</h2>
<!--                        雷达图组件-->
                        <radarChart />
                        <div class="panel-footer"></div>
                    </div>
<!--                    可配置模块-->
                    <div class="panel">
                        <h2>可配置</h2>
<!--                        可配置组件-->
                        <configChart />
                        <div class="panel-footer"></div>
                    </div>
<!--                    列表模块-->
                    <div class="panel">
                        <h2>17家全国性银行总资产规模</h2>
<!--                        列表组件-->
                        <list />
                        <div class="panel-footer"></div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "Screen",
        data(){
            return {
                //天气图标url
                imgSrc: '',
                // 天气
                weatherData:{},
                //时间
                nowTime:'',
                //日期
                nowDate:'',
                //星期
                nowDay:'',
                //
                timer: null,
            }
        },
        computed: {

        },
        mounted() {
            //天气
            this.getWeather();
            //每隔一天调用函数getWeather
            this.timer = setInterval(() => {
                this.getWeather();
            }, 1000 * 60 * 60)
            //时间日期
            this.getTimes();

        },
        methods: {
            //获取天气数据
            getWeather(){
                axios.get('https://www.tianqiapi.com/api/', {
                    params: {
                        appid: '84946515',
                        appsecret: '4PHPZ0mP ',
                        version: 'v6',
                        city:'珠海'
                    }
                }).then(res => {
                    if (res.data) {
                        if (res.data.wea_img == 'xue') {
                            this.imgSrc = require('../assets/img/screen_img/xue.png');
                        } else if (res.data.wea_img == 'yin') {
                            this.imgSrc = require('../assets/img/screen_img/yin.png');
                        } else if (res.data.wea_img == 'yu' || res.data.wea_img == 'bingbao') {
                            this.imgSrc = require('../assets/img/screen_img/yu.png');
                        } else if (res.data.wea_img == 'yun') {
                            this.imgSrc = require('../assets/img/screen_img/yun.png');
                        } else if (res.data.wea_img == 'wu') {
                            this.imgSrc = require('../assets/img/screen_img/wu.png');
                        } else if (res.data.wea_img == 'shachen') {
                            this.imgSrc = require('../assets/img/screen_img/shachen.png');
                        } else if (res.data.wea_img == 'lei') {
                            this.imgSrc = require('../assets/img/screen_img/lei.png');
                        } else {
                            this.imgSrc = require('../assets/img/screen_img/qing.png');
                        }
                        this.weatherData = res.data;
                    }
                }).catch(err => {
                    console.log(err)
                })
            },
            //获取时间日期数据
            getTimes(){
                this.timeFormate(new Date());//设定格式
                setInterval(this.getTimes, 1000);//每隔一秒调用函数getTimes
                this.clear();//停止
            },
            clear() {
                clearInterval(this.getTimes)
                this.getTimes = null;
            },
            //时间日期数据格式化
            timeFormate(timeStamp) { //显示当前时间
                let newDate = new Date(timeStamp);
                let year = newDate.getFullYear();
                let month = newDate.getMonth() + 1 < 10 ? '0' + (newDate.getMonth() + 1) : newDate.getMonth() + 1;
                let date = newDate.getDate() < 10 ? '0' + newDate.getDate() : newDate.getDate();
                let hh = newDate.getHours() < 10 ? '0' + newDate.getHours() : newDate.getHours();
                let mm = newDate.getMinutes() < 10 ? '0' + newDate.getMinutes() : newDate.getMinutes();
                let ss = newDate.getSeconds() < 10 ? '0' + newDate.getSeconds() : newDate.getSeconds();
                let week = newDate.getDay();
                let weeks = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
                let getWeek = weeks[week];
                this.nowDay = getWeek;
                this.nowDate = year + '.' + month + '.' + date;
                this.nowTime = hh + ':' + mm + ':' + ss;
            },

        },
        beforeDestroy() {
            clearInterval(this.timer);
        }
    }
</script>

<style lang="scss" scoped>
.screen-container{

}
.wrap{
 position:fixed;
 top: 0;
 left: 0;
 height: 100%;
 width: 100%;
 background: url(../assets/img/screen_img/1.jpg) no-repeat #000;
 background-size: cover;
 /*min-width: 1024px;*/
 /*min-height: 600px;*/
 /*line-height: 1.15;*/
}
header{
    position: relative;
    height: 3.3rem;
    /*border: 1px solid #ffe400;*/
    background: url(../assets/img/screen_img/head_bg.png) no-repeat top center;
    background-size: 100% 100%;
}
header h2{
    color: #7ef0ff;
    font-size: 1.55rem;
    text-align: center;
    line-height: 2.55rem;
    letter-spacing: 1px;
    /*border: 1px solid #ffe400;*/
}
.showTime{
    color: #ffffff;
    position: absolute;
    right: 1.7rem;
    top: 1.25rem;
    color: rgba(126, 240, 255, .7);
    /*display: flex;*/
    /*border: 1px solid #ffe400;*/
    .getTime{
        font-size: 25px;
        margin-right: 0.025rem;
    }
}
.weather{
    position: absolute;
    left: 7rem;
    top: 1rem;
    color: rgba(126, 240, 255, .7);
    .temperature{
        font-size: 25px;
    }
    span{
        /*border: 1px solid #ffe400;*/
    }
    img {
        width: 1.5rem;
        /*border: 1px solid #ffe400;*/
    }
}
.major{
    position: relative;
    padding: 0rem 0.25rem 0.25rem 0.25rem;
    /*border: 1px solid #fe3e12;*/
    display: flex;
}
.part{
    flex: 3;
    /*border: 1px solid #ffe400;*/
    margin: 0 0.125rem 0.1rem;
    &.middel{
        flex: 6;
        margin: 0 0.125rem 0.1rem;
        overflow: hidden;
        .resume {
            /*border: 1px solid #ffe400;*/
            background: rgba(101, 132, 226, 0.1);
            padding: 0.2rem 0.7rem;
            margin:0rem 0.5rem;
            height: 4.5rem;
            .resume-hd{
                position: relative;
                border: 1px solid rgba(25, 186, 139, 0.17);
                height: 3rem;
                ul{
                    display: flex;
                    %li-line {
                        content: "";
                        position: absolute;
                        height: 50%;
                        width: 1px;
                        background: rgba(255, 255, 255, 0.2);
                        top: 25%;
                    }
                    li{
                        position: relative;
                        flex: 1;
                        text-align: center;
                        height: 3.2rem;
                        line-height: 3.2rem;
                        font-size: 1.65rem;
                        color: #ffeb7b;
                        padding: 0.05rem 0;
                        font-family: 'DIGITALDREAMFAT';
                        font-weight: bold;
                        &:nth-child(2){
                            &:after {
                                @extend %li-line;
                                right: 0;
                            }
                            &:before {
                                @extend %li-line;
                                left: 0;
                            }
                        }
                    }
                }
                &:before {
                    content: "";
                    position: absolute;
                    width: 30px;
                    height: 10px;
                    border-top: 2px solid #02a6b5;
                    border-left: 2px solid #02a6b5;
                    top: 0;
                    left: 0;
                }
                &:after {
                    content: "";
                    position: absolute;
                    width: 30px;
                    height: 10px;
                    border-bottom: 2px solid #02a6b5;
                    border-right: 2px solid #02a6b5;
                    right: 0;
                    bottom: 0;
                }
            }
            .resume-bd {
                ul {
                    display: flex;
                    li {
                        flex: 1;
                        height: 0.5rem;
                        line-height: 0.5rem;
                        text-align: center;
                        font-size: 0.75rem;
                        color: rgba(255, 255, 255, 0.7);
                        padding-top: 0.5rem;
                    }
                }
            }
        }
        .below{
            height: 7.05rem;
        }
    }
    %map-style {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 19.475rem;
        height: 19.475rem;
        background: url(../assets/img/screen_img/map.png) no-repeat;
        background-size: 100% 100%;
        opacity: 0.7;
    }
    .map_part{
        position: relative;
        height: 20rem;
        /*border: 1px solid #ffe400;*/
        .map1 {
            @extend %map-style;
        }
        .map2 {
            @extend %map-style;
            width: 22.0375rem;
            height: 22.0375rem;
            background-image: url(../assets/img/screen_img/lbx.png);
            opacity: 0.7;
            -webkit-animation: rotate 15s linear infinite;
            animation: rotate 15s linear infinite;
            z-index: -1;
        }
        .map3 {
            @extend %map-style;
            width: 21.075rem;
            height: 21.075rem;
            background-image: url(../assets/img/screen_img/jt.png);
            -webkit-animation: rotate1 10s linear infinite;
            animation: rotate1 10s linear infinite;
        }
    }
}
.panel{
    border: 1px solid rgba(25, 186, 139, 0.17);
    background: rgba(255, 255, 255, 0.04) url(../assets/img/screen_img/line.png);
    position: relative;
    height: 10rem;
    margin-bottom:0.35rem;
    padding: 0.25rem;
    &:before {
        position: absolute;
        top: 0;
        left: 0;
        content: "";
        width: 10px;
        height: 10px;
        border-top: 2px solid #02a6b5;
        border-left: 2px solid #02a6b5;
    }
    &:after {
        position: absolute;
        top: 0;
        right: 0;
        content: "";
        width: 10px;
        height: 10px;
        border-top: 2px solid #02a6b5;
        border-right: 2px solid #02a6b5;
    }
    .panel-footer {
        position: absolute;
        left: 0;
        bottom: 0;
        width: 100%;
        &:before {
            position: absolute;
            bottom: 0;
            left: 0;
            content: "";
            width: 10px;
            height: 10px;
            border-bottom: 2px solid #02a6b5;
            border-left: 2px solid #02a6b5;
        }
        &:after {
            position: absolute;
            bottom: 0;
            right: 0;
            content: "";
            width: 10px;
            height: 10px;
            border-bottom: 2px solid #02a6b5;
            border-right: 2px solid #02a6b5;
        }
    }
    h2 {
        height: 0.6rem;
        line-height: 0.6rem;
        text-align: center;
        color: #fff;
        font-size: 0.7rem;
        font-weight: 400;
    }
}

@-webkit-keyframes rotate {
    from {
        transform: translate(-50%, -50%) rotate(0deg);
    }
    to {
        transform: translate(-50%, -50%) rotate(360deg);
    }
}
@keyframes rotate {
    from {
        transform: translate(-50%, -50%) rotate(0deg);
    }
    to {
        transform: translate(-50%, -50%) rotate(360deg);
    }
}
@-webkit-keyframes rotate1 {
    from {
        transform: translate(-50%, -50%) rotate(0deg);
    }
    to {
        transform: translate(-50%, -50%) rotate(-360deg);
    }
}
@keyframes rotate1 {
    from {
        transform: translate(-50%, -50%) rotate(0deg);
    }
    to {
        transform: translate(-50%, -50%) rotate(-360deg);
    }
}

@media screen and (max-width: 1024px) {
    html {
        font-size: 42px !important;
    }
}
@media screen and (min-width: 1920px) {
    html {
        font-size: 80px !important;
    }
}
</style>
