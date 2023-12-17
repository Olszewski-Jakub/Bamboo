package live.olszewski.bamboo.panda.connection.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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


    public PandaStatusDao() {
    }

    public PandaStatusDao(Long id, String UUID, LocalDateTime lastConnection, PandaStatus status) {
        this.id = id;
        this.UUID = UUID;
        this.lastConnection = lastConnection;
        this.status = status;
    }

    public PandaStatusDao(String UUID, LocalDateTime lastConnection, PandaStatus status) {
        this.UUID = UUID;
        this.lastConnection = lastConnection;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public LocalDateTime getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(LocalDateTime lastConnection) {
        this.lastConnection = lastConnection;
    }

    public PandaStatus getStatus() {
        return status;
    }

    public void setStatus(PandaStatus status) {
        this.status = status;
    }

    public PandaStatusDto toDto() {
        return new PandaStatusDto(lastConnection, UUID, status);
    }
}
