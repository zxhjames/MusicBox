//sudo npm install npm -g && sudo npm install express@next && sudo nodemon server.js
//接受models传来的实例
const {
    User
} = require('../express/models')
const express = require('express')
const app = express()
app.use(express.json())

app.get('/api/users', async (req, res) => {
    //查找用户
    const users = await User.find()
    res.send(users)
})


//注册用户
app.post('/api/register', async (req, res) => {
    //创建用户实例
    const user = await User.create({
        username: req.body.username,
        password: req.body.password,
    })
    //回调json
    res.send(user)
})

//监听后回调
app.listen(3001, () => {
    console.log('http://localhost:3001')
})