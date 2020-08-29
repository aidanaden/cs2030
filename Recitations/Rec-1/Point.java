public class Point {
    private final double x;
    private final double y;

    Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    double distanceTo(final Point otherpoint) {
        final double dispX = this.x - otherpoint.x;
        final double dispY = this.y - otherpoint.y;
        return Math.sqrt(dispX * dispX + dispY * dispY);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}