const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
        '/api': {
            target: "http://localhost:8081/", // 实际跨域请求的API地址
            changeOrigin: true, // 跨域
            // 请求地址重写  http://front-end/api/login ⇒ http://api-url/login
            pathRewrite: {
                '^/api': '',
            }
        }
    },
    open: process.platform === 'darwin',
    port: 8080,
    host: 'localhost',

    https: false,

    hot: "only",
},
})

  
// 关闭eslint
module.exports = {

  lintOnSave: false,
 
}