/open Instructor.java
/open Class.java
/open ClassComparator.java
/open Lecture.java
/open Tutorial.java
/open Schedule.java
Schedule s0 = new Schedule()
s0 = s0.add(new Lecture("CS2030", 1, "LT19", new Instructor("hchia"), 10))
System.out.println(s0)
Schedule s = s0.add(new Tutorial("CS2030", 1, "SR7", new Instructor("tsim"), 11))
System.out.println(s)
s = s.add(new Tutorial("CS2030", 1, "SR7", new Instructor("tsim"), 12))
System.out.println(s)
System.out.println(s0)
s = s.add(new Lecture("CS2030", 2, "LT19", new Instructor("hchia"), 16))
s = s.add(new Lecture("CS2040", 1, "I3-AUD", new Instructor("tanst"), 15))
s = s.add(new Tutorial("CS2030", 4, "SR8", new Instructor("ehan"), 15))
s = s.add(new Tutorial("CS2030", 3, "SR7", new Instructor("dlee"), 15))
s = s.add(new Tutorial("CS2030", 2, "SR7", new Instructor("hchia"), 14))
System.out.println(s)

