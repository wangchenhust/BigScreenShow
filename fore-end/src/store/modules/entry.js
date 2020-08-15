// initial state
const state = () => ({
    values: null
    // [{"bank":"工商银行","growth_rate":9,"total_assets_2019":27699540,"total_assets_2020":30109436,"type":"国有行"}....]
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
