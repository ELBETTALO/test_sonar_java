/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 * Permet la creation et la manipulation de la grille
 *
 * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public class Grille {

    private int nbLig;
    private int nbCol;
    private final String[][] grille = new String[50][50];

    /**
     * Permet de creer une grille par defaut
     */
    public Grille() {

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                this.grille[i][j] = "";
            }
        }
    }

    //Constructeur
    /**
     * Permet de creer la grille du monde
     *
     * @param w Monde
     * @param p Width
     * @param s Height
     * @param J Joueur
     */
    public Grille(World w, int p, int s, Joueur J) {
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                this.grille[i][j] = "";
            }
        }
        this.grille[J.p.pos.x][J.p.pos.y] = "J";
        for (int i = 0; i < w.Listecrea.size(); i++) {

            if (w.Listecrea.get(i) instanceof Guerrier) {
                this.grille[w.Listecrea.get(i).pos.x][w.Listecrea.get(i).pos.y] = "G ";
            } else if (w.Listecrea.get(i) instanceof Archer) {
                this.grille[w.Listecrea.get(i).pos.x][w.Listecrea.get(i).pos.y] = "A ";
            } else if (w.Listecrea.get(i) instanceof Loup) {
                this.grille[w.Listecrea.get(i).pos.x][w.Listecrea.get(i).pos.y] = "LO";
            } else if (w.Listecrea.get(i) instanceof Lapin) {
                this.grille[w.Listecrea.get(i).pos.x][w.Listecrea.get(i).pos.y] = "LA";
            } else if (w.Listecrea.get(i) instanceof Paysan) {
                this.grille[w.Listecrea.get(i).pos.x][w.Listecrea.get(i).pos.y] = "P ";
            }
        }
        for (int h = 0; h < w.tailleW[0]; h++) {
            for (int r = 0; r < w.tailleW[1]; r++) {
                if (w.Mat1[h][r] != null) {
                    if (this.grille[h][r] != "") {
                        this.grille[h][r] += "+";
                    }
                    if ("arme".equals(w.Mat1[h][r].nom)) {

                        this.grille[h][r] += "Ar ";
                    }
                    if ("chocolat".equals(w.Mat1[h][r].nom)) {
                        this.grille[h][r] += "cho";
                    }
                    if ("potionSoin".equals(w.Mat1[h][r].nom)) {
                        this.grille[h][r] += "PS ";
                    }
                }
                if (w.Mat1[h][r] == null) {
                    this.grille[h][r] += "   ";
                }

            }
        }

    }

    //Methodes
    /**
     *
     * @param w
     */
    public void Afficher(World w) {
        System.out.println();
        for (int i = 0; i < w.tailleW[0]; i++) {
            for (int j = 0; j < w.tailleW[1]; j++) {
                System.out.print(" | " + this.grille[i][j]);
            }
            System.out.println(" | ");
        }
        System.out.println();
    }
}
