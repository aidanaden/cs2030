Circle createUnitCircle(Point p, Point q) {
    Point midPoint = p.midPoint(q);
    double theta = p.angleTo(q);
    double pqLength = p.distanceTo(q);
    double mqLength = midPoint.distanceTo(q);
    double dSquare = 1 - (Math.pow(mqLength, 2));
    double d = Math.sqrt(dSquare);

    Point unitCircleCenter = midPoint.moveTo((theta + Math.PI/2), d);
    Circle unitCircle = new Circle(unitCircleCenter, 1.0);

    return unitCircle;
}

int findMaxDiscCoverage(Point[] points) {
    int maxDiscCoverage = 0;
    int numPointsContained = 0;

    for (int i = 0; i < points.length - 1; i++) {
        for (int j = i + 1; j < points.length; j++) {
            
            // create unit circle from 2 points
            Circle ijUnitCircle = createUnitCircle(points[i], points[j]);
            
            // check if unit circle created contains the 2 original points
            Point[] originPoints = new Point[] {points[i], points[j]};
            if (ijUnitCircle.containsPoints(originPoints) < 2) {

                //System.out.println("Original points: " + originPoints[0] + originPoints[1]);
                //System.out.println("only 1 point found in circle! pls fix");
                //return 1;
            }

            // check amount of points contained in the unit circle
            numPointsContained = ijUnitCircle.containsPoints(points);
            
            // if number of points contained is larger than number of 
            // points contained in previous circle, this will be the new
            // max number of points.
            if (numPointsContained > maxDiscCoverage) {
                maxDiscCoverage = numPointsContained;
            }
        }
    }

    return maxDiscCoverage;
}