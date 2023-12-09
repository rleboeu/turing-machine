package com.leboeuf.main;

import java.util.ArrayList;
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
    private State currentState;

    public TuringMachine(String[] stateLabels, String[] inputAlphabet, String[] tapeAlphabet, String initialState, String[] finalStates, List<String> transitions) {
        this.states = new HashMap<String, State>();

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
            String cleanTString = transitionString.substring(1, transitionString.length() - 1);
            String[] tokens = cleanTString.split(",");

            // remove whitespace
            for (int i = 0; i < tokens.length; i++) {
                tokens[i] = tokens[i].strip();
            }

            State initial = this.states.get(tokens[ORD_INITIAL_STATE]);
            State next = this.states.get(tokens[ORD_NEXT_STATE]);
            String onSym = tokens[ORD_TRANSIT_ON];
            String writeSym = tokens[ORD_WRITE_SYM];
            EDirection direc = EDirection.fromSymbol(tokens[ORD_MOVE_DIREC]);

            this.states.get(tokens[ORD_INITIAL_STATE]).addTransition(initial, next, onSym, writeSym, direc);
        }

        this.currentState = this.initialState;
    }

    private void printFinalStates() {
        for (State s : this.states.values()) {
            if (s.isAccepting()) {
                System.out.println(s.getLabel());
            }
        }
    }

    public void simulate(ETMType type, String input) {
        this.currentState = this.initialState;
        boolean recogAccept = true;
        
        this.type = type;

        // load the input onto the tape
        List<String> tape = new ArrayList<String>();
        for (int i = 0; i < input.length(); i++) {
            tape.add(input.charAt(i) + "");
        }

        int head = 0;
        int iterations = 0;
        while (this.currentState.isAccepting() == false) {
            if (this.currentState.isAccepting()) {
                break;
            }


            if (head < 0) {
                tape.add(0, Driver.BLANK_SYMBOL);
                head = 0;
            }

            if (head == tape.size()) {
                tape.add(Driver.BLANK_SYMBOL);
                head = tape.size() - 1;
            }

            Transition t = this.currentState.parseSymbol("" + tape.get(head));
            if (t == null) {
                recogAccept = false;
                break;
            }

            tape.set(head, t.getWriteSymbol());
            switch (t.getDirection()) {
                case LEFT:
                    head--;
                    break;
                case RIGHT:
                    head++;
                    break;
                case STAY:
                    break;
                default:
                    break;
            }
            this.currentState = t.getNextState();
            iterations++;
        }

        System.out.println("INPUT: " + input);

        switch (type) {
            case TRANSDUCER:
                System.out.print("OUTPUT: ");
                for (int i = 1; i < tape.size(); i++) {
                    if (tape.get(i).equals(Driver.BLANK_SYMBOL)) {
                        break;
                    }
                    System.out.print(tape.get(i));
                }
                System.out.println();
            case RECOGNIZER:
            default:
                System.out.println(recogAccept ? "ACCEPT" : "REJECT");
                break;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (State state : this.states.values()) {
            sb.append("Label: " + state.getLabel() + "\n");
            
            for (Transition t : state.getTransitions()) {
                sb.append(t.toString() + "\n\n");
            }

            sb.append("\n");
        }

        return sb.toString();
    }

}
