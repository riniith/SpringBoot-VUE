<template>
	<div>
		<div class="container">
			<el-form ref="form" :model="customer" :rules="rules" label-width="100px">
				<el-form-item label="客户名称" prop="name">
					<el-input v-model="customer.name"></el-input>
				</el-form-item>
				<el-form-item label="性别" prop="gender">
					<el-radio-group v-model="customer.gender">
						<el-radio label="MALE">男</el-radio>
						<el-radio label="FEMALE">女</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="地址" prop="address">
					<el-input v-model="customer.address"></el-input>
				</el-form-item>
				<el-form-item label="电话" prop="phone">
					<el-input v-model="customer.phone"></el-input>
				</el-form-item>
				<el-form-item label="客户来源" prop="source">
					<select-source v-model="customer.source"></select-source>
				</el-form-item>
				<el-form-item label="客户状态" prop="state">
					<select-state v-model="customer.state"></select-state>
				</el-form-item>
				<el-form-item>
					<el-button
						@click="handleSave"
						type="primary"
						icon="el-icon-circle-check"
					>
						确认无误，保存客户信息
					</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
import SelectSource from "../components/SelectSource.vue";
import SelectState from "./SelectState.vue";
export default {
	components: { SelectSource, SelectState },
	props: {
		currentCust: {
			type: Object,
			required: true,
		},
	},
	data() {
		return {
			customer: this.currentCust,
			rules: {
				name: [{ required: true, message: "请输入客户名称", trigger: "blur" }],
				phone: [
					{ required: true, message: "请输入电话号码", trigger: "blur" },
					{
						pattern:
							"^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$",
						message: "请输入格式正确的电话号码",
						trigger: "blur",
					},
				],
			},
		};
	},
	methods: {
		handleSave() {
			// 发起ajax请求
			this.axios.put("/customer", this.customer).then(() => {
				this.$message.success("数据更新成功");
			});
		},
	},
};
</script>

<style scoped></style>
