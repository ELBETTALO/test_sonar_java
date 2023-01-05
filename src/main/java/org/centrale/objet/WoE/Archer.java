/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Archer : Permet de définir et manipuler les caractéristique d'un
 * Archer
 *
 * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public class Archer extends Personnage implements Combattant {

    /**
     * Nombre des fleches
     */
    public int nbrFleches;

    /**
     * Constructeur par defaut
     */
    public Archer() {
        this.nom = "archer";
        this.degAtt = 20;
        this.ptVie = 100;
        this.ptPar = 20;
        this.pageAtt = 10;
        this.pagePar = 30;
        this.pos = new Point2D(10, 20);
        this.distAttMax = 50;
        this.nbrFleches = 5;
    }

    /**
     * Constructeur de la classe Archer
     *
     * @param n Nom
     * @param pV Point de vie
     * @param dA degat d'attaque
     * @param pPar Point de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param dMax distance maximale
     * @param p Position de l'Archer
     * @param nbFleches Nombre des fleches
     */
    public Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nbFleches) {
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);
        this.nbrFleches = nbFleches;
    }

    /**
     * Constructeur se base sur un lapin
     *
     * @param l Monstre de type lapin
     */
    public Archer(Lapin l) {
        this.nom = "arch";
        this.degAtt = l.degAtt;
        this.ptVie = l.ptVie;
        this.ptPar = l.ptPar;
        this.pageAtt = l.pageAtt;
        this.pagePar = l.pagePar;
        this.pos = new Point2D(l.pos);
        this.distAttMax = 50;

    }
    
    /**
     * Constructeur de copie
     *
     * @param A Archer
     */
    public Archer(Archer A) {
        this.nom = "Archernompardef";
        this.ptVie = A.ptVie;
        this.degAtt = A.degAtt;
        this.ptPar = A.ptPar;
        this.pageAtt = A.pageAtt;
        this.pagePar = A.pagePar;
        this.distAttMax = 5;
        this.pos = new Point2D(A.pos);
        this.nbrFleches = A.nbrFleches;
    }

    /**
     * Methode permettant le combat avec une  creature
     *
     * @param c Creature
     */
    @Override
    public void combattre(Creature c) {
        double d = this.pos.distance(c.pos);
        boolean attaque = true;
        Random x = new Random();
        if (d == 1) {
            int y = x.nextInt(1, 100);
            System.out.println(y);
            if (y > this.pageAtt) {
                attaque = false;
            }
            if (attaque) {
                int z = x.nextInt(1, 100);
                if (z > c.pagePar) {
                    c.ptVie = this.degAtt;
                } else {
                    c.ptVie = this.ptVie - c.ptPar;
                }
            }
        }
        if ((d < this.distAttMax) && (d > 1)) {
            int y = x.nextInt(1, 100);
            System.out.println("Rand du tirage aléatoire" + y);
            if (y > this.pageAtt) {
                attaque = false;
            }
            System.out.println("attaque " + attaque);
            this.nbrFleches -= 1;
            if (attaque) {
                c.ptVie -= this.degAtt;

            }
        }
        if (d >= this.distAttMax) {
            System.out.println("Echec");
        }

    }

    /**
     *
     * @param w
     */
    public void deplacer(World w) {
        String str1;
        String str2;
        Scanner sc = new Scanner(System.in);
        System.out.println("où est ce que tu veux te déplacer: x");
        str1 = sc.nextLine();
        System.out.println("où est ce que tu veux te déplacer: y");
        str2 = sc.nextLine();
        int i = Integer.parseInt(str1);
        int j = Integer.parseInt(str2);
        System.out.println("x avant déplacement: " + this.pos.x + "/ y avant déplacement: " + this.pos.y);
        while (i >= 50 || j >= 50 || w.Mat[i][j] == 1) {
            System.out.println("position invalide");
            System.out.println("où est ce que tu veux te déplacer: x");
            str1 = sc.nextLine();
            System.out.println("où est ce que tu veux te déplacer: y");
            str2 = sc.nextLine();
            i = Integer.parseInt(str1);
            j = Integer.parseInt(str2);
        }
        System.out.println("x après déplacement: " + this.pos.x + "/ y après déplacement: " + this.pos.y);
        this.pos.setPosition(i, j);
        w.Mat[i][j] = 1;
    }

    /**
     *Save un Archer dans la base des données
     *
     * @param connection la connection de la base des données
     * @param ID_sauvegarde id de la sauvegarde
     * @param i le nombre du Archer qu'on va sauvegarder par rapport au autres Personnages
     */
    @Override
    public void saveToDatabase(Connection connection, int ID_sauvegarde, int i) {
        try {
            String Query = "insert into Humanoide(x,y,nom_humanoide,Nom_metier)values(" + this.pos.x + "," + this.pos.y + ",'" + this.nom + i + "','Archer'" + ") ";
            PreparedStatement stm = connection.prepareStatement(Query);
            stm.executeUpdate();
            String Query1 = "insert into contient_humanoide(nom_humanoide,ID_sauvegarde) values('" + this.nom + i + "'," + ID_sauvegarde + ") ";
            PreparedStatement stm1 = connection.prepareStatement(Query1);
            stm1.executeUpdate();
            System.out.println("Archer nom:" + this.nom + i);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

     /**
     *Get Archer from database
     * @param connection la connection de la base des données
     * @param id id de la sauvegarde
     * @param nom_humanoide nom d'Archer
     */
    @Override
    public void getFromDatabase(Connection connection, Integer id, String nom_humanoide) {
        try {
            String Query = "select x,y,nom_humanoide from humanoide inner join contient_humanoide using(nom_humanoide) where id_sauvegarde= " + id + "and nom_metier='Archer'and nom_humanoide='" + nom_humanoide + "'";
            PreparedStatement stm = connection.prepareStatement(Query);
            ResultSet rs = stm.executeQuery();
            rs.next();
            while (rs.next()) {
                this.nom = rs.getString("nom_humanoide");
                this.pos.x = rs.getInt("x");
                this.pos.y = rs.getInt("y");
                System.out.println("Archer: nom: " + this.nom + " x: " + this.pos.x + " y: " + this.pos.y);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
