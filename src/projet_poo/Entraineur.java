package projet_poo;

class Entraineur extends Personne {

    private Club club_entraine;

    public void demissionner() {
        this.club_entraine = null;
    }

    public Entraineur(String prenom, String nom, int age) {
        super(prenom, nom, age);
    }

    public Entraineur() {
        super("Prenom_", "Nom_", 25);
    }

    /**
     * @param club_entraine the club_entraine to set
     */
    public void setClub_entraine(Club club_entraine) {
        this.club_entraine = club_entraine;
    }

}
