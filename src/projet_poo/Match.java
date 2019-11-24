package projet_poo;

import java.util.ArrayList;

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

    public void majEtatPhysique() {
        Club dom = this.domicile;
        Club ext = this.exterieur;

        for (Joueur j :
                dom.getEffectif()) {
            if (j.estTitulaire()) {
                j.seFatigue();
            } else {
                j.recupere();
            }
        }

        for (Joueur j :
                ext.getEffectif()) {
            if (j.estTitulaire()) {
                j.seFatigue();
            } else {
                j.recupere();
            }
        }
    }


    public Club partie(Club utilisateur) {
        double res = 0;
        for (int i = 0; i < 2; i++) {
            res += simu_mitemps();
            if (utilisateur != null) {
                if (res > 0) {
                    System.out.println("Domicile domine");
                } else {
                    System.out.println("Exterieur domine");
                }
                if (i == 0) {
                    utilisateur.getCompo().faireChangement();
                }
            }

        }


        this.domicile.getStade().remplissage();
        double bonus_stade = this.domicile.getStade().bonusStade();
        res += bonus_stade;
        if (utilisateur != null) {
            System.out.println("bonus domicile stade rempli : +" + bonus_stade);
            System.out.println("balance du match : " + res);
        }


        ///////////

        /////////
        if (res > 0.5) {
            return this.domicile;
        } else if (res < -0.5) {
            return this.exterieur;
        } else {
            return null;
        }

    }

    private double simu_mitemps() {
        Club dom = this.domicile;
        Club ext = this.exterieur;

        ArrayList<Joueur> ligne_att_dom = new ArrayList<Joueur>(dom.getCompo().getAtt());
        ArrayList<Joueur> ligne_att_ext = new ArrayList<Joueur>(ext.getCompo().getAtt());
        ArrayList<Joueur> ligne_def_dom = new ArrayList<Joueur>(dom.getCompo().getDef());
        ArrayList<Joueur> ligne_def_ext = new ArrayList<Joueur>(ext.getCompo().getDef());

        float gk_dom = dom.getCompo().getGk().getStat_gk();
        float gk_ext = ext.getCompo().getGk().getStat_gk();

        float att_dom = 0;
        for (Joueur j :
                ligne_att_dom) {
            att_dom += j.getStat_att();
        }
        float att_ext = 0;
        for (Joueur j :
                ligne_att_ext) {
            att_ext += j.getStat_att();
        }

        float def_dom = 0;
        for (Joueur j :
                ligne_def_dom) {
            def_dom += j.getStat_def();
        }

        float def_ext = 0;
        for (Joueur j :
                ligne_def_ext) {
            def_ext += j.getStat_def();
        }

        double score_dom = ((1.5 * att_dom) - def_ext) / gk_ext;
        double score_ext = ((1.5 * att_ext) - def_dom) / gk_dom;

        double res = score_dom - score_ext;

        this.majEtatPhysique();


        return res;
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