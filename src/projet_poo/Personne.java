package projet_poo;

abstract class Personne {
    private static int NB_PERSONNE = 0;

    private final String prenom;

    private final String nom;

    private int age;


    Personne(String prenom, String nom, int age) {
        this.prenom = prenom + "_" + NB_PERSONNE;
        this.nom = nom + "_" + NB_PERSONNE;
        this.age = age;
        NB_PERSONNE += 1;
    }

    @Override
    public String toString() {
        return this.prenom + " " + this.nom.toUpperCase() + " agé de " + this.age + "ans";
    }
}
