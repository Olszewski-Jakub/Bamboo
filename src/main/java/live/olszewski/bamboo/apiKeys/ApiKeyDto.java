package live.olszewski.bamboo.apiKeys;

import java.time.LocalDateTime;

/**
 * This class represents the API Key data transfer object (DTO).
 * It is used to transfer data between processes or systems.
 */
public class ApiKeyDto {

    private Long id;
    private String key;
    private Long owner;
    private Long panda;
    private LocalDateTime created;
    private Boolean active;

    /**
     * Default constructor for the ApiKeyDto class.
     */
    public ApiKeyDto() {
    }

    /**
     * Constructor for the ApiKeyDto class.
     *
     * @param id      The ID of the API key.
     * @param key     The key of the API key.
     * @param owner   The owner of the API key.
     * @param panda   The panda associated with the API key.
     * @param created The creation time of the API key.
     * @param active  The active status of the API key.
     */
    public ApiKeyDto(Long id, String key, Long owner, Long panda, LocalDateTime created, Boolean active) {
        this.id = id;
        this.key = key;
        this.owner = owner;
        this.panda = panda;
        this.created = created;
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
     * This method returns a string representation of the API key DTO.
     * @return A string representation of the API key DTO.
     */
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
