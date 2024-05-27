package view;

import java.awt.Polygon;
import java.awt.Point;

public class Hexagon {
    private Polygon hexagon;
    private Point position;
    private int value;

    public Hexagon(Point position, int radius, int value) {
        this.position = position;
        this.value = value;
        hexagon = createHexagon(position, radius);
    }

    private Polygon createHexagon(Point center, int radius) {
        Polygon hex = new Polygon();
        for (int i = 0; i < 6; i++) {
            int angleDeg = 60 * i - 30;
            double angleRad = Math.toRadians(angleDeg);
            int x = center.x + (int) (radius * Math.cos(angleRad));
            int y = center.y + (int) (radius * Math.sin(angleRad));
            hex.addPoint(x, y);
        }
        return hex;
    }

    public Polygon getHexagon() {
        return hexagon;
    }

    public Point getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }
}
