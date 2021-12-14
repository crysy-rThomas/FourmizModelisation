
package fourmiz;
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
     
     public SplineInterpolateur(double[] x , double[] y)
     {
         this.x = x;
         this.y = y;
        
     }
     
     public void interpolateSpline()
     {
        PSF = spline.interpolate(x, y);
        
     }
     
     public double compute(double x)
     {
         
         return PSF.value(x);
     }
     
	
}