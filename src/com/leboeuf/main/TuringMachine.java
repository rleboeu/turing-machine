package com.leboeuf.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuringMachine {
    
    public static final int ORD_INITIAL_STATE = 0;
    public static final int ORD_TRANSIT_ON = 1;
    public static final int ORD_NEXT_STATE = 2;
    public static final int ORD_WRITE_SYM = 3;
    public static final int ORD_MOVE_DIREC = 4;

    private List<String> inputAlphabet;
    private List<String> tapeAlphabet;
    private Map<String, State> states;
    private ETMType type;
    private State initialState;

    public TuringMachine(ETMType type, List<String> stateLabels, List<String> inputAlphabet, List<String> tapeAlphabet, String initialState, List<String> finalStates, List<String> transitions) {
        this.states = new HashMap<String, State>();
        // set type
        this.type = type;

        // create the states
        for (String label : stateLabels) {
            this.states.put(label, new State(label));
        }

        // set the initial and final states
        this.initialState = this.states.get(initialState);

        for (String label : finalStates) {
            this.states.get(label).setAccepting(true);
        }

        // apply transitions
        for (String transitionString : transitions) {
            // remove parentheses
            String cleanTString = transitionString.substring(0, transitionString.length() - 1);
            String[] tokens = cleanTString.split(",");

            State initial = this.states.get(tokens[ORD_INITIAL_STATE]);
            State next = this.states.get(tokens[ORD_NEXT_STATE]);
            String onSym = tokens[ORD_TRANSIT_ON];
            String writeSym = tokens[ORD_WRITE_SYM];
            EDirection direc = EDirection.fromSymbol(tokens[ORD_MOVE_DIREC]);

            this.states.get(tokens[ORD_INITIAL_STATE]).addTransition(initial, next, onSym, writeSym, direc);
        }
    }
    
}
