<template>
	<div>
		<!-- 统计条件 -->
		<div class="condition">
			<select-user
				v-model="condition.username"
				@input="handleStatistics"
				v-if="this.$store.state.currentUser.role === 'MANAGER'"
			></select-user>
			<el-date-picker
				v-model="condition.begin"
				type="date"
				value-format="yyyy-MM-dd"
				placeholder="开始日期"
				@change="handleStatistics"
			></el-date-picker>
			<span>至</span>
			<el-date-picker
				v-model="condition.end"
				type="date"
				value-format="yyyy-MM-dd"
				placeholder="结束日期"
				@change="handleStatistics"
			></el-date-picker>
		</div>
		<!-- 漏斗 -->
		<el-card style="margin-bottom: 20px">
			<div slot="header">销售漏斗</div>
			<el-row :gutter="10">
				<el-col :span="16">
					<div id="funnel" style="height: 210px"></div>
				</el-col>
				<el-col :span="8">
					<el-descriptions title="转化率统计" :column="1" size="medium">
						<el-descriptions-item label="报备客户数">
							{{ conversionRate.报备客户数 }}
						</el-descriptions-item>
						<el-descriptions-item label="意向客户数">
							{{ conversionRate.意向客户数 }}
						</el-descriptions-item>
						<el-descriptions-item label="成交客户数">
							{{ conversionRate.成交客户数 }}
						</el-descriptions-item>
						<el-descriptions-item label="意向转化率">
							{{ conversionRate.意向转化率 }}
						</el-descriptions-item>
						<el-descriptions-item label="成交转化率">
							{{ conversionRate.成交转化率 }}
						</el-descriptions-item>
					</el-descriptions>
				</el-col>
			</el-row>
		</el-card>
		<!-- 折线图 -->
		<el-card>
			<div slot="header">成交曲线</div>
			<div id="dealTrend" style="height: 300px"></div>
		</el-card>
	</div>
</template>

<script>
import * as echarts from "echarts";
import SelectUser from "@/components/SelectUser.vue";
export default {
	components: { SelectUser },
	data() {
		return {
			// 统计条件
			condition: {
				username: this.$store.state.currentUser.username,
				begin: null,
				end: null,
			},
			// 统计结果
			funnel: [],
			conversionRate: {},
			dealTrend: [],
		};
	},
	computed: {
		funnelLegend() {
			return this.funnel.map((item) => item.state);
		},
		funnelData() {
			return this.funnel.map((item) => {
				return {
					name: item.state,
					value: item.count,
				};
			});
		},
		dealTrendXAxisData() {
			return this.dealTrend.map((item) => item.YM);
		},
		dealTrendYAxisData() {
			return this.dealTrend.map((item) => item.count);
		},
	},
	created() {
		this.handleStatistics();
	},
	methods: {
		handleStatistics() {
			this.handleFunnel();
			this.handleConversionRate();
			this.handleDealTrend();
		},

		handleFunnel() {
			// 请求漏斗数据
			this.axios
				.get("/funnel", {
					params: this.condition,
				})
				.then((res) => {
					this.funnel = res.data;
					if (this.funnel.length === 0) {
						this.$message.warning("无数据");
					}
					// 使用echarts渲染图表
					var chartDom = document.getElementById("funnel");
					var myChart = echarts.init(chartDom);
					var option;

					option = {
						tooltip: {
							trigger: "item",
							formatter: "{a} <br/>{b} : {c}",
						},
						legend: {
							// data: this.funnelLegend,
							data: ["UNKNOWN", "INTENTIONAL", "DEALED"],
						},
						series: [
							{
								name: "客户状态",
								type: "funnel",
								// left: "10%",
								// top: 60,
								bottom: 15,
								// width: "80%",
								// min: 0,
								// max: 100,
								// minSize: "0%",
								// maxSize: "100%",
								sort: "descending",
								gap: 2,
								label: {
									show: true,
									position: "inside",
								},
								labelLine: {
									length: 10,
									lineStyle: {
										width: 1,
										type: "solid",
									},
								},
								itemStyle: {
									borderColor: "#fff",
									borderWidth: 1,
								},
								emphasis: {
									label: {
										fontSize: 20,
									},
								},
								data: this.funnelData,
							},
						],
					};
					option && myChart.setOption(option);
				});
		},

		handleConversionRate() {
			this.axios
				.get("/conversionRate", { params: this.condition })
				.then((res) => {
					this.conversionRate = res.data;
				});
		},

		handleDealTrend() {
			this.axios.get("/dealTrend", { params: this.condition }).then((res) => {
				this.dealTrend = res.data;
				// 渲染图表
				var chartDom = document.getElementById("dealTrend");
				var myChart = echarts.init(chartDom);
				var option;

				option = {
					tooltip: {
						trigger: "axis",
					},
					xAxis: {
						type: "category",
						data: this.dealTrendXAxisData,
					},
					yAxis: {
						type: "value",
					},
					series: [
						{
							data: this.dealTrendYAxisData,
							type: "line",
							left: "0%",
							top: 0,
							bottom: 0,
							width: "100%",
						},
					],
				};

				option && myChart.setOption(option);
			});
		},
	},
};
</script>

<style scoped>
.condition {
	display: flex;
	gap: 20px;
	align-items: center;
	margin-bottom: 20px;
}
</style>
