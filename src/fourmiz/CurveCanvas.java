package fourmiz;

import fourmiz.METIER.SplineInterpolateur;
import fourmiz.METIER.PolynomeInterpolateur;
import fourmiz.METIER.CalculeAffine;
import fourmiz.METIER.BezierCourbe;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JComponent;

public class CurveCanvas extends JComponent {

    private ArrayList <CalculeAffine> function ;
    private PolynomeInterpolateur pi;
    private SplineInterpolateur spline;
    private BezierCourbe Bz;
    private Graphics2D graphics;
    private final double echelle = 5;
    
        public double getEchelle()
    {
        return echelle;
    }

    public CurveCanvas()
    {
        function = new ArrayList<>();
    }   

    /**
     *
     * @param graphicsSimple
     */
    @Override
    protected void paintComponent(Graphics graphicsSimple) {
        super.paintComponent(graphicsSimple);
        
        // --- Activate antialiasing flag ---
        graphics = (Graphics2D) graphicsSimple;
        graphics.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                                   RenderingHints.VALUE_ANTIALIAS_ON );
        
        // --- White background ---
        graphics.setColor( Color.WHITE );
        graphics.fillRect( 0, 0, getWidth(), getHeight() );
        
        // --- Draw axis ---
        graphics.setColor( Color.GRAY );
        graphics.drawLine( 0, getHeight()/2, getWidth(), getHeight()/2 );
        graphics.drawLine( getWidth()/2, 0, getWidth()/2, getHeight() );
        
        // --- Draw values ---
        graphics.setColor( Color.BLACK );
        graphics.drawString( "0,0", (int)(getWidth()*0.50), (int)(getHeight()*0.54));
        graphics.drawString( String.valueOf(-echelle), (int)(getWidth()*0.02), (int)(getHeight()*0.54));
        graphics.drawString( String.valueOf(echelle), (int)(getWidth()*0.96), (int)(getHeight()*0.54));
        
        
       
        
        if (pi != null) {
            //dessine Lagrange
            drawCurveLagrange();
        } else if (spline != null) {
            drawCurveSpline();
        } else if (Bz != null) {
            //dessine bezier
            drawCurveBezier();
        } else {
            //Dessine les affines
            drawCurveAffine();
        }

        //points du sujet
       //-1 / -1.5 -- -0.5 / 0 -- 0 / 0.25 -- 0.5 / 0 -- 1 / 0
        
        
        
       }
         // --- Draw curve Affine ---
        public void drawCurveAffine(){
         for (int i=0;i<function.size();i++) 
         {
        double step = 0.01;
        graphics.setColor( new Color( 255, 0, 255 ) );
        //oskour
        int oldX = xToPixel( function.get(i).getPoint().get(0).getX() );
        int oldY = yToPixel( function.get(i).getPoint().get(0).getY() );
        
        for( double lx=function.get(i).getPoint().get(0).getX(); lx<= function.get(i).getPoint().get(1).getX(); lx+=step ) {
            int x = xToPixel( lx );
            int y = yToPixel( function.get(i).compute(lx));
            graphics.drawLine( x, y, oldX, oldY );
            
            oldX = x;
            oldY = y;
        }
       }
      }
        
         // --- Draw curve Lagrange ---
        public void drawCurveLagrange() {       
            double step = 0.1;
            graphics.setColor(new Color(255, 0, 255));
            //oskour
            int oldX = xToPixel(-echelle);
            int oldY = yToPixel(echelle);

            for (double lx = -echelle; lx <= echelle; lx += step) {
                int x = xToPixel(lx);
                int y = yToPixel(pi.compute(lx));
                graphics.drawLine(x, y, oldX, oldY);

                oldX = x;
                oldY = y;
            }  
    }
        
        // --- Draw curve Spline ---
        public void drawCurveSpline() {  
             for(int i = 1; i<spline.getX().length;i++)
            {
            double step = 0.01;
            graphics.setColor(new Color(255, 0, 255));
            //oskour
            int oldX = xToPixel(spline.getX()[i]);
            int oldY = yToPixel(spline.getY()[i]);          
            //probleme de tracage echelle > intervalle du premier X-Y je pense qu'il faut tracer entre les intervalles
            for (double lx = spline.getX()[i-1]; lx <= spline.getX()[i]; lx += step) {
                int x = xToPixel(lx);
                int y = yToPixel(spline.compute(lx));
                graphics.drawLine(x, y, oldX, oldY);

                oldX = x;
                oldY = y;
            }
        }    
    }
        
             // --- Draw curve Bezier ---
        public void drawCurveBezier() {        
         
         double step = 0.1;
            graphics.setColor(new Color(255, 0, 255));
            //oskour
            int oldX = xToPixel(Bz.getPts().get(0).getX());
            int oldY = yToPixel(Bz.getPts().get(0).getY());
            
            double l = Bz.getPts().get(1).getX() - Bz.getPts().get(0).getX();
            //probleme avec le compute surment une erreur de comprehention
            for (double lx = 0; lx <= l ; lx += step) {
                int x = xToPixel(lx);
                int y = yToPixel(Bz.compute(lx).getY());
                graphics.drawLine(x, y, oldX, oldY);

                oldX = x;
                oldY = y;
            }  
            
            
            
    }
        
    private int xToPixel( double x ) {
        return (int)( getWidth() * (x + echelle)/(2*echelle) );
    }

    private int yToPixel( double y ) {
        return (int)( getHeight() * (1 - (y+(echelle/2.0))/echelle) );
    }
/**
 * Efface tout les dessins
 */
    public void Clear()
    {
        function.clear();
        this.pi = null;
        this.spline = null;
        this.Bz = null;
        repaint();
    }
    /**
     * Definie une Fonction Affine
     * @param ca la fonction calcule d'une afine
     */
    public void setFunction(CalculeAffine ca)
    {
        this.function.add(ca);
        repaint();
    }
    /**
     * Definie une Fonction Lagrange
     * @param pi le polynome calcule
     */
    public void setFunction(PolynomeInterpolateur pi)
    {
        this.pi = pi;
        repaint();
    }
    /**
     * Definie une Fonction Spline
     * @param spline calcule
     */
    public void setFunction(SplineInterpolateur spline)
    {
        this.spline = spline;
        repaint();
    }
    /**
     * Definie une Fonction Bezier
     * @param bz Bezier calcule
     */
    public void setFunction(BezierCourbe bz)
    {
        this.Bz = bz;
        repaint();
    }
    
}
