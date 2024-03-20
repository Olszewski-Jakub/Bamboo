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

    @Override
    public ResponseEntity<ApiResponseDto<?>> removeUserFromSpace(String spaceId, String userId) {
        // Check if user is space owner
        Long requesterId = userStorage.getCurrentUserId();
        if (userPrivilegesRepository.findBySpaceIdAndUserIdAndPrivileges(Long.valueOf(spaceId), requesterId, Privilege.OWNER).isEmpty()) {
            return builder.error().code403(messageService.getMessage("space.not.owner", requesterId));
        }
        // Check if user exists
        if (!userService.isValidUserById(Long.valueOf(userId))) {
            return builder.error().code404(messageService.getMessage("user.not.found.id", userId));
        }
        // Check if user is in the space
        List<UserPrivilegeDao> userPrivileges = userPrivilegesRepository.findBySpaceIdAndUserId(Long.valueOf(spaceId), Long.valueOf(userId));
        if (userPrivileges.isEmpty()) {
            return builder.error().code404(messageService.getMessage("user.not.in.space", userId, spaceId));
        }
        // Remove user privileges from the space with specific user ID
        userPrivilegesRepository.deleteAll(userPrivileges);


        return builder.success().code200(messageService.getMessage("user.removed.from.space", userId, spaceId), null);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> changeUserPrivilege(String spaceId, String userId, String privilege) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteSpace(String spaceId) {
        if (spaceRepository.findById(Long.valueOf(spaceId)).isEmpty()) {
            return builder.error().code404(messageService.getMessage("space.not.found.id", spaceId));
        }
        // Is current user administator of the space
        if (userPrivilegesRepository.findBySpaceIdAndUserIdAndPrivileges(Long.valueOf(spaceId), userStorage.getCurrentUserId(), Privilege.OWNER).isEmpty()) {
            return builder.error().code403(messageService.getMessage("space.not.owner", userStorage.getCurrentUserId()));
        }
        // Delete the space
        spaceRepository.deleteById(Long.valueOf(spaceId));
        //Delete all user privileges for the space
        userPrivilegesRepository.deleteBySpaceId(Long.valueOf(spaceId));
        return builder.success().code200(messageService.getMessage("space.deleted", spaceId), null);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getSpaces() {
        //Check if user is an administrator
        if (!userStorage.isAdministrator()) {
            return builder.error().code403(messageService.getMessage("user.not.administrator"));
        }
        //Get all spaces
        List<SpaceDao> spaces = spaceRepository.findAll();
        return builder.success().code200(messageService.getMessage("spaces.found"), spaces);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getMySpace() {
        // Get all spaces by user id
        List<SpaceDao> spaces = spaceRepository.findByUserId(userStorage.getCurrentUserId());
        return builder.success().code200(messageService.getMessage("spaces.found"), spaces);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getSpace(String spaceId) {
        // Get the space by id
        SpaceDao space = spaceRepository.findById(Long.valueOf(spaceId)).orElse(null);
        if (space == null) {
            return builder.error().code404(messageService.getMessage("space.not.found.id", spaceId));
        }
        return builder.success().code200(messageService.getMessage("space.found", spaceId), space);
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> myPrivileges(String spaceId) {
        // Get the user privileges for the space
        List<UserPrivilegeDao> userPrivileges = userPrivilegesRepository.findBySpaceIdAndUserId(Long.valueOf(spaceId), userStorage.getCurrentUserId());
        return builder.success().code200(messageService.getMessage("space.users.privileges", spaceId), userPrivileges);
    }
}
