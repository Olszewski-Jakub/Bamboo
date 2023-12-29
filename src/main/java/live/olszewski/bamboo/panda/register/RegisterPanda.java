package live.olszewski.bamboo.panda.register;

/**
 * This class represents a Panda device that is being registered.
 * It includes the location and name of the device.
 */
public class RegisterPanda {

    private String location;
    private String name;

    /**
     * Default constructor.
     */
    public RegisterPanda() {
    }

    /**
     * Constructs a new RegisterPanda with the provided location and name.
     *
     * @param location The location of the Panda device.
     * @param name     The name of the Panda device.
     */
    public RegisterPanda(String location, String name) {
        this.location = location;
        this.name = name;
    }

    /**
     * Retrieves the location of the Panda device.
     *
     * @return The location of the Panda device.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the Panda device.
     *
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Retrieves the name of the Panda device.
     *
     * @return The name of the Panda device.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Panda device.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

}