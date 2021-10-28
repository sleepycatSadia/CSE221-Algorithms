import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
class Task02{
    public static void main (String[] args){
        try{
            Scanner sc = new Scanner(new File("F:\\5TH\\CSE221\\txt files\\Lab Assignment 3 Inputs\\A3 Task 2 Input.txt"));
            String line = sc.nextLine();
            String []splitJokes = line.split(" ");
            String roseJokes  = splitJokes[0];
            roseJokese=roseJokes.replaceAll(" ","");
            String chandlerJokes = splitJokes[1];
            chandlerJokes=chandlerJokes.replaceAll(" ","");
            LCS(roseJokes , chandlerJokes);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    static void LCS(String roseJokes, String chandlerJokes){
        HashMap<String, String> map = new HashMap<>();
        map.put("M","monkeys");
        map.put("B","because");
        map.put("W","wearing");
        map.put("O","of");
        map.put("C","coats");
        map.put("E","evolution");
        map.put("A","are");
        map.put("R","results");
        map.put("D","doctors");
        map.put("P","eruption");
        char[] y = roseJokes.toCharArray();
        char[] x = chandlerJokes.toCharArray();
        int[][] matrix = new int[x.length + 1][y.length + 1];
        for (int i = 1; i <= x.length; i++) {
            for (int j = 1; j <= y.length; j++) {
                if (x[i - 1] == y[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
            }
        }
        int m = x.length;
        int n = y.length;
        int count = 1;
        int totalMatched=matrix[x.length][y.length];
        String commonPart = "";
        String temp;
        while (count <=totalMatched) {
            if(m==-1)
                m++;
            if(n==-1)
                n++;
            if (matrix[m][n] != matrix[m - 1][n] && matrix[m][n] != matrix[m][n - 1]) {
                temp = x[m-1] + commonPart;
                commonPart = temp;
                m--;
                n--;
                count++;
            } else if (matrix[m][n] == matrix[m - 1][n]) {
                m--;
            } else if (matrix[m][n] == matrix[m][n - 1]) {
                n--;
            }
        }
        System.out.println(commonPart.length());
        for(int k=0 ; k<commonPart.length() ;k++)
            System.out.print(map.get(Character.toString(commonPart.charAt(k)))+" ");
        System.out.println();
    }

}
