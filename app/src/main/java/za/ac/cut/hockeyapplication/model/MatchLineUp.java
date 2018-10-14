package za.ac.cut.hockeyapplication.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class MatchLineUp implements Serializable {

    private String objectId;
    private Opponent opponent;
    private Player player;
    private String goals;
    private String playerPosition;
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

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String position) {
        this.playerPosition = position;
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

    public HashMap toMap() {
        HashMap matchLineUp = new HashMap();
        matchLineUp.put("goals", this.goals);
        matchLineUp.put("match", this.match);
        matchLineUp.put("playerPosition", this.playerPosition);
        matchLineUp.put("players", this.players);
        matchLineUp.put("teamRating", this.teamRating);
        return matchLineUp;
    }
}
