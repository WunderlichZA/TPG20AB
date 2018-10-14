package za.ac.cut.hockeyapplication.model;

import java.io.Serializable;
import java.util.HashMap;

public class MedicalInfo implements Serializable {

    private String objectId;
    private String medicalAidName;
    private String medicalAidPlan;
    private String medicalAidNumber;
    private String allergies;
    private String parentOneCellNumber;
    private String parentTwpCellNumber;
    private Player player;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getMedicalAidName() {
        return medicalAidName;
    }

    public void setMedicalAidName(String medicalAidName) {
        this.medicalAidName = medicalAidName;
    }

    public String getMedicalAidPlan() {
        return medicalAidPlan;
    }

    public void setMedicalAidPlan(String medicalAidPlan) {
        this.medicalAidPlan = medicalAidPlan;
    }

    public String getMedicalAidNumber() {
        return medicalAidNumber;
    }

    public void setMedicalAidNumber(String medicalAidNumber) {
        this.medicalAidNumber = medicalAidNumber;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getParentOneCellNumber() {
        return parentOneCellNumber;
    }

    public void setParentOneCellNumber(String parentOneCellNumber) {
        this.parentOneCellNumber = parentOneCellNumber;
    }

    public String getParentTwpCellNumber() {
        return parentTwpCellNumber;
    }

    public void setParentTwpCellNumber(String parentTwpCellNumber) {
        this.parentTwpCellNumber = parentTwpCellNumber;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public HashMap toMap() {
        HashMap medicalInfo = new HashMap();
        medicalInfo.put("allergies", this.allergies);
        medicalInfo.put("firstParentPhoneNumber", this.parentOneCellNumber);
        medicalInfo.put("medical_aid_number", this.medicalAidNumber);
        medicalInfo.put("medicalAidName", this.medicalAidNumber);
        medicalInfo.put("medicalAidPlan", this.medicalAidPlan);
        medicalInfo.put("player", this.player);
        medicalInfo.put("secondParentPhoneNumber", this.parentTwpCellNumber);
        return medicalInfo;
    }
}
