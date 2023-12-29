package live.olszewski.bamboo.panda.connection.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * This class represents the status of a Panda device in the database.
 * It includes the ID, UUID, last connection time, and status of the device.
 */
@Entity
@Table(name = "panda_status")
public class PandaStatusDao {

    @Id
    @SequenceGenerator(
            name = "panda_status_sequence",
            sequenceName = "panda_status_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "panda_status_sequence")
    private Long id;
    private String UUID;
    private LocalDateTime lastConnection;
    private PandaStatus status;

    /**
     * Default constructor.
     */
    public PandaStatusDao() {
    }

    /**
     * Constructs a new PandaStatusDao with the provided ID, UUID, last connection time, and status.
     *
     * @param id             The ID of the Panda device.
     * @param UUID           The UUID of the Panda device.
     * @param lastConnection The last connection time of the Panda device.
     * @param status         The status of the Panda device.
     */
    public PandaStatusDao(Long id, String UUID, LocalDateTime lastConnection, PandaStatus status) {
        this.id = id;
        this.UUID = UUID;
        this.lastConnection = lastConnection;
        this.status = status;
    }

    /**
     * Constructs a new PandaStatusDao with the provided UUID, last connection time, and status.
     *
     * @param UUID The UUID of the Panda device.
     * @param lastConnection The last connection time of the Panda device.
     * @param status The status of the Panda device.
     */
    public PandaStatusDao(String UUID, LocalDateTime lastConnection, PandaStatus status) {
        this.UUID = UUID;
        this.lastConnection = lastConnection;
        this.status = status;
    }

    /**
     * Retrieves the ID of the Panda device.
     *
     * @return The ID of the Panda device.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the Panda device.
     *
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the UUID of the Panda device.
     *
     * @return The UUID of the Panda device.
     */
    public String getUUID() {
        return UUID;
    }

    /**
     * Sets the UUID of the Panda device.
     *
     * @param UUID The UUID to set.
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    /**
     * Retrieves the last connection time of the Panda device.
     *
     * @return The last connection time of the Panda device.
     */
    public LocalDateTime getLastConnection() {
        return lastConnection;
    }

    /**
     * Sets the last connection time of the Panda device.
     *
     * @param lastConnection The last connection time to set.
     */
    public void setLastConnection(LocalDateTime lastConnection) {
        this.lastConnection = lastConnection;
    }

    /**
     * Retrieves the status of the Panda device.
     *
     * @return The status of the Panda device.
     */
    public PandaStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the Panda device.
     *
     * @param status The status to set.
     */
    public void setStatus(PandaStatus status) {
        this.status = status;
    }

    /**
     * Converts this PandaStatusDao to a PandaStatusDto.
     *
     * @return A PandaStatusDto representing this PandaStatusDao.
     */
    public PandaStatusDto toDto() {
        return new PandaStatusDto(lastConnection, UUID, status);
    }
}