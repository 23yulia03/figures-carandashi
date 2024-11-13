package com.example.figurescarandashi;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class Rectangle extends Shape {
    private double length, width;

    public Rectangle(Color color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setFill(color);
        gr.fillRect(x, y, length, width);  // Рисуем прямоугольник с заданными длиной и шириной
    }

    @Override
    public String toString() {
        return "Rectangle color is " + color + " and area is: " + area();
    }
}
