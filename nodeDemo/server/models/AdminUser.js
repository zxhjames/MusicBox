const mongoose = require("mongoose")

//定义一个模型的字段(管理员)
const schema = new mongoose.Schema({
    username: {
        type: String
    },
    password: {
        type: String,
        select: false /* 禁止密码查找 */ ,
        set(val) {
            /* 进行哈希加密 */
            return require('bcrypt').hashSync(val, 10)
        }
    }
})


//导出一个模型
module.exports = mongoose.model('AdminUser', schema)