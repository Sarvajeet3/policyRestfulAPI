package com.example.policyDemo.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.policyDemo.entity.Policy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class PolicyDAOImpl implements PolicyDAO{
	
	private EntityManager entityManager;

	public PolicyDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<Policy> findALL() {
		// TODO Auto-generated method stub
		
		TypedQuery<Policy> theQuery = entityManager.createQuery("from Policy",Policy.class);
		
		return theQuery.getResultList();
	}

	@Override
	public Policy findById(int id) {
		// TODO Auto-generated method stub
		
		Policy policy = entityManager.find(Policy.class, id);
		
		return policy;
	}

	@Override
	public Policy save(Policy policy) {
		// TODO Auto-generated method stub
		entityManager.persist(policy);
		
		return policy;
	}

	@Override
	public List<Policy> findAll() {
		TypedQuery<Policy> theQuery = entityManager.createQuery("From Policy",Policy.class);
		
		return theQuery.getResultList();
	}

}
