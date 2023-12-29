package live.olszewski.bamboo.panda.dataPackets.objects;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class represents a data transfer object (DTO) for a data packet.
 * It includes the date, day of the week, time, and people count.
 */
public class DataPacketDto {

    private LocalDate date;
    private String dayOfWeek;
    private LocalTime time;
    private int peopleCount;

    /**
     * Default constructor.
     */
    public DataPacketDto() {

    }

    /**
     * Constructs a new DataPacketDto with the provided date, day of the week, time, and people count.
     *
     * @param date        The date of the data packet.
     * @param dayOfWeek   The day of the week of the data packet.
     * @param time        The time of the data packet.
     * @param peopleCount The people count of the data packet.
     */
    public DataPacketDto(LocalDate date, String dayOfWeek, LocalTime time, int peopleCount) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.peopleCount = peopleCount;
    }

    /**
     * Retrieves the date of the data packet.
     *
     * @return The date of the data packet.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the data packet.
     *
     * @param date The date to set.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Retrieves the day of the week of the data packet.
     *
     * @return The day of the week of the data packet.
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets the day of the week of the data packet.
     *
     * @param dayOfWeek The day of the week to set.
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Retrieves the time of the data packet.
     *
     * @return The time of the data packet.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Sets the time of the data packet.
     *
     * @param time The time to set.
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Retrieves the people count of the data packet.
     *
     * @return The people count of the data packet.
     */
    public int getPeopleCount() {
        return peopleCount;
    }

    /**
     * Sets the people count of the data packet.
     *
     * @param peopleCount The people count to set.
     */
    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }
}