package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
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


	public Occupation chercherOccupation(String idChambre, String idBadge, String idClient){return null;}


	public Occupation creerOccupation(String id, String idBadge, String idClient, String idChambre, Date dateDebut, Date dateFin)
			throws ChaineDeCaracteresNullOuVide, ChambreNonPresente, ClientNonPresent, BadgeNonPresent, OccupationMalParametree{

		Optional<Chambre> s = chercherChambre(idChambre);
		Optional<Client> c = chercherClient(idClient);
		Optional<Badge> b = chercherBadge(idBadge);
		if (idClient == null || idClient.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant client null ou vide non autorisé");
		}
		if (idChambre == null || idChambre.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant client null ou vide non autorisé");
		}
		if (idBadge == null || idBadge.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant client null ou vide non autorisé");
		}
		if (! s.isPresent())
			throw new ChambreNonPresente("chambre introuvable");

		if (! c.isPresent()) {
			throw new ClientNonPresent("client introuvable");
		}
		if (! b.isPresent()) {
			throw new BadgeNonPresent("badge introuvable");
		}

		b.get().reinitialiserBadge();
		Occupation o = chercherOccupation(idChambre, idBadge, idClient);

		if(o != null || dateDebut == null || dateFin == null){
			throw new OccupationMalParametree("Occupation déjà existante ou dates null");
		}

		return new Occupation(id, dateDebut, dateFin);


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

	public Optional<Client> chercherClient(final String id) throws ChaineDeCaracteresNullOuVide{
		if(id == null || id.equals("")){
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		return Optional.ofNullable(clients.get(id));
	}

	public Optional<Badge> chercherBadge(final String id) throws ChaineDeCaracteresNullOuVide{
		if(id == null || id.equals("")){
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		return Optional.ofNullable(badges.get(id));
	}

	public void libérerChambre(final String idChambre)
			throws ChaineDeCaracteresNullOuVide, ChambreNonPresente, OccupationNonPresente {
		if (idChambre == null || idChambre.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		if (! chambres.containsKey(idChambre)) {
			throw new ChambreNonPresente("chambre '" + idChambre + "' non présente dans le système");
		}
		Chambre currentChambre = chambres.get(idChambre);
		Occupation occupation = currentChambre.getOccupation();
		if(occupation == null){
			throw new OccupationNonPresente("Cette occupation n'existe pas");
		}

		for(Map.Entry<String, Client> entry : clients.entrySet()) {
			String key = entry.getKey();
			Client client = entry.getValue();

			if(client.getOccupation().equals(occupation)){
				client.setOccupation(null);
			}
		}
		currentChambre.setOccupation(null);
		chambres.replace(idChambre, currentChambre);
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
