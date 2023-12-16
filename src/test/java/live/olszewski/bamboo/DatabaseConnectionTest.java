package live.olszewski.bamboo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void whenConnected_thenReturnsTrue() {
        Boolean isConnected = jdbcTemplate.queryForObject("SELECT 1", Boolean.class);
        assertTrue(isConnected);
    }
}