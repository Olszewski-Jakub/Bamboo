package live.olszewski.bamboo;

import live.olszewski.bamboo.services.uuid.UUIDService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UUIDServiceTest {

    @Autowired
    UUIDService uuidService;

    @Test
    public void generateUUIDFromStringReturnsSameUUIDForSameInput() {
        String input = "testInput";
        UUID uuid1 = uuidService.generateUUIDFromString(input);
        UUID uuid2 = uuidService.generateUUIDFromString(input);
        assertEquals(uuid1, uuid2);
    }

    @Test
    public void generateUUIDFromStringReturnsDifferentUUIDForDifferentInput() {
        String input1 = "testInput1";
        String input2 = "testInput2";
        UUID uuid1 = uuidService.generateUUIDFromString(input1);
        UUID uuid2 = uuidService.generateUUIDFromString(input2);
        assertNotEquals(uuid1, uuid2);
    }

}
