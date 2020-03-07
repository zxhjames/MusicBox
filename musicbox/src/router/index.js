import Vue from 'vue'
import Router from 'vue-router'
// 引用views里的文件
import Login from '../views/Login.vue'
import Main from '.././views/Main.vue'
import Home from '../views/Home.vue'
import Album from '../views/songs/Album.vue'
import Singer from '../views/songs/Singer.vue'
import SearchList from '../views/songs/SearchList.vue'
import Regist from '../views/Regist.vue'
import UserMain from '../views/user/userMain.vue'
import Community from '../views/Community.vue'
import OthersMain from '../views/others/othersMain.vue'
Vue.use(Router)

const router = new Router({
  mode: 'history',
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
    //注册页面
    path: '/regist',
    name: 'Regist',
    component: Regist
  }, {
    //主页
    path: '/Main',
    name: 'Main',
    component: Main,
    meta: {
      title: '主页',
      keepAlive: true // 需要被缓存
    },
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
        //用户主页
        path: '/UserMain',
        name: 'User',
        component: UserMain
      }, {
        path: '/Community',
        name: 'Comminuty',
        component: Community
      }, {
        path: '/OthersMain',
        name: 'OthersMain',
        component: OthersMain
      }
    ]
  }]
})

//全局守卫写在main中
//router对象调用  
//.beforeEach((进入到哪一个路由,从哪一个路由离开,对应的函数)=>{}) 
// router.beforeEach((to, form, next) => {
//   //如果进入到的路由是登录页或者注册页面，则正常展示
//   if (to.path == '/Login' || to.path == '/regist') {
//     next();
//   } else if (!JSON.parse(localStorage.getItem('usermsg')).token) {
//     alert("还没有登录，请先登录！");
//     next('/Login'); //转入login登录页面，登录成功后会将token存入localStorage
//   } else {
//     next();
//   }
// })


export default router