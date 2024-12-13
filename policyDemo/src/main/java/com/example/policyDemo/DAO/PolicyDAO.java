package com.example.policyDemo.DAO;

import java.util.List;

import com.example.policyDemo.entity.Policy;

public interface PolicyDAO {
	List<Policy> findALL();
	Policy save(Policy policy);
	Policy findById(int id);
	List<Policy> findAll();
}
