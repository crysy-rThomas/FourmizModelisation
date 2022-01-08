/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourmiz.METIER;

/**
 *
 * @author crysy
 */
public interface CurveFunction {
    /**
     * Calcule des differentes fonctions f(x)
     * @param x valeur de l'inconnue
     * @return resultat de f(x)
     */
    public double compute( double x ); 
}
