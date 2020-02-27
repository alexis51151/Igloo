package eu.telecomsudparis.csc4102.gestionclefshotel;

public class Badge {
	private final String id;
	private byte[] clef1;
	private byte[] clef2;
	private Occupation occupation;
	
	Badge(String id, byte[] clef1, byte[] clef2, Occupation occupation){
		this.id = id;
		this.clef1 = clef1;
		this.clef2 = clef2;
		this.occupation = occupation;
	}

	public byte[] getClef1() {
		return clef1;
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

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	

	
	
	
	
}
