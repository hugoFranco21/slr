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
}