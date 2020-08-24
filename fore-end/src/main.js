import Vue from 'vue'
import App from './App.vue'
import echarts from 'echarts'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import store from './store'
import request from './assets/js/request.js'
//引入中国地图
import china from 'echarts/map/json/china.json'
echarts.registerMap('china', china)
//引入轮播插件
import VueAwesomeSwiper from 'vue-awesome-swiper'
Vue.use(VueAwesomeSwiper)
//引入自适应
import 'lib-flexible'

import Vcomp from './components/index'
Vue.use(Vcomp)

// 显示基本样式
import '@/assets/styles/base.scss'
import '@/assets/styles/common.scss'

//动态滚动
import scroll from 'vue-seamless-scroll'
Vue.use(scroll)
//粒子特效
import VueParticles from 'vue-particles'
Vue.use(VueParticles)

//初始化sse
import sse from "./assets/js/sse"
sse.sourceInit()
Vue.prototype.$sse=sse

Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.$echarts = echarts
Vue.prototype.$axios = axios
//Vue.prototype.$store = store
Vue.prototype.$H = request
Vue.prototype.bus = new Vue()

//根据路由设置title
router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app")

