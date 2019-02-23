package com.sapient.assesment.footballleague.service;

import static com.sapient.assesment.footballleague.util.ActionsEnum.GET_COUNTRIES;
import static com.sapient.assesment.footballleague.util.ActionsEnum.GET_LEAGUES;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.assesment.footballleague.api.dtos.LeagueSearchResult;
import com.sapient.assesment.footballleague.service.client.FootballApiProxy;
import com.sapient.assesment.footballleague.service.dtos.CompetitionStandingDetail;
import com.sapient.assesment.footballleague.service.dtos.CountryDetail;
import com.sapient.assesment.footballleague.service.dtos.LeagueDetail;
import com.sapient.assesment.footballleague.service.exceptions.CountryNotFoundException;
import com.sapient.assesment.footballleague.service.exceptions.LeagueNotFoundException;
import com.sapient.assesment.footballleague.service.exceptions.StandingNotFoundException;
import com.sapient.assesment.footballleague.util.ActionsEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * Service layer for all the business logic for search standings
 */
@Service
@Slf4j
public class StandingsSearchService {

	@Autowired
    private FootballApiProxy apiProxy;
	//TODO : Pass the key as env variable
	private static final String API_KEY = "9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978"; 

    public LeagueSearchResult search(String countryName,String leagueName,String teamName){
    	String countryId = getCountryId(countryName);
    	String leagueId = getLeagueId(countryId,leagueName);
    	CompetitionStandingDetail standingDetail = getStandingDetails(leagueId,teamName);
    	return new LeagueSearchResult(countryId, countryName, leagueId, leagueName, "unknown", teamName, standingDetail.getOverallLeaguePosition());
    	
    }
    
    private CompetitionStandingDetail getStandingDetails(String leagueId, String teamName) {
		CompetitionStandingDetail[] standingDetails = apiProxy.standingDetails(ActionsEnum.GET_STANDINGS.getAction(), leagueId, API_KEY);
		Optional<CompetitionStandingDetail> standingOpt = 
		        Stream.of(standingDetails)        
		            .filter(standing->teamName.equalsIgnoreCase(standing.getTeamName()))	
		           .findFirst();
		return standingOpt.orElseThrow(()->new StandingNotFoundException("standing with name " + teamName + " not found"));
	}

	private String getLeagueId(String countryId, String leagueName) {
		LeagueDetail[] leagueDetails = apiProxy.leagueDetails(GET_LEAGUES.getAction(), countryId, API_KEY);
		Optional<LeagueDetail> leagueOpt = 
		        Stream.of(leagueDetails)        
		            .filter(league->leagueName.equalsIgnoreCase(league.getLeagueName()))	
		           .findFirst();
		return leagueOpt.orElseThrow(()->new LeagueNotFoundException("league with name " + leagueName + " not found")).getLeagueId();
	}

	private String getCountryId(String countryName) {
    	CountryDetail[] countries = apiProxy.countryDetails(GET_COUNTRIES.getAction(), API_KEY);
    	
        Optional<CountryDetail> countryOpt = 
        Stream.of(countries)        
            .filter(country->countryName.equalsIgnoreCase(country.getCountryName()))
            .findFirst();
        return countryOpt.orElseThrow(()->new CountryNotFoundException("country with name " + countryName + " not found")).getCountryId();
            
    }
	
	
}
