class Tutorial extends Class {

    Tutorial(String moduleCode, int classId, String venueId, Instructor instructor, int startTime) {
        super(moduleCode, classId, venueId, startTime, 1, instructor);
    }

    @Override
    public String toString() {

        int endTime = super.getStartTime() + super.getDuration();

        return String.format("%s T%d @ %s [", super.getModuleCode(), super.getClassId(), super.getVenueId()) + super.getInstructor().toString() + String.format("] %d--%d", super.getStartTime(), endTime);
    }
}
