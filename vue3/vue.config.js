const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})


// 关闭eslint
module.exports = {
  lintOnSave: false,
}
