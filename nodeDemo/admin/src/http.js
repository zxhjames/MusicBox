import axios from 'axios'
import Vue from 'vue'
const http = axios.create({
    //创建后台接口地址
    baseURL: 'http://localhost:3000/admin/api'
})
Vue.config.productionTip = false
/* 增加拦截器 */
http.interceptors.response.use(res => {
    return res
}, err => {
    if (err.response.data.message) {
        //直接弹出错误
        Vue.prototype.$message({
            type: 'error',
            message: err.response.data.message
        })
    }
    return Promise.reject(err)
})
//将这个变量导出
export default http