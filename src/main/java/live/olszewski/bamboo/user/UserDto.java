package live.olszewski.bamboo.user;

/**
 * This class represents a Data Transfer Object (DTO) for the User entity.
 * It is used to transfer data between processes or across network connections.
 * It includes fields for the user's id, UID, name, surname, email, and administrator status.
 */
public class UserDto {

    private Long id;
    private String UID;
    private String name;
    private String surname;
    private String email;
    private Boolean isAdministrator;

    /**
     * Default constructor.
     */
    public UserDto() {

    }

    /**
     * Constructor with all fields.
     *
     * @param id              the id of the user
     * @param UID             the UID of the user
     * @param name            the name of the user
     * @param surname         the surname of the user
     * @param email           the email of the user
     * @param isAdministrator the administrator status of the user
     */
    public UserDto(Long id, String UID, String name, String surname, String email, Boolean isAdministrator) {
        this.id = id;
        this.UID = UID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isAdministrator = isAdministrator;
    }

    /**
     * Getter for id.
     *
     * @return the id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for id.
     *
     * @param id the id of the user
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for UID.
     *
     * @return the UID of the user
     */
    public String getUID() {
        return UID;
    }

    /**
     * Setter for UID.
     *
     * @param UID the UID of the user
     */
    public void setUID(String UID) {
        this.UID = UID;
    }

    /**
     * Getter for name.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for surname.
     *
     * @return the surname of the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for surname.
     *
     * @param surname the surname of the user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Getter for email.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email.
     *
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for administrator status.
     *
     * @return the administrator status of the user
     */
    public Boolean getAdministrator() {
        return isAdministrator;
    }

    /**
     * Setter for administrator status.
     *
     * @param administrator the administrator status of the user
     */
    public void setAdministrator(Boolean administrator) {
        isAdministrator = administrator;
    }

    /**
     * Overrides the toString method to return a string representation of the UserDto object.
     *
     * @return a string representation of the UserDto object
     */
    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", UID='" + UID + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", isAdministrator=" + isAdministrator +
                '}';
    }
}