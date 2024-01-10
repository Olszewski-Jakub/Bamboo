package live.olszewski.bamboo.testUtils;

import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.panda.objects.PandaDao;
import live.olszewski.bamboo.panda.register.RegisterPanda;
import live.olszewski.bamboo.user.UserDao;
import live.olszewski.bamboo.user.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Function;

public interface TestUtils {

    void clearAllDatabases();
    void clearUserDatabase();

    void clearPandaDatabase();
    void clearApiKeysDatabase();
    void clearUserStorage();
    boolean areObjectEqual(Object obj1, Object obj2);
    UserDao generateUserDaoWithId(Long id);
    void addUsersToDatabase(int numberOfUsers);

    void setUserStorageByUserId(Long id, boolean administrator);

    RegisterPanda generateRegisterPandaWithId(Long id);

    PandaDao generatePandaDaoWithId(Long id);

    <T> List<T> mapToModelList(List<Object> objectList, Function<Object, T> mapper);


    public Function<Object, UserDto> USER_DTO_FUNCTION = object -> {
        // Convert object to UserDao
        UserDto userDto = new UserDto();
        // Set properties of userDao based on object
        return userDto;
    };

    ApiResponseDto<?> deserialize(ResponseEntity<ApiResponseDto<?>> responseEntity);
}