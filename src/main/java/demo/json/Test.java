package demo.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenya.1291813139.com
 * on 2017/6/26.
 * Java EE_1702.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Weather weather = new Weather("33","50","12");
        System.out.println(objectMapper.writeValueAsString(weather));


        List<Weather> weathers = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            weathers.add(new Weather("tmp" + i, "hum" +i, "spd" + i));
        }
        System.out.println(objectMapper.writeValueAsString(weathers));

        Weather newWeatheer = objectMapper.readValue("{\"tmp\":\"33\",\"hum\":\"50\",\"spd\":\"12\"}", Weather.class);
    }
}
