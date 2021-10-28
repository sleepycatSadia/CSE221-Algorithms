import java.util.Scanner;
import java.io.File;
class Task01{
    public static void main (String[] args){
        try{
            Scanner sc = new Scanner(new File("F:\\5TH\\CSE221\\txt files\\Lab Assignment 3 Inputs\\A3 Task 1 Input.txt"));
            String line = sc.nextLine();
            String []splitNotes = line.split(" ");
            String schoolNote  = splitNotes[0];
            schoolNote=schoolNote.replaceAll(" ","");
            String studentNote = splitNotes[1];
            studentNote=studentNote.replaceAll(" ","");
            lcs(schoolNote , studentNote);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    static void lcs(String schoolNote, String studentNote){
        char[] y = schoolNote.toCharArray();
        char[] x = studentNote.toCharArray();
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
        int percentage=matrix[m][n]*100/schoolNote.length();
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
        System.out.println(commonPart);
        System.out.println(percentage+"% PASSED");
    }

}