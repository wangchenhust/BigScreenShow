import Vue from 'vue'
import Vuex from 'vuex'
import pie from './modules/pie'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        pie
    }
})
