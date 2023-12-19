package hexlet.code.formaters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JSON {
    public static String generateJSON(List<Map<String, Object>> listData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
