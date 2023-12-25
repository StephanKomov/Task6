package com.example.task6;

import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
    private Map<Integer, Shape> shapeMap;

    public ShapeFactory() {
        shapeMap = new HashMap<>();
        shapeMap.put(0, new Circle(Color.RED, 5));
        shapeMap.put(1, new Triangle(Color.GREEN, 5, 10));
        shapeMap.put(2, new Rectangle(Color.BLUE, 10, 15));
        shapeMap.put(3, new Oval(Color.YELLOW, 8, 5));
    }

    public Shape createShape(int id) {
        return shapeMap.get(id);
    }
}