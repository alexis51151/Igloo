package eu.telecomsudparis.csc4102.gestionclefshotel.unitaires.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreNonPresente;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.OccupationNonPresente;

public class TestLibererChambre {

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
	public void libererChambreTest1Jeu1() throws Exception{
		system.libérerChambre(null);
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void libererChambreTest1Jeu2() throws Exception{
		system.libérerChambre("");
	}
	
	@Test(expected = ChambreNonPresente.class)
	public void libererChambreTest2() throws Exception{
		system.libérerChambre("id1");
	}
	
	@Test(expected = OccupationNonPresente.class)
	public void libererChambreTest3() throws Exception{
		system.creerChambre("id1", "graine1", 0);
		system.libérerChambre("id1");
	}
	
}
