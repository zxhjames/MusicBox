import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
//引入axios
import axios from 'axios'
Vue.prototype.$http = axios.create({
  //定义后端接口的根地址
  baseURL: 'http://localhost:3001/api'
})
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')