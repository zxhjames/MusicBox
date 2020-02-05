import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from './views/Home.vue'
import Main from './views/Main.vue'
import CategoryEdit from './views/CategoryEdit.vue'
import CategoryList from './views/CategoryList.vue'
<<<<<<< HEAD
import ItemEdit from './views/ItemEdit.vue'
import ItemList from './views/ItemList.vue'
import HeroEdit from './views/HeroEdit.vue'
import HeroList from './views/HeroList.vue'
=======

>>>>>>> origin/master
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
<<<<<<< HEAD
        }, {
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
        }, {
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
=======
>>>>>>> origin/master
        }]
    }, ]
})