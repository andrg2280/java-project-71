import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class TestApp {
    private static final String DEFAULT_FORMAT = "stylish";
    private static final String JSON = "json";
    private static final String PLAIN = "plain";
    private static final String STYLISH = "stylish";
    private static String jsonFile1;
    private static String jsonFile2;
    private static String yamlFile1;
    private static String yamlFile2;
    private static String stylishParsingResult;
    private static String jsonParsingResult;
    private static String plainParsingResult;
    @BeforeAll
    public static void beforeAll() throws Exception {
        jsonFile1 = "src/test/resources/file1.json";
        jsonFile2 = "src/test/resources/file2.json";
        yamlFile1 = "src/test/resources/file1.yml";
        yamlFile2 = "src/test/resources/file2.yml";
        stylishParsingResult = Files.readString(Paths.get("src/test/resources/stylishParsingResult"));
        jsonParsingResult = Files.readString(Paths.get("src/test/resources/jsonParsingResult"));
        plainParsingResult = Files.readString(Paths.get("src/test/resources/plainParsingResult"));
    }
    @Test
    public void testDefaultFormat() throws Exception {
        String jsonResult = Differ.generate(jsonFile1, jsonFile2, DEFAULT_FORMAT);
        assertThat(jsonResult).isEqualTo(stylishParsingResult);
        String yamlResult = Differ.generate(yamlFile1, yamlFile2,DEFAULT_FORMAT);
        assertThat(yamlResult).isEqualTo(stylishParsingResult);
    }
    @Test
    public void testStylishFormat() throws Exception {
        String jsonResult = Differ.generate(jsonFile1, jsonFile2, STYLISH);
        assertThat(jsonResult).isEqualTo(stylishParsingResult);
        String yamlResult = Differ.generate(yamlFile1, yamlFile2, STYLISH);
        assertThat(yamlResult).isEqualTo(stylishParsingResult);
    }
    @Test
    public void testJsonFormat() throws Exception {
        String jsonResult = Differ.generate(jsonFile1, jsonFile2, JSON);
        assertThat(jsonResult).isEqualTo(jsonParsingResult);
        String yamlResult = Differ.generate(yamlFile1, yamlFile2, JSON);
        assertThat(yamlResult).isEqualTo(jsonParsingResult);
    }
    @Test
    public void testPlainFormat() throws Exception {
        String jsonResult = Differ.generate(jsonFile1, jsonFile2, PLAIN);
        assertThat(jsonResult).isEqualTo(plainParsingResult);
        String yamlResult = Differ.generate(yamlFile1, yamlFile2, PLAIN);
        assertThat(yamlResult).isEqualTo(plainParsingResult);
    }
}