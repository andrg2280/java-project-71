package hexlet.code;

import hexlet.code.formaters.JSON;
import hexlet.code.formaters.Stylish;
import hexlet.code.formaters.Plain;

        import java.util.List;
        import java.util.Map;

public class FormatTypeSwitcher {
    public static String dataFormatGenerate(List<Map<String, Object>> listData, String extension) {
        return switch (extension) {
            case "stylish" -> Stylish.generateStylish(listData);
            case "json" -> JSON.generateJSON(listData);
            case "plain" -> Plain.generatePlain(listData);
            default -> throw new IllegalArgumentException("Unknown data extension:" + extension);
        };
    }
}
