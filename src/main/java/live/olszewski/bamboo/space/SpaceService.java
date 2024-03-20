package live.olszewski.bamboo.space;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;

public interface SpaceService {

    ResponseEntity<ApiResponseDto<?>> createSpace(String name);

    ResponseEntity<ApiResponseDto<?>> getIUsersAndPrivileges(String spaceId);

    ResponseEntity<ApiResponseDto<?>> addUserToSpace(String spaceId, String userId, String privilege);

    ResponseEntity<ApiResponseDto<?>> removeUserFromSpace(String spaceId, String userId);

    ResponseEntity<ApiResponseDto<?>> changeUserPrivilege(String spaceId, String userId, String privilege);

    ResponseEntity<ApiResponseDto<?>> deleteSpace(String spaceId);

    ResponseEntity<ApiResponseDto<?>> getSpaces();

    ResponseEntity<ApiResponseDto<?>> getMySpace();

    ResponseEntity<ApiResponseDto<?>> getSpace(String spaceId);

    ResponseEntity<ApiResponseDto<?>> myPrivileges(String spaceId);



}
