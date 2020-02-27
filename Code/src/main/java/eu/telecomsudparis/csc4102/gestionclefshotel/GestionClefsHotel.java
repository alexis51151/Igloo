package eu.telecomsudparis.csc4102.gestionclefshotel;

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
