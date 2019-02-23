package com.sapient.assesment.footballleague.api.service;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.sapient.assesment.footballleague.api.dtos.LeagueSearchResult;
import com.sapient.assesment.footballleague.service.StandingsSearchService;
import com.sapient.assesment.footballleague.service.client.FootballApiProxy;
import com.sapient.assesment.footballleague.service.dtos.CompetitionStandingDetail;
import com.sapient.assesment.footballleague.service.dtos.CountryDetail;
import com.sapient.assesment.footballleague.service.dtos.LeagueDetail;
import com.sapient.assesment.footballleague.service.exceptions.StandingNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class StandingSearchServiceTest {

	// Service under test
	@InjectMocks
	private StandingsSearchService service;

	// Mocks
	@Mock
	FootballApiProxy proxy;

	@Before
	public void setup() {
		service = new StandingsSearchService();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldCallAllMethodsFromProxy() {
		Mockito.when(proxy.countryDetails(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(this.createCountrDetailArr());
		Mockito.when(proxy.leagueDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(this.createLeagueDetailArr());
		Mockito.when(proxy.standingDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(this.createStandingDetailArr());
		LeagueSearchResult result = service.search("England","Championship", "Norwich City");
		Mockito.verify(proxy, Mockito.times(1)).countryDetails(Mockito.anyString(), Mockito.anyString());
		Assert.assertThat(result.getPosition(), Matchers.is("1"));
		Assert.assertThat(result.getCountry(), Matchers.is("1-England"));
	}
	
	
	@Test(expected = StandingNotFoundException.class)
	public void shoudThrowException() {
		CompetitionStandingDetail[] arr = this.createStandingDetailArr();
		arr[0].setTeamName("Randome");
		Mockito.when(proxy.countryDetails(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(this.createCountrDetailArr());
		Mockito.when(proxy.leagueDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(this.createLeagueDetailArr());
		Mockito.when(proxy.standingDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
				.thenReturn(arr);
		LeagueSearchResult result = service.search("England","Championship", "Norwich City");
		Mockito.verify(proxy, Mockito.times(1)).countryDetails(Mockito.anyString(), Mockito.anyString());
		Assert.assertThat(result.getPosition(), Matchers.is("1"));
	}


	private CompetitionStandingDetail[] createStandingDetailArr() {
		CompetitionStandingDetail detail = new CompetitionStandingDetail();
		detail.setCountryName("England");
		detail.setOverallLeaguePosition("1");
		detail.setLeagueId("2");
		detail.setLeagueName("Championship");
		detail.setTeamName("Norwich City");
		return new CompetitionStandingDetail[] {detail};
	}

	private LeagueDetail[] createLeagueDetailArr() {
		LeagueDetail detail = new LeagueDetail();
		detail.setCountryId("1");
		detail.setCountryName("England");
		detail.setLeagueId("2");
		detail.setLeagueName("Championship");
		return new LeagueDetail[] {detail};
		}
	

	private CountryDetail[] createCountrDetailArr() {
		CountryDetail detail = new CountryDetail();
		detail.setCountryId("1");
		detail.setCountryName("England");
		return new CountryDetail[] { detail };
	}

}
