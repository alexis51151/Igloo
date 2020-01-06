// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.gestionserrures.unitaires;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClefNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;
import eu.telecomsudparis.csc4102.gestionserrures.GestionSerrures;
import eu.telecomsudparis.csc4102.gestionserrures.Serrure;

public class TestSerrure {

	private Serrure serrure1;
	private Serrure serrure2;
	private byte[] clef1;
	private byte[] clef2;
	private byte[] clef3;
	private byte[] clef4;

	@Before
	public void setUp() throws ChaineDeCaracteresNullOuVide, ProblemeDansGenerationClef {
		serrure1 = new Serrure("serrure1", "graine1", 0);
		clef1 = serrure1.getPremiereClef();
		clef2 = serrure1.getSecondeClef();
		// création d'une seconde serrure avec une graine différente
		// pour avoir deux autres clefs avec très forte probabilité
		// d'être différente
		serrure2 = new Serrure("serrure2", "graine2", 0);
		clef3 = serrure2.getPremiereClef();
		clef4 = serrure2.getSecondeClef();
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
		serrure1 = null;
		serrure2 = null;
		clef1 = null;
		clef2 = null;
		clef3 = null;
		clef4 = null;
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurSerrureTest1Jeu1() throws Exception {
		new Serrure(null, "graine1", 0);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurSerrureTest1Jeu2() throws Exception {
		new Serrure("", "graine1", 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurSerrureTest2Jeu1() throws Exception {
		new Serrure("serrure1", null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructeurSerrureTest2Jeu2() throws Exception {
		new Serrure("serrure1", "", 0);
	}

	@Test
	public void constructeurSerrureTest3() throws Exception {
		Serrure serrure = new Serrure("serrure1", "graine2", 0);
		Assert.assertNotNull(serrure);
		byte[] premiereClef = serrure.getPremiereClef();
		Assert.assertNotNull(premiereClef);
		byte[] secondeClef = serrure.getSecondeClef();
		Assert.assertNotNull(secondeClef);
	}

	@Test(expected = ClefNullOuVide.class)
	public void testerSerrureTest1Jeu1() throws Exception {
		serrure1.testerSerrure(null, clef2);
	}

	@Test(expected = ClefNullOuVide.class)
	public void testerSerrureTest1Jeu2() throws Exception {
		serrure1.testerSerrure(new byte[0], clef2);
	}

	@Test(expected = ClefNullOuVide.class)
	public void testerSerrureTest1Jeu3() throws Exception {
		serrure1.testerSerrure(clef1, null);
	}

	@Test(expected = ClefNullOuVide.class)
	public void testerSerrureTest1Jeu4() throws Exception {
		serrure1.testerSerrure(clef1, new byte[0]);
	}

	@Test
	public void testerSerrureTest2Jeu1() throws Exception {
		Assert.assertFalse(serrure1.testerSerrure(clef1, clef1));
		Assert.assertTrue(Arrays.equals(clef1, serrure1.getPremiereClef()));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getSecondeClef()));
	}

	@Test
	public void testerSerrureTest2Jeu2() throws Exception {
		Assert.assertFalse(serrure1.testerSerrure(clef1, clef3));
		Assert.assertTrue(Arrays.equals(clef1, serrure1.getPremiereClef()));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getSecondeClef()));
	}

	@Test
	public void testerSerrureTest2Jeu3() throws Exception {
		Assert.assertFalse(serrure1.testerSerrure(clef3, clef2));
		Assert.assertTrue(Arrays.equals(clef1, serrure1.getPremiereClef()));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getSecondeClef()));
	}

