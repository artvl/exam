package tree;

/**
 * Created by Artem on 12.12.2015.
 */
public class Rectangle {

    Point leftDown;
    Point leftUp;
    Point rightUp;
    Point rightDown;

    @Override
    public String toString() {
        String string = "{ " + leftDown + " , "
                + leftUp + " , "
                + rightUp + " , "
                + rightDown + " }";
        return string;
    }

    Rectangle() {
        leftDown = new Point();
        leftUp = new Point();
        rightUp = new Point();
        rightDown = new Point();
    }

    public Point getRightDown() {
        return rightDown;
    }

    public void setRightDown(Point rightDown) {
        this.rightDown = rightDown;
    }

    public Point getRightUp() {
        return rightUp;
    }

    public void setRightUp(Point rightUp) {
        this.rightUp = rightUp;
    }

    public Point getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(Point leftUp) {
        this.leftUp = leftUp;
    }

    public Point getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(Point leftDown) {
        this.leftDown = leftDown;
    }

    Rectangle(Point ld, Point lu, Point ru, Point rd) {
        leftDown = ld;
        leftUp = lu;
        rightUp = ru;
        rightDown = rd;
    }


    public boolean isInside(Point p) {
        if (p.getX() > leftDown.getX() && p.getX() < rightUp.getX()
                && p.getY() > leftDown.getY() && p.getY() < rightUp.getY()) {
            return true;
        }
        return false;
    }

}
