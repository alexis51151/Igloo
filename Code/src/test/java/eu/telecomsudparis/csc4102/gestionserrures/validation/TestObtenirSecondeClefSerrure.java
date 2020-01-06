// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.gestionserrures.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionserrures.GestionSerrures;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureInexistante;

public class TestObtenirSecondeClefSerrure {

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

	@Test(expected=ChaineDeCaracteresNullOuVide.class)
	public void obtenirSecondeClefSerrureTest1Jeu1() throws Exception {
		systeme.obtenirSecondeClefSerrure(null);
	}

	@Test(expected=ChaineDeCaracteresNullOuVide.class)
	public void obtenirSecondeClefSerrureTest1Jeu2() throws Exception {
		systeme.obtenirSecondeClefSerrure("");
	}

	@Test(expected=SerrureInexistante.class)
	public void obtenirSecondeClefSerrureTest2() throws Exception {
		systeme.obtenirSecondeClefSerrure("erreur");
	}

	@Test
	public void obtenirSecondeClefSerrureTest3() throws Exception {
		byte[] secondeClef = systeme.obtenirSecondeClefSerrure("serrure1");
		Assert.assertNotNull(secondeClef);
		Assert.assertNotEquals(secondeClef.length, 0);
//		System.out.println(SimSerruresInterface.clefToString(secondeClef));
	}
}
