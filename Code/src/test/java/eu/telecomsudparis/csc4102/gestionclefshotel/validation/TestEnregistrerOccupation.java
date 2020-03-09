package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.Occupation;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreNonPresente;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientNonPresent;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.OccupationMalParametree;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeNonPresent;

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
	
	@Test(expected = BadgeNonPresent.class)
	public void enregistrerOccupationTest7() throws Exception{
		system.creerChambre("idChambre1", "grain1", 0);
		system.creerClient("idClient1", null);
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));	
	}
	
	@Test(expected = OccupationMalParametree.class)
	public void enregistrerOccupationTest8Jeu1() throws Exception{
		system.creerChambre("idChambre1", "grain1", 0);
		system.creerClient("idClient1", new Occupation("idOcc1", new Date(2020, 05, 12), new Date(2020, 05, 12)));
		system.creerBadge("idBadge1");
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));	
	}
	
	@Test(expected = OccupationMalParametree.class)
	public void enregistrerOccupationTest8Jeu2() throws Exception{
		system.creerChambre("idChambre1", "grain1", 0);
		system.chercherChambre("idChambre1").get().setOccupation(new Occupation("idOcc1", new Date(2020, 05, 12), new Date(2020, 05, 12)));;
		system.creerClient("idClient1", null);
		system.creerBadge("idBadge1");
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));	
	}
	
	@Test(expected = OccupationMalParametree.class)
	public void enregistrerOccupationTest8Jeu3() throws Exception{
		system.creerChambre("idChambre1", "grain1", 0);
		system.creerClient("idClient1", null);
		system.creerBadge("idBadge1");
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", "idChambre1", null, new Date(2020, 05, 21));	
	}
	
	@Test(expected = OccupationMalParametree.class)
	public void enregistrerOccupationTest8Jeu4() throws Exception{
		system.creerChambre("idChambre1", "grain1", 0);
		system.creerClient("idClient1", null);
		system.creerBadge("idBadge1");
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", "idChambre1", new Date(2020, 05, 21), null);	
	}
	
	@Test
	public void enregistrerOccupationTest9() throws Exception{
		system.creerChambre("idChambre1", "grain1", 0);
		system.creerClient("idClient1", null);
		system.creerBadge("idBadge1");
		system.enregistrerOccupation("id1", "idBadge1", "idClient1", "idChambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
		Assert.assertTrue(system.chercherChambre("idChambre1").get().getOccupation() != null);
		Assert.assertTrue(system.chercherClient("idClient1").get().getOccupation() != null);
		Assert.assertTrue(system.chercherBadge("idBadge1").get().getOccupation() != null);
	}
	
	
}
