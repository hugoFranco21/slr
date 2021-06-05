package project;

public class State {
    private Kernel kernel;
    private Closure closure;

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

    public Boolean equals(State s){
        if(this.kernel.getProductions().size() == s.getKernel().getProductions().size()){
            for(ExtendedProduction ex : this.kernel.getProductions()){
                if(!s.getKernel().getProductions().contains(ex))
                    return false;
            }
        }
        return true;
    }

}