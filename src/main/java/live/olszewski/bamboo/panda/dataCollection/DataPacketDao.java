package live.olszewski.bamboo.panda.dataCollection;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "data_packets")
public class DataPacketDao {

    @Id
    @SequenceGenerator(
            name = "data_packets_sequence",
            sequenceName = "data_packets_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_packets_sequence")
    private Long id;

    private Long pandaId;

    private LocalDateTime created = LocalDateTime.now();

    private int peopleCount;

    public DataPacketDao() {

    }

    public DataPacketDao(Long id, Long pandaId, LocalDateTime created, int peopleCount) {
        this.id = id;
        this.pandaId = pandaId;
        this.created = created;
        this.peopleCount = peopleCount;
    }

    public DataPacketDao(Long pandaId, LocalDateTime created, int peopleCount) {
        this.pandaId = pandaId;
        this.created = created;
        this.peopleCount = peopleCount;
    }

    public DataPacketDao(Long pandaId, int peopleCount) {
        this.pandaId = pandaId;
        this.peopleCount = peopleCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPandaId() {
        return pandaId;
    }


    public void setPandaId(Long pandaId) {
        this.pandaId = pandaId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }
}
