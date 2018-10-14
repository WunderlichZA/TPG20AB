package za.ac.cut.hockeyapplication.model;

import java.io.Serializable;
import java.util.HashMap;

public class Player implements Serializable {

    private String objectId;
    private String surname;
    private String name;
    private MedicalInfo medicalInfo;
    private Team team;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicalInfo getMedicalInfo() {
        return medicalInfo;
    }

    public void setMedicalInfo(MedicalInfo medicalInfo) {
        this.medicalInfo = medicalInfo;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public HashMap toMap() {
        HashMap player = new HashMap();
        player.put("medicalAidInfo", this.medicalInfo);
        player.put("surname", this.surname);
        player.put("team", this.team);
        return player;
    }
}
