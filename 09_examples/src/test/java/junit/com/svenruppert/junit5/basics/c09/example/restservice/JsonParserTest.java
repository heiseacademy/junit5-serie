package junit.com.svenruppert.junit5.basics.c09.example.restservice;

import com.svenruppert.junit5.basics.c09.example.restservice.server.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

  private final JsonParser jsonParser = new JsonParser();

  @Test
  public void testParseJson_withValidJson() {
    String json = "{\"2025-01-20\":-5.0,\"2025-01-21\":-3.5,\"2025-01-22\":-7.8}";

    Map<String, Double> result = jsonParser.parseJson(json);

    assertNotNull(result, "Result should not be null");
    assertEquals(3, result.size(), "The map should contain 3 entries");
    assertEquals(-5.0, result.get("2025-01-20"), "Value for 2025-01-20 should be -5.0");
    assertEquals(-3.5, result.get("2025-01-21"), "Value for 2025-01-21 should be -3.5");
    assertEquals(-7.8, result.get("2025-01-22"), "Value for 2025-01-22 should be -7.8");
  }

  @Test
  public void testParseJson_withEmptyJson() {
    String json = "{}";

    Map<String, Double> result = jsonParser.parseJson(json);

    assertNotNull(result, "Result should not be null");
    assertTrue(result.isEmpty(), "The map should be empty");
  }

  @Test
  public void testParseJson_withMalformedJson() {
    String json = "{\"2025-01-20\":-5.0,\"2025-01-21\":}";

    Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
      jsonParser.parseJson(json);
    });

//    String expectedMessage = "For input string: \"\"";
    String expectedMessage = "Index 1 out of bounds for length 1";
    String actualMessage = exception.getMessage();
    System.out.println("actualMessage = " + actualMessage);
    assertTrue(actualMessage.contains(expectedMessage), "Expected exception message to contain: " + expectedMessage);
  }

  @Test
  public void testParseJson_withSingleEntry() {
    String json = "{\"2025-01-20\":15.0}";

    Map<String, Double> result = jsonParser.parseJson(json);

    assertNotNull(result, "Result should not be null");
    assertEquals(1, result.size(), "The map should contain 1 entry");
    assertEquals(15.0, result.get("2025-01-20"), "Value for 2025-01-20 should be 15.0");
  }

  @Test
  public void testParseJson_withAdditionalSpaces() {
    String json = "{ \"2025-01-20\" : -5.0 , \"2025-01-21\" : -3.5 }";

    Map<String, Double> result = jsonParser.parseJson(json);

    assertNotNull(result, "Result should not be null");
    assertEquals(2, result.size(), "The map should contain 2 entries");
    assertEquals(-5.0, result.get("2025-01-20"), "Value for 2025-01-20 should be -5.0");
    assertEquals(-3.5, result.get("2025-01-21"), "Value for 2025-01-21 should be -3.5");
  }
}
