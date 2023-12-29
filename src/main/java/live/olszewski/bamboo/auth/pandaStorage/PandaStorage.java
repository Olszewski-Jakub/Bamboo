package live.olszewski.bamboo.auth.pandaStorage;

/**
 * This interface defines methods for managing Panda objects in storage.
 */
public interface PandaStorage {

    /**
     * Retrieves the ID of the current Panda object.
     *
     * @return The ID of the current Panda object.
     */
    Long getPandaId();

    /**
     * Retrieves the UUID of the current Panda object.
     *
     * @return The UUID of the current Panda object.
     */
    String getPandaUuid();

    /**
     * Retrieves the location of the current Panda object.
     *
     * @return The location of the current Panda object.
     */
    String getLocation();

    /**
     * Retrieves the name of the current Panda object.
     *
     * @return The name of the current Panda object.
     */
    String getName();

    /**
     * Retrieves the status of the current Panda object.
     *
     * @return The status of the current Panda object.
     */
    Boolean getStatus();

    /**
     * Retrieves the owner of the current Panda object.
     *
     * @return The owner of the current Panda object.
     */
    Long getOwner();

    /**
     * Retrieves the API key of the current Panda object.
     *
     * @return The API key of the current Panda object.
     */
    String getApiKey();

    /**
     * Sets the current Panda object using the provided API key.
     *
     * @param apiKey The API key of the Panda object to set as the current Panda object.
     */
    void setCurrentPanda(String apiKey);

    /**
     * Clears the current Panda object.
     */
    void clearCurrentPanda();
}
