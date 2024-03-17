package live.olszewski.bamboo.space;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "space_sequence")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "space")
    private List<UserPrivilegesDao> userPrivileges;

    @ElementCollection
    private List<Long> pandaDevices;

    @Column(nullable = false)
    private boolean status;

    public SpaceDao() {
    }

    public SpaceDao(Long id, String name, List<UserPrivilegesDao> userPrivileges, List<Long> pandaDevices, boolean status) {
        this.id = id;
        this.name = name;
        this.userPrivileges = userPrivileges;
        this.pandaDevices = pandaDevices;
        this.status = status;
    }

    public SpaceDao(String name, List<UserPrivilegesDao> userPrivileges, List<Long> pandaDevices, boolean status) {
        this.name = name;
        this.userPrivileges = userPrivileges;
        this.pandaDevices = pandaDevices;
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

    public List<UserPrivilegesDao> getUserPrivileges() {
        return userPrivileges;
    }

    public void setUserPrivileges(List<UserPrivilegesDao> userPrivileges) {
        this.userPrivileges = userPrivileges;
    }

    public List<Long> getPandaDevices() {
        return pandaDevices;
    }

    public void setPandaDevices(List<Long> pandaDevices) {
        this.pandaDevices = pandaDevices;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
