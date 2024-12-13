package com.example.policyDemo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.policyDemo.service.PolicyService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class ControllerMockMVCTest {
	

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private PolicyService policyService;
	
	
	
	@Test
	@DisplayName("Get all https request")
	@Order(1)
	public void getHttpsRequest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/policies"))
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Get by id https request")
	@Order(2)
	public void getHttpsRequestById() throws Exception{
		
		MvcResult mvcResult =mockMvc.perform(MockMvcRequestBuilders.get("/policies/121201"))
				.andExpect(status().isOk()).andReturn();
		System.out.println(mvcResult);
	}
	
	@Test
	@DisplayName("Post https request with correct data")
	@Order(3)
	public void postHttpsRequest() throws Exception{
		
		String requestBody="""
				{
					"policyNumber":12,
					"policyHolderName":"Sam",
					"premiumAmount":4000
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/policies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(content().string("Policy Created Successfully"));
		
		
	}
	
	
	@Test
	@DisplayName("Post https request with incorrect policy number first")
	@Order(4)
	public void postHttpsRequestIncorrectPolicyNumber1() throws Exception{
		
		String requestBody="""
				{
					"policyNumber":-1,
					"policyHolderName":"Sam",
					"premiumAmount":4000
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/policies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"policyNumber\":\"Policy number should be positive and more than 0!!\"}"));
		
		
	}
	
	@Test
	@DisplayName("Post https request with incorrect policy number second")
	@Order(5)
	public void postHttpsRequestIncorrectPolicyNumber2() throws Exception{
		
		String requestBody="""
				{
					"policyNumber":"abc",
					"policyHolderName":"Sam",
					"premiumAmount":4000
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/policies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Malformed JSON request: JSON parse error: Cannot deserialize value of type `int` from String \"abc\": not a valid `int` value"));
		
		
	}
	
	@Test
	@DisplayName("Post https request with incorrect policy holder name first")
	@Order(6)
	public void postHttpsRequestIncorrectPolicyHolderName1() throws Exception{
		
		String requestBody="""
				{
					"policyNumber":506,
					"policyHolderName":"ab",
					"premiumAmount":4000
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/policies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"policyHolderName\":\"Policy holder name should be atleast 3 chars !!\"}"));
		
		
	}
	
	@Test
	@DisplayName("Post https request with incorrect policy holder name second")
	@Order(7)
	public void postHttpsRequestIncorrectPolicyHolderName2() throws Exception{
		
		String requestBody="""
				{
					"policyNumber":506,
					"policyHolderName":12445,
					"premiumAmount":4000
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/policies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"policyHolderName\":\"The value must not be a purely numeric string\"}"));
		
		
	}
	
	@Test
	@DisplayName("Post https request with incorrect premium amount first")
	@Order(8)
	public void postHttpsRequestIncorrectPremiumAmount1() throws Exception{
		
		String requestBody="""
				{
					"policyNumber":23,
					"policyHolderName":"Sam",
					"premiumAmount":100
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/policies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"premiumAmount\":\"Premium amount should atleast 300 !!\"}"));
		
		
	}
	
	
	@Test
	@DisplayName("Post https request with incorrect premium amount second")
	@Order(9)
	public void postHttpsRequestIncorrectPremiumAmount2() throws Exception{
		
		String requestBody="""
				{
					"policyNumber":23,
					"policyHolderName":"Sam",
					"premiumAmount":"abc"
				}
				""";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/policies")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("Malformed JSON request: JSON parse error: Cannot deserialize value of type `double` from String \"abc\": not a valid `double` value (as String to convert)"));
		
		
	}
	
	
	
	
	
	
}
