package live.olszewski.bamboo.panda.dataCollection;

public class DataPacketDto {

    private String pandaUuid;
    private int peopleCount;

    public DataPacketDto() {
    }

    public DataPacketDto(String pandaUuid, int peopleCount) {
        this.pandaUuid = pandaUuid;
        this.peopleCount = peopleCount;
    }

    public String getPandaUuid() {
        return pandaUuid;
    }

    public void setPandaUuid(String pandaUuid) {
        this.pandaUuid = pandaUuid;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    private DataCollectionService dataCollectionService;

    public DataPacketDao toDao() {
        return new DataPacketDao(dataCollectionService.getPandaIdFromUUID(pandaUuid), peopleCount);
    }
}
