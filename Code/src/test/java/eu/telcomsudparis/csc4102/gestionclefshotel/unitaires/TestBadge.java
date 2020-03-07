// CHECKSTYLE:OFF
package eu.telcomsudparis.csc4102.gestionclefshotel.unitaires;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionserrures.Serrure;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class TestBadge {
	private Badge badge1;
	private Chambre chambre1;
	private Chambre chambre2;
	private Chambre chambre3;
	private byte[] clef1;
	private byte[] clef2;

	
	@Before
	public void SetUp() throws ChaineDeCaracteresNullOuVide, OperationImpossible { 
		badge1 = new Badge("badge1");
		chambre1 = null;
		chambre2 = new Chambre("chambre2", "graine2", 0);
		chambre3 = new Chambre("chambre3", "graine3", 0);
		// On suppose qu'on a des anciennes clés sur le badge pour une chambre random, ici chambre2
		clef1 = chambre2.getClef1();
		clef2 = chambre2.getClef2();
		badge1.setClef1(clef1);
		badge1.setClef2(clef2);

	}
	/*
	 * Série de tests pour le Constructeur de Badge d'accès
	 */
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurBadgeTest1Jeu1() throws Exception {
		new Badge(null);
	}

	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void constructeurBadgeTest1Jeu2() throws Exception {
		new Badge("");
	}
		
	@Test(expected = OperationImpossible.class)
	public void constructeurBadgeTest2Jeu1() throws Exception {
		new Badge(null);
	}
	
	@Test
	public void constructeurBadgeTest3Jeu1() throws Exception {
		new Badge("idDuBadge");
	}

	/*
	 * Série de tests pour estDonnéAUnClient (Modifie les clés)
	 */
	@Test(expected = OperationImpossible.class)
	public void estDonneAUnClientTest1() throws OperationImpossible{
		badge1.estDonnéAUnClient(chambre1);
	}
	
	@Test
	public void estDonneAUnClientTest2() throws OperationImpossible{
		badge1.estDonnéAUnClient(chambre2);
	}

	
	@Test
	public void estDonneAUnClientTest3() throws OperationImpossible{
		// Clés avant modification
		byte[] c1 = badge1.getClef1();
		byte[] c2 = badge1.getClef2();
		badge1.estDonnéAUnClient(chambre3);
		// Clés après modification
		Assert.assertTrue(Arrays.equals(badge1.getClef1(), c2 ));
	}
	
	@Test
	public void estDonneAUnClientTest4() throws OperationImpossible{
		// Clés avant modification
		byte[] c1 = badge1.getClef1();
		byte[] c2 = badge1.getClef2();
		badge1.estDonnéAUnClient(chambre3);
		// Clés après modification
		Assert.assertFalse(Arrays.equals(badge1.getClef2(), c2));
	}
	
	@Test
	public void estDonneAUnClientTest5() throws OperationImpossible{
		// Sel avant modification
		int sel = chambre3.getSel();
		badge1.estDonnéAUnClient(chambre3);
		// Sel après modification
		Assert.assertTrue((sel + 1) == chambre3.getSel());
	}


	
	@After
	public void TearDown() {
		badge1 = null;
		chambre1 = null;
		chambre2 = null;
		clef1 = null;
		clef2 = null;
	}
	
	
}
