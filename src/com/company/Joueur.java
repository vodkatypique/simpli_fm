package com.company;

import java.util.Random;

class Joueur extends Personne {
    private float stat_att;

    private float stat_def;

    private float stat_gk;

    private Club club;


    public void jouer() {
    }

    public Joueur(String prenom, String nom, int age, int stat_att, int stat_def, int stat_gk, Club club) {
        super(prenom, nom, age);
        this.stat_att = stat_att;
        this.stat_def = stat_def;
        this.stat_gk = stat_gk;
        this.club = club;
    }

    public Joueur(Random rand) {
        this("defaut", "defaut", rand.nextInt(35), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), null);
    }

    /**
     * @return the stat_att
     */
    public float getStat_att() {
        return stat_att;
    }

    /**
     * @return the stat_def
     */
    public float getStat_def() {
        return stat_def;
    }

    /**
     * @return the stat_gk
     */
    public float getStat_gk() {
        return stat_gk;
    }

    public void integre_effectif(Club club) {
        if (this.club != null) {
            this.club.retraitJoueur(this);
        }
        this.club = club;
        club.ajoutJoueur(this);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.toStringGardien() + ", " + this.toStringDef() + ", " + this.toStringAtt();
    }

    public String toStringGardien() {
        return "GK : " + this.stat_gk;
    }

    public String toStringDef() {
        return "DEF : " + this.stat_def;
    }

    public String toStringAtt() {
        return "ATT : " + this.stat_att;
    }

}
