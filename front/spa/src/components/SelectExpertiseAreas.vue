<template>
	<el-select
		:value="value"
		@input="$emit('input', $event)"
		clearable
		style="width: 100%"
	>
		<el-option label="金融系（銀行）" value="financeBank"></el-option>
		<el-option label="金融系（証券）" value="financialSecurities"></el-option>
		<el-option label="金融系（生損保）" value="financeLossAndLifeInsurance"></el-option>
		<el-option label="金融系（その他）" value="financeOthers"></el-option>
		<el-option label="流通" value="distribution"></el-option>
		<el-option label="製造" value="manufacture"></el-option>
		<el-option label="組込み・制御" value="embeddedControl"></el-option>
		<el-option label="官公庁" value="government"></el-option>
		<el-option label="その他" value="other"></el-option>
	</el-select>
</template>

<script>
export default {
	props: ["value"],
};
</script>
