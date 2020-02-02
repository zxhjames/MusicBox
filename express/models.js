//引入模块
const mongoose = require('mongoose')
//链接数据库
mongoose.connect('mongodb://localhost:27017/express-auth', {
    useNewUrlParser: true
})

//定义用户模型
const UserSchema = new mongoose.Schema({
    username: {
        type: String,
        unique: true
    },
    password: {
        type: String
    },
})

//导出对象
const User = mongoose.model('User', UserSchema)
// User.db.dropCollection('users') //用于删除所有数据
module.exports = {
    User
}