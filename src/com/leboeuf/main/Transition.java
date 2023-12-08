package com.leboeuf.main;

public class Transition {
    private State fromState;
    private State toState;
    private String transitionOn;
    private String writeSymbol;
    private EDirection moveTo;

    public Transition(State from, State to, String on, String write, EDirection move) {
        this.fromState = from;
        this.toState = to;
        this.transitionOn = on;
        this.writeSymbol = write;
        this.moveTo = move;
    }

    public State getFromState() {
        return this.fromState;
    }

    public State getNextState() {
        return this.toState;
    }

    public String getInputSymbol() {
        return this.transitionOn;
    }

    public String getWriteSymbol() {
        return this.writeSymbol;
    }

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
