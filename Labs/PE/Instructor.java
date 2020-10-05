class Instructor {

    private final String name;

    Instructor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Instructor) {

            Instructor objInstructor = (Instructor) obj;

            if (objInstructor.toString() == this.toString()) {

                return true;

            } else {

                return false;
            }

        } else {

            return false;
        }
    }
}
