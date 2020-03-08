package eu.telcomsudparis.csc4102.gestionclefshotel.unitaires.validation;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreNonPresente;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientNonPresent;

public class TestEnregistrerOccupation {

	private GestionClefsHotel system;
	private Chambre chambre1;
	private Date dateDebut;
	private Date dateFin;
	
	@Before
	public void setUp() {
		system = new GestionClefsHotel();
	}
	
	@After
	public void tearDown() {
		system = null;
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void enregistrerOccupationTest1Jeu1() throws Exception{
		system.enregistrerOccupation(null, "idBadge1", "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void enregistrerOccupationTest1Jeu2() throws Exception{
		system.enregistrerOccupation("", "idBadge1", "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void enregistrerOccupationTest2Jeu1() throws Exception{
		system.enregistrerOccupation("id1", null, "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void enregistrerOccupationTest2Jeu2() throws Exception{
		system.enregistrerOccupation("id1", "", "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void enregistrerOccupationTest3Jeu1() throws Exception{
		system.enregistrerOccupation("id1", "idBadge1", null, "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void enregistrerOccupationTest3Jeu2() throws Exception{
		system.enregistrerOccupation("id1", "idBadge1", "", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void enregistrerOccupationTest4Jeu1() throws Exception{
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", null, new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
	@Test(expected = ChaineDeCaracteresNullOuVide.class)
	public void enregistrerOccupationTest4Jeu2() throws Exception{
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", "", new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
	@Test(expected = ChambreNonPresente.class)
	public void enregistrerOccupationTest5() throws Exception{
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
	@Test(expected = ClientNonPresent.class)
	public void enregistrerOccupationTest6() throws Exception{
		system.creerChambre("idChambre1", "graine1", 0);
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
	}
	
}
