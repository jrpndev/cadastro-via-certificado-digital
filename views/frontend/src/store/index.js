import Vuex from 'vuex'

export default new Vuex.Store({
  state: {
    token: null
  },
  mutations: {
    setToken (state, token) {
      state.token = token
    },
    removeToken (state) {
      state.token = null
    }
  },
  actions: {
    login ({ commit }, token) {
      commit('setToken', token)
    },
    logout ({ commit }) {
      commit('removeToken')
    }
  },
  getters: {
    isAuthenticated (state) {
      return !!state.token
    }
  }
})
