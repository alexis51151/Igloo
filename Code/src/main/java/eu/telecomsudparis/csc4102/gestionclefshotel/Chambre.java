package eu.telecomsudparis.csc4102.gestionclefshotel;

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
        this.clef2 = Util.genererUneNouvelleClef(graine, String.format("%010d%n", this.sel));
    }
}
