//Evan Huynh, Enzo Hiu, Thomas Guo Q3 Cemetery Lab
package com.cemetary;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;
import java.util.Calendar;

public class Main
{
    public static int getFileSize(String fileName) throws IOException
    {
        Scanner input = new Scanner(new FileReader(fileName));
        int size = 0;
        while (input.hasNextLine())                //while there is another line in the file
        {
            size++;                                        //add to the size
            input.nextLine();                            //go to the next line in the file
        }
        input.close();                                    
        return size;
    }

    public static String[] readFile(String fileName) throws IOException
    {
        int size = getFileSize(fileName);        //holds the # of elements in the file
        String[] list = new String[size];        
        Scanner input = new Scanner(new FileReader(fileName));
        int i = 0;                                            //index for placement in the array
        String line;
        while (input.hasNextLine())                
        {
            line = input.nextLine();                    //read in the next Line in the file and store it in line
            list[i] = line;                                //add the line into the array
            i++;                                            //advance the index of the array
        }
        input.close();
        return list;
    }

    public static void writeToFile(String[] array, String filename) throws IOException
    {
        System.setOut(new PrintStream(new FileOutputStream(filename)));
        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);
    }

    public static void main(String[] args) throws IOException
    {
        String[] strArr = readFile("cemetery_orig.txt");
        ArrayList<Deceased> deceased = new ArrayList<>();
        for (int i = 0; i < strArr.length; i++)
        {
            String name = strArr[i].substring(0, 25).trim(); //trim cuts off excess spaces
            String date = strArr[i].substring(25, 37).trim();
            float age = Float.parseFloat(strArr[i].substring(37, 41).trim());
            String address = strArr[i].substring(41).trim();
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
        DeceasedList deceasedList = new DeceasedList(deceased);
        while(true)
        {
            System.out.println("Enter 1 to search by last name");
            System.out.println("Enter 2 to search by street name");
            System.out.println("Enter 3 to return sorted data by category");
            System.out.println("Enter 4 to return interesting data");
            System.out.println("Enter 5 to get a list of street names");
            System.out.println("Enter 0 to exit \n");
            Scanner s = new Scanner(System.in);
            System.out.print("Enter Choice: ");
            String choice = s.nextLine();
            if(choice.equals("0"))
            {
                break; //exit the program
            }
            switch(choice)
            {
                case "1":
                    System.out.print("Enter last name in ALL CAPS: ");
                    String name = s.nextLine();
                    System.out.println(deceasedList.searchByName(name));
                    break;
                case "2":
                    System.out.print("Enter street: ");
                    String street = s.nextLine();
                    System.out.println(deceasedList.searchByStreet(street));
                    break;
                case "3":
                    System.out.println("Enter 1 to sort by name");
                    System.out.println("Enter 2 to sort by burial date");
                    System.out.println("Enter 3 to sort by age");
                    System.out.println("Enter 4 to sort by address");
                    System.out.print("Enter Choice: ");
                    String sortChoice = s.nextLine();
                    switch(sortChoice)
                    {
                        case "1":
                            deceasedList.sortByName();
                            System.out.println(deceasedList);
                            break;
                        case "2":
                            deceasedList.sortByDate();
                            System.out.println(deceasedList);
                            break;
                        case "3":
                            deceasedList.sortByAge();
                            System.out.println(deceasedList);
                            break;
                        case "4":
                            deceasedList.sortByAddress();
                            System.out.println(deceasedList);
                            break;
                    }
                    break;
                case "4":
                    System.out.println();
                    System.out.println("Average Age at Death: " + deceasedList.averageAgeAtDeath());
                    System.out.println("Year with Highest Mortality Rate: " + deceasedList.highestMortalityRateYear());
                    System.out.println("Oldest Age at Death: " + deceasedList.oldestAge());
                    System.out.println("Street with Highest Mortality Rate: " + deceasedList.highestMortalityRateStreet());
                    System.out.println();
                    break;
                case "5":
                    System.out.println(deceasedList.getStreets());
            }
        }
    }

}