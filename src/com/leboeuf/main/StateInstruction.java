package com.leboeuf.main;

public class StateInstruction {
    private State next;
    private EDirection direction;
    private String writeSymbol;

    public StateInstruction(State next, EDirection direction, String writeSymbol) {
        this.next = next;
        this.direction = direction;
        this.writeSymbol = writeSymbol;
    }

    public State getNext() {
        return this.next;
    }

    public EDirection getDirection() {
        return this.direction;
    }

    public String getWriteSymbol() {
        return this.writeSymbol;
    }

}
