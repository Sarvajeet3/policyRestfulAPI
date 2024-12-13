package com.example.policyDemo.service;

import java.util.List;

import com.example.policyDemo.entity.Policy;

public interface PolicyService {

	List<Policy> findALL();
	String save(Policy policy);
	Policy findById(int id);
	List<Policy> findAll();
	
}
