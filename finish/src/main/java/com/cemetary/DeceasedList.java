package com.cemetary;

import java.util.*;

public class DeceasedList
{
    public List<Deceased> deceasedList;

    public DeceasedList(List<Deceased> deceasedList)
    {
        this.deceasedList = deceasedList;
    }

    public enum SortBy {
        DATE("date"),
        NAME("name"),
        AGE("age"),
        ADDRESS("address");
        public final String value;
        SortBy(final String value) {
            this.value = value;
        }
    }

    public void sortByDate()
    {
        deceasedList.sort(Comparator.comparing(d -> d.date));
    }

    public void sortByName()
    {
        deceasedList.sort((d1, d2) -> d1.name.compareToIgnoreCase(d2.name));
    }

    public void sortByAge()
    {
        deceasedList.sort((d1, d2) -> Float.compare(d1.age, d2.age));
    }

    public void sortByAddress()
    {
        deceasedList.sort((d1, d2) -> d1.address.compareToIgnoreCase(d2.address));
    }

    public List<Deceased> searchByName(String name)
    {
        List<Deceased> temp = new ArrayList<>();
        for(int i = 0; i < deceasedList.size(); i++)
        {
            if(deceasedList.get(i).name.contains(name))
                temp.add(deceasedList.get(i));
        }
        return temp;
    }

    public float averageAgeAtDeath()
    {
        float total = 0;
        for(int i = 0; i < deceasedList.size(); i++)
        {
            total += deceasedList.get(i).age;
        }
        return total / deceasedList.size();
    }

    public float oldestAge()
    {
        float maxAge = 0;
        for(int i = 0; i < deceasedList.size(); i++)
        {
            if(deceasedList.get(i).age > maxAge)
            {
                maxAge = deceasedList.get(i).age;
            }
        }
        return maxAge;
    }

    public int highestMortalityRateYear()
    {
        HashMap<Integer, Integer> yearDeathPair = new HashMap<Integer, Integer>();
        for(int i = 0; i < deceasedList.size(); i++)
        {
            int year = deceasedList.get(i).date.get(Calendar.YEAR);
            if(yearDeathPair.containsKey(year))
            {
                yearDeathPair.put(year, yearDeathPair.get(year) + 1);
            }
            else
            {
               yearDeathPair.put(year, 1);
            }
        }

        int highestYear = 0;
        int highestMortality = Integer.MIN_VALUE;
        for(int year : yearDeathPair.keySet())
        {
            int tempMortality = yearDeathPair.get(year);
            if(tempMortality > highestMortality)
            {
                highestMortality = tempMortality;
                highestYear = year;
            }
        }

        return highestYear;
    }

    public String highestMortalityRateStreet()
    {
        HashMap<String, Integer> streetDeathPair = new HashMap<String, Integer>();
        for(int i = 0; i < deceasedList.size(); i++)
        {
            String street = deceasedList.get(i).street;
            if(streetDeathPair.containsKey(street))
            {
                streetDeathPair.put(street, streetDeathPair.get(street) + 1);
            }
            else
            {
                streetDeathPair.put(street, 1);
            }
        }

        String highestStreet = "";
        int highestMortality = Integer.MIN_VALUE;
        for(String year : streetDeathPair.keySet())
        {
            int tempMortality = streetDeathPair.get(year);
            if(tempMortality > highestMortality)
            {
                highestMortality = tempMortality;
                highestStreet = year;
            }
        }

        return highestStreet;
    }

    public List<Deceased> searchByStreet(String street)
    {
        List<Deceased> temp = new ArrayList<>();
        for(int i = 0; i < deceasedList.size(); i++)
        {
            if(deceasedList.get(i).address.contains(street))
                temp.add(deceasedList.get(i));
        }
        return temp;
    }

    public HashSet<String> getStreets()
    {
        HashSet<String> temp = new HashSet<>();
        for(int i = 0; i < deceasedList.size(); i++)
        {
            String street = deceasedList.get(i).street;
            if(street != null)
            {
                temp.add(street);
            }else
            {
                temp.add(deceasedList.get(i).street);
            }
        }
        temp.remove(null);
        return temp;
    }

    @Override
    public String toString()
    {
        return listToString(deceasedList);
    }

    public static String listToString(List<Deceased> deceasedList)
    {
        StringBuilder str = new StringBuilder();
        for (Deceased deceased : deceasedList)
        {
            str.append(deceased).append("\n");
        }
        return str.toString();
    }
}