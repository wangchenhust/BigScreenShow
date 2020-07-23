import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        xData:["周一","周二","周三","周四","周五"],
        chartData:[5, 20, 36, 10, 13],
        chartType:'line'
    },
    mutations:{
        changeData(state,data){
            state.xData = data.xData
            state.chartData = data.chartData
            state.chartType = data.chartType
        }
    }
})
