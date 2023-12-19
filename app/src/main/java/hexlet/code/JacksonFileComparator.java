package hexlet.code;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonFileComparator {
    public static Map<String,Object> getData(String content) throws Exception {
        return parse(content);
    }
    public static Map<String,Object> parse(String content) throws JsonProcessingException {
        return new ObjectMapper().readValue(content, new TypeReference<Map<String,Object>>(){});
    }
    public static List<Map<String, Object>> compare(Map<String, Object> data1, Map<String, Object> data2) {
        List<Map<String, Object>> result = new ArrayList<>();

        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key: keys) {
            Map<String, Object> data = new LinkedHashMap<>();
            if (!data1.containsKey(key) && data2.containsKey(key)) {
                data.put("key", key);
                data.put("newValue", data2.get(key));
                data.put("status", "added");
            } else if (data1.containsKey(key) && !data2.containsKey(key)) {
                data.put("key", key);
                data.put("oldValue", data1.get(key));
                data.put("status", "deleted");
            } else if (data1.containsKey(key) && data2.containsKey(key) && !Objects.equals(data1.get(key),
                    (data2.get(key)))) {
                data.put("key", key);
                data.put("oldValue", data1.get(key));
                data.put("newValue", data2.get(key));
                data.put("status", "changed");
            } else {
                data.put("key", key);
                data.put("oldValue", data1.get(key));
                data.put("status", "unchanged");
            }
            result.add(data);
        }
        return result;
    }
}
