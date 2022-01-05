/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourmiz;

import java.util.ArrayList;

/**
 *
 * @author crysy
 */
public class CalculeAffine implements CurveFunction {

    private ArrayList<Point> points = new ArrayList<>();

    private double a;
    private double b;

    public ArrayList<Point> getPoint() {
        return points;
    }
    public double GetA() {
        return a;
    }

    public double Getb() {
        return b;
    }

    public CalculeAffine(double x1, double y1, double x2, double y2) {
        Point e1 = new Point(x1, y1);
        Point e2 = new Point(x2, y2);

        points.add(e1);
        points.add(e2);
        calcule(); 
    }

    private void calcule() {
        if (points.get(0).getX() == points.get(1).getX()) {
            a = 0;

        } else {

            a = (points.get(1).getY() - points.get(0).getY()) / (points.get(1).getX() - points.get(0).getX());

        }

            b = points.get(0).getY() - a * points.get(0).getX();

    }

    public double compute(double x) {
        
        return a * x + b;

    }

}
