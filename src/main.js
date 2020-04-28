import Vue from 'vue'
import './plugins/fontawesome'
import App from './App.vue'
import router from './router'
import store from './store'

import { clickOutside } from './Directive.js';

import VModal from 'vue-js-modal'
 
Vue.use(VModal)

Vue.mixin({
  methods: {
    isMobile() {
      return /iPhone|iPad|iPod|Android/i.test(navigator.userAgent);
    },
    convertTimestamp(timestamp) {
      var a = new Date(timestamp);
      var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
      var year = a.getFullYear();
      var month = months[a.getMonth()];
      var date = a.getDate();
      var hour = a.getHours();
      var min = a.getMinutes();
      var sec = a.getSeconds();
      var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
      return time;
    }
  }
})

Vue.directive('click-outside', clickOutside)
import axios from 'axios'
Vue.config.productionTip = false
Vue.prototype.$axios = axios;
const token = localStorage.getItem('token')
const user = localStorage.getItem('user')
console.log("Token", token)
Vue.prototype.$axios.defaults.baseURL = 'https://team-dance-community.herokuapp.com';

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
