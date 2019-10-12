package com.company;

import java.util.ArrayList;
import java.util.Random;


public class Main {

    public static void main(String[] args) {
        ArrayList<Club> participants = new ArrayList<Club>();

        Entraineur entr1 = new Entraineur("clement", "caffin", 20);

        Stade stade1 = new Stade(90000);

        Club club1 = new Club("1999", "club1", entr1, stade1);

        for (int i = 0; i < 19; i++) {
            participants.add(new Club());
        }

        participants.add(club1);
///////////////////////////////////////////////////////////////////////
        ArrayList<Joueur> base_de_donnees_joueur = genererBddJoueur(10000);


        for (Club club :
                participants) {
            club.genererEffectif(20, base_de_donnees_joueur);
        }



//////////////////////////////////////////////////////////////////////////

        Championnat champ = new Championnat(3, 0, 1, null, participants);

        while (!champ.getProgramme_match().isEmpty()) {
            champ.jouer_match();
            champ.getClassement();
        }
        //champ.getClassement();
    }

    public static ArrayList<Joueur> genererBddJoueur(int taille) {
        ArrayList<Joueur> base_de_donnees = new ArrayList<Joueur>();
        Random rand = new Random();

        for (int i = 0; i < taille; i++) {
            base_de_donnees.add(new Joueur(rand));
        }
        return base_de_donnees;
    }
}