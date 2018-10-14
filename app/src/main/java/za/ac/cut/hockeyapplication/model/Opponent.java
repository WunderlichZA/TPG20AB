package za.ac.cut.hockeyapplication.model;


import java.util.HashMap;

public class Opponent {

    private String objectId;
    private String name;

    public String getObjecyId() {
        return objectId;
    }

    public void setObjecyId(String objecyId) {
        this.objectId = objecyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap toMap(){
        HashMap opponent =  new HashMap();
        opponent.put("name", this.name);
        return opponent;
    }
}
