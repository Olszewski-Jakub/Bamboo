package live.olszewski.bamboo.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller for handling user-related requests.
 */
@RestController
@RequestMapping(path = "#{apiConfig.userPath}")
@Tag(name = "User", description = "User related endpoints")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for getting all users.
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
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    /**
     * Endpoint for registering a new user.
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
    public void registerNewUser(@RequestBody RegisterUser user) {
        userService.addNewUser(user);
    }

    /**
     * Endpoint for deleting a user.
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
    public void deleteUser(@PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }

    /**
     * Endpoint for getting the details of the current user.
     * @return the details of the current user.
     */
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
    public UserDto currentUserDetails() {
        return userService.currentUserDetails();
    }
}
