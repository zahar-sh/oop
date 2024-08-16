package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import sample.logic.*;

import java.util.function.IntConsumer;

public class Controller {
    @FXML
    private Canvas canvas;
    @FXML
    private TextField posX, posY, width, height;
    @FXML
    private ChoiceBox<Creator<? extends MyFigure>> addFigureCB;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Label figureType, out;

    private final MyList<MyFigure> figures = new MyList<>();
    private MyFigure selected;
    private int dx, dy;

    @SuppressWarnings("unchecked")
    @FXML
    private void initialize() {
        canvas.setOnMousePressed(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            for (MyFigure f : figures) {
                if (f.contains(x, y)) {
                    figureType.setText(f.getClass().getSimpleName());
                    posX.setText(Integer.toString(f.getX()));
                    posY.setText(Integer.toString(f.getY()));
                    width.setText(Integer.toString(f.getWidth()));
                    height.setText(Integer.toString(f.getHeight()));

                    selected = f;
                    dx = f.getX() - x;
                    dy = f.getY() - y;
                    repaint();
                    return;
                }
            }
            figureType.setText("");
            posX.setText("");
            posY.setText("");
            width.setText("");
            height.setText("");
            selected = null;
            repaint();
        });
        canvas.setOnMouseDragged(event -> {
            if (selected != null) {
                selected.setX((int) event.getX() + dx);
                selected.setY((int) event.getY() + dy);
                posX.setText(Integer.toString(selected.getX()));
                posY.setText(Integer.toString(selected.getY()));
                repaint();
            }
        });
        setSetter(posX, value -> {
            if (selected != null) {
                selected.setX(value);
                repaint();
            }
        });
        setSetter(posY, value -> {
            if (selected != null) {
                selected.setY(value);
                repaint();
            }
        });
        setSetter(width, value -> {
            if (selected != null) {
                selected.setWidth(value);
                repaint();
            }
        });
        setSetter(height, value -> {
            if (selected != null) {
                selected.setHeight(value);
                repaint();
            }
        });

        final ObservableList<Creator<? extends MyFigure>> creators =
                FXCollections.observableArrayList(Square::new, Rectangle::new, Circle::new, Ellipse::new, Line::new,
                        () -> new Polygon(new double[]{
                                410.0, 430.0, 470.0,
                                490.0, 470.0, 430.0
                        }, new double[]{
                                160.0, 130.0, 130.0,
                                160.0, 200.0, 200.0
                        }));
        final String[] names = {
                Square.class.getSimpleName(),
                Rectangle.class.getSimpleName(),
                Circle.class.getSimpleName(),
                Ellipse.class.getSimpleName(),
                Line.class.getSimpleName(),
                Polygon.class.getSimpleName(),
        };

        addFigureCB.setConverter(new StringConverter<>() {
            public String toString(Creator<? extends MyFigure> c) {
                int i = creators.indexOf(c);
                return i < 0 ? c.toString() : names[i];
            }

            public Creator<? extends MyFigure> fromString(String s) {
                for (int i = 0; i < names.length; i++)
                    if (s.equals(names[i]))
                        return creators.get(i);
                return null;
            }
        });
        addFigureCB.setItems(creators);
        addFigureCB.setValue(creators.get(0));

        colorPicker.setValue(Color.RED);
    }

    private void setSetter(TextField field, IntConsumer setter) {
        field.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    setter.accept(Integer.parseInt(field.getText().trim()));
                } catch (NumberFormatException ignored) {
                }
            }
        });
    }

    private void repaint() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.setStroke(Color.BLACK);
        g.setLineWidth(1.5);

        for (MyFigure f : figures) {
            f.draw(g);
            if (f == selected)
                f.drawStroke(g);
        }
    }

    @FXML
    private void addFigure() {
        Creator<? extends MyFigure> c = addFigureCB.getSelectionModel().getSelectedItem();
        MyFigure f = c.create();
        f.setX(((int) canvas.getWidth()) >> 1);
        f.setY(((int) canvas.getHeight()) >> 1);
        f.setWidth(80);
        f.setHeight((f instanceof Line) ? 2 : 50);
        f.setFillColor(colorPicker.getValue());
        figures.add(f);
        repaint();
    }

    @FXML
    private void setColor() {
        if (selected != null) {
            selected.setFillColor(colorPicker.getValue());
            repaint();
        } else {
            out.setText("Figure not select");
        }
    }

    @FXML
    private void removeSelectedFigure() {
        if (selected != null) {
            figures.remove(selected);
            selected = null;
            repaint();
        } else {
            out.setText("Figure not select");
        }
    }
}