<template>
	<el-select
		:value="value"
		@input="$emit('input', $event)"
		placeholder="请选择客户来源"
		style="width: 100%"
		clearable
	>
		<el-option label="口碑" value="WORD_OF_MOUTH"></el-option>
		<el-option label="新媒体" value="MEDIA"></el-option>
		<el-option label="地推活动" value="PROMOTION"></el-option>
	</el-select>
</template>

<script>
export default {
	props: ["value"],
};
</script>
