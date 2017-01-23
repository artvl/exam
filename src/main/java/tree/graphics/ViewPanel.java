package tree.graphics;

import tree.*;
import tree.Point;

import javax.swing.*;
import java.awt.*;

import java.awt.Rectangle;
import java.util.List;

public class ViewPanel extends JPanel {

    ViewPanel(List<Vertex> list , List<Point> points, Point find, Point closest) {
        this.vl = list;
        this.points = points.toArray(this.points);
        this.find = find;
        this.closest = closest;
    }

    String format = "(%1.2f;%1.2f)";

    Point find;
    Point closest;



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
                drawPointWithColor(Color.BLACK, points[i], g);
            }
            for (int j = 0; j < Math.min(step,vl.size()); j ++) {
                if (j % 2 == 0) {
                    drawRectWithColor(Color.RED, vl.get(j), g);
                } else {
                    drawRectWithColor(Color.GREEN, vl.get(j), g);
                }
            }
            if (step >= vl.size() + 1) {
                drawPointWithColor(Color.YELLOW, find, g);
            }
            if (step >= vl.size() + 2) {
                drawPointWithColor(Color.BLUE, closest, g);
            }
        }
    }

    private void drawRectWithColor(Color color, Vertex vx, Graphics g) {
        Rectangle rect = getBounds();
        double lx = vx.getRect().getLeftDown().getX();
        double ly = vx.getRect().getLeftDown().getY();
        double rx = vx.getRect().getRightUp().getX();
        double ry = vx.getRect().getRightUp().getY();
        int lxLine = Math.round((rect.width - 2 * MARGIN_X) * ((float)lx - minX) / (maxX - minX));
        int lyLine = Math.round((rect.height - 2 * MARGIN_Y) * ((float)ly - minY) / (maxY - minY));
        int rxLine = Math.round((rect.width - 2 * MARGIN_X) * ((float)rx - minX) / (maxX - minX));
        int ryLine = Math.round((rect.height - 2 * MARGIN_Y) * ((float)ry - minY) / (maxY - minY));

        g.setColor(color);
        g.drawLine(lxLine, lyLine, lxLine, ryLine);
        g.drawLine(rxLine, lyLine, rxLine, ryLine);
        g.drawLine(lxLine, lyLine, rxLine, lyLine);
        g.drawLine(lxLine, ryLine, rxLine, ryLine);
    }

    private void drawPointWithColor(Color color, Point point, Graphics g) {
        Rectangle rect = getBounds();
        int x = Math.round((rect.width - 2 * MARGIN_X) * ((float)point.getX() - minX) / (maxX - minX));
        int y = Math.round((rect.height - 2 * MARGIN_Y) * ((float)point.getY() - minY) / (maxY - minY));
        g.setColor(color);
        g.fillOval(x - 3, y - 3, 7, 7);
        g.drawString(String.format(format, point.getX(), point.getY()), x, y);
    }
}
