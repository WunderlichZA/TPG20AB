package businesslayer.model;

import io.realm.annotations.PrimaryKey;

public class CoachTeam {

    @PrimaryKey
    private String coachTeamId;
    private Team team;
    private Users users;
}