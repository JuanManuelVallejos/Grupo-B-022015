package ar.edu.unq.desapp.grupoB022015.model;

public class Midfielder extends Position {

	public Midfielder(){}
	@Override
	public int pointsForGoal(int amountGoals) {
		return amountGoals;
	}
	@Override
	public Boolean isMine(String position) {
		return position.equals("Midfielder");
	}

}
