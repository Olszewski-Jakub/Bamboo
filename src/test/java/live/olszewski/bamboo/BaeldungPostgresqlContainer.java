package live.olszewski.bamboo;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * This class extends the PostgreSQLContainer class from Testcontainers library.
 * It is a singleton class, which means it allows only one instance of itself to be created.
 * This class is used to manage a Docker container for PostgreSQL database for integration tests.
 */
public class BaeldungPostgresqlContainer extends PostgreSQLContainer<BaeldungPostgresqlContainer> {
    // The version of the PostgreSQL Docker image to be used
    private static final String IMAGE_VERSION = "postgres:16";
    // The single instance of this class
    private static BaeldungPostgresqlContainer container;

    /**
     * Private constructor to prevent creating multiple instances.
     * Calls the super constructor with the PostgreSQL Docker image version.
     */
    private BaeldungPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    /**
     * This method returns the single instance of this class.
     * If the instance does not exist, it is created.
     *
     * @return the single instance of BaeldungPostgresqlContainer
     */
    public static BaeldungPostgresqlContainer getInstance() {
        if (container == null) {
            container = new BaeldungPostgresqlContainer();
            container.withDatabaseName("integration-tests-db")
                    .withUsername("postgres")
                    .withPassword("admin")
                    .withInitScript("init.sql");

        }
        return container;
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
    /**
     * This method starts the PostgreSQL Docker container and sets the system properties
     * for the database URL, username, and password.
     */
    @Override
    public void start() {
        super.start();
        container.start();
    }

    /**
     * This method overrides the stop method from the super class.
     * The container is not stopped manually, the JVM handles the shutdown.
     */
    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}