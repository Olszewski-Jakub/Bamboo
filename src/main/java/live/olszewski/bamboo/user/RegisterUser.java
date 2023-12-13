package live.olszewski.bamboo.user;

/**
 * Represents a user registration request.
 */
public class RegisterUser {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String role;

    /**
     * Default constructor.
     */
    public RegisterUser() {
    }

    /**
     * Constructor with all fields.
     */
    public RegisterUser(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the name of the user.
     *
     * @return a String representing the user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name a String containing the user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the surname of the user.
     *
     * @return a String representing the user's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the user.
     *
     * @param surname a String containing the user's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the email of the user.
     *
     * @return a String representing the user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email a String containing the user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the user.
     *
     * @return a String representing the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password a String containing the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
