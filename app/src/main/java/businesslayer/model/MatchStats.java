package businesslayer.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MatchStats extends RealmObject {

    @PrimaryKey
    private int matchStatsId;
    private String TurnOvers;
    private String Goals;
    private String score;
    private String circlePenetrations;
    private String penaltyCorners;
    private String shotsAtGoal;
    private Team team;

    public int getMatchStatsId() {
        return matchStatsId;
    }

    public void setMatchStatsId(int matchStatsId) {
        this.matchStatsId = matchStatsId;
    }

    public String getTurnOvers() {
        return TurnOvers;
    }

    public void setTurnOvers(String turnOvers) {
        TurnOvers = turnOvers;
    }

    public String getGoals() {
        return Goals;
    }

    public void setGoals(String goals) {
        Goals = goals;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCirclePenetrations() {
        return circlePenetrations;
    }

    public void setCirclePenetrations(String circlePenetrations) {
        this.circlePenetrations = circlePenetrations;
    }

    public String getPenaltyCorners() {
        return penaltyCorners;
    }

    public void setPenaltyCorners(String penaltyCorners) {
        this.penaltyCorners = penaltyCorners;
    }

    public String getShotsAtGoal() {
        return shotsAtGoal;
    }

    public void setShotsAtGoal(String shotsAtGoal) {
        this.shotsAtGoal = shotsAtGoal;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
