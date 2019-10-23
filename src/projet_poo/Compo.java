package projet_poo;

import java.util.ArrayList;

class Compo {
    private ArrayList<Joueur> att;

    private ArrayList<Joueur> def;

    private Joueur gk;

    public Compo() {
        this.gk = null;
        this.att = new ArrayList<Joueur>();
        this.def = new ArrayList<Joueur>();
    }

    public void ajouter_joueur(String ligne, Joueur joueur) {
        if (this.getNbTitulaire() < 10) {

            switch (ligne) {
                case "att":
                    this.att.add(joueur);
                    break;
                case "def":
                    this.def.add(joueur);
                    break;
            }
        }
    }

    public void ajouter_gk(Joueur joueur) {
        if (this.gk == null) {
            this.gk = joueur;
        }
    }

    public int getNbTitulaire() {
        return this.att.size() + this.def.size();
    }

    /**
     * @return the att
     */
    public ArrayList<Joueur> getAtt() {
        return att;
    }

    /**
     * @return the def
     */
    public ArrayList<Joueur> getDef() {
        return def;
    }

    /**
     * @return the gk
     */
    public Joueur getGk() {
        return gk;
    }

    public ArrayList<Joueur> getTitulaire() {
        ArrayList<Joueur> retour = new ArrayList<Joueur>();
        for (Joueur j :
                this.getAtt()) {
            retour.add(j);
        }
        for (Joueur j :
                this.getDef()) {
            retour.add(j);
        }
        retour.add((this.getGk()));
        return retour;
    }

    public void afficheCompo() {

        System.out.println(this.getGk().toStringGardien());
        for (Joueur def : this.getDef()) {
            System.out.println(def.toStringDef());
        }
        for (Joueur att : this.getAtt()) {
            System.out.println(att.toStringAtt());
        }
    }

    public void reset() {
        this.att = new ArrayList<Joueur>();
        this.def = new ArrayList<Joueur>();
        this.gk = null;
    }

}
