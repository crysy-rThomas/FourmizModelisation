/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourmiz.METIER;


import fourmiz.METIER.Point;
import fourmiz.METIER.CurveFunction;
import java.util.ArrayList;

/**
 *
 * @author crysy
 */
public class PolynomeInterpolateur implements CurveFunction {

    private ArrayList<Point> points;
    /**
     * Constructeur d'un PolynomeInterpolateur
     * @param points Liste des differents Points
     */
    public PolynomeInterpolateur(ArrayList<Point> points) {
        this.points = points;
    }
    
    private double lagrange(int i, double x) {
        double res = 1; //calcule de lagrange
        for(int j=0;j<points.size();j++) {
            if(j!= i) {
                res *= (x-points.get(j).getX())/(points.get(i).getX()-points.get(j).getX());
            }
        }
        return res;
    }
    /**
     * Resultat du PolynomeInterpolateur
     * @param x Inconnue (Valeur du X sur le graphique)
     * @return f(x) res avec l'inconnue
     */
    @Override
    public double compute(double x) {
        double res = 0;
        for (int i = 0 ; i<points.size();i++)
        {
            res += points.get(i).getY()*lagrange(i, x);
        }
        return res;
    }
    
}
