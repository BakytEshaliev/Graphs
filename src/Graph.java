import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex vertexList[];
    private int adjMat[][];
    private int reverseAdjMat[][];
    private int nVerts;
    private StackX stack = new StackX();
    private String dfsResult = "";
    private Queue queue = new Queue();
    private String bfsResult = "";
    private ArrayList<Edge> edges = new ArrayList<>();
    private StackX stackK = new StackX();

    public Graph(){
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        reverseAdjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int i=0;i<MAX_VERTS;i++){
            for(int j=0;j<MAX_VERTS;j++){
                adjMat[i][j]=0;
            }
        }
    }

    public void addVertex(char label){
        Vertex vertex = new Vertex(label,nVerts);
        vertexList[nVerts]=vertex;
        nVerts++;
    }

    public void addEdge(int a,int b,int size){
        adjMat[a][b]=size;
        adjMat[b][a]=size;
        edges.add(new Edge(a,b,size));
    }

    public void addDirectEdge(int a,int b, int size){
        adjMat[a][b]=size;
        reverseAdjMat[b][a]=size;
        edges.add(new Edge(a,b,size));
    }

    public void displayMat(){
        for (int i=0;i<nVerts;i++){
            for (int j=0;j<nVerts;j++){
                System.out.print(adjMat[j][i]+" ");
            }
            System.out.println();
        }
    }

    public void dfsK(int v){
        if(!vertexList[v].wasVisited) {
            vertexList[v].wasVisited = true;
            System.out.println(v+" pushed");
           // stackK.push(v);
            dfsResult+=vertexList[v].label;
        }
        System.out.println(v+" pushed");
        stack.push(v);
        int adjUnvisitedVertex=getAdjUnvisitedVertex(v);
        if (adjUnvisitedVertex!=-1){
            System.out.println("dfs:"+adjUnvisitedVertex);
            dfs(adjUnvisitedVertex);
        }
        else{
            stack.pop();
            if(!stack.isEmpty())
                dfs(stack.pop());
        }
    }

    public void dfs(int v){
        if(!vertexList[v].wasVisited) {
            vertexList[v].wasVisited = true;
            dfsResult+=vertexList[v].label;
        }
        stack.push(v);
        int adjUnvisitedVertex=getAdjUnvisitedVertex(v);
        if (adjUnvisitedVertex!=-1){
            dfs(adjUnvisitedVertex);
        }
        else{
            stack.pop();
            if(!stack.isEmpty())
                dfs(stack.pop());
        }
    }

    public void bfs(int v){
        if(!vertexList[v].wasVisited) {
            vertexList[v].wasVisited = true;
            bfsResult+=vertexList[v].label;
        }
        int adjUnvisitedVertex=getAdjUnvisitedVertex(v);
        if (adjUnvisitedVertex!=-1){
            vertexList[adjUnvisitedVertex].wasVisited=true;
            bfsResult+=vertexList[adjUnvisitedVertex].label;
            queue.insert(adjUnvisitedVertex);
            bfs(v);
        }
        else{
            if (!queue.isEmpty())
                bfs(queue.remove());
        }
    }

    public int getAdjUnvisitedVertex(int v){
        for(int i=0;i<nVerts;i++){
            if(adjMat[v][i]!=0 && !vertexList[i].wasVisited){
                return i;
            }
        }
        return -1;
    }

    public String getDfsResult(int a){
        dfs(a);
        String result = dfsResult;
        dfsResult = "";
        clearAllVertexStatus();
        return result;
    }

    public  String getBfsResult(){
        bfs(0);
        clearAllVertexStatus();
        return bfsResult;
    }

    public void clearAllVertexStatus(){
        for (int i =0;i<nVerts;i++)
            vertexList[i].wasVisited=false;
    }

    public void kruskal(){
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.size-e2.size;
            }
        });
        Graph resultGraph = new Graph();
        for(int i=0;i<nVerts;i++){
            resultGraph.addVertex(vertexList[i].label);
        }
        int ecounter = 0;
        int k = 0;
        while (ecounter<nVerts-1&&k<edges.size()){
            Edge edge=edges.get(k);
            if (!resultGraph.isConnected(edge.a,edge.b)){
                resultGraph.addEdge(edge.a,edge.b,edge.size);
                ecounter++;
            }
            k++;
        }
        for(Edge edge:resultGraph.edges){
            System.out.println(vertexList[edge.a].label+"---"+vertexList[edge.b].label+"==="+edge.size);
        }
    }

    public void prim(){
        int[] vertexes = new int[nVerts];
        int count=1;
        for(int i=1;i<nVerts;i++){
            vertexes[i]=-1;
        }
        ArrayList<Edge> result = new ArrayList<>();
        for(int i=1;i<nVerts;i++){
            Edge minEdge=new Edge(-1,-1,Integer.MAX_VALUE);
            for (int j=0;j<nVerts;j++){
                for (int k=0;k<nVerts;k++){
                    if(containsV(vertexes,j)&&!containsV(vertexes,k)&&adjMat[j][k]!=0&&adjMat[j][k]<minEdge.size){
                        minEdge=new Edge(j,k,adjMat[j][k]);
                    }
                }
            }
            vertexes[count]=minEdge.b;
            count++;
            result.add(minEdge);
        }
        for(Edge edge:result){
            System.out.println(vertexList[edge.a].label+"---"+vertexList[edge.b].label+"==="+edge.size);
        }
    }

    private boolean containsV(int[] vertexes,int a){
        for(int i=0;i<nVerts;i++){
            if(vertexes[i]==a)
                return true;
        }
        return false;
    }

    private boolean isConnected(int a, int b){
        String dfs = getDfsResult(a);
        return dfs.indexOf(vertexList[b].label)!=-1;
    }

    public void dijkstra(int v){
        int[] result = new int[nVerts];
        int[] obr=new int[nVerts];

        for(int i=0;i<nVerts;i++)
            result[i]=-1;

        for (int i=0;i<nVerts;i++)
            obr[i]=0;

        result[v]=0;

        for(int i=0;i<nVerts;i++){

            int min=Integer.MAX_VALUE;
            int indexOfMin=-1;

            for(int j=0;j<nVerts;j++){
                if (result[j]<min && obr[j]==0&&result[j]!=-1) {
                    min = result[j];
                    indexOfMin=j;
                }
            }

            if (indexOfMin==-1)
                break;

            obr[indexOfMin]=1;

            for(int j=0;j<nVerts;j++){
                if(((obr[j]==0&&adjMat[indexOfMin][j]+min<result[j])||result[j]==-1)&&adjMat[indexOfMin][j]!=0) {
                    result[j] = adjMat[indexOfMin][j] + min;
                }
            }
        }

        for(int i=0;i<nVerts;i++){
            System.out.println(i+"--"+result[i]);
        }
    }

    public void floyd(){
        int[][] mass = adjMat;

        for (int i=0;i<nVerts;i++){
            for (int j=0;j<nVerts;j++){
                if(i!=j&&mass[i][j]==0)
                    mass[i][j]=Integer.MAX_VALUE;
            }
        }

        for(int k=0;k<nVerts;k++){
            for (int i=0;i<nVerts;i++){
                for (int j=0;j<nVerts;j++){
                    if (mass[i][k]!=Integer.MAX_VALUE&&mass[k][j]!=Integer.MAX_VALUE)
                        mass[i][j]=Math.min(mass[i][j],mass[i][k]+mass[k][j]);
                }
            }
        }
        for (int i=0;i<nVerts;i++){
            for (int j=0;j<nVerts;j++){
                if(mass[i][j]==Integer.MAX_VALUE)
                    System.out.print("Беск ");
                else
                    System.out.print(mass[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void kosaraju(){
        clearAllVertexStatus();
        for (int i=0;i<nVerts;i++){
            if (!vertexList[i].wasVisited){
                dfs(i);
                for (int j=dfsResult.length()-1;j>=0;j--){
                    System.out.println("push "+indexOf(dfsResult.charAt(j)));
                    stackK.push(indexOf(dfsResult.charAt(j)));
                }
                System.out.println(dfsResult);
                dfsResult="";
            }
        }


        dfsResult="";

        clearAllVertexStatus();
        adjMat=reverseAdjMat;

        ArrayList<String> result = new ArrayList<>();


        for (int i=0;i<nVerts;i++){
            if (!stackK.isEmpty()) {
                if (!vertexList[stackK.peek()].wasVisited) {
                    dfs(stackK.pop());
                    result.add(dfsResult);
                    dfsResult = "";
                } else {
                    stackK.pop();
                }
            }
        }

        System.out.println();

        System.out.println("kosarayo:");
        System.out.println(result);

        clearAllVertexStatus();
    }

    public int indexOf(char label){
        for (int i=0;i<nVerts;i++){
            if (vertexList[i].label==label)
                return i;
        }
        return -1;
    }
}
