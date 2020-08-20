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
        //自适应
        config.module
            .rule('sass') //如果你用的是sass就写sass，我用的是less所以就写less
            .oneOf('vue')
            .use('px2rem-loader')
            .loader('px2rem-loader')
            .before('postcss-loader') // this makes it work.
            .options({ remUnit: 192, remPrecision: 8 }) // remUnit: 192代表以1920px为整体，如果设计稿的尺寸是750px，这里的值为75
            .end()
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
