package org.alkemy.challengedisney.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.alkemy.challengedisney.dto.CustomUserDTO;
import org.alkemy.challengedisney.model.CustomUser;
import org.alkemy.challengedisney.util.CustomMail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper om = new ObjectMapper();	
	
	@InjectMocks 
	AuthController auth;
		
	@MockBean
	CustomMail mail;

	@Test
	public void whenUserDoRegisterReturnCreated() throws JsonProcessingException, Exception {
		doNothing().when(mail).send("", "", "");
		
		CustomUserDTO inputUser = new CustomUserDTO();
		String timeStamp = System.currentTimeMillis()+"";
		inputUser.setEmail(timeStamp + "mike@localhost.local");
		inputUser.setUserName(timeStamp + "Micaelaa");
		inputUser.setPassWord(timeStamp + "123456");
		
		MvcResult result;
		String resultContent;
		ResultActions resultAction = mockMvc.perform(post("/auth/register").content(
				om.writeValueAsString(inputUser)
				).contentType(MediaType.APPLICATION_JSON_VALUE));
		
		result = resultAction.andExpect(status().isCreated()).andReturn();

		resultContent = result.getResponse().getContentAsString();
		CustomUser responseUser = om.readValue(resultContent, CustomUser.class); 
		assertEquals(inputUser.getEmail(), responseUser.getEmail());
		assertEquals(inputUser.getUserName(), responseUser.getUserName());
	}
}
