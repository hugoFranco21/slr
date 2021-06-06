package project;

import java.util.HashSet;

public class Closure {
    private HashSet<ExtendedProduction> productions;
    
    /**
     * Constructor method for the Closure, intializes a new set of productions
     */
    public Closure(){
        productions = new HashSet<>();
    }

    /**
     * This method receives a production and inserts it into the productions set if it 
     * is not there already
     * @param e ExtendedProduction (i.e. E -> . T)
     */
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

    /**
     * Getter for the productions in a Closure
     * @return HashSet<ExtendedProduction>
     */
    public HashSet<ExtendedProduction> getProductions(){
        return this.productions;
    }

    /**
     * This method gets all the productions that have a period before the parameter s element.
     * @param s String - The element to look for in the productions
     * @return HashSet<ExtendedProduction> - A set of all the valid productions
     */
    public HashSet<ExtendedProduction> getAdvanceableProductions(String s){
        HashSet<ExtendedProduction> hs = new HashSet<>();
        for(ExtendedProduction ex : this.productions){
            if(ex.rightOfPeriod().equals(s)){
                ExtendedProduction aux = ExtendedProduction.copy(ex);
                hs.add(aux);
            }
        }
        return hs;
    }

    /**
     * Method to print the Closure in a nice format
     */
    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("Closure: \n");
        for(ExtendedProduction ex : this.productions){
            bobTheBuilder.append(ex.toString() + "\n");
        }
        return bobTheBuilder.toString();
    }

    /**
     * This method returns the Closure in a nice format
     * to be printed in HTML
     * @return String
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