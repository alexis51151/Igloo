// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.gestionserrures.validation;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClefNullOuVide;
import eu.telecomsudparis.csc4102.gestionserrures.GestionSerrures;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureInexistante;

public class TestTesterSerrure {

	private GestionSerrures systeme;
	private byte[] clef1;
	private byte[] clef2;
	private byte[] clef3;
	private byte[] clef4;

	@Before
	public void setUp() throws Exception {
		systeme = new GestionSerrures();
		systeme.creerSerrure("serrure1", "graine1", 0);
		clef1 = systeme.obtenirPremiereClefSerrure("serrure1");
		clef2 = systeme.obtenirSecondeClefSerrure("serrure1");
		// création d'une seconde serrure avec une graine différente
		// pour avoir deux autres clefs avec très forte probabilité
		// d'être différente
		systeme.creerSerrure("serrure2", "graine2", 0);
		clef3 = systeme.obtenirPremiereClefSerrure("serrure2");
		clef4 = systeme.obtenirSecondeClefSerrure("serrure2");
		// vérification de quatre clefs distinctes
		Assert.assertFalse(Arrays.equals(clef1, clef2));
		Assert.assertFalse(Arrays.equals(clef1, clef3));
		Assert.assertFalse(Arrays.equals(clef1, clef4));
		Assert.assertFalse(Arrays.equals(clef2, clef3));
		Assert.assertFalse(Arrays.equals(clef2, clef4));
		Assert.assertFalse(Arrays.equals(clef3, clef4));
	}

	@After
	public void tearDown() {
		systeme = null;
		clef1 = null;
		clef2 = null;
		clef3 = null;
		clef4 = null;
	}

	@Test(expected=ChaineDeCaracteresNullOuVide.class)
	public void testerSerrureTest1Jeu1() throws Exception {
		systeme.testerSerrure(null, clef1, clef2);
	}

	@Test(expected=ChaineDeCaracteresNullOuVide.class)
	public void testerSerrureTest1Jeu2() throws Exception {
		systeme.testerSerrure("", clef1, clef2);
	}

	@Test(expected=SerrureInexistante.class)
	public void testerSerrureTest2() throws Exception {
		systeme.testerSerrure("erreur", clef1, clef2);
	}

	@Test(expected=ClefNullOuVide.class)
	public void testerSerrureTest3Jeu1() throws Exception {
		systeme.testerSerrure("serrure1", null, clef2);
	}

	@Test(expected=ClefNullOuVide.class)
	public void testerSerrureTest3Jeu2() throws Exception {
		systeme.testerSerrure("serrure1", new byte[0], clef2);
	}

	@Test(expected=ClefNullOuVide.class)
	public void testerSerrureTest3Jeu3() throws Exception {
		systeme.testerSerrure("serrure1", clef1, null);
	}

	@Test(expected=ClefNullOuVide.class)
	public void testerSerrureTest3Jeu4() throws Exception {
		systeme.testerSerrure("serrure1", clef1, new byte[0]);
	}

	@Test
	public void testerSerrureTest4Jeu1() throws Exception {
		Assert.assertFalse(systeme.testerSerrure("serrure1", clef1, clef1));
	}

	@Test
	public void testerSerrureTest4Jeu2() throws Exception {
		Assert.assertFalse(systeme.testerSerrure("serrure1", clef1, clef3));
	}

	@Test
	public void testerSerrureTest4Jeu3() throws Exception {
		Assert.assertFalse(systeme.testerSerrure("serrure1", clef3, clef2));
	}

	@Test
	public void testerSerrureTest4Jeu4() throws Exception {
		Assert.assertFalse(systeme.testerSerrure("serrure1", clef3, clef1));
	}

	@Test
	public void testerSerrureTest4Jeu5() throws Exception {
		Assert.assertFalse(systeme.testerSerrure("serrure1", clef3, clef4));
	}

	@Test
	public void testerSerrureTest5Jeu1() throws Exception {
		Assert.assertTrue(systeme.testerSerrure("serrure1", clef2, clef2));
	}

	@Test
	public void testerSerrureTest5Jeu2() throws Exception {
		Assert.assertTrue(systeme.testerSerrure("serrure1", clef2, clef1));
	}

	@Test
	public void testerSerrureTest5Jeu3() throws Exception {
		Assert.assertTrue(systeme.testerSerrure("serrure1", clef2, clef3));
	}
}
