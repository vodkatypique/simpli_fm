package projet_poo;

import java.util.ArrayList;
import java.util.Collections;

class Championnat extends Structure {

    private ArrayList<Club> participants;

    private Club joueur;

    private final int ptsVictoire;

    private final int ptsNul;

    private final int ptsDef;

    private ArrayList<Club> classement;

    private ArrayList<Match> programmeMatch;


    public Championnat(String date, int vic, int def, int nul, Club joueur, ArrayList<Club> participants) {
        super(date);
        this.joueur = joueur;
        this.classement = new ArrayList<Club>();
        for (Club club : participants) {
            this.classement.add(club);
        }
        this.programmeMatch = new ArrayList<Match>();
        this.participants = participants;
        this.ptsDef = def;
        this.ptsNul = nul;
        this.ptsVictoire = vic;
        this.programmerMatchs();
    }

    public Championnat(int vic, int def, int nul, Club joueur, ArrayList<Club> participants) {
        this("Inconnue", vic, def, nul, joueur, participants);
    }

    public void programmerMatchs() {
        this.programmeMatch = new ArrayList<Match>();
        for (Club club_dom : this.participants) {
            for (Club club_ext : this.participants) {
                if (club_dom != club_ext) {
                    Match match = new Match(club_dom, club_ext);
                    this.programmeMatch.add(match);
                }
            }
        }
        Collections.shuffle(this.programmeMatch);
    }

    public void jouerMatch() {
        Match match = this.programmeMatch.remove(0);
        System.out.println(match);
        Club dom = match.getDomicile();
        Club ext = match.getExterieur();
        Club utilisateur = null;
        if (dom.equals(this.joueur) || ext.equals(this.joueur)) {
            if (dom.equals(this.joueur)) {
                utilisateur = dom;
            } else {
                utilisateur = ext;
            }
            if (dom.equals(this.joueur)) {
                match.getDomicile().programmerCompo();
                match.getExterieur().genererCompo();
            } else {
                match.getExterieur().programmerCompo();
                match.getDomicile().genererCompo();
            }
        } else {
            match.getDomicile().genererCompo();
            match.getExterieur().genererCompo();
        }
        Club gagnant = match.partie(utilisateur);


        if (gagnant == null) {
            System.out.println("match nul");
            this.classement.get(this.classement.indexOf(dom)).addPoints(this.ptsNul);
            this.classement.get(this.classement.indexOf(ext)).addPoints(this.ptsNul);
        } else {
            System.out.println("le gagnant est : " + gagnant.toString());
            if (gagnant == dom) {
                this.classement.get(this.classement.indexOf(dom)).addPoints(this.ptsVictoire);
                this.classement.get(this.classement.indexOf(ext)).addPoints(this.ptsDef);

            } else {
                this.classement.get(this.classement.indexOf(dom)).addPoints(this.ptsDef);
                this.classement.get(this.classement.indexOf(ext)).addPoints(this.ptsVictoire);

            }
        }
        System.out.println("\n\n\n");
    }

    /**
     * @return the classement
     */
    public void getClassement() {
        Collections.sort(this.classement, (club, t1) -> t1.getPoints() - club.getPoints()); //j'ai remplacé le new comparateur par une lambda, pareil mais plus court

        System.out.println("CLASSEMENT : ");
        for (Club club :
                this.classement) {
            System.out.println(this.classement.indexOf(club) + 1 + "-> " + club + " " + club.getPoints() + " point(s)");
        }
        System.out.println("\n");
    }


    public ArrayList<Match> getProgrammeMatch() {
        return programmeMatch;
    }
}
