package project;

import java.util.Deque;
import java.util.HashMap;

public class SLRClosure {
    private Integer prodCount = -1;
    private Integer state = -1;
    private HashMap<Integer, ExtendedProduction> productions;
    private HashMap<Integer, Kernel> kernels;
    private HashMap<Integer, Closure> closures;
    private HashMap<Integer, GoTo> goTo;
    private Deque<GoTo> remaining;

    public SLRClosure(Glc g){
        productions = new HashMap<>();
        kernels = new HashMap<>();
        closures = new HashMap<>();
        for(Production prod : g.getProductions().values()){
            this.insertExtendedProduction(new ExtendedProduction(prod));
        }
        this.state++;
        kernel
        createClosure();
    }

    public void insertExtendedProduction(ExtendedProduction prod){
        this.productions.put(++this.prodCount, prod);
    }

    public void insertKernel(Kernel k){
        this.kernels.put(this.state, k);
    }

    public void insertClosures(Closure c){
        this.closures.put(this.state, c);
    }

    public void createClosure(){

    }
    
}