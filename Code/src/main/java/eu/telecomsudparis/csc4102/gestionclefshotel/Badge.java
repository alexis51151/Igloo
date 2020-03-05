package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Arrays;

import eu.telecomsudparis.csc4102.exception.ChaineDeCaracteresNullOuVide;

public class Badge {
	private final String id;
	private byte[] clef1;
	private byte[] clef2;
	private Occupation occupation;
	private boolean perdu;
	
	Badge(String id) throws ChaineDeCaracteresNullOuVide{
		if (id == null || id.equals("")) {
			throw new ChaineDeCaracteresNullOuVide("identifiant badge null ou vide non autorisé");
		}
		this.id = id;
		this.perdu = false;
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

	public void reinitialiserBadge(){}
	
	public boolean invariant() {
		boolean prete = (occupation==null);
		return ( (!prete && !perdu) || (prete && !perdu) || (prete && perdu));
		
	}
	
	
}
