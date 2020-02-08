import Vue from 'vue'
import Home from '../views/Home.vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'main',
    component: Main,
    // 子路由
    children: [
      //首页
      {
        path: '/',
        name: 'home',
        component: Home
      }

      //攻略中心

    ]
  },

]

const router = new VueRouter({
  routes
})

export default router