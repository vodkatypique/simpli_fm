package projet_poo;

import java.util.Random;

class Stade extends Structure {
    private int capacite;

    public Stade(int capacite, String date) {
        super(date);
        this.capacite = capacite;
    }

    public Stade(int capacite) {
        this(capacite, "inconnue");
    }

    public Stade() {
        this(10000, "inconnue");
    }

    public double remplissage() {
        Random rand = new Random();
        double borne_inf = Double.valueOf(rand.nextInt(this.capacite / 2));

        //System.out.println("inf : " +borne_inf/this.capacite);
        double res = borne_inf + rand.nextInt(capacite + 1);
        if (res > this.capacite) {
            res = this.capacite;
        }
        return res / this.capacite;
    }
}
