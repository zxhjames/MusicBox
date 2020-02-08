import Vue from 'vue'
import App from './App.vue'
import './assets/iconfont/iconfont.css'
import router from './router/index'
Vue.config.productionTip = false
import './assets/scss/style.scss'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/dist/css/swiper.css'
//定义插槽
import Card from './components/Card.vue'
Vue.component('m-card', Card)
import ListCard from './components/ListCard.vue'
Vue.component('m-list-card', ListCard)
Vue.use(VueAwesomeSwiper, /* { default global options } */ )
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')