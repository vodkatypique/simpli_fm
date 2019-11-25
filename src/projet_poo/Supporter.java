package projet_poo;

public class Supporter extends Personne {
    private boolean hooligan;

    Supporter(String prenom, String nom, int age, boolean hooligan) {
        super(prenom, nom, age);
        this.hooligan = hooligan;
    }

    Supporter(String prenom, String nom, int age) {
        this(prenom, nom, age, false);
    }

    public boolean isHooligan() {
        return hooligan;
    }

    @Override
    public String toString() {
        return super.toString() + ((isHooligan()) ? " est hooligan" : "n'est pas un hooligan");
    }
}
