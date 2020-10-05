class Class {

    private final String moduleCode;
    private final int classId;
    private final String venueId;
    private final int startTime;
    private final int duration;
    private final Instructor instructor;


    Class(String moduleCode, int classId, String venueId, int startTime, int duration, Instructor instructor) {

        this.moduleCode = moduleCode;
        this.classId = classId;
        this.venueId = venueId;
        this.startTime = startTime;
        this.duration = duration;
        this.instructor = instructor;
    }

    public int getClassId() {
        return this.classId;
    }

    public String getModuleCode() {

        return this.moduleCode;
    }

    public String getVenueId() {

        return this.venueId;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getDuration() {
        return this.duration;
    }

    public Instructor getInstructor() {

        return this.instructor;
    }

    public String toString() {

        return this.venueId;
    }

    public boolean hasSameModule(Class anotherClass) {

        if (this.moduleCode == anotherClass.getModuleCode()) {

            return true;
        
        } else {

            return false;
        }
    }

    public boolean hasSameVenue(Class anotherClass) {

        if (this.venueId == anotherClass.getVenueId()) {

            return true;
        
        } else {

            return false;
        }
    }

    public boolean hasSameInstructor(Class anotherClass) {

        if (this.instructor.equals(anotherClass.getInstructor())) {

            return true;

        } else {

            return false;
        }
    }

    public boolean clashWith(Class otherClass) {

        // if both classes are lectures, make sure that next lecture
        // starts 2 hours after current lecture ends
        if (this.hasSameModule(otherClass) == false) {

            if (checkTimeClash(otherClass)) {

                if (this.hasSameVenue(otherClass)) {

                    return true;

                } else if (this.hasSameInstructor(otherClass)) {

                    return true;

                } else {

                    return false;
                }

            } else {

                return false;
            }
        
        // else if both classes are tutorials, make sure that next tutorial
        // cannot start before current tutorial ends (unless diff TA)
        } else if ((this.duration == 1) && (otherClass.getDuration() == 1)) {

            // if next tutorial starts before current tutorial ends,
            // only allow if next tutorial has different TA and 
            // is in a different venue.
            if (checkTimeClash(otherClass)) {
                
                if (this.hasSameInstructor(otherClass)) {

                    return true;
               
                } else if (this.hasSameVenue(otherClass)){
                   
                    return true;
                
                } else {

                    return false;
                }
            
            } else {

                return false;
            }
        
        // else if next class is a different module, allow
        // next class to start ONLY if it's in a different 
        // venue, with a different instructor
        } else if ((this.duration == 2) && (otherClass.getDuration() == 2)) {
            
            int bufferBetweenStartTime = Math.abs(this.startTime - otherClass.getStartTime());

            if (bufferBetweenStartTime < 2) {
                // System.out.println("Other class is and current class are BOTH lectures of the SAME module and clashes!");
                return true;
            
            } else {

                return false;
            }

        // if current class is Tutorial and other class is Lecture,
        // classes CANNOT clash.
        } else {

            return checkTimeClash(otherClass);
        }

    }

    public boolean checkTimeClash(Class otherClass) {

        int endTime = this.startTime + this.duration;
        int otherEndTime = otherClass.getStartTime() + otherClass.getDuration();

        if ((otherClass.getStartTime() > this.startTime) && (otherClass.getStartTime() < endTime)) {
            // Other class start time is in the middle of current class
            // FALSE
            // System.out.println("[ERROR] Other class starts during current class");
            return true;
        
        } else if ((otherEndTime > this.startTime) && (otherEndTime < endTime)) {
            // Other class starts before current class ends
            // TRUE
            // System.out.println("[ERROR] Other class ends during current class");
            return true;
        
        } else if (otherClass.getStartTime() == this.startTime) {

            // System.out.println("[ERROR] Other class starts same time as current class");
            return true;
        
        } else if (otherEndTime == endTime) {

            // System.out.println("[ERROR] Other class ends same time as current class");
            return true;
        
        } else {

            return false;
        }
    }
}
