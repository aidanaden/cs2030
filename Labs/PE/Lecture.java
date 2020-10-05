class Lecture extends Class {

    Lecture(String moduleCode, int classId, String venueId, Instructor instructor, int startTime) {
        super(moduleCode, classId, venueId, startTime, 2, instructor);
    }

    @Override
    public String toString() {

        int endTime = super.getStartTime() + super.getDuration();

        return String.format("%s L%d @ %s", super.getModuleCode(), super.getClassId(), super.getVenueId()) + " [" + super.getInstructor() + String.format("] %d--%d", super.getStartTime(), endTime);
    }
}
