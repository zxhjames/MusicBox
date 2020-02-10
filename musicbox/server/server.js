const express = require('express')
const app = express()
app.use(require('cors')())
app.use(express.json())
const api = 'localhost:3000'
//处理登录接口,返回用户个人数据
app.post(api + '/login', async (req, res) => {
    // const article = await Article.create(req.body)
    console.log(req.params.name)
})

app.listen(3001, () => {
    console.log('http://localhost:3001')
})