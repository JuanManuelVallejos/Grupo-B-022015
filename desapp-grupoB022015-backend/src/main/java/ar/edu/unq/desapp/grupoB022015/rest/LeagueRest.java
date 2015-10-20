package ar.edu.unq.desapp.grupoB022015.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ar.edu.unq.desapp.grupoB022015.model.League;
import ar.edu.unq.desapp.grupoB022015.model.SuperGol;
import ar.edu.unq.desapp.grupoB022015.services.LeagueService;

@Path("/league")
public class LeagueRest {
	
	private LeagueService leagueService;

	public LeagueService getLeagueService() {
		return leagueService;
	}

	public void setLeagueService(LeagueService leagueService) {
		this.leagueService = leagueService;
	}
	
	@POST
	@Path("/create")
	@Produces("application/json")
	public Response createLeague(@FormParam("name") String leaguename){
		
		League league = new League(leaguename, new SuperGol());
		return Response.ok(league).build();
	}
	
	@GET
    @Path("/list")
    @Produces("application/json")
    public List<League> getLeague() {
        List<League> league = leagueService.retriveAll();
        return league;
    }
}