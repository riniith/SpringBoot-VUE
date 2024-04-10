package com.mms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mms.dao.CustomerMapper;
import com.mms.entity.Customer;
import com.mms.entity.SearchResult;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerMapper customerMapper;

	@Override
	public boolean save(Customer newCustomer) {
		// 调用dao层，执行insert操作
		this.customerMapper.insert(newCustomer);
		return true;
	}

	@Override
	public List<Customer> list() {
		return this.customerMapper.selectAll();

	}

	@Override
	public Customer selectById(int custId) {
		return this.customerMapper.selectById(custId);
	}

	@Override
	public List<Customer> top6() {
		return this.customerMapper.selectTop6();
	}


	@Override
	public SearchResult<Customer> search(String nameOrPhone, String ownerUsername, int size,
			int page) {
		// 计算LIMIT子句需要的两个参数
		final int skip = (page - 1) * size;
		final int take = size;
		// 查询一： select count(0) from ... where .... => long
		long total = this.customerMapper.countByNameOrPhoneLikeAndOwnerUsername(nameOrPhone, ownerUsername);
		// 查询二：select * from ... where ... limit .... => List<Customer>
		List<Customer> list = this.customerMapper.selectByNameOrPhoneLikeAndOwnerUsernamePageable(nameOrPhone, ownerUsername, skip, take);
		// 把这两个结果封装成SearchResult对象，返回
		return new SearchResult<>(list, total);
	}

	@Override
	public boolean update(Customer customer) {
		this.customerMapper.updateByPrimaryKey(customer);
		return true;
	}

	@Override
	public boolean delete(int custId) {
		this.customerMapper.deleteByPrimaryKey(custId);
		return true;
	}

	@Override
	public void assign(int custId, String newOwnerUsername) {
		this.customerMapper.updateOwnerUsername(custId, newOwnerUsername);
	}

}
