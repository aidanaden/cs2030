import java.util.Comparator;

public class ModuleComparator implements Comparator<Module> {
    
    @Override
    public int compare(Module mod1, Module mod2) {

        int mod1Int = Integer.parseInt(mod1.getKey().substring(2));
        int mod2Int = Integer.parseInt(mod2.getKey().substring(2));

        if (mod1Int < mod2Int) {

            return -1;

        } else {
            
            return 1;
        }
    }
}
