package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreNonPresente;

import java.time.LocalDate;
import java.util.Map;

/**
 * Cette classe définit la façade du système de gestion des clefs de l'hôtel.
 * 
 * @author Denis Conan
 */
public class GestionClefsHotel {
	
	/** Les collections
	 * 
	 */
	private Map<String, Chambre> chambres;
	private Map<String, Client> clients;
	private Map<String, Badge> badges;

	public void creerOccupation(String idBadge, String idClient, String idChambre, LocalDate dateDebut, LocalDate dateFin)
			throws ChaineDeCaracteresNullOuVide, ChambreNonPresente{

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
		if (s == null) {
			throw new ChambreNonPresente("");
		}

	}
	/**
	 * construit la façade.
	 */
	public GestionClefsHotel() {
		assert invariant();
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
