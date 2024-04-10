<template>
	<div>
		<div>
			<el-button type="primary" size="small" @click="dialogVisible = true">
				<i class="el-icon-plus"></i>
				创建新用户
			</el-button>
			<el-dialog title="创建新用户" :visible.sync="dialogVisible" width="500">
				<el-form ref="form" :model="user" :rules="rules" label-width="80px">
					<el-form-item label="帐号" prop="username">
						<el-input v-model="user.username"></el-input>
					</el-form-item>
					<el-form-item label="角色" prop="role">
						<el-select
							v-model="user.role"
							placeholder="请选择角色"
							style="width: 100%"
						>
							<el-option label="NORMAL" value="NORMAL"></el-option>
							<el-option label="MANAGER" value="MANAGER"></el-option>
							<el-option label="USERMANAGER" value="USERMANAGER"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="直属领导">
						<SelectUser v-model="user.manager"></SelectUser>
					</el-form-item>
					<el-form-item>
						<el-button
							@click="handleCreate"
							type="primary"
							class="el-icon-success"
						>
							确定，创建用户
						</el-button>
					</el-form-item>
				</el-form>
			</el-dialog>
		</div>
		<el-table :data="users" style="width: 100%">
			<el-table-column prop="username" label="帐号"></el-table-column>
			<el-table-column prop="enabled" label="状态">
				<template slot-scope="scope">
					<el-tag type="primary" v-if="scope.row.enabled">正常</el-tag>
					<el-tag type="danger" v-else>禁用</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="role" label="角色"></el-table-column>
			<el-table-column prop="manager" label="直属领导"></el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button
						type="primary"
						size="mini"
						v-if="!scope.row.enabled"
						@click="handleEnable(scope.row.username)"
					>
						启用
					</el-button>
					<el-button
						type="danger"
						size="mini"
						v-else
						@click="handleDisable(scope.row.username)"
					>
						禁用
					</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
import SelectUser from "@/components/SelectUser.vue";
export default {
	components: { SelectUser },
	data() {
		return {
			users: [], // => 对应后端的List<User>
			dialogVisible: false,
			user: {
				username: "",
				role: "",
				manager: "",
			},
			rules: {
				username: [
					{ required: true, message: "请输入帐号", trigger: "blur" },
					{
						validator: (rule, value, callback) => {
							this.axios
								.get(`/user/check/${this.user.username}`)
								.then((res) => {
									if (res.data === true) {
										callback();
									} else {
										callback(new Error("这个帐号已存在"));
									}
								});
						},
						trigger: "blur",
					},
				],
				role: [{ required: true, message: "请选择角色", trigger: "blur" }],
			},
		};
	},
	created() {
		this.getUsers();
	},
	methods: {
		async getUsers() {
			this.axios.get("/user").then((res) => {
				this.users = res.data;
			});
		},
		handleDisable(username) {
			this.axios.put(`/user/disable/${username}`).then(() => {
				this.$message.success("已成功禁用" + username);
				this.getUsers();
			});
		},
		handleEnable(username) {
			this.axios.put(`/user/enable/${username}`).then(() => {
				this.$message.success("已成功启用" + username);
				this.getUsers();
			});
		},
		handleCreate() {
			this.$refs.form.validate((valid) => {
				if (!valid) return;
				this.axios.post("/user", this.user).then(() => {
					this.$message.success("创建用户成功");
					this.dialogVisible = false;
					this.getUsers();
				});
			});
		},
	},
};
</script>

<style></style>
