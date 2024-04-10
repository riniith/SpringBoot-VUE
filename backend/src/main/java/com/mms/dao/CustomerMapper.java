package com.mms.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.mms.entity.Customer;


@Mapper
public interface CustomerMapper {

	/**
	 * 以主键作为条件，更新一个客户
	 * 
	 * @param customer
	 */
	void updateByPrimaryKey(Customer customer);

	/**
	 * 查询all
	 */
	@Select("SELECT * FROM test.customer;")
	List<Customer> selectAll();

	/**
	 * 查询top6
	 */
	@Select("SELECT * FROM test.customer order by test.customer.custId  desc limit 0,6 ;")
	List<Customer> selectTop6();

	/**
	 * 以主键作为条件，查询一个客户
	 * 
	 * @param custId
	 * @return
	 */
	@Select("SELECT * FROM customer WHERE custId = #{custId}")
	Customer selectByPrimaryKey(int custId);

	/**
	 * 以姓名或电话为条件，进行模糊查询
	 * 
	 * @param nameOrPhone
	 * @return
	 */
	List<Customer> selectByNameOrPhoneLike(String nameOrPhone);

	/**
	 * 插入新客户
	 * 
	 * @param newCustomer
	 */
	void insert(Customer newCustomer);

	/**
	 * 根据主键删除客户
	 * 
	 * @param custId
	 */
	void deleteByPrimaryKey(int custId);

	long countByNameOrPhoneLikeAndOwnerUsername(
		@Param("nameOrPhone") String nameOrPhone,
		@Param("ownerUsername") String ownerUsername);

	List<Customer> selectByNameOrPhoneLikeAndOwnerUsernamePageable(
			@Param("nameOrPhone") String nameOrPhone,
			@Param("ownerUsername") String ownerUsername,
			@Param("skip") int skip,
			@Param("take") int take);

	@Update("UPDATE `test`.`customer` SET `ownerUsername`=#{ownerUsername} WHERE `custId`=#{custId};\n")
	void updateOwnerUsername(int custId, String newOwnerUsername);

	@Select("SELECT * FROM test.customer where custId = #{custId};")
	Customer selectById(int custId);
}
