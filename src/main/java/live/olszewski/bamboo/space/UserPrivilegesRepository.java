package live.olszewski.bamboo.space;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPrivilegesRepository extends JpaRepository<UserPrivilegeDao, Long> {

    @Query("SELECT s FROM UserPrivilegeDao s WHERE s.space.id = ?1")
    List<UserPrivilegeDao> findBySpaceId(Long spaceId);

    // userPrivilegesRepository.findBySpaceIdAndUserIdAndPrivileges(Long.valueOf(spaceId), requesterId, Privilege.OWNER)
    @Query("SELECT s FROM UserPrivilegeDao s WHERE s.space.id = ?1 AND s.userId = ?2 AND ?3 MEMBER OF s.privileges")
    List<UserPrivilegeDao> findBySpaceIdAndUserIdAndPrivileges(Long spaceId, Long userId, Privilege privilege);

    // (userPrivilegesRepository.findBySpaceIdAndUserId(Long.valueOf(spaceId), Long.valueOf(userId)
    @Query("SELECT s FROM UserPrivilegeDao s WHERE s.space.id = ?1 AND s.userId = ?2")
    List<UserPrivilegeDao> findBySpaceIdAndUserId(Long spaceId, Long userId);


}
