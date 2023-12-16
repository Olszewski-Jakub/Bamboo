package live.olszewski.bamboo.panda.config;

import live.olszewski.bamboo.panda.PandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public interface PandaConfigService {


    ResponseEntity<byte[]> downloadPandaConfig(Long pandaId);

}
