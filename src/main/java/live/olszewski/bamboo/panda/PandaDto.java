package live.olszewski.bamboo.panda;

public class PandaDto {
    private Long id;
    private String uuid;
    private String location;
    private String name;
    private Boolean status;
    private Long owner;
    private String api_key;

    public PandaDto() {
    }

    public PandaDto(Long id, String uuid, String location, String name, Boolean status, Long owner, String api_key) {
        this.id = id;
        this.uuid = uuid;
        this.location = location;
        this.name = name;
        this.status = status;
        this.owner = owner;
        this.api_key = api_key;
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
}
