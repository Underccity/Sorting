package main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import sorting.BubbleSort;
import sorting.Sorting;

import java.util.Arrays;

public class Controller {
    @FXML
    private Canvas canvasSort;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonPause;
    @FXML
    private Label labelSortingName;

    GraphicsContext gs;
    private Sorting sorting;
    private boolean isPause;
    private boolean waitingFlag;
    private Thread th;

    public boolean isPause() {
        return isPause;
    }

    public Thread getTh() {
        return th;
    }

    public void setLabelSortingName(String labelSortingName) {
        this.labelSortingName.setText(labelSortingName);
    }

    public Sorting getSorting() {
        return sorting;
    }

    public void setSorting(Sorting sorting) {
        this.sorting = sorting;
    }

    public boolean getWaitingFlag() {
        return waitingFlag;
    }

    public void setWaitingFlag(boolean waitingFlag) {
        this.waitingFlag = waitingFlag;
    }

    public void initialize() {
        sorting = new BubbleSort(Main.unsortedMass);
        gs = canvasSort.getGraphicsContext2D();
        gs.setStroke(Color.RED);
        gs.setLineWidth(3);
        int[] array = sorting.getSortedArray();
        canvasSort.setHeight(Main.unsortedMass.length*10+gs.getLineWidth());
        canvasSort.setWidth(Main.MAX_ARRAY_VALUE*2+10);
        for (int i = 0; i < array.length; i++) {
            gs.strokeLine(10, (i + 1) * 10, array[i] * 2, (i + 1) * 10);
        }

    }

    public void startButtonClick(ActionEvent event) {

        if (buttonStart.getText().equals("Старт")) {
            //isPause = false;
            th = new Thread(sorting);
            th.start();
            buttonStart.setText("Стоп");
            buttonPause.setDisable(false);

        } else {
            buttonStart.setText("Старт");
            isPause = false;
            waitingFlag = false;
            th.interrupt();
            th = null;
            //sorting.setSortedArray(Main.unsortedMass);
            buttonReset();
            reDraw();
        }

    }

    public void pauseButtonClick() {
        if (buttonPause.getText().equals("Пауза")) {
            isPause = true;
            waitingFlag = false;
            buttonPause.setText("Продолжить");
        } else {
            isPause = false;
            buttonPause.setText("Пауза");
            synchronized (sorting) {
                sorting.notifyAll();
            }
        }
    }

    public void reDraw() {
        gs.clearRect(0, 0, canvasSort.getWidth(), canvasSort.getHeight());
        int[] array = sorting.getSortedArray();
        for (int i = 0; i < array.length; i++) {
            gs.strokeLine(10, (i + 1) * 10, array[i] * 2, (i + 1) * 10);

        }
    }

    public void buttonReset() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                isPause = false;
                waitingFlag = false;
                th = null;
                sorting.setSortedArray(Main.unsortedMass);
                buttonStart.setText("Старт");
                buttonPause.setText("Пауза");
                buttonPause.setDisable(true);
            }
        });
    }
}
