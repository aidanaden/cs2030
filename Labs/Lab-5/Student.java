import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

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

        map.put(super.getKey(), existingModules);
            
        return this;
    }

    @Override
    public Module get(String moduleName) {

        Map<String, ArrayList<Module>> map = super.getMap();

        ArrayList<Module> modules = map.get(super.getKey());

        for (Module mod : modules) {

            if (mod.getKey() == moduleName) {
                return mod;
            }
        }
       
        return null;
    }

    @Override
    public String toString() {

        String formatStr = String.format("%s: {", super.getKey());

        ArrayList<Module> objects = super.getMap().get(super.getKey());

        for (int i = 0; i < objects.size(); i++) {

            Module ass = objects.get(i);

            if (i == 0) {

                formatStr += ass.toString();

            } else {

                formatStr += ", " + ass.toString();
            }
        }

        formatStr += "}";

        return formatStr;
    }
}
