import Vue from "vue";
import Vuex from "vuex";

// 全局注册Vuex插件
Vue.use(Vuex);

function loadCurrentUser() {
	const string = sessionStorage.getItem("CURRENT_USER");
	if (string) return JSON.parse(string);
	return {};
}

export default new Vuex.Store({
	// 定义全局状态
	state: {
		currentUser: loadCurrentUser(),
	},
	// 可看作是更新state的setter方法们
	mutations: {
		setCurrentUser(state, payload) {
			state.currentUser = payload;
			// 持久化
			sessionStorage.setItem("CURRENT_USER", JSON.stringify(payload));
		},
	},
	// action可包含异步操作
	// action不能直接变更状态，而是要提交mutation
	actions: {},
	modules: {},
});
