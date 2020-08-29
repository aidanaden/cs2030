import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        Point circlePointCenter = new Point(0, -1);
        Double circleRadius = 1.0;

        Circle circleOne = new Circle(circlePointCenter, circleRadius);
        Point samplePoints[] = new Point[] {new Point(0,0), new Point(0,-1), new Point(1,0), new Point(0,1), new Point(-1,0)};
        ArrayList<Point> samplePointsArrayList = new ArrayList<Point>(Arrays.asList(samplePoints));


        ArrayList<Point> pointsContained = countCoverage(circleOne, samplePointsArrayList); 
        System.out.println(pointsContained);
    }

    public static ArrayList<Point> countCoverage(Circle circle, ArrayList<Point> points) {

        ArrayList<Point> pointContained = new ArrayList<Point>();
    
        for (Point i : points) {
            if (circle.contains(i)) {
                pointContained.add(i);
            }
        }
        return pointContained;
    }
}

