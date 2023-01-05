/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Classe Paysan : Permet de définir et manipuler les caractéristique d'un
 * Paysan
 * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public class Paysan extends Personnage {

     /**
     *Constructeur de la classe Paysan
     * @param n Nom 
     * @param pV Point de vie
     * @param dA degat d'attaque
     * @param pPar Point de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param dMax distance maximale
     * @param p Position du Paysan
     */
    public Paysan(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p) {
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);
    }

    /**
     *Constructeur se base sur un Personnage
     * @param p Paysan
     */    public Paysan(Paysan p) {
        super(p);
    }

    /**
     *Constructeur par defaut
     */
    public Paysan() {
        this.nom = "pays";
        this.degAtt = 5;
        this.ptVie = 100;
        this.ptPar = 30;
        this.pageAtt = 15;
        this.pagePar = 25;
        this.pos = new Point2D(1, 10);
    }

    /**
     *Save un paysan dans la base des données
     *
     * @param connection la connection de la base des données
     * @param ID_sauvegarde id de la sauvegarde
     * @param i le nombre du paysan qu'on va sauvegarder par rapport au autres Personnages
     */
    @Override
    public void saveToDatabase(Connection connection, int ID_sauvegarde, int i) {
        try {

            String Query = "insert into Humanoide(x,y,nom_humanoide,Nom_metier)values(" + this.pos.x + "," + this.pos.y + ",'" + this.nom + i + "','Paysan'" + ") ";
            PreparedStatement stm = connection.prepareStatement(Query);
            stm.executeUpdate();
            String Query1 = "insert into contient_humanoide(nom_humanoide,ID_sauvegarde) values('" + this.nom + i + "'," + ID_sauvegarde + ") ";
            PreparedStatement stm1 = connection.prepareStatement(Query1);
            stm1.executeUpdate();
            System.out.println("Paysan nom:" + this.nom + i);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     *Get Paysan from database
     * @param connection la connection de la base des données
     * @param id id de la sauvegarde
     * @param nom_humanoide nom du paysan
     */
    @Override
    public void getFromDatabase(Connection connection, Integer id, String nom_humanoide) {
        try {
            String Query = "select x,y,nom_humanoide from humanoide inner join contient_humanoide using(nom_humanoide) where id_sauvegarde= " + id + "and nom_metier='Paysan' and nom_humanoide='" + nom_humanoide + "'";
            PreparedStatement stm = connection.prepareStatement(Query);
            ResultSet rs = stm.executeQuery();
            rs.next();
            while (rs.next()) {
                this.nom = rs.getString("nom_humanoide");
                this.pos.x = rs.getInt("x");
                this.pos.y = rs.getInt("y");
                System.out.println("Paysan: nom: " + this.nom + " x: " + this.pos.x + " y: " + this.pos.y);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
