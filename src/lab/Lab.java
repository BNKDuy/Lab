package lab;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Lab 
{
    // these arrays hold the information from the data file.
    private static ArrayList<Integer> rank = new ArrayList<Integer>();
    private static ArrayList<String> name = new ArrayList<String>();
    private static ArrayList<String> country = new ArrayList<String>();
    private static ArrayList<Integer> point = new ArrayList<Integer>();
    private static ArrayList<String> change = new ArrayList<String>();
    private static ArrayList<String> ppoint = new ArrayList<String>();
    private static ArrayList<String> schange = new ArrayList<String>();
    
    // These arrays hold the name of the countries in that continent
    private static ArrayList<Integer> asia = new ArrayList<Integer>();
    private static ArrayList<Integer> europe = new ArrayList<Integer>();
    private static ArrayList<Integer> africa = new ArrayList<Integer>();
    private static ArrayList<Integer> namerica = new ArrayList<Integer>();
    private static ArrayList<Integer> samerica = new ArrayList<Integer>();
    
    // Color
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m"; 
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    
    static Scanner input = new Scanner(System.in);
    
    //import the data from the file to ArrayList in this program
    public static void data() throws IOException
    {
        File in = new File("clubRanking.csv");
        Scanner read = new Scanner(in);
        read.useDelimiter(",|\r\n");
        
        while (read.hasNext())
        {
            rank.add(read.nextInt());
            name.add(read.next());
            country.add(read.next());
            point.add(read.nextInt());
            change.add(read.next());
            ppoint.add(read.next());
            schange.add(read.next());
        }
    }
    
    //print the search result
    public void print(ArrayList<Integer> arr)
    {
        for (int i = 0; i < arr.size(); i++)
        {
            System.out.println(name.get(arr.get(i)) + "(" + country.get(arr.get(i)) + ")" + "\nPoint: " + point.get(arr.get(i)) + "\nRank: " + rank.get(arr.get(i)) + "\n\n\n");
        }
    }
    
    //move to the next page
    public static void nextPage()
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.println();
        }
    }

    //Return the Mean of the array
    private static Double getMean(ArrayList<Integer> array) 
    {
        double total = 0;
        for (double num : array) 
        {
            total += num;
        }
        return total / array.size();
    }
    
    //Return the Standard deviation of the array
    private static Double standardDeviation(ArrayList<Integer> array) 
    {
        double mean = getMean(array);
        double sum = 0;
        for (double x : array) 
        {
            sum += Math.pow(x - mean, 2);
        }
        double variance = sum / array.size();
        return Math.sqrt(variance);
    }
    
    //Methods for question1
    
    //this method return the arraylist with just only the value at index
    public static ArrayList<Integer> fillIntArray (ArrayList<Integer> arr, ArrayList<Integer> index)
    {
        ArrayList<Integer> out = new ArrayList<Integer>();
        
        for (int i = 0; i < index.size(); i++)
        {
            out.add(arr.get(index.get(i)));
        }
        return out;
    }
    
    //search and return the index of all values between the input range 
    public static ArrayList<Integer> rangeSearch(ArrayList<Integer> arr, int hi, int lo)
    {
        ArrayList<Integer> index = new ArrayList<Integer>();
        
        for (int i = 0; i < arr.size(); i++)
        {
            if (lo <= arr.get(i) && arr.get(i) <= hi) index.add(i);
        }
        
        return index;
    }
    
    // Calculates Pearson coefficient
    private static Double pearson(ArrayList<Integer> xAxis, ArrayList<Integer> yAxis) 
    {
        double xMean = getMean(xAxis);
        double yMean = getMean(yAxis);
        int n = xAxis.size();
        
        double numerator = 0;
        for (int i = 0; i < xAxis.size(); i++) {
            numerator += (xAxis.get(i) - xMean)*(yAxis.get(i) - yMean);
        }
        return numerator / (n * standardDeviation(xAxis) * standardDeviation(yAxis));
    }
    
    
    
    
    //Methods for question2
    
    // search and return the index of the target string that appear in the dataset
    public ArrayList<Integer> targetSearch(ArrayList<String> arr, String target)
    {
        ArrayList<Integer> index = new ArrayList<Integer>();
        
        for (int i = 0; i < arr.size(); i++)
        {
            if (arr.get(i).equalsIgnoreCase(target)) index.add(i);
        }
        
        return index;
    }
    
    public static void fillContinentArray(String file, ArrayList<Integer> arr) throws IOException
    {
        ArrayList<String> list = new ArrayList<String>();

        File in = new File(file);
        Scanner read = new Scanner(in);
        read.useDelimiter(",|\r\n");
        
        while (read.hasNext())
        {
            list.add(read.next());
        }
        
        for (int i = 0; i < country.size(); i++)
        {
            for (int j = 0; j < list.size(); j++)
            {
                if (country.get(i).equalsIgnoreCase(list.get(j))) arr.add(i);              
            }
        }
    }

    public static void continentFill() throws IOException
    {
        fillContinentArray("Asia.csv", asia);
        fillContinentArray("Africa.csv", africa);
        fillContinentArray("Europe.csv", europe);
        fillContinentArray("NAmerica.csv", namerica);
        fillContinentArray("SAmerica.csv", samerica);
    }
    
    //This method calculate the central tendency (mean, median, mode) and standard deviation
    public static void centralTendency(ArrayList<Integer> arr)
    {
        double mean, median, mode;
        int sum = 0;
        int maxCount = 0;
        int maxValue = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for (int i = 0; i < arr.size(); i++)
        {
            list.add(rank.get(arr.get(i)));
        }
        
        for (int i = 0; i < list.size(); i++)
        {
            int count = 0;
            
            for (int j = 0; j < list.size(); ++j) 
            {
                if (list.get(i) == list.get(j))
                count++;
            }

            if (count > maxCount) 
            {
                maxCount = count;
                maxValue = list.get(i);
            }
        }
        
        System.out.println("Mean: " + getMean(list));
        
        if ((list.size() % 2) == 1) System.out.println("Median: " + list.get(list.size()/2));    
        else System.out.println("Median: " + (((list.get(list.size()/2)) + ((list.get((list.size()/2) -1))))/2));

        System.out.println("Mode: " + maxValue);
        System.out.println("Standard deviation: " + standardDeviation(list) + "\n");
        
    }
    
    //this method check the correlation coefficient and return the strength of the correlation
    public static void correlationStrength(Double pearson)
    {
        if (0.67 < pearson && pearson <= 1) System.out.print(GREEN + "Strong positive linear correlation" + RESET);
        else if (0.33 < pearson && pearson <= 0.67) System.out.print(YELLOW + "Moderate positive linear correlation" + RESET);
        else if (0 < pearson && pearson <= 0.33) System.out.print(RED + "Weak positive linear correlation" + RESET);
        else if (0 > pearson && pearson >= -0.33) System.out.print(RED + "Weak negative linear correlation" + RESET);
        else if (-0.33 > pearson && pearson >= -0.67) System.out.print(YELLOW + "Moderate nagetive linear correlation" + RESET);
        else if (-0.67 > pearson && pearson >= -1) System.out.print(GREEN + "Strong negative linear correlation" + RESET);
        else System.out.println(RED + "No correlation" + RESET);
    }
    
    // This menu answer the first question
    public static void menu1()
    {
        int choose = -1;
        int input1, input2, high, low;
        ArrayList<Integer> index = new ArrayList<Integer>(); 
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        ArrayList<Integer> arr2 = new ArrayList<Integer>();

        nextPage();
        System.out.println("\t\t\tQUESTION 1:");
        System.out.println("- Correlation Coeficient: " + pearson(rank, point));
        System.out.print("Strength of Linear Correlation: ");
        correlationStrength(pearson(rank, point));
        System.out.println("\n\nDo you want to know further about this question?\n" + GREEN + "1. Yes" + RESET + "\n" + RED + "0. No" + RESET);
        choose = input.nextInt();
        System.out.println("The you want to search by rank or by point?\n1. Rank\n2. Point");
        choose = input.nextInt();   
        while (choose != 0)
        {    
            if (choose == 1)
            {
                System.out.println("Please enter the range of rank you want to investigate (integer only):");
                System.out.print("- From ");
                input1 = input.nextInt();
                System.out.print(" to ");
                input2 = input.nextInt();
                
                if (input1 > input2)
                {
                    high = input1;
                    low = input2;
                }
                else
                {
                    high = input2;
                    low = input1;
                }
                
                index = rangeSearch(rank, high, low);
                arr1 = fillIntArray(rank, index);
                arr2 = fillIntArray(point, index);
                
                System.out.println("- Correlation Coeficient: " + pearson(arr1, arr2));
                System.out.print("Strength of Linear Correlation: ");
                correlationStrength(pearson(arr1, arr2));
                System.out.println();
            }
            else if (choose == 2)
            {
                System.out.println("Please enter the range of point you want to investigate (integer only):");
                System.out.print("- From ");
                input1 = input.nextInt();
                System.out.print(" to ");
                input2 = input.nextInt();
                
                if (input1 > input2)
                {
                    high = input1;
                    low = input2;
                }
                else
                {
                    high = input2;
                    low = input1;
                }
                
                index = rangeSearch(point, high, low);
                arr1 = fillIntArray(rank, index);
                arr2 = fillIntArray(point, index);
                
                System.out.println("- Correlation Coeficient: " + pearson(arr1, arr2));
                System.out.print("Strength of Linear Correlation: ");
                correlationStrength(pearson(arr1, arr2));
                System.out.println();
            }
            
            System.out.println("\nDo you want to continue with question 1?\n" + GREEN + "1. Yes" + RESET + "\n" + RED + "0. No" + RESET);
            choose = input.nextInt();
            
            if (choose == 1)
            {
                System.out.println("\nThe you want to search by rank or by point?\n1. Rank\n2. Point");
                choose = input.nextInt();
            }
        }
    }
    
    //This menu answer the second question
    public static void menu2() throws IOException
    {
        ArrayList<String> countryList = new ArrayList<String>();
        
        nextPage();
        continentFill();
        
        System.out.println("\t\t\tQUESTION 2:");
        System.out.println("Asia:");
        centralTendency(asia);
        System.out.println("Europe:");
        centralTendency(europe);
        System.out.println("Africa:");
        centralTendency(africa);
        System.out.println("North America");
        centralTendency(namerica);
        System.out.println("South America");
        centralTendency(samerica);   
    }
    
    
    
    
    //MAIN METHOD
    public static void main(String[] args) throws IOException
    {
        int choose = -1;
        
        System.out.println("Which question do you want to answer?");
        System.out.println("Enter \"0\" to stop the program" );
        System.out.println("1. Is there a strong connection between ranking and point scores? (assuming it is standardized)");
        System.out.println("2. Are there geographic (continental) connections between rankings?");
        System.out.print("\nEnter your choice: ");
        choose = input.nextInt();
        
        data();
        while (choose != 0)
        {
            if (choose == 1) menu1();  
            else if (choose == 2) menu2();
            System.out.println("Do you want to continue this program?\n" + GREEN + "1. Yes" + RESET + "\n" + RED + "0. No" + RESET);
            choose = input.nextInt();
            
            if (choose == 1)
            {
                System.out.println("Which question do you want to answer?");
                System.out.println("Enter \"0\" to stop the program" );
                System.out.println("1. Is there a strong connection between ranking and point scores? (assuming it is standardized)");
                System.out.println("2. Are there geographic (continental) connections between rankings?");
                System.out.print("\nEnter your choice: ");
                choose = input.nextInt();
            }
        }     
    }
}
