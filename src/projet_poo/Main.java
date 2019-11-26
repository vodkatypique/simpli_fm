/*
 * @nickname : vodkatypique
 * @name : CAFFIN Clement
 * @date : 11 / 2019
 */

package projet_poo;

public class Main {

    public static void main(String[] args) {
        //Jeu jeu = new Jeu(20, 50);
        Jeu jeu = new Jeu(true, 1, 20, "Dupond", "Gégé", 65, 90000, "FCV", "1999");
        //System.out.println(jeu);
        jeu.Jouer();
    }


}