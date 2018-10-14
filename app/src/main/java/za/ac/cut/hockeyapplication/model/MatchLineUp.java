package za.ac.cut.hockeyapplication.model;

import java.util.HashMap;
import java.util.List;

public class MatchLineUp {

    private String objectId;
    private Opponent opponent;
    private Player player;
    private String goals;
    private String position;
    private String teamRating;
    private Match match;
    private List<Player> players;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getTeamRating() {
        return teamRating;
    }

    public void setTeamRating(String teamRating) {
        this.teamRating = teamRating;
    }

    public HashMap toMap(){
        HashMap matchLineUp =  new HashMap();
        matchLineUp.put("goals", this.goals);
        matchLineUp.put("match", this.match);
        matchLineUp.put("playerPosition", this.position);
        matchLineUp.put("players", this.players);
        matchLineUp.put("team_rating", this.teamRating);
        return matchLineUp;
    }
}
