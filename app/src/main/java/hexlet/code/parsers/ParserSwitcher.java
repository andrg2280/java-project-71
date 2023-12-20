package hexlet.code.parsers;

import java.util.Map;

public class ParserSwitcher {
    public static Map<String, Object> pickParser(String format, String content) {
        switch (format) {
            case "json" -> {
                return JSONParser.parse(content);
            }
            case "yml", "yaml" -> {
                return YAMLParser.parse(content);
            }
            default -> throw new IllegalArgumentException("Unknown format:" + format
                    + ". Try again.");
        }
    }
}