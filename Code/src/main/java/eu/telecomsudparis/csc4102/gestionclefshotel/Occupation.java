package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.time.LocalDate;
import java.util.Date;

public class Occupation {

    private String id;
    private Date dateDebut;
    private Date dateFin;

    public Occupation(String id, Date dateDebut, Date dateFin) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }
}
