import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class Student extends KeyableMap<Module> {
    
    Student(String studentName) {
        super(studentName, new HashMap<String, ArrayList<Module>>());
        super.getMap().put(studentName, new ArrayList<Module>());
    }

    Student(String studentName, Map<String, ArrayList<Module>> studentHashMap) {
        super(studentName, studentHashMap);
    }

    @Override
    public Student put(Module newModule) {

        Map<String, ArrayList<Module>> map = super.getMap();

        ArrayList<Module> existingModules = map.get(super.getKey());

        existingModules.add(newModule);        

        Collections.sort(existingModules, new ModuleComparator());

        map.put(super.getKey(), existingModules);
            
        return this;
    }
}
