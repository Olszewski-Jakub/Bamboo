package live.olszewski.bamboo.user;

import jakarta.persistence.*;

/**
 * Represents a User entity in the database.
 */
@Entity
@Table(name = "users")
public class UserDao {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_sequence")
    private Long id;
    private String UID;
    private String name;
    private String surname;
    private String email;

    private Boolean isAdministrator = false;

    /**
     * Default constructor.
     */
    public UserDao() {
    }

    /**
     * Constructor with all fields.
     */
    public UserDao(Long id, String UID, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.UID = UID;
        this.email = email;
    }

    /**
     * Constructor with all fields except id.
     */
    public UserDao(String UID, String name, String surname, String email, Boolean isAdministrator) {
        this.UID = UID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.isAdministrator = isAdministrator;
    }

    /**
     * Constructor without id and isAdministrator fields.
     */
    public UserDao(String UID, String name, String surname, String email) {
        this.UID = UID;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
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

    @Override
    public String toString() {
        return "UserDao{" +
                "id=" + id +
                ", UID='" + UID + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Converts this UserDao to a UserDto.
     *
     * @return a UserDto with the same data as this UserDao.
     */
    public UserDto toUserDto() {
        return new UserDto(id, UID, name, surname, email, isAdministrator);
    }
}
