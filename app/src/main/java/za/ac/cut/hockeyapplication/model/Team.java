package za.ac.cut.hockeyapplication.model;

import java.util.HashMap;
import java.util.List;

public class Team {

    private String ObjectId;
    private String teamName;
    private Users coach;
    private List<Match> matches;
    private List<Player> players;
    private String ageGroup;

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }

    public Users getCoach() {
        return coach;
    }

    public void setCoach(Users coach) {
        this.coach = coach;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public HashMap toMap(){
        HashMap team =  new HashMap();
        team.put("coach", this.coach);
        team.put("matches", this.matches);
        team.put("name", this.teamName);
        team.put("players", this.players);
        return team;
    }
}
