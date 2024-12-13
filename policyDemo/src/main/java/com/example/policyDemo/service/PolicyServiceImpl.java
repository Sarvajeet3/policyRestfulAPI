package com.example.policyDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.policyDemo.DAO.PolicyDAO;
import com.example.policyDemo.entity.Policy;

@Service
public class PolicyServiceImpl implements PolicyService {

	private PolicyDAO policyDAO; 
	
	
	@Autowired
	public PolicyServiceImpl(PolicyDAO policyDAO) {
		super();
		this.policyDAO = policyDAO;
	}

	@Override
	public List<Policy> findALL() {
		// TODO Auto-generated method stub
		return policyDAO.findALL();
	}

	@Transactional
	@Override
	public String save(Policy policy) {
		// TODO Auto-generated method stub
		policy.toString();
		policy.setId(0);
		
		Policy newPolicy=policyDAO.save(policy);
		
		if(newPolicy!=null) {
			return "Policy Created Successfully";
		}
		else
		{
			return "Invalid request";
		}
		
		
	}
	
	@Transactional
	@Override
	public Policy findById(int id) {
		// TODO Auto-generated method stub
		return policyDAO.findById(id);
	}

	@Override
	public List<Policy> findAll() {
		return policyDAO.findAll();
	}

}
