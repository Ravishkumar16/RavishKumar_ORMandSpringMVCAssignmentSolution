package com.greatLearning.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatLearning.model.Customer;
import com.greatLearning.service.CustomerService;

@Controller
@RequestMapping("/Customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listBooks(Model theModel) {
		System.out.println("Request Recieved");
		List<Customer> theCustomer = customerService.getCustomers();
		theModel.addAttribute("Customers", theCustomer);
		return "list-Customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		theModel.addAttribute("Customer", theCustomer);
		return "customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		Customer theCustomer = customerService.getCustomer(theId);
		theModel.addAttribute("Customer", theCustomer);
		return "customer-form";
	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("customerId") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		Customer theCustomer;
		if (id != 0) {
			//Update 
			theCustomer = customerService.getCustomer(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
		} else {
			//Create
			theCustomer = new Customer(firstName, lastName, email);
		}
		customerService.saveCustomer(theCustomer);
		return "redirect:/Customers/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {
		customerService.deleteCustomer(theId);
		return "redirect:/Customers/list";
	}
}
