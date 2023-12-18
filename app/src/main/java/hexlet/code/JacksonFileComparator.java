package hexlet.code;

import java.util.Map;

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
}
