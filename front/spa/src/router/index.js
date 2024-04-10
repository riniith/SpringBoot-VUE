import Vue from "vue";
import VueRouter from "vue-router";
// import Login from "../views/Login.vue";
// import store from "../store";
Vue.use(VueRouter);

const routes = [
	// {
	// 	path: "/",
	// 	name: "Login",
	// 	component: Login,
	// },
	{
		path: "/",
		name: "Home",
		// 二级路由
		component: () => import("../views/Home.vue"),
		children: [
			{
				path: "/customer/create",
				component: () => import("../views/customer/Create.vue"),
			},
			{
				path: "/customer/list",
				
				component: () => import("../views/customer/List.vue"),
			},
			{
				path: "/customer/detail",
				
				component: () => import("../views/customer/Detail.vue"),
			},
			{
				path: "/customer/update",
				name: "customerUpdate",
				component: () => import("../views/customer/Update.vue"),
			},
			{
				path: "/user/pwd",
				component: () => import("../views/user/Password.vue"),
			},
			{
				path: "/user/manage",
				component: () => import("../views/user/UserManage.vue"),
			},
			{
				path: "/dashboard",
				component: () => import("../views/statistics/Dashboard.vue"),
			},
		],
	},
];

const router = new VueRouter({
	routes,
});

// router.beforeEach((to, from, next) => {
// 	if (to.name !== "Login" && !store.state.currentUser.username) {
// 		next({ name: "Login" });
// 	} else {
// 		next();
// 	}
// });

export default router;
