package za.ac.cut.hockeyapplication.model;

public class Team {

    private int ObjectId;
    private String teamName;
    private Users coach;
    private String ageGroup;

    public int getObjectId() {
        return ObjectId;
    }

    public void setObjectId(int objectId) {
        ObjectId = objectId;
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
