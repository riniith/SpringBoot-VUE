<template>
	<div>
		<div class="container" id="app">
			<!--  -->
			<div class="border">最近の取引情報</div>
			<div class="down" style="padding-top: 10px">
				<div style="text-align: center">
					<el-button @click="handleCreate">新規</el-button>
				</div>
				<el-table
					:data="customer"
					:header-cell-style="{ background: '#f0ffff', color: '#646a73' }"
					style="
						border: 2px solid lightblue;
						color: #606266;
						margin-top: 10px;
						width: 95%;
						left: 2%;
					"
				>
					<el-table-column prop="name" label="取引先名">
						<template slot-scope="scope">
							<el-link @click="handleDetail(scope.row.custId)">
								{{ scope.row.name }}
							</el-link>
						</template>
					</el-table-column>
					<el-table-column prop="phone" label="住所"></el-table-column>
					<el-table-column prop="adress" label="電話"></el-table-column>
				</el-table>
				<!-- 分页栏 -->
				<el-container
					style="margin-right: 20px; margin-top: 20px; color: #606266"
				>
					<div style="padding-left: 15px; width: 100%">
						<div class="border">レポート</div>
						<div class="down" style="padding-top: 10px">
							<el-link href="https://element.eleme.io" target="_blank">
								取引先・得意先一覧
							</el-link>
							<br />
							<br />
							<el-link href="https://element.eleme.io" target="_blank">
								取引先・パートナー一覧
							</el-link>
							<br />
							<br />
							<el-link href="https://element.eleme.io" target="_blank">
								取引先所有者一覧画面
							</el-link>
							<br />
							<br />
						</div>
					</div>
					<div style="margin-left: 10%; width: 100%">
						<div class="border" style="padding-left: 15px">ツール</div>
						<div class="down" style="padding-top: 10px">
							<el-link href="https://element.eleme.io" target="_blank">
								取引先のインポート
							</el-link>
							<br />
							<br />
							<el-link href="https://element.eleme.io" target="_blank">
								取引先のエクスポート
							</el-link>
							<br />
							<br />
							<el-link href="https://element.eleme.io" target="_blank">
								取引先所有権の移行
							</el-link>
							<br />
							<br />
						</div>
					</div>
				</el-container>
				<br />
			</div>
		</div>
	</div>
</template>

<script>
export default {
	components: {},
	data() {
		return {
			customer: [],
			// 查询结果
			searchResult: {
				list: [],
			},
			// 抽屉是否可见
			drawer: false,
			// 当前点击的客户
			currentCust: {},
			custId: 1
		};
	},
	created() {
		this.getCustomer();
	},
	methods: {
		async getCustomer() {
			this.axios.get("/customer/top6").then((res) => {
				this.customer = res.data;
			});
		},
		handleCreate() {
			this.$router.push("/customer/create");
		},
		handleSizeChange() {
			this.pageNum = 1;
			this.query();
		},
		handleCurrentChange() {
			this.query();
		},
		handleSearch() {
			this.pageNum = 1;
			this.query();
		},
		query() {
			// 请求后端，传递查询条件，得到查询结果，更新data里面的searchResult
			this.axios
				.get("/customer", {})
				.then((res) => (this.searchResult = res.data));
		},
		handleDetail(custId) {
			this.custId = custId;
			console.log(this.custId)
			this.$router.push({ path: '/customer/detail', query: { custId: this.custId}});
		},
		handleDelete() {
			this.$confirm("此操作将永久删除该客户, 是否继续?", "警告", {
				type: "warning",
			}).then(() => {
				this.axios
					.delete(`/customer/$(id)`)
					.then(() => {
						this.$message.success("删除成功");
						// 重新请求后端，刷新表格中的数据
						this.query();
					})
					.catch(() => {
						this.$message.error("该客户已存在跟进记录，不能删除！");
					});
			});
		},
	},
};
</script>

<style scoped>
.search {
	display: flex;
	justify-content: space-between;
	gap: 20px;
	margin-bottom: 20px;
}
</style>
