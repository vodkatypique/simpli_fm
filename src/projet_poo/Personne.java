package projet_poo;

abstract class Personne {
    private static int NB_PERSONNE = 0;

    private String prenom;

    private String nom;

    private int age;


    Personne(String prenom, String nom, int age) {
        this.prenom = prenom + NB_PERSONNE;
        this.nom = nom + NB_PERSONNE;
        this.age = age;
        NB_PERSONNE += 1;
    }
}
