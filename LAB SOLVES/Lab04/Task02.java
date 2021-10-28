import java.util.Scanner;
import java.io.File;
class Task02{
    public static String[] trophyNames;
    public static String[] clubNames;
    public static double[] trophyWeight;
    public static double[] trophyPrice;
    static int trophyIndex=0;
    public static void main (String[] args){
        try{
            Scanner sc = new Scanner(new File("F:\\5TH\\CSE221\\txt files\\Lab Assignment 4 Inputs\\A4 Task 2 Input.txt"));
            int maxWeight=Integer.parseInt(sc.nextLine());
            int totalTrophies=Integer.parseInt(sc.nextLine());
            trophyNames=new String[totalTrophies];
            clubNames =new String[totalTrophies];
            trophyWeight = new double [totalTrophies];
            trophyPrice =new double [totalTrophies];
            for(int i=1 ; i <= totalTrophies ; i++){
                String line = sc.nextLine();
                String []splitNotes = line.split(", ");
                trophyNames[trophyIndex]= splitNotes[0];
                trophyWeight[trophyIndex]= Double.parseDouble(splitNotes[1]);
                trophyPrice[trophyIndex]= Double.parseDouble(splitNotes[2]);
                clubNames[trophyIndex]= splitNotes[3];
                trophyIndex++;
            }

            knapsack(maxWeight);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void knapsack(int n){
        double[][] k = new double[trophyWeight.length+1][n+1];
        for(int i = 0 ; i <= trophyWeight.length  ; i++){
            for(int j = 0 ; j <= n ; j++){
                if (i == 0 || j == 0)
                    k[i][j] = 0;
                else if(trophyWeight[i-1] <= j){
                    k[i][j] = Math.max(trophyPrice[i-1] + k[i-1][(int)(j-trophyWeight[i-1])], k[i-1][j]);
                }
                else{
                    k[i][j] = k[i-1][j];

                }
            }
        }
        System.out.println("Name of clubs whose trophies were sold:");
        int i = trophyNames.length-1;
        int j = n;
        String trophiesForSell="";
        while(i > 0 && j > 0){
            if(k[i][j] == k[i-1][j]){
                i--;
            }
            else{
                trophiesForSell+=trophyNames[i]+",";
                i--;
                j-=trophyWeight[i];
            }
        }
        String[] printTrophies =trophiesForSell.split(",");
        for(int idx = printTrophies.length-1 ; idx >= 0 ; idx--){
            System.out.print(printTrophies[idx]);
            if(idx!=0)
                System.out.print("->");
        }
        System.out.println();
        System.out.println("Maximum money he earned: "+k[trophyWeight.length][n]+" million");


    }

}
/*
There is a typo in the input given in Assignment question.Weight of Individual trophy
would be actually 5kg according to the table and Sample output given in pdf .But in the
given input it was 4kg.We used 5kg so that our output matches with given output.
***************
Input used here:
15
6
Chelsea, 8, 9.6, Premier league
Inter Milan, 7, 9.4, Champion’s League
Individual, 5, 8.3, Coach of the Year
Real Madrid, 5, 8.7, Liga BBVA
Porto, 4, 8.5, Champion’s League
Mansister United, 6, 8.9, Europa League
 */