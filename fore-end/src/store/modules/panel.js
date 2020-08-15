// initial state
const state = () => ({
    values: null
    //[{"name":"资产利润率","value":2},...]
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
