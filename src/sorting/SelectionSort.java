package sorting;

import main.Main;

/**
 * Created by Леонид on 13.05.2016.
 */
public class SelectionSort extends Sorting {

    public SelectionSort(int[] unsortedArray) {
        sortedArray = unsortedArray.clone();
        sortingName ="Выборки";
    }

    @Override
    public void run() {
        int min, temp;
        for (int index = 0; index < sortedArray.length - 1; index++) {
            min = index;
            try {
                sortingPause();
            } catch (InterruptedException e) {
                return;
            }
            for (int scan = index + 1; scan < sortedArray.length; scan++) {

                if (sortedArray[scan] < sortedArray[min])
                    min = scan;
            }
            // Swap the values
            synchronized (Main.monitor) {
                try {
                    sortingWait();
                } catch (InterruptedException e) {
                    return;
                }
            }
            temp = sortedArray[min];
            sortedArray[min] = sortedArray[index];
            sortedArray[index] = temp;
        }
        cr.buttonReset();
    }
}

