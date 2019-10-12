package com.company;

import java.util.*;

class Championnat extends Structure {
    private ArrayList<Club> participants;

    private int point_victoire;

    private int point_nul;

    private int point_defaite;

    private Hashtable<Club, Integer> classement;

    private ArrayList<Match> programme_match;


    public Championnat(String date, int vic, int def, int nul, ArrayList<Club> participants) {
        super(date);
        this.classement = new Hashtable<Club, Integer>();
        for (Club club : participants) {
            this.classement.put(club, 0);
        }
        this.programme_match = new ArrayList<Match>();
        this.participants = participants;
        this.point_defaite = def;
        this.point_nul = nul;
        this.point_victoire = vic;
        this.programmer_matchs();
    }

    public Championnat(int vic, int def, int nul, ArrayList<Club> participants) {
        this("Inconnue", vic, def, nul, participants);
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
    }

    public void jouer_match() {
        Match match = this.programme_match.remove(0);
        System.out.println(match);
        Club dom = match.getDomicile();
        Club ext = match.getExterieur();
        Club gagnant = match.partie();


        if (gagnant == null) {
            System.out.println("match nul");
            this.classement.put(dom, this.classement.get(dom) + this.point_nul);
            this.classement.put(ext, this.classement.get(ext) + this.point_nul);
        } else {
            System.out.println("le gagnant est : " + gagnant.toString());
            if (gagnant == dom) {
                this.classement.put(dom, this.classement.get(dom) + this.point_victoire);
                this.classement.put(ext, this.classement.get(ext) + this.point_defaite);
            } else {
                this.classement.put(dom, this.classement.get(dom) + this.point_defaite);
                this.classement.put(ext, this.classement.get(ext) + this.point_victoire);
            }
        }
        System.out.println("\n");
    }

    /**
     * @return the classement
     */
    public Hashtable<Club, Integer> getClassement() {
        return this.classement;
    }

    public ArrayList<Match> getProgramme_match() {
        return programme_match;
    }
}
