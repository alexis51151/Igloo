package eu.telecomsudparis.csc4102.gestionclefshotel.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour indiquer qu'une chaîne de
 * caractères est par erreur soit null soit vide.
 * 
 * @author Denis Conan
 */
public class ClefNullOuVide extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public ClefNullOuVide(final String message) {
		super(message);
	}
	
}
