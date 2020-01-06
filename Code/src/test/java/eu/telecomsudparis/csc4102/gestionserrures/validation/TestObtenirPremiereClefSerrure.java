// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.gestionserrures.validation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionserrures.GestionSerrures;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureInexistante;

public class TestObtenirPremiereClefSerrure {

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
	public void obtenirPremiereClefSerrureTest1Jeu1() throws Exception {
		systeme.obtenirPremiereClefSerrure(null);
	}

	@Test(expected=ChaineDeCaracteresNullOuVide.class)
	public void obtenirPremiereClefSerrureTest1Jeu2() throws Exception {
		systeme.obtenirPremiereClefSerrure("");
	}

	@Test(expected=SerrureInexistante.class)
	public void obtenirPremiereClefSerrureTest2() throws Exception {
		systeme.obtenirPremiereClefSerrure("erreur");
	}

	@Test
	public void obtenirPremiereClefSerrureTest3() throws Exception {
		byte[] premiereClef = systeme.obtenirPremiereClefSerrure("serrure1");
		Assert.assertNotNull(premiereClef);
		Assert.assertNotEquals(premiereClef.length, 0);
//		System.out.println(SimSerruresInterface.clefToString(premiereClef));
	}
}
