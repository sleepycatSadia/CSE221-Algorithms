import java.io.File;
import java.util.LinkedList;
import java.util.*;

public class Task02 {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(new File("F:\\5TH\\CSE221\\txt files\\Lab Assignment 2 Inputs\\A2 Task 2 Input.txt"));
            int vertex = Integer.parseInt(sc.nextLine());
            int edge = Integer.parseInt(sc.nextLine());
            int[][] matrix = new int[vertex][edge];
            String[] splitA;
            for(int i = 0; i < edge; i++){
                String lineA = sc.nextLine();
                splitA = lineA.split(",");
                int x = Integer.parseInt(splitA[0]);
                int y = Integer.parseInt(splitA[1]);
                int cost = Integer.parseInt(splitA[2]);
                matrix[x][y]= cost; // since it's directed graph
            }
            int source = Integer.parseInt(sc.nextLine());
            int goal = Integer.parseInt(sc.nextLine());
            String[] splitB;
            String lineB = sc.nextLine();
            splitB = lineB.split(",");
            LinkedList<Integer> repairOngoing = new LinkedList<>();
            for (String s : splitB) {
                repairOngoing.add(Integer.parseInt(s));
            }
            DijkstraImp djk=new DijkstraImp(vertex,source,matrix,repairOngoing);
            djk.dijkstra(source);
            djk.printPath(djk.parentArr,goal,goal);
            int cost=djk.distanceArr[goal];
            System.out.println();
            System.out.println("Path Cost: "+cost);
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
    int[] distanceArr;//keeps track of distance of Nodes
    int[] parentArr;//keeps track of parent Node of each Node
    int start;
    static String []areaName={"Mouchak","Panthapath","Rampura","Shahahbagh","Dhanmondi","Lalmatia","Badda","Hatirjheel","Nilkhet","TSC","Dhaka University","BUET"};
    LinkedList<Integer> repairOngoing;
    PriorityQueue<Node>pq;
    public DijkstraImp(){
    }
    public DijkstraImp(int _vertexNum,int _start, int[][] _adjMatrix, LinkedList<Integer> _repairOngoing){
        repairOngoing=_repairOngoing;
        vertexNum=_vertexNum;
        start=_start;
        adjMatrix=_adjMatrix;
        distanceArr=new int[vertexNum];
        parentArr = new int[vertexNum];
        for(int i = 0 ; i < vertexNum ; i++){
            distanceArr[i]=Integer.MAX_VALUE;
            parentArr[i] = 0;
        }
        parentArr[start]=-1;
        pq= new PriorityQueue<>(vertexNum, new Node());
    }
    void dijkstra(int s){
        distanceArr[s]=0;
        pq.add(new Node(s,distanceArr[s]));
        while(!pq.isEmpty()){
            int u=pq.remove().node;
            if(!repairOngoing.contains(u)) {
                for (int v = 0; v < vertexNum; v++) {
                    if (adjMatrix[u][v] > 0 && distanceArr[v] > adjMatrix[u][v] + distanceArr[u]) {
                        distanceArr[v] = adjMatrix[u][v] + distanceArr[u];
                        parentArr [v] = u;
                        pq.add(new Node(v, distanceArr[v]));
                    }
                }
            }
        }
    }
    void printPath(int[] parentArr, int currentPosition , int destination){
        if (parentArr[currentPosition]==-1) {
            System.out.print (areaName[currentPosition]);
            if(currentPosition != destination)
                System.out.print("->");
            return;
        }
        printPath(parentArr, parentArr[currentPosition],destination);
        System.out.print (areaName[currentPosition]);
        if(currentPosition != destination)
            System.out.print("->");
    }
}



