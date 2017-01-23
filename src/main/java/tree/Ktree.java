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


    public Point findPoint(Point point) {
        return findPoint(root, point);
    }

    private Point findPoint(Vertex vertex, Point from) {
        if (vertex.isLeaf()) {
//            System.out.println("Tree closest point : " + vertex.getPoint());
            return vertex.getPoint();
        }
        if (vertex.compareToBorder(from)) {
            Point candidate = findPoint(vertex.getLeft(), from);
            candidate = from.getClosestPoint(candidate, calc(vertex.getRight(), from, candidate));
            return candidate;
        } else {
            Point candidate = findPoint(vertex.getRight(), from);
            candidate = from.getClosestPoint(candidate, calc(vertex.getLeft(), from, candidate));
            return candidate;
        }
    }


    private Point calc(Vertex ver, Point from, Point candidate) {

        if (ver.isLeaf()) {
            if (ver.getPoint().getLength(from)  > candidate.getLength(from)) {
                return candidate;
            } else {
                return ver.getPoint();
            }
        }

        if (lengthPointToRect(from, ver.getRect()) > from.getLength(candidate)) {
            return candidate;
        }

        return from.getClosestPoint(calc(ver.getLeft(), from, candidate), calc(ver.getRight(), from, candidate));
//        return Math.min(calc(ver.getLeft(), from, candidate), calc(ver.getRight(), from, candidate));
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
