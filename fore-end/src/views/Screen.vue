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
                <h2>银行数据统计和风险报警大屏</h2>
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
<!--                    仪表盘模块-->
                    <div class="panel">
                        <h2>工行风险指标</h2>
<!--                        柱状图组件-->
<!--                        <dashboard />-->
                        <threeDashboards />
<!--                        <three-dashboards1 />-->
                        <div class="panel-footer"></div>
                    </div>
<!--                    柱状图模块-->
                    <div class="panel small">
                        <h2>工行重要风险指标</h2>
<!--                        仪表盘组件-->
                        <barChart />
                        <div class="panel-footer"></div>
                    </div>
<!--                    饼图模块-->
                    <div class="panel small">
                        <h2>工行营业收入构成</h2>
<!--                        饼图组件-->
<!--                        <pieChart />-->
                        <pieChart1 />
                        <div class="panel-footer"></div>
                    </div>
                </div>
<!--                中间-->
                <div class="middel part">
<!--                    报警提示-->
                    <resume/>
<!--                    地图-->
                    <div class="map_part">
<!--                        地图-->
                        <mapChart />
<!--                        地图背景图片-->
                        <div class="map1"></div>
                        <div class="map2"></div>
                        <div class="map3"></div>
                    </div>
<!--                    折线图模块-->
                    <div class="panel below">
                        <h2>2020年工行核心指标变化</h2>
<!--                        折线图组件--->
                        <lineChart />
                        <div class="panel-footer"></div>
                    </div>
                </div>
<!--                右侧-->
                <div class="right part">
<!--                    可配置模块-->
                    <div class="panel small">
                        <h2>四大行指标</h2>
                        <!--                        可配置组件-->
                        <configChart />
                        <div class="panel-footer"></div>
                    </div>
<!--                    列表模块-->
                    <div class="panel small">
                        <h2>17家全国性银行总资产规模</h2>
                        <!--                        列表组件-->
                        <list />
                        <div class="panel-footer"></div>
                    </div>
<!--                    雷达图模块-->
                    <div class="panel">
                        <h2>四大行对比</h2>
<!--                        雷达图组件-->
                        <radarChart />
                        <div class="panel-footer"></div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
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
            }
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
    /*width:1000px;*/
    /*border: 1px solid #ffe400;*/
    background: url(../assets/img/screen_img/head_bg.png) no-repeat top center;
    background-size: 100% 100%;
}
header h2{
    color: #7ef0ff;
    font-size: 1.5rem;
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
        font-size: 20px;
        margin-right: 0.025rem;
    }
}
.weather{
    position: absolute;
    left: 7rem;
    top: 1rem;
    color: rgba(126, 240, 255, .7);
    .temperature{
        font-size: 20px;
    }
    span{
        /*border: 1px solid #ffe400;*/
    }
    img {
        width: 1.3rem;
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
    margin: 0rem 0.125rem 0.1rem;
    &.middel{
        flex: 6;
        margin: 0px 0.125rem 0.1rem;
        overflow: hidden;

        .below{
            position: fixed;
            height: 6.5rem;
            width: 38rem;
            margin-left: 6px;
            bottom: 7px;
            z-index: 10;
            /*margin:0px 5px 0px 5px;*/
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
        opacity: 0.5;
        /*border: 1px solid #ffe400;*/
    }
    .map_part{
        position: fixed;
        height: 30.45rem;
        width: 610px;
        /*border: 1px solid #ffe400;*/
        z-index: 1;
        top:60px;
        margin: 0px 10px;
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
            z-index: -2;
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
    position: fixed;
    border: 1px solid rgba(25, 186, 139, 0.17);
    background: rgba(255, 255, 255, 0.04) url(../assets/img/screen_img/line.png);
    position: relative;
    height: 10rem;
    padding: 0.25rem;
    margin-top: 8px;
    z-index: 10;
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
        margin-top: 3px;
    }
}

.small{
    height: 9.5rem;
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
