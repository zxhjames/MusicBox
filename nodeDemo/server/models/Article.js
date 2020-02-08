const mongoose = require("mongoose")

//定义一个模型的字段(文章)
const schema = new mongoose.Schema({
    /* 标题 */
    title: {
        type: String
    },
    /* 分类 */
    categories: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Category'
    }],
    /* 详情 */
    body: {
        type: String
    }
})


//导出一个模型
module.exports = mongoose.model('Article', schema)