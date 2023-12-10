package live.olszewski.bamboo.panda.config;

public class PandaConfigDto {
    private String uuid;
    private String location;
    private String name;
    private String owner;
    private String api_key;

    public PandaConfigDto() {
    }

    public PandaConfigDto(String uuid, String location, String name, String owner, String api_key) {
        this.uuid = uuid;
        this.location = location;
        this.name = name;
        this.owner = owner;
        this.api_key = api_key;
    }
    public PandaConfigDto(String uuid, String location, String name, String api_key) {
        this.uuid = uuid;
        this.location = location;
        this.name = name;
        this.api_key = api_key;
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




    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
