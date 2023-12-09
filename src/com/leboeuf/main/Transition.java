package com.leboeuf.main;

/**
 * Represents a Transition between two States in a Turing Machine
 */
public class Transition {
    private State fromState;
    private State toState;
    private String transitionOn;
    private String writeSymbol;
    private EDirection moveTo;

    /**
     * Create a Transition between two States
     * @param from The start State
     * @param to The next State
     * @param on The symbol to transition on
     * @param write The symbol to write on transition
     * @param move The direction to move the head
     */
    public Transition(State from, State to, String on, String write, EDirection move) {
        this.fromState = from;
        this.toState = to;
        this.transitionOn = on;
        this.writeSymbol = write;
        this.moveTo = move;
    }

    /**
     * Get the start State
     * @return the start State
     */
    public State getFromState() {
        return this.fromState;
    }

    /**
     * Get the destination State
     * @return the destination State
     */
    public State getNextState() {
        return this.toState;
    }

    /**
     * Get the symbol that triggers the Transition
     * @return the symbol that triggers the Transition
     */
    public String getInputSymbol() {
        return this.transitionOn;
    }

    /**
     * Get the symbol that is written to the tape on transition
     * @return the symbol that is written to the tape on transition
     */
    public String getWriteSymbol() {
        return this.writeSymbol;
    }

    /**
     * Get the direction that the head should move on transition
     * @return the direction that the head should move on transition
     */
    public EDirection getDirection() {
        return this.moveTo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("From: ")
            .append(this.fromState.getLabel())
            .append("\nTo: ")
            .append(this.toState.getLabel())
            .append("\nOn: ")
            .append(this.transitionOn)
            .append("\nWriting: ")
            .append(this.writeSymbol)
            .append("\nMoving: ")
            .append(moveTo.getSymbol());

        return sb.toString();
    }

}
