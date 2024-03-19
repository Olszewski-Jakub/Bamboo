package live.olszewski.bamboo.space;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "spaces")
public class SpaceDao {

    @Id
    @SequenceGenerator(
            name = "space_sequence",
            sequenceName = "space_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "space_sequence")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserPrivilegeDao> userPrivileges;

    @ElementCollection
    private List<Long> pandaId;

    private boolean status;

    // constructors, getters, setters

    public SpaceDao() {
    }

    public SpaceDao(Long id, String name, List<UserPrivilegeDao> userPrivileges, List<Long> pandaId, boolean status) {
        this.id = id;
        this.name = name;
        this.userPrivileges = userPrivileges;
        this.pandaId = pandaId;
        this.status = status;
    }

    public SpaceDao(String name, List<UserPrivilegeDao> userPrivileges, List<Long> pandaId, boolean status) {
        this.name = name;
        this.userPrivileges = userPrivileges;
        this.pandaId = pandaId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserPrivilegeDao> getUserPrivileges() {
        return userPrivileges;
    }

    public void setUserPrivileges(List<UserPrivilegeDao> userPrivileges) {
        this.userPrivileges = userPrivileges;
    }

    public List<Long> getPandaId() {
        return pandaId;
    }

    public void setPandaId(List<Long> pandaId) {
        this.pandaId = pandaId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}