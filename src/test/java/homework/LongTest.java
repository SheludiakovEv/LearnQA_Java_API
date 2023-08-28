package homework;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class LongTest {

    //Задание №10
    @ParameterizedTest
    @ValueSource(strings = {"The", "123456789785421", "The weather is great today, Carl"})
    public void longTestString (String longText){
        Map<String, String> longer = new HashMap<>();
        longer.put("", longText);
        assertTrue(longText.length() > 15);
    }
}
