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
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

public class Main extends Application {

    public static final int NUMBER_OF_SORTINGS=4;
    public static final int ARRAY_SIZE=20;
    public static final int [] unsortedMass=new int[ARRAY_SIZE];
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox hBox = new HBox();


        Random random=new Random();

        Button []ca=new Button[NUMBER_OF_SORTINGS];

        for(int i=0;i<ARRAY_SIZE;i++){
            unsortedMass[i]=random.nextInt(95)+5;
        }

        for(int i=0;i<NUMBER_OF_SORTINGS;i++) {
//            Parent panel = FXMLLoader.load(getClass().getResource("Main.fxml"));
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Main.fxml"));
            hBox.getChildren().add(fxmlLoader.load());
            Controller controller=fxmlLoader.getController();
            //controller.

        }
        Scene scene= new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();

        /*final int SIZE=2;

        HBox hBoxPane=new HBox();
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);


        for(int i=0;i<SIZE;i++) {
            Label label = new Label("Rename me");
            Button start = new Button("Старт");
            Button stop = new Button("Пауза");
            Canvas canvas = new Canvas(300, 500);

            label.setId("Label"+i);
            start.setId("Start"+i);
            stop.setId("Stop"+i);
            canvas.setId("Canvas"+i);

            VBox vBoxPane=new VBox();
            hBoxPane.getChildren().add(vBoxPane);
            vBoxPane.getChildren().addAll(canvas, label, start, stop);
            vBoxPane.setAlignment(Pos.BOTTOM_CENTER);
        }



        //System.out.println(vBox.getChildren());
        primaryStage.setScene(new Scene(hBoxPane));
        primaryStage.show();


*/
    }
}



