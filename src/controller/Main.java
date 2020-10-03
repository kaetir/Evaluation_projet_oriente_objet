package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.*;
import view.DisplayController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/view.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Team aux niais");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        DisplayController dController = fxmlLoader.getController();

        Map mapster = new Map(15, 15, 3, 2);
        dController.drawMap(mapster);

    }


    public static void main(String[] args) {
        launch(args);
        System.out.println("Bip Boop Init");

        Controller controller = Controller.getInstance();

        controller.start(); // Starting
    }
}
