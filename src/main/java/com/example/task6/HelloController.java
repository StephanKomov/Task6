package com.example.task6;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public ObservableList<String> obs;
    private GraphicsContext gc;

    @FXML
    private ColorPicker colorpicker;
    @FXML
    private ColorPicker strokeColor;
    @FXML
    private ListView<String> listview;
    @FXML
    private TextField textStroke;

    @FXML
    private Canvas canvas;

    @FXML
    private Button clear;
    @FXML
    private Label label;

    private ShapeFactory shapeFactory;


    @Override
    public void initialize(URL location, ResourceBundle resource) {
        shapeFactory = new ShapeFactory();
        gc = canvas.getGraphicsContext2D();
        obs = FXCollections.observableArrayList("Круг", "Треугольник", "Прямоугольник", "Овал");
        listview.setItems(obs);
        canvas.setOnMouseClicked(event -> drawShape(event));
    }

    @FXML
    private void drawShape(MouseEvent mouseEvent) {
        int index = listview.getSelectionModel().getSelectedIndex();
        Shape shape = shapeFactory.createShape(index);

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        Color defaultColor = Color.RED;
        int defaultStrokeWidth = 1;
        Color defaultStrokeColor = Color.BLACK;

        Color shapeColor = colorpicker.getValue() != null ? colorpicker.getValue() : defaultColor;

        int strokeWidth;
        try {
            strokeWidth = textStroke.getText().isEmpty() ? defaultStrokeWidth : Integer.parseInt(textStroke.getText());
        } catch (NumberFormatException e) {
            strokeWidth = defaultStrokeWidth;
        }

        shape.setColor(shapeColor);
        shape.draw(gc, x, y, shapeColor, strokeWidth, strokeColor.getValue());
        label.setText("Фигура: " + shape);
    }

    @FXML
    private void btnclear() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        label.setText("Панель очищена от фигур");
    }

}