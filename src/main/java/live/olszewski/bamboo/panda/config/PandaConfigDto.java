package live.olszewski.bamboo.panda.config;

/**
 * This class represents the Data Transfer Object (DTO) for the Panda configuration.
 * It is used to transfer data between processes.
 * It includes fields for UUID, location, name, owner, and API key.
 */
public class PandaConfigDto {
    private String uuid;
    private String location;
    private String name;
    private String owner;
    private String api_key;

    /**
     * Default constructor.
     */
    public PandaConfigDto() {
    }

    /**
     * Constructor with all fields.
     *
     * @param uuid     the UUID of the panda device
     * @param location the location of the panda device
     * @param name     the name of the panda device
     * @param owner    the owner of the panda device
     * @param api_key  the API key of the panda device
     */
    public PandaConfigDto(String uuid, String location, String name, String owner, String api_key) {
        this.uuid = uuid;
        this.location = location;
        this.name = name;
        this.owner = owner;
        this.api_key = api_key;
    }

    /**
     * Constructor without owner field.
     *
     * @param uuid     the UUID of the panda device
     * @param location the location of the panda device
     * @param name     the name of the panda device
     * @param api_key  the API key of the panda device
     */
    public PandaConfigDto(String uuid, String location, String name, String api_key) {
        this.uuid = uuid;
        this.location = location;
        this.name = name;
        this.api_key = api_key;
    }

    /**
     * @return the UUID of the panda device
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the UUID to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the location of the panda device
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the name of the panda device
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the owner of the panda device
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the API key of the panda device
     */
    public String getApi_key() {
        return api_key;
    }

    /**
     * @param api_key the API key to set
     */
    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
