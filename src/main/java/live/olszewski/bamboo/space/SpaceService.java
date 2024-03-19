package live.olszewski.bamboo.space;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;

public interface SpaceService {

    ResponseEntity<ApiResponseDto<?>> createSpace(String name);

    ResponseEntity<ApiResponseDto<?>> getIUsersAndPrivileges(String spaceId);

    ResponseEntity<ApiResponseDto<?>> addUserToSpace(String spaceId, String userId, String privilege);

}
