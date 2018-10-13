package za.ac.cut.hockeyapplication.model;

import io.realm.annotations.PrimaryKey;

public class CoachTeam {

    @PrimaryKey
    private String coachTeamId;
    private Team team;
    private businesslayer.model.Users users;
}