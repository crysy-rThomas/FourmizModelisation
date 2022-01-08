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
public class Point {
    
    private double x;
    private double y;
    /**
     * 
     * @return la valeur de X
     */
    public double getX()
    {
        return x;
    }
     /**
     * 
     * @return la valeur de Y
     */
    public double getY()
    {
        return y;
    }
    /**
     * Construteur d'un point
     * @param x
     * @param y 
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * Modifier le x et y d'un point
     * @param x
     * @param y 
     */
    public void setPoint(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    
    
    
}
