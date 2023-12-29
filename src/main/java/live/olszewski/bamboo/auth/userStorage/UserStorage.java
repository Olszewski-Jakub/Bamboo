package live.olszewski.bamboo.auth.userStorage;

/**
 * This interface defines methods for managing User objects in storage.
 */
public interface UserStorage {

    /**
     * Retrieves the name of the current User object.
     *
     * @return The name of the current User object.
     */
    String getCurrentUserName();

    /**
     * Retrieves the email of the current User object.
     *
     * @return The email of the current User object.
     */
    String getCurrentUserEmail();

    /**
     * Retrieves the UUID of the current User object.
     *
     * @return The UUID of the current User object.
     */
    String getCurrentUserUuid();

    /**
     * Retrieves the ID of the current User object.
     *
     * @return The ID of the current User object.
     */
    Long getCurrentUserId();

    /**
     * Checks if the current User object is an administrator.
     *
     * @return True if the current User object is an administrator, false otherwise.
     */
    Boolean isAdministrator();

    /**
     * Sets the current User object using the provided parameters.
     *
     * @param name            The name of the User object to set as the current User object.
     * @param email           The email of the User object to set as the current User object.
     * @param uuid            The UUID of the User object to set as the current User object.
     * @param isAdministrator The administrator status of the User object to set as the current User object.
     * @param id              The ID of the User object to set as the current User object.
     */
    void setCurrentUser(String name, String email, String uuid, Boolean isAdministrator, Long id);

    /**
     * Clears the current User object.
     */
    void clearCurrentUser();
}