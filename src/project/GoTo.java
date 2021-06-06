package project;

public class GoTo {
    private Integer fromState;
    private String symbol;
    private Integer destinationState;

    public GoTo(){}

    public GoTo(Integer fromState, String symbol){
        this.fromState = fromState;
        this.symbol = symbol;
    }

    public Integer getFromState(){
        return this.fromState;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public Integer getDestinationState(){
        return this.destinationState;
    }

    public void setDestinationState(Integer destinationState){
        this.destinationState = destinationState;
    }

    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("GoTo(" + this.fromState.toString() + ", " + this.symbol + ") = " + this.getDestinationState().toString() + "\n");
        return bobTheBuilder.toString();
    }
}
