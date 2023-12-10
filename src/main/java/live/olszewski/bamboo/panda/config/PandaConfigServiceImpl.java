package live.olszewski.bamboo.panda.config;

import live.olszewski.bamboo.panda.PandaDao;
import live.olszewski.bamboo.panda.PandaDto;
import live.olszewski.bamboo.panda.PandaRepository;
import live.olszewski.bamboo.panda.ownershipCheck.PandaOwnershipCkeck;
import live.olszewski.bamboo.services.jsonExporter.JsonExporterService;
import live.olszewski.bamboo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PandaConfigServiceImpl implements PandaConfigService {

    private final PandaRepository pandaRepository;
    private final JsonExporterService jsonExporterService;
    private final UserService userService;

    @Autowired
    public PandaConfigServiceImpl(PandaRepository pandaRepository, JsonExporterService jsonExporterService, UserService userService) {
        this.pandaRepository = pandaRepository;
        this.jsonExporterService = jsonExporterService;
        this.userService = userService;
    }

    @Autowired
    PandaOwnershipCkeck pandaOwnershipCkeck;

    private String getOwner(Long id) {
        return userService.getUserById(id);
    }

    private PandaConfigDto getPandaConfigDto(Long pandaId) {

        Optional<PandaDao> optionalPandaDao = pandaRepository.findDeviceById(pandaId);
        if (optionalPandaDao.isEmpty()) {
            throw new IllegalStateException("Device with id " + pandaId + " does not exists");
        }
        PandaDto pandaDto = optionalPandaDao.get().toDto();
        PandaConfigDto pandaConfigDto = pandaDto.toConfigDto();
        pandaConfigDto.setOwner(getOwner(pandaDto.getOwner()));

        return pandaConfigDto;
    }

    @Override
    public ResponseEntity<byte[]> downloadPandaConfig(Long pandaId) {

        if (!pandaOwnershipCkeck.isPandaOwner(pandaId)) {
            throw new IllegalStateException("User is not owner of device");
        }

        PandaConfigDto pandaConfigDto = getPandaConfigDto(pandaId);
        String pandaConfigJson = jsonExporterService.export(pandaConfigDto);
        byte[] pandaConfigJsonBytes = pandaConfigJson.getBytes();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=panda-" + pandaConfigDto.getName()+"-config.json")
                .body(pandaConfigJsonBytes);
    }
}
