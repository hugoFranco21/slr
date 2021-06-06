package project;

import java.util.HashSet;

public class Closure {
    private HashSet<ExtendedProduction> productions;
    
    public Closure(){
        productions = new HashSet<>();
    }

    public void insertProduction(ExtendedProduction e){
        Boolean match;
        for(ExtendedProduction ex : this.productions){
            match = false;
            if(ex.getLeftSide().equals(e.getLeftSide())){
                Integer i = 0;
                if(e.getRightSide().size() == ex.getRightSide().size()){
                    for(String s : ex.getRightSide()){
                        if(s.equals(e.getRightSide().get(i))){
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                        i++;
                    }
                    if(match) return;
                } 
            }
        }
        this.productions.add(e);
    }

    public HashSet<ExtendedProduction> getProductions(){
        return this.productions;
    }

    public HashSet<ExtendedProduction> getAdvanceableProductions(String s){
        HashSet<ExtendedProduction> hs = new HashSet<>();
        for(ExtendedProduction ex : this.productions){
            if(ex.rightOfPeriod().equals(s)){
                System.out.println(ex.toString());
                ExtendedProduction aux = ExtendedProduction.copy(ex);
                hs.add(aux);
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