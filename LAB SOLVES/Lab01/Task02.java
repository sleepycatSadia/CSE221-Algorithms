import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Task02 {

    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new File("C:\\Users\\User\\Google Drive\\Codes\\CSE221\\Assignment 1\\input2.txt"));
            int vertex = Integer.parseInt(sc.nextLine());
            int edge = Integer.parseInt(sc.nextLine());
            String[] splitA;

            int[][] matrix = new int[vertex][vertex];

            for(int i = 0; i < edge; ++i){
                String line = sc.nextLine();
                splitA = line.split(" ");
                int x = Integer.parseInt(splitA[0]);
                int y = Integer.parseInt(splitA[1]);
                matrix[x][y] = 1;
                matrix[y][x] = 1; // since it's bidirectional
            }
            int destination = Integer.parseInt(sc.nextLine());
            int source1 = Integer.parseInt(sc.nextLine());
            int source2 = Integer.parseInt(sc.nextLine());
            int ans1 = bfs(matrix, source1, destination);
            int ans2 = bfs(matrix, source2, destination);
            if (ans1 == ans2) System.out.println("Both");
            else System.out.println(ans1 < ans2? "Nora" : "Lara");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static int bfs(int[][] a, int s, int d) {
        int[] color = new int[a.length];
        int[] level = new int[a.length];
        int[] parent = new int[a.length];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < a.length; i++) {
            color[i] = 0; // 0,1,2 means white, grey and black respectively
            level[i] = Integer.MAX_VALUE; // defaulting the value to infinity
            parent[i] = -1; // -1 means null
        }

        color[s] = 1;
        level[s] = 0;

        q.add(s);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = 0; i < a.length; ++i) {
                if (a[u][i] == 1 && color[i] == 0) {
                    color[i] = 1;
                    level[i] = level[u] + 1;
                    parent[i] = u;
                    q.add(i);
                    if (i == d) {
                        return level[i];
                    }
                }
            }
        }
        return 0;
    }
}
