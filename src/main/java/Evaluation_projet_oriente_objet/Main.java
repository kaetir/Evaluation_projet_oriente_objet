import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.java.Evaluation_projet_oriente_objet.controller.Controller;
import main.java.Evaluation_projet_oriente_objet.view.DisplayController;


public class Main extends Application {

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("java/Evaluation_projet_oriente_objet/view/view.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Team Aux Niais");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        DisplayController dController = fxmlLoader.getController();

        Controller.setDisplayController(dController);

    }

    /**
     * Main... you know what
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
        Platform.exit();
        Controller.getInstance().finish(); // To avoid a phantom task
        System.out.println("Bip Boop End");

    }
}
