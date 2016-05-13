package sorting;

import main.Main;

/**
 * Created by Леонид on 13.05.2016.
 */
public class QuickSort extends Sorting {

    public QuickSort(int[] unsortedArray) {
        sortedArray = unsortedArray.clone();
        sortingName ="Быстрая";
    }
    @Override
    public void run() {
        int startIndex = 0;
        int endIndex = sortedArray.length - 1;
        doSort(startIndex, endIndex);
        cr.buttonReset();

    }

    private void doSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (sortedArray[i] <= sortedArray[cur])) {
                i++;
            }
            while (j > cur && (sortedArray[cur] <= sortedArray[j])) {
                j--;
            }
            try {
                sortingPause();
            } catch (InterruptedException e) {
                return;
            }
            if (i < j) {
                synchronized (Main.monitor) {
                    try {
                        sortingWait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                int temp = sortedArray[i];
                sortedArray[i] = sortedArray[j];
                sortedArray[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur);
        doSort(cur+1, end);
    }
}
