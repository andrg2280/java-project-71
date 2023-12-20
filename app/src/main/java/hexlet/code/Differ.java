package hexlet.code;
import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.parsers.ParserSwitcher;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static hexlet.code.JacksonFileComparator.compare;
import static hexlet.code.parsers.ParserSwitcher.pickParser;

@Command(name = "differ", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Prints the checksum (SHA-256 by default) of a file to STDOUT.")
public class Differ implements Callable<Integer> {
    private static final int SUCCESS = 0;
    private static final int ERROR = 1;
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private static String format = "stylish";
    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format));
            return SUCCESS;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR;
        }
    }
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        List<Map<String, Object>> listData = compare(getData(filepath1), getData(filepath2));
        return FormatTypeSwitcher.dataFormatGenerate(listData, format);
    }

    public static Path getAbsolutePath(String path) {

        return Paths.get(path).toAbsolutePath().normalize();
    }
    public static String getContent(String path) throws IOException {
        return Files.readString(getAbsolutePath(path));
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