var path = require('path')
var utils = require('./utils')
var config = require('../config')
var vueLoaderConfig = require('./vue-loader.conf')
var webpack = require('webpack');

function resolve (dir) {
  return path.join(__dirname, '..', dir)
}

module.exports = {
  entry: {
    app: './src/main.js'
  },
  output: {
    path: config.build.assetsRoot,
    filename: '[name].js',
    publicPath: process.env.NODE_ENV === 'production'
      ? config.build.assetsPublicPath
      : config.dev.assetsPublicPath
  },
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      'vue$': 'vue/dist/vue.esm.js',
      'jquery': 'jquery',
      '@': resolve('src'),
      // 'jsBezier':  path.resolve(__dirname, '../src/vendor/jsBezier-0.7.js'),
      // 'mottle':  path.resolve(__dirname, '../src/vendor/mottle-0.7.1.js'),
      // 'biltong':  path.resolve(__dirname, '../src/vendor/biltong-0.2.js'),
      // 'katavorio':  path.resolve(__dirname, '../src/vendor/katavorio-0.13.0.js'),
      // 'util':  path.resolve(__dirname, '../src/vendor/util.js'),
      // 'browser':  path.resolve(__dirname, '../src/vendor/browser-util.js'),
      // 'domAdapter':  path.resolve(__dirname, '../src/vendor/dom-adapter.js'),
      // 'overlay':  path.resolve(__dirname, '../src/vendor/overlay-component.js'),
      // 'endpoint':  path.resolve(__dirname, '../src/vendor/endpoint.js'),
      // 'connection':  path.resolve(__dirname, '../src/vendor/connection.js'),
      // 'anchors':  path.resolve(__dirname, '../src/vendor/anchors.js'),
      // 'defaults':  path.resolve(__dirname, '../src/vendor/defaults.js'),
      // 'connectorsBezier':  path.resolve(__dirname, '../src/vendor/connectors-bezier.js'),
      // 'connectorsStatemachine':  path.resolve(__dirname, '../src/vendor/connectors-statemachine.js'),
      // 'connectorsFlowchart':  path.resolve(__dirname, '../src/vendor/connectors-flowchart.js'),
      // 'renderersSvg':  path.resolve(__dirname, '../src/vendor/renderers-svg.js'),
      // 'base':  path.resolve(__dirname, '../src/vendor/base-library-adapter.js'),
      // 'domjsPlumb':  path.resolve(__dirname, '../src/vendor/dom.jsPlumb.js'),
      // 'demo':  path.resolve(__dirname, '../src/vendor/demo.js'),
    }
  },
  // 增加一个plugins
  plugins: [
     new webpack.ProvidePlugin({
         $: "jquery",
         jQuery: "jquery",
     })
  ],
  module: {
    rules: [
      // {
      //   test: /\.(js|vue)$/,
      //   loader: 'eslint-loader',
      //   enforce: 'pre',
      //   include: [resolve('src'), resolve('test')],
      //   options: {
      //     formatter: require('eslint-friendly-formatter')
      //   }
      // },
      {
        test: /\.vue$/,
        loader: 'vue-loader',
        options: vueLoaderConfig
      },
      {
        test: /\.js$/,
        loader: 'babel-loader',
        include: [resolve('src'), resolve('test')]
      },
      {
        test: /\.svg$/,
        loader: 'svg-sprite-loader',
        include: [resolve('src/icons')],
        options: {
          symbolId: 'icon-[name]'
        }
      },
      {
        test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
        loader: 'url-loader',
        exclude: [resolve('src/icons')],
        options: {
          limit: 10000,
          name: utils.assetsPath('img/[name].[hash:7].[ext]')
        }
      },
      {
        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
          name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
        }
      }
    ]
  }
}
