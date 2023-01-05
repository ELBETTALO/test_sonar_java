/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import java.sql.Connection;
import java.util.Random;


/**
 * Classe Creature : Permet de définir et manipuler les caractéristique d'une
 * creature
 *
  * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public abstract class Creature implements Deplacable{

    /**
     * points de vie
     */
    public int ptVie;

    /**
     * Degré d'attaque
     */
    public int degAtt;

    /**
     * Point de parade
     */
    public int ptPar;

    /**
     * Pourcentage d'attaque
     */
    public int pageAtt;

    /**
     * Pourcentage de parade
     */
    public int pagePar;

    /**
     * Position
     */
    public Point2D pos;
    /**
     * Constructeur par defaut
     */
    public Creature(){
            this.ptVie=100;
            this.degAtt=0;
            this.ptPar=0;
            this.pageAtt=0;
            this.pagePar=0;
            this.pos=new Point2D();
        
    }

    /**
     *
     * @param c
     */
    public Creature(Creature c){
            this.ptVie=c.ptVie;
            this.degAtt=c.degAtt;
            this.ptPar=c.ptPar;
            this.pageAtt=c.pageAtt;
            this.pagePar=c.pagePar;
            this.pos=new Point2D(c.pos);
        }

    
    /**
     * Constructeur de la classe Creature
     *
     * @param pV Point de vie
     * @param dA degat d'attaque
     * @param pPar Point de parade
     * @param paAtt Pourcentage d'attaque
     * @param paPar Pourcentage de parade
     * @param p Position du creature
     */
    public Creature(int pV,int dA,int pPar,int paAtt,int paPar,Point2D p ){
            
            this.ptVie=pV;
            this.degAtt=dA;
            this.ptPar=pPar;
            this.pageAtt=paAtt;
            this.ptPar=paPar;
            this.pos= new Point2D (p);
        }
    /**
     * Methode abstraitre permettant l'affichage d'une creature
     */
    public abstract void Affiche();

    /**
     *
     * @return
     */
    public int getPtVie() {
        return ptVie;
    }

    /**
     *
     * @return
     */
    public int getDegAtt() {
        return degAtt;
    }

    /**
     *
     * @return
     */
    public int getPtPar() {
        return ptPar;
    }

    /**
     *
     * @return
     */
    public int getPageAtt() {
        return pageAtt;
    }

    /**
     *
     * @return
     */
    public int getPagePar() {
        return pagePar;
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
     * @param ptVie
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    /**
     *
     * @param degAtt
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    /**
     *
     * @param ptPar
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    /**
     *
     * @param pageAtt
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    /**
     *
     * @param pagePar
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    /**
     *
     * @param pos
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    /**
     * Permet de deplacer une Creature aléatoirement sur l'une des cases adjacentes
     *
     * @param w le Monde
     */
    @Override
        public void deplacer(World w){
        Point2D posult=this.pos;
        Random alea= new Random();
        try{
            int x=alea.nextInt(3);
            int y=alea.nextInt(3);
             while ((x==1) || (y==1)){
            x=alea.nextInt(3);
            y=alea.nextInt(3);  
            }
            if (w.Mat[x][y]!=1){
                this.pos.setPosition(this.pos.x+x-1,this.pos.y+y-1);
            }
            w.Mat[posult.x][posult.y]=0;
              
                }
        catch(IndexOutOfBoundsException e){
            System.out.println("limite du monde");
        }
            
            
    };

    /**
     *Methode abstraite permet de sauvegarder une creature dans la base des données
     * @param connection
     * @param ID_sauvegarde
     * @param i
     */
    public abstract void saveToDatabase(Connection connection,int ID_sauvegarde,int i);

    /**
     *Methode abstraite permet de recuperer une creature dans la base des données
     * @param connection
     * @param id
     * @param nom_creature
     */
    public abstract void getFromDatabase(Connection connection, Integer id,String nom_creature);
}
    

