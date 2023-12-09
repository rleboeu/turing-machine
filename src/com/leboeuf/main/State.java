package com.leboeuf.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a State of a Turing Machine
 */
public class State {
    
    private String label;
    private boolean isAccepting;

    private Map<String, Transition> transitions;

    /**
     * Create a new instance of a State
     * @param label The label of the State
     */
    public State(String label) {
        this.transitions = new HashMap<String, Transition>();
        this.setLabel(label);
    }

    /**
     * Return a Transition for the machine based on a given input symbol
     * @param symbol Input symbol
     * @return Transition for the given input symbol
     */
    public Transition parseSymbol(String symbol) {
        return this.transitions.get(symbol);
    }

    /**
     * Add a Transition to the State for a specific input symbol
     * @param from The origin State of the Transition
     * @param to The destination State of the Transition
     * @param on The symbol to transition on
     * @param write The symbol to write to tape during transition
     * @param move The direction to move the head after transition
     */
    public void addTransition(State from, State to, String on, String write, EDirection move) {
        this.transitions.put(on, new Transition(from, to, on, write, move));
    }

    /**
     * Return a list of the State's Transitions
     * @return a list of Transitions
     */
    public List<Transition> getTransitions() {
        List<Transition> trans = new ArrayList<Transition>();
        for (Transition t : this.transitions.values()) {
            trans.add(t);
        }
        return trans;
    }

    /**
     * Get the label of the State
     * @return The label of the State
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Set the label of the State
     * @param newLabel The new label of the State
     */
    public void setLabel(String newLabel) {
        this.label = newLabel;
    }

    /**
     * Get whether or not the State is accepting
     * @return True iff accepting state, false otherwise
     */
    public boolean isAccepting() {
        return this.isAccepting;
    }

    /**
     * Set whether not not the State is accepting
     * @param isAccepting
     */
    public void setAccepting(boolean isAccepting) {
        this.isAccepting = isAccepting;
    }

    @Override
    public String toString() {
        return this.getLabel();
    }

}
