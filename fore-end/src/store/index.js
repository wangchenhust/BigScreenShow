import Vue from 'vue'
import Vuex from 'vuex'
import pie from './modules/pie'
import bar from './modules/bar'
import panel from './modules/panel'
import entry from "./modules/entry";
import map from "./modules/map";
import cline from "./modules/cline";
import line from "./modules/line";
import crada from "./modules/crada";
import rada from "./modules/rada";
import risk from "./modules/risk";

Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        configData:{
            chartType:'',
            bankChart:[],
            gaugeChart:{},
            chartName:''
        }
    },
    mutations:{
        changeChart(state,newConfig){
            state.configData = newConfig
            console.log(state.configData)
            localStorage.setItem('config',JSON.stringify(state.configData))
        }
    },

    getters: {
        getConfigInfo(state) {
            if (localStorage.getItem('config')){
                state.configData = JSON.parse(localStorage.getItem('config'))
                //console.log(state.configData)
            }
            return state.configData
        }
     },
    modules:{
        pie,
        bar,
        panel,
        entry,
        map,
        cline,
        line,
        crada,
        rada,
        risk
    }
})
