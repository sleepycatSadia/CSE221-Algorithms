import java.util.Scanner;
import java.io.File;
class Task01{
    public static String[] playerNames;
    public static String[] playerPosition;
    public static int[] playerPrice;
    public static int[] playerForm;
    static int playerIndex=0;
    public static void main (String[] args){
        try{
            Scanner sc = new Scanner(new File("F:\\5TH\\CSE221\\txt files\\Lab Assignment 4 Inputs\\A4 Task 1 Input.txt"));
            int budget=Integer.parseInt(sc.nextLine());
            int shortlistPlayerNum=Integer.parseInt(sc.nextLine());
            playerNames=new String[shortlistPlayerNum];
            playerPosition =new String[shortlistPlayerNum];
            playerPrice = new int [shortlistPlayerNum];
            playerForm =new int [shortlistPlayerNum];
            for(int i=1 ; i <= shortlistPlayerNum ; i++){
                String line = sc.nextLine();
                String []splitNotes = line.split(", ");
                playerNames[playerIndex]= splitNotes[0];
                playerPrice[playerIndex]= Integer.parseInt(splitNotes[1]);
                playerForm[playerIndex]= Integer.parseInt(splitNotes[2]);
                playerPosition[playerIndex]= splitNotes[3];
                playerIndex++;
            }
            knapsack(budget);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void knapsack(int n){
        int[][] k = new int[playerPrice.length+1][n+1];
        for(int i = 0 ; i <= playerPrice.length  ; i++){
            for(int j = 0 ; j <= n ; j++){
                if (i == 0 || j == 0)
                    k[i][j] = 0;
                else if(playerPrice[i-1] <= j){
                    k[i][j] = Math.max((playerForm[i-1] + k[i-1][j-playerPrice[i-1]]) , k[i-1][j]);
                }
                else{
                    k[i][j] = k[i-1][j];

                }
            }
        }
        System.out.println("Bought Players:");
        int i = playerNames.length-1;
        int j = n;
        String playersBought="";
        while(i > 0 && j > 0){
            if(k[i][j] == k[i-1][j]){
                i--;
            }
            else{
                playersBought+=playerNames[i]+",";
                i--;
                j-=playerPrice[i];
            }
        }
        String printPlayers[]=playersBought.split(",");
        for(int idx = printPlayers.length-1 ; idx >= 0 ; idx--){
            System.out.print(printPlayers[idx]);
            if(idx!=0)
                System.out.print("->");
        }
        System.out.println();
        System.out.println("Maximum summation of form: "+k[playerPrice.length][n]);
    }
}
/*
There is a typo in the input given in Assignment question.Price of Thiago would be
actually 45 according to the table and Sample output given in pdf .But in the given 
input it was 40.We used 45 so that our output matches with given output.
***************
Input used here:
150
6
Aubameyang, 80, 96, F
Koulibaly, 70, 94, D
Thiago, 45, 83, M
Insigne, 50, 87, F
Kimmich, 40, 85, D
Sancho, 60, 89, M
 */