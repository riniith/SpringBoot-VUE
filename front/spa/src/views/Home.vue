<template>
	<div>
		<div class="border" style="height: 60px">
			<div style="font-size: x-large; padding: 15px; color: #606266">
				営業プラス管理システム
				<el-button size="mini" style="float: right;    margin-top: 10px;">
					<i class="el-icon-caret-right"></i>
					営業活動</el-button>
			</div>
			
		</div>
		<el-container>
			<el-aside width="200px" style="margin-top: 10px">
				<div class="border">業務支援メニュー</div>
				<div class="down" style="height: 110px; padding-right: 15px">
					<el-select
						style="margin-top: 10px"
						v-model="value"
						clearable
						placeholder="新規作成"
					>
						<el-option
							v-for="item in options"
							:key="item.value"
							:label="item.label"
							:value="item.value"
						></el-option>
					</el-select>

					<el-select
						style="margin-top: 10px"
						v-model="value"
						clearable
						placeholder="最近使ったデータ"
					>
						<el-option
							v-for="item in options"
							:key="item.value"
							:label="item.label"
							:value="item.value"
						></el-option>
					</el-select>
				</div>

				<div class="border" style="margin-top: -2px">通常業務</div>
				<div class="down" style="border-top: 0px">
					<div style="font-size: small">
						<br />
						基本情報
						<br />
						・取引先
						<br />
						・取引先担当者
						<br />
						・スタッフ
						<br />
						<br />
						営業活動
						<br />
						・商談
						<br />
						・案件
						<br />
						・契約
						<br />
						<br />
						請求関連
						<br />
						・請求書作成
						<br />
						・注文書作成
						<br />
						・見積書作成
						<br />
						<br />
						レポート
						<br />
						・取引先/担当者
						<br />
						・商談
						<br />
						・売上
						<br />

						<el-dropdown>
							<el-button type="normal" style="        width: 166px;
							text-align: left;    margin: 20px 0px;">
								簡易作成
								<i class="el-icon-arrow-down el-icon--right"
									style="padding-left: 35%;"></i>
							</el-button>

							<br />
							<el-dropdown-menu slot="dropdown" style="width: 166px;">
								<el-form 
									:model="customer"
									ref="form" 
									size="mini"
									:rules="rules"
									>
									<el-form-item label="取引先名" prop="name">
										<el-input v-model="customer.name"></el-input>
									</el-form-item>
									<el-form-item label="電話">
										<el-input v-model="customer.phone"></el-input>
									</el-form-item>
									<el-form-item label="Webサイト">
										<el-input v-model="customer.website"></el-input>
									</el-form-item>
									<el-form-item>
										<el-button @click="handleSave">保存</el-button>
									</el-form-item>
								</el-form>
								
							</el-dropdown-menu>
						</el-dropdown>
						<br/>
					</div>
				</div>
			</el-aside>
			<el-main
				style="padding-top: 10px; padding-right: 0px; padding-left: 10px"
			>
				<div class="border">営業プラス</div>
				<div class="down">
					<el-menu
						:default-active="this.$route.path"
						mode="horizontal"
						text-color="#606266"
						background-color="transparent"
						active-text-color="#409EFF"
						:router="true"
					>
						<el-menu-item index="/home">ホーム</el-menu-item>
						<el-menu-item index="/customer/list">取引先</el-menu-item>
						<el-menu-item index="/customer/user">取引先担当者</el-menu-item>
						<el-menu-item index="/c">スタッフ</el-menu-item>
						<el-menu-item index="/dashboard">商談</el-menu-item>
						<el-menu-item index="/dashboard">案件</el-menu-item>
						<el-menu-item index="/dashboard">契約</el-menu-item>
						<el-menu-item index="/dashboard">リード</el-menu-item>
						<el-menu-item index="/report">レポート</el-menu-item>
						<el-menu-item index="/more">+</el-menu-item>
						<!-- <el-menu-item
						index="/user/manage"
						class="el-icon-apple"
						v-if="this.$store.state.currentUser.role === 'USERMANAGER'"
					>
						用户管理
					</el-menu-item>
					<el-submenu index="" style="float: right">
						<template slot="title">
							<i class="el-icon-user"></i>
							欢迎您，【{{ this.$store.state.currentUser.username }}】
						</template>
						<el-menu-item index="/user/pwd">修改密码</el-menu-item>
						<el-menu-item @click="handleLogout">注销</el-menu-item>
					</el-submenu> -->
					</el-menu>

					<el-main>
						<router-view></router-view>
					</el-main>
				</div>
			</el-main>
		</el-container>
	</div>
</template>

<script>
export default {
	data() {
		return {
			customer: {},
			input: "",
			form: {
				name: "",
			},
			options: [
				{
					value: "选项1",
					label: "黄金糕",
				},
				{
					value: "选项2",
					label: "双皮奶",
				},
				{
					value: "选项3",
					label: "蚵仔煎",
				},
				{
					value: "选项4",
					label: "龙须面",
				},
				{
					value: "选项5",
					label: "北京烤鸭",
				},
			],

			rules: {
				name: [{ required: true, message: "请输入客户名称", trigger: "blur" }],
			},
		};
	},
	methods: {
		// 注销
		handleLogout() {
			// 清除浏览器会话存储中持久存储的CURRENT_USER
			sessionStorage.removeItem("CURRENT_USER");
			// 清除Vuex全局状态中的currentUser
			this.$store.commit("setCurrentUser", {});
			this.$router.push("/");
		},
				handleSave() {
			// 从sessionStorage中获得当前用户的帐号，作为这个新客户的负责人
			// this.customer.ownerUsername = this.$store.state.currentUser.username;
			// 发起ajax请求
			this.axios
				.post("/customer", this.customer)
				.then(() => {
					this.$message.success("数据保存成功");
					this.$router.push("/customer/list");
				})
				.catch(() => {
					this.$message.error("数据保存失败");
				});
		},
	},
};
</script>

<style  scoped>
.el-form-item {
	width: 120px;
padding-left:20px ;
}
</style>
