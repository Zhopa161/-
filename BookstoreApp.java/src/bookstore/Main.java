package bookstore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.application.Platform;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Загрузка FXML - используем относительный путь от корня classpath
            URL fxmlUrl = getClass().getResource("/bookstore.fxml");
            if (fxmlUrl == null) {
                throw new IOException("Не могу найти bookstore.fxml в classpath");
            }

            Parent root = FXMLLoader.load(fxmlUrl);
            Scene scene = new Scene(root, 600, 400);

            // Загрузка CSS
            URL cssUrl = getClass().getResource("/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            primaryStage.setTitle("Книжный магазин");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            showErrorAlert("Ошибка запуска", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void showErrorAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}