import Vue from 'vue'
import Router from 'vue-router'
// 引用views里的文件
import Login from '../views/Login.vue'
import Main from '.././views/Main.vue'
import Home from '../views/Home.vue'
import Album from '../views/songs/Album.vue'
import Singer from '../views/songs/Singer.vue'
import SearchList from '../views/songs/SearchList.vue'

import Community from '../views/Community.vue'
Vue.use(Router)

export default new Router({
  routes: [{
    /* 根目录重定向到登录页面 */
    path: '/',
    name: 'root',
    redirect: '/Login'
  }, {
    //登录页面
    path: '/Login',
    name: 'Login',
    component: Login
  }, {
    //主页
    path: '/Main',
    name: 'Main',
    component: Main,
    children: [
      //首页
      {
        path: '/main',
        name: 'main',
        component: Home
      }, {
        path: '/album',
        name: 'album',
        component: Album
      }, {
        path: '/singer',
        name: 'singer',
        component: Singer
      }, {
        path: '/searchList',
        name: 'searchList',
        component: SearchList
      }, {
        //社区主页
        path: '/Community',
        name: Community,
        component: Community
      }
    ]
  }]
})