package com.leboeuf.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static String BLANK_SYMBOL = "_";

    private static final String USAGE = "program <tm_description> <tm_input>";

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println(USAGE);
        } else {
            start(args[0], args[1]);
        }

    }

    private static void start(String definitionFilepath, String inputFilepath) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(definitionFilepath));
            String line = reader.readLine();
            
            if (!line.strip().toLowerCase().equals("tm")) {
                System.out.println("Not a proper definition file.");
                return;
            }

            line = reader.readLine();   // states list
            String[] stateLabels = line.strip().split(",");
            for (int i = 0; i < stateLabels.length; i++) {
                stateLabels[i] = stateLabels[i].strip();
            }

            line = reader.readLine();   // input alphabet
            line = reader.readLine();   // tape alphabet

            line = reader.readLine();   // initial state
            String initialState = line.strip();

            line = reader.readLine();   // blank symbol
            BLANK_SYMBOL = line.strip();

            line = reader.readLine();   // final states
            String[] finalStates = line.strip().split(",");

            for (int i = 0; i < finalStates.length; i++) {
                finalStates[i] = finalStates[i].strip();
            }

            // read transitions
            List<String> transitionStrings = new ArrayList<String>();
            while (true) {
                line = reader.readLine();

                if (line == null) {
                    break;
                }

                transitionStrings.add(line.strip());
            }
            reader.close();

            // Create TM
            TuringMachine tm = new TuringMachine(stateLabels, null, null, initialState, finalStates, transitionStrings);

            reader = new BufferedReader(new FileReader(inputFilepath));
            line = reader.readLine();
            ETMType type = line.strip().toLowerCase().equals("transducer") ? ETMType.TRANSDUCER : ETMType.RECOGNIZER;

            line = reader.readLine();
            while (line != null) {
                tm.simulate(type, line.strip());
                line = reader.readLine();
                System.out.println();
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}