package com.sapient.assesment.footballleague.service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for country data 
 *  
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CountryDetail {

	@JsonProperty("country_id")
	private String countryId;
	
	@JsonProperty("country_name")
	private String countryName;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
