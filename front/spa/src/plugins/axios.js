"use strict";

import Vue from "vue";
import axios from "axios";

// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
	// baseURL: process.env.baseURL || process.env.apiUrl || ""
	// timeout: 60 * 1000, // Timeout
	// withCredentials: true, // Check cross-site Access-Control
	baseURL: "http://localhost:8080",
};

const _axios = axios.create(config);

// 请求拦截器
_axios.interceptors.request.use(
	function (config) {
		// Do something before request is sent
		return config;
	},
	function (error) {
		// Do something with request error
		return Promise.reject(error);
	}
);

// 响应拦截器
_axios.interceptors.response.use(
	function (response) {
		// Do something with response data
		return response;
	},
	function (error) {
		// Do something with response error
		return Promise.reject(error);
	}
);

// 把axios注册为Vue的插件
// 目的：在各个.vue文件中，不再需要import，直接通过this.axios就可以调用axios的方法了！
Plugin.install = function (Vue) {
	Vue.axios = _axios;
	window.axios = _axios;
	Object.defineProperties(Vue.prototype, {
		axios: {
			get() {
				return _axios;
			},
		},
		$axios: {
			get() {
				return _axios;
			},
		},
	});
};

Vue.use(Plugin);

export default Plugin;
