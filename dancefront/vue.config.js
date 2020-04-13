module.exports = {
  devServer: {
    host: '0.0.0.0',
// 
},
pwa: {
  workboxOptions: {
      skipWaiting: true
  }
},
  chainWebpack: config => {
    config.module.rules.delete('eslint');
  },
  lintOnSave: false,
  css: {
    loaderOptions: {
      // pass options to sass-loader
      // @/ is an alias to src/
      // so this assumes you have a file named `src/variables.sass`
      // Note: this option is named as "data" in sass-loader v7
  
      // by default the `sass` option will apply to both syntaxes
      // because `scss` syntax is also processed by sass-loader underlyingly
      // but when configuring the `data` option
      // `scss` syntax requires an semicolon at the end of a statement, while `sass` syntax requires none
      // in that case, we can target the `scss` syntax separately using the `scss` option
      scss: {
          prependData: `@import "@/scss/_mixins.scss";`
      }
      // pass Less.js Options to less-loader
     
      }
    }
  }
