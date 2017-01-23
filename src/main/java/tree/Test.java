package tree;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by artvl on 23.01.17.
 */
public class Test {
    public static void main(String[] args) {

        int r  = 0, w = 0;
        while (true) {
            r++;
            ArrayList<Point> a = new ArrayList<>();

            Random k = new Random();

            for (int i = 0; i < 4; i++) {
                a.add(new Point(k.nextDouble() * 100, k.nextDouble() * 100));
            }


            Point pointToFind = new Point(k.nextDouble() * 100, k.nextDouble() * 100);

            System.out.println(a);
            System.out.println("find : " + pointToFind);


            Ktree temp = new Ktree(a);
            temp.build();

            Point l1 = temp.findPoint(pointToFind);
            Point l2 = findClosest(pointToFind, a);
            System.out.println("Tree : " + l1);
            System.out.println("all points : " + l2);

            if (l1.getLength(l2) > 0.01) {
                Ktree help = new Ktree(a);
                help.build();

                System.out.println(temp.findPoint(pointToFind));
                System.out.println(help.findPoint(pointToFind));
                //return;
                w++;
                System.out.println("\n\n " + r + " " + w + "\n\n");
            }
        }
    }

    static Point findClosest(Point p, ArrayList<Point> list) {
        if (list.size() == 0) {
            return null;
        }
        int j = 0;
        double len = p.getLength(list.get(0));
        for (int i = 0; i < list.size(); i++) {
            if (p.getLength(list.get(i)) < len) {
                len = p.getLength(list.get(i));
                j = i;
            }

        }
        System.out.println("Real closest point : " + list.get(j));

        return list.get(j);
    }

}

