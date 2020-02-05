const express = require("express")
const app = express()
app.use(express.json())
//引入跨域模块
app.use(require('cors')())
/* 引用连接数据库函数 */
require('./plugins/db')(app)
//引用api函数
require('./routes/admin')(app)
<<<<<<< HEAD
//采用静态文件托管
app.use('/uploads', express.static(__dirname + '/uploads'))
=======
>>>>>>> origin/master
//监听3000端口
app.listen(3000, () => {
    console.log('App listenning on port http://localhost:3000');
})