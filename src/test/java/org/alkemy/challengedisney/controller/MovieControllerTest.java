package org.alkemy.challengedisney.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.alkemy.challengedisney.configuration.JwtRequestFilter;
import org.alkemy.challengedisney.configuration.JwtTokenUtil;
import org.alkemy.challengedisney.dto.request.MovieRequestDTO;
import org.alkemy.challengedisney.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {
	
	ObjectMapper om = new ObjectMapper();	
	
	@Autowired
	private MockMvc mockMvc;
    
	@InjectMocks 
	JwtRequestFilter jwtRequest;
	
	@MockBean
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Test
	public void whenFieldIsMissingWillFail() throws JsonProcessingException, Exception {
		when(userDetailsServiceImpl.loadUserByUsername("test"))
		.thenReturn(new User("test", "sdfa",
				new ArrayList<>()));
		
		MovieRequestDTO movieDTO = new MovieRequestDTO();
				
		String token = jwtTokenUtil.generateToken(new User("test", "sdfa",
				new ArrayList<>()));	
				mockMvc.perform(post("/movies")
					 .content(om.writeValueAsString(movieDTO))
				     .header("Authorization", "Bearer " + token)
				     .contentType(MediaType.APPLICATION_JSON)
				     )					  
					 .andExpect(status().isBadRequest())
					 .andExpect(jsonPath("$.message")
			         .value("El género es requerido"));
	}
}
