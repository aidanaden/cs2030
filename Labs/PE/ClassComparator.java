import java.util.Comparator;

public class ClassComparator implements Comparator<Class> {
    
    @Override
    public int compare(Class c1, Class c2) {

        if (c1.getStartTime() < c2.getStartTime()) {
            return -1;

        } else if (c1.getStartTime() == c2.getStartTime()) {
            
            int c1EndTime = c1.getStartTime() + c1.getDuration();
            int c2EndTime = c2.getStartTime() + c2.getDuration();

            if (c1EndTime < c2EndTime) {
                return -1;

            } else if (c2EndTime < c1EndTime) {
                return 1;

            } else {

                int c1Id = c1.getClassId();
                int c2Id = c2.getClassId();

                if (c1Id < c2Id) {
                    return -1;
                    
                } else {
                    return 1;
                }
            }

        } else {
            return 1;
        }
    }
}
