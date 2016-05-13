package sorting;

import main.Main;

/**
 * Created by Леонид on 13.05.2016.
 */
public class InsertionSort extends Sorting {
    public InsertionSort(int[] unsortedArray) {
        sortedArray = unsortedArray.clone();
        sortingName ="Вставки";
    }

    @Override
    public void run() {

        for (int i = 1; i < sortedArray.length; i++) {
            int currElem = sortedArray[i];
            int prevKey = i - 1;
            while (prevKey >= 0 && sortedArray[prevKey] > currElem) {
                try {
                    sortingPause();
                } catch (InterruptedException e) {
                    return;
                }
                sortedArray[prevKey + 1] = sortedArray[prevKey];
                prevKey--;
                synchronized (Main.monitor) {
                    try {
                        sortingWait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
            sortedArray[prevKey + 1] = currElem;

        }
        cr.buttonReset();
    }
}
