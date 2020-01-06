package eu.telecomsudparis.csc4102.gestionclefshotel.exception;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe définit le type d'exception pour indiquer qu'un problème est
 * survenu lors de la génération d'une clef.
 * 
 * @author Denis Conan
 */
public class ProblemeDansGenerationClef extends OperationImpossible {
	/**
	 * numéro de version pour la sérialisation.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * construit une instance.
	 * 
	 * @param message le message de l'exception.
	 */
	public ProblemeDansGenerationClef(final String message) {
		super(message);
	}

}
