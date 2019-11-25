package projet_poo;

import java.util.Random;

class Joueur extends Personne {
    private float statAtt;

    private float statDef;

    private float statGk;

    private Club club;

    private float etatPhysique;


    public Joueur(String prenom, String nom, int age, int statAtt, int statDef, int statGk, Club club) {
        super(prenom, nom, (age < 16) ? 16 : age);
        this.statAtt = statAtt;
        this.statDef = statDef;
        this.statGk = statGk;
        this.club = club;
        this.etatPhysique = 1;

    }

    public Joueur(Random rand) {
        this("defaut", "defaut", rand.nextInt(35), rand.nextInt(20), rand.nextInt(20), rand.nextInt(20), null);
    }

    /**
     * @return the stat_att
     */
    public float getStatAtt() {
        return statAtt * this.etatPhysique;
    }

    /**
     * @return the stat_def
     */
    public float getStatDef() {
        return statDef * this.etatPhysique;
    }

    /**
     * @return the stat_gk
     */
    public float getStatGk() {
        return statGk * this.etatPhysique;
    }

    public void integreEffectif(Club club) {
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

    public String affichageString() {
        return super.toString() + ", " + this.toString();
    }

    public String toStringGardien() {
        return "GK : " + this.statGk;
    }

    public String toStringDef() {
        return "DEF : " + this.statDef;
    }

    public String toStringAtt() {
        return "ATT : " + this.statAtt;
    }

    public String toStringEtatPhysique() {
        return "PHYSIQUE : " + this.etatPhysique;
    }

    public float getEtatPhysique() {
        return etatPhysique;
    }

    public void seFatigue() {
        Random rnd = new Random();
        float perte = (float) (0.1 + rnd.nextFloat() * (0.15 - 0.1)); //alea entre 0.1 et 0.2
        if (this.etatPhysique >= perte) {
            this.etatPhysique -= perte;
        } else {
            this.etatPhysique = 0;
        }
    }

    public void recupere() {
        Random rnd = new Random();
        float gain = (float) (0.08 + rnd.nextFloat() * (0.25 - 0.05)); //alea entre 0.05 et 0.25
        if (this.etatPhysique <= 1 - gain) {
            this.etatPhysique += gain;
        } else {
            this.etatPhysique = 1;
        }
    }

    public boolean estTitulaire() {
        return this.club.getCompo().getTitulaire().contains(this);
    }
}
