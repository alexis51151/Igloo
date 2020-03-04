package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.time.LocalDate;

public class Occupation {

    private String id;
    private Date dateDebut;
    private Date dateFin;

    public Occupation(String id, Date dateDebut, Date dateFin) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }
}
