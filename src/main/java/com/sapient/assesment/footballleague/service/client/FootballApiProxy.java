package com.sapient.assesment.footballleague.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.assesment.footballleague.service.dtos.CompetitionStandingDetail;
import com.sapient.assesment.footballleague.service.dtos.CountryDetail;
import com.sapient.assesment.footballleague.service.dtos.LeagueDetail;

/**
 * Client to the service	
 */
@FeignClient(name="FootballApi" ,url="https://apifootball.com/api/")
public interface FootballApiProxy {
	
	@GetMapping("/")
    public CountryDetail[] countryDetails(@RequestParam("action") String action, @RequestParam("APIkey") String apiKey);
	
	
	@GetMapping("/")
	public LeagueDetail[] leagueDetails(@RequestParam("action") String action,@RequestParam("country_id") String countryId, @RequestParam("APIkey") String apiKey);
	
	@GetMapping("/")
    public CompetitionStandingDetail[] standingDetails(@RequestParam("action") String action, @RequestParam("league_id") String leagueId, @RequestParam("APIkey") String apiKey);

}
