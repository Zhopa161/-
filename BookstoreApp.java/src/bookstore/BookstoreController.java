package bookstore;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.SpinnerValueFactory;
import java.nio.file.*;

public class BookstoreController {
    @FXML private ImageView bookImage;
    @FXML private Label bookTitle;
    @FXML private Label bookAuthor;
    @FXML private Label bookPrice;
    @FXML private Spinner<Integer> quantitySpinner;
    @FXML private Label statusLabel;

    @FXML
    public void initialize() {
        // Загрузка изображения с проверкой
        try {
            // Вариант 1: Из папки resources (рекомендуется)
            var imageUrl = getClass().getResource("book.jpg");
            if (imageUrl != null) {
                bookImage.setImage(new Image(imageUrl.toString()));
            } else {
                // Вариант 2: Абсолютный путь (если вариант 1 не работает)
                Path path = Paths.get("src/resources/book.jpg");
                if (Files.exists(path)) {
                    bookImage.setImage(new Image(path.toUri().toString()));
                } else {
                    System.err.println("Изображение не найдено ни в ресурсах, ни по пути: " + path.toAbsolutePath());
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка загрузки изображения: " + e.getMessage());
        }

        // Инициализация данных книги
        bookTitle.setText("Преступление и наказание");
        bookAuthor.setText("Автор: Ф.М. Достоевский");
        bookPrice.setText("Цена: 599 ₽");

        // Настройка спиннера
        SpinnerValueFactory.IntegerSpinnerValueFactory factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        quantitySpinner.setValueFactory(factory);
    }

    @FXML
    private void handleBuyButton() {
        try {
            int quantity = quantitySpinner.getValue();
            double totalPrice = quantity * 599.0;
            statusLabel.setText(String.format("Спасибо за покупку! Итого: %.2f ₽", totalPrice));
        } catch (Exception e) {
            statusLabel.setText("Ошибка при оформлении заказа");
        }
    }
}