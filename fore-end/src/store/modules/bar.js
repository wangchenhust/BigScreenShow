// initial state
const state = () => ({
    values: null
    //[{name:"资产利润率",value:2}, {name:"资本利润率",value:13},
    //         {name:"不良贷款率",value:1}, {name:"不良资产率",value:2},
    //         {name:"流动性比例",value:35}]
})

// getters
const getters = {
    getValues:(state)=>{
        return state.values
    }
}

// actions
const actions = {}

// mutations
const mutations = {
    setValues (state, v) {
        state.values = v
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
