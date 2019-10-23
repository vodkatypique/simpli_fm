package projet_poo;

abstract class Personne {
    private String prenom;

    private String nom;

    private int age;

    private boolean est_heureux;

    Personne(String prenom, String nom, int age, boolean heureux) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.est_heureux = heureux;
    }

    Personne(String prenom, String nom, int age) {
        this(prenom, nom, age, true);
    }

}
