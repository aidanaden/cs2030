import java.util.HashMap;
import java.util.Map;

public class Assessment implements Keyable {
    
    private final String key;
    private final Map<String, String> assessmentHashMap;

    Assessment(String assessmentName, String assessmentGrade) {

        this.key = assessmentName;
        this.assessmentHashMap = new HashMap<String, String> ();
        this.assessmentHashMap.put(assessmentName, assessmentGrade);
    }

    public String getGrade() {
        return this.assessmentHashMap.get(getKey());
    }

    public String getKey() {
        return this.key;
    }

    public Assessment put(String newGrade) {
        this.assessmentHashMap.put(getKey(), newGrade);
        return this;
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}", getKey(), getGrade());
    }
}