	@Test
	public void testerSerrureTest2Jeu4() throws Exception {
		Assert.assertFalse(serrure1.testerSerrure(clef3, clef1));
		Assert.assertTrue(Arrays.equals(clef1, serrure1.getPremiereClef()));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getSecondeClef()));
	}

	@Test
	public void testerSerrureTest2Jeu5() throws Exception {
		Assert.assertFalse(serrure1.testerSerrure(clef3, clef4));
		Assert.assertTrue(Arrays.equals(clef1, serrure1.getPremiereClef()));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getSecondeClef()));
	}

	@Test
	public void testerSerrureTest3Jeu1() throws Exception {
		Assert.assertTrue(serrure1.testerSerrure(clef2, clef2));
		Assert.assertTrue(Arrays.equals(clef1, serrure1.getPremiereClef()));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getSecondeClef()));
	}

	@Test
	public void testerSerrureTest3Jeu2() throws Exception {
		Assert.assertTrue(serrure1.testerSerrure(clef2, clef1));
		Assert.assertTrue(Arrays.equals(clef1, serrure1.getPremiereClef()));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getSecondeClef()));
	}

	@Test
	public void testerSerrureTest3Jeu3() throws Exception {
		Assert.assertTrue(serrure1.testerSerrure(clef2, clef3));
		Assert.assertTrue(Arrays.equals(clef1, serrure1.getPremiereClef()));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getSecondeClef()));
	}

	@Test(expected = ClefNullOuVide.class)
	public void testerEssayerDOuvrirLaPorteTest1Jeu1() throws Exception {
		serrure1.essayerDOuvrirLaPorte(null, clef2);
	}

	@Test(expected = ClefNullOuVide.class)
	public void testerEssayerDOuvrirLaPorteTest1Jeu2() throws Exception {
		serrure1.essayerDOuvrirLaPorte(new byte[0], clef2);
	}

	@Test(expected = ClefNullOuVide.class)
	public void testerEssayerDOuvrirLaPorteTest1Jeu3() throws Exception {
		serrure1.essayerDOuvrirLaPorte(clef1, null);
	}

	@Test(expected = ClefNullOuVide.class)
	public void testerEssayerDOuvrirLaPorteTest1Jeu4() throws Exception {
		serrure1.essayerDOuvrirLaPorte(clef1, new byte[0]);
	}

	@Test
	public void testerEssayerDOuvrirLaPorteTest2Jeu1() throws Exception {
		Assert.assertEquals(GestionSerrures.MESSAGE_PORTE_FERMEE, serrure1.essayerDOuvrirLaPorte(clef1, clef1));
	}

	@Test
	public void testerEssayerDOuvrirLaPorteTest2Jeu2() throws Exception {
		Assert.assertEquals(GestionSerrures.MESSAGE_PORTE_FERMEE, serrure1.essayerDOuvrirLaPorte(clef1, clef3));
	}

	@Test
	public void testerEssayerDOuvrirLaPorteTest2Jeu3() throws Exception {
		Assert.assertEquals(GestionSerrures.MESSAGE_PORTE_FERMEE, serrure1.essayerDOuvrirLaPorte(clef3, clef2));
	}

	@Test
	public void testerEssayerDOuvrirLaPorteTest2Jeu4() throws Exception {
		Assert.assertEquals(GestionSerrures.MESSAGE_PORTE_FERMEE, serrure1.essayerDOuvrirLaPorte(clef3, clef1));
	}

	@Test
	public void testerEssayerDOuvrirLaPorteTest2Jeu5() throws Exception {
		Assert.assertEquals(GestionSerrures.MESSAGE_PORTE_FERMEE, serrure1.essayerDOuvrirLaPorte(clef3, clef4));
	}

	@Test
	public void testerEssayerDOuvrirLaPorteTest3Jeu1() throws Exception {
		Assert.assertEquals(GestionSerrures.MESSAGE_PORTE_OUVERTE, serrure1.essayerDOuvrirLaPorte(clef2, clef2));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getPremiereClef()));
		// avec une grande probabilité, vérification nouvelle clef distincte
		byte[] nouvelleClef2 = serrure1.getSecondeClef();
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef1));
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef2));
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef3));
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef4));
	}

	@Test
	public void testerEssayerDOuvrirLaPorteTest3Jeu2() throws Exception {
		Assert.assertEquals(GestionSerrures.MESSAGE_PORTE_OUVERTE, serrure1.essayerDOuvrirLaPorte(clef2, clef1));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getPremiereClef()));
		// avec une grande probabilité, vérification nouvelle clef distincte
		byte[] nouvelleClef2 = serrure1.getSecondeClef();
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef1));
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef2));
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef3));
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef4));
	}

	@Test
	public void testerEssayerDOuvrirLaPorteTest3Jeu3() throws Exception {
		Assert.assertEquals(GestionSerrures.MESSAGE_PORTE_OUVERTE, serrure1.essayerDOuvrirLaPorte(clef2, clef3));
		Assert.assertTrue(Arrays.equals(clef2, serrure1.getPremiereClef()));
		// avec une grande probabilité, vérification nouvelle clef distincte
		byte[] nouvelleClef2 = serrure1.getSecondeClef();
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef1));
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef2));
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef3));
		Assert.assertFalse(Arrays.equals(nouvelleClef2, clef4));
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void reinitialiserTest1Jeu1() throws Exception {
		Serrure serrure = new Serrure(null, "graine1", 0);
		serrure.reInitialise(null, 0);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void reinitialiserTest1Jeu2() throws Exception {
		Serrure serrure = new Serrure(null, "graine1", 0);
		serrure.reInitialise("", 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void reinitialiserTest2Jeu1() throws Exception {
		serrure1.reInitialise("graine1", serrure1.getSel());
	}

	@Test
	public void reinitialiserTest5Jeu1() throws Exception {
		byte[] premiereClef = serrure1.getPremiereClef();
		byte[] secondeClef = serrure1.getSecondeClef();
		String graine = serrure1.getGraine();
		int sel = serrure1.getSel();
		serrure1.reInitialise(graine + "autre", sel);
		Assert.assertNotEquals(graine, serrure1.getGraine());
		Assert.assertNotEquals(sel, serrure1.getSel());
		Assert.assertFalse(Arrays.equals(serrure1.getPremiereClef(), premiereClef));
		Assert.assertFalse(Arrays.equals(serrure1.getSecondeClef(), premiereClef));
		Assert.assertFalse(Arrays.equals(serrure1.getPremiereClef(), secondeClef));
		Assert.assertFalse(Arrays.equals(serrure1.getSecondeClef(), secondeClef));
		Assert.assertFalse(Arrays.equals(serrure1.getPremiereClef(), serrure1.getSecondeClef()));
	}

	@Test
	public void reinitialiserTest5Jeu2() throws Exception {
		byte[] premiereClef = serrure1.getPremiereClef();
		byte[] secondeClef = serrure1.getSecondeClef();
		String graine = serrure1.getGraine();
		int sel = serrure1.getSel();
		serrure1.reInitialise(graine, sel + 10);
		Assert.assertEquals(graine, serrure1.getGraine());
		Assert.assertNotEquals(sel, serrure1.getSel());
		Assert.assertFalse(Arrays.equals(serrure1.getPremiereClef(), premiereClef));
		Assert.assertFalse(Arrays.equals(serrure1.getSecondeClef(), premiereClef));
		Assert.assertFalse(Arrays.equals(serrure1.getPremiereClef(), secondeClef));
		Assert.assertFalse(Arrays.equals(serrure1.getSecondeClef(), secondeClef));
		Assert.assertFalse(Arrays.equals(serrure1.getPremiereClef(), serrure1.getSecondeClef()));
	}

	@Test
	public void reinitialiserTest5Jeu3() throws Exception {
		byte[] premiereClef = serrure1.getPremiereClef();
		byte[] secondeClef = serrure1.getSecondeClef();
		String graine = serrure1.getGraine();
		int sel = serrure1.getSel();
		serrure1.reInitialise(graine + "autre", sel + 10);
		Assert.assertNotEquals(graine, serrure1.getGraine());
		Assert.assertNotEquals(sel, serrure1.getSel());
		Assert.assertFalse(Arrays.equals(serrure1.getPremiereClef(), premiereClef));
		Assert.assertFalse(Arrays.equals(serrure1.getSecondeClef(), premiereClef));
		Assert.assertFalse(Arrays.equals(serrure1.getPremiereClef(), secondeClef));
		Assert.assertFalse(Arrays.equals(serrure1.getSecondeClef(), secondeClef));
		Assert.assertFalse(Arrays.equals(serrure1.getPremiereClef(), serrure1.getSecondeClef()));
	}
}
