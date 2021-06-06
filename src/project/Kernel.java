package project;

import java.util.HashSet;

public class Kernel {
    private HashSet<ExtendedProduction> productions;
    
    public Kernel(){
        this.productions = new HashSet<>();
    }

    public Kernel(HashSet<ExtendedProduction> productions){
        this.productions = productions;
    }

    public void addElement(ExtendedProduction prod){
        productions.add(prod);
    }

    public HashSet<ExtendedProduction> getProductions(){
        return this.productions;
    }

    public Boolean equals(Kernel k){
        if(k.getProductions().size() == this.productions.size()){
            return k.getProductions().containsAll(this.productions);
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("Kernel: \n");
        for(ExtendedProduction ex : this.productions){
            bobTheBuilder.append(ex.toString() + "\n");
        }
        return bobTheBuilder.toString();
    }
}