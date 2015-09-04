package ar.edu.unq.desapp.grupoB022015.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;

public class League {

	private String name;
	private List<Date> fixture;
	private List<User> ranking;
	
	public League(String leagueName){
		this.name = leagueName;
		this.fixture = new ArrayList<Date>();
		this.ranking = new ArrayList<User>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public List<Date> getFixture(){
		return this.fixture;
	}
	
	public Date getCurrentDate(){
		for ( Date currentDate: fixture)
			if(currentDate.dateMine(new DateTime()))
				return currentDate;
		
		return null;
	}
	
	/*
	 * CHANGE ALL THIS ALGORITM
	 * 
	public void createFixtureRoundTrip(DateTime start, int durationOfDateinDays){
		createFixtureOnlyTrip(start,durationOfDateinDays);
		int limitLoop = fixture.size();
		DateTime startNewDate = start.plusDays(durationOfDateinDays * limitLoop);
		DateTime endNewDate = startNewDate.plusDays(durationOfDateinDays);
		for(int index = 0; index < limitLoop;index++){
			fixture.add(fixture.get(index).myOpposite(startNewDate,endNewDate));
			startNewDate = startNewDate.plusDays(durationOfDateinDays);
			endNewDate = endNewDate.plusDays(durationOfDateinDays);
		}
	}
	
	public void createFixtureOnlyTrip(DateTime start, int durationOfDateinDays){
		int totalSize = ranking.size();
		
		List<User> usersLocals = ranking.subList(0, totalSize/2);
		List<User> usersVisitors = ranking.subList(totalSize/2, totalSize);
		Collections.reverse(usersVisitors);
		
		DateTime currentDate = start;
		DateTime finalDate = start.plusDays(durationOfDateinDays);

		for(int numDate = 0; numDate < totalSize; numDate++){
			fixture.add(createDateWith(currentDate, finalDate, usersLocals,usersVisitors));
			usersVisitors = firstEnd(usersVisitors);
			currentDate = finalDate;
			finalDate = finalDate.plusDays(durationOfDateinDays);
		}
	}
	*/
	public List<User> firstEnd(List<User> users){
		User firstU = users.get(0);
		List<User> copyU = users;
		copyU.remove(0);
		copyU.add(firstU);
		return copyU;
	}
	
	public Date createDateWith(DateTime startD, DateTime endD, List<User> usersA,List<User> usersB){
		List<Match> matchsOfDate = new ArrayList<Match>();
		for(int indexU = 0; indexU < usersA.size(); indexU ++)
			matchsOfDate.add(new Match(usersA.get(indexU),usersB.get(indexU)));
		return new Date(matchsOfDate, startD, endD);
	}
	
	/**
	 * Search the date
	 * @param numDate is the number of the  date
	 * @return the wanted date
	 */
	public Date getDate(int numDate){
		return fixture.get(numDate-1);
	}
	
	public void addUser(User aUser){
		ranking.add(aUser);
	}
	
	public void updateRanking(){
		Collections.sort(ranking);
	}
	
}
