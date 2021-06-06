package project;

public class State {
    private Integer numb;
    private Kernel kernel;
    private Closure closure;
    private Boolean isAcceptance = false;

    /**
     * Default constructor for the State class
     */
    public State(){}

    /**
     * Getter for the Kernel of the state
     * @return Kernel
     */
    public Kernel getKernel(){
        return this.kernel;
    }
    
    /**
     * Setter for the kernel
     * @param k - Kernel
     */
    public void setKernel(Kernel k){
        this.kernel = k;
    }

    /**
     * Getter for the Closure of the state
     * @return
     */
    public Closure getClosure(){
        return this.closure;
    }

    /**
     * Setter for the closure
     * @param c -  Closure
     */
    public void setClosure(Closure c){
        this.closure = c;
    }

    /**
     * Getter for the number of the state
     * @return Integer
     */
    public Integer getNumb(){
        return this.numb;
    }

    /**
     * Setter for the number of the state
     * @param numb
     */
    public void setNumber(Integer numb){
        this.numb = numb;
    }

    /**
     * Getter for the is Acceptance flag, this flag is particularly useful for knowing when to call reduce
     * @return Boolean
     */
    public Boolean getIsAcceptance(){
        return this.isAcceptance;
    }

    /**
     * Setter for the isAcceptance flag
     * @param isAcceptance
     */
    public void setIsAcceptance(Boolean isAcceptance){
        this.isAcceptance = isAcceptance;
    }

    /**
     * Comparison method to check if two states are equal
     * @param s - Another state
     * @return Boolean
     */
    public Boolean equals(State s){
        if(this.kernel.getProductions().size() == s.getKernel().getProductions().size()){
            for(ExtendedProduction ex : this.kernel.getProductions()){
                if(!s.getKernel().getProductions().contains(ex))
                    return false;
            }
        }
        return true;
    }

    /**
     * Method to get the state as a java string
     */
    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("State number = " + this.numb.toString() + "\n");
        bobTheBuilder.append((this.kernel != null ? kernel.toString() : "\n"));
        bobTheBuilder.append((this.closure != null ? closure.toString() : "\n"));
        bobTheBuilder.append("This state is " + (this.isAcceptance ? "" : "NOT ") + "of acceptance\n");
        return bobTheBuilder.toString();
    }

    /**
     * Method to format the State for HTML presentation
     * @param isRepeated
     * @return
     */
    public String toHTML(Boolean isRepeated){
        StringBuilder bobTheBuilder = new StringBuilder();
        if(this.isAcceptance){
            bobTheBuilder.append("<td style=\"color: green; border: 1px solid black;\">{" + kernel.toHTML() + "}</td>");
            bobTheBuilder.append("<td style=\"color: blue; border: 1px solid black;\">" + this.numb.toString() + "</td>");
            if(!isRepeated) {
                bobTheBuilder.append("<td style=\"color: green; border: 1px solid black;\">{" + this.closure.toHTML() + "}</td>");
            } else {
                bobTheBuilder.append("<td style=\"border: 1px solid black;\"></td>");
            }
        } else {
            bobTheBuilder.append("<td style=\"border: 1px solid black;\">{" + kernel.toHTML() + "}</td>");
            bobTheBuilder.append("<td style=\"color: blue; border: 1px solid black;\">" + this.numb.toString() + "</td>");
            if(!isRepeated){
                bobTheBuilder.append("<td style=\"border: 1px solid black;\">{" + this.closure.toHTML() + "}</td>");
            } else {
                bobTheBuilder.append("<td style=\"border: 1px solid black;\"></td>");
            }
        }
        return bobTheBuilder.toString();
    }

}