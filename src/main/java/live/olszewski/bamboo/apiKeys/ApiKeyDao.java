package live.olszewski.bamboo.apiKeys;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_keys")
public class ApiKeyDao {
    @Id
    @SequenceGenerator(
            name = "api_sequence",
            sequenceName = "api_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "api_sequence")
    private Long id;
    private String key;
    private Long owner;
    private Long panda;

    private LocalDateTime created = LocalDateTime.now();
    private Boolean active = true;

    public ApiKeyDao() {
    }

    public ApiKeyDao(Long id, String key, Long owner, Long panda, Boolean active) {
        this.id = id;
        this.key = key;
        this.owner = owner;
        this.panda = panda;
        this.active = active;
    }

    public ApiKeyDao(String key, Long owner, Long panda, Boolean active) {
        this.key = key;
        this.owner = owner;
        this.panda = panda;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "ApiKeysDao{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", owner=" + owner +
                ", panda='" + panda + '\'' +
                ", created=" + created +
                ", active=" + active +
                '}';
    }

    public String getSeed(){
        return owner.toString() + panda + created.toString();
    }

    public ApiKeyDto toDto(){
        return new ApiKeyDto(id,key, owner, panda, created, active);
    }
}

