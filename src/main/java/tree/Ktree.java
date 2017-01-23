package tree;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 11.12.2015.
 */
public class Ktree {

    ArrayList<Point> points;
    XVertex root;

    public List<Vertex> vertexList = new ArrayList<>();

    public Ktree(ArrayList<Point> points) {
        this.points = points;
        root = new XVertex();
    }

    public void build() {

        if (points.size() == 0) {
            return;
        }

        Point leftUp, leftDown, rightUp, rightDown;

        leftUp = leftDown = rightUp = rightDown = points.get(0);

        double maxX, maxY, minX, minY;

        maxX = minX = leftDown.getX();
        maxY = minY = leftDown.getY();

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getX() > maxX) {
                maxX = points.get(i).getX();
            }
            if (points.get(i).getX() < minX) {
                minX = points.get(i).getX();
            }
            if (points.get(i).getY() > maxY) {
                maxY = points.get(i).getY();
            }
            if (points.get(i).getY() < minY) {
                minY = points.get(i).getY();
            }
        }

        leftDown = new Point(minX, minY);
        leftUp = new Point(minX, maxY);
        rightUp = new Point(maxX, maxY);
        rightDown = new Point(maxX, minY);

        Rectangle rect = new Rectangle(leftDown, leftUp, rightUp, rightDown);

        buildTree(points, root, rect);

    }

    public Vertex getRoot() {
        return root;
    }

    public Vertex buildTree(List<Point> points, Vertex current, Rectangle rect) {
        current.setRect(rect);
        if (points.size() == 1) {
            current.isLeaf(true);
            current.setPoint(points.get(0));
            return current;
        }
        vertexList.add(current);

        Point middle = Utils.findOrderStatistics(points, (points.size()) / 2, current.getComparator());

        current.setBorder(middle);

        current.setLeft(buildTree(points.subList(0, (points.size()) / 2), current.getOpposite(), current.getLowerRect()));
        current.setRight(buildTree(points.subList((points.size()) / 2 , points.size()), current.getOpposite(), current.getUpperRect()));
        return current;
    }


    public double findPoint(Point point) {
        return findPoint(root, point);
    }

    private double findPoint(Vertex vertex, Point p) {
        if (vertex.isLeaf()) {

            System.out.println("Tree closest point : " + vertex.getPoint());

            return vertex.getPoint().getLength(p);
        }
        if (vertex.compareToBorder(p)) {
            double len = findPoint(vertex.getLeft(), p);
            len = Math.min(len, calc(vertex.getRight(), p, len));
            return len;
        } else {
            double len = findPoint(vertex.getRight(), p);
            len = Math.min(len, calc(vertex.getLeft(), p, len));
            return len;
        }
    }


    private double calc(Vertex ver, Point p, double length) {

        if (ver.isLeaf()) {
            return p.getLength(ver.getPoint());
        }

        if (lengthPointToRect(p, ver.getRect()) > length) {
            return length;
        }

        return Math.min(calc(ver.getLeft(), p, length), calc(ver.getRight(), p, length));

    }

    private double lengthPointToRect(Point p, Rectangle rect) {
        double lenght = p.getLength(rect.getLeftDown());

        lenght = Math.min(lenght, lengthPointToTwo(p, rect.getLeftDown(), rect.getLeftUp()));
        lenght = Math.min(lenght, lengthPointToTwo(p, rect.getLeftUp(), rect.getRightUp()));
        lenght = Math.min(lenght, lengthPointToTwo(p, rect.getRightUp(), rect.getRightDown()));
        lenght = Math.min(lenght, lengthPointToTwo(p, rect.getLeftDown(), rect.getLeftDown()));

        return lenght;
    }

    private double lengthPointToTwo(Point a, Point b, Point c) {
        double ans = Math.min(a.getLength(b), a.getLength(c));

        //if parrallel OY
        if (Math.abs(b.getX() - c.getX()) < 0.001) {
            if ((a.getY() < b.getY() && a.getY() > c.getY()) ||
                    (a.getY() > b.getY() && a.getY() < c.getY())) {
                return Math.abs(a.getX() - b.getX());

            }
        }

        if (Math.abs(b.getY() - c.getY()) < 0.001) {
            if ((a.getX() < b.getX() && a.getX() > c.getX()) ||
                    (a.getX() > b.getX() && a.getX() < c.getX())) {
                return Math.abs(a.getY() - b.getY());

            }
        }

        return ans;
    }


}
