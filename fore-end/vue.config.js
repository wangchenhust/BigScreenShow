// vue.config.js
const path = require('path');
function resolve (dir) {
    return path.join(__dirname, dir)
}
const webpack = require('webpack')
module.exports = {
    chainWebpack: config => {
        //路径配置
        config.resolve.alias
            .set('assets', resolve('src/assets'))
            .set('styles', resolve('src/assets/styles'))
    },
    // webpack-dev-server 相关配置
    devServer: {
        // 调试端口
        port: 8081
    },
    //其他配置....
    configureWebpack: {
        // jquery的配置
        plugins: [
            new webpack.ProvidePlugin({
                $:"jquery",
                jQuery:"jquery",
                "windows.jQuery":"jquery"
            })
        ]
    }
}
