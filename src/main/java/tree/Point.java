package tree;

public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString ()
    {
        String string = "( " + x + " , " + y + " )";
        return string;
    }

    Point() {
        x = 0;
        y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getLength(Point b) {
        double length = 0.;
        length += Math.sqrt(Math.pow((x - b.x), 2) + Math.pow((y - b.y), 2));
        return length;
    }

    public double getSquaredLength(Point b) {
        double length = 0.;
        length += (Math.pow((x - b.x), 2) + Math.pow((y - b.y), 2));
        return length;
    }
}