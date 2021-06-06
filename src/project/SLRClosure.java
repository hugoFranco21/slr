package project;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
                states.get(0).setIsAcceptance(false);
            } else {
                states.get(0).setIsAcceptance(true);
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
            State newState = new State();
            State s = this.states.get(g.getFromState());
            Kernel k = new Kernel(s.getClosure().getAdvanceableProductions(g.getSymbol()));
            for(ExtendedProduction ex : k.getProductions()){
                ex.advancePeriod();
            }
            Integer pS = this.compareKernels(k);
            if(pS != - 1){
                g.setDestinationState(pS);
                updateElementList(g, pS);
            } else {
                newState.setKernel(k);
                createClosure(newState);
                insertState(newState);
                g.setDestinationState(this.state);
                updateElementList(g, this.state);
                for(ExtendedProduction ex : states.get(this.state).getClosure().getProductions()){
                    if(!ex.isPeriodLast()){
                        GoTo go = new GoTo(0, ex.rightOfPeriod());
                        goTo.add(go);
                        remaining.add(go);
                    } else {
                        states.get(this.state).setIsAcceptance(true);
                    }
                }
            }
        }
    }

    public void updateElementList(GoTo g, Integer destination){
        Integer index = this.goTo.indexOf(g);
        this.goTo.get(index).setDestinationState(destination);
    }

    public Integer compareKernels(Kernel k){
        for(Map.Entry<Integer, State> s : this.states.entrySet()){
            if(s.getValue().getKernel().equals(k)){
                return s.getKey();
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        for(Integer i = 0; i < this.state; i++){
            bobTheBuilder.append(this.states.get(i).toString());
            if(i > 0){
                bobTheBuilder.append(this.goTo.get(i - 1));
            }
        }
        return bobTheBuilder.toString();
    }
}