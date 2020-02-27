package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.time.LocalDate;

public class Occupation {

    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Occupation(LocalDate dateDebut, LocalDate dateFin) {
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
