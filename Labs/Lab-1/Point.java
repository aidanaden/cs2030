class Point {
    // include the properties of a Point
    private double x;
    private double y;
    // complete the constructor
    Point(double x, double y) {
        this.x = x + (double)0.0;
        this.y = y + (double)0.0;
    }

    public Point midPoint(Point otherPoint) {
        double midpointX = (this.x + otherPoint.x) / 2;
        double midpointY = (this.y + otherPoint.y) / 2;
        
        Point midPoint = new Point(midpointX, midpointY);
        return midPoint;
    }

    public double angleTo(Point otherPoint) {

        double xDistance = otherPoint.x - this.x;
        double yDistance = otherPoint.y - this.y;

        double angle = Math.atan2(yDistance, xDistance);

        return angle;
    }

    public double distanceTo(Point otherPoint) {
        
        double xDistance = Math.abs(otherPoint.x - this.x);
        double yDistance = Math.abs(otherPoint.y - this.y);

        double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        
        return distance;
    }

    public Point moveTo(double theta, double d) {
        double newPointX = this.x + d * Math.cos(theta);
        double newPointY = this.y + d * Math.sin(theta);
        
        Point newPoint = new Point(newPointX, newPointY);
        return newPoint;
    }

    // include a toString method
    @Override
    public String toString() {
        String pointX = String.format("%.3f", this.x);
        String pointY = String.format("%.3f", this.y);
        return "point (" + pointX + ", " + pointY + ")";
    }    
}
