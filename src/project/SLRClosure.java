package project;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class SLRClosure {
    private Glc grammar;
    private Integer prodCount = -1;
    private Integer state = -1;
    private HashMap<Integer, ExtendedProduction> productions;
    private HashMap<Integer, State> states;
    private LinkedList<GoTo> goTo;
    private Deque<GoTo> remaining;

    public SLRClosure(Glc g){
        this.grammar = g;
        productions = new HashMap<>();
        states = new HashMap<>();
        goTo = new LinkedList<>();
        remaining = new ArrayDeque<>();
        for(Production prod : this.grammar.getProductions().values()){
            this.insertExtendedProduction(new ExtendedProduction(prod));
        }
        State initial = new State();
        Kernel k0 = new Kernel();
        k0.addElement(productions.get(0));
        initial.setKernel(k0);
        createClosure(initial);
        insertState(initial);
        for(ExtendedProduction ex : states.get(0).getClosure().getProductions()){
            if(!ex.isPeriodLast()){
                GoTo go = new GoTo(0, ex.rightOfPeriod());
                goTo.add(go);
                remaining.add(go);
            }
        }
        createSLR();
    }

    public void insertExtendedProduction(ExtendedProduction prod){
        this.productions.put(++this.prodCount, prod);
    }

    public void insertState(State s){
        this.states.put(++this.state, s);
    }

    public void createClosure(State s){
        Kernel k = s.getKernel();
        Closure c = new Closure();
        Integer currentSize, prevSize;
        for(ExtendedProduction ex : k.getProductions()){
            c.insertProduction(ex);
        }
        currentSize = c.getProductions().size();
        prevSize = 0;
        while(prevSize != currentSize){
            prevSize = currentSize;
            for(ExtendedProduction ex : c.getProductions()){
                if(!ex.isPeriodLast() && this.grammar.getNonTerminals().contains(ex.rightOfPeriod())){
                    for(Production p : this.grammar.getProductionStartingWith(ex.rightOfPeriod())){
                        c.insertProduction(new ExtendedProduction(p));
                    }
                }
            }
            currentSize = c.getProductions().size();
        }
        s.setClosure(c);
    }

    public void createSLR(){
        while(!remaining.isEmpty()){
            GoTo g = remaining.removeFirst();
            
        }
    }
}