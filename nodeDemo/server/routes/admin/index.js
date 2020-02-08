/* 表示路由的index.js */
//导出一个函数
module.exports = app => {

    const express = require("express") /* 引入express模块 */
    const jwt = require('jsonwebtoken')
    const AdminUser = require('../../models/AdminUser')
    const assert = require('http-assert') //npm i http-assert 引入异常处理插件
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
    router.get('/', async (req, res, next) => {
        //加入中间件
        //校验用户是否登录
        //提取Bearer token 后面的token
        const token = String(req.headers.authorization || '').split(' ').pop()
        assert(token, 401, '请提供jwt token')
        const {
            id
        } = jwt.verify(token, app.get('secret')) //解析数据返回用户id
        assert(id, 401, '无效的jwt token')
        req.user = await AdminUser.findById(id)
        assert(req.user, 401, '请先登录')
        await next()
    }, async (req, res) => {
        //关联到parent
        // console.log(req.user)
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

        const user = await AdminUser.findOne({
            username
        }).select('+password')
        //使用assert 如果用户不存在 返回异常
        assert(user, 422, '用户不存在')

        //2.校验密码
        const isValid = require('bcrypt').compareSync(password, user.password)
        assert(isValid, 422, '密码错误')
        //3.返回token
        const token = jwt.sign({
            id: user._id,
        }, app.get('secret'))
        console.log('token is ' + token)
        res.send({
            token
        })
    })

    //错误处理函数
    app.use(async (err, req, res, next) => {
        // 直接用错误码的方式返回一个错误信息
        res.status(err.statusCode || 500).send({
            message: err.message
        })
    })

}