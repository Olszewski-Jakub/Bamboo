package live.olszewski.bamboo.space;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping(path = "/getUsersAndPrivileges")
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
}
