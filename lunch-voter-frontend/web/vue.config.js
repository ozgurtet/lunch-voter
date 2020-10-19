module.exports = {
  publicPath: '/lunch-voter',
  productionSourceMap: true,
  devServer: {
    proxy: 'http://localhost:8085'
  }
}
