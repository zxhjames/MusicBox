const express = require("express")
const app = express()
/* 全局token秘钥 */
app.set('secret', 'i282349nsn434b')
app.use(express.json())
//引入跨域模块
app.use(require('cors')())
/* 引用连接数据库函数 */
require('./plugins/db')(app)
//引用api函数
require('./routes/admin')(app)
//采用静态文件托管
app.use('/uploads', express.static(__dirname + '/uploads'))
//监听3000端口
app.listen(3000, () => {
    console.log('App listenning on port http://localhost:3000');
})