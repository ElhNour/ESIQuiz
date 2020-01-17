package sample;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;





//Au cas où, vous voulez tester l'application =, le username est "nour", le mot de passe est : "EL HASSANE17-12-1999"




public class ESIQuiz_GUI extends Application {

    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        primaryStage.setWidth(580);
        primaryStage.setHeight(600);
        Scene scene = new Scene(root, 1000, 1000);
        scene.getStylesheets().add(ESIQuiz_GUI.class.getResource("style.css").toExternalForm());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        try {
            scene.setRoot(fxmlLoader.load());
        } catch (IOException e) {
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}

