package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Championnat extends Structure {
    private ArrayList<Club> participants;
    private Club joueur;
    private int point_victoire;

    private int point_nul;

    private int point_defaite;

    private ArrayList<Club> classement;

    private ArrayList<Match> programme_match;


    public Championnat(String date, int vic, int def, int nul, Club joueur, ArrayList<Club> participants) {
        super(date);
        this.joueur = joueur;
        this.classement = new ArrayList<Club>();
        for (Club club : participants) {
            this.classement.add(club);
        }
        this.programme_match = new ArrayList<Match>();
        this.participants = participants;
        this.point_defaite = def;
        this.point_nul = nul;
        this.point_victoire = vic;
        this.programmer_matchs();
    }

    public Championnat(int vic, int def, int nul, Club joueur, ArrayList<Club> participants) {
        this("Inconnue", vic, def, nul, joueur, participants);
    }

    public void programmer_matchs() {
        this.programme_match = new ArrayList<Match>();
        for (Club club_dom : this.participants) {
            for (Club club_ext : this.participants) {
                if (club_dom != club_ext) {
                    Match match = new Match(club_dom, club_ext);
                    this.programme_match.add(match);
                }
            }
        }
        Collections.shuffle(this.programme_match);
    }

    public void jouer_match() {
        Match match = this.programme_match.remove(0);
        System.out.println(match);
        Club dom = match.getDomicile();
        Club ext = match.getExterieur();
        if (dom.equals(this.joueur) || ext.equals(this.joueur)) {
            if (dom.equals(this.joueur)) {
                match.getDomicile().programmer_compo();
                match.getExterieur().generer_compo();
            } else {
                match.getExterieur().programmer_compo();
                match.getDomicile().generer_compo();
            }
        } else {
            match.getDomicile().generer_compo();
            match.getExterieur().generer_compo();
        }
        Club gagnant = match.partie();


        if (gagnant == null) {
            System.out.println("match nul");
            this.classement.get(this.classement.indexOf(dom)).addPoints(this.point_nul);
            this.classement.get(this.classement.indexOf(ext)).addPoints(this.point_nul);
        } else {
            System.out.println("le gagnant est : " + gagnant.toString());
            if (gagnant == dom) {
                this.classement.get(this.classement.indexOf(dom)).addPoints(this.point_victoire);
                this.classement.get(this.classement.indexOf(ext)).addPoints(this.point_defaite);

            } else {
                this.classement.get(this.classement.indexOf(dom)).addPoints(this.point_defaite);
                this.classement.get(this.classement.indexOf(ext)).addPoints(this.point_victoire);

            }
        }
        System.out.println("\n\n\n");
    }

    /**
     * @return the classement
     */
    public void getClassement() {
        Collections.sort(this.classement, new Comparator<Club>() {
            @Override
            public int compare(Club club, Club t1) {
                return t1.getPoints() - club.getPoints();
            }
        });

        System.out.println("CLASSEMENT : ");
        for (Club club :
                this.classement) {
            System.out.println(this.classement.indexOf(club) + 1 + "-> " + club + " " + club.getPoints() + " point(s)");
        }
        System.out.println("\n");
    }


    public ArrayList<Match> getProgramme_match() {
        return programme_match;
    }
}
