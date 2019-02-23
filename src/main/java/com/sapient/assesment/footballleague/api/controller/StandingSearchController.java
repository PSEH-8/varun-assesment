package com.sapient.assesment.footballleague.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.assesment.footballleague.api.dtos.LeagueSearchResult;
import com.sapient.assesment.footballleague.service.StandingsSearchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/")
@Validated
public class StandingSearchController {

	@Autowired
	StandingsSearchService searchService;

	protected static final int HTTP_OK = 200;
	protected static final int HTTP_CREATED = 201;
	protected static final int HTTP_INTERNAL_SERVER_ERROR = 500;
	protected static final int HTTP_BAD_REQUEST = 400;

	@ApiOperation(nickname = "search standing", value = "search standing")
	@ApiResponses({ @ApiResponse(code = HTTP_OK, message = "Result retrieved successfully"),
			@ApiResponse(code = HTTP_INTERNAL_SERVER_ERROR, message = "Internal server error"),
			@ApiResponse(code = HTTP_BAD_REQUEST, message = "Bad Request") })
	@RequestMapping(path = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public LeagueSearchResult search(@RequestParam("countryName") String countryName,
			@RequestParam("leagueName") String leagueName, @RequestParam("teamName") String teamName) {
		return searchService.search(countryName, leagueName, teamName);
	}

}
