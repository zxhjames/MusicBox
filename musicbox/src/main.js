import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
Vue.prototype.$http = axios.create({
  //定义后端接口的根地址
  baseURL: 'http://localhost:3000'
})

//引入 npm i element-ui -S
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
Vue.use(ElementUI)

import './assets/iconfont/iconfont.css'
Vue.config.productionTip = false
import './assets/scss/style.scss'
import VueAwesomeSwiper from 'vue-awesome-swiper'
Vue.use(VueAwesomeSwiper, /* { default global options } */ )
import 'swiper/dist/css/swiper.css'
//定义插槽
import Card from './components/Card.vue'
Vue.component('m-card', Card)
import ListCard from './components/ListCard.vue'
Vue.component('m-list-card', ListCard)
import SingerPane from './components/SingerPane.vue'
Vue.component('m-singer-pane', SingerPane)



// 引入Bootstrape
// import "bootstrap"
// import 'bootstrap/dist/css/bootstrap.min.css';
// import 'bootstrap/dist/js/bootstrap.min.js';
Vue.config.productionTip = false
new Vue({
  router,
  axios,
  render: h => h(App)
}).$mount('#app')