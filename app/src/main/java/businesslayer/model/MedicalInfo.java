package businesslayer.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MedicalInfo extends RealmObject {

    @PrimaryKey
    private int medicalRecordId;
    private String medicalAidName;
    private String medicalAidPlan;
    private String medicalAidNumber;
    private String allergies;
    private String parentOneCellNumber;
    private String parentTwpCellNumber;
    private Player player;

    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
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
}
