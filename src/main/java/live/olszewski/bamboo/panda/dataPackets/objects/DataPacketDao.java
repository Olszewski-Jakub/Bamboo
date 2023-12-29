package live.olszewski.bamboo.panda.dataPackets.objects;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class represents a data packet in the database.
 * It includes the ID, panda ID, date, day of the week, time, and people count.
 */
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

    /**
     * Default constructor.
     */
    public DataPacketDao() {

    }

    /**
     * Constructs a new DataPacketDao with the provided ID, panda ID, date, day of the week, time, and people count.
     *
     * @param id          The ID of the data packet.
     * @param pandaId     The panda ID of the data packet.
     * @param date        The date of the data packet.
     * @param dayOfWeek   The day of the week of the data packet.
     * @param time        The time of the data packet.
     * @param peopleCount The people count of the data packet.
     */
    public DataPacketDao(Long id, Long pandaId, LocalDate date, String dayOfWeek, LocalTime time, int peopleCount) {
        this.id = id;
        this.pandaId = pandaId;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.peopleCount = peopleCount;
    }

    /**
     * Constructs a new DataPacketDao with the provided panda ID, date, day of the week, time, and people count.
     *
     * @param pandaId The panda ID of the data packet.
     * @param date The date of the data packet.
     * @param dayOfWeek The day of the week of the data packet.
     * @param time The time of the data packet.
     * @param peopleCount The people count of the data packet.
     */
    public DataPacketDao(Long pandaId, LocalDate date, String dayOfWeek, LocalTime time, int peopleCount) {
        this.pandaId = pandaId;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.peopleCount = peopleCount;
    }

    /**
     * Constructs a new DataPacketDao with the provided panda ID and people count.
     * The date and time are set to the current date and time.
     *
     * @param pandaId The panda ID of the data packet.
     * @param peopleCount The people count of the data packet.
     */
    public DataPacketDao(Long pandaId, int peopleCount) {
        this.pandaId = pandaId;
        this.peopleCount = peopleCount;
    }

    // Getters and setters omitted for brevity...

    /**
     * Converts this DataPacketDao to a DataPacketDto.
     *
     * @return A DataPacketDto representing this DataPacketDao.
     */
    public DataPacketDto toDto() {
        return new DataPacketDto(date, dayOfWeek, time, peopleCount);
    }
}
