package live.olszewski.bamboo.apiKeys;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * This class represents the API Key data access object (DAO).
 * It maps to the "api_keys" table in the database.
 */
@Entity
@Table(name = "api_keys")
public class ApiKeyDao {
    @Id
    @SequenceGenerator(
            name = "api_keys_sequence",
            sequenceName = "api_keys_sequence",
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

    /**
     * Default constructor for the ApiKeyDao class.
     */
    public ApiKeyDao() {
    }

    /**
     * Constructor for the ApiKeyDao class.
     *
     * @param id     The ID of the API key.
     * @param key    The key of the API key.
     * @param owner  The owner of the API key.
     * @param panda  The panda associated with the API key.
     * @param active The active status of the API key.
     */
    public ApiKeyDao(Long id, String key, Long owner, Long panda, Boolean active) {
        this.id = id;
        this.key = key;
        this.owner = owner;
        this.panda = panda;
        this.active = active;
    }

    /**
     * Constructor for the ApiKeyDao class.
     * @param key The key of the API key.
     * @param owner The owner of the API key.
     * @param panda The panda associated with the API key.
     * @param active The active status of the API key.
     */
    public ApiKeyDao(String key, Long owner, Long panda, Boolean active) {
        this.key = key;
        this.owner = owner;
        this.panda = panda;
        this.active = active;
    }

    /**
     * Getter for the ID of the API key.
     * @return The ID of the API key.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the ID of the API key.
     * @param id The new ID of the API key.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the key of the API key.
     * @return The key of the API key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Setter for the key of the API key.
     * @param key The new key of the API key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Getter for the owner of the API key.
     * @return The owner of the API key.
     */
    public Long getOwner() {
        return owner;
    }

    /**
     * Setter for the owner of the API key.
     * @param owner The new owner of the API key.
     */
    public void setOwner(Long owner) {
        this.owner = owner;
    }

    /**
     * Getter for the panda associated with the API key.
     * @return The panda associated with the API key.
     */
    public Long getPanda() {
        return panda;
    }

    /**
     * Setter for the panda associated with the API key.
     * @param panda The new panda associated with the API key.
     */
    public void setPanda(Long panda) {
        this.panda = panda;
    }

    /**
     * Getter for the active status of the API key.
     * @return The active status of the API key.
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Setter for the active status of the API key.
     * @param active The new active status of the API key.
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Getter for the creation time of the API key.
     * @return The creation time of the API key.
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * Setter for the creation time of the API key.
     * @param created The new creation time of the API key.
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * This method returns a string representation of the API key.
     * @return A string representation of the API key.
     */
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

    /**
     * This method generates a seed for the API key.
     * @return A string representation of the seed for the API key.
     */
    public String getSeed(){
        return owner.toString() + panda + created.toString();
    }

    /**
     * This method converts the API key DAO to a DTO.
     * @return A DTO representation of the API key.
     */
    public ApiKeyDto toDto(){
        return new ApiKeyDto(id,key, owner, panda, created, active);
    }
}