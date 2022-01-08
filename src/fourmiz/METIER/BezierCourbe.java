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
public class BezierCourbe {
    private ArrayList<Point> pts = new ArrayList<>();
    private ArrayList<CalculeAffine> ca = new ArrayList<>();
    
/**
 * 
 * @return La liste des points
 */
    public ArrayList<Point> getPts()
    {
        return pts;
    }

    /**
     * 
     * @param pts 
     */
    public BezierCourbe(ArrayList<Point> pts)
    {
        this.pts = pts;
        defAffine();
    }
    
    /**
     * definition de tt les affines contenues dans la courbe
     **/ 
    private void defAffine()
    {
        for(int i = 0; i<pts.size()-1;i++) {            
            CalculeAffine af = new CalculeAffine(pts.get(i).getX(),pts.get(i).getY(),pts.get(i+1).getX(), pts.get(i+1).getY());
            ca.add(af);
        }
    }
    
    /**
     * Calcule les coordonnee de la courbe
     * @param x la valeur de x sur le graphique
     * @return Le point de debut de la courbe
     */
    public Point compute(double x)
    {
        ArrayList<Point> ptsBezier = new ArrayList<>();
        
        Point pt = new Point(6, 9);
        if (pts.size() > 1) {
            for (int i = 0; i < ca.size(); i++) {
               Point p = new Point(ca.get(i).getPoint().get(0).getX()+x,ca.get(i).compute(ca.get(i).getPoint().get(0).getX()+x));
               ptsBezier.add(p);
            }
            BezierCourbe BzCourbe = new BezierCourbe(ptsBezier);
            BzCourbe.compute(x);
        } 
        
        else{
          pt = pts.get(0);
        }
       
      return pt;
    }
    
    
    
    
    
}
