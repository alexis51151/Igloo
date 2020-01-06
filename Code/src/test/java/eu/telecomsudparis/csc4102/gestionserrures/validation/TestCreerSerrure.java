// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.gestionserrures.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionserrures.GestionSerrures;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureDejaPresente;

public class TestCreerSerrure {

	private GestionSerrures systeme;

	@Before
	public void setUp() {
		systeme = new GestionSerrures();
	}

	@After
	public void tearDown() {
		systeme = null;
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerSerrureTest1Jeu1() throws Exception {
		systeme.creerSerrure(null, "graine1", 0);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurSerrureTest1Jeu2() throws Exception {
		systeme.creerSerrure("", "graine1", 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creerSerrureTest2Jeu1() throws Exception {
		systeme.creerSerrure("serrure1", null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void creerSerrureTest2Jeu2() throws Exception {
		systeme.creerSerrure("serrure1", "", 0);
	}

	@Test(expected = SerrureDejaPresente.class)
	public void creerSerrureTest4Puis3() throws Exception {
		try {
			systeme.creerSerrure("serrure1", "graine2", 0);
		} catch (SerrureDejaPresente e) {
			Assert.fail();
		}
		systeme.creerSerrure("serrure1", "graine2", 0);
	}
}
