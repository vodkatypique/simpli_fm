package projet_poo;

import java.util.Random;

class Joueur extends Personne {
    private float stat_att;

    private float stat_def;

    private float stat_gk;

    private Club club;

    private float etat_physique;


    public Joueur(String prenom, String nom, int age, int stat_att, int stat_def, int stat_gk, Club club) {
        super(prenom, nom, age);
        this.stat_att = stat_att;
        this.stat_def = stat_def;
        this.stat_gk = stat_gk;
        this.club = club;
        this.etat_physique = 1;
    }

    public Joueur(Random rand) {
        this("defaut", "defaut", rand.nextInt(35), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), null);
    }

    /**
     * @return the stat_att
     */
    public float getStat_att() {
        return stat_att * this.etat_physique;
    }

    /**
     * @return the stat_def
     */
    public float getStat_def() {
        return stat_def * this.etat_physique;
    }

    /**
     * @return the stat_gk
     */
    public float getStat_gk() {
        return stat_gk * this.etat_physique;
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
        return this.toStringGardien() + ", " + this.toStringDef() + ", " + this.toStringAtt() + ", " + this.toStringEtatPhysique();
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

    public String toStringEtatPhysique() {
        return "PHYSIQUE : " + this.etat_physique;
    }

    public float getEtat_physique() {
        return etat_physique;
    }

    public void seFatigue() {
        Random rnd = new Random();
        float perte = (float) (0.1 + rnd.nextFloat() * (0.15 - 0.1)); //alea entre 0.1 et 0.2
        if (this.etat_physique >= perte) {
            this.etat_physique -= perte;
        } else {
            this.etat_physique = 0;
        }
    }

    public void recupere() {
        Random rnd = new Random();
        float gain = (float) (0.08 + rnd.nextFloat() * (0.25 - 0.05)); //alea entre 0.05 et 0.25
        if (this.etat_physique <= 1 - gain) {
            this.etat_physique += gain;
        } else {
            this.etat_physique = 1;
        }
    }

    public boolean estTitulaire() {
        return this.club.getCompo().getTitulaire().contains(this);
    }
}
