package com.mms.entity;

/**
 * 枚举：客户状态
 * 
 * @author Administrator
 *
 */
public enum CustomerExpertiseAreas {
	/**
	 * 金融系（銀行）
	 * 金融系（証券）
	 * 金融系（生損保）
	 * 金融系（その他）
	 * 流通
	 * 製造
	 * 組込み・制御
	 * 官公庁
	 * その他
	 */
	financeBank,
	financialSecurities,
	financeLossAndLifeInsurance,
	financeOthers,
	distribution,
	manufacture,
	embeddedControl,
	government,
	other;
}
