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

/**
 * Classe nourriture permet de creer et modifier les caracteristiques d'une
 * nourriture
 *
 * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public class Nourriture extends Objet implements Utilisable {

    static Random alea = new Random();

    /**
     * Bonus
     */
    public int bonus;

    /**
     * Malus
     */
    public int malus;

    /**
     * Permet de generer une nouritture avec des bonus et malus aleatoirement
     */
    public void genererNourriture() {
        this.bonus = alea.nextInt(1, 15);
        this.malus = alea.nextInt(1, 10);
        this.nom = "chocolat";

    }

    /**
     * Permet d'ajouter le bonus à un personnage
     *
     * @param p personnage
     *
     */
    public void Bonus(Personnage p) {
        p.pagePar += this.bonus;
    }

    /**
     * Permet d'ajouter le malus à un personnage
     *
     * @param p Personnage
     */
    public void Malus(Personnage p) {
        p.degAtt += alea.nextInt(1, 10);

    }

    
    /**
     * Save une nourriture  dans la base des données
     *
     * @param connection la connection de la base des données
     * @param ID_sauvegarde id de la sauvegarde
     * @param id_inventaire id de l'inventaire
     * @param i le nombre de la nourriture  qu'on va sauvegarder par rapport au autres objets
     */
    @Override
    public void saveToDatabase(Connection connection, int ID_sauvegarde, int id_inventaire, int i) {
        try {
            String Query = "insert into nourriture(nom_nourriture,x,y)values('" + this.nom + i + "'," + this.pos.x + ',' + this.pos.y + ") ";
            PreparedStatement stm = connection.prepareStatement(Query);
            stm.executeUpdate();
            String Query2 = "insert into contient_nourriture(nom_nourriture,id_inventaire)values('" + this.nom + i + "'," + id_inventaire + ") ";
            PreparedStatement stm2 = connection.prepareStatement(Query2);
            stm2.executeUpdate();
            System.out.println("Nourriture nom:" + this.nom + i + " a la position:[" + this.pos.x + "," + this.pos.y + "]");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * Get nourriture from database
     * @param connection la connection de la base des données
     * @param id id de la sauvegarde
     * @param id_inventaire id de l'inventaire
     * @param nom_nourriture nom de la nourriture
     */
    @Override
    public void getFromDatabase(Connection connection, Integer id, int id_inventaire, String nom_nourriture) {
        try {
            String Query = "select nom_nourriture,x,y from nourriture inner join contient_nourriture using(nom_nourriture) inner join inventaire using(id_inventaire) inner join contient_inventaire using(id_inventaire) where id_sauvegarde= " + id + "and id_inventaire=" + id_inventaire + " and nom_nourriture='" + nom_nourriture + "'";
            PreparedStatement stm = connection.prepareStatement(Query);
            ResultSet rs = stm.executeQuery();
            rs.next();
            this.nom = rs.getString("nom_nourriture");
            this.pos.x = rs.getInt("x");
            this.pos.y = rs.getInt("y");
            System.out.println("nom nourriture est: " + this.nom + " x: " + this.pos.x + " y: " + this.pos.y);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
