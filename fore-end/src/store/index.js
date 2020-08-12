import Vue from 'vue'
import Vuex from 'vuex'
import pie from './modules/pie'

Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        configData:{}
    },
    mutations:{
        changeChart(state,newConfig){
            state.configData = newConfig
            console.log(state.configData)
        }
    },
    getters: {
        shareConfig(state) {
            return state.configData
        }
    },
    modules: {
        pie
    }
})
