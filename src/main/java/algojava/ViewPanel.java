package algojava;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class ViewPanel extends JPanel {

    class Node {
        private float x;
        private float y;

        public Node(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }

    private Node[] points;
    private float minX = 0, maxX = 1, minY = 0, maxY = 1;
    private int MARGIN_X = 8, MARGIN_Y = 8;

    public void SetRandom(int N) {
        Random random = new Random();
        points = new Node[N];
        for (int i = 0; i < N; i++)
            points[i] = new Node(random.nextFloat(), random.nextFloat());

        repaint();
    }

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

        if (points != null) {
            for (int i = 0; i < points.length; i++) {
                int x = Math.round((rect.width - 2 * MARGIN_X) * (points[i].x - minX) / (maxX - minX));
                int y = Math.round((rect.height - 2 * MARGIN_Y) * (points[i].y - minY) / (maxY - minY));

                if (i < step) {
                    if (i % 2 == 0) {
                        g.setColor(Color.RED);
                        g.drawLine(x, 0, x, rect.height);
                    } else {
                        g.setColor(Color.BLUE);
                        g.drawLine(0, y, rect.width, y);
                    }
                }

                g.setColor(Color.BLACK);
                g.fillOval(x - 3, y - 3, 7, 7);
            }
        }
    }
}
