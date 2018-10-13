package za.ac.cut.hockeyapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Team extends RealmObject {

    @PrimaryKey
    private int teamId;
    private String teamName;
    private Users coach;
    private String ageGroup;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Users getCoach() {
        return coach;
    }

    public void setCoach(Users coach) {
        this.coach = coach;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }
}
