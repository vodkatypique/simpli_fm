package projet_poo;

class Entraineur extends Personne {

    private static int NB_ENTRAINEUR = 0;

    private Club club_entraine;

    public void demissionner() {
        this.club_entraine = null;
    }

    public Entraineur(String prenom, String nom, int age) {
        super(prenom, nom, age);
        NB_ENTRAINEUR += 1;
    }

    public Entraineur() {
        super("Prenom_" + NB_ENTRAINEUR, "Nom_" + NB_ENTRAINEUR, 25);
    }

    /**
     * @param club_entraine the club_entraine to set
     */
    public void setClub_entraine(Club club_entraine) {
        this.club_entraine = club_entraine;
    }

}
