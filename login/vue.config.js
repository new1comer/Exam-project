const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
      host:"localhost",
      port:"8888",
      proxy:{
        'server':{
          target:'http://localhost:8081',
          ws:true,
          pathRewrite:{
            '^/server':''
          }
        }
      }
  }
})
