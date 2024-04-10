package com.mms.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类：客户
 * 
 * @author Administrator
 *
 */
public class Customer {
	// 客户id，唯一
	private int custId;
	// 客户姓名
	private String name;
	// 客户联系电话
	private String phone;
	// 客户联系FAX
	private String FAX;
	// 客户联系website
	private String website;
	// 客户联系地址
	private String address;
	// 客户従業員数
	private int employeesNumber;
	// 客户年間売上
	private BigDecimal AnnualSales;
	// 客户支店名
	private String BranchName;
	// 支払サイト
	private String Payment;
	// 預金種別
	private CustomerDepositCategory depositCategory;
	// 客户郵便番号
	private String postalCode;
	// 客户得意分野
	private CustomerExpertiseAreas expertiseAreas;
	// 客户取引先の種別
	private CustomerCategory customerCategory;
	// 客户銀行名
	private String bankName;
	// 客户口座番号
	private String Accounts;
	// 客户名義人
	private String nominalPerson;
	// 締め日
	private Date dealDate;
	// 该客户属于哪个用户
	private String ownerUsername;
	// 客户備考
	private String remarks;

	private boolean is_deleted;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFAX() {
		return FAX;
	}

	public void setFAX(String FAX) {
		this.FAX = FAX;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getEmployeesNumber() {
		return employeesNumber;
	}

	public void setEmployeesNumber(int employeesNumber) {
		this.employeesNumber = employeesNumber;
	}

	public BigDecimal getAnnualSales() {
		return AnnualSales;
	}

	public void setAnnualSales(BigDecimal annualSales) {
		AnnualSales = annualSales;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String payment) {
		Payment = payment;
	}

	public CustomerDepositCategory getDepositCategory() {
		return depositCategory;
	}

	public void setDepositCategory(CustomerDepositCategory depositCategory) {
		this.depositCategory = depositCategory;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public CustomerExpertiseAreas getExpertiseAreas() {
		return expertiseAreas;
	}

	public void setExpertiseAreas(CustomerExpertiseAreas expertiseAreas) {
		this.expertiseAreas = expertiseAreas;
	}

	public CustomerCategory getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(CustomerCategory customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccounts() {
		return Accounts;
	}

	public void setAccounts(String accounts) {
		Accounts = accounts;
	}

	public String getNominalPerson() {
		return nominalPerson;
	}

	public void setNominalPerson(String nominalPerson) {
		this.nominalPerson = nominalPerson;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"custId=" + custId +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", FAX='" + FAX + '\'' +
				", website='" + website + '\'' +
				", address='" + address + '\'' +
				", employeesNumber=" + employeesNumber +
				", AnnualSales=" + AnnualSales +
				", BranchName='" + BranchName + '\'' +
				", Payment='" + Payment + '\'' +
				", depositCategory=" + depositCategory +
				", postalCode='" + postalCode + '\'' +
				", expertiseAreas=" + expertiseAreas +
				", customerCategory=" + customerCategory +
				", bankName='" + bankName + '\'' +
				", Accounts='" + Accounts + '\'' +
				", nominalPerson='" + nominalPerson + '\'' +
				", dealDate=" + dealDate +
				", ownerUsername='" + ownerUsername + '\'' +
				'}';
	}
}
