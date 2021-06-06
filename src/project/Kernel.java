package project;

import java.util.HashSet;

public class Kernel {
    private HashSet<ExtendedProduction> productions;
    
    /**
     * Default constructor for the Kernel class
     */
    public Kernel(){
        this.productions = new HashSet<>();
    }

    /**
     * Constructor for the Kernel class, this method receives a HashSet of ExtendedPorduction
     * @param productions HashSet<ExtendedProduction>
     */
    public Kernel(HashSet<ExtendedProduction> productions){
        this.productions = productions;
    }

    /**
     * This method adds a new extended production to the productions set
     * @param prod
     */
    public void addElement(ExtendedProduction prod){
        productions.add(prod);
    }

    /**
     * Getter for the productions HashSet
     * @return HashSet<ExtendedProduction>
     */
    public HashSet<ExtendedProduction> getProductions(){
        return this.productions;
    }

    /**
     * Defininition of an equals method to compare Kernels
     * @param k - another kernel
     * @return Boolean
     */
    public Boolean equals(Kernel k){
        if(k.getProductions().size() == this.productions.size()){
            return k.getProductions().containsAll(this.productions);
        } else {
            return false;
        }
    }

    /**
     * Method to get the kernel as a nice Java String
     */
    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("Kernel: \n");
        for(ExtendedProduction ex : this.productions){
            bobTheBuilder.append(ex.toString() + "\n");
        }
        return bobTheBuilder.toString();
    }

    /**
     * Method to get the Kernel in a nice HTML format
     * @return
     */
    public String toHTML(){
        StringBuilder bobTheBuilder = new StringBuilder();
        for(ExtendedProduction ex : this.productions){
            bobTheBuilder.append(ex.toString() + "; ");
        }
        bobTheBuilder.delete(bobTheBuilder.length() - 2, bobTheBuilder.length());
        return bobTheBuilder.toString();
    }
}