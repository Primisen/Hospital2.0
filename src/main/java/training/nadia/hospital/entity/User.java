package training.nadia.hospital.entity;

public class User {

    private long id;
    private String login;
    private String password;

    private Role role;

    private String name;
    private String surname;

    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRole(int id) {

        if (id == Role.PATIENT.getId()) {
            role = Role.PATIENT;

        } else if (id == Role.DOCTOR.getId()) {
            role = Role.DOCTOR;

        } else if (id == Role.NURSE.getId()) {
            role = Role.NURSE;
        }
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return id == user.id &&
                login.equals(user.login) &&
                name.equals(user.name) &&
                surname.equals(user.surname);
    }

    @Override
    public int hashCode() {

        int result = 17;
        result = 37 * result + (int) id;
        result = 37 * result + (login == null ? 0 : login.hashCode());
        result = 37 * result + (name == null ? 0 : name.hashCode());
        result = 37 * result + (surname == null ? 0 : surname.hashCode());

        return result;
    }
}
