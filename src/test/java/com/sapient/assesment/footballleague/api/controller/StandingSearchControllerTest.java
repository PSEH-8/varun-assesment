package com.sapient.assesment.footballleague.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StandingSearchControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void shouldGetResultsFromAPI() throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.get("/v1/search?countryName=England&leagueName=Championship&teamName=Norwich City")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.position").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.country").value("169-England"));

	}
	
	
	@Test
	public void testRequestParamMissing() throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.get("/v1/search?countryName=England&leagueName=Championship&")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}
	
	
	@Test(expected = NestedServletException.class)
	public void shouldFailWhenWrongRequestParam() throws Exception {
		mvc.perform(MockMvcRequestBuilders
				.get("/v1/search?countryName=Random&leagueName=Championship&teamName=Norwich City")
				.accept(MediaType.APPLICATION_JSON));
	}
	
}





