package com.example.figurescarandashi;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Shape {
    private double x2, y2;

    public Line(Color color, double x) {
        super(color);
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public double area() {
        return 0; // Линия не имеет площади
    }

    @Override
    public void draw(GraphicsContext gr) {
        gr.setStroke(color);
        gr.strokeLine(x, y, x2, y2);
    }
}
