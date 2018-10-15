package za.ac.cut.hockeyapplication.model;

import java.io.Serializable;
import java.util.HashMap;

public class Match implements Serializable {

    private String objectId;
    private Opponent opponent;
    private Team team;

    public Opponent getOpponent() {
        return opponent;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public HashMap toMap() {
        HashMap match = new HashMap();
        match.put("opponent", this.opponent);
        match.put("team", this.team);
        return match;
    }
}
