import Vue from "vue";
import "./plugins/axios";
import App from "./App.vue";
import router from "./router";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import store from "./store";

Vue.config.productionTip = false;

// 全局注册ElementUI提供的各个组件，这样就不用在我们的其它vue文件中用import的方式来导入组件了
Vue.use(ElementUI);

// 这个Vue实例是单页应用的根组件：Root
new Vue({
	router,
	store,
	render: (h) => h(App),
}).$mount("#app");
