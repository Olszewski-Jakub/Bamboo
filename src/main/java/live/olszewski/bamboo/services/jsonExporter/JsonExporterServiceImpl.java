package live.olszewski.bamboo.services.jsonExporter;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class JsonExporterServiceImpl implements JsonExporterService {

    private final Gson gson = new Gson();

    @Override
    public String export(Object object) {
        return gson.toJson(object);
    }
}
