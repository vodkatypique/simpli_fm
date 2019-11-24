package projet_poo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Club extends Structure {

    private static int NB_CLUB = 0;

    private Entraineur entraineur;

    private Compo compo;

    private String nom;

    private Stade stade;

    private ArrayList<Joueur> effectif;

    private int points;


    public Club(String date, String nom, Entraineur entr, Stade stade) {
        super(date);
        NB_CLUB += 1;
        this.stade = stade;
        this.nom = nom;
        this.entraineur = entr;
        this.entraineur.setClub_entraine(this);
        this.effectif = new ArrayList<Joueur>();

        this.compo = new Compo();
    }

    public Club() {
        this("Inconnue", "club_" + NB_CLUB, new Entraineur(), new Stade());
    }

    /**
     * @return the compo
     */
    public Compo getCompo() {
        return this.compo;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.nom;
    }

    /**
     * @return the stade
     */
    public Stade getStade() {
        return this.stade;
    }

    public ArrayList<Joueur> getEffectif() {
        return this.effectif;
    }

    public void ajoutJoueur(Joueur joueur) {
        this.effectif.add(joueur);
    }

    public void retraitJoueur(Joueur joueur) {
        this.effectif.remove(joueur);
    }


    public void programmer_compo() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String change = "y";
        if (this.compo.getGk() != null) {
            this.compo.afficheCompo();
            System.out.println("changer de compo ? Y/N");
            change = myObj.next();
        }
        if (change.equalsIgnoreCase("y")) {
        this.compo.reset();
        ArrayList<Joueur> selectionnable = new ArrayList<Joueur>(this.effectif);

        while (this.compo.getGk() == null) {
            System.out.println("choisissez votre gardien : ");
            for (Joueur j : selectionnable) {
                System.out.println("GK : " + j.getStat_gk() + ", " + j.toStringEtatPhysique() + " index : " + selectionnable.indexOf(j));
            }
            System.out.println("Index du joueur choisi pour jouer GK");

            int choix = myObj.nextInt();  // Read user input
            while (selectionnable.size() - 1 < choix) {
                System.out.println("Index du joueur choisi pour jouer GK");
                choix = myObj.nextInt();  // Read user input
            }

            this.compo.ajouter_gk(selectionnable.get(choix));
            selectionnable.remove(choix);
        }

        while (this.compo.getNbTitulaire() < 10) {
            this.compo.afficheCompo();
            int nbDispo = 10 - this.compo.getNbTitulaire();
            System.out.println("encore " + nbDispo + " joueur a choisir");
            for (Joueur j : selectionnable) {
                System.out.println("att: " + j.getStat_att() + " | def: " + j.getStat_def() + "|" + j.toStringEtatPhysique() + " |index : " + selectionnable.indexOf(j));
            }

            System.out.println("def ou att ?");
            String choix = myObj.next();  // Read user input
            while (!choix.equalsIgnoreCase("att") && !choix.equalsIgnoreCase("def")) {
                System.out.println("def ou att ?");
                choix = myObj.next();
            }

            System.out.println("combien de " + choix + " ?");
            int occ = myObj.nextInt();
            while (occ > nbDispo || occ < 1) {
                System.out.println("combien de " + choix + " ? maximum de " + nbDispo);
                occ = myObj.nextInt();
            }


            for (int i = 0; i < occ; i++) {
                System.out.println("choisissez votre " + choix + ": ");

                for (Joueur j : selectionnable) {
                    if (choix.equalsIgnoreCase("att")) {
                        System.out.println(j.getStat_att() + ", " + j.toStringEtatPhysique() + " index : " + selectionnable.indexOf(j));
                    } else {
                        System.out.println(j.getStat_def() + ", " + j.toStringEtatPhysique() + " index : " + selectionnable.indexOf(j));
                    }
                }
                System.out.println("Index du joueur choisi pour jouer " + choix + " : ");

                int index = myObj.nextInt();  // Read user input
                while (selectionnable.size() - 1 < index) {
                    System.out.println("Index du joueur choisi pour jouer " + choix + " : ");
                    index = myObj.nextInt();  // Read user input
                }

                this.compo.ajouter_joueur(choix.toLowerCase(), selectionnable.get(index));
                selectionnable.remove(index);


            }
        }
        }
        this.generer_remplacant();
    }

    public void genererEffectif(int taille, ArrayList<Joueur> bdd) {
        for (int i = 0; i < taille; i++) {
            Random index = new Random();
            int rnd = index.nextInt(bdd.size());
            bdd.get(rnd).integre_effectif(this);
            bdd.remove(rnd);
        }
    }


    public void generer_remplacant() {
        this.compo.reset_remplacant();
        Random rnd = new Random();
        ArrayList<Joueur> remplacantPossible = new ArrayList<Joueur>();
        for (Joueur j :
                this.effectif) {
            if (!this.compo.contient(j)) {
                remplacantPossible.add(j);
            }
        }
        for (int i = 0; i < 7; i++) {
            int choix = rnd.nextInt(remplacantPossible.size() - 1);
            Joueur rempl = remplacantPossible.get(choix);
            this.compo.ajouter_remplacant(rempl);
            remplacantPossible.remove(rempl);
        }
    }

    public void generer_compo() {
        this.compo.reset();
        ArrayList<Joueur> selectionnable = new ArrayList<Joueur>(this.effectif);

        Random gen_rnd = new Random();
        int rnd = gen_rnd.nextInt((this.effectif.size()));
        this.compo.ajouter_gk(this.effectif.get(rnd));
        selectionnable.remove(rnd);

        rnd = gen_rnd.nextInt(7);
        while (rnd < 3) {
            rnd = gen_rnd.nextInt(7);
        }

        for (int i = 0; i < rnd; i++) {
            this.compo.ajouter_joueur("def", this.effectif.get(rnd));
            selectionnable.remove(rnd);
        }

        while (this.compo.getNbTitulaire() < 10) {
            int titu = this.compo.getNbTitulaire();
            rnd = gen_rnd.nextInt(selectionnable.size());
            this.compo.ajouter_joueur("att", this.effectif.get(rnd));
            selectionnable.remove(rnd);

        }
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getNbClub() {
        return this.NB_CLUB;
    }
}

