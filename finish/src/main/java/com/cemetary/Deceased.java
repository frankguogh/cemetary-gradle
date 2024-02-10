package com.cemetary;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Deceased
{
    public static SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy");
    public String name;
    public Calendar date;
    public float age;
    public String address;
    public String street;

    public Deceased(String name, String date, float age, String address)
    {
        this.name = name;
        String[] dates = date.split("\\s+");
        this.date = Calendar.getInstance();
        int month = stringToMonth(dates[1]);
        this.date.set(Integer.parseInt(dates[2]), month, Integer.parseInt(dates[0]));
        this.age = age;
        this.address = address;
    }

    public Deceased(String name, String date, float age, String address, String address2)
    {
        this.name = name;
        String[] dates = date.split("\\s+");
        this.date = Calendar.getInstance();
        int month = stringToMonth(dates[1]);
        this.date.set(Integer.parseInt(dates[2]), month, Integer.parseInt(dates[0]));
        this.age = age;
        this.address = address;
        this.street = address2;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + ", Date: " + sdf.format(date.getTime()) +  ", Age: " + age + ", Address: " + address;
    }

    public String getFormattedDate() {
        return sdf.format(date.getTime());
    }

    private static int stringToMonth(String monthStr)
    {
        int month = -1;
        switch (monthStr)
        {
            case "Jan":
                month = 0;
                break;
            case "Feb":
                month = 1;
                break;
            case "Mar":
                month = 2;
                break;
            case "Apr":
                month = 3;
                break;
            case "May":
                month = 4;
                break;
            case "Jun":
                month = 5;
                break;
            case "Jul":
                month = 6;
                break;
            case "Aug":
                month = 7;
                break;
            case "Sep":
                month = 8;
                break;
            case "Oct":
                month = 9;
                break;
            case "Nov":
                month = 10;
                break;
            case "Dec":
                month = 11;
                break;
        }
        return month;
    }
}