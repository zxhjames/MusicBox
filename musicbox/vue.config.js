/*
 *Vue-CLI项目的核心配置文件
 */
const webpack = require("webpack");

module.exports = {
    devServer: {
        proxy: {
            "/api": {
                target: "http://192.168.0.105/api", // 改成自己的目标服务器
                secure: true, //接受对方是https的接口
                changeOrigin: true
            }
        }

    },
    // },
    configureWebpack: {
        plugins: [
            new webpack.ProvidePlugin({
                $: "jquery",
                jQuery: "jquery",
                "window.jQuery": "jquery",
                Popper: ["popper.js", "default"]
            })
        ]
    }
};