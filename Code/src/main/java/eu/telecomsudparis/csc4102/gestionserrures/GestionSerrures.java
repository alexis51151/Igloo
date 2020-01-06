package eu.telecomsudparis.csc4102.gestionserrures;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClefNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureDejaPresente;
import eu.telecomsudparis.csc4102.gestionserrures.exception.SerrureInexistante;

/**
 * Cette classe définit la façade du système GestionSerrures.
 * 
 * @author Denis Conan
 */
public class GestionSerrures {
	/**
	 * message indiquant que la porte s'ouvre (simulation de la mécanique de la
	 * serrure).
	 */
	public static final String MESSAGE_PORTE_OUVERTE = "la porte est ouverte";
	/**
	 * message indiquant que la porte reste fermée (simulation de la mécanique de la
	 * serrure).
	 */
	public static final String MESSAGE_PORTE_FERMEE = "la porte reste fermée";
	/**
	 * la collection des serrures.
	 */
	private Map<String, Serrure> serrures;

	/**
	 * construit la façade.
	 */
	public GestionSerrures() {
		serrures = new HashMap<>();
	}

	/**
	 * teste si l'invariant est vérifié.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return serrures != null;
	}

	/**
	 * crée une serrure. La graine sert à démarrer la séquence de clefs de la
	 * nouvelle serrure : les deux premières clefs sont générées. Le sel pour la
	 * génération est un compteur du nombre de clefs générée depuis la création de
	 * l'objet. La valeur du sel est donc trivialement égale à 0.
	 * 
	 * @param identifiant l'identifiant de la serrure.
	 * @param graine      la graine pour la génération des clefs.
	 * @param sel         la valeur initiale du compteur de génération de clefs pour
	 *                    la génération de la séquence de clefs.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws SerrureDejaPresente          serrure avec cet identifiant déjà
	 *                                      présente.
	 * @throws ProblemeDansGenerationClef   problème dans la génération des deux
	 *                                      premières clefs de la serrure.
	 */
	public void creerSerrure(final String identifiant, final String graine, final int sel)
			throws ChaineDeCaracteresNullOuVide, SerrureDejaPresente, ProblemeDansGenerationClef {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		if (serrures.containsKey(identifiant)) {
			throw new SerrureDejaPresente("serrure '" + identifiant + "' déjà présente dans le système");
		}
		serrures.put(identifiant, new Serrure(identifiant, graine, sel));
		assert invariant();
	}

	/**
	 * obtenir la serrure avec l'identifiant donné.
	 * 
	 * @param identifiant de la serrure.
	 * @return la serrure.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 */
	private Optional<Serrure> chercherSerrure(final String identifiant) throws ChaineDeCaracteresNullOuVide {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		return Optional.ofNullable(serrures.get(identifiant));
	}

	/**
	 * essayer d'ouvrir la serrure avec avec les deux clefs d'un badge.
	 * 
	 * @param identifiant  l'identifiant de la serrure
	 * @param premiereClef la première clef pour le test (K1).
	 * @param secondeClef  la seconde clef pour le test (K2).
	 * @return "la porte est ouverte" ∨ "la porte reste fermée".
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws SerrureInexistante           serrure avec cet identifiant
	 *                                      inexistante.
	 * @throws ClefNullOuVide               clef null ou vide.
	 * @throws ProblemeDansGenerationClef   problème dans la génération de la
	 *                                      nouvelle clef (dans le cas du client
	 *                                      «&nbsp;suivant&nbsp;»).
	 */
	public String essayerDOuvrirUnePorte(final String identifiant, final byte[] premiereClef, final byte[] secondeClef)
			throws ChaineDeCaracteresNullOuVide, ClefNullOuVide, SerrureInexistante, ProblemeDansGenerationClef {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		if (premiereClef == null || premiereClef.length == 0) {
			throw new ClefNullOuVide("première clef null ou vide non autorisé");
		}
		if (secondeClef == null || secondeClef.length == 0) {
			throw new ClefNullOuVide("seconde clef null ou vide non autorisé");
		}
		Optional<Serrure> serrure = chercherSerrure(identifiant);
		if (!serrure.isPresent()) {
			throw new SerrureInexistante("serrure '" + identifiant + "' inexistante");
		}
		return serrure.get().essayerDOuvrirLaPorte(premiereClef, secondeClef);
	}

