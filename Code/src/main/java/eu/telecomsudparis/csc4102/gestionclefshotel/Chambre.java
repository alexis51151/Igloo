package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.util.Arrays;

import eu.telecomsudparis.csc4102.gestionclefshotel.Util;
import eu.telecomsudparis.csc4102.gestionclefshotel.exception.ProblemeDansGenerationClef;

public class Chambre {

    private final String id;
    private String graine;
    private int sel;
    private byte clef1[];
    private byte clef2[];
    private Occupation occupation;

    public Chambre(String id, String graine, int sel) throws ProblemeDansGenerationClef{
        this.id = id;
        this.graine = graine;
        this.sel = sel;
        this.clef1 = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
		sel++;
        this.clef2 = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(clef1);
		result = prime * result + Arrays.hashCode(clef2);
		result = prime * result + ((graine == null) ? 0 : graine.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((occupation == null) ? 0 : occupation.hashCode());
		result = prime * result + sel;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Chambre))
			return false;
		Chambre other = (Chambre) obj;
		if (!Arrays.equals(clef1, other.clef1))
			return false;
		if (!Arrays.equals(clef2, other.clef2))
			return false;
		if (graine == null) {
			if (other.graine != null)
				return false;
		} else if (!graine.equals(other.graine))
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
		if (sel != other.sel)
			return false;
		return true;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public boolean invariant() {
		return ( (occupation == null) || (occupation != null));
	}
	
    
}
