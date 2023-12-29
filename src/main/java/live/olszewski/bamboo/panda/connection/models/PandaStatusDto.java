package live.olszewski.bamboo.panda.connection.models;

import java.time.LocalDateTime;

/**
 * This class represents a data transfer object (DTO) for the status of a Panda device.
 * It includes the last connection time, UUID, and status of the device.
 */
public class PandaStatusDto {

    private LocalDateTime lastConnection;
    private String UUID;
    private PandaStatus status;

    /**
     * Default constructor.
     */
    public PandaStatusDto() {

    }

    /**
     * Constructs a new PandaStatusDto with the provided last connection time, UUID, and status.
     *
     * @param lastConnection The last connection time of the Panda device.
     * @param UUID           The UUID of the Panda device.
     * @param status         The status of the Panda device.
     */
    public PandaStatusDto(LocalDateTime lastConnection, String UUID, PandaStatus status) {
        this.lastConnection = lastConnection;
        this.UUID = UUID;
        this.status = status;
    }

    /**
     * Constructs a new PandaStatusDto with the provided UUID and status.
     * The last connection time is set to the current time.
     *
     * @param UUID   The UUID of the Panda device.
     * @param status The status of the Panda device.
     */
    public PandaStatusDto(String UUID, PandaStatus status) {
        this.lastConnection = LocalDateTime.now();
        this.UUID = UUID;
        this.status = status;
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
     * Returns a string representation of the PandaStatusDto.
     *
     * @return A string representation of the PandaStatusDto.
     */
    @Override
    public String toString() {
        return "PandaStatusDto{" +
                "lastConnection=" + lastConnection +
                ", UUID='" + UUID + '\'' +
                ", status=" + status +
                '}';
    }
}