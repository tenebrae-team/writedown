var MiniCssExtractPlugin = require('mini-css-extract-plugin');
config.plugins.push(new MiniCssExtractPlugin({
    filename: '[name].bundle.css',
    chunkFilename: '[id].bundle.css'
}));

config.module.rules.push({ test: /\.css$/, use: [ {
        loader: MiniCssExtractPlugin.loader
    }, 'css-loader' ] });
config.resolve.modules.push("../src/jvmMain/css");