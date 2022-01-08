
package fourmiz.METIER;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

/**
 * Performs spline interpolation given a set of control points.
 * 
 */
public class SplineInterpolateur {
     private double[] x;
     private double[] y;
     
     private SplineInterpolator spline = new SplineInterpolator();
     private PolynomialSplineFunction PSF ;
     /**
      * 
      * @return Tableau de X de tous les points
      */
     public double[] getX()
     {
         return x;
     }
      /**
      * 
      * @return Tableau de Y de tous les points
      */
     public double[] getY()
     {
         return y;
     }
     
     /**
      * Constructeur du SplineInterpolateur
      * @param x tableau des coordonnees des differents X
      * @param y tableau des coordonnees des differents Y
      */
     public SplineInterpolateur(double[] x , double[] y)
     {
         this.x = x;
         this.y = y;
        
     }
     /**
      * Calcule grace a la fonction Interpolate de CommonsMaths
      */
     public void interpolateSpline()
     {
        PSF = spline.interpolate(x, y);
        
     }
     /**
      * Renvoie le resultat pour une inconnue donnee
      * @param x inconnue (sur le graphique)
      * @return f(x)
      */
     public double compute(double x)
     {
         
         return PSF.value(x);
     }
     
	
}