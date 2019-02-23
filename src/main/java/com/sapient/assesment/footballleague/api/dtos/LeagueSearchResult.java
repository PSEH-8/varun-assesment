package com.sapient.assesment.footballleague.api.dtos;

public class LeagueSearchResult {

    private String country;
    private String team;
    private String league;
    private String position;
    
    public LeagueSearchResult(String countryId, String countryName, String leagueId, String leagueName, String teamId, String teamName, String position) {
    	this.country = this.createOutput(countryId, countryName);
    	this.league = this.createOutput(leagueId, leagueName);
    	this.team = this.createOutput(teamId, teamName);
        this.position = position;
    }
 
   
    
    private String createOutput(String id,String name) {
 	   StringBuilder builder = new StringBuilder();
 	   builder.append(id);
 	   builder.append("-");
 	   builder.append(name);
 	   return builder.toString();

    }



	public String getCountry() {
		return country;
	}



	public String getTeam() {
		return team;
	}



	public String getLeague() {
		return league;
	}



	public String getPosition() {
		return position;
	}
    
}