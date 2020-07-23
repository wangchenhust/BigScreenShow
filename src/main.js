import Vue from 'vue'
import App from './App.vue'
import echarts from 'echarts'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import store from './store/index.js'
import request from './assets/js/request.js'

Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.$echarts = echarts
Vue.prototype.$axios = axios
Vue.prototype.$store = store
Vue.prototype.$H = request

new Vue({
  render: h => h(App),
  store
}).$mount('#app')
