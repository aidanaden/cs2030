import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Roster extends KeyableMap<Student> {
    
    Roster(String rosterName) {
        super(rosterName, new HashMap<String, ArrayList<Student>>());
        super.getMap().put(rosterName, new ArrayList<Student>());
    }

    public String getGrade(String studentName, String moduleName, String assessmentName) {

        Optional<String> grade = super.get(studentName).flatMap(x -> x.get(moduleName)).flatMap(x -> x.get(assessmentName)).map(Assessment::getGrade);

        String errorStr = "No such record: " + studentName + " " + moduleName + " " + assessmentName;

        return grade.orElse(errorStr);
    }

    public Roster put(Student newStudent) {

        Map<String, ArrayList<Student>> map = super.getMap();

        ArrayList<Student> existingStudents = map.get(super.getKey());

        existingStudents.add(newStudent);        

        map.put(getKey(), existingStudents);
            
        return this;
    }
}
