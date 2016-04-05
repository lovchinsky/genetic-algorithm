package dev.lovchinsky.genetic;

import dev.lovchinsky.genetic.conception.City;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CustomCanvas extends Canvas implements Display {
    private static final int FONT_SIZE = 11;
    private static final double POINT_DIAMETER = 5.0;

    GraphicsContext graphicsContext;

    public CustomCanvas(double width, double height) {
        super(width, height);
        graphicsContext = getGraphicsContext2D();
        setStroke();
        setFont();
    }

    private void setStroke() {
        graphicsContext.setStroke(Color.DARKGREY);
        graphicsContext.strokeRect(0, 0, getWidth(), getHeight());
    }

    private void setFont() {
        graphicsContext.setFont(new Font(FONT_SIZE));
    }

    @Override
    public void clear() {
        graphicsContext.clearRect(0, 0, getWidth(), getHeight());
        setStroke();
    }

    @Override
    public void drawCity(City city) {
        graphicsContext.fillOval(city.getX(), city.getY(), POINT_DIAMETER, POINT_DIAMETER);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.fillText(String.valueOf(city.getId() + 1), city.getX() + POINT_DIAMETER / 2, city.getY() - POINT_DIAMETER);
    }

    @Override
    public void drawRoute(City firstCity, City secondCity) {
        graphicsContext.beginPath();
        graphicsContext.moveTo(firstCity.getX(), firstCity.getY());
        graphicsContext.lineTo(secondCity.getX(), secondCity.getY());
        graphicsContext.stroke();
        graphicsContext.closePath();
    }

}
