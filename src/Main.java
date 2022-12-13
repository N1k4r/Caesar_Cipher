import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Cryptanalyzer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 300);
        stage.setTitle("Cryptanalyzer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}