public class Circle {
    private final Point centre;
    private final double radius;

    Circle(final Point centre, final double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    boolean contains(Point point) {
        return centre.distanceTo(point) <= this.radius;
    }
}