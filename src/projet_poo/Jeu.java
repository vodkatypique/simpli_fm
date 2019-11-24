package projet_poo;

import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    private ArrayList<Club> participants = new ArrayList<Club>();
    private Championnat championnat = null;
    private ArrayList<Joueur> base_de_donnees_joueur;


    public Jeu(boolean joueur_humain, int nombre_bots, int taille_effectif, String nom_entr, String prenom_entr, int age_entr, int capa_stade, String nom_club, String date_club) {
        this.genererBddJoueur(taille_effectif * nombre_bots * 20);
        for (int i = 0; i < nombre_bots + 1; i++) {
            this.participants.add(new Club());
        }
        for (Club club :
                participants) {
            club.genererEffectif(taille_effectif, this.base_de_donnees_joueur);
        }
        if (joueur_humain) {
            participants.remove(0);
            Entraineur entr = new Entraineur(prenom_entr, nom_entr, age_entr);
            Stade stade = new Stade(capa_stade);
            Club club = new Club(date_club, nom_club, entr, stade);
            participants.add(0, club);
            participants.get(0).genererEffectif(taille_effectif, this.base_de_donnees_joueur);
        }
        this.championnat = new Championnat(3, 0, 1, (joueur_humain == true) ? participants.get(0) : null, participants);
        this.Jouer();
    }

    public Jeu(int nombre_bots, int taille_effectif, int taille_bdd) {
        this(false, nombre_bots, taille_effectif, null, null, 0, 0, null, null);
    }

    private ArrayList<Joueur> genererBddJoueur(int taille) {
        this.base_de_donnees_joueur = new ArrayList<Joueur>();
        Random rand = new Random();

        for (int i = 0; i < taille; i++) {
            this.base_de_donnees_joueur.add(new Joueur(rand));
        }
        return base_de_donnees_joueur;
    }

    private void Jouer() {
        while (!this.championnat.getProgramme_match().isEmpty()) {
            this.championnat.jouer_match();
            this.championnat.getClassement();
        }
    }


}
