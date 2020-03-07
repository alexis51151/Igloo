// CHECKSTYLE:OFF
package eu.telcomsudparis.csc4102.gestionclefshotel.unitaires;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionserrures.Serrure;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class TestBadge {
	private Badge badge;

	
	@Before
	public void SetUp() throws ChaineDeCaracteresNullOuVide, OperationImpossible { // Non nécessaire
		badge = new Badge("test"); // Pas très utile, juste parce qu'il était dit dans l'énoncé qu'il fallait mettre un @Before et @After
	}
	
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

	
	
	@After
	public void TearDown() {
		badge = null;
	}
	
	
}
