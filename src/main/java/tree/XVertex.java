package tree;

import java.util.Comparator;

/**
 * Created by Artem on 12.12.2015.
 */
public class XVertex extends Vertex {
    @Override
    public Comparator<Point> getComparator() {
        return (Point x, Point y) -> {
            if (Math.abs(x.getX() - y.getX()) < 0.0000001)
                return 0;
            else
                return Double.compare(x.getX(), y.getX());
        };
    }

    @Override
    public Vertex getOpposite() {
        return new YVertex();
    }

    @Override
    public void setBorder(Point point) {
        setBorder(point.getX());
    }

    @Override
    public Boolean compareToBorder(Point p)
    {
        if (p.getX() < getBorder())
            return true;
        else
            return false;

    }

    @Override
    public Rectangle getLowerRect()
    {
        Rectangle temp = new Rectangle(
                new Point(getRect().getLeftDown().getX(), getRect().getLeftDown().getY()),
                new Point(getRect().getLeftUp().getX(), getRect().getLeftUp().getY()),
                new Point(getRect().getRightUp().getX(), getRect().getRightUp().getY()),
                new Point(getRect().getRightDown().getX(), getRect().getRightDown().getY())
        );

        temp.setRightDown(new Point(getBorder(), temp.getRightDown().getY()));
        temp.setRightUp(new Point(getBorder(), temp.getRightUp().getY()));

        return temp;

    }

    @Override
    public Rectangle getUpperRect()
    {
        Rectangle temp = new Rectangle(new Point(getRect().getLeftDown().getX(), getRect().getLeftDown().getY()),
                new Point(getRect().getLeftUp().getX(), getRect().getLeftUp().getY()),
                new Point(getRect().getRightUp().getX(), getRect().getRightUp().getY()),
                new Point(getRect().getRightDown().getX(), getRect().getRightDown().getY())
        );

        temp.setLeftDown(new Point(getBorder(), temp.getLeftDown().getY()));
        temp.setLeftUp(new Point(getBorder(), temp.getLeftUp().getY()));

        return temp;
    }
}


