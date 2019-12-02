package com.webs.beewyka819.two;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    private static final int HALT = 99;
    private static final int ADD = 1;
    private static final int MULTIPLY = 2;

    private static int[] opcodes;

    public static void main(String[] args) {
        try {
            StringBuilder code = new StringBuilder();
            InputStream in = Main.class.getResourceAsStream("/res/code.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine()) != null) {
                code.append(line);
            }
            reader.close();
            
            String[] opString = code.toString().split(",");
            
            opcodes = new int[opString.length];
            for(int i = 0; i < opcodes.length; i++) {
                opcodes[i] = Integer.parseInt(opString[i]);
            }

            int value = 0;
            int valOne = 0;
            int valTwo = 0;

            for(int x = 0; x < 100; x++) {
                for(int y = 0; y < 100; y++) {
                    valTwo = y;
                    value = run(x, y);
                    if(value == 19690720) {
                        break;
                    }
                }
                valOne = x;
                if(value == 19690720) {
                    break;
                }
            }
            if(value != 19690720) {
                System.err.println("Couldn't find value");
            } else {
                System.out.println("Pos 1 = " + valOne + "\nPos 2 = " + valTwo);
                System.out.println("Test: " + runCalc(valOne, valTwo));
            }
        } catch(Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }

    private static int run(int valOne, int valTwo) throws Exception {
        int[] copy = new int[opcodes.length];
        for(int i = 0; i < copy.length; i++) {
            copy[i] = opcodes[i];
        }
        copy[1] = valOne;
        copy[2] = valTwo;
        for(int i = 0; i < copy.length - 3; i += 4) {
            int action = copy[i];
            if(action == HALT) {
                return copy[0];
            } else if(action == ADD) {
                copy[copy[i + 3]] = copy[copy[i + 1]] + copy[copy[i + 2]];
            } else if(action == MULTIPLY) {
                copy[copy[i + 3]] = copy[copy[i + 1]] * copy[copy[i + 2]];
            } else {
                System.err.println("[ERROR] Bad opcode");
            }
        }
        return -1;
    }

    private static int runCalc(int valOne, int valTwo) throws Exception {
        opcodes[1] = valOne;
        opcodes[2] = valTwo;
        for(int i = 0; i < opcodes.length - 3; i += 4) {
            int action = opcodes[i];
            if(action == HALT) {
                return opcodes[0];
            } else if(action == ADD) {
                opcodes[opcodes[i + 3]] = opcodes[opcodes[i + 1]] + opcodes[opcodes[i + 2]];
            } else if(action == MULTIPLY) {
                opcodes[opcodes[i + 3]] = opcodes[opcodes[i + 1]] * opcodes[opcodes[i + 2]];
            } else {
                throw new RuntimeException("[ERROR] Unknown opcode");
            }
        }
        return -1;
    }
}