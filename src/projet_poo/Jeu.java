package projet_poo;

import java.util.ArrayList;
import java.util.Random;

public class Jeu {
    private ArrayList<Club> participants = new ArrayList<Club>();
    private Championnat championnat = null;
    private ArrayList<Joueur> base_de_donnees_joueur;


    public Jeu(boolean joueur_humain, int nombre_bots, int taille_effectif, String nom_entr, String prenom_entr, int age_entr, int capa_stade, String nom_club, String date_club) {
        this.genererBddJoueur(taille_effectif * nombre_bots * 15);
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
        this.championnat = new Championnat(3, 0, 1, (joueur_humain) ? participants.get(0) : null, participants);
    }

    public Jeu(int nombre_bots, int taille_effectif) {
        this(false, nombre_bots, taille_effectif, null, null, 0, 0, null, null);
    }

    private void genererBddJoueur(int taille) {
        this.base_de_donnees_joueur = new ArrayList<Joueur>();
        Random rand = new Random();

        for (int i = 0; i < taille; i++) {
            this.base_de_donnees_joueur.add(new Joueur(rand));
        }
    }

    public void Jouer() {
        while (!this.championnat.getProgrammeMatch().isEmpty()) {
            this.championnat.jouerMatch();
            this.championnat.getClassement();
        }
    }


    @Override
    public String toString() {
        StringBuilder participant = new StringBuilder();
        participant.append("  --" + this.participants.size() + "clubs--  \n");
        StringBuilder stades = new StringBuilder();
        stades.append("  --" + Stade.NB_STADE + "stades--  \n");
        for (Club part :
                this.participants) {
            participant.append(part.toString()).append("\n");
            stades.append(part.getStade().toString()).append("\n");
        }

        StringBuilder joueurs = new StringBuilder();
        joueurs.append("  --" + this.base_de_donnees_joueur.size() + "joueurs--  \n");
        for (Joueur j :
                this.base_de_donnees_joueur) {
            joueurs.append(j.affichageString()).append("\n");
        }

        String entete = "#-----Presentation de la bdd-----#\n";
        return entete + participant + stades + joueurs;
    }
}
