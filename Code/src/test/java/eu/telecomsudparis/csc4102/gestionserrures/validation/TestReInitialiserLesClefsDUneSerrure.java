// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.gestionserrures.validation;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionserrures.GestionSerrures;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureInexistante;

public class TestReInitialiserLesClefsDUneSerrure {

	private GestionSerrures systeme;

	@Before
	public void setUp() throws Exception {
		systeme = new GestionSerrures();
		systeme.creerSerrure("serrure1", "graine1", 0);
	}

	@After
	public void tearDown() {
		systeme = null;
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void reinitialiserSerrureTest1Jeu1() throws Exception {
		systeme.reInitialiserLesClefsDUneSerrure(null, "graine1", 0);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void reinitialiserSerrureTest1Jeu2() throws Exception {
		systeme.reInitialiserLesClefsDUneSerrure("", "graine1", 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void reinitialiserSerrureTest2Jeu1() throws Exception {
		systeme.reInitialiserLesClefsDUneSerrure("serrure1", null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void reinitialiserSerrureTest2Jeu2() throws Exception {
		systeme.reInitialiserLesClefsDUneSerrure("serrure1", "", 0);
	}

	@Test(expected = SerrureInexistante.class)
	public void reinitialiserSerrureTest3() throws Exception {
		systeme.reInitialiserLesClefsDUneSerrure("serrure2", "", 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void reinitialiserTest4() throws Exception {
		systeme.reInitialiserLesClefsDUneSerrure("serrure1", "graine1", systeme.obtenirSelSerrure("serrure1"));
	}

	@Test
	public void reinitialiserTest5Jeu1() throws Exception {
		byte[] premiereClef = systeme.obtenirPremiereClefSerrure("serrure1");
		byte[] secondeClef = systeme.obtenirSecondeClefSerrure("serrure1");
		String graine = systeme.obtenirGraineSerrure("serrure1");
		int sel = systeme.obtenirSelSerrure("serrure1");
		systeme.reInitialiserLesClefsDUneSerrure("serrure1", "graine2", systeme.obtenirSelSerrure("serrure1"));
		Assert.assertNotEquals(graine, systeme.obtenirGraineSerrure("serrure1"));
		Assert.assertNotEquals(sel, systeme.obtenirSelSerrure("serrure1"));
		Assert.assertFalse(Arrays.equals(systeme.obtenirPremiereClefSerrure("serrure1"), premiereClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirSecondeClefSerrure("serrure1"), premiereClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirPremiereClefSerrure("serrure1"), secondeClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirSecondeClefSerrure("serrure1"), secondeClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirPremiereClefSerrure("serrure1"),
				systeme.obtenirSecondeClefSerrure("serrure1")));
	}

	@Test
	public void reinitialiserTest5Jeu2() throws Exception {
		byte[] premiereClef = systeme.obtenirPremiereClefSerrure("serrure1");
		byte[] secondeClef = systeme.obtenirSecondeClefSerrure("serrure1");
		String graine = systeme.obtenirGraineSerrure("serrure1");
		int sel = systeme.obtenirSelSerrure("serrure1");
		systeme.reInitialiserLesClefsDUneSerrure("serrure1", "graine1", systeme.obtenirSelSerrure("serrure1") + 10);
		Assert.assertEquals(graine, systeme.obtenirGraineSerrure("serrure1"));
		Assert.assertNotEquals(sel, systeme.obtenirSelSerrure("serrure1"));
		Assert.assertFalse(Arrays.equals(systeme.obtenirPremiereClefSerrure("serrure1"), premiereClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirSecondeClefSerrure("serrure1"), premiereClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirPremiereClefSerrure("serrure1"), secondeClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirSecondeClefSerrure("serrure1"), secondeClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirPremiereClefSerrure("serrure1"),
				systeme.obtenirSecondeClefSerrure("serrure1")));
	}

	@Test
	public void reinitialiserTest5Jeu3() throws Exception {
		byte[] premiereClef = systeme.obtenirPremiereClefSerrure("serrure1");
		byte[] secondeClef = systeme.obtenirSecondeClefSerrure("serrure1");
		String graine = systeme.obtenirGraineSerrure("serrure1");
		int sel = systeme.obtenirSelSerrure("serrure1");
		systeme.reInitialiserLesClefsDUneSerrure("serrure1", "graine2", systeme.obtenirSelSerrure("serrure1") + 10);
		Assert.assertNotEquals(graine, systeme.obtenirGraineSerrure("serrure1"));
		Assert.assertNotEquals(sel, systeme.obtenirSelSerrure("serrure1"));
		Assert.assertFalse(Arrays.equals(systeme.obtenirPremiereClefSerrure("serrure1"), premiereClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirSecondeClefSerrure("serrure1"), premiereClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirPremiereClefSerrure("serrure1"), secondeClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirSecondeClefSerrure("serrure1"), secondeClef));
		Assert.assertFalse(Arrays.equals(systeme.obtenirPremiereClefSerrure("serrure1"),
				systeme.obtenirSecondeClefSerrure("serrure1")));
	}
}
