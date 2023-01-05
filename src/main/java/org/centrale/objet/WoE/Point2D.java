/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

import static java.lang.Math.pow;

/**
 *Classe Point2D : Permet de définir et manipuler les caractéristique d'un
 * Point de 2 dimensions .
 * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public class Point2D {

    /**
     *cordonnée d'abscisse
     */
    public int x;

    /**
     *cordonné d'ordonné
     */
    public int y;

    /**
     *Constructeur par defaut
     */
    public Point2D() {
        x = 0;
        y = 0;

    }

    /**
     *Constructeur de la Classe Point2D
     * @param x abscisse du point
     * @param y ordonné du point
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *Constructeur de copie 
     * @param P
     */
    public Point2D(Point2D P) {
        this.x = P.x;
        this.y = P.y;

    }


    /**
     *
     * @param X
     */
    public void setX(int X) {
        this.x = X;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return  
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @param x abscisse du point
     * @param y ordonné du point
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;

    }

    /**
     *Translation d'un point 
     * @param dx 
     * @param dy
     */
    public void translate(int dx, int dy) {
        this.x = this.x + dx;
        this.y = this.y + dy;
    }

    /**
     * Calcul de la distance entre 2 points
     * @param P Point
     * @return la distance entre 2 points
     */
    public double distance(Point2D P) {
        double dis = Math.sqrt((this.x - P.x)*(this.x - P.x) +(this.y - P.y)*(this.y - P.y));
        return dis;

    }

    /**
     *Affiche les caracteristiques d'un point
     */
    public void Affiche() {
        System.out.println("[" + this.x + ";" + this.y + "]");
    }
    
    /**
     *Redefinition de la methode equals qui permet de verifier l'egalité entre 2 points
     * @param obj Point 
     * @return true si 2 points sont egaux et false sinnon
     */
    @Override
    public boolean equals(Object obj)
    {
 
        // checking if the two objects
        // pointing to same object
        if (this == obj)
            return true;
 
        // checking for two condition:
        // 1) object is pointing to null
        // 2) if the objects belong to
        // same class or not
        if (obj == null
            || this.getClass() != obj.getClass())
            return false;
 
        Point2D p1 = (Point2D)obj; // type casting object to the
                             // intended class type
 
        // checking if the two
        // objects share all the same values
        return this.x == p1.x && this.y == p1.y ;

    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        return hash;
    }


}
