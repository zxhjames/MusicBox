import axios from 'axios'
import Vue from 'vue'
import router from './router'
const http = axios.create({
    //创建后台接口地址
    baseURL: 'http://localhost:3000/admin/api'
})
Vue.config.productionTip = false
/* 增加拦截器 */
http.interceptors.request.use(function (config) {
    // Do something before request is sent
    if (localStorage.token) {
        config.headers.Authorization = 'Bearer ' + (localStorage.token || '')
    }
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});


http.interceptors.response.use(res => {
    return res
}, err => {
    if (err.response.data.message) {
        //直接弹出错误
        Vue.prototype.$message({
            type: 'error',
            message: err.response.data.message
        })
        if (err.response.status == 401) {
            // eslint-disable-next-line no-console
            router.push('/login')
        }
    }

    return Promise.reject(err)
})
//将这个变量导出
export default http