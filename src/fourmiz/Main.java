/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fourmiz;

import java.awt.*;
import java.util.*;
import java.awt.geom.*;

public class Main {
    public static GeneralPath drawBezierCurve(AffineTransform affine,
            Vector<Point2D.Double> gPathPoints, Graphics g) {
        if (affine == null) {
            affine = new AffineTransform();
            affine.setToIdentity();/*from  w  w  w.  j  ava 2  s  . co  m*/
        }
        boolean drawPath = true;
        if (g == null)
            drawPath = false;
        int pixelSize = 1;
        Point2D.Double pt;
        if (gPathPoints.size() == 0)
            return null;
        // if (gPathPoints.size() % 2 == 0) return  null;
        // System.out.println("Path Points Size: "+gPathPoints.size() + 
        // " Mod2: "+gPathPoints.size() % 2 );
        Point2D.Double[] points = gPathPoints
                .toArray(new Point2D.Double[0]);
        Graphics2D g2 = null;
        AffineTransform origAffine = null;
        if (drawPath) {
            g2 = (Graphics2D) g;
            origAffine = g2.getTransform();
        }
        GeneralPath gPath = new GeneralPath();
        gPath.moveTo(points[0].x, points[0].y);
        if (points.length == 1) {
            if (drawPath)
                drawPoint(affine, g2, points[0], pixelSize, Color.BLACK);
            return null;
        }
        for (int i = 1; i < points.length; i += 3) {
            if (i + 1 == points.length)
                gPath.lineTo(points[i].x, points[i].y);
            else if (i + 2 == points.length)
                gPath.quadTo(points[i].x, points[i].y, points[i + 1].x,
                        points[i + 1].y);
            else
                gPath.curveTo(points[i].x, points[i].y, points[i + 1].x,
                        points[i + 1].y, points[i + 2].x, points[i + 2].y);
        }
        Color ptColor;
        if (drawPath) {
            drawPoint(affine, g2, points[0], pixelSize, Color.BLUE);
            for (int i = 1; i < points.length; i += 3) {
                if (i + 1 == points.length) {
                    drawPoint(affine, g2, points[i], pixelSize, Color.CYAN);
                } else if (i + 2 == points.length) {
                    drawPoint(affine, g2, points[i], pixelSize, Color.CYAN);
                    drawPoint(affine, g2, points[i + 1], pixelSize,
                            Color.CYAN);
                } else {
                    for (int cntr = 0; cntr < 3; cntr++) {
                        int ptCntr = i + cntr;
                        if (cntr == 2)
                            g2.setColor(Color.blue);
                        else
                            g2.setColor(Color.cyan);
                        Point2D.Double affinePt = (Point2D.Double) affine
                                .transform(points[ptCntr], null);
                        g2.fill(new Ellipse2D.Double(affinePt.x - 4
                                * pixelSize, affinePt.y - 4 * pixelSize,
                                8 * pixelSize, 8 * pixelSize));
                        // System.out.println("Drawing Point at PtCntr: "+ptCntr);
                    }
                }
            } // for (int i = 1; i < points.length;  i += 3)
        }
        gPath = (GeneralPath) gPath.createTransformedShape(affine);
        if (drawPath) {
            g2.setColor(Color.DARK_GRAY);
            Stroke origStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(0.5f));
            g2.draw(gPath);
            g2.setStroke(origStroke);
            g2.transform(origAffine);
        }
        return gPath;
    }

    public static void drawPoint(Graphics2D g2, Point2D.Double pt,
            int ptSize, Color color) {
        AffineTransform affine = new AffineTransform();
        affine.setToIdentity();
        drawPoint(affine, g2, pt, ptSize, color);
    }

    public static void drawPoint(AffineTransform affine, Graphics2D g2,
            Point2D.Double point, int ptSize, Color color) {
        Point2D.Double pt = (Point2D.Double) affine.transform(point, null);
        Color origColor = g2.getColor();
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(pt.x - 4 * ptSize, pt.y - 4 * ptSize,
                8 * ptSize, 8 * ptSize));
        g2.setColor(origColor);
    }
}