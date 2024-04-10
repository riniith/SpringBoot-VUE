<template>
	<div>
		<div class="container">
			<!-- 头部 -->
			<i class="el-icon-office-building">取引先</i>
			<i class="el-icon-question" style="position: absolute; right: 20px">
				help
			</i>
			<br />
			<br />
			<div style="text-align: center">
				<el-button size="small" @click="handleUpdate">编辑</el-button>
				<el-button size="small" @click="handleDelete"> 削除</el-button>
				<el-button size="small" @click="handleCancel">コピ</el-button>
			</div>
			<br />
			<!-- body -->
			<div class="border">取引先情報</div>
			<!-- 表单 -->

			<el-form
				ref="form"
				:model="customer"
				:rules="rules"
				label-width="100px"
				style="background: #f2f6fc; padding-top: 20px; padding-right: 20px"
			>
				<el-row>
					<el-col :span="24">
						<el-form-item label="取引先所有者" prop="">
							田中 雄一
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="取引先名" prop="name"  >
							{{ currentCust.name }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="電話番号" prop="phone">
							{{ currentCust.phone }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="FAX" prop="FAX">
							{{ currentCust.FAX }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="website" prop="website">
							{{ currentCust.website }}
						</el-form-item>
					</el-col>
				</el-row>
				<div class="border" style="margin-right: -20px">詳細情報</div>
				<el-row style="padding-top: 20px">
					<el-col :span="12">
						<el-form-item label="郵便番号" prop="postalCode">
							{{ currentCust.postalCode }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="住所" prop="address">
							{{ currentCust.address }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="得意分野" prop="expertiseAreas">
							{{ currentCust.expertiseAreas }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="従業員数" prop="employeesNumber">
							{{ currentCust.employeesNumber }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="取引先の種別" prop="customerCategory">
							{{ currentCust.customerCategory }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="年間売上" prop="AnnualSales">
							{{ currentCust.AnnualSales }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="銀行名" prop="bankName">
							{{ currentCust.bankName }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="支店名" prop="BranchName">
							{{ currentCust.BranchName }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="口座番号" prop="Accounts">
							{{ currentCust.FAX }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="支払サイト" prop="Payment">
							{{ currentCust.Payment }}
						</el-form-item>
					</el-col>
				</el-row>
				<div class="border" style="margin-right: -20px">
					システム情報
				</div>
				<el-row style="padding-top: 20px">
					<el-col :span="12">
						<el-form-item label="作成者" prop="bankName">
							{{ currentCust.bankName }}
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="最終更新者" prop="BranchName">
							{{ currentCust.BranchName }}
						</el-form-item>
					</el-col>
					<br />
				</el-row>
			</el-form>
			<div style="text-align: center;padding-top: 20px;">
				<el-button size="small" @click="handleUpdate">编辑</el-button>
				<el-button size="small" @click="handleDelete"> 削除</el-button>
				<el-button size="small" @click="handleCancel">コピ</el-button>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	components: {
	},
	data() {
		return {
			currentCust: {},
			dialogVisible: false,
			nameOrPhone: "",
			checkResult: [],
			customer: {
				gender: "FEMALE",
			},
			custId: 1,
			rules: {
				name: [{ required: true, message: "请输入客户名称", trigger: "blur" }],
				phone: [
					{  message: "请输入电话号码", trigger: "blur" },
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
	mounted(){
		this.custId = this.$route.query.custId;
		console.log(this.custId);
		this.getCodeResult();
		
		console.log("mounted")
		},
	methods: {
		getCodeResult() {
			const custId = this.$route.query.custId;
			this.axios
				.get(`/customer/${this.custId}`,custId, {
				})
				.then((res) => {
					console.log(res.data,this.custId);
					this.currentCust = res.data;			
				});
		},
		handleCheck() {
			this.axios
				.get("/customer/check-duplicate", {
					params: { keyword: this.nameOrPhone },
				})
				.then((res) => {
					this.checkResult = res.data;
				});
		},
		handleSave() {
			// 从sessionStorage中获得当前用户的帐号，作为这个新客户的负责人
			this.customer.ownerUsername = this.$store.state.currentUser.username;
			// 发起ajax请求
			this.axios
				.post("/customer", this.customer)
				.then(() => {
					this.$message.success("数据保存成功");
				})
				.catch(() => {
					this.$message.error("数据保存失败");
				});
		},
		handleUpdate() {
			this.getCodeResult();
			console.log(this.custId);
			this.$router.push({ path: '/customer/update', query: { custId: this.custId}});
		},
		handleDelete() {
			const custId = this.$route.query.custId;
			console.log(this.custId);
			this.$confirm("此操作将永久删除该客户, 是否继续?", "警告", {
				type: "warning",
			}).then(() => {
				this.axios
					.delete(`/customer/${this.custId}`,custId,)
					.then(() => {
						this.$message.success("删除成功");
						// 重新请求后端，刷新表格中的数据
						this.$router.push("/customer/list");
					})
					.catch(() => {
						this.$message.error("该客户已存在跟进记录，不能删除！");
					});
			});
		},
		handleCancel() {
			this.$router.push("/customer/list");
		},
	},
};
</script>

<style >
.sava {
	text-align: center;
}
.el-form-item__label{
    color: darkblue;
  }
</style>
