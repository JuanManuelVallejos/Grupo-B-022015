package ar.edu.unq.desapp.grupoB022015.test;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.desapp.grupoB022015.model.Defender;


public class DefenderTest {
	
	
	private Defender defender;

	@Before
    public void init() {
      defender = new Defender();
    }
	
	@Test
	public void test() {
		assert(defender.calculatePoints() == 3);
	}

}