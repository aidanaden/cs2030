import java.security.Key;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class KeyableMap<V> implements Keyable {

    private Map<String, ArrayList<V>> map;
    private final String key;

    KeyableMap(String key, Map<String, ArrayList<V>> map) {
        this.key = key;
        this.map = map;
    }

    public Map<String, ArrayList<V>> getMap() {
        return this.map;
    }

    public String getKey() {
        return this.key;
    }

    public abstract V get(String key);
    public abstract KeyableMap<V> put(V item);

    @Override
    public String toString() {

        String formatStr = String.format("%s: {", getKey());

        ArrayList<V> objects = getMap().get(getKey());

        for (int i = 0; i < objects.size(); i++) {

            V ass = objects.get(i);

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
