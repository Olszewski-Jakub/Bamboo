package live.olszewski.bamboo.space;

import live.olszewski.bamboo.apiResponse.ApiResponseBuilder;
import live.olszewski.bamboo.apiResponse.ApiResponseDto;
import live.olszewski.bamboo.apiResponse.MessageService;
import live.olszewski.bamboo.auth.userStorage.UserStorage;
import live.olszewski.bamboo.services.uuid.UUIDService;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SpaceServiceImpl implements SpaceService {

    private final UUIDService uuidService;
    private final UserService userService;
    private final ApiResponseBuilder builder;
    private final MessageService messageService;
    private final UserStorage userStorage;

    private final SpaceRepository spaceRepository;

    private final UserPrivilegesRepository userPrivilegesRepository;

    @Autowired
    public SpaceServiceImpl(UUIDService uuidService, UserService userService, ApiResponseBuilder builder, MessageService messageService, UserStorage userStorage, SpaceRepository spaceRepository, UserPrivilegesRepository userPrivilegesRepository) {
        this.uuidService = uuidService;
        this.userService = userService;
        this.builder = builder;
        this.messageService = messageService;
        this.userStorage = userStorage;
        this.spaceRepository = spaceRepository;
        this.userPrivilegesRepository = userPrivilegesRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> createSpace(String name) {

//        // Create space
//        SpaceDao spaceDao = new SpaceDao();
//        spaceDao.setName(name);
//        spaceDao.setStatus(true);
//
//// Create list of user privileges
//        List<UserPrivilegesDao> userPrivileges = new ArrayList<>();
//
//// Create user privileges
//        UserPrivilegesDao userPrivilegesDao = new UserPrivilegesDao();
//        List<Privilege> privileges = new ArrayList<>();
//        privileges.add(Privilege.OWNER);
//        userPrivilegesDao.setUserId(userStorage.getCurrentUserId());
//        userPrivilegesDao.setPrivileges(privileges);
//
//// Set the space for the user privileges
//        userPrivilegesDao.setSpace(spaceDao);
//
//        userPrivileges.add(userPrivilegesDao);
//
//// Set the user privileges for the space
//        spaceDao.setUserPrivileges(userPrivileges);
//
//// Save space
//        spaceRepository.save(spaceDao);

        SpaceDao space = new SpaceDao();
        space.setName(name);
        space.setPandaId(Collections.emptyList());
        space.setStatus(true);

        // Save the space in the database
        space = spaceRepository.save(space);

        // Create UserPrivilege for owner
        UserPrivilegeDao ownerPrivilege = new UserPrivilegeDao();
        ownerPrivilege.setSpace(space);
        ownerPrivilege.setUserId(userStorage.getCurrentUserId());
        ownerPrivilege.setPrivileges(Collections.singletonList(Privilege.OWNER));

        // Save the user privilege in the database
        userPrivilegesRepository.save(ownerPrivilege);


//        userPrivilegesRepository.save(userPrivileges);

        return builder.success().code200(messageService.getMessage("space.created", null), null);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getIUsersAndPrivileges(String spaceId) {

        // Get the user privileges for the space
        List<UserPrivilegeDto> userPrivileges = userPrivilegesRepository.findBySpaceId(Long.valueOf(spaceId)).stream().map(UserPrivilegeDao::toDto).toList();

        return builder.success().code200("messageService.getMessage(space.users.privileges, spaceId)", userPrivileges);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> addUserToSpace(String spaceId, String userId, String privilege) {
        //Convert the string privilege to the enum
        Privilege privilegeEnum = Privilege.valueOf(privilege);
        // Check if user is space owner
        Long requesterId = userStorage.getCurrentUserId();
        if (userPrivilegesRepository.findBySpaceIdAndUserIdAndPrivileges(Long.valueOf(spaceId), requesterId, Privilege.OWNER).isEmpty()) {
            return builder.error().code403(messageService.getMessage("space.not.owner", requesterId));
        }
        // Check if user exists
        if (!userService.isValidUserById(Long.valueOf(userId))) {
            return builder.error().code404(messageService.getMessage("user.not.found.id", userId));
        }
        // Check if user is already in the space
        if (!userPrivilegesRepository.findBySpaceIdAndUserIdAndPrivileges(Long.valueOf(spaceId), Long.valueOf(userId), privilegeEnum).isEmpty()) {
            return builder.error().code409(messageService.getMessage("user.already.in.space", userId, spaceId));
        }
        // Create user privilege
        UserPrivilegeDao userPrivilege = new UserPrivilegeDao();
        userPrivilege.setSpace(spaceRepository.getReferenceById(Long.valueOf(spaceId)));
        userPrivilege.setUserId(Long.valueOf(userId));
        userPrivilege.setPrivileges(Collections.singletonList(privilegeEnum));
        // Save user privilege
        userPrivilegesRepository.save(userPrivilege);
        return builder.success().code200(messageService.getMessage("user.added.to.space", userId, spaceId), null);
    }
}
