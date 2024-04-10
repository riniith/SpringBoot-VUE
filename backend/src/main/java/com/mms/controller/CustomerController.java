package com.mms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mms.entity.Customer;
import com.mms.entity.SearchResult;
import com.mms.service.CustomerService;

@CrossOrigin
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer")
	public List<Customer> list() {
		List<Customer> list = this.customerService.list();
		return list;
	}

	@GetMapping("/customer/{custId}")
	public Customer detail(@PathVariable int custId) {
		Customer customer = this.customerService.selectById(custId);
		return customer;
	}

	@GetMapping("/customer/top6")
	public List<Customer> top6() {
		List<Customer> list = this.customerService.top6();
		return list;
	}

	@GetMapping("/customer/search")
	public SearchResult<Customer> search(@RequestBody String nameOrPhone, String ownerUsername, int size, int page) {
		SearchResult<Customer> searchResult = this.customerService.search(nameOrPhone,  ownerUsername, size, page);
		return searchResult;
	}
	
	@PostMapping("/customer")
	public ResponseEntity<?> create(@RequestBody Customer customer) {
		boolean success = this.customerService.save(customer);
		if (success) {
			return ResponseEntity.ok(null); // 200
		}
		return ResponseEntity.badRequest().body("该客户属于别的用户，不能重复报备");
	}
	
	@PutMapping("/customer/{custId}")
	public void update(@PathVariable int custId ,@RequestBody Customer customer) {
		this.customerService.update(customer);
	}
	
	@PutMapping("/customer/assign/{custId}/{newOwnerUsername}")
	public void assign(@PathVariable int custId, @PathVariable String newOwnerUsername) {
		this.customerService.assign(custId, newOwnerUsername);
	}
	
	@DeleteMapping("/customer/{custId}")
	public void delete(@PathVariable int custId) {
		this.customerService.delete(custId);
	}
}
