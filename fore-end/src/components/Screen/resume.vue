<template>
    <div class="resume">
        <div class="resume-hd">
            <ul>
                <li>
                    <!--水球图-->
                    <waterSphereChart :value="safeRate" />
                </li>
                <li>{{total}}</li>
                <li>{{riskNum}}</li>
            </ul>
        </div>
        <div class="resume-bd">
            <ul>
                <li>省分行风险比率</li>
                <li>工商银行省分行总数 (个)</li>
                <li>存在风险的省分行数 (个)</li>
            </ul>
        </div>
    </div>
</template>

<script>
    import {mapGetters, mapState} from "vuex";

    export default {
        name: "resume",
        data(){
            return{
                total:0,//工商银行省分行数
                riskNum:0,//存在风险的省分行数
                safeRate:0,
            }
        },
        computed: {
            // safeRate: function () {
            //     return (this.riskNum)/this.total;
            // },
            ...mapGetters({
                getValues:'map/getValues'
                // ...
            }),
            ...mapState([
                'map/values'
            ])
        },
        watch:{//监听store的value变化
            getValues:{
                handler(newVal,oldVal) {// eslint-disable-line no-unused-vars
                    console.log("watch: map store更改！！")
                    this.setData();
                }
            },
        },
        mounted() {
        },
        methods: {
            setData(){
                this.total=this.getValues.length;
                this.safeRate=(this.riskNum)/this.total;
            }
        }
    }
</script>

<style lang="scss" scoped>
    .resume {
        /*border: 1px solid #ffe400;*/
        /*visibility:hidden;*/
        background: rgba(101, 132, 226, 0.1);
        padding: 0.2rem 0.7rem;
        margin:0.2rem 0.5rem;
        height: 4.5rem;
        width: 37rem;
        z-index: 10;
        position: fixed;
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
</style>
