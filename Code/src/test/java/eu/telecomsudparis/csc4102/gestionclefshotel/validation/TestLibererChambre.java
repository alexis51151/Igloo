package eu.telecomsudparis.csc4102.gestionclefshotel.validation;

import java.util.Arrays;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.Badge;
import eu.telecomsudparis.csc4102.gestionclefshotel.Chambre;
import eu.telecomsudparis.csc4102.gestionclefshotel.Client;
import eu.telecomsudparis.csc4102.gestionclefshotel.GestionClefsHotel;
import eu.telecomsudparis.csc4102.gestionclefshotel.Util;
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
	
	@Test(expected = OccupationNonPresente.class)
	public void libererChambreTest4() throws Exception{
		system.creerChambre("id1", "graine1", 0);
		
		system.libérerChambre("id1");
	}
	
	@Test
	public void libererChambreTest5Jeu1() throws Exception{
		// On enregistre une occupation et on la libère
		system.creerBadge("badge1");
		system.creerClient("client1", null);
		system.creerChambre("chambre1", "graine1", 0);
		system.enregistrerOccupation("occupation1", "badge1", "client1", "chambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
		system.libérerChambre("chambre1");
	}
	
	@Test
	public void libererChambreTest5Jeu2() throws Exception{
		system.creerBadge("badge1");
		system.creerClient("client1", null);
		system.creerChambre("chambre1", "graine1", 0);
		system.enregistrerOccupation("occupation1", "badge1", "client1", "chambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
		system.libérerChambre("chambre1");
		Client c = system.chercherClient("client1").get();
		Badge b = system.chercherBadge("badge1").get();
		Chambre ch = system.chercherChambre("chambre1").get();
		Assert.assertTrue(Arrays.equals(b.getClef1(), new byte[Util.TAILLE_CLEF]));
		Assert.assertTrue(Arrays.equals(b.getClef2(), new byte[Util.TAILLE_CLEF]));
	}

	@Test
	public void libererChambreTest5Jeu3() throws Exception{
		system.creerBadge("badge1");
		system.creerClient("client1", null);
		system.creerChambre("chambre1", "graine1", 0);
		system.enregistrerOccupation("occupation1", "badge1", "client1", "chambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
		system.libérerChambre("chambre1");
		Badge b = system.chercherBadge("badge1").get();
		Assert.assertTrue(b.getOccupation() == null);
	}
	
	@Test
	public void libererChambreTest5Jeu4() throws Exception {
		system.creerBadge("badge1");
		system.creerClient("client1", null);
		system.creerChambre("chambre1", "graine1", 0);
		system.enregistrerOccupation("occupation1", "badge1", "client1", "chambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
		system.libérerChambre("chambre1");
		Client c = system.chercherClient("client1").get();
		Assert.assertTrue(c.getOccupation() == null);
	}
	
	@Test
	public void libererChambreTest5Jeu5() throws Exception{
		system.creerBadge("badge1");
		system.creerClient("client1", null);
		system.creerChambre("chambre1", "graine1", 0);
		system.enregistrerOccupation("occupation1", "badge1", "client1", "chambre1", new Date(2020, 05, 12), new Date(2020, 05, 21));
		system.libérerChambre("chambre1");
		Chambre ch = system.chercherChambre("chambre1").get();
		Assert.assertTrue(ch.getOccupation() == null);
	}
	
}
