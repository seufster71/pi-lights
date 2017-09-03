var webpack = require('webpack');
const path = require('path');

var BUILD_DIR = path.resolve(__dirname, 'src/main/resources/static/build');
var APP_DIR = path.resolve(__dirname, 'src/main/js');

module.exports = {
	entry: {
		app: APP_DIR + '/index.js'
	},
	devtool: 'inline-source-map',
	devServer: {
		contentBase: './src/main/resources/static'
	},
	output: {
	    path: path.resolve(__dirname, 'src/main/resources/static/build'),
	    filename: 'bundle.js',
	    publicPath: 'build/'
	},
	module : {
		loaders : [
			{
				test : /\.js?/,
				include: APP_DIR,
				loader: 'eslint-loader'
			},
	      {
	        test : /\.js?/,
	        include : APP_DIR,
	        loader : 'babel-loader',
	        query: {
	                    presets: ['es2015', 'react']
	                }
	      }
	    ]
	}
};

