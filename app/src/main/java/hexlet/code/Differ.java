package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.DiffereceTree.compare;
import static hexlet.code.parsers.ParserSwitcher.pickParser;


public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        List<Map<String, Object>> listData = compare(getData(filepath1), getData(filepath2));
        return FormatTypeSwitcher.dataFormatGenerate(listData, format);
    }

    public static Path getAbsolutePath(String path) {

        return Paths.get(path).toAbsolutePath().normalize();
    }
    public static String getContent(String path) throws IOException {
        String absolutePath = String.valueOf(getAbsolutePath(path));
        return Files.readString(Path.of(absolutePath));
    }
    public static String getFormat(String filepath) {

        return filepath.substring(filepath.lastIndexOf(".") + 1);
    }
    public static Map<String, Object> getData(String path) throws Exception {
        String content = getContent(path);
        String format = getFormat(path);
        return pickParser(format, content);
    }
}
