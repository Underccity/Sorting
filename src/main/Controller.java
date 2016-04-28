package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import sorting.BubbleSort;
import sorting.Sorting;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;

public class Controller {
    @FXML
    private Canvas canvasSort;
    @FXML
    private Button buttonStart;

    GraphicsContext gs;

    private Sorting sorting;
    private int[] array;
    public Button getStart() {
        return buttonStart;
    }


    public void initialize()  {
        sorting=new BubbleSort(Main.unsortedMass);
        array=sorting.getSortedArray();

        gs = canvasSort.getGraphicsContext2D();
        gs.setStroke(Color.RED);
        gs.setLineWidth(1);

        for (int i = 0; i < array.length; i++) {
            gs.strokeLine(10, (i + 1) * 10, array[i]*2, (i + 1) * 10);
        }

    }

    public void reDraw(ActionEvent event) {
        System.out.println(Arrays.toString(array));
        System.out.println(event.getTarget().hashCode());
        Random random=new Random();
        gs.clearRect(0, 0, canvasSort.getWidth(), canvasSort.getHeight());

        for (int i = 0; i < array.length; i++) {
            array[i]=random.nextInt(95)+5;
            gs.strokeLine(10, (i + 1) * 10, array[i] * 2, (i + 1) * 10);
        }
        System.out.println(Arrays.toString(array));
    }
}
