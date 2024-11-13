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
    private ColorPicker colorPicker;

    private ShapeFactory shapeFactory = new ShapeFactory();
    private double startX, startY; // Начальные координаты для рисования фигур в ряд
    private boolean isMousePressed = false; // Флаг, чтобы отслеживать, зажата ли мышь

    // Обработчик кнопки "Нарисовать"
    public void onDraw() {
        String shapeName = shapeInput.getText().trim().toLowerCase(); // Получаем название фигуры в нижнем регистре
        Color color = colorPicker.getValue(); // Получаем выбранный цвет
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Shape shape = createShapeByName(shapeName, color);

        if (shape != null) {
            // Устанавливаем позицию фигуры на место клика
            shape.setPosition(200, 200);  // Для теста, можно переместить позже
            shape.draw(gc);
        } else {
            showAlert("Ошибка", "Неверное название фигуры.");
        }
    }

    // Метод для очистки холста
    public void onClear() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    // Создаёт фигуру на основе введённого названия
    private Shape createShapeByName(String shapeName, Color color) {
        switch (shapeName) {
            case "линия":
                return shapeFactory.createShape("Line", color, 100);
            case "квадрат":
                return shapeFactory.createShape("Square", color, 100);
            case "треугольник":
                return shapeFactory.createShape("Triangle", color, 100, 100);
            case "круг":
                return shapeFactory.createShape("Circle", color, 50);
            case "угол":
                return shapeFactory.createShape("Angle", color, 50);
            case "пятиугольник":
                return shapeFactory.createShape("Pentagon", color, 50);
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

    // Обработчик для одиночного клика
    @FXML
    private void onMouseClick(MouseEvent event) {
        String shapeName = shapeInput.getText().trim().toLowerCase(); // Получаем название фигуры
        Color color = colorPicker.getValue(); // Получаем цвет
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Shape shape = createShapeByName(shapeName, color);

        if (shape != null) {
            // Устанавливаем позицию фигуры на место клика
            shape.setPosition(event.getX(), event.getY());
            shape.draw(gc);
        } else {
            showAlert("Ошибка", "Неверное название фигуры.");
        }
    }

    // Обработчик для нажатия мыши (зажатие)
    @FXML
    private void onMousePress(MouseEvent event) {
        isMousePressed = true;
        startX = event.getX(); // Сохраняем координаты нажатия
        startY = event.getY();
    }

    // Обработчик для перетаскивания мыши (рисование в ряд)
    @FXML
    private void onMouseDrag(MouseEvent event) {
        if (isMousePressed) {
            String shapeName = shapeInput.getText().trim().toLowerCase(); // Получаем название фигуры
            Color color = colorPicker.getValue(); // Получаем цвет
            GraphicsContext gc = canvas.getGraphicsContext2D();

            Shape shape = createShapeByName(shapeName, color);

            if (shape != null) {
                // Рисуем фигуры в ряд по горизонтали
                double offsetX = event.getX() - startX;
                double offsetY = event.getY() - startY;
                for (int i = 0; i < 5; i++) {  // Пример: рисуем 5 фигур
                    shape.setPosition(startX + i * (shape.getWidth() + 10), startY); // Расстояние между фигурами
                    shape.draw(gc);
                }
            } else {
                showAlert("Ошибка", "Неверное название фигуры.");
            }
        }
    }

    // Обработчик для отпускания мыши (остановить рисование в ряд)
    @FXML
    private void onMouseRelease(MouseEvent event) {
        isMousePressed = false; // Снимаем флаг зажатия мыши
    }
}
