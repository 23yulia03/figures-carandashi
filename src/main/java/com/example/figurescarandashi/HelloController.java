package com.example.figurescarandashi;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private Canvas canvas;
    @FXML
    private TextField shapeInput;
    @FXML
    private TextField sizeInput;
    @FXML
    private ColorPicker colorPicker;

    private ShapeFactory shapeFactory = new ShapeFactory();

    // Метод для очистки холста
    public void onClear() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    // Создаёт фигуру на основе введённого названия
    private Shape createShapeByName(String shapeName, Color color, double size) {
        switch (shapeName) {
            case "линия":
                return shapeFactory.createShape("Line", color, size);
            case "квадрат":
                return shapeFactory.createShape("Square", color, size);
            case "треугольник":
                return shapeFactory.createShape("Triangle", color, size, size);
            case "круг":
                return shapeFactory.createShape("Circle", color, size);
            case "угол":
                return shapeFactory.createShape("Angle", color, size);
            case "пятиугольник":
                return shapeFactory.createShape("Pentagon", color, size);
            default:
                return null;
        }
    }

    // Показывает уведомление об ошибке
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Обработчик для движения мыши
    @FXML
    private void onMouseMove(MouseEvent event) {
        String shapeName = shapeInput.getText().trim().toLowerCase(); // Получаем название фигуры
        Color color = colorPicker.getValue(); // Получаем цвет
        double size = Double.parseDouble(sizeInput.getText()); // Получаем размер фигуры
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Shape shape = createShapeByName(shapeName, color, size);

        if (shape != null) {
            // Устанавливаем позицию фигуры на место курсора
            shape.setPosition(event.getX(), event.getY());
            shape.draw(gc);
        } else {
            showAlert("Ошибка", "Неверное название фигуры.");
        }
    }
}