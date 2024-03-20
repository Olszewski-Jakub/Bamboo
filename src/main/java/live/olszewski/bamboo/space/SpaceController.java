package live.olszewski.bamboo.space;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "#{apiConfig.spacePath}")
@Tag(name = "Space", description = "Endpoints related to space")
public class SpaceController {
    @Autowired
    private SpaceService spaceService;

    @PostMapping(path = "/create")
    @Operation(
            description = "Create new space",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> createSpace(String name) {
        return spaceService.createSpace(name);
    }


    @GetMapping(path = "/getUsersAndPrivileges")
    @Operation(
            description = "Get users and their privileges for a space",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> getIUsersAndPrivileges(String spaceId) {
        return spaceService.getIUsersAndPrivileges(spaceId);
    }

    @PostMapping(path = "/addUser")
    @Operation(
            description = "Add user to space",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> addUserToSpace(String spaceId, String userId, String privilege) {
        return spaceService.addUserToSpace(spaceId, userId, privilege);
    }

    @DeleteMapping(path = "/removeUser")
    @Operation(
            description = "Remove user from space",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> removeUserFromSpace(String spaceId, String userId) {
        return spaceService.removeUserFromSpace(spaceId, userId);
    }

    @PatchMapping(path = "/changeUserPrivilege")
    @Operation(
            description = "Change user privilege in space",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> changeUserPrivilege(String spaceId, String userId, String privilege) {
        return spaceService.changeUserPrivilege(spaceId, userId, privilege);
    }

    @DeleteMapping(path = "/delete")
    @Operation(
            description = "Delete space",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> deleteSpace(String spaceId) {
        return spaceService.deleteSpace(spaceId);
    }

    @GetMapping(path = "/getSpaces")
    @Operation(
            description = "Get all spaces",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> getSpaces() {
        return spaceService.getSpaces();
    }

    @GetMapping(path = "/getMySpace")
    @Operation(
            description = "Get space by user id",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> getMySpace() {
        return spaceService.getMySpace();
    }

    @GetMapping(path = "/getSpace")
    @Operation(
            description = "Get space by space id",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> getSpace(String spaceId) {
        return spaceService.getSpace(spaceId);
    }

    @GetMapping(path = "/myPrivileges")
    @Operation(
            description = "Get user privileges for a space",
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
                            description = "Space with this name already exists"
                    )
            }
    )
    public ResponseEntity<ApiResponseDto<?>> myPrivileges(String spaceId) {
        return spaceService.myPrivileges(spaceId);
    }
}
