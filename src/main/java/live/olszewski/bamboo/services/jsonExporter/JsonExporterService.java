package live.olszewski.bamboo.services.jsonExporter;


/**
 * This interface defines a method for exporting an object to a JSON string.
 */
public interface JsonExporterService {

    /**
     * Exports an object to a JSON string.
     *
     * @param object The object to export.
     * @return The JSON string representation of the object.
     */
    String export(Object object);

}