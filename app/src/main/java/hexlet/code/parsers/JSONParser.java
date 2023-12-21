package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JSONParser {
    public static Map<String, Object> parse(String content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content, new TypeReference<>() { });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
