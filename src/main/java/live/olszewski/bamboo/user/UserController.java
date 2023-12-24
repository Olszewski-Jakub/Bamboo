package live.olszewski.bamboo.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling user-related requests.
 */
@RestController
@RequestMapping(path = "#{apiConfig.userPath}")
@Tag(name = "User", description = "User related endpoints")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * Endpoint for getting all users.
     *
     * @return a list of all users.
     */
    @GetMapping
    @Operation(
            description = "Get all users",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    )
            })
    public ResponseEntity<ApiResponseDto<?>> getUsers() {

        return userService.getUsers();
    }

    /**
     * Endpoint for registering a new user.
     *
     * @param user the user to register.
     */
    @PostMapping
    @Operation(
            description = "Register new user",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "User already exists"
                    )
            }
    )
    @Parameter(
            name = "user",
            description = "User to register",
            required = true,
            example = "{\n" +
                    "  \"name\": \"John\",\n" +
                    "  \"surname\": \"Doe\",\n" +
                    "  \"email\": \"john.doe@gmail.com\",\n" +
                    "  \"password\": \"password\"\n" +
                    "}"
    )
    public ResponseEntity<ApiResponseDto<?>> registerNewUser(@RequestBody RegisterUser user) {
        return userService.addNewUser(user);
    }

    /**
     * Endpoint for deleting a user.
     *
     * @param id the id of the user to delete.
     */
    @DeleteMapping(path = "{userId}")
    @Operation(
            description = "Delete user",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "User does not exists"
                    )
            }
    )
    @Parameter(
            name = "userId",
            description = "User id",
            required = true,
            example = "1"
    )
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable("userId") Long id) {
        return userService.deleteUser(id);
    }


    @GetMapping(path = "/current")
    @Operation(
            description = "Get current user details",
            security = @SecurityRequirement(name = "bearerAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Unauthorized"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> currentUserDetails() {
        return userService.currentUserDetails();
    }
}
