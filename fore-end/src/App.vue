<template>
    <div id="app">
<!--        <vue-particles-->
<!--                color="#fff"-->
<!--                :particleOpacity="0.7"-->
<!--                :particlesNumber="60"-->
<!--                shapeType="circle"-->
<!--                :particleSize="4"-->
<!--                linesColor="#fff"-->
<!--                :linesWidth="1"-->
<!--                :lineLinked="true"-->
<!--                :lineOpacity="0.4"-->
<!--                :linesDistance="150"-->
<!--                :moveSpeed="2"-->
<!--                :hoverEffect="true"-->
<!--                hoverMode="grab"-->
<!--                :clickEffect="true"-->
<!--                clickMode="push"-->
<!--                class="lizi"-->
<!--        >-->
<!--        </vue-particles>-->
        <router-view/>
    </div>
</template>

<script>
    export default {
        name: 'App',
        data(){
            return{};
        }
    }
</script>

<style>
    #app {
        /*font-family: 'Avenir', Helvetica, Arial, sans-serif;*/
        /*-webkit-font-smoothing: antialiased;*/
        /*-moz-osx-font-smoothing: grayscale;*/
        /*text-align: center;*/
        /*color: #2c3e50;*/
        /*margin-top: 60px;*/
        /*width: 100%;*/
        /*height: 100%;*/
        /*-webkit-font-smoothing: antialiased;*/
        /*-moz-osx-font-smoothing: grayscale;*/
        /*-webkit-user-select: none;*/
        /*-moz-user-select: none;*/
        /*-ms-user-select: none;*/
        /*user-select: none;*/
    }
</style>
