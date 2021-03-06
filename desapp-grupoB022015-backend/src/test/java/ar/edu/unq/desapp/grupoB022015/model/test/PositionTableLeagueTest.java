package ar.edu.unq.desapp.grupoB022015.model.test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import ar.edu.unq.desapp.grupoB022015.model.Defender;
import ar.edu.unq.desapp.grupoB022015.model.Forward;
import ar.edu.unq.desapp.grupoB022015.model.League;
import ar.edu.unq.desapp.grupoB022015.model.Midfielder;
import ar.edu.unq.desapp.grupoB022015.model.Player;
import ar.edu.unq.desapp.grupoB022015.model.SuperGol;
import ar.edu.unq.desapp.grupoB022015.model.User;

public class PositionTableLeagueTest {

	private SuperGol superGol;
	
	private League league;
	
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	
	private Player player6;
	private Player player5;
	private Player player4;
	private Player player3;
	private Player player2;
	private Player player1;
	

	@Test
	public void testForTableLeague() throws Throwable {
      superGol = new SuperGol();
      
      user1 = new User(superGol,"u1");
      user2 = new User(superGol,"u2");
      user3 = new User(superGol,"u3");
      user4 = new User(superGol,"u4");

      user1.createFantasyTeam("User1 Team");
      user2.createFantasyTeam("User2 Team");
      user3.createFantasyTeam("User3 Team");
      user4.createFantasyTeam("User4 Team");
      
      league = user1.createLeague("The League");
      
      user2.addmeToLeague(league);
      user3.addmeToLeague(league);
      user4.addmeToLeague(league);
      
      league.createFixtureRoundTrip(new DateTime(), 7);
      
      player1 = new Player("Jon", "Defender");
      player2 = new Player("Smith", "Defender");
      player3 = new Player("Steve", "Defender");
      player4 = new Player("Peter", "Midfielder");
      player5 = new Player("Louis", "Forward");
      player6 = new Player("Jimbo", "Forward");
      
      user1.setPlayers(player1,player2);
      user2.setPlayers(player5,player6);
      user3.setPlayers(player5,player6);
      user4.setPlayers(player3,player4);
      
      
		superGol.playerScoredNGoals(player1,1);
		superGol.playerScoredNGoals(player2,1);
		
		superGol.playerScoredNGoals(player3,1);
		superGol.playerScoredNGoals(player4,2);
		
		superGol.playerScoredNGoals(player5,1);
		superGol.playerScoredNGoals(player6,1);
		
		//player1: 3  <- u1
		//player2: 3  <- u1
		//player3: 3  <- u4
		//player4: 2  <- u4
		//player5: 1  <- u3, u2
		//player6: 1  <- u3, u2
		
		league.refreshTablePoints();
		league.updateGeneralRAnking();
		
		league.createFixtureOnlyTrip(new DateTime(), 1);
				
		// Date 1 =
		//user1 vs user4
		//user2 vs user3
		
		assertEquals(league.getTablePointsForUser(user1),(Integer) 3);
		assertEquals(league.getTablePointsForUser(user2),(Integer) 1);
		assertEquals(league.getTablePointsForUser(user3),(Integer) 1);
		assertEquals(league.getTablePointsForUser(user4),(Integer) 0);
		
	}

}
