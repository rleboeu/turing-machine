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
            run(args[0], args[1]);
        }

    }

    private static void run(String definitionFilepath, String inputFilepath) {
        try {
            // get 'er done
            BufferedReader reader = new BufferedReader(new FileReader(definitionFilepath));
            String line = reader.readLine();
            
            // insult the creator of improper definition file
            if (!line.strip().toLowerCase().equals("tm")) {
                System.out.println("Not a proper definition file, you egg.\nDefinition file must start with 'TM'.");
                reader.close();
                return;
            }

            // we've been trying to reach you about the state of the union
            line = reader.readLine();   // states list
            String[] stateLabels = line.strip().split(",");
            for (int i = 0; i < stateLabels.length; i++) {
                stateLabels[i] = stateLabels[i].strip();
            }

            // useless lines
            line = reader.readLine();   // input alphabet
            line = reader.readLine();   // tape alphabet

            // gotta start somewhere
            line = reader.readLine();   // initial state
            String initialState = line.strip();

            // my blank symbol is 9
            line = reader.readLine();   // blank symbol
            BLANK_SYMBOL = line.strip();

            // yay i'm done
            line = reader.readLine();   // final states
            String[] finalStates = line.strip().split(",");

            // eliminate white space from the gene pool
            for (int i = 0; i < finalStates.length; i++) {
                finalStates[i] = finalStates[i].strip();
            }

            // read transitions (i can't read, personally)
            List<String> transitionStrings = new ArrayList<String>();
            while (true) {
                line = reader.readLine();

                if (line == null) {
                    break;
                }

                transitionStrings.add(line.strip());
            }
            reader.close();

            // IM THE MACHINE
            TuringMachine tm = new TuringMachine(stateLabels, null, null, initialState, finalStates, transitionStrings);

            // input file
            reader = new BufferedReader(new FileReader(inputFilepath));
            line = reader.readLine().strip().toLowerCase();

            ETMType type = ETMType.RECOGNIZER;  // default type
            if (line.equals("transducer")) {
                type = ETMType.TRANSDUCER;
            } else if (line.equals("recognizer")) {
                type = ETMType.RECOGNIZER;
            } else {
                // i spit atchu
                System.out.println("Not a proper input file.");
                reader.close();
                return;
            }

            // Simulate the TM with inputs
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