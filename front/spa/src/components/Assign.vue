<template>
	<div class="container">
		<el-button type="info">原负责人：{{ oldOwnerUsername }}</el-button>
		<i class="el-icon-right" style="font-size: 30px"></i>
		<SelectUser
			v-model="newOwnerUsername"
			:exclude="oldOwnerUsername"
		></SelectUser>
		<el-button type="primary" @click="handleClick">确定指派</el-button>
	</div>
</template>

<script>
import SelectUser from "./SelectUser.vue";
export default {
	components: { SelectUser },
	props: {
		currentCust: {
			type: Object,
			required: true,
		},
	},
	data() {
		return {
			newOwnerUsername: "",
		};
	},
	computed: {
		// 原负责人
		oldOwnerUsername() {
			return this.currentCust.ownerUsername;
		},
		// 排除了原负责人的用户列表
		usersExcluedSelf() {
			return this.users.filter(
				(item) => item.username !== this.oldOwnerUsername
			);
		},
	},
	methods: {
		handleClick() {
			this.axios
				.put(
					`/customer/assign/${this.currentCust.custId}/${this.newOwnerUsername}`
				)
				.then(() => {
					this.$message.success("指派成功");
					// 更新当前客户对象的负责人属性
					this.currentCust.ownerUsername = this.newOwnerUsername;
					// 清除下拉列表的当前选项
					this.newOwnerUsername = "";
				});
		},
	},
};
</script>

<style scoped>
.container {
	display: flex;
	gap: 20px;
	align-items: center;
}
</style>
