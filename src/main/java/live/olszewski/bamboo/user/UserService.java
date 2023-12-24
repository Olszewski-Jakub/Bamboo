package live.olszewski.bamboo.user;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import org.springframework.http.ResponseEntity;


public interface UserService {

    ResponseEntity<ApiResponseDto<?>> getUsers();

    ResponseEntity<ApiResponseDto<?>> addNewUser(RegisterUser registerUser);

    ResponseEntity<ApiResponseDto<?>> currentUserDetails();

    ResponseEntity<ApiResponseDto<?>> deleteUser(Long id);

    Long getPandaOwner();

    Boolean isAdministrator(String email);

    Long getUserId(String email);
    String getUserEmailById(Long id);

    UserDto getUserById(Long id);

}
