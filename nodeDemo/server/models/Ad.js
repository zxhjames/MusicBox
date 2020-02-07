const mongoose = require("mongoose")

//定义一个模型的字段(广告)
const schema = new mongoose.Schema({
    /* 广告位的名字 */
    name: {
        type: String
    },
    /* 每一个子广告的名字 */
    items: [{
        image: {
            type: String
        },
        url: {
            type: String
        }
    }]

})


//导出一个模型
module.exports = mongoose.model('Ad', schema)