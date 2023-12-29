package live.olszewski.bamboo.panda.dataPackets.objects;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private LocalDate date = LocalDate.now();
    private String dayOfWeek = date.getDayOfWeek().toString();
    private LocalTime time = LocalTime.now();
    private int peopleCount;


    public DataPacketDao() {

    }

    public DataPacketDao(Long id, Long pandaId, LocalDate date, String dayOfWeek, LocalTime time, int peopleCount) {
        this.id = id;
        this.pandaId = pandaId;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.peopleCount = peopleCount;
    }

    public DataPacketDao(Long pandaId, LocalDate date, String dayOfWeek, LocalTime time, int peopleCount) {
        this.pandaId = pandaId;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public DataPacketDto toDto() {
        return new DataPacketDto(date, dayOfWeek, time, peopleCount);
    }
}
