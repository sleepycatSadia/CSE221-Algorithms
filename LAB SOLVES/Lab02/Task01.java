import java.io.File;
import java.util.*;

public class Task01 {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new File("F:\\5TH\\CSE221\\txt files\\Lab Assignment 2 Inputs\\A2 Task 1 Input.txt"));
            String[] split;
            String line = sc.nextLine();
            split = line.split(" ");
            int vertex = Integer.parseInt(split[0]);
            int edge = Integer.parseInt(split[1]);
            int hotel = Integer.parseInt(split[2]);
            int missionNum = Integer.parseInt(split[3]);
            int[][] matrix = new int[vertex][vertex];
            String[] splitA;
            for(int i = 0; i < edge; i++){
                String lineA = sc.nextLine();
                splitA = lineA.split(" ");
                int x = Integer.parseInt(splitA[0])-1;//-1 because in this matrix ,node index start from 0
                int y = Integer.parseInt(splitA[1])-1;
                int marker = Integer.parseInt(splitA[2]);
                matrix[x][y] = marker; // since it's directed
            }
            for(int i = 1; i <=missionNum; ++i){
                String lineB = sc.nextLine();
                String[] splitB;
                splitB = lineB.split(" ");
                int source = Integer.parseInt(splitB[0]);
                int goal = Integer.parseInt(splitB[1]);
                DijkstraImp djk=new DijkstraImp(vertex,matrix);
                boolean condition1 =pathExists(matrix, source-1, hotel-1);//To check if a path exists from start position to hotel
                boolean condition2 =pathExists(matrix, hotel-1, goal-1);//To check if a path exists from hotel to final position
                if(source ==  hotel) condition1 = true;
                if(hotel == goal) condition2 = true;
                int markerCost=0;
                if(condition1) {
                    djk.dijkstra(source-1);
                    markerCost = djk.distanceArr[hotel - 1];
                    if(condition2) {
                        djk.dijkstra(hotel-1);
                        markerCost += djk.distanceArr[goal - 1];
                    }
                }
                System.out.println("Case "+i+":");
                if(condition1 && condition2 )
                    System.out.println(markerCost);
                else
                    System.out.println("Be seeing ya, John");

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static boolean pathExists(int[][] a, int s, int d) {
        int[] color = new int[a.length];
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            color[i] = 0; // 0,1,2 means white, grey and black respectively
        }
        color[s] = 1;
        q.add(s);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = 0; i < a.length; ++i) {
                if (a[u][i] > 0 && color[i] == 0) {
                    color[i] = 1;
                    q.add(i);
                    if (i == d) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
class Node implements Comparator<Node>{
    int node;
    int distance;
    public Node(){
    }
    public Node(int _node,int _distance){
        node=_node;
        distance=_distance;
    }
    public int compare(Node n1,Node n2){
        if(n1.distance<n2.distance)
            return -1;
        else if(n1.distance>n2.distance)
            return 1;
        return 0;
    }
}
class DijkstraImp{
    int vertexNum;
    int[][] adjMatrix;
    int[] distanceArr;
    PriorityQueue<Node>pq;
    public DijkstraImp(){
    }
    public DijkstraImp(int _vertexNum, int[][] _adjMatrix){
        vertexNum=_vertexNum;
        adjMatrix=_adjMatrix;
        distanceArr=new int[vertexNum];
        for(int i=0 ; i<vertexNum ; i++){
            distanceArr[i]=Integer.MAX_VALUE;
        }
        pq= new PriorityQueue<>(vertexNum, new Node());
    }
    void dijkstra(int s){
        distanceArr[s]=0;
        pq.add(new Node(s,distanceArr[s]));
        while(!pq.isEmpty()){
            int u=pq.remove().node;
            for(int v=0 ; v<vertexNum ; v++){
                if(adjMatrix[u][v]>0 && distanceArr[v]>adjMatrix[u][v]+distanceArr[u]){
                    distanceArr[v]=adjMatrix[u][v]+distanceArr[u];
                    pq.add(new Node(v,distanceArr[v]));
                }
            }
        }
    }
}



