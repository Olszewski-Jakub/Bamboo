package live.olszewski.bamboo.user;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;


/**
 * This interface defines methods for managing users.
 */
public interface UserService {

    /**
     * Retrieves all users.
     *
     * @return A ResponseEntity containing a list of all users.
     */
    ResponseEntity<ApiResponseDto<?>> getUsers();

    /**
     * Registers a new user.
     *
     * @param registerUser The RegisterUser object containing the details of the new user.
     * @return A ResponseEntity containing the result of the operation.
     */
    ResponseEntity<ApiResponseDto<?>> addNewUser(RegisterUser registerUser);

    /**
     * Retrieves the details of the current user.
     *
     * @return A ResponseEntity containing the details of the current user.
     */
    ResponseEntity<ApiResponseDto<?>> currentUserDetails();

    /**
     * Deletes a user.
     *
     * @param id The ID of the user to delete.
     * @return A ResponseEntity containing the result of the operation.
     */
    ResponseEntity<ApiResponseDto<?>> deleteUser(Long id);

    /**
     * Retrieves the owner of a Panda device.
     *
     * @return The owner of the Panda device.
     */
    Long getPandaOwner();

    /**
     * Checks if a user is an administrator.
     *
     * @param email The email of the user.
     * @return True if the user is an administrator, false otherwise.
     */
    Boolean isAdministrator(String email);

    /**
     * Retrieves the ID of a user.
     *
     * @param email The email of the user.
     * @return The ID of the user.
     */
    Long getUserId(String email);

    /**
     * Retrieves the email of a user.
     *
     * @param id The ID of the user.
     * @return The email of the user.
     */
    String getUserEmailById(Long id);

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user.
     * @return A UserDto representing the user.
     */
    UserDto getUserById(Long id);

    /**
     * Checks if a user exists.
     *
     * @param id The ID of the user.
     * @return True if the user exists, false otherwise.
     */
    Boolean isValidUserById(Long id);

}