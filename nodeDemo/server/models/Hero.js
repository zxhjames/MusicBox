const mongoose = require("mongoose")

//定义一个模型的字段(分类)
const schema = new mongoose.Schema({
    name: {
        type: String
    },
    //头像
    avatar: {
        type: String
    },
    //称号
    title: {
        type: String
    },
    //类型(父分类),注意分类可能有多个,所以改成一个数组
    categories: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Category'
    }],
    //定义一个对象数组，存储英雄的一些属性
    scores: {
        difficult: {
            type: Number
        },
        skills: {
            type: Number
        },
        attack: {
            type: Number
        },
        survive: {
            type: Number
        }
    },
    //只要是复数,外面一定要定义成一个数组
    //英雄的特技(普攻+三个特技)
    skills: [{
        icon: {
            type: String
        },
        name: {
            type: String
        },
        description: {
            type: String
        },
        tips: {
            type: String
        }
    }],

    //出装推荐(顺风出装和逆风出装)
    items1: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Item'
    }],
    items2: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Item'
    }],
    //使用技巧
    usageTips: {
        type: String
    },
    //对抗技巧
    battleTips: {
        type: String
    },
    //团战思路
    teamTips: {
        type: String
    },
    //英雄关系
    partners: [{
        //搭档的英雄
        hero: {
            type: mongoose.Schema.Types.ObjectId,
            ref: 'Hero'
        },
        //描述
        description: {
            type: String
        }
    }]

})


//导出一个模型
module.exports = mongoose.model('Hero', schema)