	/**
	 * teste une serrure avec deux clefs.
	 * 
	 * @param identifiant  l'identifiant de la serrure
	 * @param premiereClef la première clef pour le test (K1).
	 * @param secondeClef  la seconde clef pour le test (K2).
	 * @return K1 et K2 fournies identiques respectivement aux première et seconde
	 *         clefs de la serrure ∨ K1 fournie est identique à la seconde clef de
	 *         la serrure.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws SerrureInexistante           serrure avec cet identifiant
	 *                                      inexistante.
	 * @throws ClefNullOuVide               clef null ou vide.
	 */
	public boolean testerSerrure(final String identifiant, final byte[] premiereClef, final byte[] secondeClef)
			throws ChaineDeCaracteresNullOuVide, ClefNullOuVide, SerrureInexistante {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		if (premiereClef == null || premiereClef.length == 0) {
			throw new ClefNullOuVide("première clef null ou vide non autorisé");
		}
		if (secondeClef == null || secondeClef.length == 0) {
			throw new ClefNullOuVide("seconde clef null ou vide non autorisé");
		}
		Optional<Serrure> serrure = chercherSerrure(identifiant);
		if (!serrure.isPresent()) {
			throw new SerrureInexistante("serrure '" + identifiant + "' inexistante");
		}
		return serrure.get().testerSerrure(premiereClef, secondeClef);
	}

	/**
	 * ré-initialise une serrure. La graine donnée sert à construire les deux
	 * premières clefs de la nouvelle séquence de clefs. Lorsque l'on ré-initialise
	 * une serrure, on donne la nouvelle graine et le nouveau sel.
	 * 
	 * @param identifiant              l'identifiant de la serrure.
	 * @param nouvelleGraine           la nouvelle graine du générateur de clefs de
	 *                                 la serrure.
	 * @param nouveauCompteurPourLeSel le nouveau compteur pour le sel du générateur
	 *                                 des clefs de la serrures.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws SerrureInexistante           serrure avec ce identifiant inexistante.
	 * @throws ProblemeDansGenerationClef   problème dans la génération des deux
	 *                                      premières clefs de la nouvelle séquence
	 *                                      de clefs.
	 */
	public void reInitialiserLesClefsDUneSerrure(final String identifiant, final String nouvelleGraine,
			final int nouveauCompteurPourLeSel)
			throws ChaineDeCaracteresNullOuVide, SerrureInexistante, ProblemeDansGenerationClef {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		Optional<Serrure> serrure = chercherSerrure(identifiant);
		if (!serrure.isPresent()) {
			throw new SerrureInexistante("serrure '" + identifiant + "' inexistante");
		}
		serrure.get().reInitialise(nouvelleGraine, nouveauCompteurPourLeSel);
	}

	/**
	 * obtiens la première clef d'une serrure.
	 * 
	 * @param identifiant l'identifiant de la serrure.
	 * @return la premiere clef.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws SerrureInexistante           serrure avec cet identifiant inexistante.
	 */
	public byte[] obtenirPremiereClefSerrure(final String identifiant) throws ChaineDeCaracteresNullOuVide, SerrureInexistante {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		Optional<Serrure> serrure = chercherSerrure(identifiant);
		if (!serrure.isPresent()) {
			throw new SerrureInexistante("serrure '" + identifiant + "' inexistante");
		}
		return serrure.get().getPremiereClef();
	}

	/**
	 * obtiens la graine d'une serrure.
	 * 
	 * @param identifiant l'identifiant de la serrure.
	 * @return la graine.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws SerrureInexistante           serrure avec cet identifiant inexistante.
	 */
	public String obtenirGraineSerrure(final String identifiant) throws ChaineDeCaracteresNullOuVide, SerrureInexistante {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		Optional<Serrure> serrure = chercherSerrure(identifiant);
		if (!serrure.isPresent()) {
			throw new SerrureInexistante("serrure '" + identifiant + "' inexistante");
		}
		return serrure.get().getGraine();
	}

	/**
	 * obtiens le sel d'une serrure.
	 * 
	 * @param identifiant l'identifiant de la serrure.
	 * @return la sel.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws SerrureInexistante           serrure avec cet identifiant inexistante.
	 */
	public int obtenirSelSerrure(final String identifiant) throws ChaineDeCaracteresNullOuVide, SerrureInexistante {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		Optional<Serrure> serrure = chercherSerrure(identifiant);
		if (!serrure.isPresent()) {
			throw new SerrureInexistante("serrure '" + identifiant + "' inexistante");
		}
		return serrure.get().getSel();
	}

	/**
	 * obtiens la seconde clef d'une serrure.
	 * 
	 * @param identifiant l'identifiant de la serrure.
	 * @return la seconde clef.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws SerrureInexistante           serrure avec cet identifiant inexistante.
	 */
	public byte[] obtenirSecondeClefSerrure(final String identifiant) throws ChaineDeCaracteresNullOuVide, SerrureInexistante {
		if (identifiant == null || identifiant.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		Optional<Serrure> serrure = chercherSerrure(identifiant);
		if (!serrure.isPresent()) {
			throw new SerrureInexistante("serrure '" + identifiant + "' inexistante");
		}
		return serrure.get().getSecondeClef();
	}

	/**
	 * liste les serrures du système.
	 * 
	 * @return la liste des serrures sous forme de tableau de chaînes de caractères.
	 */
	public String[] listerSerrures() {
		return serrures.values().stream().map(Serrure::toString).toArray(String[]::new);
	}
}
