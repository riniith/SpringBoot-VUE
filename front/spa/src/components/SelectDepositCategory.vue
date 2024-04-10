<template>
	<el-select
		:value="value"
		@input="$emit('input', $event)"
		clearable
		style="width: 100%"
	>
		<el-option label="普通預金" value="ordinaryDeposit"></el-option>
		<el-option label="当座預金" value="checkingAccount"></el-option>
		<el-option label="貯蓄預金" value="savingsDeposits"></el-option>
		<el-option label="その他" value="others"></el-option>
	</el-select>
</template>

<script>
export default {
	props: ["value"],
};
</script>
