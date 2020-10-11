import java.util.Comparator;

public class AssessmentComparator implements Comparator<Assessment> {

    public int convertGradeToInt(String grade) {

        if (grade.equals("A+")) {
        
            return 1;
        
        } else if (grade.equals("A")) {

            return 2;
        
        } else if (grade.equals("A-")) {

            return 3;
        
        } else if (grade.equals("B")) {

            return 4;
        
        } else if (grade.equals("C")) {
            
            return 5;

        } else if (grade.equals("D")) {

            return 6;

        } else {

            return 7;
        }
    }
    
    @Override
    public int compare(Assessment ass1, Assessment ass2) {

        int int1 = convertGradeToInt(ass1.getGrade());
        int int2 = convertGradeToInt(ass2.getGrade());

        if (int1 > int2) {
            
            return -1;

        } else {
            
            return 1;
        
        }
    }
}
