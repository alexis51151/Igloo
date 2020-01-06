package eu.telecomsudparis.csc4102.gestionserrures.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour une serrure non trouvée.
 * 
 * @author Denis Conan
 */
public class SerrureInexistante extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public SerrureInexistante(final String message) {
		super(message);
	}
}
