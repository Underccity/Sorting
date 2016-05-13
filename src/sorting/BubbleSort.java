package sorting;

import main.Main;

/**
 * Created by Леонид on 23.04.2016.
 */
public class BubbleSort extends Sorting {


    public BubbleSort(int[] unsortedArray) {
        sortedArray = unsortedArray.clone();
        sortingName ="Пузырек";
    }

    @Override
    public void run() {
        for (int i = 0; i < sortedArray.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < sortedArray.length - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    try {
                        sortingPause();
                    } catch (InterruptedException e) {
                        return;
                    }
                    int tmp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = tmp;
                    swapped = true;
                    synchronized (Main.monitor) {
                        try {
                            sortingWait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            }
            if (!swapped)
                break;
        }
        cr.buttonReset();
    }
}
