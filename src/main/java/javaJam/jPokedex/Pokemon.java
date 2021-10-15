package javaJam.jPokedex;

import java.util.ArrayList;

public class Pokemon {
    private String engName;
    private String jpName;
    private String pokeDexNumber;
    private ArrayList<String> description;

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getJpName() {
        return jpName;
    }

    public void setJpName(String jpName) {
        this.jpName = jpName;
    }

    public String getPokeDexNumber() {
        return pokeDexNumber;
    }

    public void setPokeDexNumber(String pokeDexNumber) {
        this.pokeDexNumber = pokeDexNumber;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }
}
