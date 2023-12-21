package hexlet.code.formaters;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String generatePlain(List<Map<String, Object>> listData) {
        StringBuilder stringPlain = new StringBuilder();
        String addedDate = "Property '%s' was added with value: %s\n";
        String changedDate = "Property '%s' was updated. From %s to %s\n";
        String deletedDate = "Property '%s' was removed\n";
        for (Map<String, Object> map: listData) {
            var key = map.get("status").toString();
            switch (key) {
                case "added" -> stringPlain.append(String.format(addedDate, map.get("key"),
                        normalize(map.get("newValue"))));
                case "changed" -> stringPlain.append(String.format(changedDate, map.get("key"),
                        normalize(map.get("oldValue")), normalize(map.get("newValue"))));
                case "deleted" -> stringPlain.append(String.format(deletedDate, map.get("key")));
                case "unchanged" -> {
                }
                default ->
                        throw new RuntimeException(key);
            }
        }
        return stringPlain.toString().trim();
    }

    public static Object normalize(Object obj) {
        if (obj instanceof String) {
            return "'" + obj + "'";
        } else if (obj instanceof Map || obj instanceof List) {
            return "[complex value]";
        }
        return String.valueOf(obj);
    }
}
