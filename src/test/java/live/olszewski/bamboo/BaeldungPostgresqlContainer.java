package live.olszewski.bamboo;

import org.testcontainers.containers.PostgreSQLContainer;

/**
 * This class extends the PostgreSQLContainer class from Testcontainers library.
 * It is a singleton class, which means it allows only one instance of itself to be created.
 * This class is used to manage a Docker container for PostgreSQL database for integration tests.
 */
public class BaeldungPostgresqlContainer extends PostgreSQLContainer<BaeldungPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:16";
    private static BaeldungPostgresqlContainer container;

    private BaeldungPostgresqlContainer() {
        super(IMAGE_VERSION);
        withDatabaseName("");
        withUsername("postgre");
        withPassword("admin");
        withInitScript("init.sql");
    }

    public static BaeldungPostgresqlContainer getInstance() {
        if (container == null) {
            container = new BaeldungPostgresqlContainer();
            container.start();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", getJdbcUrl());
        System.setProperty("DB_USERNAME", getUsername());
        System.setProperty("DB_PASSWORD", getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
