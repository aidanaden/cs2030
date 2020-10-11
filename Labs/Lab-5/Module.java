import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Module extends KeyableMap<Assessment> {

    Module(String moduleName) {
        super(moduleName, new HashMap<String, ArrayList<Assessment>>());
        super.getMap().put(moduleName, new ArrayList<Assessment>());
    }
    
    Module(String moduleName, Map<String, ArrayList<Assessment>> moduleHashMap) {
        super(moduleName, moduleHashMap);
    }

    @Override
    public Module put(Assessment newAssessment) {

        Map<String, ArrayList<Assessment>> map = super.getMap();

        ArrayList<Assessment> existingAssessments = map.get(super.getKey());

        existingAssessments.add(newAssessment);        

        map.put(getKey(), existingAssessments);
            
        return this;
    }
}
