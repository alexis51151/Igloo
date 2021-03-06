package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Arrays;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class Badge {
	private final String id;
	private byte[] clef1 = new byte[Util.TAILLE_CLEF];
	private byte[] clef2 = new byte[Util.TAILLE_CLEF];
	private Occupation occupation = null;
	private boolean perdu = false;
	
	public Badge(String id) throws ChaineDeCaracteresNullOuVide, OperationImpossible{
		if (id == null || id.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant badge null ou vide non autorisé");
		}		
		if (occupation != null) {
			throw new OperationImpossible("L'occupation devrait être nulle" + this);
		}
		this.id = id;
		assert invariant();
	}

	public void setClef1(byte[] clef1) {
		this.clef1 = clef1;
	}

	public byte[] getClef2() {
		return clef2;
	}

	public void setClef2(byte[] clef2) {
		this.clef2 = clef2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(clef1);
		result = prime * result + Arrays.hashCode(clef2);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Badge))
			return false;
		Badge other = (Badge) obj;
		if (!Arrays.equals(clef1, other.clef1))
			return false;
		if (!Arrays.equals(clef2, other.clef2))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (occupation == null) {
			if (other.occupation != null)
				return false;
		} else if (!occupation.equals(other.occupation))
			return false;
		return true;
	}
	
	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
	public void estDonnéAUnClient(Chambre ch) throws OperationImpossible {
		if (ch == null) {
			throw new OperationImpossible("On ne peut associer une chambre nulle à un badge" + this);
		}
		this.clef1 = this.clef2;
		ch.setSel(ch.getSel() + 1);
		this.clef2 = Util.genererUneNouvelleClef(ch.getGraine(), String.format("%010d%n",ch.getSel()));
		assert invariant();
	}

	public byte[] getClef1() {
		return clef1;
	}

	public void reinitialiserBadge(){}
	
	public boolean invariant() {
		boolean prete = (occupation==null);
		return ( ((!prete && !perdu) || (prete && !perdu) || (prete && perdu)) && (clef1 != null) && (clef1.length == Util.TAILLE_CLEF) && (clef2 != null) && (clef2.length == Util.TAILLE_CLEF) );
		
	}
	
	
}
