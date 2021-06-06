package project;

import java.util.HashSet;

public class Closure {
    private HashSet<ExtendedProduction> productions;
    
    public Closure(){
        productions = new HashSet<>();
    }

    public void insertProduction(ExtendedProduction e){
        this.productions.add(e);
    }

    public HashSet<ExtendedProduction> getProductions(){
        return this.productions;
    }

    public HashSet<ExtendedProduction> getAdvanceableProductions(String s){
        HashSet<ExtendedProduction> hs = new HashSet<>();
        for(ExtendedProduction ex : this.productions){
            if(ex.rightOfPeriod().equals(s)){
                hs.add(ex);
            }
        }
        return hs;
    }

    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("Closure: \n");
        for(ExtendedProduction ex : this.productions){
            bobTheBuilder.append(ex.toString() + "\n");
        }
        return bobTheBuilder.toString();
    }
}