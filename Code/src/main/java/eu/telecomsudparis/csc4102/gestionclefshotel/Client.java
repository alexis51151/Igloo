package eu.telecomsudparis.csc4102.gestionclefshotel;

public class Client {
	private String id;
	private String nom;
	private String prénom;
	private Occupation occupation;
	
	public Client(String id, String nom, String prénom, Occupation occupation) {
		super();
		this.id = id;
		this.nom = nom;
		this.prénom = prénom;
		this.occupation = occupation;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	
	public boolean invariant() { // Invariant pas très utile mais bon...
		return ((this.occupation == null) || (this.occupation != null));
	}
	
	
}
