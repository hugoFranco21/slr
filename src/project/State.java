package project;

public class State {
    private Kernel kernel;
    private Closure closure;
    private Boolean isAcceptance = false;

    public State(){}

    public Kernel getKernel(){
        return this.kernel;
    }
    
    public void setKernel(Kernel k){
        this.kernel = k;
    }

    public Closure getClosure(){
        return this.closure;
    }

    public void setClosure(Closure c){
        this.closure = c;
    }

    public Boolean getIsAcceptance(){
        return this.isAcceptance;
    }

    public void setIsAcceptance(Boolean isAcceptance){
        this.isAcceptance = isAcceptance;
    }

    public Boolean equals(State s){
        if(this.kernel.getProductions().size() == s.getKernel().getProductions().size()){
            for(ExtendedProduction ex : this.kernel.getProductions()){
                if(!s.getKernel().getProductions().contains(ex))
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append((this.kernel != null ? kernel.toString() : "\n"));
        bobTheBuilder.append((this.closure != null ? closure.toString() : "\n"));
        bobTheBuilder.append("This state is " + (this.isAcceptance ? "" : "NOT ") + "of acceptance\n");
        return bobTheBuilder.toString();
    }

}