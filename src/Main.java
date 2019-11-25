public class Main {
    public static void main(String[] args){
        Graph graph = new Graph();
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addEdge(0,1,1);
        graph.addEdge(1,2,1);
        graph.addEdge(0,3,1);
        graph.addEdge(3,4,1);

        System.out.println("Матрица Смежности:");
        graph.displayMat();
        System.out.println("DFS:");
        System.out.println(graph.getDfsResult(0));
        System.out.println("BFS:");
        System.out.println(graph.getBfsResult());

        Graph graph1 = new Graph();
        graph1.addVertex('A');
        graph1.addVertex('B');
        graph1.addVertex('C');
        graph1.addVertex('D');
        graph1.addVertex('E');
        graph1.addVertex('F');
        graph1.addEdge(0,1,6);
        graph1.addEdge(0,3,4);
        graph1.addEdge(1,3,7);
        graph1.addEdge(1,2,10);
        graph1.addEdge(1,4,7);
        graph1.addEdge(3,2,8);
        graph1.addEdge(3,4,12);
        graph1.addEdge(2,4,5);
        graph1.addEdge(2,5,6);
        graph1.addEdge(4,5,7);
        System.out.println("Kruskal:");
        graph1.kruskal();

        Graph graph2 = new Graph();
        graph2.addVertex('1');
        graph2.addVertex('2');
        graph2.addVertex('3');
        graph2.addVertex('4');
        graph2.addVertex('5');
        graph2.addVertex('6');
        graph2.addVertex('7');
        graph2.addEdge(0,2,13);
        graph2.addEdge(0,3,19);
        graph2.addEdge(2,1,20);
        graph2.addEdge(3,1,4);
        graph2.addEdge(3,6,17);
        graph2.addEdge(1,4,15);
        graph2.addEdge(4,5,22);
        graph2.addEdge(4,6,8);
        graph2.addEdge(6,5,10);
        System.out.println("Prim:");
        graph2.prim();

        Graph graph3 = new Graph();
        graph3.addVertex('A');
        graph3.addVertex('B');
        graph3.addVertex('C');
        graph3.addVertex('D');
        graph3.addVertex('E');
        graph3.addDirectEdge(0,1,50);
        graph3.addDirectEdge(0,3,80);
        graph3.addDirectEdge(1,3,90);
        graph3.addDirectEdge(1,2,60);
        graph3.addDirectEdge(4,1,50);
        graph3.addDirectEdge(3,2,20);
        graph3.addDirectEdge(2,4,40);
        graph3.addDirectEdge(3,4,70);
        System.out.println("Алгоритм Дейкстры:");
        graph3.deyxtr(0);

        Graph graph4 = new Graph();
        graph4.addVertex('A');
        graph4.addVertex('B');
        graph4.addVertex('C');
        graph4.addVertex('D');
        graph4.addDirectEdge(0,2,3);
        graph4.addDirectEdge(1,0,2);
        graph4.addDirectEdge(2,1,7);
        graph4.addDirectEdge(2,3,1);
        graph4.addDirectEdge(3,0,6);
        System.out.println("Floyd:");
        graph4.floyd();

        Graph graph5 = new Graph();
        graph5.addVertex('A');
        graph5.addVertex('B');
        graph5.addVertex('C');
        graph5.addVertex('D');
        graph5.addDirectEdge(1,0,70);
        graph5.addDirectEdge(1,3,10);
        graph5.addDirectEdge(3,2,20);
        graph5.addDirectEdge(2,0,30);
        System.out.println("Floyd2:");
        graph5.floyd();
    }
}
