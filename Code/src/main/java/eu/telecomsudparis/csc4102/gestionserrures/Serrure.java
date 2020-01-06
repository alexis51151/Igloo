package eu.telecomsudparis.csc4102.gestionserrures;

import java.util.Arrays;
import java.util.Objects;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.Util;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ClefNullOuVide;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;

/**
 * Cette classe définit le concept de serrures.
 * 
 * @author Denis Conan
 */
public class Serrure {
	/**
	 * l'identifiant de la serrure, c'est-à-dire son identifiant unique.
	 */
	private final String identifiant;
	/**
	 * la graine pour la génération des clefs.
	 */
	private String graine;
	/**
	 * le compteur pour le sel pour la génération des clefs avec la même graine. Le
	 * compteur est incrémenté à chaque génération de clef.
	 */
	private int sel;
	/**
	 * la premiere clef.
	 */
	private byte[] premiereClef;
	/**
	 * la seconde clef.
	 */
	private byte[] secondeClef;

	/**
	 * construit une serrure.
	 * <p>
	 * NB: le sel est un entier, donc sur 10 chiffres. L'utilisation de
	 * {@code String.format("%010d%n", this.sel)} permet de mettre l'entier sur 10
	 * chiffres en mettant des 0 au début au besoin.
	 * 
	 * @param nom    la serrure.
	 * @param graine la graine pour la génération de la séquence des clefs.
	 * @param sel    la valeur initiale du compteur de génération de clefs pour la
	 *               génération de la séquence de clefs.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws ProblemeDansGenerationClef   problème dans la génération des clefs.
	 */
	public Serrure(final String nom, final String graine, final int sel)
			throws ChaineDeCaracteresNullOuVide, ProblemeDansGenerationClef {
		if (nom == null || nom.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant null ou vide non autorisé");
		}
		if (graine == null || graine.equals("")) {
			throw new IllegalArgumentException("identifiant null ou vide non autorisé");
		}
		this.identifiant = nom;
		this.graine = graine;
		this.sel = sel;
		premiereClef = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
		this.sel++;
		secondeClef = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
		assert invariant();
	}

	/**
	 * teste la serrure avec deux clefs.
	 * 
	 * @param premiereClef la première clef pour le test (K1).
	 * @param secondeClef  la seconde clef pour le test (K2).
	 * @return K1 et K2 fournies identiques respectivement aux première et seconde
	 *         clefs de la serrure ∨ K1 fournie est identique à la seconde clef de
	 *         la serrure.
	 * @throws ClefNullOuVide clef null ou vide.
	 */
	public boolean testerSerrure(final byte[] premiereClef, final byte[] secondeClef) throws ClefNullOuVide {
		if (premiereClef == null || premiereClef.length == 0) {
			throw new ClefNullOuVide("première clef null ou vide non autorisé");
		}
		if (secondeClef == null || secondeClef.length == 0) {
			throw new ClefNullOuVide("seconde clef null ou vide non autorisé");
		}
		assert invariant();
		return (Arrays.equals(premiereClef, this.premiereClef) && Arrays.equals(secondeClef, this.secondeClef))
				|| Arrays.equals(premiereClef, this.secondeClef);
	}

	/**
	 * essayer d'ouvrir la serrure avec avec les deux clefs d'un badge.
	 * <p>
	 * NB: le sel est un entier, donc sur 10 chiffres. L'utilisation de
	 * {@code String.format("%010d%n", this.sel)} permet de mettre l'entier sur 10
	 * chiffres en mettant des 0 au début au besoin.
	 * 
	 * @param premiereClef la première clef pour le test (K1).
	 * @param secondeClef  la seconde clef pour le test (K2).
	 * @return "la porte est ouverte" ∨ "la porte reste fermée".
	 * @throws ClefNullOuVide               clef null ou vide.
	 * @throws ProblemeDansGenerationClef   problème dans la génération des clefs.
	 * @throws ChaineDeCaracteresNullOuVide
	 */
	public String essayerDOuvrirLaPorte(final byte[] premiereClef, final byte[] secondeClef)
			throws ClefNullOuVide, ProblemeDansGenerationClef {
		if (premiereClef == null || premiereClef.length == 0) {
			throw new ClefNullOuVide("première clef null ou vide non autorisé");
		}
		if (secondeClef == null || secondeClef.length == 0) {
			throw new ClefNullOuVide("seconde clef null ou vide non autorisé");
		}
		boolean test = testerSerrure(premiereClef, secondeClef);
		if (test) {
			this.premiereClef = this.secondeClef.clone();
			sel++;
			this.secondeClef = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
		}
		assert invariant();
		return test ? GestionSerrures.MESSAGE_PORTE_OUVERTE : GestionSerrures.MESSAGE_PORTE_FERMEE;
	}

	/**
	 * ré-initialise la serrure avec la nouvelle graine.
	 * <p>
	 * NB: le sel est un entier, donc sur 10 chiffres. L'utilisation de
	 * {@code Stringt.format("%010d%n", this.sel)} permet de mettre l'entier sur 10
	 * chiffres en mettant des 0 au début au besoin.
	 * 
	 * @param graine la nouvelle graine pour la génération de la séquence des clefs
	 *               de la serrure.
	 * @param sel    le nouveau sel pour la génération de la séquence des clefs de
	 *               la serrure.
	 * @throws ChaineDeCaracteresNullOuVide identifiant null ou vide.
	 * @throws ProblemeDansGenerationClef   problème dans la génération des clefs.
	 */
	public void reInitialise(final String graine, final int sel)
			throws ChaineDeCaracteresNullOuVide, ProblemeDansGenerationClef {
		if (graine == null || graine.equals("")) {
			throw new IllegalArgumentException("graine null ou vide non autorisé");
		}
		if (this.graine.equals(graine) && this.sel == sel) {
			throw new IllegalArgumentException("pas possible de ré-initialiser avec la même graine et le même sel");
		}
		this.graine = graine;
		this.sel = sel;
		premiereClef = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
		this.sel++;
		secondeClef = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
		assert invariant();
	}

	/**
	 * obtient une copie de la première clef de la serrure.
	 * 
	 * @return la clef.
	 */
	public byte[] getPremiereClef() {
		return premiereClef.clone();
	}

	/**
	 * obtient une copie de la seconde clef de la serrure.
	 * 
	 * @return la seconde clef.
	 */
	public byte[] getSecondeClef() {
		return secondeClef.clone();
	}

	/**
	 * obtient la graine de la serrure.
	 * 
	 * @return la graine.
	 */
	public String getGraine() {
		return graine;
	}

	/**
	 * obtient le sel de la serrure.
	 * 
	 * @return la sel.
	 */
	public int getSel() {
		return sel;
	}

	/**
	 * vérifie l'invariant.
	 * 
	 * @return true si l'invariant est vérifié.
	 */
	public boolean invariant() {
		return identifiant != null && !identifiant.equals("") && premiereClef != null
				&& premiereClef.length == Util.TAILLE_CLEF && secondeClef != null
				&& secondeClef.length == Util.TAILLE_CLEF;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifiant);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Serrure)) {
			return false;
		}
		Serrure other = (Serrure) obj;
		return Objects.equals(identifiant, other.identifiant);
	}

	@Override
	public String toString() {
		return "Serrure [graine=" + graine + ", sel=" + sel + "]";
	}
}
