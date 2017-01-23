package tree.graphics;

import tree.*;
import tree.Point;

import javax.swing.*;
import java.awt.*;

import java.awt.Rectangle;
import java.util.Random;
import java.util.List;

public class ViewPanel extends JPanel {

    ViewPanel(List<Vertex> list , List<Point> points) {
        this.vl = list;
        this.points = points.toArray(this.points);
    }

    String format = "(%1.2f;%1.2f)";





    private Point[] points = {};

    private float minX = 0, maxX = 1, minY = 0, maxY = 1;
    private int MARGIN_X = 8, MARGIN_Y = 8;

    public void initiate(int N) {
        repaint();
    }

    List<Vertex> vl;

    private int step;
    public void next() {
        step++;
        repaint();
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        Rectangle rect = getBounds();
        g.fillRect(0, 0, rect.width, rect.height);

        if (points != null && vl != null) {
            for (int i = 0; i < points.length; i++) {
                int x = Math.round((rect.width - 2 * MARGIN_X) * ((float)points[i].getX() - minX) / (maxX - minX));
                int y = Math.round((rect.height - 2 * MARGIN_Y) * ((float)points[i].getY() - minY) / (maxY - minY));
                g.setColor(Color.BLACK);
                g.fillOval(x - 3, y - 3, 7, 7);
                g.drawString(String.format(format, points[i].getX(), points[i].getY()), x, y);
            }
            for (int j = 0; j < Math.min(step,vl.size()); j ++) {
                if (j % 2 == 0) {
                    double lx = vl.get(j).getRect().getLeftDown().getX();
                    double ly = vl.get(j).getRect().getLeftDown().getY();
                    double rx = vl.get(j).getRect().getRightUp().getX();
                    double ry = vl.get(j).getRect().getRightUp().getY();
                    int lxLine = Math.round((rect.width - 2 * MARGIN_X) * ((float)lx - minX) / (maxX - minX));
                    int lyLine = Math.round((rect.height - 2 * MARGIN_Y) * ((float)ly - minY) / (maxY - minY));
                    int rxLine = Math.round((rect.width - 2 * MARGIN_X) * ((float)rx - minX) / (maxX - minX));
                    int ryLine = Math.round((rect.height - 2 * MARGIN_Y) * ((float)ry - minY) / (maxY - minY));

                    g.setColor(Color.RED);
                    g.drawLine(lxLine, lyLine, lxLine, ryLine);
                    g.drawLine(rxLine, lyLine, rxLine, ryLine);
                    g.drawLine(lxLine, lyLine, rxLine, lyLine);
                    g.drawLine(lxLine, ryLine, rxLine, ryLine);
                } else {
                    double lx = vl.get(j).getRect().getLeftDown().getX();
                    double ly = vl.get(j).getRect().getLeftDown().getY();
                    double rx = vl.get(j).getRect().getRightUp().getX();
                    double ry = vl.get(j).getRect().getRightUp().getY();
                    int lxLine = Math.round((rect.width - 2 * MARGIN_X) * ((float)lx - minX) / (maxX - minX));
                    int lyLine = Math.round((rect.height - 2 * MARGIN_Y) * ((float)ly - minY) / (maxY - minY));
                    int rxLine = Math.round((rect.width - 2 * MARGIN_X) * ((float)rx - minX) / (maxX - minX));
                    int ryLine = Math.round((rect.height - 2 * MARGIN_Y) * ((float)ry - minY) / (maxY - minY));

                    g.setColor(Color.GREEN);
                    g.drawLine(lxLine, lyLine, lxLine, ryLine);
                    g.drawLine(rxLine, lyLine, rxLine, ryLine);
                    g.drawLine(lxLine, lyLine, rxLine, lyLine);
                    g.drawLine(lxLine, ryLine, rxLine, ryLine);
                }
            }
        }
    }
}
