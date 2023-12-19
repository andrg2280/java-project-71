package hexlet.code.formaters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String generateStylish(List<Map<String, Object>> listData) {
        StringBuilder sbStylish = new StringBuilder("{\n");
        String dateAdd = "  + %s: %s\n";
        String dateSame = "    %s: %s\n";
        String dateMod = "  - %s: %s\n  + %s: %s\n";
        String dateDel = "  - %s: %s\n";
        for (Map<String, Object> map: listData) {
            var key = map.get("status").toString();
            switch (key) {
                case "added" -> sbStylish.append(String.format(dateAdd, map.get("key"), map.get("newValue")));
                case "unchanged" -> sbStylish.append(String.format(dateSame, map.get("key"), map.get("oldValue")));
                case "changed" -> sbStylish.append(String.format(dateMod, map.get("key"),
                        map.get("oldValue"), map.get("key"), map.get("newValue")));
                case "deleted" -> sbStylish.append(String.format(dateDel, map.get("key"), map.get("oldValue")));
                default ->
                        throw new RuntimeException(key);
            }
        }
        return sbStylish.append("}").toString();
    }
}
