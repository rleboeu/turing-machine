package com.leboeuf.main;

public enum EDirection {
    LEFT("L"), RIGHT("R"), STAY("S");

    private String symbol;

    private EDirection(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public static EDirection fromSymbol(String symbol) {
        EDirection direction = LEFT;
        
        switch (symbol) {
            case "R":
            direction = RIGHT;
            break;
            case "S":
            direction = STAY;
            break;
            case "L":
            default:
            break;
        }

        return direction;
    }
}
