package fourmiz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class CurveTracer extends JFrame {

    private JButton btnAffine = new JButton( "Affine" );
    private JButton btnLagrange = new JButton( "Lagrange" );
    private JButton btnSpline = new JButton( "Spline" );
    private JButton btnBezier = new JButton( "Bezier" );
    private JButton btnClear = new JButton( "Clear" );
    private CurveCanvas curveCanvas = new CurveCanvas();
    
    public CurveTracer() {
        super( "Curve tracer" );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        
        JPanel contentPane = (JPanel) this.getContentPane();
        
        JPanel pnlTop = new JPanel( new GridLayout( 1, 2, 10, 0 ) );
        pnlTop.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
        btnAffine.addActionListener( this::btnAffineListener );
        pnlTop.add( btnAffine );
        btnLagrange.addActionListener( this::btnLagrangeListener );
        pnlTop.add( btnLagrange );
        btnSpline.addActionListener( this::btnSplineListener );
        pnlTop.add( btnSpline );
        btnBezier.addActionListener( this::btnBezierListener );
        pnlTop.add( btnBezier );
        btnClear.addActionListener( this::btnClearListener );
        pnlTop.add( btnClear );
        contentPane.add( pnlTop, BorderLayout.NORTH );
        contentPane.add( curveCanvas, BorderLayout.CENTER );
        
        
        this.setSize( 700, 770 );
        this.setLocationRelativeTo( null );
    }
    
    private void btnAffineListener( ActionEvent event ) {
       AffinePanel a = new AffinePanel(curveCanvas);
       a.setVisible(true); 
    
    }
        private void btnSplineListener( ActionEvent event ) {
       PanelSpline a = new PanelSpline(curveCanvas);
       a.setVisible(true); 
    
    }
        private void btnLagrangeListener( ActionEvent event ) {
       PanelLagrang a = new PanelLagrang(curveCanvas);
       a.setVisible(true); 
    
    }
    private void btnBezierListener( ActionEvent event ) {
       PanelBezier a = new PanelBezier(curveCanvas);
       a.setVisible(true); 
    
    }

    private void btnClearListener( ActionEvent event ) {
       curveCanvas.Clear();
    }



    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        CurveTracer window = new CurveTracer();
        window.setVisible( true );
    }

}
