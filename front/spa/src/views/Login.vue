<template>
	<div class="form">
		<h1 style="text-align: center">GRTS - MMS</h1>
		<el-form
			ref="form"
			:model="user"
			:rules="rules"
			@keydown.native.enter="handleLogin"
		>
			<el-form-item prop="username">
				<el-input v-model="user.username" placeholder="帐号"></el-input>
			</el-form-item>
			<el-form-item prop="password">
				<el-input
					v-model="user.password"
					placeholder="密码"
					show-password
				></el-input>
			</el-form-item>
			<el-form-item>
				<el-button @click="handleLogin" type="primary" style="width: 100%">
					登录
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
export default {
	data() {
		return {
			user: {
				username: "",
				password: "",
			},
			rules: {
				username: [{ required: true, message: "请输入帐号", trigger: "blur" }],
				password: [{ required: true, message: "请输入密码", trigger: "blur" }],
			},
		};
	},
	methods: {
		handleLogin() {
			console.log(this.$store);
			// 验证表单数据
			// this.$refs.form.validate((valid) => {
			// 	if (!valid) return;
				this.axios
					// 发送post请求，把this.user作为json请求体
					.post("/login", this.user)
					// 响应状态码为200时，执行then逻辑
					.then((res) => {
						this.$store.commit("setCurrentUser", res.data);
						this.$router.push("/home");
					})
					// 响应状态码为其它时(401, 500...)，执行catch逻辑
					.catch(() => {
						this.$message.error("登录失败，请检查帐号或密码");
					});
			// });
		},
	},
};
</script>

<style scoped>
.form {
	width: 400px;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
}
</style>
