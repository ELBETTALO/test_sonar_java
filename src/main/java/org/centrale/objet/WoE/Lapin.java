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
 * Classe Lapin : Permet de définir et manipuler les caractéristique d'un
 * lapin
 * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public class Lapin extends Monstre {

    /**
     *Constructeur de la classe Lapin
     * @param pV Point de vie
     * @param dA degat d'attaque
     * @param pPar Point de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param p Position du Lapin
     */
    public Lapin(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        super(pV, dA, pPar, paAtt, paPar, p);
    }

    /**
     *Constructeur de copie
     * @param l Lapin de referance
     */
    public Lapin(Lapin l) {
        super(l);
    }

     /**
     *Constructeur par defaut
     */
    public Lapin() {
        this.degAtt = 50;
        this.ptVie = 100;
        this.ptPar = 80;
        this.pageAtt = 0;
        this.pagePar = 0;
        this.pos = new Point2D(-50, -10);
    }

    /**
     * Save un lapin  dans la base des données
     *
     * @param connection la connection de la base des données
     * @param ID_sauvegarde id de la sauvegarde
     * @param i le nombre du lapin  qu'on va sauvegarder par rapport au autres monstres
     */

    @Override
    public void saveToDatabase(Connection connection, int ID_sauvegarde, int i) {
        try {
            String Query = "insert into monstre(nom_monstre,x,y,Nom_metier)values('lap" + i + "'," + this.pos.x + "," + this.pos.y + ",'Lapin'" + ") ";
            PreparedStatement stm = connection.prepareStatement(Query);
            stm.executeUpdate();
            String Query1 = "insert into contient_monstre(nom_monstre,ID_sauvegarde) values('lap" + i + "'," + ID_sauvegarde + ") ";
            PreparedStatement stm1 = connection.prepareStatement(Query1);
            stm1.executeUpdate();
            System.out.println("Lapin nom:lap" + i);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     * Get lapin from database
     * @param connection la connection de la base des données
     * @param id id de la sauvegarde
     * @param nom_monstre nom du lapin
     */
    @Override
    public void getFromDatabase(Connection connection, Integer id, String nom_monstre) {
        try {

            String Query = "select x,y from monstre inner join contient_monstre using(nom_monstre) where id_sauvegarde= " + id + "and nom_type='Lapin' and nom_monstre='" + nom_monstre + "'";
            PreparedStatement stm = connection.prepareStatement(Query);
            ResultSet rs = stm.executeQuery();
            rs.next();
            while (rs.next()) {
                this.pos.x = rs.getInt("x");
                this.pos.y = rs.getInt("y");
                System.out.println("nom_monstre: " + nom_monstre + " x: " + this.pos.x + " y: " + this.pos.y);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
