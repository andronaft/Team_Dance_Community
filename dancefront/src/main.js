import Vue from 'vue'
import './plugins/fontawesome'
import App from './App.vue'
import router from './router'
import store from './store'

import { clickOutside } from './Directive.js';

Vue.directive('click-outside', clickOutside)
import axios from 'axios'
Vue.config.productionTip = false
Vue.prototype.$axios = axios;
const token = localStorage.getItem('token')
const user = localStorage.getItem('user')
console.log("Token", token)
if (token) {
  Vue.prototype.$axios.defaults.headers.common['Authorization'] = token
}

if(user) {
  store.state.user = JSON.parse(user);
}


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
