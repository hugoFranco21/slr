package project;

import java.util.LinkedList;

import project.exceptions.NotCorrectFormatException;

public class Production {
    private String leftSide;
    private LinkedList<String> rightSide;

    /**
    * Default constructor of the Production class,
    * initializes the right side as an empty Linked List
    */
    public Production(){
        this.rightSide = new LinkedList<>();
    }

    /**
     * Constructor of the Production class, this method is used to create the auxiliary
     * production StartSymbol'-> StartSymbol
     * @param leftSide String Left side of the production
     * @param rightSide String right side of the production, will be added to a LinkedList
     */
    public Production(String leftSide, String rightSide){
        this.leftSide = leftSide;
        this.rightSide = new LinkedList<>();
        this.rightSide.add(rightSide);
    }

    /**
     * This method creates a copy of the paremeter object, this to avoid
     * side effects
     * @param p - Production to be copied
     * @return Production - A production with the same data
     */
    public static Production copy(Production p){
        Production newProd = new Production();
        newProd.leftSide = p.getLeftSide();
        for(String s : p.getRightSide()){
            newProd.rightSide.add(s);
        }
        return newProd;
    }

    /**
     * Construction of the production class, this method receives the full production from 
     * the input file
     * @param prod The complete production of form S -> A
     * @throws NotCorrectFormatException
     */
    public Production(String prod) throws NotCorrectFormatException{
        this.rightSide = new LinkedList<>();
        explodeProduction(prod);
        if(this.leftSide == null ||
            this.rightSide.isEmpty()){
            throw new NotCorrectFormatException("Las producciones no están construidas correctamente");
        }
    }

    /**
     * This method receives the production of form S -> A from the constructor
     * and divides it into a left side and right side
     * @param production - String
     * @throws NotCorrectFormatException if the production is not in the form S -> A
     */
    public void explodeProduction(String production) throws NotCorrectFormatException{
        int i = 0;
        StringBuilder bobTheBuilder = new StringBuilder();
        while(production.charAt(i) != 32){
            bobTheBuilder.append(production.charAt(i));
            i++;
        }
        this.leftSide = bobTheBuilder.toString();
        i++;
        if(!production.substring(i, i+2).equals("->")){
            throw new NotCorrectFormatException("Las producciones no están construidas correctamente");
        }
        i += 3;
        while(i < production.length()){
            bobTheBuilder.setLength(0);
            while(i < production.length() && production.charAt(i) != 32){
                bobTheBuilder.append(production.charAt(i));
                i++;
            }
            this.rightSide.add(bobTheBuilder.toString());
            i++;
        }
    }

    /**
     * This method prints the production
     * @return String - The production in nice printable form
     */
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

    /**
     * This method returns the left side of the production
     * @return String
     */
    public String getLeftSide(){
        return this.leftSide;
    }

    /**
     * Setter for the left side of the production
     * @param s
     */
    public void setLeftSide(String s){
        this.leftSide = s;
    }

    /**
     * This method returns the right side of the production
     * @return LinkedList<String>
     */
    public LinkedList<String> getRightSide(){
        return this.rightSide;
    }

    /**
     * Setter for the right side of the production
     * @param rightSide
     */
    public void setRightSide(LinkedList<String> rightSide){
        this.rightSide = rightSide;
    }
    
    /**
     * This function gets the elements that are in the right
     * side of the production after a given element. This is useful
     * to find the follows.
     * @param s String the element to use as reference
     * @return String - the element that comes after the input
     */
    public String getElementRightOfSymbol(String s){
        Integer index = this.getRightSide().indexOf(s);
        if(index == this.getRightSide().size() - 1){
            return null;
        } else {
            return this.getRightSide().get(index + 1);
        }
    }

    /**
     * Create a copy of a production from an extended production
     * @param ex ExtendedProduction
     * @return Production
     */
    public static Production copyFromExtended(ExtendedProduction ex){
        Production p = new Production();
        ExtendedProduction origin = ExtendedProduction.copy(ex);
        p.setLeftSide(origin.getLeftSide());
        p.setRightSide(origin.getRightSide());
        p.getRightSide().removeLast();
        return p;
    }
}