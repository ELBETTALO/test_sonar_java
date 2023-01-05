/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.util.Random;


/**
  * Classe Objet : permet de créer et de modifier les caractéristiques d'un objet
 * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public abstract class Objet implements Utilisable{
    
    /**
     *Position de l'objet
     */
    public Point2D pos ;

    /**
     *Nom de l'objet
     */
    public String nom;

    /**
     *Score d'un objet
     */
    public int score;
    
    /**
     *Permet de creer un objet
     * @param pos position
     * @param nom Nom
     * @param score Score
     */
    public Objet( Point2D pos,String nom,int score) {
       
        this.pos = pos;
        this.nom=nom;
        this.score=score;
    }

    /**
     *Constructeur par defaut
     */
    public Objet() {
        this.pos = new Point2D(0,0) ;
        this.nom= "nom_objet_par_defaut";
        this.score= 3;
    }

    /**
     *
     * @return
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     *
     * @param pos
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
     Objet[][] Mat=new Objet[World.tailleW[0]][World.tailleW[1]];
    
    /**
     *Permet de generer un objet aleatoirement
     */
    public void genererObjet() {
        int i;
        Random alea = new Random();
        int j;

        for (i = 0; i < World.tailleW[0]; i++) {
            for (j = 0; j <World.tailleW[1]; j++) {
                int x=alea.nextInt(4);
                switch(x){
                    case 0 :{
                         PotionSoin ps=new PotionSoin();
                        ps.genererPotionSoin(); 
                        Mat[i][j]=ps;
                        break;
                    }
                    case 1:{
                        Epee ep=new Epee();
                        ep.genererEpee();
                        Mat[i][j]=ep;
                        break;
                    }
                    case 2:{
                         Nourriture N=new Nourriture();
                        N.genererNourriture();
                        Mat[i][j]=N;
                        break;
                    }
                    case 3:{
                        Mat[i][j]=null;
                        break;
                    }
                    default : {
                        break;
                    }
                }
            }
        }
    }

    /**
     *permet d'utiliser un objet
     */
    @Override
    public void utilisable(){
        
    }

    /**
     * Save un objet  dans la base des données
     *
     * @param connection la connection de la base des données
     * @param ID_sauvegarde id de la sauvegarde
     * @param id_inventaire id de l'inventaire
     * @param i le nombre de l'objet  qu'on va sauvegarder par rapport au autres objets
     */
    public abstract void saveToDatabase(Connection connection,int ID_sauvegarde,int id_inventaire,int i);

    /**
     * Get objet from database
     * @param connection la connection de la base des données
     * @param id id de la sauvegarde
     * @param id_inventaire id de l'inventaire
     * @param nom_objet nom de l'objet
     */
    public abstract void getFromDatabase(Connection connection, Integer id,int id_inventaire,String nom_objet);
    
}

