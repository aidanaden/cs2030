class Circle {
    private Point centre;
    private double radius;
    
    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public int containsPoints(Point[] points) {

        int numPointsContained = 0;

        for (Point p : points) {

            double distance = p.distanceTo(this.centre);   

            if (distance <= this.radius) {
                numPointsContained = numPointsContained + 1;
            } else {
                
                //System.out.println(p);
                //System.out.println(distance);
                continue;
            }
        }

        return numPointsContained;
    }

    @Override
    public String toString() {
        String output1 = String.format("circle of radius %.1f", this.radius);
        String output2 = " centered at " + this.centre;

        return output1 + output2;
    }
}
