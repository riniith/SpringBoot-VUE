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
				<el-button size="small" @click="handleUpdate">保存</el-button>
				<el-button size="small" @click="handleSave">
					保存して新しく作成
				</el-button>
				<el-button size="small" @click="handleCancel">キャンセル</el-button>
			</div>
			<br />
			<!-- body -->
			<div class="border">取引先情報</div>
			<!-- 表单 -->
			<el-form
				ref="form"
				:model="currentCust"
				:rules="rules"
				label-width="100px"
				style="background: #f2f6fc; padding-top: 20px; padding-right: 20px"
			>
				<el-row>
					<el-col :span="24">
						<el-form-item label="取引先所有者" prop="">田中 雄一</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="取引先名" prop="name"  >
							<el-input v-model="currentCust.name" ></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="電話番号" prop="phone">
							<el-input v-model="currentCust.phone"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="FAX" prop="FAX">
							<el-input v-model="currentCust.FAX"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="website" prop="website">
							<el-input v-model="currentCust.website"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<div class="border" style="margin-right: -20px">詳細情報</div>
				<el-row style="padding-top: 20px">
					<el-col :span="12">
						<el-form-item label="郵便番号" prop="postalCode">
							<el-input v-model="currentCust.postalCode"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="住所" prop="address">
							<el-input v-model="currentCust.address"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="得意分野" prop="expertiseAreas">
							<SelectExpertiseAreas
								v-model="currentCust.expertiseAreas"
							></SelectExpertiseAreas>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="従業員数" prop="employeesNumber">
							<el-input v-model="currentCust.employeesNumber"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="取引先の種別" prop="customerCategory">
							<SelectCustomerCategory
								v-model="currentCust.customerCategory"
							></SelectCustomerCategory>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="年間売上" prop="AnnualSales">
							<el-input v-model="currentCust.AnnualSales"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="銀行名" prop="bankName">
							<el-input v-model="currentCust.bankName"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="支店名" prop="BranchName">
							<el-input v-model="currentCust.BranchName"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="口座番号" prop="Accounts">
							<el-input v-model="currentCust.Accounts"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="締め日" prop="dealDate">
							<el-input v-model="currentCust.dealDate"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="6">
						<el-form-item label="支払サイト" prop="Payment">
							<el-input v-model="currentCust.Payment"></el-input>
						</el-form-item>
					</el-col>
					<el-col :span="24">
						<el-form-item label="備考" prop="remarks">
							<el-input type="textarea" v-model="currentCust.remarks"></el-input>
						</el-form-item>
					</el-col>
				</el-row>
				<br />
				<div style="text-align: center">
					<el-button size="small" @click="handleSave">保存</el-button>
					<el-button size="small" @click="handleSave">
						保存して新しく作成
					</el-button>
					<el-button size="small" @click="handleCancel">キャンセル</el-button>
				</div>
				<br />
			</el-form>
		</div>
	</div>
</template>

<script>
import SelectExpertiseAreas from "../../components/SelectExpertiseAreas.vue";
import SelectCustomerCategory from "../../components/SelectCustomerCategory.vue";
export default {
	components: { SelectExpertiseAreas, SelectCustomerCategory },
	data() {
		return {
			currentCust: {},
			dialogVisible: false,
			nameOrPhone: "",
			checkResult: [],
			customer: {
				gender: "FEMALE",
			},
			rules: {
				name: [{ required: true, message: "请输入客户名称", trigger: "blur" }],
				phone: [
					{ message: "请输入电话号码", trigger: "blur" },
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
			console.log("save",this.currentCust)
			this.axios
			
				.post("/customer", this.currentCust)
				.then(() => {
					this.$message.success("数据保存成功");
				})
				.catch(() => {
					this.$message.error("数据保存失败");
				});
		},
		handleUpdate() {
			this.customer.id = this.custId;
			this.axios
				.update(`/customer/${this.currentCust.custId}`, this.customer)
				.then(() => {
					this.$message.success("更新成功");
					this.$router.push("/customer/list");
				})
				.catch(() => {
					this.$message.error("更新失败");
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

.el-form-item__label {
	color: darkblue;
}

.el-input{

}
</style>
