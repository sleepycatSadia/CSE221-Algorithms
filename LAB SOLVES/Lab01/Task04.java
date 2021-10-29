import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Task04 {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new File("C:\\Users\\User\\Google Drive\\Codes\\CSE221\\Assignment 1\\input4.txt"));
            int vertex = Integer.parseInt(sc.nextLine());
            int edge = Integer.parseInt(sc.nextLine());
            String[] splitA;

            int[][] matrix = new int[vertex][vertex];

            for(int i = 0; i < edge; ++i){
                String line = sc.nextLine();
                splitA = line.split(" ");
                int x = Integer.parseInt(splitA[0]);
                int y = Integer.parseInt(splitA[1]);
                matrix[y][x] = 1; // reversing the inputs because we'll find in degree using out degree
            }
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix.length; ++j) {
                    if (matrix[i][j] != 0) break;
                    if (j == matrix.length-1) {
                        System.out.println(i);
                        return;
                    }
                }
            }
            //bfs(matrix, destination, sources);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
