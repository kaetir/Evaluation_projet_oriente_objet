package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        System.out.println("Hello?");

        Individual a = new Pirate();
        Individual b = new MasterPirate();

        System.out.println(a instanceof Mechant);
        System.out.println(a instanceof Good);
        System.out.println(a instanceof Pirate);
        System.out.println(a instanceof MasterPirate);
        System.out.println(b instanceof Pirate);
        // Ptet j'ai pas fait attention,  mais super utile!
    }
}
