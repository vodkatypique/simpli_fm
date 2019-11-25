package projet_poo;

import java.util.ArrayList;
import java.util.Random;

class Stade extends Structure {
    static final int CAPADEFAUT = 10000;
    private int capacite;

    private ArrayList<Supporter> supporters;

    public Stade(int capacite, String date) {
        super(date);
        this.capacite = capacite;
        this.supporters = new ArrayList<Supporter>(this.capacite);
    }

    public Stade(int capacite) {
        this(capacite, "inconnue");
    }

    public Stade() {
        this(CAPADEFAUT, "inconnue");
    }

    public void remplissage() {
        while (!this.supporters.isEmpty()) {
            this.supporters.remove(this.supporters.size() - 1);
        }
        Random rand = new Random();
        double borne_inf = Double.valueOf(rand.nextInt(this.capacite / 2));

        //System.out.println("inf : " +borne_inf/this.capacite);
        double res = borne_inf + rand.nextInt(capacite + 1);
        if (res > this.capacite) {
            res = this.capacite;
        }
        for (int i = 0; i < res; i++) {
            boolean hooligan = rand.nextBoolean() && rand.nextBoolean() && rand.nextBoolean(); //pour diminuer la proportion de hooligan max a 1/8
            this.supporters.add(new Supporter("prenomsupp", "nomsupp", 18, hooligan));
        }
    }

    public int getCapacite() {
        return capacite;
    }

    public int getNbSupp() {
        return this.supporters.size();
    }

    public double bonusStade() {
        double bonus = 0;
        for (Supporter supp :
                this.supporters) {
            if (supp.isHooligan()) {
                bonus += 0.7;
            } else {
                bonus += 1;
            }
        }
        bonus /= this.getCapacite();

        return bonus;
    }

    @Override
    public String toString() {
        return "Stade de " + this.capacite + " places, " + super.toString();
    }
}
