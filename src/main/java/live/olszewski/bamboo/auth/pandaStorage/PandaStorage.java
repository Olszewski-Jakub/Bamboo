package live.olszewski.bamboo.auth.pandaStorage;

public interface PandaStorage {

    Long getPandaId();

    String getPandaUuid();

    String getLocation();

    String getName();

    Boolean getStatus();

    Long getOwner();

    String getApiKey();

    void setCurrentPanda(String apiKey);

    void clearCurrentPanda();

}
