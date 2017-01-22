package qSort;

import iterableRange.Pair;
import iterableRange.Queue;

import java.util.Comparator;


/**
 * Created by artvl on 19.01.17.
 */
public class QSort {

    private static void swap(Object[] array, int i, int j) {
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static int chooseMiddle(int l, int r, int mid, Object[] array, Comparator cmp) {
        Object left = array[l];
        Object right = array[r];
        Object middle = array[mid];
        if (cmp.compare(middle, right) < 0) {
            if (cmp.compare(middle, left) > 0) {
                return mid;
            } else if (cmp.compare(left, right) < 0) {
                return l;
            } else {
                return r;
            }
        } else {
            if (cmp.compare(middle, left) < 0) {
                return mid;
            } else if (cmp.compare(left, right) < 0) {
                return r;
            } else {
                return l;
            }
        }
    }

    private static int partition(Object[] array, int begin, int end, Comparator cmp) {

        int i = begin - 1;
        int j = end + 1;
        int middle = (begin + end) / 2;
        int index = chooseMiddle(begin, end, middle, array, cmp);
        Object pivot = array[index];
        while (true) {

            while (cmp.compare(array[++i], pivot) < 0) {
                if (i == end) break;
            }

            while (cmp.compare(pivot, array[--j]) < 0) {
                if (j == begin) break;
            }

            if (i >= j) {
                break;
            }

            swap(array, i, j);
        }

        return j;
    }

    private static void qsort(Object[] array, int begin, int end, Comparator cmp) {
        if (end > begin) {
            int index = partition(array, begin, end, cmp);
            Queue<Pair<Integer, Integer>> queue = new Queue<>();
            if (index > (begin + end) / 2) {
                qsort(array, index + 1, end, cmp);
                queue.enqueue(Pair.of(begin, index));
            } else {
                qsort(array, begin, index, cmp);
                queue.enqueue(Pair.of(index + 1, end));
            }

            //Bigger part w/o recursion
            while (!queue.isEmpty()) {
                Pair<Integer, Integer> current = queue.dequeue();
                int ind = partition(array, current.getKey(), current.getValue(), cmp);
                if (current.getKey() < ind) {
                    queue.enqueue(Pair.of(current.getKey(), ind));
                }
                if (current.getValue() > ind + 1) {
                    queue.enqueue(Pair.of(ind + 1, current.getValue()));
                }
            }


        }
    }

    public static void sort(Object[] array, Comparator cmp) {
        if (array.length < 2) {
            return;
        }
        if (array.length > 2) {
            qsort(array, 0, array.length - 1, cmp);
        } else {
            insertionSort(array, cmp);
        }
    }

    private static void insertionSort(Object[] array, Comparator cmp) {
        for (int j = 1; j < array.length; j++) {
            Object key = array[j];
            int i = j - 1;
            while (i > 0 && cmp.compare(key, array[i]) < 0) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = key;
        }

    }
}
