package eu.telecomsudparis.csc4102.gestionclefshotel;

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
