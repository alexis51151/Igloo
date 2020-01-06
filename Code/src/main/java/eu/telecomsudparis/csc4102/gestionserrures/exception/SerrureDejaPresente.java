package eu.telecomsudparis.csc4102.gestionserrures.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour une serrure déjà présente dans
 * le système.
 * 
 * @author Denis Conan
 */
public class SerrureDejaPresente extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public SerrureDejaPresente(final String message) {
		super(message);
	}
}
