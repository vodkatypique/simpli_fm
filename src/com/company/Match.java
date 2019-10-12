package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Match
 */
public class Match {

    private Club domicile;
    private Club exterieur;

    public Match(Club dom, Club ext) {
        this.domicile = dom;
        this.exterieur = ext;
    }

    public Club partie() {
        Club dom = this.domicile;
        Club ext = this.exterieur;

        float balance_att = 0;
        ArrayList<Joueur> att_dom = new ArrayList<Joueur>(dom.getCompo().getAtt());
        ArrayList<Joueur> att_ext = new ArrayList<Joueur>(ext.getCompo().getAtt());

        for (Joueur j : att_dom) {
            balance_att += j.getStat_att();
        }
        for (Joueur j : att_ext) {
            balance_att -= j.getStat_att();
        }

        float balance_def = 0;
        ArrayList<Joueur> def_dom = new ArrayList<Joueur>(dom.getCompo().getDef());
        ArrayList<Joueur> def_ext = new ArrayList<Joueur>(ext.getCompo().getDef());

        for (Joueur j : def_dom) {
            balance_def += j.getStat_def();
        }
        for (Joueur j : def_ext) {
            balance_def -= j.getStat_def();
        }

        float balance_gk = 0;
        balance_gk += dom.getCompo().getGk().getStat_gk();
        balance_gk -= ext.getCompo().getGk().getStat_gk();

        /*System.out.println("Compo Domicile : \n");
        this.domicile.getCompo().afficheCompo();

        System.out.println("\nCompo Exterieur : \n");
        this.exterieur.getCompo().afficheCompo();*/
        System.out.println("balance att : " + balance_att + ", balance def : " + balance_def + ", balance gk : " + balance_gk);
        double res = 1.5 * balance_att + balance_def + balance_gk;
        double bonus_stade = this.domicile.getStade().remplissage();
        res += bonus_stade;
        System.out.println("bonus domicile stade rempli : +" + bonus_stade);
        System.out.println("balance du match : " + res);
        if (res > 0.25) {
            return dom;
        } else if (res < -0.25) {
            return ext;
        } else {
            return null;
        }

    }

    /**
     * @return the domicile
     */
    public Club getDomicile() {
        return domicile;
    }

    /**
     * @return the exterieur
     */
    public Club getExterieur() {
        return exterieur;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.domicile + " contre " + this.exterieur;
    }
}