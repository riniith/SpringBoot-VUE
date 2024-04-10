<template>
	<div class="container">
		<h2>修改密码</h2>
		<el-form
			ref="form"
			:model="passwordModel"
			:rules="rules"
			label-width="80px"
			label-position="top"
		>
			<el-form-item label="旧密码" prop="oldPassword">
				<el-input v-model="passwordModel.oldPassword" show-password></el-input>
			</el-form-item>
			<el-form-item label="新密码" prop="newPassword">
				<el-input v-model="passwordModel.newPassword" show-password></el-input>
			</el-form-item>
			<el-form-item label="确认新密码" prop="newPasswordConfirm">
				<el-input
					v-model="passwordModel.newPasswordConfirm"
					show-password
				></el-input>
			</el-form-item>
			<el-form-item>
				<el-button @click="handleClick" type="primary" class="el-icon-success">
					确定，更新密码
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
export default {
	data() {
		return {
			passwordModel: {
				username: this.$store.state.currentUser.username,
				oldPassword: "",
				newPassword: "",
				newPasswordConfirm: "",
			},
			rules: {
				oldPassword: [
					{ required: true, message: "请输入旧密码", trigger: "blur" },
				],
				newPassword: [
					{ required: true, message: "请输入新密码", trigger: "blur" },
					{
						// 自定义验证
						validator: (rule, value, callback) => {
							// 调用newPasswordConfirm的验证规则
							this.$refs.form.validateField("newPasswordConfirm");
							callback();
						},
						trigger: "blur",
					},
				],
				newPasswordConfirm: [
					{ required: true, message: "请再次输入新密码", trigger: "blur" },
					{
						// 自定义验证
						validator: (rule, value, callback) => {
							if (value !== this.passwordModel.newPassword) {
								callback(new Error("两次输入的新密码不一致"));
							} else {
								callback();
							}
						},
						trigger: "blur",
					},
				],
			},
		};
	},
	methods: {
		handleClick() {
			this.$refs.form.validate((valid) => {
				if (!valid) return;
				this.axios
					.put("/user/pwd", this.passwordModel)
					.then(() => {
						this.$message.success("密码更新成功");
						this.passwordModel = {};
					})
					.catch(() => {
						this.$message.error("旧密码不正确");
					});
			});
		},
	},
};
</script>

<style scoped>
.container {
	width: 50%;
	margin-left: auto;
	margin-right: auto;
}
</style>
