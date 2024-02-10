package com.cemetary;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LoadDeceasedList {
    public static DeceasedList loadFromResources() {
        InputStream is = LoadDeceasedList.class.getResourceAsStream("/cemetery_orig.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        return load(br.lines().collect(Collectors.toList()));
    }
    
    private static DeceasedList load(List<String> lines) {
        ArrayList<Deceased> deceased = new ArrayList<>();
        for (String line: lines)
        {
            String name = line.substring(0, 25).trim(); //trim cuts off excess spaces
            String date = line.substring(25, 37).trim();
            float age = Float.parseFloat(line.substring(37, 41).trim());
            String address = line.substring(41).trim();
            String street = null;
            if(address.contains(","))
            {
                street = address.replaceAll("[0-9]","");
                street = street.trim();
                street = street.split(",")[1].trim();
            }

            if(street != null)
            {
                Deceased deadGuy = new Deceased(name, date, age, address, street);
                deceased.add(deadGuy);
            }else
            {
                Deceased deadGuy = new Deceased(name, date, age, address);
                deceased.add(deadGuy);
            }
        }
        return new DeceasedList(deceased);
    }
}
