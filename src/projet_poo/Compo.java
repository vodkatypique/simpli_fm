package projet_poo;

import java.util.ArrayList;
import java.util.Scanner;

class Compo {
    private ArrayList<Joueur> att;

    private ArrayList<Joueur> def;

    private Joueur gk;

    private ArrayList<Joueur> remplacants;

    public Compo() {
        this.gk = null;
        this.att = new ArrayList<Joueur>();
        this.def = new ArrayList<Joueur>();
        this.remplacants = new ArrayList<Joueur>();

    }

    public void ajouter_remplacant(Joueur joueur) {
        if (this.remplacants.size() < 7) {
            this.remplacants.add(joueur);
        }
    }

    public boolean contient(Joueur j) {
        boolean res = false;
        System.out.println(this.def.contains(j));
        if (this.gk == j || this.def.contains(j) || this.att.contains(j)) {
            res = true;
        }
        return res;
    }

    public void reset_remplacant() {
        this.remplacants = new ArrayList<Joueur>();
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
            System.out.println(def.toStringDef() + " | " + def.toStringEtatPhysique() + "| index : " + this.getTitulaire().indexOf(def));
        }
        for (Joueur att : this.getAtt()) {
            System.out.println(att.toStringAtt() + " | " + att.toStringEtatPhysique() + "| index : " + this.getTitulaire().indexOf(att));
        }
    }

    public void afficheRemplacants() {
        for (Joueur j :
                this.remplacants) {
            System.out.println(j.toString());
        }
    }

    public void reset() {
        this.att = new ArrayList<Joueur>();
        this.def = new ArrayList<Joueur>();
        this.gk = null;
    }

    public void faireChangement() {
        Scanner scan = new Scanner(System.in);
        System.out.println("combien de changements ? ");
        int choix = scan.nextInt();
        while (choix < 0 || choix > 3) {
            System.out.println("combien de changements ? entre 1 et 3");
            choix = scan.nextInt();
        }
        this.afficheCompo();
        for (int i = 0; i < choix; i++) {
            System.out.println("QUI SORT ? ");
            choix = scan.nextInt();
            while (choix > 10) {
                System.out.println("QUI ENTRE ? ");
                this.afficheRemplacants();
                choix = scan.nextInt();
            }
            Joueur sortant = this.getTitulaire().get(choix);
            if (this.gk == sortant) {
                this.gk = null;
            }

            this.def.remove(sortant);
            this.att.remove(sortant);

            for (Joueur j :
                    this.remplacants) {
                System.out.println(j.toString() + " Index : " + this.remplacants.indexOf(j));
            }
            System.out.println("QUI ENTRE ? ");
            choix = scan.nextInt();
            while (choix > 6) {
                System.out.println("QUI ENTRE ? ");
                choix = scan.nextInt();
            }
            System.out.println("il entre en att ou en def ou gk ? ");
            String poste = scan.next();
            while (!poste.equalsIgnoreCase("att") && !poste.equalsIgnoreCase("def") && !poste.equalsIgnoreCase("gk")) {
                System.out.println("ATT ou DEF ou GK");
                poste = scan.next();
            }

            if (poste.equalsIgnoreCase("gk")) {
                this.gk = this.remplacants.get(choix);
            } else if (poste.equalsIgnoreCase("att")) {
                this.att.add(this.remplacants.get(choix));
            } else {
                this.def.add(this.remplacants.get(choix));
            }
            this.remplacants.remove(choix);
        }
    }

}
