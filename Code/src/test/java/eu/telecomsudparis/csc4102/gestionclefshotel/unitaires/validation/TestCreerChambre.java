package eu.telecomsudparis.csc4102.gestionclefshotel.unitaires.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.Occupation;
import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.ChambreDejaPresente;

public class TestCreerChambre {

	private GestionClefsHotel system;
	
	@Before
	public void setUp() {
		system = new GestionClefsHotel();
	}
	
	@After
	public void tearDown() {
		system = null;
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerChambreTest1Jeu1() throws Exception {
		system.creerChambre(null, "graine1", 0);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerChambreTest1Jeu2() throws Exception {
		system.creerChambre("", "graine1", 0);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerChambreTest2Jeu1() throws Exception {
		system.creerChambre("id1", null, 0);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void creerChambreTest2Jeu2() throws Exception {
		system.creerChambre("id1", "", 0);
	}
	
	@Test(expected = ChambreDejaPresente.class)
	public void creerChambreTest3() throws Exception {
		system.creerChambre("id1", "graine1", 0);
		system.creerChambre("id1", "graine2", 1);
	}
	
	@Test
	public void creerChambreTest4() throws Exception {
		system.creerChambre("id1", "graine1", 0);
	}

	
	
	
	
	
}
