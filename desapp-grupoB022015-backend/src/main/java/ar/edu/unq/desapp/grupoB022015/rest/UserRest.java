package ar.edu.unq.desapp.grupoB022015.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ar.edu.unq.desapp.grupoB022015.model.FantasyTeam;
import ar.edu.unq.desapp.grupoB022015.model.Player;
import ar.edu.unq.desapp.grupoB022015.model.SuperGol;
import ar.edu.unq.desapp.grupoB022015.model.User;
import ar.edu.unq.desapp.grupoB022015.model.exceptions.MaximumNumberOfPlayersInTeamException;
import ar.edu.unq.desapp.grupoB022015.services.LeagueService;
import ar.edu.unq.desapp.grupoB022015.services.PlayerService;
import ar.edu.unq.desapp.grupoB022015.services.PointsForUserService;
import ar.edu.unq.desapp.grupoB022015.services.UserService;

@Path("/user")
public class UserRest {
	
	private UserService userService;
	private LeagueService leagueService;

	public LeagueService getLeagueService() {
		return leagueService;
	}

	public void setLeagueService(LeagueService leagueService) {
		this.leagueService = leagueService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private PlayerService playerService;

	public PlayerService getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@PUT
	@Path("/create/{userName}/{idGoogle}")
	@Produces("application/json")
	public Response createUser(@PathParam("userName") String userName, @PathParam("idGoogle") String idGoogle){
		User user = getUserService().findByIdGoogle(idGoogle);
		if(user == null){
			user = new User();
			user.assignParameters(userName, idGoogle);
			getUserService().save(user);
		}
		return Response.ok(user).build();
	}
	
	@GET
	@Path("/getUser/{idGoogle}")
	@Produces("application/json")
	public User getUser(@PathParam("idGoogle") String idGoogle){
		User user =  getUserService().findByIdGoogle(idGoogle);
		return user;
	}
	
	@POST
	@Path("/createMyTeam/{teamname}/{idGoogle}")
	@Produces("application/json")
	public User createMyTeam(@PathParam("teamname") String teamname, @PathParam("idGoogle") String idGoogle) {
		 User theUser = getUser(idGoogle);
		 FantasyTeam myTeam = new FantasyTeam(teamname);
		 theUser.setFantasyTeam(myTeam);
		 
		 getUserService().update(theUser);
		 
		 return theUser;
	}
	
	
	@GET
    @Path("/list")
    @Produces("application/json")
    public List<User> getUsers() {
        List<User> users = getUserService().retriveAll();
        return users;
    }
	
	@POST
	@Path("/updateDateForUsers/")
	@Produces("application/json")
	public void updateDateForUsers() {
		 List<User> users = getUserService().retriveAll();
		 int currentDate = getLeagueService().retriveAll().get(0).getCurrentDate();
		 for(User user: users){
			 if(user.getTeam()!=null)
				 updateOneUser(user, currentDate);
		 }
	}
	
	public void updateOneUser(User user, int currentDate) { 
		 List<Player> myTeam = user.getTeam().getPlayers();
		 int points = 0;
		 for(Player player: myTeam){
			 points = points + player.getPointsForDate()[currentDate];
		 }
		 int oldPoints = user.getRankingPoints();
		 user.setRankingPoints(oldPoints + points);
		 getUserService().update(user);
	}
}
