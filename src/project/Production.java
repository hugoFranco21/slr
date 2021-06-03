package project;

import java.util.LinkedList;

import project.exceptions.NotCorrectFormatException;

public class Production {
    private String leftSide;
    private LinkedList<String> rightSide;

    public Production(){
        this.rightSide = new LinkedList<>();
    }

    public Production(String prod) throws NotCorrectFormatException{
        this.rightSide = new LinkedList<>();
        explodeProduction(prod);
        if(this.leftSide == null ||
            this.rightSide.isEmpty()){
            throw new NotCorrectFormatException("Las producciones no están construidas correctamente");
        }
    }

    public void explodeProduction(String production) throws NotCorrectFormatException{
        int i = 0;
        StringBuilder bobTheBuilder = new StringBuilder();
        while(production.charAt(i) != 32){
            bobTheBuilder.append(production.charAt(i));
            i++;
        }
        this.leftSide = bobTheBuilder.toString();
        i++;
        if(production.substring(i, i+1) != "->"){
            throw new NotCorrectFormatException("Las producciones no están construidas correctamente");
        }
        i += 2;
        while(i < production.length()){
            bobTheBuilder.setLength(0);
            while(production.charAt(i) != 32 && i < production.length()){
                bobTheBuilder.append(production.charAt(i));
                i++;
            }
            this.rightSide.add(bobTheBuilder.toString());
            i++;
        }
    }

    @Override
    public String toString() {
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append(leftSide);
        bobTheBuilder.append(" -> ");
        for(String s: rightSide){
            bobTheBuilder.append(s + " ");
        }
        return bobTheBuilder.toString();
    }

    public String getLeftSide(){
        return this.leftSide;
    }

    public LinkedList<String> getRightSide(){
        return this.rightSide;
    }
}