const mongoose = require("mongoose")

//定义一个模型的字段(分类)
const schema = new mongoose.Schema({
    name: {
        type: String
    },
    //关联主键id
    parent: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Category'
    },
})


//导出一个模型
module.exports = mongoose.model('Category', schema)