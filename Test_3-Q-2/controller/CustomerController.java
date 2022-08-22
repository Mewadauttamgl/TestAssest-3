package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.name.example.dao.CustomerRepo;
import com.name.example.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepo repo;

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return repo.findAll();
	}

	@RequestMapping("/customer/{cid}")
	public Optional<Customer> getCustomer(@PathVariable("cid") int cid) {
		return repo.findById(cid);
	}

	@PostMapping(path = "/customer", consumes = { "application/json" })
	public Customer addCustomer(@RequestBody Customer customer) {
		repo.save(customer);
		return customer;
	}

	@DeleteMapping("/customer/{cid}")
	public String deleteAlien(@PathVariable int cid) {
		Customer c = repo.getOne(cid);
		repo.delete(c);
		return "Deleted";
	}

	@PutMapping(path = "/customer", consumes = { "application/json" })
	public Customer saveOrUpdateAlien(@RequestBody Customer customer) {
		repo.save(customer);
		return customer;
	}

}


