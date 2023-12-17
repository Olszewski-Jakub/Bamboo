package live.olszewski.bamboo.panda.connection.models;

import java.time.LocalDateTime;

public class PandaStatusDto {

    private LocalDateTime lastConnection;
    private String UUID;
    private PandaStatus status;

    public PandaStatusDto() {

    }

    public PandaStatusDto(LocalDateTime lastConnection, String UUID, PandaStatus status) {
        this.lastConnection = lastConnection;
        this.UUID = UUID;
        this.status = status;
    }

    public PandaStatusDto(String UUID, PandaStatus status) {
        this.lastConnection = LocalDateTime.now();
        this.UUID = UUID;
        this.status = status;
    }

    public LocalDateTime getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(LocalDateTime lastConnection) {
        this.lastConnection = lastConnection;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public PandaStatus getStatus() {
        return status;
    }

    public void setStatus(PandaStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PandaStatusDto{" +
                "lastConnection=" + lastConnection +
                ", UUID='" + UUID + '\'' +
                ", status=" + status +
                '}';
    }
}
