package shoshin.alex.hadoop;

import org.apache.hadoop.io.Text;

import java.util.HashMap;
import java.util.Map;

public class TextPool {
    private static Map<String, Text> textPool;
    static {
        textPool = new HashMap<>();
    }
    public static Text getText(String val) {
        if (!textPool.containsKey(val)) {
            textPool.put(val, new Text(val));
        }
        return textPool.get(val);
    }
}