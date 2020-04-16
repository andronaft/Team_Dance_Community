import Vue from "vue";
import Vuex from "vuex";
import axios from 'axios'

Vue.use(Vuex);


export default new Vuex.Store({
  state: {
    status: '',
    token: localStorage.getItem('token') || '',
    user : localStorage.getItem('user') || '',
  },
  mutations: {
    SET_USER(state, user) {
      state.user = user
    },
    auth_request(state){
      state.status = 'loading'
    },
    auth_success(state, token, user){
      console.log(user)
      state.status = 'success'
      state.token = token
      //state.user = user
    },
    auth_error(state){
      state.status = 'error'
    },
    logout(state){
      state.status = ''
      state.token = ''
      state.user = ''
    },
  
  },
  actions: {
    register({commit}, user){
      return new Promise((resolve, reject) => {
        commit('auth_request')
        axios({url: 'https://team-dance-community.herokuapp.com/api/v1/auth/register', data: user, method: 'POST' })
        .then(resp => {
          const token = resp.data.token
          const user = resp.data
          console.log(resp)
          localStorage.setItem('token', 'Bearer_' + token)
          localStorage.setItem('user', JSON.stringify(user))
          axios.defaults.headers.common['Authorization'] = 'Bearer_' + token
          commit('auth_success', 'Bearer_' + token, user)
          resolve(resp)
        })
        .catch(err => {
          commit('auth_error', err)
          localStorage.removeItem('token')
          reject(err)
        })
      })
    },    
    login({commit}, user){
      return new Promise((resolve, reject) => {
        commit('auth_request')
        axios({url: 'https://team-dance-community.herokuapp.com/api/v1/auth/login', data: user, method: 'POST' })
        .then(resp => {
          const token = resp.data.token
          const user = resp.data
          console.log(resp)
          console.log("USER", user)
          localStorage.setItem('token', 'Bearer_' + token)
          localStorage.setItem('user', JSON.stringify(user))
          axios.defaults.headers.common['Authorization'] = 'Bearer_' + token
          commit('auth_success', 'Bearer_' + token, '123')
          commit('SET_USER', user)

          resolve(resp)
        })
        .catch(err => {
          commit('auth_error')
          localStorage.removeItem('token')
          localStorage.removeItem('user')
          reject(err)
        })
      })
    },
    logout({commit}){
      return new Promise((resolve, reject) => {
        commit('logout')
        localStorage.removeItem('token')
        localStorage.removeItem('user')
        delete axios.defaults.headers.common['Authorization']
        resolve()
      })
     }
  },
  getters : {
    isLoggedIn: state => !!state.token,
    authStatus: state => state.status,
    user: state => state.user
  }
})