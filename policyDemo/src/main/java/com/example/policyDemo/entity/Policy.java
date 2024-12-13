package com.example.policyDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="policy")
public class Policy {
	
	//Define table fields
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="policy_number")
	@NotNull( message = "Policy number should not be null !!")
	@Positive( message = "Policy number should be positive and more than 0!!")
	private int policyNumber;
	
	@Column(name="policy_holder_name")
	@NotBlank( message = "Policy holder name should not be blank !!")
	@Size(min = 3, message = "Policy holder name should be atleast 3 chars !!")
	@Pattern(regexp = "^(?!\\d+$).*", message = "The value must not be a purely numeric string")
	private String policyHolderName;
	
	@Column(name="premium_amount")
	@NotNull( message = "Premium amount should not be null !!")
	@Positive( message = "Premium amount should be positive and more than 0 !!")
	@DecimalMin(value = "300.0",inclusive = true, message = "Premium amount should atleast 300 !!")
	private double premiumAmount;

	public Policy() {
		super();
	}

	public Policy(int policyNumber, String policyHolderName, double premiumAmount) {
		super();
		this.policyNumber = policyNumber;
		this.policyHolderName = policyHolderName;
		this.premiumAmount = premiumAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyHolderName() {
		return policyHolderName;
	}

	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}

	public double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	@Override
	public String toString() {
		return "{\n id=" + id + ",\n policyNumber=" + policyNumber + ",\n policyHolderName=" + policyHolderName
				+ ",\n premiumAmount=" + premiumAmount + "\n}";
	}
	
	
	
	
}
