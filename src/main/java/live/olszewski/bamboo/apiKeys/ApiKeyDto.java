package live.olszewski.bamboo.apiKeys;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ApiKeyDto {

    private Long id;
    private String key;
    private Long owner;
    private Long panda;

    private LocalDateTime created;
    private Boolean active ;


    public ApiKeyDto() {
    }

    public ApiKeyDto(Long id, String key, Long owner, Long panda, LocalDateTime created, Boolean active) {
        this.id = id;
        this.key = key;
        this.owner = owner;
        this.panda = panda;
        this.created = created;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public Long getPanda() {
        return panda;
    }

    public void setPanda(Long panda) {
        this.panda = panda;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "ApiKeyDto{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", owner=" + owner +
                ", panda=" + panda +
                ", created=" + created +
                ", active=" + active +
                '}';
    }
}
