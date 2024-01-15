package live.olszewski.bamboo.panda.dataPacketCollection.objects;

import live.olszewski.bamboo.panda.dataPacketCollection.collection.DataCollectionService;

/**
 * This class represents a data packet received from a Panda device.
 * It includes the UUID of the Panda device and the people count.
 */
public class DataPacketReceive {

    private String pandaUuid;
    private int peopleCount;

    /**
     * Default constructor.
     */
    public DataPacketReceive() {
    }

    /**
     * Constructs a new DataPacketReceive with the provided panda UUID and people count.
     *
     * @param pandaUuid   The UUID of the Panda device.
     * @param peopleCount The people count.
     */
    public DataPacketReceive(String pandaUuid, int peopleCount) {
        this.pandaUuid = pandaUuid;
        this.peopleCount = peopleCount;
    }

    /**
     * Retrieves the UUID of the Panda device.
     *
     * @return The UUID of the Panda device.
     */
    public String getPandaUuid() {
        return pandaUuid;
    }

    /**
     * Sets the UUID of the Panda device.
     *
     * @param pandaUuid The UUID to set.
     */
    public void setPandaUuid(String pandaUuid) {
        this.pandaUuid = pandaUuid;
    }

    /**
     * Retrieves the people count.
     *
     * @return The people count.
     */
    public int getPeopleCount() {
        return peopleCount;
    }

    /**
     * Sets the people count.
     *
     * @param peopleCount The people count to set.
     */
    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    private DataCollectionService dataCollectionService;

    /**
     * Converts this DataPacketReceive to a DataPacketDao.
     *
     * @return A DataPacketDao representing this DataPacketReceive.
     */
    public DataPacketDao toDao() {
        return new DataPacketDao(dataCollectionService.getPandaIdFromUUID(pandaUuid), peopleCount);
    }
}
