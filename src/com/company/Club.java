package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class Club extends Structure {
    private Entraineur entraineur;

    private Compo compo;

    private String nom;

    private Stade stade;

    private ArrayList<Joueur> effectif;


    public Club(String date, String nom, Entraineur entr, Stade stade) {
        super(date);
        this.stade = stade;
        this.nom = nom;
        this.entraineur = entr;
        this.entraineur.setClub_entraine(this);
        this.effectif = new ArrayList<Joueur>();

        this.compo = new Compo();
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

        ArrayList<Joueur> selectionnable = new ArrayList<Joueur>(this.effectif);

        while (this.compo.getGk() == null) {
            System.out.println("choisissez votre gardien : ");
            for (Joueur j : selectionnable) {
                System.out.println(j.getStat_gk() + "index : " + selectionnable.indexOf(j));
            }
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
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
            System.out.println("encore " + (10 - this.compo.getNbTitulaire()) + "joueur a choisir");
            for (Joueur j : selectionnable) {
                System.out.println("att: " + j.getStat_att() + "def: " + j.getStat_def() + "index : " + selectionnable.indexOf(j));
            }
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("def ou att ?");

            String choix = myObj.next();  // Read user input

            while (!choix.equals("att") || !choix.equals("def")) {
                System.out.println("def ou att ?");
                choix = myObj.next();
            }
            if (choix.equals("att")) {
                System.out.println("choisissez votre att : ");
                for (Joueur j : selectionnable) {
                    System.out.println(j.getStat_att() + "index : " + selectionnable.indexOf(j));
                }
                System.out.println("Index du joueur choisi pour jouer att");

                int att = myObj.nextInt();  // Read user input
                while (selectionnable.size() - 1 < att) {
                    System.out.println("Index du joueur choisi pour jouer att");
                    att = myObj.nextInt();  // Read user input
                }

                this.compo.ajouter_joueur("att", selectionnable.get(att));
                selectionnable.remove(att);
            } else {
                System.out.println("choisissez votre def : ");
                for (Joueur j : selectionnable) {
                    System.out.println(j.getStat_def() + "index : " + selectionnable.indexOf(j));
                }
                System.out.println("Index du joueur choisi pour jouer def");

                int def = myObj.nextInt();  // Read user input
                while (selectionnable.size() - 1 < def) {
                    System.out.println("Index du joueur choisi pour jouer def");
                    def = myObj.nextInt();  // Read user input
                }

                this.compo.ajouter_joueur("def", selectionnable.get(def));
                selectionnable.remove(def);
            }
        }
    }
}

