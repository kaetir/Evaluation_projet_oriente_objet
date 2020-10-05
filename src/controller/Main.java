package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.*;
import model.Map.*;
import view.DisplayController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/view.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Team Aux Niais");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        DisplayController dController = fxmlLoader.getController();

        Controller controller = Controller.getInstance();
        controller.setDisplayController(dController);

        controller.step();


    }


    public static void main(String[] args) {
        launch(args);
        System.out.println("Bip Boop Init");

    }
}
