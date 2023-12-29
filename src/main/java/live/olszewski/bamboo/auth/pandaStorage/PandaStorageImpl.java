package live.olszewski.bamboo.auth.pandaStorage;

import live.olszewski.bamboo.apiKeys.ApiKeysService;
import live.olszewski.bamboo.panda.PandaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PandaStorageImpl implements PandaStorage {

    private Long currentId;
    private String currentIUuid;
    private String currentLocation;
    private String currentName;
    private Boolean currentIStatus;
    private Long currentIOwner;
    private String currentIApiKey;

    @Autowired
    private ApiKeysService apiKeysService;

    @Override
    public Long getPandaId() {
        return currentId;
    }

    @Override
    public String getPandaUuid() {
        return currentIUuid;
    }

    @Override
    public String getLocation() {
        return currentLocation;
    }

    @Override
    public String getName() {
        return currentName;
    }

    @Override
    public Boolean getStatus() {
        return currentIStatus;
    }

    @Override
    public Long getOwner() {
        return currentIOwner;
    }

    @Override
    public String getApiKey() {
        return currentIApiKey;
    }


    @Override
    public void setCurrentPanda(String apiKey) {
        PandaDao pandaDao = apiKeysService.getPandaByApiKey(apiKey);
        this.currentId = pandaDao.getId();
        this.currentIUuid = pandaDao.getUuid();
        this.currentLocation = pandaDao.getLocation();
        this.currentName = pandaDao.getName();
        this.currentIStatus = pandaDao.getStatus();
        this.currentIOwner = pandaDao.getOwner();
        this.currentIApiKey = apiKey;
    }

    @Override
    public void clearCurrentPanda() {
        this.currentId = null;
        this.currentIUuid = null;
        this.currentLocation = null;
        this.currentName = null;
        this.currentIStatus = null;
        this.currentIOwner = null;
        this.currentIApiKey = null;
    }
}
