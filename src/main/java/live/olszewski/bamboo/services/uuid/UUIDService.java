package live.olszewski.bamboo.services.uuid;

import java.util.UUID;

/**
 * This interface defines a method for generating a UUID from a string.
 */
public interface UUIDService {

    /**
     * Generates a UUID using the provided input string.
     *
     * @param inputString The string used to generate the UUID.
     * @return The generated UUID.
     */
    UUID generateUUIDFromString(String inputString);
}