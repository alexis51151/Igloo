package eu.telecomsudparis.csc4102.gestionclefshotel;

import java.time.LocalDate;

public class Occupation {

    private final String id;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Occupation(String id, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
}
