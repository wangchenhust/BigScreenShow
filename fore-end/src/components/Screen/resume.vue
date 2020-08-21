<template>
    <div class="resume">
        <div class="resume-hd">
            <ul>
                <li>
                    <!--水球图-->
                    <waterSphereChart :risk="safeRate" />
                </li>
                <li>{{total}}</li>
                <li>{{riskNum}}</li>
            </ul>
        </div>
        <div class="resume-bd">
            <ul>
                <li>分行风险比率</li>
                <li>工商银行分行总数 (个)</li>
                <li>存在风险的分行数 (个)</li>
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
                limit:[],//[8,4,5,60]"资本充足率","核心资本充足率","核心一级资本充足率","核心负债依存度"
            }
        },
        computed: {
            // safeRate: function () {
            //     return (this.riskNum)/this.total;
            // },
            ...mapGetters({
                getValues:'map/getValues',
                getRisk:'risk/getValues'
                // ...
            }),
            ...mapState([
                'map/values',
                'risk/values'
            ])
        },
        watch:{//监听store的value变化
            getValues:{
                handler(newVal,oldVal) {// eslint-disable-line no-unused-vars
                    console.log("watch: map store更改！！")
                    this.setData();
                    this.setLimit();
                }
            },
        },
        mounted() {
          this.initData();
        },
        methods: {
            async initData(){
              let data1=await this.$H.get('/GetData/MMap');
              this.$store.commit('map/setValues',data1)
            },
            //初始化limit数组
            setLimit(){
                this.limit=[];
                let name=["资本充足率","核心资本充足率","核心一级资本充足率","核心负债依存度"];
                for(let j in name){
                  for(let i in this.getRisk){
                    if(this.getRisk[i].name.toString().localeCompare(name[j])==0){
                      this.limit.push(this.getRisk[i].value);
                      break;
                    }
                    else{
                      // console.log("不等");
                    }
                  }
                }
              console.log("地图阈值列表："+this.limit);
            },
            setData(){
                this.total=this.getValues.length;
                this.riskNum=this.countRisk(this.getValues);
                this.safeRate=(this.riskNum)/(this.total);
            },
            countRisk(datas){
                this.data=datas
                let count=0;
                this.data.forEach(item=>{
                  if (item.zbczl<this.limit[0]||item.hxzbczl<this.limit[1]||item.hxyjzbczl<this.limit[2]||item.hxfzycd<this.limit[3]){
                    count+=1;
                  }
                });
                return count
            }
        }
    }
</script>

<style lang="scss" scoped>
    .resume {
        /*border: 1px solid #ffe400;*/
        /*visibility:hidden;*/
        /*position: absolute;*/
        overflow: hidden;
        /*display: block;*/
        cursor: default;
        z-index: 10;
        /*left: 0px;*/
        /*top: 10px;*/
        width: 99%;
        height: 100%;
        padding-left: 4px;
        .resume-hd{
            position: relative;
            border: 1px solid rgba(25, 186, 139, 0.2);
            height: 50px/*3rem*/;
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
                    height: 50px/*3.2rem*/;
                    line-height: 50px/*3.2rem*/;
                    font-size: 30px/*1.65rem*/;
                    color: #ffeb7b;
                    padding: 1px 0/*0.05rem 0*/;
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
                    height: 10px/*0.5rem*/;
                    line-height: 7.2px/*0.5rem*/;
                    text-align: center;
                    font-size: 12px/*0.75rem*/;
                    color: rgba(255, 255, 255, 0.7);
                    padding-top: 7.2px/*0.5rem*/;
                }
            }
        }
    }
</style>
