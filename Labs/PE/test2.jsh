/open Class.java
/open Lecture.java
/open Tutorial.java
Lecture l1 = new Lecture("CS2030", 1, "LT19", new Instructor("hchia"), 10)
Tutorial t1 = new Tutorial("CS2030", 1, "SR7", new Instructor("tsim"), 12)
Tutorial t2 = new Tutorial("CS2030", 2, "SR8", new Instructor("hchia"), 12)
Lecture l2 = new Lecture("CS2040", 1, "LT19", new Instructor("tanst"), 12)
l1.hasSameModule(t1)
l1.hasSameModule(l2)
l1.hasSameInstructor(t1)
l1.hasSameInstructor(t2)
l1.hasSameVenue(l2)
t1.hasSameVenue(t2)

