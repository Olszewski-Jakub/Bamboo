package live.olszewski.bamboo.panda.objects;

import jakarta.persistence.*;

/**
 * This class represents a Panda device in the database.
 * It includes the ID, UUID, location, name, status, owner, and API key of the device.
 */
@Entity
@Table(name = "panda_device")
public class PandaDao {

    /**
     * The ID of the Panda device.
     */
    @Id
    @SequenceGenerator(
            name = "panda_sequence",
            sequenceName = "panda_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "panda_sequence")
    private Long id;

    /**
     * The UUID of the Panda device.
     */
    private String uuid;

    /**
     * The location of the Panda device.
     */
    private String location;

    /**
     * The name of the Panda device.
     */
    private String name;

    /**
     * The status of the Panda device.
     */
    private Boolean status;

    /**
     * The owner of the Panda device.
     */
    private Long owner;

    /**
     * The API key of the Panda device.
     */
    private String api_key;

    /**
     * Default constructor.
     */
    public PandaDao() {

    }

    /**
     * Constructs a new PandaDao with the provided ID, UUID, location, name, status, owner, and API key.
     *
     * @param id       The ID of the Panda device.
     * @param uuid     The UUID of the Panda device.
     * @param location The location of the Panda device.
     * @param name     The name of the Panda device.
     * @param status   The status of the Panda device.
     * @param owner    The owner of the Panda device.
     * @param api_key  The API key of the Panda device.
     */
    public PandaDao(Long id, String uuid, String location, String name, Boolean status, Long owner, String api_key) {
        this.id = id;
        this.uuid = uuid;
        this.location = location;
        this.name = name;
        this.status = status;
        this.owner = owner;
        this.api_key = api_key;
    }

    /**
     * Constructs a new PandaDao with the provided UUID, location, name, status, and owner.
     * The ID and API key are not included.
     *
     * @param uuid     The UUID of the Panda device.
     * @param location The location of the Panda device.
     * @param name     The name of the Panda device.
     * @param status   The status of the Panda device.
     * @param owner    The owner of the Panda device.
     */
    public PandaDao(String uuid, String location, String name, Boolean status, Long owner) {
        this.uuid = uuid;
        this.location = location;
        this.name = name;
        this.status = status;
        this.owner = owner;
    }

    /**
     * Constructs a new PandaDao with the provided location, name, status, and owner.
     * The ID, UUID, and API key are not included.
     *
     * @param location The location of the Panda device.
     * @param name     The name of the Panda device.
     * @param status   The status of the Panda device.
     * @param owner    The owner of the Panda device.
     */
    public PandaDao(String location, String name, Boolean status, Long owner) {
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

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    @Override
    public String toString() {
        return "PandaDao{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", owner=" + owner +
                ", api_key='" + api_key + '\'' +
                '}';
    }


    /**
     * Generates a string used for UUID generation.
     *
     * @return A string used for UUID generation.
     */
    public String valuesForUuidGeneration() {
        return name + location + owner.toString();
    }

    /**
     * Converts this PandaDao to a PandaDto.
     *
     * @return A PandaDto representing this PandaDao.
     */
    public PandaDto toDto() {
        return new PandaDto(id, uuid, location, name, status, owner, api_key);
    }
}
