package qSort;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by artvl on 19.01.17.
 */
public class Compare {
    static int size = 100;
    static int tries = 1000;
    public static void main(String[] args) {

        for (int k = 0; k < tries; k++) {
            Random random = new Random();


            Integer a[] = {};
            Integer b[] = {};
            ArrayList<Integer> arr = random.ints(0, 1000).limit(size)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            a = arr.toArray(a);
            b = arr.toArray(b);

            final int cnt[] = {0, 0};
            Arrays.sort(a, (Integer o1, Integer o2) -> {
                        cnt[0]++;
                        return Integer.compare(o1, o2);
                    }
            );


            QSort.sort(b, (Object o1, Object o2) -> {
                cnt[1]++;
                return Integer.compare((Integer) o1, (Integer) o2);
            }, true, false);
            System.out.println(cnt[0] + " " + cnt[1]);

            for (int i = 0; i < size; i++) {
                if (!a[i].equals(b[i])) {
                    System.out.println(k + " " + Arrays.asList(a));
                    System.out.println(i + " " + Arrays.asList(b));
                    throw new RuntimeException("SortFailed");
                }
            }

        }
    }

}
