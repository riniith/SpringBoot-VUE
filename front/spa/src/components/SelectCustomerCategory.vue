<template>
	<el-select
		:value="value"
		@input="$emit('input', $event)"
		clearable
		style="width: 100%"
	>
		<el-option label="アナリスト" value="	analyst"></el-option>
		<el-option label="競合" value="	compete"></el-option>
		<el-option label="顧客" value="	customer"></el-option>
		<el-option label="インテグレーター（統合）" value="	integrate"></el-option>
		<el-option label="投資家" value="	investor"></el-option>
		<el-option label="パートナー" value="	cooperativePartner"></el-option>
		<el-option label="報道機関" value="	newsAgencies"></el-option>
		<el-option label="見込顧客" value="	prospect"></el-option>
		<el-option label="代理店" value="	agencyStore"></el-option>
		<el-option label="そのた" value="	other"></el-option>
	</el-select>
</template>

<script>
export default {
	props: ["value"],
};
</script>
