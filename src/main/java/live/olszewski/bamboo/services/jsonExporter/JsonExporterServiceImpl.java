package live.olszewski.bamboo.services.jsonExporter;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class JsonExporterServiceImpl implements JsonExporterService {

    @Override
    public String export(Object object) {
        Gson gson = new Gson();
        String objectInJson = gson.toJson(object);
        return objectInJson;
    }

}
