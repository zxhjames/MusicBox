/* 表示路由的index.js */
//导出一个函数
module.exports = app => {

    const express = require("express") /* 引入express模块 */
    const router = express.Router({
        mergeParams: true //表示合并url参数
    }) /* 定义子路由 */
    // const Category = require("../../models/Category")
    //新增分类
    router.post('/', async (req, res) => {
        //类似于注入一个动态类型去寻找
        // const Model = require(`../../models/${req.params.resource}`)
        const model = await req.Model.create(req.body)
        res.send(model)
    })
    //显示所有分类
    router.get('/', async (req, res) => {
        //关联到parent
        const queryOptions = {}
        if (req.Model.modelName == 'Category') {
            queryOptions.populate = 'parent'
        }
        const items = await req.Model.find().setOptions(queryOptions).limit(10)
        res.send(items)
    })
    //删除分类
    router.delete('/:id', async (req, res) => {
        //删除并且返回一个状态码
        await req.Model.findByIdAndDelete(req.params.id)
        res.send({
            status: true
        })
    })
    //获取详情页面
    router.get('/:id', async (req, res) => {
        const model = await req.Model.findById(req.params.id)
        res.send(model)
    })
    //更新否个问题
    router.put('/:id', async (req, res) => {
        const model = await req.Model.findByIdAndUpdate(req.params.id, req.body);
        res.send(model);
    })
    //制成动态api,中间件用来引入resource接口名
    app.use('/admin/api/rest/:resource', async (req, res, next) => {
        //引入一下 npm i inflection 可以把附属
        const modelName = require('inflection').classify(req.params.resource)
        //挂载Model
        req.Model = require(`../../models/${modelName}`)
        next()
    }, router)

    //采用静态文件托管
    app.use('/uploads', express.static(__dirname + '/uploads'))
    //在server文件夹里安装multer
    //接受文件并且上传
    const multer = require('multer')
    const upload = multer({
        dest: __dirname + '../../../uploads'
    }) //引入中间件
    app.post('/admin/api/upload', upload.single('file'), async (req, res) => {
        const file = req.file
        file.url = `http://localhost:3000/uploads/${file.filename}`
        res.send(file)
    })


    //用户登录接口
    app.post('/admin/api/login', async (req, res) => {
        //解构赋值
        const {
            username,
            password
        } = req.body
        //1.根据用户名找用户
        const AdminUser = require('../../models/AdminUser')
        const user = await AdminUser.findOne({
            username
        }).select('+password')
        if (!user) {
            /* 如果用户不存在直接返回给前台一个信息 */
            return res.status(422).send({
                message: '用户不存在'
            })
        }
        //2.校验密码
        const isValid = require('bcrypt').compareSync(password, user.password)
        if (!isValid) {
            return res.status(422).send({
                message: '密码错误'
            })
        }
        //3.返回token
        const jwt = require('jsonwebtoken')
        const token = jwt.sign({
            id: user._id,
        }, app.get('secret'))

        res.send(token)
    })


}