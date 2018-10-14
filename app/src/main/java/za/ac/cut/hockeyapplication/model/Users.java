package za.ac.cut.hockeyapplication.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Users implements Serializable {

    private String objectId;
    private String email;
    private String surname;
    private String name;
    private String password;
    private String role;
    private List<Team> teams;
    private List<Match> matches;

    public Users() {
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public HashMap toMap() {
        HashMap user = new HashMap();
        user.put("surname", this.surname);
        user.put("name", this.name);
        user.put("password", this.password);
        user.put("teams", this.teams);
        user.put("matches", this.matches);
        user.put("email", this.email);
        return user;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}