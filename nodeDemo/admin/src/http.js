import axios from 'axios'

const http = axios.create({
    //创建后台接口地址
    baseURL: 'http://localhost:3000/admin/api'
})

//将这个变量导出
export default http