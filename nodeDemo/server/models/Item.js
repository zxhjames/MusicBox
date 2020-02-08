const mongoose = require("mongoose")

//定义一个模型的字段(物品)
const schema = new mongoose.Schema({
    name: {
        type: String
    },
    icon: {
        type: String
    }
})
//导出一个模型
module.exports = mongoose.model('Item', schema)