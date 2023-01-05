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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.centrale.objet.WoE.DatabaseTools;

/**
 * Classe Guerrier : Permet de définir et manipuler les caractéristique d'un
 * Guerrier
 * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public class Guerrier extends Personnage implements Combattant {

    /**
     *Constructeur par defaut
     */
    public Guerrier() {
        this.degAtt = 50;
        this.ptVie = 100;
        this.ptPar = 80;
        this.pageAtt = 30;
        this.pagePar = 40;
        this.pos = new Point2D(1, 10);
        this.nom = "guerr";
    }

     /**
     *Constructeur de copie
     * @param g Guerrier de referance
     */
    public Guerrier(Guerrier g) {
        super(g);
    }

    /**
     *Constructeur de la classe Loup
     * @param n Nom
     * @param pV Point de vie
     * @param dA degat d'attaque
     * @param pPar Point de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param dMax distance d'attaque maximale
     * @param p Point de 2 dimension
     */
    public Guerrier(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p) {
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);
    }

    /**
     *Methode permettant le combat avec une creature
     * @param c Creature
     */
    @Override
    public void combattre(Creature c) {
        double d = this.pos.distance(c.pos);
        boolean attaque = true;
        Random x = new Random();
        if (d == 1) {
            int y = x.nextInt(1, 20);
            System.out.println("Rand du tirage aléatoire" + y);
            if (y > this.pageAtt) {
                attaque = false;
            }
            System.out.println("attaque " + attaque);
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
            if (y > this.pageAtt) {
                attaque = false;
            }
            if (attaque) {
                c.ptVie -= this.degAtt;

            }
        }
        if (d >= this.distAttMax) {
            System.out.println("Echec");
        }

    }

     /**
     *Save un Guerrier dans la base des données
     *
     * @param connection la connection de la base des données
     * @param ID_sauvegarde id de la sauvegarde
     * @param i le nombre du guerrier qu'on va sauvegarder par rapport au autres Personnages
     */
    @Override
    public void saveToDatabase(Connection connection, int ID_sauvegarde, int i) {
        try {
            String Query = "insert into Humanoide(x,y,nom_humanoide,Nom_metier)values(" + this.pos.x + "," + this.pos.y + ",'" + this.nom + i + "','Guerrier'" + ") ";
            PreparedStatement stm = connection.prepareStatement(Query);
            stm.executeUpdate();
            String Query1 = "insert into contient_humanoide(nom_humanoide,ID_sauvegarde) values('" + this.nom + i + "'," + ID_sauvegarde + ") ";
            PreparedStatement stm1 = connection.prepareStatement(Query1);
            stm1.executeUpdate();
            System.out.println("Guerreir nom:" + this.nom + i);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     *Get Guerrier from database
     * @param connection la connection de la base des données
     * @param id id de la sauvegarde
     * @param nom_humanoide nom du Guerrier
     */
    @Override
    public void getFromDatabase(Connection connection, Integer id, String nom_humanoide) {
        try {
            String Query = "select x,y,nom_humanoide from humanoide inner join contient_humanoide using(nom_humanoide) where id_sauvegarde= " + id + "and nom_metier='Guerrier' and nom_humanoide='" + nom_humanoide + "'";
            PreparedStatement stm = connection.prepareStatement(Query);
            ResultSet rs = stm.executeQuery();
            rs.next();
            while (rs.next()) {
                this.nom = rs.getString("nom_humanoide");
                this.pos.x = rs.getInt("x");
                this.pos.y = rs.getInt("y");
                System.out.println("Guerrier: nom: " + this.nom + " x: " + this.pos.x + " y: " + this.pos.y);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
