/* 表示路由的index.js */
//导出一个函数
module.exports = app => {

    const express = require("express") /* 引入express模块 */
    const router = express.Router() /* 定义子路由 */
    const Category = require("../../models/Category")
    //新增分类
    router.post('/categories', async (req, res) => {
        const model = await Category.create(req.body)
        res.send(model)
    })
    //显示所有分类
    router.get('/categories', async (req, res) => {
        //关联到parent
        const items = await Category.find().populate('parent').limit(10)
        res.send(items)
    })
    //删除分类
    router.delete('/categories/:id', async (req, res) => {
        //删除并且返回一个状态码
        await Category.findByIdAndDelete(req.params.id)
        res.send({
            status: true
        })
    })
    //获取详情页面
    router.get('/categories/:id', async (req, res) => {
        const model = await Category.findById(req.params.id)
        res.send(model)
    })
    //更新否个问题
    router.put('/categories/:id', async (req, res) => {
        const model = await Category.findByIdAndUpdate(req.params.id, req.body);
        res.send(model);
    })
    app.use('/admin/api', router)
}