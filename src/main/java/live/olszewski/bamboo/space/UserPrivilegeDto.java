package live.olszewski.bamboo.space;

import java.util.List;

public class UserPrivilegeDto {


    Long userId;
    Long spaceId;
    List<Privilege> privileges;

    public UserPrivilegeDto() {
    }

    public UserPrivilegeDto(Long userId, Long spaceId, List<Privilege> privileges) {
        this.userId = userId;
        this.spaceId = spaceId;
        this.privileges = privileges;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }


}
