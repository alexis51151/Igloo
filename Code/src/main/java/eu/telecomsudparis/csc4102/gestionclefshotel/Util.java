package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;

/**
 * Cette classe définit quelques constantes et quelques méthodes de classes
 * utilitaires.
 * 
 * @author Denis Conan
 */
public final class Util {
	/**
	 * la taille des clefs en octets, soit 128 bits.
	 */
	public static final int TAILLE_CLEF = 16;

	/**
	 * constructeur privé pour cette classe utilitaire.
	 */
	private Util() {
		// nop
	}

	/**
	 * génère la clef suivante d'un générateur de clef selon la graine et le sel
	 * donné. La génération est pseudo-déterministe : avec la même graine et le même
	 * sel, c'est la même clef qui est générée. La graine est supposée être créée de
	 * manière aléatoire, par exemple via {@code SecureRandom}. Pour une séquence de
	 * clefs, la graine reste la même et c'est le sel qui
	 * «&nbsp;évolue&nbsp;»&nbsp;: ici, le sel est construit comme la concaténation
	 * de "génération numéro " avec un compteur, qui représente le nombre de clefs
	 * générés.
	 * 
	 * @param graine la graine pour la génération.
	 * @param sel    le sel pour la génération des clefs générées avec la même
	 *               graine.
	 * @return la nouvelle clef générée
	 * @throws ProblemeDansGenerationClef problème dans la génération de la clef.
	 */
	public static byte[] genererUneNouvelleClef(final String graine, final String sel)
			throws ProblemeDansGenerationClef {
		if (graine == null || graine.equals("")) {
			throw new ProblemeDansGenerationClef("la graine ne peut pas être null ou vide");
		}
		byte[] nouvelleClef = null;
		try {
			final int nbIterations = 65536 / 16; // les tests sont longs ou moins longs...
			final int tailleClefEnBit = TAILLE_CLEF * 8;
			nouvelleClef = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
					.generateSecret(new PBEKeySpec(graine.toCharArray(), sel.getBytes(StandardCharsets.UTF_8),
							nbIterations, tailleClefEnBit))
					.getEncoded();
		} catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
			throw new ProblemeDansGenerationClef(ex.getLocalizedMessage());
		}
		return nouvelleClef;
	}

	/**
	 * retourne une chaîne de caractères représentant le contenu d'une clef.
	 *
	 * @param clef la clef.
	 * @return une chaîne de caractères.
	 */
	public static String clefToString(final byte[] clef) {
		if (clef == null) {
			return "null";
		}
		int indiceDernierElement = clef.length - 1;
		if (indiceDernierElement == -1) {
			return "[]";
		}
		StringBuilder b = new StringBuilder();
		for (int i = 0; i <= indiceDernierElement; i++) {
			b.append(String.format("%02x", clef[i]));
		}
		return b.toString();
	}
}
