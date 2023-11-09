package live.olszewski.bamboo.pandaDevice.register;

import jakarta.persistence.*;
import live.olszewski.bamboo.uuid.UUIDService;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "panda_device")
public class RegisterPandaDao {

    @Id
    @SequenceGenerator(
            name = "panda_sequence",
            sequenceName = "panda_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "panda_sequence")
    private Long id;
    private String uuid;
    private String location;
    private String name;
    private Boolean status;
    private Long owner;

    public RegisterPandaDao() {

    }

    public RegisterPandaDao(Long id, String uuid, String location, String name, Boolean status, Long owner) {
        this.id = id;
        this.uuid = uuid;
        this.location = location;
        this.name = name;
        this.status = status;
        this.owner = owner;
    }

    public RegisterPandaDao(String uuid, String location, String name, Boolean status, Long owner) {

        this.uuid = uuid;
        this.location = location;
        this.name = name;
        this.status = status;
        this.owner = owner;

    }
    public RegisterPandaDao(String location, String name, Boolean status, Long owner) {
        this.location = location;
        this.name = name;
        this.status = status;
        this.owner = owner;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "RegisterPanda{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", owner=" + owner +
                '}';
    }
}
