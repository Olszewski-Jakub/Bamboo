package live.olszewski.bamboo.space;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_space_privileges")
public class UserPrivilegesDao {

    @Id
    @SequenceGenerator(
            name = "user_privileges_sequence",
            sequenceName = "user_privileges_sequence",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private SpaceDao space;

    @Column(nullable = false)
    private Long userId;

    @ElementCollection
    @Enumerated(EnumType.STRING) // Specify that the enum values are stored as strings
    private List<Privilege> privileges;

    public UserPrivilegesDao() {
    }

    public UserPrivilegesDao(SpaceDao space, Long userId, List<Privilege> privileges) {
        this.space = space;
        this.userId = userId;
        this.privileges = privileges;
    }

    public UserPrivilegesDao(Long id, SpaceDao space, Long userId, List<Privilege> privileges) {
        this.id = id;
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
}
