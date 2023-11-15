package live.olszewski.bamboo.user;

public class UserDto {

    private Long id;
    private String UID;
    private String name;
    private String surname;
    private String email;

    private Boolean isAdministrator;

    public UserDto() {

    }

    public UserDto(Long id, String UID, String name, String surname, String email, Boolean isAdministrator) {
        this.id = id;
        this.UID = UID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isAdministrator = isAdministrator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(Boolean administrator) {
        isAdministrator = administrator;
    }
}
