<template>
	<div>
		<el-row :gutter="10">
			<el-col :span="10">
				<el-form
					ref="form"
					:model="followUp"
					:rules="rules"
					label-width="80px"
					label-position="top"
				>
					<el-form-item label="客户状态" prop="customerState">
						<el-select
							v-model="followUp.customerState"
							placeholder="请选择状态"
							clearable
							style="width: 100%"
						>
							<el-option label="意向不明" value="UNKNOWN"></el-option>
							<el-option label="有意向" value="INTENTIONAL"></el-option>
							<el-option label="已成交" value="DEALED"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="跟进日期" prop="followUpDate">
						<el-date-picker
							v-model="followUp.followUpDate"
							type="date"
							placeholder="请选择跟进日期"
							style="width: 100%"
						></el-date-picker>
					</el-form-item>
					<el-form-item label="跟进情况说明" prop="followUpNote">
						<el-input
							type="textarea"
							v-model="followUp.followUpNote"
						></el-input>
					</el-form-item>
					<el-form-item label="">
						<el-button type="primary" @click="handleSave">保存跟进</el-button>
					</el-form-item>
				</el-form>
			</el-col>
			<el-col :span="14">
				<el-timeline>
					<el-timeline-item
						v-for="h in history"
						:key="h.followUpId"
						:timestamp="h.followUpDate"
					>
						{{ h.followUpNote }}
					</el-timeline-item>
				</el-timeline>
			</el-col>
		</el-row>
	</div>
</template>

<script>
export default {
	// 对父组暴露属性，这使得父组件可以传递数据给子组件
	props: {
		// 属性：当前客户对象，类型为Object，父组件必须传值
		currentCust: {
			type: Object,
			required: true,
		},
	},
	data() {
		return {
			// 新的跟进
			followUp: {
				followUpDate: null,
				followUpUsername: "",
				customerState: "",
				custId: 0,
				followUpNote: "",
			},
			rules: {
				followUpDate: [
					{ required: true, message: "请选择跟进日期", trigger: "blur" },
				],
				followUpNote: [
					{ required: true, message: "请输入跟进情况说明", trigger: "blur" },
				],
			},
			// 历史跟进记录
			history: [],
		};
	},
	created() {
		// 设置客户的当前状态，这样下拉列表会默认选中
		this.followUp.customerState = this.currentCust.state;
		// 请求获取历史跟进记录
		this.getHistory();
	},
	methods: {
		getHistory() {
			this.axios.get(`/followup/${this.currentCust.custId}`).then((res) => {
				this.history = res.data;
			});
		},
		handleSave() {
			this.$refs.form.validate((valid) => {
				if (!valid) return;
				// 请求保存新的跟进
				this.followUp.custId = this.currentCust.custId;
				this.followUp.followUpUsername = this.$store.state.currentUser.username;
				this.axios.post("/followup", this.followUp).then(this.getHistory);
			});
		},
	},
};
</script>

<style></style>
