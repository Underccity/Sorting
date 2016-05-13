package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sorting.*;

import java.util.Arrays;
import java.util.Random;

public class Main extends Application {

    //<Sorting settings>

    //Для добавления отрисовки новой сортировки добавить класс этой сортировки в массив sortings,
    private static final Class[] sortings = new Class[]
            {BubbleSort.class, InsertionSort.class, QuickSort.class, SelectionSort.class};
    private static final int NUMBER_OF_SORTINGS = sortings.length;
    private static final int ARRAY_SIZE = 40;
    public static final int PAUSE_BETWEEN_REDRAW=800;
    public static final int MAX_ARRAY_VALUE=100;
    //</Sorting settings>

    public static Controller[] controllers = new Controller[NUMBER_OF_SORTINGS];
    public static final int[] unsortedMass = new int[ARRAY_SIZE];
    public static final Object monitor = new Object();
    public static Thread mainThread;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Заполнение массива
        HBox hBox = new HBox();
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            unsortedMass[i] = random.nextInt(95) + 5;
        }

        //Отрисовка элементов каждой из сортировок
        for (int i = 0; i < NUMBER_OF_SORTINGS; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
            hBox.getChildren().add(fxmlLoader.load());
            Controller controller = fxmlLoader.getController();
            controller.setSorting((Sorting) sortings[i].getConstructor(int[].class).newInstance(unsortedMass));
            controller.setLabelSortingName(controller.getSorting().getSortingName());
            controllers[i] = controller;
            controllers[i].getSorting().cr = controllers[i];
        }
        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
        new Thread(new Loop()).start();
    }

    public static void main(String[] args) {
        mainThread=Thread.currentThread();
        launch(args);

    }
}



