package tree;

import java.util.Comparator;

/**
 * Created by Artem on 12.12.2015.
 */
public class Vertex {
    private Vertex left;
    private Vertex right;
    private Boolean isLeaf;
    private Point point;
    private double border;

    private Rectangle rect;

    public Rectangle getLowerRect()
    {
        return new Rectangle();
    }

    public Rectangle getUpperRect()
    {
        return new Rectangle();
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Boolean compareToBorder(Point p)
    {
        return null;
    }

    public void setBorder(Point point) {

    }

    public double getBorder() {
        return border;
    }

    public void setBorder(double border) {
        this.border = border;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    Vertex() {
        left = null;
        right = null;
        isLeaf = false;
        point = null;
    }

    public Comparator<Point> getComparator() {
        return null;
    }

    public Vertex getOpposite() {
        return null;
    }

    public Vertex getLeft() {
        return left;
    }

    public void setLeft(Vertex left) {
        this.left = left;
    }

    public Vertex getRight() {
        return right;
    }

    public void setRight(Vertex right) {
        this.right = right;
    }

    public Boolean isLeaf() {
        return isLeaf;
    }

    public void isLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

}


