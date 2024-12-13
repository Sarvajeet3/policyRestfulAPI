package com.example.policyDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.example.policyDemo.entity.Policy;
import com.example.policyDemo.service.PolicyService;
import jakarta.validation.ConstraintViolationException;


@TestPropertySource("/application.properties")
@SpringBootTest
class PolicyDemoApplicationTests {
	
	@Autowired
	private PolicyService policyService;
	
	Policy policy;
	
	@Test
	@DisplayName("Save Service execution with correct data")
	void ServiceWithCorrectDataTest() {
		policy=new Policy();
		policy.setPolicyHolderName("Sarvajeet");
		policy.setPolicyNumber(50);
		policy.setPremiumAmount(400);
		assertEquals("Policy Created Successfully",policyService.save(policy),"data should be saved");
	}
	
	
	@Test
	@DisplayName("Save Service execution with incorrect policy holder name")
	void ServiceWithInCorrectPolicyHolderName() {
		policy=new Policy();
		policy.setPolicyHolderName("ab");
		policy.setPolicyNumber(23);
		policy.setPremiumAmount(400);
		assertThrows(ConstraintViolationException.class, (()->{policyService.save(policy);}),"Constraint violation exception should be thrown");
	}
	
	@Test
	@DisplayName("Save Service execution with incorrect policy number")
	void ServiceWithInCorrectPolicyNumber() {
		policy=new Policy();
		policy.setPolicyHolderName("sarvajeet");
		policy.setPolicyNumber(-1);
		policy.setPremiumAmount(400);
		assertThrows(ConstraintViolationException.class, (()->{policyService.save(policy);}),"Constraint violation exception should be thrown");
	}

	@Test
	@DisplayName("Save Service execution with incorrect premium amount")
	void ServiceWithInCorrectPremiumAmount() {
		policy=new Policy();
		policy.setPolicyHolderName("sarvajeet");
		policy.setPolicyNumber(45);
		policy.setPremiumAmount(100);
		assertThrows(ConstraintViolationException.class, (()->{policyService.save(policy);}),"Constraint violation exception should be thrown");
	}
	
	@Test
	@DisplayName("Get Service execution with correct id")
	void ServiceWithGetById() {
		assertNotNull(policyService.findById(1),"Output Should not be null");
	}
	
	@Test
	@DisplayName("Get Service execution with incorrect id")
	void ServiceWithGetByIdIncorrect() {
		assertNull(policyService.findById(40),"Output Should not be null");
	}
	
	
}
