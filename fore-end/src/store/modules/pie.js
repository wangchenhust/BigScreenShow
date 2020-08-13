// initial state
const state = () => ({
    values: null
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