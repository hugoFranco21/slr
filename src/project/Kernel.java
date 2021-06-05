package project;

import java.util.HashSet;

public class Kernel {
    private HashSet<ExtendedProduction> productions;
    
    public Kernel(){
        this.productions = new HashSet<>();
    }

    public void addElement(ExtendedProduction prod){
        productions.add(prod);
    }

    
}