import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import router from './router'
import http from './http'
Vue.config.productionTip = false

/* 建立一个全局的数据接口 */
Vue.prototype.$http = http

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')