package live.olszewski.bamboo.panda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "#{apiConfig.pandaConfigPath}")
public class PandaConfigController {

    @Autowired
    private PandaConfigService pandaConfigService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPandaConfig(@PathVariable Long id) {
        return pandaConfigService.downloadPandaConfig(id);
    }
}
