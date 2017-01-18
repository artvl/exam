package bigInt;

import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import java.math.BigInteger;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Created by artvl on 17.01.17.
 */
public class TestBIgInteger {
    static int cnt = 20000;

    public static void main(String[] args) {

        warmUp();

        long[] arr = new long[cnt];
        arr[0] = 0;

        Random random = new Random();
        for (int  k = 0; k < 3 ; k ++) {
            for (int i = 1; i < cnt; i += 1) {

                IntStream intStream1 = random.ints(0, 10);
                IntStream intStream2 = random.ints(0, 10);
                String a = intStream1.limit(i).map(num -> (char) num).collect(StringBuilder::new,
                        (sb, num) -> sb.append(num),
                        StringBuilder::append
                ).toString();
                String b = intStream2.limit(i).map(num -> (char) num).collect(StringBuilder::new,
                        (sb, num) -> sb.append(num),
                        StringBuilder::append
                ).toString();
//            System.out.println(a);
//            System.out.println(b);

                BigInteger aa = new BigInteger(a);
                BigInteger bb = new BigInteger(b);

                long start = System.nanoTime();
                BigInteger c = aa.multiply(bb);
                long end = System.nanoTime();
                arr[i] += (end - start);

                System.out.println(k + " " + i);
            }
        }
        for (int i = 1; i < cnt; i += 1) {
            arr[i] = Math.round(((double)arr[i])/3.);
        }

        final XYSeries series = new XYSeries("Random Data");
        for (int i = 0 ; i < cnt ; i ++) {
            series.add((double)i, calcAvg(arr, i));
        }
        final XYSeriesDemo demo = new XYSeriesDemo("XY Series Demo",series);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    static void warmUp() {
        Random random = new Random();
        for (int i = 1; i < 2000; i ++) {

            IntStream intStream1 = random.ints(0, 10);
            IntStream intStream2 = random.ints(0, 10);
            String a = intStream1.limit(i).map(num ->  (char) num ).collect(StringBuilder::new,
                    (sb, num) -> sb.append(num),
                    StringBuilder::append
            ).toString();
            String b = intStream2.limit(i).map(num ->  (char) num ).collect(StringBuilder::new,
                    (sb, num) -> sb.append(num),
                    StringBuilder::append
            ).toString();

            BigInteger aa = new BigInteger(a);
            BigInteger bb = new BigInteger(b);


            BigInteger c = aa.multiply(bb);


        }

    }

    static double calcAvg (long[] arr, int pos) {
        int size = arr.length;
        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;
        long sum = 0;
        long cnt = 0;
        for (int i = Math.max(0, pos - 15); i < Math.min(size, pos + 15); i ++) {
            cnt ++;
            sum += arr[i];
            min = arr[i] < min ? arr[i] : min;
            max = arr[i] > max ? arr[i] : max;
        }
        sum -= (max + min);
        return  (double) sum / (double) (cnt - 2);
    }
}
