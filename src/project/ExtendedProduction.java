package project;

import java.util.LinkedList;

public class ExtendedProduction {
    private String leftSide;
    private LinkedList<String> rightSide;

    /**
     * Constructor for an extended production
     * @param prod The normal production
     */
    public ExtendedProduction(Production prod){
        this.leftSide = prod.getLeftSide();
        this.rightSide = prod.getRightSide();
        this.rightSide.addFirst(".");
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
        ExtendedProduction eprod;
        try {
            eprod =  (ExtendedProduction) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
        if(eprod.isPeriodLast()){
            return eprod;
        } else {
            eprod.getRightSide().add(eprod.getPeriodIndex() + 1, ".");
            eprod.getRightSide().removeFirstOccurrence(".");
            return eprod;
        }
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
}
