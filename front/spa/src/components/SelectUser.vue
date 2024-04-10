<template>
	<el-select
		placeholder="请选择用户"
		style="width: 100%"
		clearable
		:value="value"
		@input="$emit('input', $event)"
	>
		<el-option
			v-for="user in usersExclude"
			:key="user.username"
			:label="user.username"
			:value="user.username"
		></el-option>
	</el-select>
</template>

<script>
export default {
	props: ["value", "exclude"],
	data() {
		return {
			users: [],
		};
	},
	computed: {
		usersExclude() {
			if (this.exclude) {
				return this.users.filter((item) => item.username !== this.exclude);
			} else {
				return this.users;
			}
		},
	},
	created() {
		this.axios.get("/user").then((res) => {
			this.users = res.data;
		});
	},
};
</script>

<style></style>
