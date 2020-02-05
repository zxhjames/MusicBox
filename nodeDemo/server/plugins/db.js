/* 导出链接数据库的函数 */
module.exports = app => {
    const mongoose = require("mongoose")
    mongoose.connect('mongodb://localhost:27017/hhh', {
        useNewUrlParser: true,
        useFindAndModify: true,
        useCreateIndex: true,
        useUnifiedTopology: true
    }, function (err) {
        if (err) {
            console.log(err);
        } else {
            console.log('数据库连接成功');
        }
    })
}