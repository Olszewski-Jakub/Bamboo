package live.olszewski.bamboo;

import live.olszewski.bamboo.panda.config.PandaConfigDto;
import live.olszewski.bamboo.services.jsonExporter.JsonExporterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class JsonExporterTest {
    @Autowired
    JsonExporterService jsonExporterService;

    @Test
    public void exportReturnsCorrectJsonForString() {
        String input = "test";
        String expectedJson = "\"test\"";
        String actualJson = jsonExporterService.export(input);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void exportReturnsCorrectJsonForInteger() {
        Integer input = 123;
        String expectedJson = "123";
        String actualJson = jsonExporterService.export(input);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void exportReturnsCorrectJsonForBoolean() {
        Boolean input = true;
        String expectedJson = "true";
        String actualJson = jsonExporterService.export(input);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void exportReturnsCorrectJsonForPandaConfigDto() {
        PandaConfigDto input = new PandaConfigDto("uuid", "location", "name", "owner", "api_key");
        String expectedJson = "{\"uuid\":\"uuid\",\"location\":\"location\",\"name\":\"name\",\"owner\":\"owner\",\"api_key\":\"api_key\"}";
        String actualJson = jsonExporterService.export(input);
        assertEquals(expectedJson, actualJson);
    }
}
