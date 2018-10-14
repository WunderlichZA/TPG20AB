package za.ac.cut.hockeyapplication.model;

import java.io.Serializable;

public class CoachTeam implements Serializable {

    private String objectId;
    private Team team;
    private Users users;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}