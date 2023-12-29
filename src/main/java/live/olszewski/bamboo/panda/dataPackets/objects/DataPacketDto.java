package live.olszewski.bamboo.panda.dataPackets.objects;

import java.time.LocalDate;
import java.time.LocalTime;

public class DataPacketDto {

    private LocalDate date;
    private String dayOfWeek;
    private LocalTime time;
    private int peopleCount;


    public DataPacketDto() {

    }

    public DataPacketDto(LocalDate date, String dayOfWeek, LocalTime time, int peopleCount) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.peopleCount = peopleCount;
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
}
