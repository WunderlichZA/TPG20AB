package za.ac.cut.hockeyapplication.model;


import java.util.HashMap;
import java.util.List;

public class Team {

    private String ObjectId;
    private String teamName;
    private Users user;
    private List<Match> matches;
    private List<Player> players;
    private String ageGroup;

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
        team.put("coach", this.user);
        team.put("matches", this.matches);
        team.put("name", this.teamName);
        team.put("players", this.players);
        return team;
    }
}
