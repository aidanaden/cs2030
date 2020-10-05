import java.util.ArrayList;

class Schedule {

    private final ArrayList<Class> classSchedule;

    Schedule() {

        this.classSchedule = new ArrayList<Class>();
    }

    Schedule(ArrayList<Class> classSchedule) {
        this.classSchedule = classSchedule;
    }

    public Schedule add(Class newClass) {


        int clashCounter = 0;

        for (Class currentClass: this.classSchedule) {

            if (currentClass.clashWith(newClass)) {

                clashCounter++;
                // System.out.print(newClass + " clashes with " + currentClass);
            }       
        }

        // String printStr = String.format("clash counter is %d", clashCounter);
        // System.out.println(printStr);

        ArrayList<Class> newClassSchedule = new ArrayList<Class>();
        newClassSchedule.addAll(this.classSchedule);

        if (clashCounter == 0) {

            newClassSchedule.add(newClass);
        }

        return new Schedule(newClassSchedule);
    }

    @Override
    public String toString() {

        String returnString = "";

        this.classSchedule.sort(new ClassComparator());

        for (Class currentClass: this.classSchedule) {

            if (currentClass.getDuration() == 2) {

                Lecture currentLecture = (Lecture) currentClass;
                returnString += currentLecture.toString() + "\n";
            
            } else {

                Tutorial currentTutorial = (Tutorial) currentClass;
                returnString += currentTutorial.toString() + "\n";
            }
        }

        return returnString;
    }
}
