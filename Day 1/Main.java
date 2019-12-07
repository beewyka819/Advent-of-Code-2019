package com.webs.beewyka819.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> modules = new ArrayList<Integer>();
        
        try(InputStream in = Main.class.getResourceAsStream("/res/modules.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                    String line;
                    while((line = reader.readLine()) != null) {
                        modules.add(Integer.parseInt(line));
                    }
        }

        int sum = 0;
        for(int module : modules) {
            int additionalFuel = module / 3 - 2;
            while(additionalFuel > 0) {
                sum += additionalFuel;
                additionalFuel = additionalFuel / 3 - 2;
            }
        }
        System.out.println(sum);
    }
}
