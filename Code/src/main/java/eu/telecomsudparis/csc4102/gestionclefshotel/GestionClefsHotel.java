package eu.telecomsudparis.csc4102.gestionclefshotel;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.BadgeNonPresent;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ChambreNonPresente;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClientNonPresent;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.OccupationMalParametree;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.OccupationNonPresente;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;
import eu.telecomsudparis.csc4102.gestionserrures.Serrure;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureDejaPresente;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

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

	/**
	 * Cas d'utilisation : enregistrer l'occupation d'une chambre par un client
	 */


	public Occupation enregistrerOccupation(String id, String idBadge, String idClient, String idChambre, Date dateDebut, Date dateFin)
			throws ChaineDeCaracteresNullOuVide, ChambreNonPresente, ClientNonPresent, BadgeNonPresent, OccupationMalParametree{

		Optional<Chambre> s = chercherChambre(idChambre);
		Optional<Client> c = chercherClient(idClient);
		Optional<Badge> b = chercherBadge(idBadge);
		if (id == null || id.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant occupation null ou vide non autorisé");
		}
		if (idClient == null || idClient.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant client null ou vide non autorisé");
		}
		if (idChambre == null || idChambre.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant chambre null ou vide non autorisé");
		}
		if (idBadge == null || idBadge.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant badge null ou vide non autorisé");
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
		Occupation o1 = c.get().getOccupation();
		Occupation o2 = s.get().getOccupation();


		if(o1 != null || o2 != null || dateDebut == null || dateFin == null){
			throw new OccupationMalParametree("Occupation déjà existante ou dates null");
		}

		Occupation o = new Occupation(id, dateDebut, dateFin);
		b.get().setOccupation(o);
		c.get().setOccupation(o);
		s.get().setOccupation(o);
		assert invariant();
		return(o);
	}



	/**
	 * construit la façade.
	 */
	public GestionClefsHotel() {
		chambres = new HashMap<String, Chambre>();
		clients = new HashMap<String, Client>();
		badges= new HashMap<String, Badge>();
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
		if (graine == null || graine.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("Graine null ou vide non autorisé");
		}
		if (chambres.containsKey(identifiant)) {
			throw new ChambreDejaPresente("chambre '" + identifiant + "' déjà présente dans le système");
		}
		chambres.put(identifiant, new Chambre(identifiant, graine, sel));
		assert invariant();
	}
	
	public void creerClient(final String identifiant, final Occupation occupation) {
		clients.put(identifiant, new Client(identifiant, "nom", "prenom", occupation));
	}
	
	public void creerBadge(final String identifiant) throws ChaineDeCaracteresNullOuVide, OperationImpossible{
		badges.put(identifiant, new Badge(identifiant));
	}

	
	/**
	 * obtenir la chambre avec l'identifiant donné.
	 * 
	 * @param identifiant de la chambre.
	 * @return la chambre.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 */
	public Optional<Chambre> chercherChambre(final String identifiant) throws ChaineDeCaracteresNullOuVide {
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
		// Effaçage des paires de clefs 
		for(Map.Entry<String, Badge> entry : badges.entrySet()) {
			String key = entry.getKey();
			Badge badge = entry.getValue();

			if(badge.getOccupation().equals(occupation)){
				// Créer des tableaux de bytes avec que des 0
				badge.setClef1(new byte[Util.TAILLE_CLEF]);
				badge.setClef2(new byte[Util.TAILLE_CLEF]);
				badge.setOccupation(null);
			}
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
