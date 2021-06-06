package project;

public class GoTo {
    private Integer fromState;
    private String symbol;
    private Integer destinationState;
    private Boolean pointsToPrevious = false;

    /**
     * Default constructor of the GoTo class
     */
    public GoTo(){}

    /**
     * Constructor for the GoTo class
     * @param fromState - The state from which you are moving
     * @param symbol - The symbol that is being read
     */
    public GoTo(Integer fromState, String symbol){
        this.fromState = fromState;
        this.symbol = symbol;
    }

    /**
     * Getter for the from state
     * @return Integer
     */
    public Integer getFromState(){
        return this.fromState;
    }

    /**
     * Getter for the symbol in the GoTo
     * @return String
     */
    public String getSymbol(){
        return this.symbol;
    }

    /**
     * Getter for the state you arrive at
     * @return Integer
     */
    public Integer getDestinationState(){
        return this.destinationState;
    }

    /**
     * Setter for the state you arrive to
     * @param destinationState - Integer
     */
    public void setDestinationState(Integer destinationState){
        this.destinationState = destinationState;
    }

    /**
     * Getter for the pointsToPrevious flags that says
     * if the state you are moving has already been discovered
     * @return Boolean
     */
    public Boolean getPointsToPrevious(){
        return this.pointsToPrevious;
    }

    /**
     * Setter for the pointsToPrevious flag
     * @param points
     */
    public void setPointsToPrevious(Boolean points){
        this.pointsToPrevious = points;
    }

    /**
     * Method to get the GoTo object in a nice format
     */
    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("GoTo(" + this.fromState.toString() + ", " + this.symbol + ") = " + (this.getDestinationState() != null ?  this.getDestinationState().toString() : "") + "\n");
        return bobTheBuilder.toString();
    }

    /**
     * Method to get the GoTo object in a HTML format
     * @return
     */
    public String toHTML(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("<td style=\"border: 1px solid black;\">goto ( " + this.fromState.toString() + ",   " + this.symbol + " )</td>\n");
        return bobTheBuilder.toString();
    }
}
