package tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Artem on 11.12.2015.
 */
public class Utils {

    public static <T> T findOrderStatistics(List<T> list, int order, Comparator<T> comparator) {
        list.sort(comparator);
        return list.get(order);
    }

//    public static <T> T findOrderStatistics1(List<T> list, int order, Comparator<T> comparator) {
//        int left = 0;
//        int rigth = list.size() - 1;
//
//        while (true) {
//            int mid = partition(list, left, rigth , comparator);
//
//
//            if (mid == order) {
//                return list.get(mid);
//            } else if (order < mid) {
//
//                rigth = mid;
//            } else {
//                //order -= mid + 1 ;
//                left = mid + 1;
//            }
//
//        }
//
//    }
//
//    private static <T> int partition(List<T> list, int left, int right, Comparator<T> comparator) {
//
//        int i = left - 1;
//        int j = right + 1;
//        int index = left + (right - left) /2;
//        T pivot = list.get(index);
//        while (true) {
//
//            while (comparator.compare(list.get(++i), pivot) < 0) {
//                if (i == right) break;
//            }
//
//            while (comparator.compare(pivot, list.get(--j)) < 0) {
//                if (j == left) break;
//            }
//
//            if (i >= j) {
//                break;
//            }
//
//            T swap = list.get(i);
//            list.set(i, list.get(j));
//            list.set(j, swap);
//        }
//        return j;
//
////        T element = list.get((left + right) / 2);
////        int mid = (left + right) / 2;
////        int i = left;
////        int j = right;
////
////        while (true) {
////            while (comparator.compare(list.get(i), element) < 0) {
////                i++;
////            }
////            while (comparator.compare(list.get(j), element) > 0) {
////                j--;
////            }
////            if (i < j) {
////                T swap = list.get(i);
////                list.set(i, list.get(j));
////                list.set(j, swap);
////                i++;
////                j--;
////            } else {
////                return j;
////            }
////
////        }
//    }

}
