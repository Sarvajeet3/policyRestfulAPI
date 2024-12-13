package com.example.policyDemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.policyDemo.entity.Policy;
import com.example.policyDemo.service.PolicyService;

import jakarta.validation.Valid;

@RestController
public class PolicyRestController {
	
	private PolicyService policyService;
	
	
	@Autowired
	public PolicyRestController(PolicyService policyService) {
		super();
		this.policyService = policyService;
	}

	@GetMapping("/policies/{policyId}")
	public String getPolicyById(@Valid @PathVariable int policyId) {
		 
		
		Policy trackedPolicy = policyService.findById(policyId);;
		if(trackedPolicy!=null) {
			System.out.println();
			return "Policy retrieved successfully,\n" + trackedPolicy.toString();
		}
		else
		{
			return ("Policy Does not exists");
		}
		
	}
	
	@GetMapping("/policies")
	public List<Policy> getPolicies() {
		 
		return policyService.findAll();
		
	}
	
	
	@PostMapping("/policies")
	public String posttPolicy(@RequestBody Policy policy) {
		return policyService.save(policy);
	}
	
}
