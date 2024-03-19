package live.olszewski.bamboo.space;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_privileges")
public class UserPrivilegeDao {

    @Id
    @SequenceGenerator(
            name = "user_privilege_sequence",
            sequenceName = "user_privilege_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_privilege_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private SpaceDao space;

    private Long userId;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Privilege> privileges;

    // constructors, getters, setters

    public UserPrivilegeDao() {
    }

    public UserPrivilegeDao(Long id, SpaceDao space, Long userId, List<Privilege> privileges) {
        this.id = id;
        this.space = space;
        this.userId = userId;
        this.privileges = privileges;
    }

    public UserPrivilegeDao(SpaceDao space, Long userId, List<Privilege> privileges) {
        this.space = space;
        this.userId = userId;
        this.privileges = privileges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpaceDao getSpace() {
        return space;
    }

    public void setSpace(SpaceDao space) {
        this.space = space;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }


    UserPrivilegeDto toDto() {
        return new UserPrivilegeDto(userId, space.getId(), privileges);
    }
}