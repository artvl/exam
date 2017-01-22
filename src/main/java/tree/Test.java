package tree;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by artvl on 23.01.17.
 */
public class Test {
    public static void main(String[] args) {

//        A.B a= new A.B();
//
//        A.C adf = new A().new C();
//
//        TreeMap<String, Integer> aff = new TreeMap<>();
//
//        aff.keySet();
//
//        //PriorityQueue <String> a = new PriorityQueue<>();


        while (true) {
            ArrayList<Point> a = new ArrayList<>();

            Random k = new Random();

            for (int i = 0; i < 4; i++) {
                a.add(new Point(k.nextDouble() * 100, k.nextDouble() * 100));
            }


            Point pointToFind = new Point(k.nextDouble() * 100, k.nextDouble() * 100);

            System.out.println("find : " + pointToFind);

            Ktree temp = new Ktree(a);

            temp.build();

            double l1 = temp.findPoint(pointToFind);
            double l2 = findClosest(pointToFind, a);
            System.out.println("Tree : " + l1);
            System.out.println("all points : " + l2);

            if (Math.abs(l1 - l2) > 0.01) {
                Ktree help = new Ktree(a);
                help.build();

                System.out.println(a);
                System.out.println(temp.findPoint(pointToFind));
                System.out.println(help.findPoint(pointToFind));
                //return;
                int i = 0;
            }
        }
    }

    static double findClosest(Point p, ArrayList<Point> list) {
        if (list.size() == 0) {
            return 0.;
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

        return len;
    }

}

