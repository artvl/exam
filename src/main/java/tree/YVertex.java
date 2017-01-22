package tree;

import java.util.Comparator;

/**
 * Created by Artem on 12.12.2015.
 */
public class YVertex extends Vertex {
    @Override
    public Comparator<Point> getComparator() {
        return (Point x, Point y) -> {
            if (Math.abs(x.getY() - y.getY()) < 0.0001)
                return 0;
            else
                return Double.compare(x.getY(), y.getY());
        };
    }

    @Override
    public Vertex getOpposite() {
        return new XVertex();
    }

    @Override
    public void setBorder(Point point) {
        setBorder(point.getY());
    }

    @Override
    public Boolean compareToBorder(Point p)
    {
        if (p.getX() < getBorder())
            return true;
        else
            return false;

    }

    public Rectangle getLowerRect()
    {
        Rectangle temp = new Rectangle(new Point(getRect().getLeftDown().getX(), getRect().getLeftDown().getY()),
                new Point(getRect().getLeftUp().getX(), getRect().getLeftUp().getY()),
                new Point(getRect().getRightUp().getX(), getRect().getRightUp().getY()),
                new Point(getRect().getRightDown().getX(), getRect().getRightDown().getY())
        );


        temp.setLeftUp(new Point(temp.getLeftUp().getX(),getBorder()));
        temp.setRightUp(new Point(temp.getRightUp().getX(),getBorder()));

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

        temp.setLeftDown(new Point(temp.getLeftDown().getX(),getBorder()));
        temp.setRightDown(new Point(temp.getRightDown().getX(),getBorder()));

        return temp;
    }
}
