// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.gestionserrures.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.gestionserrures.GestionSerrures;

public class TestListerSerrures {

	private GestionSerrures systeme;

	@Before
	public void setUp() throws Exception {
		systeme = new GestionSerrures();
		systeme.creerSerrure("serrure1", "graine1", 0);
		systeme.creerSerrure("serrure2", "graine2", 0);
		systeme.creerSerrure("serrure3", "graine3", 0);
	}

	@After
	public void tearDown() {
		systeme = null;
	}

	@Test
	public void listerSerrureTest1() throws Exception {
		String[] serrures = systeme.listerSerrures();
		Assert.assertEquals(serrures.length, 3);
//		System.out.println(Arrays.stream(systeme.listerSerrures()).collect(Collectors.joining("\n")));
	}
}
