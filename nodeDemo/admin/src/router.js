import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from './views/Home.vue'
import Main from './views/Main.vue'
import Login from './views/Login.vue'
import CategoryEdit from './views/CategoryEdit.vue'
import CategoryList from './views/CategoryList.vue'
import ItemEdit from './views/ItemEdit.vue'
import ItemList from './views/ItemList.vue'
import HeroEdit from './views/HeroEdit.vue'
import HeroList from './views/HeroList.vue'
import ArticleEdit from './views/ArticleEdit.vue'
import ArticleList from './views/ArticleList.vue'
import AdEdit from './views/AdEdit.vue'
import AdList from './views/AdList.vue'
import AdminUserEdit from './views/AdminUserEdit.vue'
import AdminUserList from './views/AdminUserList.vue'
Vue.use(VueRouter)

export default new VueRouter({
    routes: [
        /* 登录界面路由 */
        {
            path: '/login',
            name: 'login',
            component: Login
        },
        /* 显示后台页面的路由 */
        {
            path: '/',
            name: 'main',
            component: Main,
            children: [{
                    /* 添加子路由 */
                    path: '/categories/create',
                    component: CategoryEdit
                }, {
                    /* 添加子路由 */
                    path: '/categories/list',
                    component: CategoryList
                }, {
                    path: '/categories/edit/:id',
                    component: CategoryEdit,
                    props: true //注入到Categories页面里面
                },



                {
                    /* 添加子路由 */
                    path: '/items/create',
                    component: ItemEdit
                }, {
                    /* 添加子路由 */
                    path: '/items/list',
                    component: ItemList
                }, {
                    path: '/items/edit/:id',
                    component: ItemEdit,
                    props: true
                },



                {
                    /* 添加子路由 */
                    path: '/heroes/create',
                    component: HeroEdit
                }, {
                    /* 添加子路由 */
                    path: '/heroes/list',
                    component: HeroList
                }, {
                    path: '/heroes/edit/:id',
                    component: HeroEdit,
                    props: true //注入到Categories页面里面
                },


                {
                    /* 添加子路由 */
                    path: '/articles/create',
                    component: ArticleEdit
                }, {
                    /* 添加子路由 */
                    path: '/articles/list',
                    component: ArticleList
                }, {
                    path: '/articles/edit/:id',
                    component: ArticleEdit,
                    props: true //注入到Categories页面里面
                },


                {
                    /* 添加子路由 */
                    path: '/ads/create',
                    component: AdEdit
                }, {
                    /* 添加子路由 */
                    path: '/ads/list',
                    component: AdList
                }, {
                    path: '/ads/edit/:id',
                    component: AdEdit,
                    props: true //注入到Categories页面里面
                },



                {
                    /* 添加子路由 */
                    path: '/admin_users/create',
                    component: AdminUserEdit
                }, {
                    /* 添加子路由 */
                    path: '/admin_users/list',
                    component: AdminUserList
                }, {
                    path: '/admin_users/edit/:id',
                    component: AdminUserEdit,
                    props: true //注入到Categories页面里面
                }
            ]
        },
    ]
})