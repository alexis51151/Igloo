package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeNonPresent;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreNonPresente;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientNonPresent;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.OccupationMalParametree;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;
import eu.telecomsudparis.csc4102.gestionserrures.Serrure;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureDejaPresente;

/**
 * Cette classe définit la façade du système de gestion des clefs de l'hôtel.
 * 
 * @author Denis Conan
 */
public class GestionClefsHotel {
	
	/** Collections
	 * 
	 */
	private Map<String, Chambre> chambres;
	private Map<String, Client> clients;
	private Map<String, Badge> badges;

	public Chambre chercherChambre(String id){return null;}
	public Client chercherClient(String id){return null;}
	public Badge chercherBadge(String id){return null;}
	public Occupation chercherOccupation(String idChambre, String idBadge, String idClient){return null;}


	public Occupation creerOccupation(String idBadge, String idClient, String idChambre, LocalDate dateDebut, LocalDate dateFin)
			throws ChaineDeCaracteresNullOuVide, ChambreNonPresente, ClientNonPresent, BadgeNonPresent, OccupationMalParametree{

		Chambre s = chercherChambre(idChambre);
		Client c = chercherClient(idClient);
		Badge b = chercherBadge(idBadge);
		if (idClient == null || idClient.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant client null ou vide non autorisé");
		}
		if (idChambre == null || idChambre.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant client null ou vide non autorisé");
		}
		if (idBadge == null || idBadge.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant client null ou vide non autorisé");
		}
		if (s == null)
			throw new ChambreNonPresente("chambre introuvable");

		if (c == null) {
			throw new ClientNonPresent("client introuvable");
		}
		if (b == null) {
			throw new BadgeNonPresent("badge introuvable");
		}

		b.reinitialiserBadge();
		Occupation o = chercherOccupation(idChambre, idBadge, idClient);

		if(o != null || dateDebut == null || dateFin == null){
			throw new OccupationMalParametree("Occupation déjà existante ou dates null");
		}

		return new Occupation(dateDebut, dateFin);


	}
	/**
	 * construit la façade.
	 */
	public GestionClefsHotel() {
		assert invariant();
	}
	
	/**
	 * Cas d'utilisation : créer une chambre
	 */
	
	public void creerChambre(final String identifiant, final String graine, final int sel)
			throws ChaineDeCaracteresNullOuVide, ChambreDejaPresente, ProblemeDansGenerationClef {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		if (chambres.containsKey(identifiant)) {
			throw new ChambreDejaPresente("chambre '" + identifiant + "' déjà présente dans le système");
		}
		chambres.put(identifiant, new Chambre(identifiant, graine, sel));
		assert invariant();
	}
	
	/**
	 * obtenir la chambre avec l'identifiant donné.
	 * 
	 * @param identifiant de la chambre.
	 * @return la chambre.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 */
	private Optional<Chambre> chercherChambre(final String identifiant) throws ChaineDeCaracteresNullOuVide {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		return Optional.ofNullable(chambres.get(identifiant));
	}
	
	public void libérerChambre(final String identifiant) throws ChaineDeCaracteresNullOuVide, ChambreNonPresente {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		if (! chambres.containsKey(identifiant)) {
			throw new ChambreNonPresente("chambre '" + identifiant + "' non présente dans le système");
		}
		chambres.get(identifiant);
		

	}
	

	/**
	 * teste si l'invariant est vérifié.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return true;
	}

}
