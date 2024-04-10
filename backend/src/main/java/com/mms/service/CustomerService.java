package com.mms.service;

import java.util.List;

import com.mms.entity.Customer;
import com.mms.entity.SearchResult;

/**
 * 客户模块业务接口
 * @author Administrator
 *
 */
public interface CustomerService {

//	/**
//	 * 根据客户的名称或电话查重
//	 * @param nameOrPhone 查询条件，可能是姓名，也可能是电话，总之是用户输入的一个查找关键字
//	 * @return 返回匹配的客户列表；如果查无结果则返回null
//	 */
//	List<Customer> checkDuplicate(String nameOrPhone);
	
	/**
	 * 保存新客户信息
	 * @param newCustomer 一个包含了名称、性别、地址、电话、来源、状态、最后跟进日期、最后跟进情况的Customer对象
	 * @return 保存成功则返回true；否则false
	 */
	boolean save(Customer newCustomer);

	/**
	 *all
	 */
	List<Customer> list( );

	/**
	 *all
	 */
	Customer selectById(int custId);

	/**
	 *all
	 */
	List<Customer> top6( );


	/**
	 * 根据复杂条件分页查询客户信息，支持模糊查找
	 * @param nameOrPhone 查询关键字，表示姓名或电话
	 * @param ownerUsername 用户名
	 * @param size 每页行数
	 * @param page 页码
	 * @return 返回查询结果，该结果是一个封装了List&lt;Customer&gt;和总行数的SearchResult对象
	 */
	SearchResult<Customer> search(String nameOrPhone, String ownerUsername, int size, int page);

	/**
	 * 更新客户信息
	 * @param customer 一个包含了新数据的客户对象
	 * @return 更新成功则返回true；否则false
	 */
	boolean update(Customer customer);
	
	/**
	 * 删除指定的客户
	 * @param custId 待删除的客户的唯一id
	 * @return 删除成功则返回true；否则返回false
	 */
	boolean delete(int custId);
	
	void assign(int custId, String newOwnerUsername);
}
