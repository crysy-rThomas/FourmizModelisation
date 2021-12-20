package fourmiz;





import java.awt.*;
import java.applet.*;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
public class Main extends Applet
{
            Point[] controlPoints,curvePoints;
            public void init(Point[] controlePoints)
            {
                        this.controlPoints = controlePoints;
                        curvePoints=new Point[25];
                        for(int i=0;i<curvePoints.length;i++)
                                    curvePoints[i]=new Point(0,0);
            }
            public void SubDivide(Point p1,Point p2,double t)
            {
                double x1 = p1.getX();
                double y1 = p1.getY();
                        if(p1.getX()>p2.getX())
                        {
                                   x1 -=Math.abs(p1.getX()-p2.getX())*t;
                                   p1.setPoint(x1, y1);
                        }
                        else
                        {
                                    x1 +=Math.abs(p1.getX()-p2.getX())*t;
                                    p1.setPoint(x1, y1);
                        }
                        if (p1.getY()>p2.getY())
                        {
                                    y1 -=Math.abs(p1.getY()-p2.getY())*t;
                                    p1.setPoint(x1, y1);
                        }
                        else{
                            y1 +=Math.abs(p1.getY()-p2.getY())*t;
                             p1.setPoint(x1, y1);
                        }
                                    
            }
            public void Compute()
            {
                        Point[] tmp=new Point[controlPoints.length];
                        for (int i=0; i<tmp.length; i++)
                                    tmp[i] = new Point(0,0);
                        for (int i=0; i<curvePoints.length; i++)
                        {
                                    double t = ((double) i)/(curvePoints.length-1);
                                    for (int j=0; j<controlPoints.length; j++)
                                                tmp[j]=new Point(controlPoints[j].getX(), controlPoints[j].getY());
                                    int Depth = tmp.length;
                                    while (Depth>1)
                                    {
                                                for (int j=0; j<Depth-1; j++)
                                                            SubDivide(tmp[j], tmp[j+1], t);
                                                Depth--;
                                    }
                                    curvePoints[i]=new Point(tmp[0].getX(), tmp[0].getY());
                        }                      
            }
            public void Draw(Graphics2D g2d)
            {
                        g2d.setColor(Color.BLACK);
                        for (int i=0; i<controlPoints.length-1; i++)
                                    g2d.drawLine((int) controlPoints[i].getX(),(int) controlPoints[i].getY(),
                                                            (int) controlPoints[i+1].getX(),(int) controlPoints[i+1].getY());
                        GeneralPath path = new GeneralPath();                           // Bezier curve
                        g2d.setColor(Color.RED);
                        path.moveTo(curvePoints[0].getX(), curvePoints[0].getY());
                        for (int i=1; i<curvePoints.length; i++)
                                    path.lineTo(curvePoints[i].getX(), curvePoints[i].getY());
                        g2d.draw(path);
            }
            public void paint(Graphics g)
            {
                        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = image.createGraphics();
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2d.setColor(Color.WHITE);
                        g2d.fillRect(0, 0, getWidth(), getHeight());
                        Compute();
                        Draw(g2d);
                        g.drawImage(image, 0, 0, this);
            }
}