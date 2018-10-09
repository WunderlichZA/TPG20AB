package businesslayer.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import util.Role;

public class User extends RealmObject {

    @PrimaryKey
    private int userId = 0;
    private String surname;
    private String name;
    private String email;
    private String password;
    private String role;

    public User() {
        userId ++;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
