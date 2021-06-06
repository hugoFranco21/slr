package project;

import java.util.LinkedList;

public class ExtendedProduction {
    private String leftSide;
    private LinkedList<String> rightSide;

    /**
     * Constructor for an extended production
     * @param prod The normal production
     */
    public ExtendedProduction(final Production prod){
        this.leftSide = prod.getLeftSide();
        this.rightSide = prod.getRightSide();
        this.rightSide.addFirst(".");
    }

    /**
     * Default constructor for the ExtendedProduction class
     */
    public ExtendedProduction(){
        this.rightSide = new LinkedList<>();
    }

    /**
     * Getter for the left side of an extended production
     * @return String
     */
    public String getLeftSide(){
        return this.leftSide;
    }

    /**
     * Getter for the right side of an extended production
     * @return LinkedList<String>
     */
    public LinkedList<String> getRightSide(){
        return this.rightSide;
    }

    /**
     * Get the index of the period
     * @return Integer
     */
    public Integer getPeriodIndex(){
        return this.rightSide.lastIndexOf(".");
    }

    /**
     * Return true if the period is at the end of the production
     */
    public Boolean isPeriodLast(){
        return this.getPeriodIndex() == this.getRightSide().size() - 1;
    }

    /**
     * Advance the period one position.
     * If the Period is at the end, return the same production
     * @return ExtendedProduction
     */
    public ExtendedProduction advancePeriod() {
        ExtendedProduction eprod = new ExtendedProduction();
        eprod = ExtendedProduction.copy(this);
        if(eprod.isPeriodLast()){
            return eprod;
        } else {
            eprod.getRightSide().add(eprod.getPeriodIndex() + 2, ".");
            eprod.getRightSide().removeFirstOccurrence(".");
            return eprod;
        }
    }

    /**
     * Method to copy the content of an ExtendedProduction to a different object
     * @param ex - ExtendedProduction to be copied
     * @return ExtendedProduction - The copy of the parameter
     */
    public static ExtendedProduction copy(ExtendedProduction ex) {
        ExtendedProduction eprod = new ExtendedProduction();
        eprod.leftSide = ex.getLeftSide();
        for(String s : ex.getRightSide()){
            eprod.rightSide.add(s);
        }
        return eprod;
    }

    /**
     * Return the element at the right of the period.
     * Return null if the period is the last element
     * @return String
     */
    public String rightOfPeriod(){
        if(this.isPeriodLast()) return null;
        return this.getRightSide().get(this.getPeriodIndex() + 1);
    }

    /**
     * Method to get the extended production as a Java String
     */
    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append(leftSide);
        bobTheBuilder.append(" -> ");
        for(String s: rightSide){
            bobTheBuilder.append(s + " ");
        }
        return bobTheBuilder.toString();
    }

}
