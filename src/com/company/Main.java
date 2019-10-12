package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        ArrayList<Club> participants = new ArrayList<Club>();

        //Entraineur entr = new Entraineur("clement", "caffin", 20);

        Entraineur entr2 = new Entraineur("clement", "caffin", 20);

        Entraineur entr3 = new Entraineur("clement2", "caffin2", 20);

        //Stade stade1 = new Stade();
        Stade stade2 = new Stade(20000);
        Stade stade3 = new Stade(90000);

        //Club club1 = new Club("1999", "club1", entr, stade1);
        Club club2 = new Club("2000", "club2", entr2, stade2);
        Club club3 = new Club("2000", "club3", entr3, stade3);
///////////////////////////////////////////////////////////////////////
        ArrayList<Joueur> base_de_donnees_joueur = genererBddJoueur(10000000);
        genererEffectif(club2, 50, base_de_donnees_joueur);
        genererEffectif(club3, 50, base_de_donnees_joueur);


/*
//////////////////////////////////////////////////////////////////////////


        //participants.add(club1);
        participants.add(club2);
        participants.add(club3);
m!
        Championnat champ = new Championnat(3, 0, 1, participants);
        while (!champ.getProgramme_match().isEmpty()) {
            Match match = champ.getProgramme_match().get(0);
            match.getDomicile().programmer_compo();
            match.getExterieur().programmer_compo();
            champ.jouer_match();
        }
        System.out.println(champ.getClassement());
   */
    }

    public static ArrayList<Joueur> genererBddJoueur(int taille) {
        ArrayList<Joueur> base_de_donnees = new ArrayList<Joueur>();
        Random rand = new Random();

        for (int i = 0; i < taille; i++) {
            base_de_donnees.add(new Joueur(rand));
        }
        return base_de_donnees;
    }

    public static void genererEffectif(Club club, int taille, ArrayList<Joueur> bdd) {
        for (int i = 0; i < taille; i++) {
            Random index = new Random();
            int rnd = index.nextInt(bdd.size());
            club.ajoutJoueur(bdd.get(rnd));
            bdd.remove(rnd);
        }
    }
}