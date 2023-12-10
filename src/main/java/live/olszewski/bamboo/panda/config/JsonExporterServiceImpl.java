package live.olszewski.bamboo.panda.config;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class JsonExporterServiceImpl implements JsonExporterService {

    @Override
    public String export(PandaConfigDto pandaConfigDto) {
        Gson gson = new Gson();
        String customerInJson = gson.toJson(pandaConfigDto);
        return customerInJson;
    }

}
