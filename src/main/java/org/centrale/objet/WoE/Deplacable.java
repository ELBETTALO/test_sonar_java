/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *Classe permet de definir les elements de jeu qui peuvent se deplacer
  * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public interface Deplacable {
    
    /**
     * Methode abstraite permettant à un element de jeu de se deplacer
     * @param w Monde
     */
    public abstract void deplacer(World w);
    
}
