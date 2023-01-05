/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.objet.WoE;

/**
 *Interface des creatures qui peuvent combattre
  * @author SAMAR ELAYED / ELBETTAL Oussama
 */
public interface Combattant {
    
    /**
     *Methode abstraite permettant le combat entre 2 creature
     * @param c Creature
     */
    public abstract void combattre(Creature c);
}
