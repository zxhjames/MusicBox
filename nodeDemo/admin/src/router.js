import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from './views/Home.vue'
import Main from './views/Main.vue'
import CategoryEdit from './views/CategoryEdit.vue'
import CategoryList from './views/CategoryList.vue'

Vue.use(VueRouter)

export default new VueRouter({
    routes: [{
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
        }]
    }, ]
})