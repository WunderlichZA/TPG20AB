package za.ac.cut.hockeyapplication.model;

import java.util.HashMap;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MatchStats {

    private String objectId;
    private String firstHalfTurnOvers;
    private String secondHalfTurnOvers;
    private String firstHalfGoals;
    private String secondHalfGoals;
    private String score;
    private String circlePenetration;
    private String penaltyCorners;
    private String shortsAtGoal;
    private List<Match> matches;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getFirstHalfTurnOvers() {
        return firstHalfTurnOvers;
    }

    public void setFirstHalfTurnOvers(String firstHalfTurnOvers) {
        this.firstHalfTurnOvers = firstHalfTurnOvers;
    }

    public String getSecondHalfTurnOvers() {
        return secondHalfTurnOvers;
    }

    public void setSecondHalfTurnOvers(String secondHalfTurnOvers) {
        this.secondHalfTurnOvers = secondHalfTurnOvers;
    }

    public String getFirstHalfGoals() {
        return firstHalfGoals;
    }

    public void setFirstHalfGoals(String firstHalfGoals) {
        this.firstHalfGoals = firstHalfGoals;
    }

    public String getSecondHalfGoals() {
        return secondHalfGoals;
    }

    public void setSecondHalfGoals(String secondHalfGoals) {
        this.secondHalfGoals = secondHalfGoals;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCirclePenetration() {
        return circlePenetration;
    }

    public void setCirclePenetration(String circlePenetration) {
        this.circlePenetration = circlePenetration;
    }

    public String getPenaltyCorners() {
        return penaltyCorners;
    }

    public void setPenaltyCorners(String penaltyCorners) {
        this.penaltyCorners = penaltyCorners;
    }

    public String getShortsAtGoal() {
        return shortsAtGoal;
    }

    public void setShortsAtGoal(String shortsAtGoal) {
        this.shortsAtGoal = shortsAtGoal;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public HashMap toMap(){
        HashMap matchStats =  new HashMap();
        matchStats.put("circlePenetrations", this.circlePenetration);
        matchStats.put("firstHalfGoals", this.firstHalfGoals);
        matchStats.put("matches", this.matches);
        matchStats.put("penaltyCorners", this.penaltyCorners);
        matchStats.put("score", this.score);
        matchStats.put("secondHalfGoals", this.secondHalfGoals);
        matchStats.put("secondHalfTurnOver", this.secondHalfTurnOvers);
        matchStats.put("shots_at_goal", this.shortsAtGoal);
        return matchStats;
    }
}
