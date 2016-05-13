package sorting;

import main.Controller;
import main.Main;

/**
 * Created by Леонид on 22.04.2016.
 */
public abstract class Sorting implements Runnable {
    protected int[] sortedArray;
    public Controller cr;
    protected String sortingName ="defaultSortingName";
    public int[] getSortedArray() {
        return sortedArray;
    }
    public String getSortingName(){return sortingName;}
    public void setSortedArray(int[] sortedArray) {
        this.sortedArray = sortedArray.clone();
    }

    protected void sortingPause() throws InterruptedException {
        if (cr.isPause()) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
        }
    }

    protected void sortingWait() throws InterruptedException {
        try {
            cr.setWaitingFlag(true);
            Main.monitor.wait();
        } catch (InterruptedException e) {
            throw e;
        }
        if (!Thread.interrupted()) {
            cr.reDraw();
            cr.setWaitingFlag(false);
        } else {
            throw new InterruptedException();
        }
    }
}
