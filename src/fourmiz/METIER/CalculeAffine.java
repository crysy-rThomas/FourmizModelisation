/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourmiz.METIER;

import java.util.ArrayList;

/**
 *
 * @author crysy
 */
public class CalculeAffine implements CurveFunction {

    private ArrayList<Point> points = new ArrayList<>();

    private double a;
    private double b;

    /**
     * 
     * @return la liste de points
     */
    public ArrayList<Point> getPoint() {
        return points;
    }
    /**
     * 
     * @return le coef A
     */
    public double GetA() {
        return a;
    }
    /**
     * 
     * @return le coef B
     */
    public double Getb() {
        return b;
    }
    /**
     * Construteur d'une affine
     * @param x1 du premier point
     * @param y1 du premier point
     * @param x2 du deuxieme point
     * @param y2 du deuxieme point
     */
    public CalculeAffine(double x1, double y1, double x2, double y2) {
        Point e1 = new Point(x1, y1);
        Point e2 = new Point(x2, y2);

        points.add(e1);
        points.add(e2);
        calcule(); 
    }

    private void calcule() {
        //test si coef a est = 0
        if (points.get(0).getX() == points.get(1).getX()) {
            a = 0;
            
        } else {
            //calcule de coef A
            a = (points.get(1).getY() - points.get(0).getY()) / (points.get(1).getX() - points.get(0).getX());

        }
            //calcule de coef B
            b = points.get(0).getY() - a * points.get(0).getX();

    }
    
    /**
     * calcule Y(x) = ax+b
     * @param x sur le graphique
     * @return Y(x)
     */
    public double compute(double x) {
        
        return a * x + b;

    }

}
